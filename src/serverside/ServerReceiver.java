package serverside;

import java.io.IOException;
import java.io.ObjectInputStream;

import globalClasses.States;

public class ServerReceiver implements Runnable {

	private ObjectInputStream from_client;
	private ClientTable client_table;
	private String client_name;

	public ServerReceiver(String n, ObjectInputStream c, ClientTable t) {
		client_name = n;
		from_client = c;
		client_table = t;
	}

	@Override
	public void run() {
		while (true) {
			Packet packet = null;

			try {
				packet = (Packet) from_client.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			States state = packet.getMessageCommand();

			switch (state) {
			case PROGRESS:

				break;

			case FUN:

				break;

			case CAFFIENE:

				break;

			case SANITY:

				break;

			case POSITION:

				break;

			case S_COMPUTER:

				break;

			case S_COFFIE_MAKER:

				break;

			case S_SOFA:

				break;
				
			case S_POOL_TABLE:

				break;

			default:
				break;
			}
		}
	}
}