package ch.lanza.rmb.application;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import ch.lanza.rbm.application.services.ClientRemoteRepository;
import ch.lanza.rbm.application.services.RoomRemoteRepository;
import ch.lanza.rbm.domain.services.ClientRepository;
import ch.lanza.rbm.domain.services.ReservationRepository;
import ch.lanza.rbm.domain.services.RoomRepository;
import ch.lanza.rbm.persistence.InMemoryClientRepository;
import ch.lanza.rbm.persistence.InMemoryReservationRepository;
import ch.lanza.rbm.persistence.InMemoryRoomRepository;
import ch.lanza.rmb.application.services.ClientRmiService;
import ch.lanza.rmb.application.services.ReservationRmiService;
import ch.lanza.rmb.application.services.RoomRmiService;

public class RmiServer {
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		LocateRegistry.createRegistry(1099);
		
		ReservationRepository reservationRepository = new InMemoryReservationRepository();
		ClientRepository clientRepository = new InMemoryClientRepository();
		RoomRepository roomRepository = new InMemoryRoomRepository();
		
		
		ReservationRmiService reservationRemoteRepository = new ReservationRmiService(reservationRepository, 
				clientRepository, roomRepository);
		Naming.bind("//localhost/reservation", reservationRemoteRepository);
		
		ClientRemoteRepository clientRmiService = new ClientRmiService(clientRepository, reservationRepository);
		Naming.bind("//localhost/client", clientRmiService);
		
		RoomRemoteRepository roomRmiService = new RoomRmiService(roomRepository, reservationRepository);
		Naming.bind("//localhost/room", (Remote) roomRmiService);
	}
}
