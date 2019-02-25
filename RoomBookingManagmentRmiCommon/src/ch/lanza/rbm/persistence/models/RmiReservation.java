package ch.lanza.rbm.persistence.models;

import java.io.Serializable;
import java.util.UUID;

public class RmiReservation implements Serializable {
	private static final long serialVersionUID = 6927173051344427995L;
	
	public UUID id;
	public RmiClient client;
	public RmiRoom room;
	public short discount;
	public RmiTimeSpan timeSpan;
}
