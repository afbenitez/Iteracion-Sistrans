/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.epsAndes.interfazApp;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.sun.prism.RectShadowGraphics;

import uniandes.isis2304.epsAndes.negocio.Cita;
import uniandes.isis2304.epsAndes.negocio.EpsAndes;
import uniandes.isis2304.epsAndes.negocio.Gerente;
import uniandes.isis2304.epsAndes.negocio.Ips;
import uniandes.isis2304.epsAndes.negocio.Medico;
import uniandes.isis2304.epsAndes.negocio.Prestan;
import uniandes.isis2304.epsAndes.negocio.Recepcionista;
import uniandes.isis2304.epsAndes.negocio.RecetaMedica;
import uniandes.isis2304.epsAndes.negocio.Rol;
import uniandes.isis2304.epsAndes.negocio.Usuario;
import uniandes.isis2304.epsAndes.negocio.VOAdministrador;

/**
 * Clase principal de la interfaz
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazEpsAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazEpsAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private EpsAndes eps;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazEpsAndesApp( )
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);

		eps = new EpsAndes (tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}

	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "EpsAndes App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "EpsAndes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );


		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}

	/* ****************************************************************
	 * 			CRUD de TipoBebida
	 *****************************************************************/
	public void registrarRol()
	{
		new PanelRegistrarRol(this);
	}


	/**
	 * Adiciona un tipo de bebida con la información dada por el usuario
	 * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese nombre no existía
	 */
	public void adicionarRolesDeUsuario(long id, String nombreTipo )
	{
		try 
		{
			if(id != 0 && nombreTipo != null)
			{
				if(!eps.existeRol(id))
				{
					Rol tb = eps.adicionarRolesDeUsuario(id, nombreTipo);
					if(tb == null)
					{

						throw new Exception ("No se pudo crear un con nombre: " + nombreTipo);

					}
					String resultado = "En registrarRolDeUsuario\n\n";
					resultado += "Rol agregado exitosamente: " + tb;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Ya existe el rol");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarOrganizador()
	{
		new PanelRegistrarOrganizador(this);
	}
	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
	 */
	public void registrarOrganizadorDatos( long id, String email,  String nombre, long cedula, int rol , String tipoId)
	{
		try 
		{
			if(id != 0 && email != null && nombre != null && cedula != 0 && rol != 0 && tipoId != null)
			{

				Usuario tb = eps.adicionarOrganizadorDeCampania(id, email, nombre, cedula, rol, tipoId);
				if(tb == null)
				{

					throw new Exception ("No se pudo crear un organizador con la cedula: " + cedula);

				}
				String resultado = "En registrarOrganizadorDatos\n\n";
				resultado += "Organizador agregado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}


			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarMedico()
	{
		new PanelRegistrarMedico(this);
	}
	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
	 */
	public void registrarMedicoDatos( long id, String email,  String nombre, long cedula, int rol , String tipoId, String especialidad, String registro)
	{
		try 
		{
			if(id != 0 && email != null && nombre != null && cedula != 0 && rol != 0 && tipoId != null && especialidad != null && registro != null)
			{
				if(!eps.existeMedico(id))
				{
					Medico tb = eps.adicionarMedico(id, email, nombre, cedula, rol, tipoId, especialidad, registro);
					if(tb == null)
					{

						throw new Exception ("No se pudo crear un medico con la cedula: " + cedula);

					}
					String resultado = "En registrarMedicoDatos\n\n";
					resultado += "Medico agregado exitosamente: " + tb;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Ya existe el Medico");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}




	public void registrarAdministrador()
	{
		new PanelRegistrarAdministrador(this);
	}
	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
	 */
	public void registrarAdministradorDatos( long id, String email,  String nombre, long cedula, int rol , String tipoId)
	{
		try 
		{
			if(id != 0 && email != null && nombre != null && cedula != 0 && rol != 0 && tipoId != null)
			{
				if(!eps.existeAdministrador(id))
				{
					Usuario tb = eps.adicionarAdministrador(id, email, nombre, cedula, rol, tipoId);
					if(tb == null)
					{

						throw new Exception ("No se pudo crear un el administrador con la cedula: " + cedula);

					}
					String resultado = "En registrarAdministradorDatos\n\n";
					resultado += "Administrador agregado exitosamente: " + tb;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Ya existe el administrador");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarGerente()
	{
		new PanelRegistrarGerente(this);
	}
	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
	 */
	public void registrarGerenteDatos( long id, String email,  String nombre, long cedula, int rol , String tipoId)
	{
		try 
		{
			if(id != 0 && email != null && nombre != null && cedula != 0 && rol != 0 && tipoId != null)
			{
				if(!eps.existeGerente(id))
				{
					Usuario tb = eps.adicionarGerente(id, email, nombre, cedula, rol, tipoId);
					if(tb == null)
					{

						throw new Exception ("No se pudo crear un gerente con la cedula: " + cedula);

					}
					String resultado = "En registrarGerenteDatos\n\n";
					resultado += "Gerente agregado exitosamente: " + tb;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Ya existe el administrador");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarAfiliado()
	{
		new PanelRegistrarAfiliado(this);
	}
	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
	 */
	public void registrarAfiliadoDatos( long id, String email,  String nombre, long cedula, int rol , String tipoId, String fecha)
	{
		try 
		{
			if(id != 0 && email != null && nombre != null && cedula != 0 && rol != 0 && tipoId != null && fecha != null)
			{
				if(!eps.existeAfiliado(id))
				{
					Usuario tb = eps.adicionarAfiliado(id, email, nombre, cedula, rol, tipoId, fecha);
					if(tb == null)
					{

						throw new Exception ("No se pudo crear un afiliado con la cedula: " + cedula);

					}
					String resultado = "En registrarAfiliadoDatos\n\n";
					resultado += "Afiliado agregado exitosamente: " + tb;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Ya existe el afiliado");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	public void registrarRecepcionista()
	{
		new PanelRegistrarRecepcionista(this);
	}
	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
	 */
	public void registrarRecepcionistaDatos( long id, String email,  String nombre, long cedula, int rol , String tipoId, String ips)
	{
		try 
		{
			if(id != 0 && email != null && nombre != null && cedula != 0 && rol != 0 && tipoId != null && ips != null)
			{
				if(!eps.existeRecepcionista(id))
				{
					Recepcionista tb = eps.adicionarRecepcionista(id, email, nombre, cedula, rol, tipoId, ips);
					if(tb == null)
					{

						throw new Exception ("No se pudo crear un recepcionista con la cedula: " + cedula);

					}
					String resultado = "En registrarRecepcionistaDatos\n\n";
					resultado += "Recepcionista agregado exitosamente: " + tb;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Ya existe el recepcionista");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}



	public void registrarIps()
	{
		new PanelRegistrarIps(this);
	}
	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
	 */
	public void registrarIpsDatos( long id,  String nombre, String tipoId, String ubicacion)
	{
		try 
		{
			if(id != 0 &&  nombre != null   && tipoId != null && ubicacion != null)
			{
				if(!eps.existeIps(nombre))
				{
					Ips tb = eps.adicionarIps(id, nombre, tipoId, ubicacion);
					if(tb == null)
					{

						throw new Exception ("No se pudo crear la ips con el nombre: " + nombre);

					}
					String resultado = "En registrarIpsDatos\n\n";
					resultado += "Ips agregada exitosamente: " + tb;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Ya existe la ips");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	public void registrarServicioSalud()
	{
		new PanelRegistrarServicio(this);
	}
	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
	 */
	public void registrarServicioPrestadoDatos( int dia, int horario, String idServicio, String idIps, long capacidad, long capacidadMax, int estado)
	{
		try 
		{
			if(dia > 0 && dia <=7 && idServicio != null && idIps != null && horario> 0 && horario <= 20 && capacidad != 0 && capacidadMax != 0 && capacidad <= capacidadMax && (estado ==0 && estado == 1))
			{

				Prestan tb = eps.adicionarServicioDeSalud( dia, horario, idServicio, idIps, capacidad, capacidadMax, estado);
				if(tb == null)
				{

					throw new Exception ("No se pudo crear servicio para la ips: " + idIps);

				}
				String resultado = "En registrarServicioPrestadoDatos\n\n";
				resultado += "Servicio agregado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);

			}
			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	public void registrarOrden()
	{
		new PanelRegistrarOrden(this);
	}
	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
	 */
	public void registrarOrdenDatos( long id, String  fecha, long idMedico , long idUsuario, String idServicio, String medicamentos)
	{
		try 
		{
			if(id != 0 && idServicio != null && fecha != null && idMedico!= 0 && idUsuario != 0 && idServicio != null && medicamentos != null)
			{

				RecetaMedica tb = eps.adicionarRecetaMedica(id, fecha, idMedico, idUsuario, idServicio, medicamentos);
				if(tb == null)
				{

					throw new Exception ("No se pudo crear la orden medica: " + id);

				}
				String resultado = "En registrarOrdenDatos\n\n";
				resultado += "Orden agregado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);

			}
			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarCita()
	{
		new PanelRegistrarCita(this);
	}
	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
	 */
	public void registrarCitaDatos(  long id, long idReceta, long idUsuario, long idRecepcionista, String idServicio, String estado, String fecha, int horario)
	{
		try 
		{
			if(id != 0 && idServicio != null && estado != null && idReceta!= 0 && idUsuario != 0  && fecha != null && horario > 0 && horario<=20 && idRecepcionista != 0)
			{

				Cita tb = eps.adicionarCita(id, idReceta, idUsuario, idRecepcionista, idServicio, estado, fecha, horario);
				if(tb == null)
				{

					throw new Exception ("No se pudo crear la cita: " + id);

				}
				String resultado = "En registrarCitaDatos\n\n";
				resultado += "Cita agregada exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);



			}
			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarCampania()
	{
		new PanelRegistrarCampania(this);
	}
	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
	 */
	public void registrarCampaniaDatos(String nombre, String fechaInicio, String fechaFin, String idOrganizador)
	{
		try 
		{
			if(nombre != null && fechaFin != null && fechaInicio != null && idOrganizador != null)
			{

				eps.registrarCampania(nombre, fechaInicio, fechaFin, idOrganizador);

				String resultado = "En registrarCampaniaDatos\n\n";
				resultado += "Campania agregada exitosamente: " ;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Los datos estan incompletos");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}



	public void registrarPrestacion(  )
	{
		try 
		{

			String input = JOptionPane.showInputDialog(this, "Identificador de la cita ","Registrar prestacion", JOptionPane.QUESTION_MESSAGE);
			long id = Long.parseLong(input);
			Cita cita = eps.darCitaPorId(id);


			if(cita == null)
			{

				throw new Exception ("La cita con: " + id + " no existe");

			}
			else
			{
				eps.registrarPrestacion(id, cita.getId_recepcionista(), cita.getId_servicio(), cita.getId_usuario());
			}
			String resultado = "En registrarPrestacion\n\n";
			resultado += "Cita prestada exitosamente: " + cita;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);



		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}



	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("parranderos.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}




	/**
	 * Muestra el modelo conceptual de EpsAndes
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Starter Class Diagram.png");
	}

	/**
	 * Muestra el modelo relacional de EpsAndes
	 */
	public void mostrarModeloRelacional ()
	{
		mostrarArchivo ("data/20 Iteracion 2.docx");
	}
	/**
	 * Muestra el documento de la iteracion de EpsAndes
	 */
	public void mostrarDocumentoIteracion ()
	{
		mostrarArchivo ("data/20 Iteracion 2.docx");
	}


	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: EpsAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Andres Benitez y Miguel Ramos\n";
		resultado += " * Noviembre 2019\n";
		resultado += " * \n";
		resultado += " * Revisado por: \n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}


	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
	/**
	 * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una línea por cada tipo de bebida
	 * @param lista - La lista con los tipos de bebida
	 * @return La cadena con una líea para cada tipo de bebida recibido
	 */
	private String listarTiposBebida(List<VOAdministrador> lista) 
	{
		String resp = "Los tipos de bebida existentes son:\n";
		int i = 1;
		for (VOAdministrador tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazEpsAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		BasicConfigurator.configure();
		try
		{

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazEpsAndesApp interfaz = new InterfazEpsAndesApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
