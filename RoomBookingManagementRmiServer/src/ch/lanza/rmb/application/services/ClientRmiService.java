package ch.lanza.rmb.application.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;
import java.util.UUID;

import ch.lanza.rbm.application.services.ClientRemoteRepository;
import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.domain.services.ClientRepository;
import ch.lanza.rbm.domain.services.ReservationRepository;
import ch.lanza.rbm.persistence.models.RmiClient;
import ch.lanza.rbm.persistence.services.ClientFactory;

public class ClientRmiService extends UnicastRemoteObject implements ClientRemoteRepository {
	private static final long serialVersionUID = 8487925229928982602L;

	private ClientRepository clientRepository;
	private ReservationRepository reservationRepository;
	
	public ClientRmiService(ClientRepository clientRepository, ReservationRepository reservationRepository) throws RemoteException {
		super();
		this.clientRepository = clientRepository;
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void addOrUpdate(RmiClient client) throws ConnectionException, ValueException {
		clientRepository.addOrUpdate(ClientFactory.toClient(client, reservationRepository));
	}

	@Override
	public Set<RmiClient> get(String name, String firstName) throws ConnectionException {
		return ClientFactory.toRmiClients(clientRepository.get(name, firstName), reservationRepository);
	}

	@Override
	public RmiClient get(UUID id) throws ConnectionException {
		return ClientFactory.toRmiClient(clientRepository.get(id));
	}

	@Override
	public Set<RmiClient> getAll() throws ConnectionException {
		return ClientFactory.toRmiClients(clientRepository.getAll(), reservationRepository);
	}

	@Override
	public void remove(UUID id) throws ConnectionException {
		clientRepository.remove(id);
	}

}
