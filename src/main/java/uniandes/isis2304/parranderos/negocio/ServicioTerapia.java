package uniandes.isis2304.parranderos.negocio;

public class ServicioTerapia {
	
	private long id;
	
	private String tipo_terapia;
	
	private int numero_sesiones;
	
	public ServicioTerapia(){
		this.id = 0 ; 
		this.numero_sesiones = 0 ;
		this.tipo_terapia = "";
	}

	/**
	 * @param id
	 * @param tipo_terapia
	 * @param numero_sesiones
	 */
	public ServicioTerapia(long id, int numero_sesiones, String tipo_terapia) {
		super();
		this.id = id;
		this.tipo_terapia = tipo_terapia;
		this.numero_sesiones = numero_sesiones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo_terapia() {
		return tipo_terapia;
	}

	public void setTipo_terapia(String tipo_terapia) {
		this.tipo_terapia = tipo_terapia;
	}

	public int getNumero_sesiones() {
		return numero_sesiones;
	}

	public void setNumero_sesiones(int numero_sesiones) {
		this.numero_sesiones = numero_sesiones;
	}
	
}
