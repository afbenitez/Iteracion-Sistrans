package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Medico extends Usuario {

	private String especialidad;

	private String numero_registro;

	public Medico()
	{
		super();
		this.especialidad = "";
		this.numero_registro = "";
	}

	/**
	 * @param id
	 * @param email
	 * @param nombre
	 * @param numero_id
	 * @param rol
	 * @param tipo_identificacion
	 */

	

	public Medico(long id, String email, String nombre, long numero_id, int rol, String tipo_identificacion, String especialidad, String numero_registro) {

		super(id, email, nombre, numero_id, rol, tipo_identificacion);
		this.especialidad = especialidad;
		this.numero_registro = numero_registro;
		// TODO Auto-generated constructor stub
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getNumero_registro() {
		return numero_registro;
	}

	public void setNumero_registro(String numero_registro) {
		this.numero_registro = numero_registro;
	}


	

}
