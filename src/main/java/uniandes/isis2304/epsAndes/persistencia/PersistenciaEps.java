package uniandes.isis2304.epsAndes.persistencia;

import java.util.LinkedList; 
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.*;
import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.epsAndes.negocio.AdministradorDatos;
import uniandes.isis2304.epsAndes.negocio.Afiliado;
import uniandes.isis2304.epsAndes.negocio.Campania;
import uniandes.isis2304.epsAndes.negocio.Cita;
import uniandes.isis2304.epsAndes.negocio.Gerente;
import uniandes.isis2304.epsAndes.negocio.Ips;
import uniandes.isis2304.epsAndes.negocio.Medico;
import uniandes.isis2304.epsAndes.negocio.OrganizadorDeCampania;
import uniandes.isis2304.epsAndes.negocio.Prestan;
import uniandes.isis2304.epsAndes.negocio.Recepcionista;
import uniandes.isis2304.epsAndes.negocio.RecetaMedica;
import uniandes.isis2304.epsAndes.negocio.Rol;
import uniandes.isis2304.epsAndes.negocio.ServicioDeSalud;
import uniandes.isis2304.epsAndes.negocio.Usuario;

public class PersistenciaEps {

	private static Logger log = Logger.getLogger(PersistenciaEps.class.getName());

	public final static String SQL = "javax.jdo.query.SQL";

	private static PersistenciaEps instance;
	
	private PersistenceManagerFactory pmf;
	
	private List <String> tablas;
	
	private SQLUtil sqlUtil;
	
	private SQLAdministradorDatos sqlAdministradorDatos;
	
	private SQLAfiliado sqlAfiliado;
	
	private SQLCita sqlCita;
	
	private SQLEps sqlEps;
	
	private SQLGerente sqlGerente;
	
	private SQLHorario sqlHorario;
	
	private SQLIps sqlIps;
	
	private SQLMedico sqlMedico;
	
	private SQLPrestan sqlPrestan;
	
	private SQLRecepcionista sqlRecepcionista;
	
	private SQLRecetaMedica sqlRecetaMedica;
	
	private SQLRol sqlRol;

	private SQLServicioDeSalud sqlServicioDeSalud;
	
	private SQLServicioTerapia sqlServicioTerapia;
	
	private SQLTrabajan sqlTrabajan;
	
	private SQLUsuario sqlUsuario;
	
	private SQLCampania sqlCampania;
	
	private SQLOrganizador sqlOrganizador;
	
	
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaEps ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("EpsAndes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("EpsAndes_sequence");
		tablas.add ("ADMINISTRADORDATOS");
		tablas.add ("AFILIADO");
		tablas.add ("CITA");
		tablas.add ("EPS");
		tablas.add ("GERENTE");
		tablas.add ("HORARIO");
		tablas.add ("IPS");
		tablas.add ("MEDICO");
		tablas.add ("PRESTAN");
		tablas.add ("RECEPCIONISTA");
		tablas.add ("RECETAMEDICA");
		tablas.add ("ROL");
		tablas.add ("SERVICIODESALUD");
		tablas.add ("SERVICIOTERAPIA");
		tablas.add ("TRABAJAN");
		tablas.add ("USUARIO");
		tablas.add ("CAMPANIA");
		tablas.add ("ORGANIZADORDECAMPANIA");
		tablas.add ("RESERVADECAMPANIA");
	
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaEps (JsonObject tableConfig)
	{
		crearClasesSQL ();
	
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaEps getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaEps ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaEps getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaEps (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlAdministradorDatos = new SQLAdministradorDatos(this);
		sqlAfiliado = new SQLAfiliado(this);
		sqlCita = new SQLCita(this);
		sqlEps = new SQLEps(this);
		sqlGerente = new SQLGerente(this);
		sqlHorario = new SQLHorario(this);
		sqlIps = new SQLIps(this);
		sqlMedico = new SQLMedico(this);	
		sqlPrestan = new SQLPrestan(this);
		sqlRecepcionista = new SQLRecepcionista(this);
		sqlRecetaMedica = new SQLRecetaMedica(this);
		sqlRol = new SQLRol(this);
		sqlServicioDeSalud = new SQLServicioDeSalud(this);
		sqlServicioTerapia = new SQLServicioTerapia(this);
		sqlTrabajan = new SQLTrabajan(this);
		sqlUsuario = new SQLUsuario(this);
		sqlCampania = new SQLCampania(this);
		sqlOrganizador = new SQLOrganizador(this);
		
		sqlUtil = new SQLUtil(this);
	}
	
	
	public String darSeqEpsAndes ()
	{
		return tablas.get (0);
	}

	public String darTablaAdministradorDatos ()
	{
		return tablas.get (1);
	}

	public String darTablaAfiliado ()
	{
		return tablas.get (2);
	}

	public String darTablaCita ()
	{
		return tablas.get (3);
	}

	public String darTablaEps ()
	{
		return tablas.get (4);
	}
	
	public String darTablaGerente ()
	{
		return tablas.get (5);
	}
	
	public String darTablaHorario()
	{
		return tablas.get (6);
	}
	
	public String darTablaIps ()
	{
		return tablas.get (7);
	}

	public String darTablaMedico ()
	{
		return tablas.get (8);
	}
	
	public String darTablaPrestan()
	{
		return tablas.get (9);
	}
	
	public String darTablaRecepcionista ()
	{
		return tablas.get (10);
	}

	public String darTablaRecetaMedica ()
	{
		return tablas.get (11);
	}

	public String darTablaRol ()
	{
		return tablas.get (12);
	}
	
	public String darTablaServicioDeSalud ()
	{
		return tablas.get (13);
	}
	
	public String darTablaServicioTerapia ()
	{
		return tablas.get (14);
	}

	public String darTablaTrabajan ()
	{
		return tablas.get (15);
	}

	public String darTablaUsuario ()
	{
		return tablas.get (16);
	}
	
	public String darTablaCampania ()
	{
		return tablas.get (17);
	}
	public String darTablaOrganizador ()
	{
		return tablas.get (18);
	}
	public String darTablaReservaCampania ()
	{
		return tablas.get (19);
	}
	
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
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
	

	public Rol adicionarRolesDeUsuario(long id , String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlRol.adicionarRol(pm, id, nombre);
            tx.commit();
            
            log.trace ("Inserción de rol: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Rol (id, nombre);
        }
        catch (Exception e)
        {
        	//   e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Usuario adicionarAdministrador(long id , String email ,String nombre, long  cedula, int rol, String tipoId)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlAdministradorDatos.adicionarAdministrador(pm, id, email, nombre, cedula, rol, tipoId);
            tx.commit();
            
            log.trace ("Inserción de administrador: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario(id, email, nombre, cedula, rol, tipoId);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Usuario adicionarGerente(long id , String email ,String nombre, long  cedula, int rol, String tipoId)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlGerente.adicionarGerente(pm, id, email, nombre, cedula, rol, tipoId);
            tx.commit();
            
            log.trace ("Inserción de gerente: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario(id, email, nombre, cedula, rol, tipoId);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Recepcionista adicionarRecepcionista(long id , String email ,String nombre, long  cedula, int rol, String tipoId, String ips)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlRecepcionista.adicionarRecepcionista(pm, id, email, nombre, cedula, rol, tipoId, ips);
            tx.commit();
            
            log.trace ("Inserción de recepcionista: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Recepcionista(id, email, nombre, cedula, rol, tipoId, ips);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public AdministradorDatos darAdministradorPorId(long id)
	{
		return (AdministradorDatos) sqlAdministradorDatos.darAdministradorPorId(pmf.getPersistenceManager(), id);
	}
	
	public Rol darRolPorId(long id)
	{
		return (Rol) sqlRol.darRolPorId(pmf.getPersistenceManager(), id);
	}
	
	public Afiliado darAfiliadoPorId(long id)
	{
		return (Afiliado) sqlAfiliado.darAfiliadoPorId(pmf.getPersistenceManager(), id);
	}
	
	public Gerente darGerentePorId(long id)
	{
		return (Gerente) sqlGerente.darGerentePorId(pmf.getPersistenceManager(), id);
	}

	public Recepcionista darRecepcionistaPorId(long id)
	{
		return (Recepcionista) sqlRecepcionista.darRecepcionistaPorId(pmf.getPersistenceManager(), id);
	}
	
	public Ips darIpsPorNombre(String nombre)
	{
		return (Ips) sqlIps.darIpsPorId(pmf.getPersistenceManager(), nombre);
	}
	
	public Medico darMedicoPorId(long id)
	{
		return (Medico) sqlMedico.darMedicoPorId(pmf.getPersistenceManager(), id);
	}
	
	public Cita darCitaPorId(long id)
	{
		return (Cita) sqlCita.darCitaPorId(pmf.getPersistenceManager(), id);
	}
	
	
	public Ips adicionarIps(long id , String nombre, String tipo, String ubicacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlIps.adicionarIps(pm, id, nombre, tipo, ubicacion);
            tx.commit();
            
            log.trace ("Inserción de ips: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Ips(id, nombre, tipo, ubicacion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Medico adicionarMedico(long id , String email ,String nombre, long  cedula, int rol, String tipoId, String especialidad, String numeroRegistro)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlMedico.adicionarMedico(pm, id, email, nombre, cedula, rol, tipoId, especialidad, numeroRegistro);
            tx.commit();
            
            log.trace ("Inserción de medico: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Medico(id, email, nombre, cedula, rol, tipoId, especialidad, numeroRegistro);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Afiliado adicionarAfiliado(long id , String email ,String nombre, long  cedula, int rol, String tipoId, String fecha )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlAfiliado.adicionarAfiliado(pm, id, fecha, email, nombre, cedula, rol, tipoId);
            tx.commit();
            
            log.trace ("Inserción de afiliado: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Afiliado(id, email, nombre, cedula, rol, tipoId, fecha);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Prestan adicionarServicioDeSalud( int dia, int horario, String idServicio, String idIps, long capacidad, long capacidadMax ,int estado )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlPrestan.adicionarPrestan(pm, dia, horario, idServicio, idIps, capacidad, capacidadMax, estado);
            tx.commit();
            
            log.trace ("Inserción de adicionar servicio: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Prestan(dia, horario, idServicio, idIps, capacidad, capacidadMax, estado);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public RecetaMedica adicionarRecetaMedica(long id, String  fecha, long idMedico , long idUsuario, String idServicio, String medicamentos) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlRecetaMedica.adicionarRecetaMedica(pm, id, fecha, idMedico, idUsuario, idServicio, medicamentos);
            tx.commit();
            
            log.trace ("Inserción de adicionarReceta: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new RecetaMedica(id, fecha, idMedico, idUsuario, idServicio, medicamentos);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Cita adicionarCita(long id, long idReceta, long idUsuario, long idRecepcionista ,String  idServicio, String estado, String fecha, int horario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlCita.adicionarCita(pm, id, idReceta, idUsuario, idRecepcionista, idServicio, estado, fecha, horario);
            tx.commit();
            
            log.trace ("Inserción de adicionarReceta: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Cita(id, idReceta, idUsuario, idRecepcionista, idServicio, estado, fecha, horario);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	
	public void registrarPrestacion(long id,  long idRecepcionista, String idServicio, long idPaciente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlCita.registrarPrestacion(pm, id, idRecepcionista, idServicio, idPaciente);
            tx.commit();
            
            log.trace ("Inserción registro de prestacion: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
           
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public OrganizadorDeCampania adicionarOrganizador(long id , String email ,String nombre, long  cedula, int rol, String tipoId )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlOrganizador.adicionarOrganizador(pm, id, email, nombre, cedula, rol, tipoId);
            tx.commit();
            
            log.trace ("Inserción de afiliado: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new OrganizadorDeCampania(id, email, nombre, cedula, rol, tipoId);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	//RF10
	public Campania registrarCampania( String nombre, String fechaFin, String fechaInicio, String idOrganizador)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin(); 
			long id=nextval();
			long tuplasInsertadas = sqlCampania.registrarCampania(pm, nombre, fechaFin, fechaInicio, idOrganizador);
			tx.commit();

			log.trace ("Insercion campaña: " +id + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Campania(nombre, fechaInicio, fechaFin, idOrganizador);
		}	
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	

	public void registrarServicioCampania( String idServicio, String idCampania,String fechaInicio,String fechaFin, int capacidad, int capacidadMax)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long tuplasInsertadas = sqlCampania.registrarServicioCampania(pm, idServicio, idCampania, fechaInicio, fechaFin, capacidad, capacidadMax);
			tx.commit();

			log.trace ("Insercion reservaCampania: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	//RF11
	public void cancelarServicioCampania(String campania, String servicio)
	{

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
		Usuario x = sqlUsuario.darUsuarioPorId(pmf.getPersistenceManager(), Long.parseLong(campania));
		List<Object[]> y = sqlCampania.darCampania(pmf.getPersistenceManager(), servicio, campania);
		List<Object[]> lista = sqlPrestan.darInfoServicioEnRango(pmf.getPersistenceManager(), servicio, (String)y.get(0)[3], (String)y.get(0)[4]);
		int capacidad = ((BigDecimal)y.get(0)[5]).intValue();
		for (int i = 0;i<capacidad;i++)
		{
			Object[] objects=lista.get(i);
			int insertadas=0;
			int capacidadP=((BigDecimal)objects[5]).intValue();
			int capacidadM=((BigDecimal)objects[7]).intValue();

			if(capacidad+capacidadP<=capacidadM){
				insertadas=capacidad+capacidadP;
				tx.begin();
				sqlPrestan.actualizarCapacidad(pmf.getPersistenceManager(), capacidad+capacidadP,(String)objects[2],servicio);
				tx.commit();
			}
			else{
				insertadas=capacidadM-capacidadP;
				tx.begin();
				sqlPrestan.actualizarCapacidad(pmf.getPersistenceManager(),capacidadP+insertadas,(String)objects[2],servicio);
				tx.commit();
			}
			capacidad-=insertadas;
		}				
		tx.begin();
	
		sqlCampania.eliminarServicioCampania(pmf.getPersistenceManager(), servicio, campania);
		tx.commit();
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	
	
	//RF12
	public String deshabilitarServicios(String fechaIni,String fechaFin,String ips,String idServicio)
	{
		String mensaje="";
		sqlPrestan.actualizarHabilitacion(pmf.getPersistenceManager(), fechaIni, fechaFin, ips, idServicio, 1);
		List<Object[]> lista1 = sqlCita.sacarCitasPorIPSServicio(pmf.getPersistenceManager(), ips, idServicio, fechaIni, fechaFin);
		List<Prestan>lista2 = sqlPrestan.darInfoServicioEnRango(pmf.getPersistenceManager(), idServicio, fechaIni, fechaFin);
		long capacidadServicios=sqlPrestan.darCapacidadServicioEnRango(pmf.getPersistenceManager(), idServicio, fechaIni, fechaFin);

		int citasR=lista1.size()-1;
		for (int j = 0; j < lista2.size() && citasR >= 0; j++) 
		{
			Prestan prestan = lista2.get(j);
			int capacidadR = (int) prestan.getCapacidad();
			for(int i = 0; i < capacidadR; i++)
			{
				Object[] objects = lista1.get(citasR);
				//RegistrarCita
				adicionarCita(((BigDecimal)objects[0]).longValue(), ((BigDecimal)objects[1]).longValue(), ((BigDecimal)objects[2]).longValue(), ((BigDecimal)objects[3]).longValue(), (String)objects[4], (String)objects[5], (String)objects[6], ((BigDecimal)objects[7]).IntValue());
				//BorrarCita
				sqlCita.eliminarCitaIPS(pmf.getPersistenceManager(), idServicio, (String)objects[3], ips);
				--citasR;
			}
		}

		if(lista1.size()>capacidadServicios )
		{
			mensaje="No se pudieron reubicar las siguientes citas \n";
			for(int i=citasR;i>=0;i--)
			{
				Object[] objects=lista1.get(citasR);
				mensaje+="Orden: "+((BigDecimal)objects[0]).intValue()+" Fecha: "+(String)objects[5]+" Afiliado: "+((BigDecimal)objects[2]).longValue()+"\n";
			}
		}
		else
			mensaje="Se pudieron reubicar todas las citas";

		return mensaje;
	}
	
	//RF13
	public void habilitarServicios(String fechaIni,String fechaFin,String ips,String idServicio)
	{
		sqlPrestan.actualizarHabilitacion(pmf.getPersistenceManager(), fechaIni, fechaFin, ips, idServicio, 0);
	}
	
	
	
	//RCF6
	public List<Object[]> analizarOperacionSemanaActividadAsc( String servicio,String unidad)
	{
		return sqlPrestan.analizarOperacionSemanaActividad(pmf.getPersistenceManager(), servicio, unidad, "ASC");
	}

	public List<Object[]> analizarOperacionSemanaDemanaDesc( String servicio,String unidad)
	{
		return sqlPrestan.analizarOperacionSemanaDemanda(pmf.getPersistenceManager(), servicio, unidad, "DESC");
	}

	
	//RCF7
	public String darExigentes()
	{
		List<Object[]> exigentes=sqlCita.darExigentes(pmf.getPersistenceManager());
		String mensaje="";
		if(exigentes.size()<=0)
			return "No se encontraron exigentes";
		else {
			mensaje="Se encontraron exigentes:\n";
			for (Object[] objects : exigentes) {
				mensaje+="Cédula: "+(String)objects[0]+" tipos diferentes: "+((BigDecimal)objects[1]).intValue()+" servicios: "+((BigDecimal)objects[2]).intValue()+"\n";
			}
		}
		return mensaje;
	}
	
	
	//RCF8
	public String darNoMuyDemandados()
	{
		String mensaje="";
		List<String> noMuy=sqlPrestan.darNoMuyDemandados(pmf.getPersistenceManager());
		if(noMuy.size()<=0)
		{
			return "No se encontraron no muy demandados";
		}
		else {
			mensaje="Se encontraron servicios no muy demandados: \n";
			for (String objects : noMuy) {
				mensaje+="Nombre: "+ objects+"\n";
			}
		}
		return mensaje;
	}
	
	
	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE .
	 * @return Un arreglo con el nÃºmero de tuplas borradas de cada tabla.
	 */
	public long[] limpiarEPSAndes()
	{
		return sqlUtil.limpiarEpsAndes(pmf.getPersistenceManager());
	}
}
