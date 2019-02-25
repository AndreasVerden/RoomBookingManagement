package ch.lanza.rbm.domain.services;

import java.util.HashSet;
import java.util.UUID;

import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.models.Client;
import ch.lanza.rbm.domain.models.Reservation;
import ch.lanza.rbm.domain.models.Room;;

public interface ReservationRepository {
	void addOrUpdate(Reservation reservation) throws ConnectionException;
	Reservation get(UUID id) throws ConnectionException;
	HashSet<Reservation> getAll() throws ConnectionException;
	HashSet<Reservation> getByClient(Client client) throws ConnectionException;
	HashSet<Reservation> getByRoom(Room room) throws ConnectionException;
	void remove(UUID id) throws ConnectionException;
}
