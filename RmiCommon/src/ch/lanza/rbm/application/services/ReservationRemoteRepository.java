package ch.lanza.rbm.application.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.UUID;

import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.persistence.models.RmiClient;
import ch.lanza.rbm.persistence.models.RmiReservation;
import ch.lanza.rbm.persistence.models.RmiRoom;

public interface ReservationRemoteRepository extends Remote {
	void addOrUpdate(RmiReservation reservation) throws RemoteException, ConnectionException, ValueException;
	RmiReservation get(UUID id) throws RemoteException, ConnectionException;
	HashSet<RmiReservation> getAll() throws RemoteException, ConnectionException;
	HashSet<RmiReservation> getByClient(RmiClient client) throws RemoteException, ConnectionException, ValueException;
	HashSet<RmiReservation> getByRoom(RmiRoom room) throws RemoteException, ConnectionException, ValueException;
	void remove(UUID id) throws RemoteException, ConnectionException;
}
