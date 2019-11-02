package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManagerFactory;

import org.apache.log4j.Logger;

public class PersistenciaEps {

	private static Logger log = Logger.getLogger(PersistenciaEps.class.getName());

	public final static String SQL = "javax.jdo.query.SQL";

	private static PersistenciaEps instance;
	
	private PersistenceManagerFactory pmf;

	private List <String> tablas;


}
