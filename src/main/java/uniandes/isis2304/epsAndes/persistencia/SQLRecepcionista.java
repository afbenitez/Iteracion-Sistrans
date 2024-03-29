package uniandes.isis2304.epsAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Gerente;
import uniandes.isis2304.epsAndes.negocio.Recepcionista;

public class SQLRecepcionista {
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
	public SQLRecepcionista (PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarRecepcionista (PersistenceManager pm, long id, String email, String nombre, long cedula, int rol, String tipoId, String idIps) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario () + " ( id, email, nombre, numero_id, rol, tipo_identificacion) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, email, nombre, cedula, rol, tipoId);
        q.executeUnique();
        
        Query k = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaRecepcionista() + " ( id, id_ips, numero_id) values (?, ?, ?)");
        k.setParameters(id, idIps,cedula);
        return (long) k.executeUnique();
	}
	
	public Recepcionista darRecepcionistaPorId (PersistenceManager pm, long cedula) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRecepcionista() + " WHERE numero_id = ?");
		q.setResultClass(Recepcionista.class);
		q.setParameters(cedula);
		return (Recepcionista) q.executeUnique();
	}
}
