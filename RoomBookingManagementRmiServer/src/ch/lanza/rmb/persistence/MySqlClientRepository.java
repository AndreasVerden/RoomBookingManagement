package ch.lanza.rmb.persistence;

import java.util.HashSet;
import java.util.UUID;

import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.models.Client;
import ch.lanza.rbm.domain.services.ClientRepository;

public class MySqlClientRepository implements ClientRepository{

	@Override
	public void addOrUpdate(Client client) throws ConnectionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashSet<Client> get(String name, String firstName) throws ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client get(UUID id) throws ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashSet<Client> getAll() throws ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(UUID id) throws ConnectionException {
		// TODO Auto-generated method stub
		
	}
}
