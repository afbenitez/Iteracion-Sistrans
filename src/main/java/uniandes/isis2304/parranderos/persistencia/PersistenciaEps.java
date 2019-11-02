package uniandes.isis2304.parranderos.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Afiliado;
import uniandes.isis2304.parranderos.negocio.Ips;
import uniandes.isis2304.parranderos.negocio.Medico;
import uniandes.isis2304.parranderos.negocio.RecetaMedica;
import uniandes.isis2304.parranderos.negocio.Rol;
import uniandes.isis2304.parranderos.negocio.ServicioDeSalud;
import uniandes.isis2304.parranderos.negocio.Usuario;

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
		return tablas.get (13);
	}

	public String darTablaTrabajan ()
	{
		return tablas.get (14);
	}

	public String darTablaUsuario ()
	{
		return tablas.get (15);
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
            
            return new Rol (nombre, id);
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
	
	public Usuario adicionarUsuario(long id , String email ,String nombre, long  cedula, int rol, String tipoId)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, id, email, nombre, cedula, rol, tipoId);
            tx.commit();
            
            log.trace ("Inserción de usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	
	public Prestan adicionarServicioDeSalud(long id , int dia, int horario, String idServicio, String idIps, long capacidad, long capacidadMax ,int estado )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlPrestan.adicionarPrestan(pm, dia, horario, idServicio, idIps, capacidad, capacidadMax, estado);
            tx.commit();
            
            log.trace ("Inserción de servicio de la ips: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Prestan(dia, horario, idServicio, idIps, capacidad, estado);
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
            
            log.trace ("Inserción de servicio de la ips: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new RecetaMedica(idUsuario, medicamentos, id_afiliado, id_medico, idServicio);
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
	
	
}
