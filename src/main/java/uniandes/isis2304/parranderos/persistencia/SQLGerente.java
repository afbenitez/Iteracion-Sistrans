package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLGerente {

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
	public SQLGerente (PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarGerente (PersistenceManager pm, long id, String  fecha, String email, String nombre, long cedula, int rol, String tipoId) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario () + "( id, email, nombre, numero_id, rol, tipo_identificacion) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, email, nombre, cedula, rol, tipoId);
        q.executeUnique();
        
        Query k = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaGerente() + "( id, numero_id) values (?, ?)");
        k.setParameters(id, cedula);
        return (long) k.executeUnique();
	}
}
