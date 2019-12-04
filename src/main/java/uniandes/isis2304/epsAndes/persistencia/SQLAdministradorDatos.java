
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.AdministradorDatos;
import uniandes.isis2304.epsAndes.negocio.Afiliado;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLAdministradorDatos 
{
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
	public SQLAdministradorDatos (PersistenciaEps pp)
	{
		this.pp = pp;

	}


	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del Adminstrador
	 * @param email - El email del Adminstrador
	 * @param nombre - El nombre del Adminstrador
	 * @param cedula - La cedula del Adminstrador
	 * @param rol - El rol de Adminstrador
	 * @param tipoId - El tipo de identificacion
	 * @return El número de tuplas insertadas
	 */
	public long adicionarAdministrador (PersistenceManager pm, long id, String email, String nombre, long cedula, int rol, String tipoId) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario() + " ( id, email, nombre, numero_id, rol, tipo_identificacion) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(id, email, nombre, cedula, rol, tipoId);

		q.executeUnique();

		Query k = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAdministradorDatos() + " ( id, numero_id) values (?, ?)");
		k.setParameters(id, cedula);
		return (long) k.executeUnique();
	}



	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de EpsAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param cedula - El identificador del ADMINISTRADORDATOS
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarAdministradorPorId (PersistenceManager pm, long cedula)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAdministradorDatos () + " WHERE numero_id = ?");
		q.setParameters(cedula);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de EpsAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param cedula - El identificador del ADMINISTRADORDATOS
	 * @return El objeto ADMINISTRADORDATOS que tiene el identificador dado
	 */
	public AdministradorDatos darAdministradorPorId (PersistenceManager pm, long cedula) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministradorDatos() + " WHERE numero_id = ?");
		q.setResultClass(AdministradorDatos.class);
		q.setParameters(cedula);
		return (AdministradorDatos) q.executeUnique();
	}



	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de EpsAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ADMINISTRADORDATOS
	 */
	public List<AdministradorDatos> darAdministradores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministradorDatos());
		q.setResultClass(AdministradorDatos.class);
		return (List<AdministradorDatos>) q.executeList();
	}


	/**
	 * @param idAdministradorDatos - El identificador del Administrador
	 * @param nombre - El nombre del administrador
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del bar
	 * @return El número de tuplas insertadas
	 */
	public long adicionarBar (PersistenceManager pm, long idBar, String nombre, String ciudad, String presupuesto, int sedes) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAdministradorDatos () + "(id, nombre, ciudad, presupuesto, cantsedes) values (?, ?, ?, ?, ?)");
		q.setParameters(idBar, nombre, ciudad, presupuesto, sedes);
		return (long) q.executeUnique();
	}
	public List<Afiliado> consultarPrestacion1(PersistenceManager pm)
	{
		String sql = 
				  "with cantidadS AS" 
				+ "( SELECT SUM(COUNT(DISTINCT id_servicio)) c2"
				+ "FROM CITA WHERE ID_SERVICIO IN('Consultas odontologicas','Radiografias')"
				+ "GROUP BY id_servicio),"
				
				+ "cantidadXUS AS"
				+ "(SELECT COUNT(DISTINCT c.id_servicio) c1,c.id_afiliado"  
				+ "FROM CITA c" 
				+ "WHERE c.ID_SERVICIO IN('Consultas odontologicas','Radiografias') AND c.cumplida = 1 AND to_date(c.fecha,'dd/mm/yyyy hh24:mi:ss') " 
				+ "BETWEEN to_date('25/12/2000 12:00:00','dd/mm/yyyy hh24:mi:ss') AND to_date('25/12/2019 12:00:00','dd/mm/yyyy hh24:mi:ss')" 
				+ "GROUP BY c.id_afiliado"
				+ "ORDER BY c.id_afiliado DESC),"
				
				+ "selecxS AS"
				+ "(SELECT *"
				+ "FROM USUARIO u, AFILIADO a, cantidadXUS c,cantidadS cs" 
				+ "WHERE a.IDENTIFICACION=u.NUMERO_DOCUMENTO AND c.id_afiliado=a.IDENTIFICACION AND c.c1=cs.c2),"
				
				
				+ "cantidadT AS"
				+ "(SELECT SUM(COUNT(DISTINCT s.tipo)) c2"
				+ "FROM CITA c,SERVICIO s"
				+ "WHERE c.ID_SERVICIO=s.nombre AND s.tipo IN('Consulta de control')"
				+ "GROUP BY s.tipo2),"
				
				+ "cantidadXUT AS"
				+ "(SELECT COUNT(DISTINCT s.tipo) c1,id_afiliado"
				+ "FROM CITA c, SERVICIO s"
				+ "WHERE c.ID_SERVICIO=s.nombre and s.tipo IN('Consulta de control') AND c.cumplida=1"
				+ "GROUP BY id_afiliado"
				+ "ORDER BY COUNT(DISTINCT s.tipo) DESC),"
				
				+ "selecxT AS"
				+ "(SELECT *" 
				+ "FROM USUARIO u, AFILIADO a, cantidadXUT c,cantidadT cs"
				+ "WHERE a.IDENTIFICACION=u.NUMERO_DOCUMENTO AND c.id_afiliado=a.IDENTIFICACION AND c.c1=cs.c2),"
				
				+ "selectI AS"
				+ "(SELECT DISTINCT c.id_afiliado" 
				+ "FROM CITA c, PRESTAN p "
				+ "WHERE c.fecha=p.dia AND c.id_servicio=p.id_servicio AND p.id_ips IN(SELECT ID_IPS FROM PRESTAN))"
				+ "SELECT  s1.email,s1.nombre,s1.numero_documento,s1.rol,s1.tipo_documento,s1.fecha_nacimiento"
				+ "FROM selecxS s1"
				+ "WHERE s1.identificacion IN(SELECT id_afiliado FROM selecxT) AND s1.identificacion IN(SELECT id_afiliado FROM selectI))";
		Query q = pm.newQuery(SQL,sql);
		return q.executeList();
	}
}
