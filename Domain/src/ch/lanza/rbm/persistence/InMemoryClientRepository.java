package ch.lanza.rbm.persistence;

import java.util.HashSet;
import java.util.UUID;

import ch.lanza.rbm.domain.models.Client;
import ch.lanza.rbm.domain.services.ClientRepository;

public class InMemoryClientRepository implements ClientRepository{
	private HashSet<Client> clients;
	
	public InMemoryClientRepository() {
		clients = new HashSet<>();
	}
	
	@Override
	public void addOrUpdate(Client client) {
		clients.add(client);
	}

	@Override
	public HashSet<Client> get(String name, String firstName) {
		HashSet<Client> result = new HashSet<>();
		
		for (Client client : clients)
			if ( client.getName().equals(name)
					|| client.getFirstName().equals(firstName))
				result.add(client);
		return result;
	}

	@Override
	public Client get(UUID id) {
		for (Client client : clients)
			if ( client.getId().equals(id) )
				return client;
		return null;
	}

	@Override
	public HashSet<Client> getAll() {
		return clients;
	}

	@Override
	public void remove(UUID id) {
		clients.remove(get(id));
	}

}
