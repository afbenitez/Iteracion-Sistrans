package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLIps {

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
	public SQLIps (PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarIps (PersistenceManager pm, long id, String  nombre, String tipoIps, String ubicacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaIps() + "( id, nombre_ips, tipo_ips, ubicacion) values (?, ?, ?, ?)");
        q.setParameters(id, nombre, tipoIps, ubicacion);
        
        return (long) q.executeUnique();
	}
}
