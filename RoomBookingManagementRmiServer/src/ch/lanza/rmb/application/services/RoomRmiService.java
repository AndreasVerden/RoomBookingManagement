package ch.lanza.rmb.application.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import ch.lanza.rbm.application.services.RoomRemoteRepository;
import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.domain.services.ReservationRepository;
import ch.lanza.rbm.domain.services.RoomRepository;
import ch.lanza.rbm.persistence.models.RmiRoom;
import ch.lanza.rbm.persistence.services.RoomFactory;

public class RoomRmiService extends UnicastRemoteObject implements RoomRemoteRepository {
	private static final long serialVersionUID = 753394723280972356L;

	private RoomRepository roomRepository;
	private ReservationRepository reservationRepository;
	
	public RoomRmiService(RoomRepository roomRepository, ReservationRepository reservationRepository) throws RemoteException {
		super();
		this.roomRepository = roomRepository;
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void addOrUpdate(RmiRoom room) throws ConnectionException, ValueException {
		roomRepository.addOrUpdate(RoomFactory.toRoom(room, reservationRepository));
	}

	@Override
	public RmiRoom get(short id) throws ConnectionException {
		return RoomFactory.toRmiRoom(roomRepository.get(id));
	}

	@Override
	public Set<RmiRoom> getAll() throws ConnectionException {
		return RoomFactory.toRmiRooms(roomRepository.getAll());
	}

	@Override
	public void remove(short id) throws ConnectionException {
		roomRepository.remove(id);		
	}

}