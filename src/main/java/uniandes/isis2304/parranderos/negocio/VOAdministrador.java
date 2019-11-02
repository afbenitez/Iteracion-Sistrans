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

import java.util.List;

/**
 * Interfaz para los métodos get de ADMINITRADOR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */
public interface VOAdministrador 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * @return El id del Administrador
	 */
	public long getId();

	/**
	 * @return La fecha de nacimiento del Administrador
	 */
	public long getFechaNacimiento();
	
	/**
	 * @return El nombre del Administrador
	 */
	public String getEmail();

	/**
	 * @return El id del Administrador
	 */
	public String getIdNombre();

	/**
	 * @return El numero de identificacion del Administrador
	 */
	public String getNumeroIdentificacion();
	
	/**
	 * @return El rol de Administrador
	 */
	public String getRol();

	/**
	 * @return El  tipo de identificacion del Administrador
	 */
	public String getTipoIdentificacion();
	
	/**
	 * @return la eps del Administrador
	 */
	public Eps getEps();

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
}
