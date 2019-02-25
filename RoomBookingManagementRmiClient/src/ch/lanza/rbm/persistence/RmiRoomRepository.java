package ch.lanza.rbm.persistence;

import java.rmi.Naming;
import java.util.Set;

import ch.lanza.rbm.application.services.RoomRemoteRepository;
import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.models.Room;
import ch.lanza.rbm.domain.services.ReservationRepository;
import ch.lanza.rbm.domain.services.RoomRepository;
import ch.lanza.rbm.persistence.services.RoomFactory;

public class RmiRoomRepository implements RoomRepository {
	private RoomRemoteRepository roomRepository;
	private ReservationRepository reservationRepository;
	
	public RmiRoomRepository(ReservationRepository reservationRepository) throws ConnectionException {
		try {
			roomRepository = (RoomRemoteRepository) Naming.lookup("//localhost/room");
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("could not connect to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
		this.reservationRepository = reservationRepository;
	}
	
	@Override
	public void addOrUpdate(Room room) throws ConnectionException {
		try {
			roomRepository.addOrUpdate(RoomFactory.toRmiRoom(room));
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public Room get(short id) throws ConnectionException {
		try {
			return RoomFactory.toRoom(roomRepository.get(id), reservationRepository);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public Set<Room> getAll() throws ConnectionException {
		try {
			return RoomFactory.toRooms(roomRepository.getAll(), reservationRepository);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public void remove(short id) throws ConnectionException {
		try {
			roomRepository.remove(id);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}
}