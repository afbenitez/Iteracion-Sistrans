package uniandes.isis2304.epsAndes.persistencia;

public class SQLCampania {
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

	/* **********************
	 * 			MÃ©todos
	 ***********************/
	/**
	 * Constructor.
	 *
	 * @param pp - El Manejador de persistencia de la aplicaciÃ³n
	 */
	public SQLCampania (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una CITA a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param cumplida el estado cumplida
	 * @param id el id
	 * @param fecha la fecha
	 * @param id_Servicio el id servicio
	 * @param id_Afiliado el id afiliado
	 * @param id_Recepcionista el id recepcionista
	 * @param hora la hora
	 * @return EL nÃºmero de tuplas insertadas
	 */
	//	public long crearCampania(PersistenceManager pm, String nada) 
	//	{
	//		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCita() + "(CUMPLIDA, ID_ORDEN , FECHA, ID_SERVICIO, ID_AFILIADO, ID_RECEPCIONISTA, HORA) values (?,?, ?, ?, ?, ?, ?)");
	//		q.setParameters(cumplida,id,fecha,id_Servicio,id_Afiliado,id_Recepcionista,hora);
	//		return (long) q.executeUnique();            
	//	}

	/**
	 * Registrar prestacion.
	 *
	 * @param pm the pm
	 * @param idCita el id cita
	 * @param id_Recepcionista el id recepcionista
	 * @return the long
	 */
	public long registrarCampania(PersistenceManager pm, String nombre, String fechaFin, String fechaInicio,String idOrganizador)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCampania()+ " (NOMBRE,FECHAFIN,FECHAINICIO,ID_ORGANIZADOR) VALUES (?,?,?,?)");
		q.setParameters(nombre,fechaFin,fechaInicio,idOrganizador);
		return (long) q.executeUnique();   
	}

	public long registrarServCamp(PersistenceManager pm, int capacidadF, String idServ, String idCamp,String fechaIni,String fechaFin)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioCampania()+ " (CAPACIDADF,ID_SERVICIO,ID_CAMPANIA,CAPACIDADINI,FECHAINI,FECHAFIN) VALUES (?,?,?,?,?,?)");
		q.setParameters(capacidadF,idServ,idCamp,capacidadF,fechaIni,fechaFin );
		return (long) q.executeUnique();   
	}

	public long eliminarServicioCampania(PersistenceManager pm,String idServ, String idCamp)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioCampania()+ " WHERE ID_SERVICIO=? AND ID_CAMPANIA=?");
		q.setParameters(idServ,idCamp);
		return (long) q.executeUnique();   
	}

	public BigDecimal darCapacidad(PersistenceManager pm,String idServ, String idCamp)
	{
		Query q = pm.newQuery(SQL, "SELECT CAPACIDADINI FROM " + pp.darTablaServicioCampania() + " WHERE ID_SERVICIO=? AND ID_CAMPANIA=?");
		q.setParameters(idServ,idCamp);
		return (BigDecimal) q.executeUnique();
	}

	public List<Object[]> darCampania(PersistenceManager pm,String idServ, String idCamp)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioCampania() + " WHERE ID_SERVICIO=? AND ID_CAMPANIA=?");
		q.setParameters(idServ,idCamp);
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
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCampania()+" WHERE ID_CAMPANIA=?");
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
