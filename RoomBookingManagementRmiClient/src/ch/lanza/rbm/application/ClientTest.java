package ch.lanza.rbm.application;

import java.util.GregorianCalendar;
import java.util.Set;
import java.util.UUID;

import ch.lanza.rbm.domain.exceptions.ConnectionException;
import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.domain.models.Client;
import ch.lanza.rbm.domain.models.Reservation;
import ch.lanza.rbm.domain.models.Room;
import ch.lanza.rbm.domain.models.TimeSpan;
import ch.lanza.rbm.domain.services.ClientRepository;
import ch.lanza.rbm.domain.services.ReservationRepository;
import ch.lanza.rbm.domain.services.RoomRepository;
import ch.lanza.rbm.persistence.RmiClientRepository;
import ch.lanza.rbm.persistence.RmiReservationRepository;
import ch.lanza.rbm.persistence.RmiRoomRepository;

public class ClientTest {
	private static ReservationRepository reservationRepository;
	private static ClientRepository clientRepository;
	private static RoomRepository roomRepository;
	
	public static void main(String[] args) throws ConnectionException, ValueException {

		reservationRepository = new RmiReservationRepository();		
		clientRepository = new RmiClientRepository(reservationRepository);
		roomRepository = new RmiRoomRepository(reservationRepository);
		
		
		// create demo data
		((RmiReservationRepository)reservationRepository).setClientRepository(clientRepository);
		((RmiReservationRepository)reservationRepository).setRoomRepository(roomRepository);
		
		TimeSpan timeSpan = new TimeSpan(
				new GregorianCalendar(2005, 6, 26).getTime(), 
				new GregorianCalendar(2005, 7, 2).getTime());
		
		Room room101 = new Room((short) 101, 30);
		Room room201 = new Room((short) 201, 40);
		Room room301 = new Room((short) 301, 50);
		
		roomRepository.addOrUpdate(room101);
		roomRepository.addOrUpdate(room201);
		roomRepository.addOrUpdate(room301);
		
		Client andrew = new Client(UUID.randomUUID(), "Smith", "Andrew");
		Client rebeca = new Client(UUID.randomUUID(), "Smith", "Rebeca");
		Client sandra = new Client(UUID.randomUUID(), "Smith", "Sandra");

		clientRepository.addOrUpdate(andrew);
		clientRepository.addOrUpdate(rebeca);
		clientRepository.addOrUpdate(sandra);
		
		Reservation rebecaRoom101 = new Reservation(UUID.randomUUID(), rebeca, room101, timeSpan);
		Reservation andrewRoom201 = new Reservation(UUID.randomUUID(), andrew, room201, timeSpan);
		Reservation sandraRoom301 = new Reservation(UUID.randomUUID(), sandra, room301, timeSpan);
		
		reservationRepository.addOrUpdate(rebecaRoom101);
		reservationRepository.addOrUpdate(andrewRoom201);
		reservationRepository.addOrUpdate(sandraRoom301);
		
		
		// DEMO
		// get all clients reservation with price where last name equals "Smith"
		Set<Client> clients = clientRepository.get("Smith", null);
		
		// present all clients reservation with price where last name equals "Smith"		
		for(Client client : clients) {
			System.out.println(client.getName() +" "+ client.getFirstName());
			for(Reservation reservation : client.getReservations()) {
				System.out.println("\t"+ reservation.getTimeSpan() +": "+ reservation.getRoom().getNumber());
				System.out.println("\t"+ reservation.getTimeSpan().inNights() * reservation.getRoom().getPrice() + " CHF");
			}
		}
	}
}
