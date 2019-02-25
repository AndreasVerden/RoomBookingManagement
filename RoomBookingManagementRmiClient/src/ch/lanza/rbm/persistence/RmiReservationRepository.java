package ch.lanza.rbm.persistence;

import java.rmi.Naming;
import java.util.HashSet;
import java.util.UUID;

import ch.lanza.rbm.application.services.ReservationRemoteRepository;
import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.models.Client;
import ch.lanza.rbm.domain.models.Reservation;
import ch.lanza.rbm.domain.models.Room;
import ch.lanza.rbm.domain.services.ClientRepository;
import ch.lanza.rbm.domain.services.ReservationRepository;
import ch.lanza.rbm.domain.services.RoomRepository;
import ch.lanza.rbm.persistence.services.ClientFactory;
import ch.lanza.rbm.persistence.services.ReservationFactory;
import ch.lanza.rbm.persistence.services.RoomFactory;

public class RmiReservationRepository implements ReservationRepository{
	private ReservationRemoteRepository reservationRemoteRepository;
	private ClientRepository clientRepository;
	private RoomRepository roomRepository;
	
	public RmiReservationRepository() 
			throws ConnectionException {
		try {
			reservationRemoteRepository = (ReservationRemoteRepository) Naming.lookup("//localhost/reservation");
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("could not connect to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}
	
	@Override
	public void addOrUpdate(Reservation reservation) throws ConnectionException {
		try {
			reservationRemoteRepository.addOrUpdate(ReservationFactory.toRmiReservation(reservation));
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public Reservation get(UUID id) throws ConnectionException {
		try {
			return ReservationFactory.toReservation(reservationRemoteRepository.get(id), clientRepository, roomRepository);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public HashSet<Reservation> getAll() throws ConnectionException {
		try {
			return ReservationFactory.toReservations(
					reservationRemoteRepository.getAll(), 
					clientRepository, 
					roomRepository);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public HashSet<Reservation> getByClient(Client client) throws ConnectionException {
		try {
			return ReservationFactory.toReservations(
					reservationRemoteRepository.getByClient(
							ClientFactory.toRmiClient(client)), 
					clientRepository, 
					roomRepository);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public HashSet<Reservation> getByRoom(Room room) throws ConnectionException {
		try {
			return ReservationFactory.toReservations(reservationRemoteRepository.getByRoom(RoomFactory.toRmiRoom(room)),
					clientRepository, roomRepository);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public void remove(UUID id) throws ConnectionException {
		try {
			reservationRemoteRepository.remove(id);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}
	
	public void setClientRepository(ClientRepository clientRepository){
		this.clientRepository = clientRepository;
	}
	
	public void setRoomRepository(RoomRepository roomRepository){
		this.roomRepository = roomRepository;
	}
}
