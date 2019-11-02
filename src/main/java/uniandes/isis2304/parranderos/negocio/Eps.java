package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Eps   {
	
	private long id;
	
	private String nombre;
	
	
	public Eps()
	{
		this.id = 0;
		this.nombre = "";
		
	}

	public Eps(long id, String nombre, long id_gerente, long id_administrador, long id_ips, long id_afiliados) {
		super();
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
	
}
