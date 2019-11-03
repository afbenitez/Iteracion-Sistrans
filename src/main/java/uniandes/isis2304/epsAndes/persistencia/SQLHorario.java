package uniandes.isis2304.epsAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLHorario 
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
	public SQLHorario (PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarHorario (PersistenceManager pm, long id, String franja) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHorario() + " ( id, franja) values (?, ?)");
        q.setParameters(id, franja);
        
        return (long) q.executeUnique();
	}
}
