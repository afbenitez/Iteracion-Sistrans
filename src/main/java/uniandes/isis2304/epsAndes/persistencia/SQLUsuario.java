package uniandes.isis2304.epsAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.AdministradorDatos;
import uniandes.isis2304.epsAndes.negocio.Usuario;

public class SQLUsuario {

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
	public SQLUsuario (PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarUsuario (PersistenceManager pm, long id, String email, String nombre, long cedula, int rol, String tipoId) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario () + " ( id, email, nombre, numero_id, rol, tipo_identificacion) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, email, nombre, cedula, rol, tipoId);
        
        return (long) q.executeUnique();
	}
	public Usuario darUsuarioPorId (PersistenceManager pm, long cedula) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() + " WHERE numero_id = ?");
		q.setResultClass(Usuario.class);
		q.setParameters(cedula);
		return (Usuario) q.executeUnique();
	}
}
