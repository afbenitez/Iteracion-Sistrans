package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Cita  {
	
	private long id;
	
	private String estado_cita;
	
	private String fecha;
	
	private long id_servicio;
	
	private long id_medico;
	
	private long id_usuario;
	
	private long id_recepcionista;
	
	public Cita()
	{
		this.id = 0;
		this.estado_cita = "";
		this.fecha = "";
		this.id_medico = 0;
		this.id_usuario = 0;
		this.id_servicio = 0;
		this.id_recepcionista = 0;
	}

	/**
	 * @param id
	 * @param estado_cita
	 * @param fecha
	 * @param id_servicio
	 * @param id_medico
	 * @param id_usuario
	 * @param id_recepcionista
	 */
	public Cita(long id, String estado_cita, String fecha, long id_servicio, long id_medico, long id_usuario,
			long id_recepcionista) {
		super();
		this.id = id;
		this.estado_cita = estado_cita;
		this.fecha = fecha;
		this.id_servicio = id_servicio;
		this.id_medico = id_medico;
		this.id_usuario = id_usuario;
		this.id_recepcionista = id_recepcionista;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEstado_cita() {
		return estado_cita;
	}

	public void setEstado_cita(String estado_cita) {
		this.estado_cita = estado_cita;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public long getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(long id_servicio) {
		this.id_servicio = id_servicio;
	}

	public long getId_medico() {
		return id_medico;
	}

	public void setId_medico(long id_medico) {
		this.id_medico = id_medico;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public long getId_recepcionista() {
		return id_recepcionista;
	}

	public void setId_recepcionista(long id_recepcionista) {
		this.id_recepcionista = id_recepcionista;
	}

	
	}