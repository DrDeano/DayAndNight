package serverside;

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
		try {
			while (true) {
				Packet packet = queue.take();
				
				States state = packet.getMessageCommand();
				
				switch (state) {
				case PROGRESS:
					
					break;

				case POSITION:
					to_client.writeObject(packet);
					to_client.flush();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}