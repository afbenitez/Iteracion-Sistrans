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

/**
 * Interfaz para los métodos get de GERENTE.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */
public interface VOGerente 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * @return El id del Gerente
	 */
	public long getId();
	
	/**
	 * @return El nombre del Gerente
	 */
	public String getEmail();

	/**
	 * @return El id del Gerente
	 */
	public String getNombre();

	/**
	 * @return El numero de identificacion del Gerente
	 */
	public String getNumero_id();
	
	/**
	 * @return El rol de Gerente
	 */
	public String getRol();

	/**
	 * @return El  tipo de identificacion del Gerente
	 */
	public String getTipo_identificacion();
	
	/**
	 * @return la eps del Gerente
	 */
	public long getId_eps();

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
}
