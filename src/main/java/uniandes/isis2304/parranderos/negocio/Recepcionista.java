package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Recepcionista implements VORecepcionista {

	private long id;
	
	private String email;
	
	private String nombre;
	
	private String numeroIdentificacion;
	
	private String rol;
	
	private String tipoIdentificacion;
	
	private Ips ips;
	
	private List<Object []> citas;


	public Recepcionista()
	{
		this.id = 0;
		this.email = "";
		this.nombre = "";
		this.numeroIdentificacion = "";
		this.rol = "";
		this.tipoIdentificacion = "";
		this.ips = null;
		this.citas = new LinkedList<>();
	}

	
	

	public Recepcionista(long id, String email, String nombre, String numeroIdentificacion, String rol,
			String tipoIdentificacion, Ips ips, List<Object[]> citas) {
		super();
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.numeroIdentificacion = numeroIdentificacion;
		this.rol = rol;
		this.tipoIdentificacion = tipoIdentificacion;
		this.ips = ips;
		this.citas = citas;
	}



	public Ips getIps() {
		return ips;
	}

	public void setIps(Ips ips) {
		this.ips = ips;
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




	public List<Object[]> getCitas() {
		return citas;
	}




	public void setCitas(List<Object[]> citas) {
		this.citas = citas;
	}
	
	
	
}
