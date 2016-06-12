package serverNetworking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import gameConfiguration.ConfigStorage;
import serverLogic.Game;

public class ServerLobby implements Runnable {

	private Game game;
	private int number_of_players;
	private ClientTable client_table;
	
	public ServerLobby() {
		game = new Game(ConfigStorage.getTestConfiguration(), this);
		number_of_players = 0;
		client_table = new ClientTable();
	}

	@Override
	public void run() {
		
	}
	
	public int get_number_of_players() {
		return number_of_players;
	}
	
	public void add_player(String player, ObjectOutputStream to_client, ObjectInputStream from_client) {
		number_of_players++;
		client_table.add(player, new PacketQueue());
		game.newPlayer(player);
		
		// Create and start a new thread to write to the client:
		ServerSender basicSender = new ServerSender(client_table.getQueue(player), to_client);
		Thread sender = new Thread(basicSender);
		sender.start();

		game.newPlayer(player);

		// Create and start a new thread to read from the client
		Thread receiver = new Thread(new ServerReceiver(player, from_client, client_table, game));
		receiver.start();
	}
	
	public Collection<String> get_players() {
		return game.getAlldIds();
	}
	
	public void sendToClient(String clientName, Packet packet) {
		client_table.getQueue(clientName).offer(packet);
	}
}