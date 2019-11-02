
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

package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.AdministradorDatos;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLAdministradorDatos 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaEps.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaEps pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLAdministradorDatos (PersistenciaEps pp)
	{
		this.pp = pp;

	}

	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del Adminstrador
	 * @param email - El email del Adminstrador
	 * @param nombre - El nombre del Adminstrador
	 * @param cedula - La cedula del Adminstrador
	 * @param rol - El rol de Adminstrador
	 * @param tipoId - El tipo de identificacion
	 * @return El número de tuplas insertadas
	 */
	public long adicionarAdministrador (PersistenceManager pm, long id, String email, String nombre, long cedula, int rol, String tipoId) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario() + " ( id, email, nombre, numero_id, rol, tipo_identificacion) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, email, nombre, cedula, rol, tipoId);
        
        q.executeUnique();
        
        Query k = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAdministradorDatos() + " ( id, numero_id) values (?, ?, )");
        k.setParameters(id, cedula);
        return (long) k.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de EpsAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param cedula - El identificador del ADMINISTRADORDATOS
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarAdministradorPorId (PersistenceManager pm, long cedula)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAdministradorDatos () + " WHERE numero_id = ?");
        q.setParameters(cedula);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de EpsAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param cedula - El identificador del ADMINISTRADORDATOS
	 * @return El objeto ADMINISTRADORDATOS que tiene el identificador dado
	 */
	public AdministradorDatos darAdministradorPorId (PersistenceManager pm, long cedula) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministradorDatos() + " WHERE numero_id = ?");
		q.setResultClass(AdministradorDatos.class);
		q.setParameters(cedula);
		return (AdministradorDatos) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de EpsAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ADMINISTRADORDATOS
	 */
	public List<AdministradorDatos> darAdministradores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministradorDatos());
		q.setResultClass(AdministradorDatos.class);
		return (List<AdministradorDatos>) q.executeList();
	}

	
	/**
	 * @param idAdministradorDatos - El identificador del Administrador
	 * @param nombre - El nombre del administrador
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del bar
	 * @return El número de tuplas insertadas
	 */
	public long adicionarBar (PersistenceManager pm, long idBar, String nombre, String ciudad, String presupuesto, int sedes) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAdministradorDatos () + "(id, nombre, ciudad, presupuesto, cantsedes) values (?, ?, ?, ?, ?)");
        q.setParameters(idBar, nombre, ciudad, presupuesto, sedes);
        return (long) q.executeUnique();
	}

}