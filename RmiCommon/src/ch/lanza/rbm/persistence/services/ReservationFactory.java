package ch.lanza.rbm.persistence.services;

import java.util.HashSet;

import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.domain.models.Reservation;
import ch.lanza.rbm.domain.services.ClientRepository;
import ch.lanza.rbm.domain.services.RoomRepository;
import ch.lanza.rbm.persistence.models.RmiReservation;

public class ReservationFactory {

	public static Reservation toReservation(RmiReservation rmiReservation, 
			ClientRepository clientRepository, RoomRepository roomRepository) throws ValueException, ConnectionException {
		
		return new Reservation(rmiReservation.id, 
				clientRepository.get(rmiReservation.client.id), 
				roomRepository.get(rmiReservation.room.number), 
				TimeSpanMapping.toTimeSpan(rmiReservation.timeSpan));
	}	
	
	public static RmiReservation toRmiReservation(Reservation reservation) {
		RmiReservation rmiReservation = new RmiReservation();
		rmiReservation.id = reservation.getId();
		rmiReservation.client = ClientFactory.toRmiClient(reservation.getClient());
		rmiReservation.room = RoomFactory.toRmiRoom(reservation.getRoom());
		rmiReservation.timeSpan = TimeSpanMapping.toRmiTimeSpan(reservation.getTimeSpan());
		rmiReservation.discount = reservation.getDiscount();
		return rmiReservation;
	}
	
	public static HashSet<Reservation> toReservations(
			HashSet<RmiReservation> reservations, 
			ClientRepository clientRepository,
			RoomRepository roomRepository ) 
					throws ValueException, ConnectionException {
		HashSet<Reservation> result = new HashSet<>();
		for ( RmiReservation reservation : reservations )
			result.add(toReservation(reservation, clientRepository, roomRepository));
		return result;
	}

	public static HashSet<RmiReservation> toRmiReservations(HashSet<Reservation> reservations) {
		HashSet<RmiReservation> rmiReservations = new HashSet<>();
		for ( Reservation reservation : reservations)
			rmiReservations.add(toRmiReservation(reservation));
		return rmiReservations;
	}

}
