package uniandes.isis2304.epsAndes.negocio;

public class Horario {
	
	private long id;
	
	private String franja;
	
	public Horario(){
		this.id = 0;
		this.franja = "";
	}

	/**
	 * @param id
	 * @param franja
	 */
	public Horario(long id, String franja) {
		super();
		this.id = id;
		this.franja = franja;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFranja() {
		return franja;
	}

	public void setFranja(String franja) {
		this.franja = franja;
	}
	
	
}
