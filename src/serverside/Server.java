package serverside;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import serverLogic.Game;

/**
 * The main server that listens for clients to connect and create a server
 * sender and receiver for each client. Usage: java Server portNumber
 * 
 * @author The_Dean
 *
 */
public class Server {

	/**
	 * The main method when the program first starts
	 * 
	 * @param args
	 *            The arguments form the user
	 * @throws IOException
	 *             If the server can't listen on that port
	 * @throws IllegalArgumentException
	 *             Must only have one argument and is must be a number
	 */
	public static void main(String[] args) throws IOException, IllegalArgumentException {
		// Checks the number of arguments, if not one the throw exception
		if (args.length != 1) {
			throw new IllegalArgumentException("Usage: java Server portNumber");
		}

		// Create a new client table contain all details about the clients
		ClientTable client_table = new ClientTable();

		// Initialise a server socket
		ServerSocket server_socket = null;

		Game game = new Game();
		int number_of_players = 0;

		// Initialise the server port to listen on
		int server_port = 0;

		// See if the port is a number, if not: exit
		try {
			server_port = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Second argument must be an integer");
		}

		// Check if the server socket can listen on the server port provided
		try {
			server_socket = new ServerSocket(server_port);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + args[0]);
			System.exit(1);
		}

		// Main program, if something is wrong, close the port and end
		try {
			while (true) {
				// Wait for a connection
				// Stuck until somebody connects
				Socket client_socet = server_socket.accept();
				number_of_players++;

				// Create a new message queue for the client that just connected
				PacketQueue packet_queue = new PacketQueue();

				// Be able to write to the client when starting the sender and
				// receiver thread
				ObjectOutputStream to_client = new ObjectOutputStream(client_socet.getOutputStream());

				// When client connects be able to receive the name of the
				// client
				ObjectInputStream from_client = new ObjectInputStream(client_socet.getInputStream());

				// Read the clients nickname
				String client_name = "";
				try {
					client_name = (String) from_client.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				// Check if the client connecting is in the client table. If not
				// then create a new user
				if (client_table.getQueue(client_name) == null) { // New user
					// Tell to client connection is successful
					to_client.writeObject("Name accepted: added");
					to_client.flush();

					game.newPlayer(client_name);

					// Add the client to the table
					client_table.add(client_name, packet_queue);

					// Create and start a new thread to read from the client
					Thread receiver = new Thread(new ServerReceiver(client_name, from_client, client_table, game));
					receiver.start();

					// Create and start a new thread to write to the client:
					Thread sender = new Thread(new ServerSender(client_table.getQueue(client_name), to_client));
					sender.start();
				} else if (number_of_players > 6) {
					to_client.writeObject("Loby full try later");
					to_client.flush();
					number_of_players--;
				} else {
					// Tell to client that there name already exists and choose
					// another
					to_client.writeObject("Name taken, choose another");
					to_client.flush();
					number_of_players--;
				}
			}
		} catch (IOException e) {
			server_socket.close();
			System.err.println(e);
		}
	}
}