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
 * Interfaz para los métodos get de RECEPCIONISTA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */
public interface VORecepcionista 
{
	/**
	 * @return El id del Recepcionista
	 */
	public long getId();

	/**
	 * @return El nombre del Recepcionista
	 */
	public String getEmail();

	/**
	 * @return El id del Recepcionista
	 */
	public String getNombre();

	/**
	 * @return El numero de identificacion del Recepcionista
	 */
	public String getNumeroIdentificacion();
	
	/**
	 * @return El rol de Recepcionista
	 */
	public String getRol();

	/**
	 * @return El  tipo de identificacion del Recepcionista
	 */
	public String getTipoIdentificacion();
	
	/**
	 * @return la ips del Recepcionista
	 */
	public Ips getIps();
	
	/**
	 * @return La lista de citas del Recepcionista
	 */
	public List<Object []> getCitas();
	
	/**
	 * @return Una cadena con la información básica del Recepcionista
	 */
	@Override
	public String toString();

}
