package uniandes.isis2304.parranderos.negocio;

import java.util.List;

/**
 * Interfaz para los métodos get de ORDENMEDICA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */
public interface VOOrdenMedica 
{

	/**
	 * @return El id de la receta
	 */
	public long getId();

	/**
	 * @return La fecha de la orden
	 */
	public String getFecha();
	
	/**
	 * @return La lista de las recetas de la orden
	 */
	public long getId_recetas();
	
	/**
	 * @return La lista de servicios de la roden
	 */
	public long getId_servicio();

	/**
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
