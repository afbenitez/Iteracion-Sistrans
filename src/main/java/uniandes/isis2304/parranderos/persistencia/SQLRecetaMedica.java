package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLRecetaMedica {

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
	public SQLRecetaMedica (PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarRecetaMedica (PersistenceManager pm, long id, String  fecha, long idMedico , long idUsuario, long idServicio, String medicamentos) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaRecetaMedica() + "( id, fecha, id_medico, id_usuario, id_servicio, medicamentos) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, fecha, idMedico, idUsuario, idServicio, medicamentos);
        
        return (long) q.executeUnique();
	}
}
