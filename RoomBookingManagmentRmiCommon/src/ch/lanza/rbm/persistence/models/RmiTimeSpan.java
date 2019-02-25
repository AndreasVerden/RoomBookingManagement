package ch.lanza.rbm.persistence.models;

import java.io.Serializable;
import java.util.Date;

public class RmiTimeSpan implements Serializable {
	private static final long serialVersionUID = 7631846315203207287L;
	
	public Date start;
	public Date end;	
}