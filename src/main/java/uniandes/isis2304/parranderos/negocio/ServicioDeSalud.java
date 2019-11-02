package uniandes.isis2304.parranderos.negocio;

public class ServicioDeSalud implements VOServicioDeSalud {
	
	private long id;
	
	private int capacidadMaxima;
	
	private int diasSemana;
	
	private String fechaRealizacion;
	
	private String horario;
	
	private String tipoServicio;
	
	public ServicioDeSalud() {
		this.id = 0;
		this.capacidadMaxima = 0;
		this.diasSemana = 0;
		this.fechaRealizacion = "";
		this.horario = "";
		this.tipoServicio = "";
	}

	public ServicioDeSalud(long id, int capacidadMaxima, int diasSemana, String fechaRealizacion, String horario,
			String tipoServicio) {
		super();
		this.id = id;
		this.capacidadMaxima = capacidadMaxima;
		this.diasSemana = diasSemana;
		this.fechaRealizacion = fechaRealizacion;
		this.horario = horario;
		this.tipoServicio = tipoServicio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public int getDiasSemana() {
		return diasSemana;
	}

	public void setDiasSemana(int diasSemana) {
		this.diasSemana = diasSemana;
	}

	public String getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(String fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
	
}
