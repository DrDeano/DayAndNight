package serverside;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import globalClasses.Pos;
import globalClasses.Progression;
import globalClasses.States;
import serverLogic.Player;
import serverLogic.Stat;
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
				Player person = get_player(client_name);
				Progression pro = (Progression) packet.getData();
				
				person.changeStat(Stat.PROGRESS, pro.get_progress());
				person.changeStat(Stat.COFFEE, pro.get_caffeine());
				person.changeStat(Stat.FUN, pro.get_fun());
				person.changeStat(Stat.SANITY, pro.get_sanity());
				
				for (String to_client : client_table.getNames()) {
					if (!client_name.equals(to_client)) {
						client_table.getQueue(client_name).offer(packet);;
					}
				}
				
				break;

			case POSITION:
				for (String to_client : client_table.getNames()) {
					if (!client_name.equals(to_client)) {
						client_table.getQueue(to_client).offer(packet);
					}
				}
				
				get_player(client_name).updatePosition((Pos) packet.getData());
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
				try {
					from_client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		}
	}
	
	private void start_game() {
		
	}
	
	private Player get_player(String player_name) {
		ArrayList<Player> players = game_stats.getPlayers();
		
		for (Player player : players) {
			if (player.getId().equals(player_name)) {
				return player;
			}
		}
		
		return null;
	}
}