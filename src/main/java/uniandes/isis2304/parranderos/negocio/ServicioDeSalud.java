package uniandes.isis2304.parranderos.negocio;

public class ServicioDeSalud {
	
	private long id;
	
	private int capacidad_maxima;
	
	private int dias_semana;
	
	private String fecha_realizacion;
	
	private int horario;
	
	private String tipo_servicio;
	
	public ServicioDeSalud() {
		this.id = 0;
		this.capacidad_maxima = 0;
		this.dias_semana = 0;
		this.fecha_realizacion = "";
		this.horario = 0;
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
	public ServicioDeSalud(long id, int capacidad_maxima, int dias_semana, String fecha_realizacion, int horario,
			String tipo_servicio) {
		super();
		this.id = id;
		this.capacidad_maxima = capacidad_maxima;
		this.dias_semana = dias_semana;
		this.fecha_realizacion = fecha_realizacion;
		this.horario = horario;
		this.tipo_servicio = tipo_servicio;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getCapacidad_maxima() {
		return capacidad_maxima;
	}


	public void setCapacidad_maxima(int capacidad_maxima) {
		this.capacidad_maxima = capacidad_maxima;
	}


	public int getDias_semana() {
		return dias_semana;
	}


	public void setDias_semana(int dias_semana) {
		this.dias_semana = dias_semana;
	}


	public String getFecha_realizacion() {
		return fecha_realizacion;
	}


	public void setFecha_realizacion(String fecha_realizacion) {
		this.fecha_realizacion = fecha_realizacion;
	}


	public int getHorario() {
		return horario;
	}


	public void setHorario(int horario) {
		this.horario = horario;
	}


	public String getTipo_servicio() {
		return tipo_servicio;
	}


	public void setTipo_servicio(String tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
	}



	
}
