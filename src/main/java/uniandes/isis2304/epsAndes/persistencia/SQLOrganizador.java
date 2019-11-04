package uniandes.isis2304.epsAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOrganizador {

	/* **********************
	 * 			Constantes
	 ***********************/
	/** Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos Se renombra acÃ¡ para facilitar la escritura de las sentencias. */
	private final static String SQL = PersistenciaEps.SQL;

	/* **********************
	 * 			Atributos
	 ***********************/
	/** El manejador de persistencia general de la aplicaciÃ³n. */
	private PersistenciaEps pp;

	/* **********************
	 * 			MÃ©todos
	 ***********************/
	/**
	 * Constructor.
	 *
	 * @param pp - El Manejador de persistencia de la aplicaciÃ³n
	 */
	public SQLOrganizador (PersistenciaEps pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un GERENTE a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param id_EPS el id de la EPS
	 * @param identificacion la identificacion
	 * @return EL nÃºmero de tuplas insertadas
	 */
	public long adicionarOrganizador (PersistenceManager pm, long id, String email, String nombre, long cedula, int rol, String tipoId) 
	{
	    Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario() + " ( id, email, nombre, numero_id, rol, tipo_identificacion) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, email, nombre, cedula, rol, tipoId);
        
        q.executeUnique();
		
		Query k = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOrganizador() + " ( numero_id) values (?)");
		k.setParameters(id);
		return (long) k.executeUnique();
	}
}
