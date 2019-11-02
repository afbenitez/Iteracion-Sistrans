package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLServicioTerapia {
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
	public SQLServicioTerapia (PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarServicioTerapia (PersistenceManager pm, long id, long capacidad, long dias, String fecha, long horario, String tipoServicio, long sesiones, String tipoTerapia) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioDeSalud() + " ( id, capacidad_maxima, dias_semana, fecha_realizacion, horario, tipo_servicio) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, capacidad, dias, fecha, horario, tipoServicio); 
        
        
        Query k = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioTerapia() + " ( id, numero_sesiones, tipo_terapia) values (?, ?, ?)");
        k.setParameters(id, sesiones, tipoTerapia);
        
        return (long) q.executeUnique();
	}
}
