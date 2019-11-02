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

package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaParranderos.SQL;

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
	public SQLUtil (PersistenciaEps pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqEpsAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarParranderos (PersistenceManager pm)
	{
        Query qAdministrador = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAdministradorDatos());          
        Query qAfliado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAfiliado());
        Query qCita = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCita());
        Query qEps = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEps());
        Query qGerente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaGerente());
        Query qIps = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaIps());
        Query qMedico = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaMedico());
        Query qRecepcionista = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRecepcionista());
        Query qReceta = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRecetaMedica());
        Query qRol = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRol());
        Query qServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioDeSalud());
        Query qTerapia = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioTerapia());
        Query qTrabajan = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTrabajan());
        Query qUsuarios = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario());
        
        
        long administradoresEliminados = (long) qAdministrador.executeUnique ();
        long afiliadosEliminados = (long) qAfliado.executeUnique ();
        long citasEliminadas = (long) qCita.executeUnique ();
        long epsEliminadas = (long) qEps.executeUnique ();
        long gerenteEliminados = (long) qGerente.executeUnique ();
        long ipsEliminados = (long) qIps.executeUnique ();
        long medicosEliminados = (long) qMedico.executeUnique ();
        long recepcionistaEliminadas = (long) qRecepcionista.executeUnique ();
        long recetaEliminadas = (long) qReceta.executeUnique ();
        long rolesEliminados = (long) qRol.executeUnique ();
        long serviciosEliminados = (long) qServicio.executeUnique ();
        long terapiasEliminados = (long) qTerapia.executeUnique ();
        long trabajanEliminados = (long) qTrabajan.executeUnique ();
        long usuariosEliminados = (long) qUsuarios.executeUnique ();
        
        return new long[] {administradoresEliminados, afiliadosEliminados, citasEliminadas, epsEliminadas,gerenteEliminados, ipsEliminados, medicosEliminados, 
        		recepcionistaEliminadas, recetaEliminadas, rolesEliminados,serviciosEliminados, terapiasEliminados,trabajanEliminados, usuariosEliminados };
	}

}
