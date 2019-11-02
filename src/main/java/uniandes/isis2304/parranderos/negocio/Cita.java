package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Cita {
	
	private long id;
	
	private String estadoCita;
	
	private String fecha;
	
	private List<Object []> servicios;
	
	private Medico medicoAsignado;
	
	private Afiliado usuario;
	
	public Cita()
	{
		this.id = 0;
		this.estadoCita = "";
		this.fecha = "";
		this.medicoAsignado = null;
		this.usuario = null;
		this.servicios = new LinkedList<>();
	}

	public Cita(long id, String estadoCita, String fecha, List<Object[]> servicios, Medico medicoAsignado,
			Afiliado usuario) {
		super();
		this.id = id;
		this.estadoCita = estadoCita;
		this.fecha = fecha;
		this.servicios = servicios;
		this.medicoAsignado = medicoAsignado;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEstadoCita() {
		return estadoCita;
	}

	public void setEstadoCita(String estadoCita) {
		this.estadoCita = estadoCita;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<Object[]> getServicios() {
		return servicios;
	}

	public void setServicios(List<Object[]> servicios) {
		this.servicios = servicios;
	}

	public Medico getMedicoAsignado() {
		return medicoAsignado;
	}

	public void setMedicoAsignado(Medico medicoAsignado) {
		this.medicoAsignado = medicoAsignado;
	}

	public Afiliado getUsuario() {
		return usuario;
	}

	public void setUsuario(Afiliado usuario) {
		this.usuario = usuario;
	}
	
	
}
