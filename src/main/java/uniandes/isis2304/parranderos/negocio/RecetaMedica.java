package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class RecetaMedica implements VORecetaMedica {
	
	private long id;
	
	private String medicamentos;
	
	private Afiliado usuarioAsignado;
	
	private Medico medicoRemitente;
	
	private List<Object []> ordenes;
	
	public RecetaMedica()
	{
		this.id = 0;
		this.medicamentos = "";
		this.medicoRemitente = null;
		this.ordenes = new LinkedList<>();
		this.usuarioAsignado = null;
	}

	public RecetaMedica(long id, String medicamentos, Afiliado usuarioAsignado, Medico medicoRemitente,
			List<Object[]> ordenes) {
		super();
		this.id = id;
		this.medicamentos = medicamentos;
		this.usuarioAsignado = usuarioAsignado;
		this.medicoRemitente = medicoRemitente;
		this.ordenes = ordenes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}

	public Afiliado getUsuarioAsignado() {
		return usuarioAsignado;
	}

	public void setUsuarioAsignado(Afiliado usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}

	public Medico getMedicoRemitente() {
		return medicoRemitente;
	}

	public void setMedicoRemitente(Medico medicoRemitente) {
		this.medicoRemitente = medicoRemitente;
	}

	public List<Object[]> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<Object[]> ordenes) {
		this.ordenes = ordenes;
	}
	
	
}

