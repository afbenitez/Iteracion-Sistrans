package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Eps implements VOEps {
	
	private long id;
	
	private String nombre;
	
	private Gerente gerente;
	
	private AdministradorDatos administrador;
	
	private List<Object []> ips;
	
	private List<Object []> afiliados;
	
	public Eps()
	{
		this.id = 0;
		this.nombre = "";
		this.gerente = null;
		this.administrador = null;
		this.ips = new LinkedList<>();
		this.afiliados = new LinkedList<>();
	}
	

	public Eps(long id, String nombre, Gerente gerente, AdministradorDatos administrador, List<Object[]> ips,
			List<Object[]> afiliados) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.gerente = gerente;
		this.administrador = administrador;
		this.ips = ips;
		this.afiliados = afiliados;
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
	
	

	public Gerente getGerente() {
		return gerente;
	}



	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}



	public AdministradorDatos getAdministrador() {
		return administrador;
	}



	public void setAdministrador(AdministradorDatos administrador) {
		this.administrador = administrador;
	}



	public List<Object[]> getIps() {
		return ips;
	}



	public void setIps(List<Object[]> ips) {
		this.ips = ips;
	}



	public List<Object[]> getAfiliados() {
		return afiliados;
	}


	public void setAfiliados(List<Object[]> afiliados) {
		this.afiliados = afiliados;
	}



	@Override
	public String toString() {
		return "Eps [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
}
