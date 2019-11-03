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
 * Interfaz para los métodos get de EPS.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author af.benitez
 */
public interface VOEps 
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id de la eps
	 */
	public long getId();
	
	/**
	 * @return el nombre de la eps
	 */
	public String getNombre();
	
	/**
	 * @return La lista de afiliados
	 */
	public long getId_afiliados();
	
	public long getId_ips();
	
	/**
	 * @return El administrador de la eps
	 */
	public long getId_administrador();
	
	/**
	 * @return El gerente de la eps
	 */
	public long getId_gerente();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}
