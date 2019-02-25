package ch.lanza.rbm.domain.models;

import java.util.HashSet;
import java.util.UUID;

import ch.lanza.rbm.domain.exceptions.ValueException;

public class Client {	
	private UUID id;
	protected String name;
	protected String firstName;
	protected HashSet<Reservation> reservations;
	
	public Client(UUID id, String name, String firstName) throws ValueException{
		setId(id);
		setName(name);
		setFirstName(firstName);
		setReservations(new HashSet<Reservation>());
	}

	public UUID getId() {
		return id;
	}

	private void setId(UUID id) throws ValueException {
		if ( id == null )
			throw new ValueException("id must not be null");
		this.id = id;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) throws ValueException {
		if ( name == null || name.isEmpty() )
			throw new ValueException("name must not be empty");
		if ( name.length() < 3 )
			throw new ValueException("name is too short");
		if ( name.length() > 50 )
			throw new ValueException("name is too long");
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	private void setFirstName(String firstName) throws ValueException {
		if ( firstName == null || firstName.isEmpty() )
			throw new ValueException("firstName must not be empty");
		if ( firstName.length() < 3 )
			throw new ValueException("firstName is too short");
		if ( firstName.length() > 50 )
			throw new ValueException("firstName is too long");
		this.firstName = firstName;
	}

	public HashSet<Reservation> getReservations() {
		return reservations;
	}
	
	protected void setReservations(HashSet<Reservation> reservations) throws ValueException{
		if ( reservations == null )
			throw new ValueException("reservations must not be null");
		this.reservations = reservations;
	}
	
	public void addReservation(Reservation reservation) throws ValueException{
		if( reservation == null )
			throw new ValueException("reservation must not be null");
		if ( !reservation.getClient().getId().equals( this.id ) )
			throw new ValueException("reservation does not belong to this client");
		reservations.add(reservation);
	}
}
