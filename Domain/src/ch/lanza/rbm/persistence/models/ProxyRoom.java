package ch.lanza.rbm.persistence.models;

import java.util.HashSet;

import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.domain.models.Reservation;
import ch.lanza.rbm.domain.models.Room;
import ch.lanza.rbm.domain.services.ReservationRepository;

public class ProxyRoom extends Room{
	private ReservationRepository reservationRepository;
	
	public ProxyRoom(short number, float price, ReservationRepository reservationRepository) throws ValueException {
		super(number, price);
		this.reservationRepository = reservationRepository;
	}
	
	@Override
	public HashSet<Reservation> getReservations() {
		if ( reservations.isEmpty() ) { 
			try {
				reservations = reservationRepository.getByRoom(this);
			} catch (ConnectionException exception) {
				// TODO implement exception handling for client proxy object;
				exception.printStackTrace();
				System.err.println("I don't know how to handle this exception...");
			}
		}
		return super.getReservations();
	}
	
}
