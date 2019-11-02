package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Cita  {
	
	private long id;
	
	private String estado_cita;
	
	private String fecha;
	
	private String id_servicio;
	
	private long id_receta;
	
	private long id_usuario;
	
	private long id_recepcionista;
	
	private int horario;
	
	public Cita()
	{
		this.id = 0;
		this.estado_cita = "";
		this.fecha = "";
		this.id_receta = 0;
		this.id_usuario = 0;
		this.id_servicio = "";
		this.id_recepcionista = 0;
		this.horario = 0;
	}

	/**
	 * @param id
	 * @param estado_cita
	 * @param fecha
	 * @param id_servicio
	 * @param id_receta
	 * @param id_usuario
	 * @param id_recepcionista
	 */
	public Cita(long id, long id_receta, long id_usuario, long id_recepcionista, String id_servicio, String estado_cita, String fecha, int horario) {
		super();
		this.id = id;
		this.estado_cita = estado_cita;
		this.fecha = fecha;
		this.id_servicio = id_servicio;
		this.id_receta = id_receta;
		this.id_usuario = id_usuario;
		this.id_recepcionista = id_recepcionista;
		this.horario = horario;
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

	public String getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(String id_servicio) {
		this.id_servicio = id_servicio;
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

	public long getId_receta() {
		return id_receta;
	}

	public void setId_receta(long id_receta) {
		this.id_receta = id_receta;
	}

	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}

	
	}