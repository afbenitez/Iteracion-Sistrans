package uniandes.isis2304.parranderos.negocio;

public class Gerente {
	
	private long id;

	private String email;

	private String nombre;

	private String numeroIdentificacion;

	private String rol;

	private String tipoIdentificacion;
	
	private Eps eps;
	
	public Gerente()
	{
		this.id = 0;
		this.email = "";
		this.nombre = "";
		this.numeroIdentificacion = "";
		this.rol = "";
		this.tipoIdentificacion = "";
		this.eps = null;
	}

	public Gerente(long id, String email, String nombre, String numeroIdentificacion, String rol,
			String tipoIdentificacion, Eps eps) {
		super();
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.numeroIdentificacion = numeroIdentificacion;
		this.rol = rol;
		this.tipoIdentificacion = tipoIdentificacion;
		this.eps = eps;
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

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}
	
	

}
