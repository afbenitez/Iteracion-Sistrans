package uniandes.isis2304.epsAndes.negocio;

import java.util.LinkedList;
import java.util.List;

public class Afiliado extends Usuario  {


	private String fecha_nacimiento;
	
	/**
	 * 
	 */
	public Afiliado() {
		super();
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
	public Afiliado(long id, String email, String nombre, long numero_id, int rol, String tipo_identificacion, String fecha_nacimiento) {

		super(id, email, nombre, numero_id, rol, tipo_identificacion);
		this.fecha_nacimiento = fecha_nacimiento;
	}
	

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}



}
