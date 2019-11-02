package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Medico implements VOMedico{

	private long id;

	private String email;

	private String nombre;

	private String numeroIdentificacion;

	private String rol;

	private String tipoIdentificacion;

	private String especialidad;

	private String numeroRegistro;

	private List<Object []> ips;
	
	private List<Object []> citas;

	private List<Object []> recetasMedicas;


	public Medico()
	{
		this.id = 0;
		this.email = "";
		this.nombre = "";
		this.numeroIdentificacion = "";
		this.rol = "";
		this.tipoIdentificacion = "";
		this.ips = new LinkedList<>();
		this.citas = new LinkedList<>();
		this.recetasMedicas = new LinkedList<>();
	}

	

	public Medico(long id, String email, String nombre, String numeroIdentificacion, String rol,
			String tipoIdentificacion, String especialidad, String numeroRegistro, List<Object[]> ips,
			List<Object[]> citas, List<Object[]> recetasMedicas) {
		super();
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.numeroIdentificacion = numeroIdentificacion;
		this.rol = rol;
		this.tipoIdentificacion = tipoIdentificacion;
		this.especialidad = especialidad;
		this.numeroRegistro = numeroRegistro;
		this.ips = ips;
		this.citas = citas;
		this.recetasMedicas = recetasMedicas;
	}



	public String getEspecialidad() {
		return especialidad;
	}



	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}



	public String getNumeroRegistro() {
		return numeroRegistro;
	}



	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
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



	public List<Object[]> getIps() {
		return ips;
	}



	public void setIps(List<Object[]> ips) {
		this.ips = ips;
	}



	public List<Object[]> getCitas() {
		return citas;
	}



	public void setCitas(List<Object[]> citas) {
		this.citas = citas;
	}



	public List<Object[]> getRecetasMedicas() {
		return recetasMedicas;
	}



	public void setRecetasMedicas(List<Object[]> recetasMedicas) {
		this.recetasMedicas = recetasMedicas;
	}
	
	

}
