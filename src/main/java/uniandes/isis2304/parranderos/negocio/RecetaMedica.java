package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class RecetaMedica {
	
	private long id;
	
	private String fecha;
	
	private String medicamentos;
	
	private long id_usuario;
	
	private long id_medico;
	
	private String id_servicio;
	
	public RecetaMedica()
	{
		this.id = 0;
		this.medicamentos = "";
		this.id_medico = 0;
		this.id_servicio = "";
		this.id_usuario = 0;
		this.fecha = "";
	}

	public RecetaMedica(long id, String fecha, long id_medico, long id_usuario, String id_servicio, String medicamentos) {
		super();
		this.id = id;
		this.medicamentos = medicamentos;
		this.id_usuario = id_usuario;
		this.id_medico = id_medico;
		this.id_servicio = id_servicio;
		this.fecha = fecha;
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

	public String getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public long getId_medico() {
		return id_medico;
	}

	public void setId_medico(long id_medico) {
		this.id_medico = id_medico;
	}

	public String getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(String id_servicio) {
		this.id_servicio = id_servicio;
	}

}

