package ch.lanza.rbm.application.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.UUID;

import ch.lanza.rbm.application.services.ReservationRemoteRepository;
import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.domain.models.Reservation;
import ch.lanza.rbm.domain.services.ClientRepository;
import ch.lanza.rbm.domain.services.ReservationRepository;
import ch.lanza.rbm.domain.services.RoomRepository;
import ch.lanza.rbm.persistence.models.RmiClient;
import ch.lanza.rbm.persistence.models.RmiReservation;
import ch.lanza.rbm.persistence.models.RmiRoom;
import ch.lanza.rbm.persistence.services.ClientFactory;
import ch.lanza.rbm.persistence.services.ReservationFactory;
import ch.lanza.rbm.persistence.services.RoomFactory;

public class ReservationRmiService extends UnicastRemoteObject implements ReservationRemoteRepository {
	private static final long serialVersionUID = -1938322372786477463L;

	private ReservationRepository reservationRepository;
	private ClientRepository clientRepository;
	private RoomRepository roomRepository;
	
	public ReservationRmiService(ReservationRepository reservationRepository, 
			ClientRepository clientRepository, RoomRepository roomRepository) throws RemoteException {
		super();
		this.reservationRepository = reservationRepository;
		this.clientRepository = clientRepository;
		this.roomRepository = roomRepository;
	}

	@Override
	public void addOrUpdate(RmiReservation rmiReservation) throws ConnectionException, ValueException {
		Reservation reservation = ReservationFactory.toReservation(rmiReservation, clientRepository, roomRepository);
		reservationRepository.addOrUpdate(reservation);
		clientRepository.addOrUpdate(reservation.getClient());
		roomRepository.addOrUpdate(reservation.getRoom());
	}

	@Override
	public RmiReservation get(UUID id) throws ConnectionException {
		return ReservationFactory.toRmiReservation(reservationRepository.get(id));
	}

	@Override
	public HashSet<RmiReservation> getAll() throws ConnectionException {
		return ReservationFactory.toRmiReservations(reservationRepository.getAll());
	}

	@Override
	public HashSet<RmiReservation> getByClient(RmiClient client) throws ConnectionException, ValueException {
		return ReservationFactory.toRmiReservations(
				reservationRepository.getByClient(
						ClientFactory.toClient(client, reservationRepository)));
	}

	@Override
	public HashSet<RmiReservation> getByRoom(RmiRoom room) throws ConnectionException, ValueException {
		return ReservationFactory.toRmiReservations(
				reservationRepository.getByRoom(
						RoomFactory.toRoom(room, reservationRepository)));
	}

	@Override
	public void remove(UUID id) throws ConnectionException {
		reservationRepository.remove(id);
	}

}
