package ch.lanza.rbm.persistence;

import java.util.HashSet;
import java.util.Set;

import ch.lanza.rbm.domain.models.Room;
import ch.lanza.rbm.domain.services.RoomRepository;

public class InMemoryRoomRepository implements RoomRepository{
	private Set<Room> rooms;
	
	public InMemoryRoomRepository() {
		rooms = new HashSet<>();
	}
	
	@Override
	public void addOrUpdate(Room room) {
		rooms.add(room);
	}

	@Override
	public Room get(short id) {
		for (Room room : rooms)
			if ( room.getNumber() == id)
				return room;
		return null;
	}

	@Override
	public Set<Room> getAll() {
		return rooms;
	}

	@Override
	public void remove(short id) {
		rooms.remove(get(id));
	}

}
