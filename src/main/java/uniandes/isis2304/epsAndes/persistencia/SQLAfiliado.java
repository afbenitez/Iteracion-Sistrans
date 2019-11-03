package uniandes.isis2304.epsAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.AdministradorDatos;
import uniandes.isis2304.epsAndes.negocio.Afiliado;

public class SQLAfiliado {

	
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
	public SQLAfiliado (PersistenciaEps pp)
	{
		this.pp = pp;
	}
	

	public long adicionarAfiliado (PersistenceManager pm, long id, String  fecha, String email, String nombre, long cedula, int rol, String tipoId) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario () + " ( id, email, nombre, numero_id, rol, tipo_identificacion) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, email, nombre, cedula, rol, tipoId);
        q.executeUnique();
        
        Query k = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAfiliado() + " ( id, fecha_nacimiento, numero_id) values (?, ?, ?)");
        k.setParameters(id, fecha, cedula);
        return (long) k.executeUnique();
	}
	
	public Afiliado darAfiliadoPorId (PersistenceManager pm, long cedula) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAfiliado() + " WHERE numero_id = ?");
		q.setResultClass(Afiliado.class);
		q.setParameters(cedula);
		return (Afiliado) q.executeUnique();
	}

}
