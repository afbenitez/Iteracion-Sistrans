/**~~~~~~~~~~~~~~~~~~~~~~~
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
 * ~~~~~~~~~~~~~~~~~~~~~~~~
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
	/* **********************
	 * 			Constantes
	 ***********************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaEps.SQL;

	/* **********************
	 * 			Atributos
	 ***********************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaEps pp;

	/* **********************
	 * 			Métodos
	 ***********************/

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
	
	
	public List<Object[]> consultarPrestacion1(PersistenceManager pm)
	{
		String sql = 
				  "SELECT  sl.email,sl.nombre,sl.numero_id,sl.rol,sl.tipo_identificacion, sl.fecha"
				  + "FROM (SELECT u.email,u.nombre,u.numero_id,u.rol,u.tipo_identificacion, c.fecha"
				  + "FROM USUARIO u, AFILIADO a, "
				  + "(SELECT COUNT(DISTINCT c.id_servicio)c1, c.id_usuario,c.fecha"
				  + "FROM CITA c"
				  + "WHERE c.id_servicio IN('Servicio 260','Servicio 379') AND c.estado_cita = 'REALIZADA' AND to_date(c.fecha,'mm/dd/yyyy') "
				  + "BETWEEN to_date('10/24/2010','mm/dd/yyyy') AND to_date('12/15/2016','mm/dd/yyyy') "
				  + "GROUP BY c.id_usuario, c.fecha "
				  + "ORDER BY c.id_usuario DESC"
				  + ")c,"
				  + "(SELECT COUNT(DISTINCT id_servicio)c2"
				  + "FROM CITA"
				  + "WHERE id_servicio IN('Servicio 260','Servicio 379')"
				  + "GROUP BY id_servicio"
				  + ")cs "
				  + "WHERE a.numero_id=u.numero_id AND c.id_usuario=a.numero_id AND c.c1=cs.c2"
				  + ")sl"
				  + "WHERE sl.numero_id IN"
				  + "(SELECT id_usuario FROM"
				  + "(SELECT *"
				  + "FROM USUARIO u, AFILIADO a,"
				  + "(SELECT COUNT(DISTINCT s.tipo_servicio) c1,id_usuario"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.id_servicio=s.nombre AND s.tipo_servicio IN('HOSPITALIZACION') AND c.estado_cita = 'REALIZADA'"
				  + "GROUP BY id_usuario"
				  + "ORDER BY COUNT(DISTINCT s.tipo_servicio) DESC"
				  + ")c,"
				  + "(SELECT SUM(COUNT(DISTINCT s.tipo_servicio))c2"
				  + "FROM CITA c,SERVICIODESALUD s"
				  + "WHERE c.id_servicio=s.nombre and s.tipo_servicio IN('HOSPITALIZACION')"
				  + "GROUP BY s.tipo_servicio"
				  + ")cs"
				  + "WHERE a.numero_id=u.numero_id AND c.id_usuario=a.numero_id AND c.c1=cs.c2"
				  + ")"
				  + ")"
				  + "AND sl.numero_id IN"
				  + "(SELECT id_usuario FROM"
				  + "(SELECT DISTINCT c.id_usuario"
				  + "FROM CITA c, PRESTAN p "
				  + "WHERE c.fecha=p.dia AND c.id_servicio=p.id_servicio AND p.id_ips IN(SELECT id_ips FROM PRESTAN)"
				  + ")"
				  + ")";
		Query q = pm.newQuery(SQL,sql);
		return q.executeList();
	}

	public List<Object[]> consultarPrestacion2(PersistenceManager pm)
	{
		String sql = 
				  "SELECT  u.numero_id,u.email,u.nombre,u.rol,u.tipo_identificacion, sl.fecha_nacimiento"
				  + "FROM AFILIADO sl, USUARIO u "
				  + "WHERE sl.numero_id=u.numero_id "
				  + "AND sl.numero_id NOT IN"
				  + "("
				  + "SELECT numero_id "
				  + "FROM (SELECT u.email,u.nombre,u.numero_id,u.rol,u.tipo_identificacion"
				  + "FROM USUARIO u, AFILIADO a,"
				  + "(SELECT COUNT(DISTINCT c.id_servicio)c1, c.id_usuario"
				  + "FROM CITA c"
				  + "WHERE c.id_servicio IN('Servicio 260','Servicio 379') AND c.estado_cita = 'REALIZADA' AND to_date(c.fecha,'mm/dd/yyyy')"
				  + "BETWEEN to_date('10/24/2010','mm/dd/yyyy') AND to_date('12/15/2016','mm/dd/yyyy')"
				  + "GROUP BY c.id_usuario"
				  + "ORDER BY c.id_usuario DESC"
				  + ")c,"
				  + "(SELECT COUNT(DISTINCT id_servicio)c2"
				  + "FROM CITA"
				  + "WHERE id_servicio IN('Servicio 260','Servicio 379')"
				  + "GROUP BY id_servicio"
				  + ")cs"
				  + "WHERE a.numero_id=u.numero_id AND c.id_usuario=a.numero_id AND c.c1=cs.c2"
				  + ") "
				  + ")"
				  + "AND sl.numero_id NOT IN"
				  + "("
				  + "SELECT numero_id "
				  + "FROM (SELECT u.email,u.nombre,u.numero_id,u.rol,u.tipo_identificacion"
				  + "FROM USUARIO u, AFILIADO a,"
				  + "(SELECT COUNT(DISTINCT s.tipo_servicio) c1,id_usuario"
				  + "FROM CITA c, SERVICIODESALUD s "
				  + "WHERE c.id_servicio=s.nombre AND s.tipo_servicio IN('HOSPITALIZACION') AND c.estado_cita = 'REALIZADA'"
				  + "GROUP BY id_usuario"
				  + "ORDER BY COUNT(DISTINCT s.tipo_servicio) DESC"
				  + ")c,"
				  + "(SELECT SUM(COUNT(DISTINCT s.tipo_servicio))c2"
				  + "FROM CITA c,SERVICIODESALUD s"
				  + "WHERE c.id_servicio=s.nombre and s.tipo_servicio IN('HOSPITALIZACION')"
				  + "GROUP BY s.tipo_servicio"
				  + ")cs"
				  + "WHERE a.numero_id=u.numero_id AND c.id_usuario=a.numero_id AND c.c1=cs.c2"
				  + ")"
				  + ")"
				  + "AND sl.numero_id NOT IN"
				  + "(SELECT id_usuario"
				  + "FROM (SELECT DISTINCT c.id_usuario"
				  + "FROM CITA c, PRESTAN p"
				  + "WHERE c.fecha=p.dia AND c.id_servicio=p.id_servicio AND p.id_ips IN(SELECT id_ips FROM PRESTAN)"
				  + ")"
				  + ")";
		Query q = pm.newQuery(SQL,sql);
		return q.executeList();
	}
	
	
	public List<Object[]> consultarFuncionamiento(PersistenceManager pm)
	{
		String sql = 
				  "SELECT *"
				  + "FROM "
				  + "("
				  + "SELECT tt1.semana AS semana, LISTAGG(tt1.tipo_servicio,', ')WITHIN GROUP(ORDER BY tt1.tipo_servicio) AS TiposMasUsados, t2.c1 AS CantidadTipoMasUsado"
				  + "FROM "
				  + "(SELECT (COUNT(s.tipo_servicio)) ca, s.tipo_servicio as tipo_servicio, to_char(to_date(c.FECHA, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.id_servicio = s.nombre AND c.estado_cita = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.tipo_servicio"
				  + "ORDER BY semana DESC"
				  + ")tt1,"
				  + "(SELECT MAX(ca) C1, semana"
				  + "FROM (SELECT   ca, tipo_servicio,semana"
				  + "FROM (SELECT (COUNT(s.tipo_servicio)) ca, s.tipo_servicio as tipo_servicio, to_char(to_date(c.FECHA, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.id_servicio = s.nombre AND c.estado_cita = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.tipo_servicio"
				  + "ORDER BY semana DESC"
				  + ")"
				  + ")"
				  + "GROUP BY semana) t2"
				  + "WHERE tt1.semana = t2.semana AND tt1.ca = t2.c1"
				  + "GROUP BY tt1.semana, t2.c1"
				  + ")t1"
				  + "NATURAL INNER JOIN"
				  + "("
				  + "SELECT tt1.semana AS semana, LISTAGG(tt1.tipo_servicio,', ')WITHIN GROUP(ORDER BY tt1.tipo_servicio) AS TiposMenosUsados, t2.c1 AS CantidadTipoMenosUsado"
				  + "FROM  (SELECT (COUNT(s.tipo_servicio)) ca, s.tipo_servicio as tipo_servicio, to_char(to_date(c.FECHA, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.id_servicio = s.nombre AND c.estado_cita = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.tipo_servicio"
				  + " ORDER BY semana DESC"
				  + ")tt1,"
				  + "(SELECT MIN(ca) C1, semana"
				  + "FROM (SELECT   ca, tipo_servicio,semana"
				  + "FROM (SELECT (COUNT(s.tipo_servicio)) ca, s.tipo_servicio as tipo_servicio, to_char(to_date(c.FECHA, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.id_servicio = s.nombre AND c.estado_cita = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.tipo_servicio"
				  + "ORDER BY semana DESC"
				  + ")"
				  + ")"
				  + "GROUP BY semana"
				  + ")t2"
				  + "WHERE tt1.semana = t2.semana AND tt1.ca = t2.c1"
				  + "GROUP BY tt1.semana, t2.c1"
				  + ")t2"
				  + "NATURAL INNER JOIN"
				  + "(SELECT t1.semana AS semana, LISTAGG(t1.nombre,', ') WITHIN GROUP(ORDER BY t1.nombre) as ServiciosMasUsados, t2.c1 as CantidadServicioMasUsado"
				  + "FROM"
				  + "(SELECT (COUNT(s.tipo_servicio))ca, s.nombre as nombre, to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.id_servicio = s.nombre AND c.estado_cita = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.nombre"
				  + ")t1,"
				  + "(SELECT MAX(ca) C1, semana"
				  + "FROM (SELECT *"
				  + "FROM (SELECT (COUNT(s.tipo_servicio))ca, s.nombre as nombre, to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.id_servicio = s.nombre AND c.estado_cita = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.nombre"
				  + ")"
				  + ")"
				  + " GROUP BY semana)t2"
				  + "WHERE t1.semana = t2.semana AND t1.ca = t2.c1"
				  + "GROUP BY t1.semana, t2.c1"
				  + ")"
				  + "NATURAL INNER JOIN"
				  + "(SELECT t1.semana as semana, LISTAGG(t1.nombre,', ') WITHIN GROUP(ORDER BY t1.nombre) AS ServiciosMenosUsados, t2.c1 AS CantidadServicioMenosUsado"
				  + "FROM (SELECT (COUNT(s.tipo_servicio))ca, s.nombre as nombre, to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.id_servicio = s.nombre AND c.estado_cita = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.nombre"
				  + ")t1,"
				  + "(SELECT MIN(ca) C1, semana"
				  + "FROM (SELECT *"
				  + "FROM (SELECT (COUNT(s.tipo_servicio))ca, s.nombre as nombre, to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.id_servicio = s.nombre AND c.estado_cita = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.nombre"
				  + ")"
				  + ")"
				  + "GROUP BY semana) t2"
				  + "WHERE t1.semana = t2.semana AND t1.ca = t2.c1 "
				  + "GROUP BY t1.semana, t2.c1"
				  + ")"
				  + "NATURAL INNER JOIN "
				  + "(SELECT ti1.semana AS semana, LISTAGG (ti1.id_ips,', ') WITHIN GROUP(ORDER BY ti1.id_ips) AS IPSMasSolicitadas, t2.c1 AS CantidadIPSMasUsado"
				  + "FROM"
				  + "(SELECT (COUNT(s.id_ips))ca,s.id_ips as id_ips, to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, PRESTAN s"
				  + "WHERE c.id_servicio = s.id_servicio AND c.estado_cita = 'REALIZADA' AND c.fecha=s.dia"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.id_ips"
				  + ")ti1,"
				  + "(SELECT MAX(ca) C1, semana"
				  + "FROM ( SELECT *"
				  + "FROM (SELECT (COUNT(s.id_ips))ca,s.id_ips as id_ips, to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, PRESTAN s"
				  + "WHERE c.id_servicio = s.id_servicio AND c.estado_cita = 'REALIZADA' AND c.fecha=s.dia"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.id_ips"
				  + ")"
				  + ")"
				  + "GROUP BY semana"
				  + ")t2"
				  + "WHERE ti1.semana = t2.semana AND ti1.ca = t2.c1 "
				  + "group by ti1.semana, t2.c1"
				  + ")"
				  + "NATURAL INNER JOIN "
				  + "(SELECT til.semana AS semana, LISTAGG(til.id_ips,', ') WITHIN GROUP(ORDER BY til.id_ips) as IPSMenosSolicitadas, t2.c1 AS CantidadIPSMenosUsado"
				  + "FROM"
				  + "(SELECT (COUNT(s.id_ips))ca,s.id_ips as id_ips, to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, PRESTAN s"
				  + "WHERE c.id_servicio = s.id_servicio AND c.estado_cita = 'REALIZADA' AND c.fecha=s.dia"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.id_ips"
				  + ")til,"
				  + "(SELECT MIN(ca) C1, semana"
				  + "FROM (SELECT * "
				  + "FROM (SELECT (COUNT(s.id_ips))ca,s.id_ips as id_ips, to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c, PRESTAN s"
				  + "WHERE c.id_servicio = s.id_servicio AND c.estado_cita = 'REALIZADA' AND c.fecha=s.dia"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), s.id_ips"
				  + ")"
				  + ")"
				  + "GROUP BY semana"
				  + ")t2"
				  + "WHERE til.semana = t2.semana AND til.ca = t2.c1"
				  + "GROUP BY til.semana, t2.c1"
				  + ")"
				  + "NATURAL INNER JOIN  "
				  + "(SELECT tul.semana as semana, listagg(tul.id_usuario,', ')within group(order by tul.id_usuario) AS AfiliadoMasSolicitador, t2.c1 AS AfiliadoMasUsador"
				  + "FROM"
				  + "(SELECT (COUNT(c.id_usuario))ca, c.id_usuario as id_usuario, to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c"
				  + "WHERE c.estado_cita = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), c.id_usuario"
				  + ")tul,"
				  + "(SELECT MAX(ca) C1, semana"
				  + "FROM (SELECT * "
				  + "FROM (SELECT (COUNT(c.id_usuario))ca, c.id_usuario as id_usuario, to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c"
				  + "WHERE c.estado_cita = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY'), c.id_usuario"
				  + ")"
				  + ")"
				  + "GROUP BY semana"
				  + ")t2"
				  + "WHERE tul.semana = t2.semana AND tul.ca = t2.c1"
				  + "GROUP BY tul.semana, t2.c1"
				  + "ORDER BY semana DESC"
				  + ")"
				  + "NATURAL INNER JOIN  "
				  + "(SELECT cuenta-AfiliadosNoCitas,semana AS semana "
				  + "FROM"
				  + " (SELECT COUNT(DISTINCT c.id_usuario) AfiliadosNoCitas, to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY') AS semana"
				  + "FROM CITA c"
				  + " WHERE c.estado_cita = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.fecha, 'mm/dd/yyyy'), 'WW/YYYY')"
				  + "),"
				  + "(SELECT COUNT(*) AS cuenta FROM AFILIADO)"
				  + ")";
		Query q = pm.newQuery(SQL,sql);
		return q.executeList();
	}
	
	public List<Object[]> consultarAFiliadosCostosos(PersistenceManager pm)
	{
		String sql = 
				  "SELECT *"
				  + "FROM "
				  + "("
				  + "SELECT t1.SEMANA AS SEMANA, LISTAGG(t1.tipo,', ') WITHIN GROUP(ORDER BY t1.tipo) AS TiposMasUsados, t2.c1 AS CantidadTipoMasUsado"
				  + "FROM (SELECT (COUNT(s.tipo_servicio))ca, s.tipo_servicio as tipo,to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.ID_SERVICIO = s.NOMBRE AND c.ESTADO_CITA = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.tipo_servicio"
				  + "ORDER BY semana DESC"
				  + ")t1,"
				  + "(SELECT MAX(ca) C1, SEMANA"
				  + "FROM (SELECT (COUNT(s.tipo_servicio)) ca, s.tipo_servicio, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.ID_SERVICIO = s.NOMBRE AND c.ESTADO_CITA = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.tipo_servicio"
				  + ")"
				  + "GROUP BY SEMANA"
				  + ") t2"
				  + "WHERE t1.SEMANA = t2.SEMANA"
				  + "AND t1.ca = t2.c1"
				  + "GROUP BY t1.SEMANA, t2.c1"
				  + "ORDER BY SEMANA DESC"
				  + ")t1"
				  + "NATURAL INNER JOIN"
				  + "("
				  + "SELECT t1.SEMANA as SEMANA, LISTAGG(t1.tipo,', ') WITHIN GROUP(ORDER BY t1.tipo) AS TiposMenosUsados, t2.c1 AS CantidadTipoMenosUsado"
				  + "FROM (SELECT (COUNT(s.tipo_servicio)) ca,s.tipo_servicio as tipo, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.ID_SERVICIO = s.NOMBRE AND c.ESTADO_CITA = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.tipo_servicio"
				  + "ORDER BY semana DESC"
				  + ")t1,"
				  + "(SELECT MIN(ca) C1, SEMANA"
				  + "FROM (SELECT (COUNT(s.tipo_servicio)) ca, s.tipo_servicio, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.ID_SERVICIO = s.NOMBRE AND c.ESTADO_CITA = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.tipo_servicio)"
				  + "GROUP BY SEMANA"
				  + ")t2"
				  + "WHERE t1.SEMANA = t2.SEMANA AND t1.ca = t2.c1 "
				  + "GROUP BY t1.SEMANA, t2.c1"
				  + "ORDER BY SEMANA DESC"
				  + ")t2"
				  + "NATURAL INNER JOIN "
				  + "("
				  + "SELECT t1.SEMANA AS SEMANA, LISTAGG(t1.NOMBRE,', ') WITHIN GROUP(ORDER BY t1.NOMBRE) AS ServiciosMasUsados, t2.c1 AS CantidadServicioMasUsado"
				  + "FROM (SELECT (COUNT(s.tipo_servicio)) ca, s.NOMBRE as NOMBRE, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.ID_SERVICIO = s.NOMBRE AND c.ESTADO_CITA = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.NOMBRE"
				  + "ORDER BY semana DESC"
				  + ") t1,"
				  + "(SELECT MAX(ca) C1, SEMANA"
				  + "FROM (SELECT (COUNT(s.NOMBRE)) ca, s.NOMBRE, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.ID_SERVICIO = s.NOMBRE AND c.ESTADO_CITA = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.NOMBRE"
				  + ")"
				  + "GROUP BY SEMANA"
				  + ") t2"
				  + "WHERE t1.SEMANA = t2.SEMANA AND t1.ca = t2.c1 group by t1.SEMANA, t2.c1"
				  + "ORDER BY SEMANA DESC"
				  + ")"
				  + "NATURAL INNER JOIN "
				  + "("
				  + "SELECT t1.SEMANA as SEMANA, LISTAGG(t1.NOMBRE,', ') WITHIN GROUP(ORDER BY t1.NOMBRE) AS ServiciosMenosUsados, t2.c1 AS CantidadServicioMenosUsado"
				  + "FROM (SELECT (COUNT(s.NOMBRE)) ca,s.NOMBRE as NOMBRE, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.ID_SERVICIO = s.NOMBRE AND c.ESTADO_CITA = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.NOMBRE"
				  + "ORDER BY semana DESC"
				  + ") t1,"
				  + "(SELECT MIN(ca) C1, SEMANA"
				  + "FROM (SELECT (COUNT(s.NOMBRE)) ca, s.NOMBRE, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c, SERVICIODESALUD s"
				  + "WHERE c.ID_SERVICIO = s.NOMBRE AND c.ESTADO_CITA = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.NOMBRE"
				  + ")"
				  + "GROUP BY SEMANA"
				  + ") t2"
				  + "WHERE t1.SEMANA = t2.SEMANA AND t1.ca = t2.c1 "
				  + "GROUP BY t1.SEMANA, t2.c1"
				  + "ORDER BY SEMANA DESC"
				  + ")"
				  + "NATURAL INNER JOIN "
				  + "( SELECT t1.SEMANA as SEMANA, LISTAGG(t1.ID_IPS,', ') WITHIN GROUP(ORDER BY t1.ID_IPS) AS IPSMasSolicitadas, t2.c1 AS CantidadIPSMasUsado"
				  + "FROM    (SELECT (COUNT(s.ID_IPS)) ca, s.ID_IPS as ID_IPS, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c, PRESTAN s"
				  + "WHERE c.ID_SERVICIO = s.ID_SERVICIO AND c.ESTADO_CITA = 'REALIZADA' and c.fecha=s.dia"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.ID_IPS"
				  + "ORDER BY  semana DESC"
				  + ") t1,"
				  + "(SELECT MAX(ca) C1, SEMANA"
				  + "FROM (SELECT (COUNT(s.ID_IPS))ca, s.ID_IPS, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c,PRESTAN s"
				  + "WHERE c.ID_SERVICIO = s.ID_SERVICIO and c.fecha=s.dia AND c.ESTADO_CITA = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.ID_IPS"
				  + ")"
				  + "GROUP BY SEMANA"
				  + ") t2"
				  + "WHERE t1.SEMANA = t2.SEMANA AND t1.ca = t2.c1 group by t1.SEMANA, t2.c1"
				  + "ORDER BY SEMANA DESC"
				  + ")"
				  + "NATURAL INNER JOIN "
				  + "( SELECT t1.SEMANA as SEMANA, LISTAGG(t1.ID_IPS,', ')  WITHIN GROUP(ORDER BY t1.ID_IPS) AS IPSMenosSolicitadas, t2.c1 AS CantidadIPSMenosUsado"
				  + "FROM (SELECT (COUNT(s.ID_IPS)) ca, s.ID_IPS as ID_IPS, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c, PRESTAN s"
				  + "WHERE c.ID_SERVICIO = s.ID_SERVICIO AND c.ESTADO_CITA = 'REALIZADA' and c.fecha=s.dia"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.ID_IPS"
				  + "ORDER BY SEMANA DESC"
				  + ") t1,"
				  + "(SELECT MIN(ca) C1, SEMANA"
				  + "FROM (SELECT (COUNT(s.ID_IPS))ca, s.ID_IPS, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c,PRESTAN s"
				  + "WHERE c.ID_SERVICIO = s.ID_SERVICIO and c.fecha=s.dia AND c.ESTADO_CITA = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), s.ID_IPS"
				  + ")"
				  + "GROUP BY SEMANA"
				  + ") t2"
				  + "WHERE t1.SEMANA = t2.SEMANA AND t1.ca = t2.c1 group by t1.SEMANA, t2.c1"
				  + "ORDER BY SEMANA DESC"
				  + ")"
				  + "NATURAL INNER JOIN "
				  + "(SELECT t1.SEMANA as SEMANA, LISTAGG(t1.ID_USUARIO,', ') WITHIN GROUP(ORDER BY t1.ID_USUARIO) AS AfiliadoMasSolicitador, t2.c1 AS AfiliadoMasUsador"
				  + "FROM (SELECT (COUNT(c.ID_USUARIO))ca, c.ID_USUARIO as ID_USUARIO, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c"
				  + "WHERE c.ESTADO_CITA = 'REALIZADA' "
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), c.ID_USUARIO"
				  + "ORDER BY semana desc"
				  + ") t1,"
				  + "(SELECT MAX(ca) C1, SEMANA"
				  + "FROM (SELECT (COUNT(c.ID_USUARIO)) ca, c.ID_USUARIO, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c"
				  + "WHERE c.ESTADO_CITA = 'REALIZADA'"
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY'), c.ID_USUARIO"
				  + ")"
				  + "GROUP BY SEMANA"
				  + ") t2"
				  + "WHERE t1.SEMANA = t2.SEMANA"
				  + "AND t1.ca = t2.c1 group by t1.SEMANA, t2.c1"
				  + "ORDER BY SEMANA DESC)"
				  + "NATURAL INNER JOIN"
				  + "(SELECT cuenta-c1,semana AS SEMANA "
				  + "FROM(SELECT COUNT(DISTINCT c.ID_USUARIO) c1, to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY') AS SEMANA"
				  + "FROM CITA c"
				  + "WHERE c.ESTADO_CITA = 'REALIZADA' "
				  + "GROUP BY to_char(to_date(c.FECHA, 'MM/DD/YYYY'), 'WW/YYYY')"
				  + "ORDER BY semana DESC), (SELECT COUNT(*) AS cuenta FROM AFILIADO"
				  + ")"
				  + ")";
		Query q = pm.newQuery(SQL,sql);
		return q.executeList();
	}
	
}