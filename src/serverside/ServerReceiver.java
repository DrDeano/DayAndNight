package serverside;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import globalClasses.Pos;
import globalClasses.States;
import serverLogic.Player;
import serverLogic.Stats;

public class ServerReceiver implements Runnable {

	private ObjectInputStream from_client;
	private ClientTable client_table;
	private String client_name;
	private Stats game_stats;

	public ServerReceiver(String n, ObjectInputStream c, ClientTable t, Stats game_stats) {
		client_name = n;
		from_client = c;
		client_table = t;
		this.game_stats = game_stats;
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
			case PLAY:
				start_game();
				break;
				
			case PROGRESS:
				
				break;

			case POSITION:
				String client = packet.getSender();
				
				for (String to_client : client_table.getNames()) {
					if (!client.equals(to_client)) {
						PacketQueue queue = client_table.getQueue(to_client);
						queue.offer(packet);
					}
				}
				
				ArrayList<Player> players = game_stats.getPlayers();
				
				for (Player player : players) {
					if (player.getId().equals(client)) {
						player.updatePosition((Pos) packet.getData());
					}
				}

				break;

			case S_COMPUTER:

				break;

			case S_COFFIE_MAKER:

				break;

			case S_SOFA:

				break;
				
			case S_POOL_TABLE:

				break;
				
			case DISCONNECT:
				client_table.remove(client_name);
				break;

			default:
				break;
			}
		}
	}
	
	private void start_game() {
		
	}
}