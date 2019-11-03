package uniandes.isis2304.epsAndes.persistencia;

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
	

	public long adicionarCita (PersistenceManager pm, long id, long idReceta, long idUsuario, long idRecepcionista, String idServicio, String estado, String fecha, int horario) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCita() + " ( id, id_receta, id_usuario, id_recepcionista, id_servicio, estado_cita, fecha, horario) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, idReceta, idUsuario, idRecepcionista, idServicio, estado, fecha);
        
        return (long) q.executeUnique();
	}
	
	public long registrarPrestacion (PersistenceManager pm, long id,  long idRecepcionista, long idServicio, long idPaciente) 
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCita() + " SET estado_cita =?, id_recepcionista=? WHERE id =? AND id_servicio=? AND id_usuario=? AND estado_cita ='RESERVADA'  ");
        q.setParameters("REALIZADA", idRecepcionista, id, idServicio, idPaciente);
        
        return (long) q.executeUnique();
	}
}
