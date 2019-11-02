package uniandes.isis2304.parranderos.negocio;

public class Eps {
	
	private long id;
	
	private String nombre;
	
	public Eps()
	{
		this.id = 0;
		this.nombre = "";	
	}
	
	public Eps(long id, String nombre)
	{
		this.id = id;
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "Eps [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
