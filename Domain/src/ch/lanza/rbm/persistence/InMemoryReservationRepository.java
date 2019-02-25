package ch.lanza.rbm.persistence;

import java.util.HashSet;
import java.util.UUID;

import ch.lanza.rbm.domain.models.Client;
import ch.lanza.rbm.domain.models.Reservation;
import ch.lanza.rbm.domain.models.Room;
import ch.lanza.rbm.domain.services.ReservationRepository;

public class InMemoryReservationRepository implements ReservationRepository {
	private HashSet<Reservation> reservations;
	
	public InMemoryReservationRepository() {
		reservations = new HashSet<Reservation>();
	}
	
	@Override
	public void addOrUpdate(Reservation reservation) {
		reservations.add(reservation);
	}

	@Override
	public Reservation get(UUID id) {
		for ( Reservation reservation : reservations)
			if ( reservation.getId().equals(id) )
				return reservation;
		return null;
	}

	@Override
	public HashSet<Reservation> getAll() {
		return reservations;
	}

	@Override
	public HashSet<Reservation> getByClient(Client client) {
		HashSet<Reservation> result = new HashSet<>();
		for ( Reservation reservation : reservations)
			if ( reservation.getClient().getId().equals( client.getId()) )
				result.add(reservation);
		return result;
	}

	@Override
	public HashSet<Reservation> getByRoom(Room room) {
		HashSet<Reservation> result = new HashSet<>();
		for ( Reservation reservation : reservations)
			if ( reservation.getRoom() == room )
				result.add(reservation);
		return result;
	}

	@Override
	public void remove(UUID id) {
		reservations.remove(get(id));
	}
}
