package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Recepcionista extends Usuario  {

	
	private String id_ips;

	/**
	 * 
	 */
	public Recepcionista() {
		super();
		this.id_ips = "";
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param email
	 * @param nombre
	 * @param numero_id
	 * @param rol
	 * @param tipo_identificacion
	 */
	public Recepcionista(long id, String email, String nombre, long numero_id, int rol, String tipo_identificacion, String id_ips) {
		super(id, email, nombre, numero_id, rol, tipo_identificacion);
		// TODO Auto-generated constructor stub
		this.id_ips = id_ips;
	}

	public String getId_ips() {
		return id_ips;
	}

	public void setId_ips(String id_ips) {
		this.id_ips = id_ips;
	}
	
	

}
