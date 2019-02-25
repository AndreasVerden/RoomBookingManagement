package ch.lanza.rbm.application.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.persistence.models.RmiRoom;

public interface RoomRemoteRepository extends Remote{
	void addOrUpdate(RmiRoom room) throws RemoteException, ConnectionException, ValueException;
	RmiRoom get(short id) throws RemoteException, ConnectionException, ValueException;
	Set<RmiRoom> getAll() throws RemoteException, ConnectionException, ValueException;
	void remove(short id) throws RemoteException, ConnectionException, ValueException;
}
