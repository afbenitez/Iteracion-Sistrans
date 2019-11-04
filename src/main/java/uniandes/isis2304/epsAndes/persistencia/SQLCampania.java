package uniandes.isis2304.epsAndes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Campania;

public class SQLCampania 
{
	/* **********************
	 * 			Constantes
	 ***********************/
	/** Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos Se renombra acÃ¡ para facilitar la escritura de las sentencias. */
	private final static String SQL = PersistenciaEps.SQL;

	/* **********************
	 * 			Atributos
	 ***********************/
	/** El manejador de persistencia general de la aplicaciÃ³n. */
	private PersistenciaEps pp;


	/**
	 * Constructor.
	 *
	 * @param pp - El Manejador de persistencia de la aplicaciÃ³n
	 */
	public SQLCampania (PersistenciaEps pp)
	{
		this.pp = pp;
	}

	
	/**
	 * Registrar prestacion.
	 *
	 * @param pm the pm
	 * @param idCita el id cita
	 * @param id_Recepcionista el id recepcionista
	 * @return the long
	 */
	public long registrarCampania(PersistenceManager pm, String nombre,  String fechaInicio ,String fechaFin,String idOrganizador)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCampania()+ " ( nombre, fecha_inicio, fecha_fin, id_organizador) VALUES ( ?,?,?,?)");
		q.setParameters(nombre,fechaInicio, fechaFin,idOrganizador);
		return (long) q.executeUnique();   
	}

	public long registrarServicioCampania(PersistenceManager pm, String idServicio, String idCampania, String fechaInicio , String fechaFin, int capacidad, int capacidadMax )
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCampania()+ " (id_servicio, id_campania, fecha_inicio, fecha_fin, capacidad, capacidadMax) VALUES (?,?,?,?,?,?)");
		q.setParameters(idServicio,idCampania,fechaInicio,fechaFin, capacidad, capacidadMax );
		return (long) q.executeUnique();   
	}

	public long eliminarServicioCampania(PersistenceManager pm,String idServicio, String idCampania)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCampania()+ " WHERE id_servicio=? AND id_campania=?");
		q.setParameters(idServicio, idCampania);
		return (long) q.executeUnique();   
	}

	public BigDecimal darCapacidad(PersistenceManager pm,String idServicio, String idCampania)
	{
		Query q = pm.newQuery(SQL, "SELECT capacidad FROM " + pp.darTablaCampania() + " WHERE id_servicio=? AND id_campania=?");
		q.setParameters(idServicio,idCampania);
		return (BigDecimal) q.executeUnique();
	}

	public List<Object[]> darCampania(PersistenceManager pm,String idServicio, String idCampania)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCampania() + " WHERE id_servicio=? AND id_campania=?");
		q.setParameters(idServicio,idCampania);
		return q.executeList();
	}
	
	
	public List<Campania> darCampanias(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCampania());
		q.setResultClass(Campania.class);
		return q.executeList();
	}
	
	public long eliminarCampaniaNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCampania()+" WHERE nombre=?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}
	
	public Campania darCampaniaNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCampania() + " WHERE ID_CAMPANIA=?");
		q.setResultClass(Campania.class);
		q.setParameters(nombre);
		return (Campania) q.executeUnique();
	}
}
