package uniandes.isis2304.epsAndes.negocio;

public class Rol {
	
	private long id;
	
	private String nombre2;
	
	public Rol(){
		this.id = 0;
		this.nombre2 = "";
	}
	
	public Rol(long id, String nombre2){
		this.nombre2 = nombre2;
		this.id = id;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
