package uniandes.isis2304.parranderos.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
	
	private SQLIps sqlIps;
	
	private SQLMedico sqlMedico;
	
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
		tablas.add ("IPS");
		tablas.add ("MEDICO");
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
		sqlIps = new SQLIps(this);
		sqlMedico = new SQLMedico(this);		
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
	
	public String darTablaIps ()
	{
		return tablas.get (6);
	}

	public String darTablaMedico ()
	{
		return tablas.get (7);
	}
	
	public String darTablaRecepcionista ()
	{
		return tablas.get (8);
	}

	public String darTablaRecetaMedica ()
	{
		return tablas.get (9);
	}

	public String darTablaRol ()
	{
		return tablas.get (10);
	}
	
	public String darTablaServicioDeSalud ()
	{
		return tablas.get (11);
	}
	
	public String darTablaServicioTerapia ()
	{
		return tablas.get (12);
	}

	public String darTablaTrabajan ()
	{
		return tablas.get (13);
	}

	public String darTablaUsuario ()
	{
		return tablas.get (14);
	}
	
	
	
}
