package ch.lanza.rbm.application.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.UUID;

import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.persistence.models.RmiClient;

public interface ClientRemoteRepository extends Remote {
	public void addOrUpdate(RmiClient client) throws RemoteException, ConnectionException, ValueException;
	public Set<RmiClient> get(String name, String firstName) throws RemoteException, ConnectionException, ValueException;
	public RmiClient get(UUID id) throws RemoteException, ConnectionException, ValueException;
	public Set<RmiClient> getAll() throws RemoteException, ConnectionException, ValueException;
	public void remove(UUID id) throws RemoteException, ConnectionException, ValueException;
}
