package uniandes.isis2304.epsAndes.negocio;


/**
 * Interfaz para los métodos get de SERVICIODESALUD.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */
public interface VOServicioDeSalud 
{

	/**
	 * @return El id del servicio de salud
	 */
	public long getId();
	
	/**
	 * @return la capacidad del servicio
	 */
	public int getCapacidadMaxima();
	
	/**
	 * @return los dias del servicio
	 */
	public int getDiasSemana();
	
	/**
	 * @return dia de realizacion del servicio
	 */
	public String getFechaRealizacion();

	/**
	 * @return el horario del servicio
	 */
	public String getHorario();
	
	/**
	 * @return El tipo de servicio
	 */
	public String getTipoServicio();
	
	
}
