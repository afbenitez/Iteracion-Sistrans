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

import java.util.List;

/**
 * Interfaz para los métodos get de MEDICO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */
public interface VOMedico 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * @return La especialidad del medico
	 */
	public String getEspecialidad();

	/**
	 * @return El numero de registro del Medico
	 */
	public String getNumero_registro();

	/**
	 * @return El id del Medico
	 */
	public long getId();

	/**
	 * @return El nombre del Medico
	 */
	public String getEmail();

	/**
	 * @return El id del Recepcionista
	 */
	public String getNombre();

	/**
	 * @return El numero de identificacion del Medico
	 */
	public String getNumero_id();
	
	/**
	 * @return El rol de Medico
	 */
	public String getRol();

	/**
	 * @return El  tipo de identificacion del Medico
	 */
	public String getTipo_identificacion();
	
	/**
	 * @return La lista de ips del Medico
	 */
	public long getId_ips();
	
	/**
	 * @return La lista de citas del Medico
	 */
	public long getId_citas();
	
	/**
	 * @return La lista de recetas del Medico
	 */
	public long getId_recetasmedicas();
	
	/**
	 * @return Una cadena con la información básica del Medico
	 */
	@Override
	public String toString();
	
}
