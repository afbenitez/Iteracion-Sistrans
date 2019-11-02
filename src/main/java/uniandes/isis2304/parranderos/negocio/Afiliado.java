package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Afiliado implements VOAfiliado {

	private long id;

	private String email;

	private String nombre;

	private String numeroIdentificacion;

	private String rol;

	private String tipoIdentificacion;
	
	private String fechaNacimiento;

	private Eps eps;
	
	private List<Object []> recetas;
	
	private List<Object []> citas;
	
	public Afiliado()
	{
		this.id = 0;
		this.email = "";
		this.fechaNacimiento = "";
		this.nombre = "";
		this.rol = "";
		this.tipoIdentificacion = "";
		this.numeroIdentificacion = "";
		this.eps = null;
		this.recetas = new LinkedList<>();
		this.citas = new LinkedList<>(); 
	}

	public Afiliado(long id, String email, String nombre, String numeroIdentificacion, String rol,
			String tipoIdentificacion, String fechaNacimiento, Eps eps, List<Object[]> recetas, List<Object[]> citas) {
		super();
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.numeroIdentificacion = numeroIdentificacion;
		this.rol = rol;
		this.tipoIdentificacion = tipoIdentificacion;
		this.fechaNacimiento = fechaNacimiento;
		this.eps = eps;
		this.recetas = recetas;
		this.citas = citas;
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public List<Object[]> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Object[]> recetas) {
		this.recetas = recetas;
	}

	public List<Object[]> getCitas() {
		return citas;
	}

	public void setCitas(List<Object[]> citas) {
		this.citas = citas;
	}
	
	
	
	

}
