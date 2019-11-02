package uniandes.isis2304.parranderos.negocio;


/**
 * Interfaz para los métodos get de RECETAMEDICA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */
public interface VORecetaMedica 
{

	/**
	 * @return El id de la receta
	 */
	public long getId();

	/**
	 * @return Los medicamentos
	 */
	public String getMedicamentos();
	
	/**
	 * @return El afiliado de la receta
	 */
	public Afiliado getAfiliado();
	
	/**
	 * @return El medico de la receta
	 */
	public Medico getMedico();
	
	/**
	 * @return La orden de la receta
	 */
	public OrdenMedica getOrdenMedica();

	/**
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
