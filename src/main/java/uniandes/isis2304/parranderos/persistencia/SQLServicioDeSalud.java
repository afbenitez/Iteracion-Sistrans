package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
	
	public List<Object []> darIPSYCantidadServiciosOfrecen (PersistenceManager pm, String fechaInicio, String fechaFin)
	{
	 String sql = "SELECT tp.ID_IPS, count (*) as numServicios";
	 sql += " FROM " + pp.darTablaCita()+" tp,";
	 sql += pp.darTablaRecepcionista()+" tr,";
	 sql += pp.darTablaCita()+" tc";
	 sql += " WHERE tc.ID_RECEPCIONISTA IS NOT NULL AND tc.ID_SERVICIO=tp.ID_SERVICIO AND tr.ID_IPS=tp.ID_IPS AND tc.ID_RECEPCIONISTA=tr.IDENTIFICACION";
	 sql += " AND TO_DATE(tp.DIA,'DD-MM-YY HH24:MI:SS') BETWEEN TO_DATE(?,'DD-MM-YY HH24:MI:SS') AND TO_DATE(?,'DD-MM-YY HH24:MI:SS')";
	 sql += " GROUP BY tp.ID_IPS";
	 Query q = pm.newQuery(SQL, sql);
	 q.setParameters(fechaInicio,fechaFin);
	 return q.executeList();
	}
}
