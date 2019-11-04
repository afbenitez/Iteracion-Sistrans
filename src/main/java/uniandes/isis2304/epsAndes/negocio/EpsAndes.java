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

package uniandes.isis2304.epsAndes.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.epsAndes.persistencia.PersistenciaEps;


/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio

 */
public class EpsAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(EpsAndes.class.getName());

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaEps pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public EpsAndes ()
	{
		pp = PersistenciaEps.getInstance ();
	}

	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public EpsAndes (JsonObject tableConfig)
	{
		pp = PersistenciaEps.getInstance (tableConfig);
	}

	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}

	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE BEBIDA
	 *****************************************************************/

	public Rol adicionarRolesDeUsuario (long id, String nombre)
	{
		log.info ("Adicionando rol: " + nombre);
		Rol rol = pp.adicionarRolesDeUsuario(id, nombre);	
		log.info ("Adicionando rol: " + rol);
		return rol;
	}

	public Usuario adicionarAdministrador(long id, String email,  String nombre, long cedula, int rol , String tipoId)
	{
		log.info ("Adicionando Administrador: " + nombre);
		Usuario usuario = pp.adicionarAdministrador(id, email, nombre, cedula, rol, tipoId);
		log.info ("Adicionando Administrador: " + usuario);
		return usuario;
	}

	public Usuario adicionarGerente(long id, String email,  String nombre, long cedula, int rol , String tipoId)
	{
		log.info ("Adicionando Gerente: " + nombre);
		Usuario usuario = pp.adicionarGerente(id, email, nombre, cedula, rol, tipoId);
		log.info ("Adicionando Gerente: " + usuario);
		return usuario;
	}

	public Recepcionista adicionarRecepcionista(long id, String email,  String nombre, long cedula, int rol , String tipoId, String ips)
	{
		log.info ("Adicionando recepcionista: " + nombre);
		Recepcionista recepcionista = pp.adicionarRecepcionista(id, email, nombre, cedula, rol, tipoId, ips);
		log.info ("Adicionando recepcionista: " + recepcionista);
		return recepcionista;
	}

	public Ips adicionarIps (long id,  String nombre, String tipo, String ubicacion)
	{
		log.info ("Adicionando ips: " + nombre);
		Ips ips = pp.adicionarIps(id, nombre, tipo, ubicacion);
		log.info ("Adicionando ips: " + ips);
		return ips;
	}

	public Medico adicionarMedico (long id, String email,  String nombre, long cedula, int rol , String tipoId, String especialidad, String numeroRegistro)
	{
		log.info ("Adicionando Medico: " + nombre);
		Medico medico = pp.adicionarMedico(id, email, nombre, cedula, rol, tipoId, especialidad, numeroRegistro);
		log.info ("Adicionando Medico: " + medico);
		return medico;
	}

	public Afiliado adicionarAfiliado(long id, String email,  String nombre, long cedula, int rol , String tipoId, String fecha)
	{
		log.info ("Adicionando afiliado: " + nombre);
		Afiliado afiliado = pp.adicionarAfiliado(id, email, nombre, cedula, rol, tipoId, fecha);
		log.info ("Adicionando afiliado: " + afiliado);
		return afiliado;
	}

	public Prestan adicionarServicioDeSalud( int dia, int horario, String idServicio, String idIps, long capacidad, long capacidadMax ,int estado) 
	{
		log.info ("Adicionando servicio a ips: " + idIps);
		Prestan servicioDeSalud = pp.adicionarServicioDeSalud( dia, horario, idServicio, idIps, capacidad, capacidadMax, estado);
		log.info ("Adicionando servicio a ips: " + idIps);
		return servicioDeSalud;
	} 

	public RecetaMedica adicionarRecetaMedica(long id, String  fecha, long idMedico , long idUsuario, String idServicio, String medicamentos) 
	{
		log.info ("Adicionando orden medica: " + idServicio);
		RecetaMedica recetaMedica = pp.adicionarRecetaMedica(id, fecha, idMedico, idUsuario, idServicio, medicamentos);
		log.info ("Adicionando orden medica: " + recetaMedica);
		return recetaMedica;
	}

	public Cita adicionarCita(long id, long idReceta, long idUsuario, long idRecepcionista, String idServicio, String estado, String fecha, int horario) 
	{
		log.info ("Adicionando cita: " + idServicio);
		Cita cita = pp.adicionarCita(id, idReceta, idUsuario, idRecepcionista, idServicio, estado, fecha, horario);
		log.info ("Adicionando cita: " + cita);
		return cita;
	}

	public void registrarPrestacion(long id,  long idRecepcionista, String idServicio, long idPaciente)
	{
		log.info ("Registrando prestacion de servicio: " + idServicio);
		pp.registrarPrestacion(id, idRecepcionista, idServicio, idPaciente);
		log.info ("Registrando prestacion de servicio: " + idServicio);

	}
	
	public OrganizadorDeCampania adicionarOrganizadorDeCampania (long id, String email,  String nombre, long cedula, int rol , String tipoId)
	{
		log.info ("Adicionando organizador: " + nombre);
		OrganizadorDeCampania organizador = pp.adicionarOrganizador(id, email, nombre, cedula, rol, tipoId);
		log.info ("Adicionando organizador: " + organizador);
		return organizador;
	}
	
	
	public boolean existeAdministrador(long id)
	{
		return pp.darAdministradorPorId(id) != null ?true:false;
	}

	public boolean existeRol(long id)
	{
		return pp.darRolPorId(id) != null ?true:false;
	}

	public boolean existeAfiliado(long id)
	{
		return pp.darAfiliadoPorId(id) != null ?true:false;
	}

	public boolean existeGerente(long id)
	{
		return pp.darGerentePorId(id) != null ?true:false;
	}

	public boolean existeRecepcionista(long id)
	{
		return pp.darRecepcionistaPorId(id) != null ?true:false;
	}

	public boolean existeIps(String nombre)
	{
		return pp.darIpsPorNombre(nombre) != null ?true:false;
	}

	public boolean existeMedico(long id)
	{
		return pp.darMedicoPorId(id) != null ?true:false;
	}

	public Cita darCitaPorId(long id)
	{
		return pp.darCitaPorId(id);
	}

	//R10
	public Campania registrarCampania(String nombre, String fechaInicio, String fechaFin, String idOrganizador)
	{
		log.info ("Adicionando campania: " + nombre);
		Campania campania = pp.registrarCampania(nombre, fechaInicio, fechaFin, idOrganizador);
		log.info ("Adicionando campania: " + campania);
		return campania;
	}

	public void registrarServCamp( String idServicio, String idCampania,String fechaInicio,String fechaFin, int capacidad, int capacidadMax)
	{
		log.info ("Adicionando servicio a la campania: " + idCampania);
		pp.registrarServCamp(idServicio, idCampania, fechaInicio, fechaFin, capacidad, capacidadMax);
		log.info ("Adicionando servicio a la campania: " + idCampania);
	}
	
	//R11
	public void cancelarServicioCampania(String campania, String servicio)
	{
		log.info ("Cancelando campania: " + campania);
		pp.cancelarServicioCampania(campania, servicio);
		log.info ("Cancelando campania: " + campania);
		
	}
	
	//R12
	public String deshabilitarServicios(String fechaIni,String fechaFin,String ips,String idServicio)
	{
		log.info ("Deshabilitando servicios: " + idServicio);
		String error = pp.deshabilitarServicios(fechaIni, fechaFin, ips, idServicio);
		log.info ("Deshabilitando servicios: " + idServicio);
		return error;
	}
	
	public void habilitarServicios(String fechaIni,String fechaFin,String ips,String idServicio)
	{
		log.info ("Habilitar servicios: " + idServicio);
		pp.habilitarServicios(fechaIni, fechaFin, ips, idServicio);
		log.info ("Habilitar servicios: " + idServicio);
	}
	
	


}

