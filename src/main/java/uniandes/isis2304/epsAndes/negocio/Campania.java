package uniandes.isis2304.epsAndes.negocio;

public class Campania {
	
	private String nombre;
	private String fecha_inicio;
	private String fecha_fin;
	private String id_organizador;
	
	public Campania()
	{
		this.nombre = "";
		this.fecha_fin = "";
		this.fecha_inicio = "";
		this.id_organizador = "";
	}

	public Campania(String nombre, String fecha_inicio, String fecha_fin, String id_organizador) {
		super();
		this.nombre = nombre;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.id_organizador = id_organizador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getId_organizador() {
		return id_organizador;
	}

	public void setId_organizador(String id_organizador) {
		this.id_organizador = id_organizador;
	}
	
	
}
