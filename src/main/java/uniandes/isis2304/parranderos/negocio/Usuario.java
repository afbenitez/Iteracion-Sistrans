package uniandes.isis2304.parranderos.negocio;

public class Usuario {
	
	private long id;
	private String email;
	private String nombre;
	private String numero_id;
	private int rol;
	private String tipo_identificacion;
	
	public Usuario(){
		this.id = 0;
		this.email = "";
		this.nombre = "";
		this.numero_id = "";
		this.rol = 0;
		this.tipo_identificacion = "";
	}

	public Usuario(long id, String email, String nombre, String numero_id, int rol, String tipo_identificacion) {
		super();
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.numero_id = numero_id;
		this.rol = rol;
		this.tipo_identificacion = tipo_identificacion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumero_id() {
		return numero_id;
	}

	public void setNumero_id(String numero_id) {
		this.numero_id = numero_id;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getTipo_identificacion() {
		return tipo_identificacion;
	}

	public void setTipo_identificacion(String tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}
	
	
}
