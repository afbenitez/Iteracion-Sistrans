package uniandes.isis2304.epsAndes.negocio;

public class Ips   {
	
	
	private long id;
	
	private String nombre;
	
	private String tipo_ips;
	
	private String ubicacion;
	

	public Ips()
	{
		this.id = 0;
		this.nombre = "";
		this.tipo_ips = "";
		this.ubicacion = "";

		
	}

	public Ips(long id, String nombre, String tipo_ips, String ubicacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo_ips = tipo_ips;
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

	public String getTipo_ips() {
		return tipo_ips;
	}

	public void setTipo_ips(String tipo_ips) {
		this.tipo_ips = tipo_ips;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}



	
	
	
	
}
