package ch.lanza.rbm.domain.models;

import java.util.HashSet;

import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.domain.services.RoomBookingPolicy;

public class Room {
	private short number;
	protected float price;
	protected HashSet<Reservation> reservations;

	public Room (short number, float price) throws ValueException{
		setNumber(number);
		setPrice(price);
		setReservations(new HashSet<Reservation>());
	}

	private void setNumber(short number) throws ValueException{
		if ( number <= 0)
			throw new ValueException("number can not be smaller or equals 0");
		this.number = number;
	}
	
	public short getNumber() {
		return number;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) throws ValueException {
		if ( price <= 0 )
			throw new ValueException("price can not be smaller or equals 0");
		this.price = price;
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
		if( reservation.getRoom().getNumber() != this.number )
			throw new ValueException("reservation does not belong to this room");
		if( RoomBookingPolicy.isAllowed(this, reservation) )
			throw new ValueException("room is not empty at that time");
		reservations.add(reservation);
	}
}
