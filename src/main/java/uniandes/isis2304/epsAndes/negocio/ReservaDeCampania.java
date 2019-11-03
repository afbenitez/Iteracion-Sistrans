package uniandes.isis2304.epsAndes.negocio;

public class ReservaDeCampania {
	
	private String id_servicio;
	private String id_campania;
	private String fecha_inicio;
	private String fecha_fin;
	private int capacidad;
	private int capacidad_maxima;
	
	public ReservaDeCampania()
	{
		this.id_servicio = "";
		this.id_campania= "";
		this.fecha_fin = "";
		this.fecha_inicio = "";
		this.capacidad = 0;
		this.capacidad_maxima = 0;
	}

	public ReservaDeCampania(String id_servicio, String id_campania, String fecha_inicio, String fecha_fin,
			int capacidad, int capacidad_maxima) {
		super();
		this.id_servicio = id_servicio;
		this.id_campania = id_campania;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.capacidad = capacidad;
		this.capacidad_maxima = capacidad_maxima;
	}

	public String getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(String id_servicio) {
		this.id_servicio = id_servicio;
	}

	public String getId_campania() {
		return id_campania;
	}

	public void setId_campania(String id_campania) {
		this.id_campania = id_campania;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getCapacidad_maxima() {
		return capacidad_maxima;
	}

	public void setCapacidad_maxima(int capacidad_maxima) {
		this.capacidad_maxima = capacidad_maxima;
	}
}	
