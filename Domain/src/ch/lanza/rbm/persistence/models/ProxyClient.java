package ch.lanza.rbm.persistence.models;

import java.util.HashSet;
import java.util.UUID;

import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.domain.models.Client;
import ch.lanza.rbm.domain.models.Reservation;
import ch.lanza.rbm.domain.services.ReservationRepository;

public class ProxyClient extends Client{
	ReservationRepository reservationRepository;
	
	public ProxyClient(UUID id, String name, String firstName, ReservationRepository reservationRepository) throws ValueException {
		super(id, name, firstName);
		this.reservationRepository = reservationRepository;
	}
	
	@Override
	public HashSet<Reservation> getReservations() {
		if ( reservations.isEmpty() ) {
			try {
				reservations = reservationRepository.getByClient(this);
			} catch (ConnectionException exception) {
				// TODO implement exception handling for client proxy object;
				exception.printStackTrace();
				System.err.println("I don't know how to handle this exception...");
			}
		}
		return super.getReservations();
	}
	
}
