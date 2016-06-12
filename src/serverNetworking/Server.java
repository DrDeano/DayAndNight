package serverNetworking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import globalClasses.ClientConnect;
import globalClasses.States;

/** The main server that listens for clients to connect and create a server
 * sender and receiver for each client. Usage: java Server portNumber
 * 
 * @author The_Dean */
public class Server {

//	public static ClientTable client_table = new ClientTable();
	private static ArrayList<ServerLoby> server_lobbies = new ArrayList<ServerLoby>();
	private static int lobby_count = -1;

	/** The main method when the program first starts
	 * 
	 * @param args
	 *        The arguments form the user
	 * @throws IOException
	 *         If the server can't listen on that port
	 * @throws IllegalArgumentException
	 *         Must only have one argument and is must be a number */
	public static void main(String[] args) throws IOException, IllegalArgumentException {
		// Checks the number of arguments, if not one the throw exception
		if (args.length != 1) {
			throw new IllegalArgumentException("Usage: java Server portNumber");
		}

		// Initialise a server socket
		ServerSocket server_socket = null;
		
		server_lobbies.add(new ServerLoby());
		lobby_count++;
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

				// Be able to write to the client when starting the sender and
				// receiver thread
				ObjectOutputStream to_client = new ObjectOutputStream(client_socet.getOutputStream());

				// When client connects be able to receive the name of the
				// client
				ObjectInputStream from_client = new ObjectInputStream(client_socet.getInputStream());

				// Read the clients nickname
				String client_name = "";
				int lobby_index = 0;
				try {
					client_name = (String) from_client.readObject();
					lobby_index = (int) from_client.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				if (client_table.getQueue(client_name) == null) { // New user
					// Tell to client connection is successful
					
					if (lobby_index > lobby_count) { // new lobby
						to_client.writeObject(new Packet("Main server", States.INITIAL_CONNECT, new ClientConnect(true, true)));
						to_client.flush();
						
						server_lobbies.add(new ServerLoby());
						
						server_lobbies.get(lobby_index).add_player(client_name, to_client, from_client);
					} else {
						if (server_lobbies.get(lobby_index).get_number_of_players() > 6) {
							to_client.writeObject(new Packet("Main server", States.INITIAL_CONNECT, new ClientConnect(true, false)));
							to_client.flush();
						} else {
							to_client.writeObject(new Packet("Main server", States.INITIAL_CONNECT, new ClientConnect(true, true)));
							to_client.flush();
							
							server_lobbies.get(lobby_index).add_player(client_name, to_client, from_client);
						}	
					}
				} else {
					// Tell to client that there name already exists and choose
					// another
					to_client.writeObject(new Packet("Main server", States.INITIAL_CONNECT, new ClientConnect(false, false)));
					to_client.flush();
				}
			}
		} catch (IOException e) {
			server_socket.close();
			System.err.println(e);
		}
	}
}