package clientLobby;

import java.io.IOException;
import java.io.ObjectInputStream;

import globalClasses.States;
import serverNetworking.Packet;

public class ClientReceiver implements Runnable {

	private ObjectInputStream from_server;
	
	public ClientReceiver(ObjectInputStream from_server) {
		this.from_server = from_server;
	}

	@Override
	public void run() {
		while (true) {
			Packet packet = null;
			
			try {
				packet = (Packet) from_server.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			States state = packet.getMessageCommand();
			
			switch (state) {
			case PROGRESS :

				break;

			case POSITION :
				
				break;

			case COMPUTER :

				break;

			case COFFIE_MAKER :

				break;

			case SOFA :

				break;

			case POOL_TABLE :

				break;

			case DISCONNECT :

				break;
			default :
				break;
		}
		}
	}
}