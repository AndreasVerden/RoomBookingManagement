package ch.lanza.rbm.domain.services;

import java.util.Set;

import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.models.Room;

public interface RoomRepository {
	void addOrUpdate(Room room) throws ConnectionException;
	Room get(short id) throws ConnectionException;
	Set<Room> getAll() throws ConnectionException;
	void remove(short id) throws ConnectionException;
}
