package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.ServicioDeSalud;

public class SQLServicioDeSalud {

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
	public SQLServicioDeSalud (PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarServicioDeSalud (PersistenceManager pm, long id, String nombre,  String tipoServicio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioDeSalud() + " ( id, nombre, tipo_servicio) values (?, ?, ?)");
        q.setParameters(id, nombre, tipoServicio);
        
        return (long) q.executeUnique();
	}
	
	public ServicioDeSalud darServicioSaludPorId (PersistenceManager pm, String identificacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioDeSalud() + " WHERE nombre = ?");
		q.setResultClass(ServicioDeSalud.class);
		q.setParameters(identificacion);
		return (ServicioDeSalud) q.executeUnique();
	}
}
