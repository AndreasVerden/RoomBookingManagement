package ch.lanza.rbm.domain.models;

import java.util.UUID;

import ch.lanza.rbm.domain.exceptions.ValueException;

public class Reservation {
	private UUID id;
	private Client client;
	private Room room;
	private short discount;
	private TimeSpan timeSpan;
	
	public Reservation(UUID id, Client client, Room room, TimeSpan timeSpan) throws ValueException {
		setId(id);
		setTimeSpan(timeSpan);
		setClient(client);
		setRoom(room);
	}

	public UUID getId() {
		return id;
	}

	private void setId(UUID id) throws ValueException {
		if ( id == null )
			throw new ValueException("id must not be null");
		this.id = id;
	}
	
	public Client getClient() {
		return client;
	}
	
	protected void setClient(Client client) throws ValueException {
		if ( client == null )
			throw new ValueException("client must not be null");
		this.client = client;
		client.addReservation(this);
	}
	
	public Room getRoom() {
		return room;
	}
	
	protected void setRoom(Room room) throws ValueException {
		if ( room == null )
			throw new ValueException("room must not be null");
		this.room = room;
		room.addReservation(this);
	}
	
	public short getDiscount() {
		return discount;
	}
	
	public void setDiscount(short discount) throws ValueException {
		if ( discount > 100 )
			throw new ValueException("discount must not be greater than 100");
		this.discount = discount;
	}
	
	public TimeSpan getTimeSpan() {
		return timeSpan;
	}
	
	public void setTimeSpan(TimeSpan timeSpan) throws ValueException {
		if ( timeSpan == null )
			throw new ValueException("timeSpan must not be null");
		this.timeSpan = timeSpan;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
