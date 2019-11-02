package uniandes.isis2304.parranderos.negocio;

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
	public String getEstadoCita();
	
	/**
	 * @return La fecha de la cita
	 */
	public String getFecha();
	
	/**
	 * @return La lista servicios de la cita
	 */
	public List<Object []> getServicios();
	
	/**
	 * @return el medico de la cita
	 */
	public Medico getMedico();
	
	/**
	 * @return el afiliado de la cita
	 */
	public Afiliado getAfiliado();
}
