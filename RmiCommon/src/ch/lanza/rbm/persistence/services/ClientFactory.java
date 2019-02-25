package ch.lanza.rbm.persistence.services;

import java.util.HashSet;
import java.util.Set;

import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.domain.models.Client;
import ch.lanza.rbm.domain.services.ReservationRepository;
import ch.lanza.rbm.persistence.models.ProxyClient;
import ch.lanza.rbm.persistence.models.RmiClient;

public class ClientFactory {
	public static Client toClient(RmiClient rmiClient, ReservationRepository reservationRepository) throws ValueException {
		return new ProxyClient(rmiClient.id, rmiClient.name, rmiClient.firstName, reservationRepository);
	}
	
	public static RmiClient toRmiClient(Client client) {
		RmiClient rmiClient = new RmiClient();
		rmiClient.id = client.getId();
		rmiClient.name = client.getName();
		rmiClient.firstName = client.getFirstName();
		return rmiClient;
	}
	
	public static Set<Client> toClients(Set<RmiClient> rmiClients, ReservationRepository reservationRepository) throws ValueException {
		Set<Client> clients = new HashSet<>();
		for (RmiClient rmiClient : rmiClients) {
			clients.add(toClient(rmiClient, reservationRepository));
		}
		return clients;
	}
	
	public static Set<RmiClient> toRmiClients(Set<Client> clients, ReservationRepository reservationRepository) {
		Set<RmiClient> rmiClients = new HashSet<>();
		for (Client client : clients) {
			rmiClients.add(toRmiClient(client));
		}
		return rmiClients;
	}
}
