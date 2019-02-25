package ch.lanza.rbm.persistence.models;

import java.io.Serializable;
import java.util.UUID;

public class RmiClient implements Serializable{	
	private static final long serialVersionUID = 4604767099109556326L;
	
	public UUID id;
	public String name;
	public String firstName;
}