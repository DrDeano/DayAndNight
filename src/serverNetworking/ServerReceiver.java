package serverNetworking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;

import globalClasses.Action;
import globalClasses.ActionResponse;
import globalClasses.Pos;
import globalClasses.States;
import serverLogic.Game;

public class ServerReceiver implements Runnable {

	private ObjectInputStream from_client;
	private ClientTable client_table;
	private String client_name;
	private Game game;
	private ServerLobby lobby;

	public ServerReceiver(String n, ObjectInputStream c, ClientTable t, Game game, ServerLobby lobby) {
		client_name = n;
		from_client = c;
		client_table = t;
		this.game = game;
		this.lobby = lobby;
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
				lobby.set_is_playing(true);
				game.start();
				for (String to_client : client_table.getNames()) {
					client_table.getQueue(to_client).offer(packet);
				}
				break;

			case POSITION:
				for (String to_client : client_table.getNames()) {
					if (!client_name.equals(to_client)) {
						client_table.getQueue(to_client).offer(packet);
					}
				}

				game.updatePosition(client_name, (Pos) packet.getData());
				break;

			case COMPUTER:
				Action computer = (Action) packet.getData();
				Optional<ActionResponse> computer_response = game.tryAction(client_name, computer);
				if (computer_response.isPresent()) {
					Packet new_packet = new Packet(client_name, state, computer_response.get());
					client_table.getQueue(client_name).offer(new_packet);
				}
				break;

			case COFFIE_MAKER:
				Action coffie_maker = (Action) packet.getData();
				Optional<ActionResponse> coffie_maker_response = game.tryAction(client_name, coffie_maker);
				if (coffie_maker_response.isPresent()) {
					Packet new_packet = new Packet(client_name, state, coffie_maker_response.get());
					client_table.getQueue(client_name).offer(new_packet);
				}
				break;

			case SOFA:
				Action sofa = (Action) packet.getData();
				Optional<ActionResponse> sofa_response = game.tryAction(client_name, sofa);
				if (sofa_response.isPresent()) {
					Packet new_packet = new Packet(client_name, state, sofa_response.get());
					client_table.getQueue(client_name).offer(new_packet);
				}
				break;

			case POOL_TABLE:
				Action poo_table = (Action) packet.getData();
				Optional<ActionResponse> poo_table_response = game.tryAction(client_name, poo_table);
				if (poo_table_response.isPresent()) {
					Packet new_packet = new Packet(client_name, state, poo_table_response.get());
					client_table.getQueue(client_name).offer(new_packet);
				}
				break;

			case DISCONNECT:
				client_table.getQueue(client_name).offer(packet);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
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
}