package uniandes.isis2304.parranderos.negocio;

public class Trabajan {
	
	private String nombre_ips;
	private long id_medico;
	
	public Trabajan()
	{
		this.nombre_ips = "";
		this.id_medico = 0;
	}

	/**
	 * @param nombre_ips
	 * @param id_medico
	 */
	public Trabajan(String nombre_ips, long id_medico) {
		super();
		this.nombre_ips = nombre_ips;
		this.id_medico = id_medico;
	}


	public long getId_medico() {
		return id_medico;
	}

	public void setId_medico(long id_medico) {
		this.id_medico = id_medico;
	}

	public String getNombre_ips() {
		return nombre_ips;
	}

	public void setNombre_ips(String nombre_ips) {
		this.nombre_ips = nombre_ips;
	}
	
	
}
