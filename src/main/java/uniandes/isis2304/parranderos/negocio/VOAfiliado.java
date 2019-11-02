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
 * Interfaz para los métodos get de AFILIADO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */
public interface VOAfiliado 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * @return El id del Afiliado
	 */
	public long getId();

	/**
	 * @return La fecha de nacimiento del afiliado
	 */
	public String getFechaNacimiento();
	
	/**
	 * @return El nombre del Afiliado
	 */
	public String getEmail();

	/**
	 * @return El id del Afiliado
	 */
	public String getNombre();

	/**
	 * @return El numero de identificacion del Afiliado
	 */
	public String getNumeroIdentificacion();
	
	/**
	 * @return El rol de Afiliado
	 */
	public String getRol();

	/**
	 * @return El  tipo de identificacion del Afiliado
	 */
	public String getTipoIdentificacion();
	
	/**
	 * @return la eps del Afiliado
	 */
	public Eps getEps();
	
	/**
	 * @return La lista de citas del Afiliado
	 */
	public List<Object []> getCitas();
	
	/**
	 * @return La lista de recetas del Afiliado
	 */
	public List<Object []> getRecetas();

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();

}
