package uniandes.isis2304.epsAndes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Prestan;
import uniandes.isis2304.epsAndes.negocio.ServicioDeSalud;

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
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPrestan() + " ( dia, horario, id_servicio, id_ips, capacidad, capacidad_max, estado) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(dia, horario, idServicio, idIps, capacidad, capacidadMax ,estado);
        
        return (long) q.executeUnique();
	}
	
	public long actualizarHabilitacion(PersistenceManager pm,String fechaInicio,String fechaFin,String ips,String idServicio,int habilitacion)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPrestan() + " SET estado=? WHERE (TO_DATE(DIA,'DD-MM-YY HH24:MI:SS') BETWEEN TO_DATE(?,'DD-MM-YY HH24:MI:SS') AND TO_DATE(?,'DD-MM-YY HH24:MI:SS')) AND id_servicio=? AND id_ips=?");
		q.setParameters(habilitacion,fechaInicio,fechaFin,idServicio,ips);
		return (long) q.executeUnique();   
	}
	
	public long darCapacidadServicioEnRango(PersistenceManager pm, String idServicio, String fechaInicio, String fechaFin)
	{
		String sql="SELECT SUM(capacidad) FROM "+pp.darTablaPrestan()+" tp  WHERE id_servicio=? AND TO_DATE(tp.DIA,'DD-MM-YY HH24:MI:SS') BETWEEN TO_DATE(?,'DD-MM-YY HH24:MI:SS') AND TO_DATE(?,'DD-MM-YY HH24:MI:SS') AND estado=0";
		Query q=pm.newQuery(SQL,sql);
		q.setParameters(idServicio,fechaInicio,fechaFin);
		BigDecimal rta=(BigDecimal) q.executeUnique();
		return rta==null?0:rta.longValue();
	}

	public List<Prestan> darInfoServicioEnRango(PersistenceManager pm, String idServicio, String fechaInicio, String fechaFin)
	{
		String sql="SELECT * FROM "+pp.darTablaPrestan()+" tp  WHERE id_servicio=? AND TO_DATE(tp.DIA,'DD-MM-YY HH24:MI:SS') BETWEEN TO_DATE(?,'DD-MM-YY HH24:MI:SS') AND TO_DATE(?,'DD-MM-YY HH24:MI:SS') AND estado=0";
		Query q=pm.newQuery(SQL,sql);
		q.setParameters(idServicio,fechaInicio,fechaFin);
		q.setResultClass(Prestan.class);
		return (List<Prestan>)q.executeList();
	}
	
	public long actualizarCapacidad(PersistenceManager pm,int capacidad,String fecha,String idServicio)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPrestan() + " SET capacidad=? WHERE dia=? AND id_servicio=?");
		q.setParameters(capacidad,fecha,idServicio);
		return (long) q.executeUnique();   
	}
	
	public List<Object[]> analizarOpSemanaDemanda(PersistenceManager pm, String servicio,String unidad,String orden)
	{
		String sql="SELECT SUM(capacidadmax-capacidad) AS diferencia, to_number(to_char(TO_DATE(tp.DIA,'DD-MM-YY HH24:MI:SS'), '"+unidad+"')) semana FROM "+ pp.darTablaPrestan();
		sql+=" tp WHERE id_servicio=?";
		sql+=" GROUP BY to_number(to_char(TO_DATE(tp.DIA,'DD-MM-YY HH24:MI:SS'), '"+unidad+"')) ";
		sql+="ORDER BY SUM(capacidadmax-capacidad) "+orden+" FETCH NEXT 5 ROWS ONLY";
		Query q=pm.newQuery(SQL,sql);
		q.setParameters(servicio);
		return q.executeList();	
	}
	
	public List<Object[]> analizarOpSemanaActividad(PersistenceManager pm, String servicio,String unidad,String orden)
	{
		String sql="SELECT COUNT (*) REALIZADAS,to_number(to_char(TO_DATE(tc.FECHA,'DD-MM-YY HH24:MI:SS'), '"+unidad+"')) fecha "+
				"FROM "+pp.darTablaPrestan()+ " tc "+
				"WHERE tc.estado=1 AND tc.id_servicio=? "+
				"GROUP BY to_number(to_char(TO_DATE(tc.FECHA,'DD-MM-YY HH24:MI:SS'), '"+unidad+"'))" + 
				" ORDER BY COUNT(*) " + orden +
				" FETCH NEXT 5 ROWS ONLY";
		Query q=pm.newQuery(SQL,sql);
		q.setParameters(servicio);
		return q.executeList();	
	}
	
	public List<String> darNoMuyDemandados(PersistenceManager pm)
	{
		String sql="SELECT DISTINCT id_servicio FROM "+
				"(SELECT id_servicio,SUM(capacidadmax-capacidad) diferencia "+
				"FROM "+pp.darTablaPrestan()+" tp "+
				"WHERE to_number(to_char(TO_DATE(tp.DIA,'DD-MM-YY HH24:MI:SS'), 'YY'))=to_number(to_char(CURRENT_DATE, 'YY'))-1 "+
				"GROUP BY id_servicio,to_number(to_char(TO_DATE(tp.DIA,'DD-MM-YY HH24:MI:SS'), 'WW'))) "+
				"WHERE diferencia<3";
		Query q=pm.newQuery(SQL,sql);
		return(List<String>) q.executeList();	
	}
}
