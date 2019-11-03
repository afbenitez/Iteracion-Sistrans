package uniandes.isis2304.epsAndes.negocio;

public class ServicioDeSalud {
	
	private long id;
	
	private String nombre;
	
	private String tipo_servicio;
	
	public ServicioDeSalud() {
		this.id = 0;
		this.nombre = "";
		this.tipo_servicio = "";
	}


	/**
	 * @param id
	 * @param capacidad_maxima
	 * @param dias_semana
	 * @param fecha_realizacion
	 * @param horario
	 * @param tipo_servicio
	 */
	public ServicioDeSalud(long id, String nombre, String tipo_servicio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo_servicio = tipo_servicio;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getTipo_servicio() {
		return tipo_servicio;
	}


	public void setTipo_servicio(String tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	
}
