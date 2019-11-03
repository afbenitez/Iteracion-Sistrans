package uniandes.isis2304.epsAndes.negocio;

import java.util.List;

/**
 * Interfaz para los métodos get de CITA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */
public interface VOCita 
{
	/**
	 * @return El id de la cita
	 */
	public long getId();
	
	/**
	 * @return El estado de la cita
	 */
	public String getEstado_cita();
	
	/**
	 * @return La fecha de la cita
	 */
	public String getFecha();
	
	/**
	 * @return La lista servicios de la cita
	 */
	public long getId_servicio();
	
	/**
	 * @return el medico de la cita
	 */
	public long getId_medico();
	
	/**
	 * @return el afiliado de la cita
	 */
	public long getId_afiliado();
}
