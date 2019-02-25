package ch.lanza.rbm.persistence;

import java.rmi.Naming;
import java.util.Set;
import java.util.UUID;

import ch.lanza.rbm.application.services.ClientRemoteRepository;
import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.models.Client;
import ch.lanza.rbm.domain.services.ClientRepository;
import ch.lanza.rbm.domain.services.ReservationRepository;
import ch.lanza.rbm.persistence.services.ClientFactory;

public class RmiClientRepository implements ClientRepository {
	private ClientRemoteRepository clientRemoteRepository;
	private ReservationRepository reservationRemoteRepository;
	
	public RmiClientRepository(ReservationRepository reservationRepository) throws ConnectionException {
		try {
			clientRemoteRepository = (ClientRemoteRepository) Naming.lookup("//localhost/client");
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("could not connect to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
		this.reservationRemoteRepository = reservationRepository;
	}
	
	@Override
	public void addOrUpdate(Client client) throws ConnectionException {
		try {
			clientRemoteRepository.addOrUpdate(ClientFactory.toRmiClient(client));
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public Set<Client> get(String name, String firstName) throws ConnectionException {
		try {
			return ClientFactory.toClients(clientRemoteRepository.get(name, firstName), reservationRemoteRepository);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public Client get(UUID id) throws ConnectionException {
		try {
			return ClientFactory.toClient(clientRemoteRepository.get(id), reservationRemoteRepository);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public Set<Client> getAll() throws ConnectionException {
		try {
			return ClientFactory.toClients(clientRemoteRepository.getAll(), reservationRemoteRepository);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}

	@Override
	public void remove(UUID id) throws ConnectionException {
		try {
			clientRemoteRepository.remove(id);
		} catch (Exception exception){
			ConnectionException connectionException = new ConnectionException("lost connection to the RMI client");
			connectionException.addSuppressed(exception);
			throw connectionException;
		}
	}
}