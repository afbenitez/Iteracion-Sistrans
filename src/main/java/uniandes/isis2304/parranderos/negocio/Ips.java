package uniandes.isis2304.parranderos.negocio;

public class Ips {
	
	public enum TipoIPS {
		HOSPITAL,
		CENTRO_DIAGNOSTICO,
		LABORATORIO,
		CENTRO_MEDICO
	}
	
	private long id;
	
	private String nombre;
	
	private String tipoIPS;
	
	private String ubicacion;

	public Ips()
	{
		this.id = 0;
		this.nombre = "";
		this.tipoIPS = "";
		this.ubicacion = "";
	}

	public Ips(long id, String nombre, String tipoIPS, String ubicacion) {
		this.id = id;
		this.nombre = nombre;
		this.tipoIPS = tipoIPS;
		this.ubicacion = ubicacion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoIPS() {
		return tipoIPS;
	}

	public void setTipoIPS(String tipoIPS) {
		this.tipoIPS = tipoIPS;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
	
}
