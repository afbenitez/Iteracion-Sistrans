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

package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.persistencia.PersistenciaEps;


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
	
	public Usuario adicionarUsuario (long id, String email,  String nombre, long cedula, int rol , String tipoId)
	{
        log.info ("Adicionando usuario: " + nombre);
        Usuario usuario = pp.adicionarUsuario(id, email, nombre, cedula, rol, tipoId);
        log.info ("Adicionando usuario: " + usuario);
        return usuario;
	}
	
	public Ips adicionarIps (long id, String email,  String nombre, String tipo, String ubicacion)
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
	
	public Servicio adicionarAfiliado(long id, String email,  String nombre, long cedula, int rol , String tipoId, String fecha)
	{
        log.info ("Adicionando afiliado: " + nombre);
        Afiliado afiliado = pp.adicionarAfiliado(id, email, nombre, cedula, rol, tipoId, fecha);
        log.info ("Adicionando afiliado: " + afiliado);
        return afiliado;
	} 
	
	
	

	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarParranderos ()
	{
        log.info ("Limpiando la BD de Parranderos");
        long [] borrrados = pp.limpiarParranderos();	
        log.info ("Limpiando la BD de Parranderos: Listo!");
        return borrrados;
	}
}

