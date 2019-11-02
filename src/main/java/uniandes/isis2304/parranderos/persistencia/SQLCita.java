package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLCita {

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
	public SQLCita(PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarCita (PersistenceManager pm, long id, long idMedico, long idUsuario, long idRecepcionista, long idServicio, String estado, String fecha) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCita() + "( id, id_medico, id_usuario, id_recepcionista, id_servicio, estado_cita, fecha) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, idMedico, idUsuario, idRecepcionista, idServicio, estado, fecha);
        
        return (long) q.executeUnique();
	}
}
