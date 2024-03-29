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
	public String getFecha_nacimiento();
	
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
	public String getNumero_id();
	
	/**
	 * @return El rol de Afiliado
	 */
	public String getRol();

	/**
	 * @return El  tipo de identificacion del Afiliado
	 */
	public String getTipo_identificacion();
	
	/**
	 * @return la eps del Afiliado
	 */
	public long getId_eps();
	
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
