package uniandes.isis2304.epsAndes.interfazDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.epsAndes.interfazApp.InterfazEpsAndesApp;
import uniandes.isis2304.epsAndes.interfazApp.PanelDatos;
import uniandes.isis2304.epsAndes.negocio.AdministradorDatos;
import uniandes.isis2304.epsAndes.negocio.Afiliado;
import uniandes.isis2304.epsAndes.negocio.Campania;
import uniandes.isis2304.epsAndes.negocio.EpsAndes;
import uniandes.isis2304.epsAndes.negocio.Ips;
import uniandes.isis2304.epsAndes.negocio.Recepcionista;
import uniandes.isis2304.epsAndes.negocio.Rol;
import uniandes.isis2304.epsAndes.negocio.Usuario;
import uniandes.isis2304.epsAndes.negocio.VOAdministrador;

public class InterfazEpsAndesDemo extends JFrame implements ActionListener{

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
	public InterfazEpsAndesDemo( )
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
	
	
	
	public void demoIPS( )
	{
		try 
		{
			String nombre = "Colmedicas";
			String ubicacion = "Cll 83a #75-50";
			String nombreEps = "EPSAndes";
			boolean errorIps = false;
						
			Ips ips = eps.adicionarIps(1, nombre, "CENTRO DIAGNOSTICO", ubicacion);
			
			if( ips == null)
			{
				errorIps = true; 
			}

			String resultado = "Demo de creación de IPS\n\n";
			resultado += "\n\n***** Generando datos de prueba ***** \n";
			if (errorIps)
			{
				resultado += "* Exception creando IPS !!\n";
				resultado += "* Es probable que la IPS ya existiera y hay restricción de UNICIDAD sobre el nombre de IPS\n";
				resultado += "* Revise el log de EPSAndes para más detalles\n";
			}
			resultado += "Adicionado la IPS con nombre: " + nombre + "\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	
	public void demoAdministrador( )
	{
		try 
		{
			boolean errorAdmin = false;
			
			String email = "asd@asd.com";
			String nombreAdmin = "Benito";
			long cedula = 1;
			int rol = 4;
			String tipoId = "CC";
			
			Usuario administrador = eps.adicionarAdministrador(1, email, nombreAdmin, cedula, rol, tipoId);
			
			if( administrador == null)
			{
				errorAdmin = true; 
			}

			String resultado = "Demo de creación de Administrador\n\n";
			resultado += "\n\n***** Generando datos de prueba ***** \n";
			if (errorAdmin)
			{
				resultado += "* Exception creando Administrador !!\n";
				resultado += "* Es probable que la Administrador ya existiera y hay restricción de UNICIDAD sobre el nombre de IPS\n";
				resultado += "* Revise el log de EPSAndes para más detalles\n";
			}
			resultado += "Adicionado el Administrador con nombre: " + nombreAdmin + "\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	public void demoAfiliado( )
	{
		try 
		{
			boolean errorAfiliado = false;
			
			String email = "asd@asd.com";
			String nombreAfiliado = "Benito 2";
			long cedula = 12;
			int rol = 2;
			String tipoId = "CC";
			
			Usuario administrador = eps.adicionarAfiliado(2, email, nombreAfiliado, cedula, rol, tipoId, "12/12/2001");
			
			if( administrador == null)
			{
				errorAfiliado = true; 
			}

			String resultado = "Demo de creación de Afiliado\n\n";
			resultado += "\n\n***** Generando datos de prueba ***** \n";
			if (errorAfiliado)
			{
				resultado += "* Exception creando Afiliado !!\n";
				resultado += "* Es probable que la Afiliado ya existiera y hay restricción de UNICIDAD sobre el nombre de IPS\n";
				resultado += "* Revise el log de EPSAndes para más detalles\n";
			}
			resultado += "Adicionado el Afiliado con nombre: " + nombreAfiliado + "\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	public void demoMedico() 
	{
		try 
		{
			boolean errorMedico = false;
			
			String email = "asd@asd1.com";
			String nombreMedico = "Benito 3";
			long cedula = 123;
			int rol = 1;
			String tipoId = "CC";
			
			Usuario administrador = eps.adicionarMedico(1, email, nombreMedico, cedula, rol, tipoId, "GERONTOLOGIA", "1234");
			
			if( administrador == null)
			{
				errorMedico = true; 
			}

			String resultado = "Demo de creación de Medico\n\n";
			resultado += "\n\n***** Generando datos de prueba ***** \n";
			if (errorMedico)
			{
				resultado += "* Exception creando Medico !!\n";
				resultado += "* Es probable que la Medico ya existiera y hay restricción de UNICIDAD sobre el nombre de IPS\n";
				resultado += "* Revise el log de EPSAndes para más detalles\n";
			}
			resultado += "Adicionado el Medico con nombre: " + nombreMedico + "\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	public void demoGerente() 
	{
		try 
		{
			boolean errorGerente = false;
			
			String email = "asd@qwe.com";
			String nombreGerente = "Benito 4";
			long cedula = 1234;
			int rol = 3;
			String tipoId = "CC";
			
			Usuario gerente = eps.adicionarGerente(1, email, nombreGerente, cedula, rol, tipoId);
			
			if( gerente == null)
			{
				errorGerente = true; 
			}

			String resultado = "Demo de creación de Gerente\n\n";
			resultado += "\n\n***** Generando datos de prueba ***** \n";
			if (errorGerente)
			{
				resultado += "* Exception creando Gerente !!\n";
				resultado += "* Es probable que la Gerente ya existiera y hay restricción de UNICIDAD sobre el nombre de IPS\n";
				resultado += "* Revise el log de EPSAndes para más detalles\n";
			}
			resultado += "Adicionado el Gerente con nombre: " + nombreGerente + "\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	
	public void demoRecepcionista() 
	{
		try 
		{
			boolean errorRecepcionista = false;
			
			String email = "asd@qqwe.com";
			String nombreRecepcionista = "Benito 4";
			long cedula = 12345;
			int rol = 5;
			String tipoId = "CC";
			
			Usuario recepcionista = eps.adicionarRecepcionista(1, email, nombreRecepcionista, cedula, rol, tipoId, "Youfeed");
			
			if( recepcionista == null)
			{
				errorRecepcionista = true; 
			}

			String resultado = "Demo de creación de Recepcionista\n\n";
			resultado += "\n\n***** Generando datos de prueba ***** \n";
			if (errorRecepcionista)
			{
				resultado += "* Exception creando Recepcionista !!\n";
				resultado += "* Es probable que la Recepcionista ya existiera y hay restricción de UNICIDAD sobre el nombre de IPS\n";
				resultado += "* Revise el log de EPSAndes para más detalles\n";
			}
			resultado += "Adicionado el Recepcionista con nombre: " + nombreRecepcionista + "\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	
	
	public void demoCampana( )
	{
		try 
		{

			eps.adicionarOrganizadorDeCampania(1, "dizkonect@asda.com", "Sandra Parra", 41929765, 6, "CC");
			
			String nombre_ips = "Sanitas";
			eps.adicionarIps(2, nombre_ips, "CENTRO MEDICO", "Suba Rincon");
			
			eps.adicionarServicioDeSalud(1, 2, "Examen Sangre", nombre_ips, 1, 50, 1);
			eps.adicionarServicioDeSalud(1, 3, "Examen Orina", nombre_ips, 4, 50, 1);
			eps.adicionarServicioDeSalud(2, 5, "Examen Piel", nombre_ips, 10, 50, 1);
			eps.adicionarServicioDeSalud(5, 10, "Examen Renal", nombre_ips, 5, 50, 1);
			
			Afiliado afil = eps.adicionarAfiliado(1, "ASD@AD.COM", "Benito Martinez", 41929372, 2, "CC", "12/13/2001");	
			
			long id = afil.getId();
			String nombreCamp = "Colombia Humana";
			String fecha_inicial = "12/12/2018";
			String fecha_fin = "12/20/2018";
			String idOrganizador = "41929372";
			
			Campania campania = eps.registrarCampania(nombreCamp, fecha_inicial, fecha_fin, idOrganizador);
			eps.registrarServicioCamp("Examen Sangre", nombreCamp, fecha_inicial, fecha_fin, 1, 10);
			eps.registrarServicioCamp("Examen Orina", nombreCamp, fecha_inicial, fecha_fin, 1, 20);
			eps.registrarServicioCamp("Examen Piel", nombreCamp, fecha_inicial, fecha_fin, 1, 30);
			eps.registrarServicioCamp("Examen Renal", nombreCamp, fecha_inicial, fecha_fin, 1, 40);
			

			boolean errorcampania=false;
			
			if( campania == null)
			{
				errorcampania = true; 
			}

			
			String resultado = "Demo de creación Campaña\n\n";
			resultado += "\n\n***** Generando datos de prueba ***** \n";
			if (errorcampania)
			{
				resultado += "* Exception creando campania !!\n";
				resultado += "* Es probable que la campania ya existiera y hay restricción de UNICIDAD sobre el nombre de campania\n";
				resultado += "* Revise el log de EPSAndes para más detalles\n";
			}
			resultado += "Adicionado la Campania con nombre: " + nombreCamp + "\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String generarMensajeError(Exception e) 
	{
		String resultado = "** Error en la ejecuciÃƒÂ³n\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y EPSAndes.log para mÃƒÂ¡s detalles";
		return resultado;
	}
	
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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
