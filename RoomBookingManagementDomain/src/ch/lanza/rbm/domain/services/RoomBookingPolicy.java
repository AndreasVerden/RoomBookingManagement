package ch.lanza.rbm.domain.services;

import ch.lanza.rbm.domain.models.Reservation;
import ch.lanza.rbm.domain.models.Room;

public class RoomBookingPolicy {
	public static boolean isAllowed(Room room, Reservation reservation) {
		for(Reservation roomReservation : room.getReservations())
			if (roomReservation.getTimeSpan().overlaps(reservation.getTimeSpan()))
				return true;
		return false;
	}
}
