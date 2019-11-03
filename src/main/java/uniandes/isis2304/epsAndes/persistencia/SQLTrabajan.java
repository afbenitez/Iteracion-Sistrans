package uniandes.isis2304.epsAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLTrabajan 
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
	public SQLTrabajan(PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarTrabajan(PersistenceManager pm, String nombre, long idMedico) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTrabajan() + " ( nombre_ips, id_medico) values (?, ?)");
        q.setParameters (nombre, idMedico);
        
        return (long) q.executeUnique();
	}
}
