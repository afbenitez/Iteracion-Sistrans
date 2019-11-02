package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class OrdenMedica {

	private long id;
	
	private String fecha;
	
	private List<Object []> recetas;
	
	private List<Object []> servicios;
	
	public OrdenMedica ()
	{
		this.id = 0;
		this.fecha = "";
		this.recetas = new LinkedList<>();
		this.servicios = new LinkedList<>();
	}

	public OrdenMedica(long id, String fecha, List<Object[]> recetas, List<Object[]> servicios) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.recetas = recetas;
		this.servicios = servicios;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<Object[]> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Object[]> recetas) {
		this.recetas = recetas;
	}

	public List<Object[]> getServicios() {
		return servicios;
	}

	public void setServicios(List<Object[]> servicios) {
		this.servicios = servicios;
	}
	
	
}
