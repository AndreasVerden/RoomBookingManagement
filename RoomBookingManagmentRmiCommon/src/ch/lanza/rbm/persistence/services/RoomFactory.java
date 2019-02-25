package ch.lanza.rbm.persistence.services;

import java.util.HashSet;
import java.util.Set;

import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.domain.models.Room;
import ch.lanza.rbm.domain.services.ReservationRepository;
import ch.lanza.rbm.persistence.models.ProxyRoom;
import ch.lanza.rbm.persistence.models.RmiRoom;

public class RoomFactory {
	public static Room toRoom(RmiRoom rmiRoom, ReservationRepository reservationRepository) throws ValueException{
		return new ProxyRoom(rmiRoom.number, rmiRoom.price, reservationRepository);
	}

	public static RmiRoom toRmiRoom(Room room) {
		RmiRoom rmiRoom = new RmiRoom();
		rmiRoom.number = room.getNumber();
		rmiRoom.price = room.getPrice();
		return rmiRoom;
	}

	public static Set<RmiRoom> toRmiRooms(Set<Room> rooms) {
		Set<RmiRoom> rmiRooms = new HashSet<>();
		for (Room room : rooms) {
			rmiRooms.add(toRmiRoom(room));
		}
		return rmiRooms;
	}

	public static Set<Room> toRooms(Set<RmiRoom> rmiRooms, ReservationRepository reservationRepository) throws ValueException {
		Set<Room> rooms = new HashSet<>();
		for (RmiRoom rmiRoom : rmiRooms) {
			rooms.add(toRoom(rmiRoom, reservationRepository));
		}
		return rooms;
	}
}
