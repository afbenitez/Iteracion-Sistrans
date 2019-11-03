package uniandes.isis2304.epsAndes.negocio;

public class Prestan {
	private int dia;
	private int horario;
	private String id_servicio;
	private String id_ips;
	private long capacidad;
	private long capacidad_max;
	private int estado;
	
	public Prestan(){
		this.dia = 0;
		this.horario = 0;
		this.capacidad = 0;
		this.estado = 0;
		this.id_ips = "";
		this.id_servicio = "";
		this.capacidad_max = 0;
	}

	/**
	 * @param dia
	 * @param horario
	 * @param id_servicio
	 * @param id_ips
	 * @param capacidad
	 * @param estado
	 */
	public Prestan(int dia, int horario, String id_servicio, String id_ips, long capacidad, long capacidad_max, int estado) {
		super();
		this.dia = dia;
		this.horario = horario;
		this.id_servicio = id_servicio;
		this.id_ips = id_ips;
		this.capacidad = capacidad;
		this.capacidad_max = capacidad_max;
		this.estado = estado;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}

	public String getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(String id_servicio) {
		this.id_servicio = id_servicio;
	}

	public String getId_ips() {
		return id_ips;
	}

	public void setId_ips(String id_ips) {
		this.id_ips = id_ips;
	}

	public long getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(long capacidad) {
		this.capacidad = capacidad;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public long getCapacidad_max() {
		return capacidad_max;
	}

	public void setCapacidad_max(long capacidad_max) {
		this.capacidad_max = capacidad_max;
	}
	
	
}
