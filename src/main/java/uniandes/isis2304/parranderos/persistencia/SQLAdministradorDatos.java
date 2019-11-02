package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLAdministradorDatos {

	private final static String SQL = PersistenciaEps.SQL;
	
	private PersistenciaEps pe;
	
	public SQLAdministradorDatos(PersistenciaEps pe)
	{
		this.pe = pe;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idAdministradorDatos - El identificador del Administrador
	 * @param nombre - El nombre del administrador
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del bar
	 * @return El número de tuplas insertadas
	 */
	public long adicionarBar (PersistenceManager pm, long idBar, String nombre, String ciudad, String presupuesto, int sedes) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBar () + "(id, nombre, ciudad, presupuesto, cantsedes) values (?, ?, ?, ?, ?)");
        q.setParameters(idBar, nombre, ciudad, presupuesto, sedes);
        return (long) q.executeUnique();
	}
}
