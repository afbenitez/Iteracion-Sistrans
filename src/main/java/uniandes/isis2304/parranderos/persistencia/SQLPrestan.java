package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPrestan {
	
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
	public SQLPrestan (PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarPrestan (PersistenceManager pm, int dia, int horario, String idServicio, String idIps, long capacidad, long capacidadMax, int estado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPrestan() + " ( dia, horario, id_servicio, id_ips, capacidad, capacidad_max,estado) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(dia, horario, idServicio, idIps, capacidad, capacidadMax ,estado);
        
        return (long) q.executeUnique();
	}
}
