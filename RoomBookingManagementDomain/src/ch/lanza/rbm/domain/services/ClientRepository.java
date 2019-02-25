package ch.lanza.rbm.domain.services;

import java.util.Set;
import java.util.UUID;

import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.models.Client;

public interface ClientRepository {
	void addOrUpdate(Client client) throws ConnectionException;
	Set<Client> get(String name, String firstName) throws ConnectionException;
	Client get(UUID id) throws ConnectionException;
	Set<Client> getAll() throws ConnectionException;
	void remove(UUID id) throws ConnectionException;
}
