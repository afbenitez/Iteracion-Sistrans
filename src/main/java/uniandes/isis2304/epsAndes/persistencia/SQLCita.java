package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.AdministradorDatos;
import uniandes.isis2304.epsAndes.negocio.Cita;

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
        q.setParameters(id, idReceta, idUsuario, idRecepcionista, idServicio, estado, fecha, horario);
        
        return (long) q.executeUnique();
	}
	
	public long registrarPrestacion (PersistenceManager pm, long id,  long idRecepcionista, String idServicio, long idPaciente) 
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCita() + " SET estado_cita =?, id_recepcionista=? WHERE id =? AND id_servicio=? AND id_usuario=? AND estado_cita ='RESERVADA'  ");
        q.setParameters("REALIZADA", idRecepcionista, id, idServicio, idPaciente);
        
        return (long) q.executeUnique();
	}
	
	public Cita darCitaPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCita() + " WHERE id = ?");
		q.setResultClass(Cita.class);
		q.setParameters(id);
		return (Cita) q.executeUnique();
	}
	
	public List<Object[]> sacarCitasPorIPSServicio(PersistenceManager pm,String ips,String servicio,String fechaInicio,String fechaFin)
	{
		String sql="SELECT tc.id_receta, tc.fecha, tc.horario, tc.id_usuario";
		sql+= " FROM "+pp.darTablaCita() +" tc, "+ pp.darTablaPrestan()+" tp, "+ pp.darTablaRecepcionista() +" tr WHERE tp.id_servicio=tc.id_servicio AND tp.id_servicio=? AND tp.id_ips=? AND tp.id_ips=tc.id_recepcionista AND TO_DATE(tc.FECHA,'DD-MM-YY HH24:MI:SS') BETWEEN TO_DATE(?,'DD-MM-YY HH24:MI:SS') AND TO_DATE(?,'DD-MM-YY HH24:MI:SS')";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(servicio,ips,fechaInicio,fechaFin);
		return q.executeList();
	}
	
	public void eliminarCitaIPS(PersistenceManager pm,String idServicio,String idAfiliado,String ips )
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCita() + " WHERE id_servicio=? AND id_usuario =? AND id_recepcionista=? ");
		q.setParameters(idServicio,idAfiliado,ips);
		q.executeUnique();  
	}
	
	public List<Object[]> darExigentes (PersistenceManager pm){
		String sql="SELECT aux.AFILIADO,aux.TIPOS,aux.SERVICIOS" + 
				"	FROM(SELECT tc.id_usuario AFILIADO, COUNT (DISTINCT ts.tipo_servicio) TIPOS, COUNT (tc.id_servicio) SERVICIOS" + 
				"	FROM "+pp.darTablaCita()+" tc, "+pp.darTablaServicioDeSalud()+" ts " + 
				"	WHERE ts.nombre=tc.id_servicio AND to_number(to_char(TO_DATE(tc.FECHA,'DD-MM-YY HH24:MI:SS'), 'YY'))=to_number(to_char(CURRENT_DATE, 'YY'))-1" + 
				"	GROUP BY tc.id_usuario,ts.tipo_servicio, tc.id_servicio)aux" + 
				"	WHERE aux.TIPOS>2 AND aux.SERVICIOS>12";
		Query q = pm.newQuery(SQL,sql);
		return q.executeList();
	}
}
