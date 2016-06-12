package serverNetworking;

import java.io.IOException;
import java.io.ObjectOutputStream;

import globalClasses.States;

public class ServerSender implements Runnable {

	private PacketQueue queue;
	private ObjectOutputStream to_client;

	public ServerSender(PacketQueue queue, ObjectOutputStream to_client) {
		this.queue = queue;
		this.to_client = to_client;
	}

	@Override
	public void run() {
		while (true) {
			Packet packet = queue.take();

			States state = packet.getMessageCommand();

			switch (state) {
				case PROGRESS :
					write_to_client(packet);
					break;

				case POSITION :
					write_to_client(packet);
					break;

				case COMPUTER :
					write_to_client(packet);
					break;

				case COFFIE_MAKER :
					write_to_client(packet);
					break;

				case SOFA :
					write_to_client(packet);
					break;

				case POOL_TABLE :
					write_to_client(packet);
					break;

				case DISCONNECT :
					try {
						to_client.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				default :
					break;
			}
		}
	}

	private void write_to_client(Packet obj) {
		try {
			to_client.writeObject(obj);
			to_client.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}