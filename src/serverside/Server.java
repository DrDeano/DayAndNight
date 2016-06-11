package serverside;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import serverside.example.ServerSender;

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
		// The online status code
		boolean online = true;

		// Checks the number of arguments, if not one the throw exception
		if (args.length != 1) {
			throw new IllegalArgumentException("Usage: java Server portNumber");
		}

		// Create a new client table contain all details about the clients
		ClientTable client_table = new ClientTable();

		// Initialise a server socket
		ServerSocket server_socket = null;

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

				// Create a new message queue for the client that just connected
				PacketQueue packet_queue = new PacketQueue();

				// Be able to write to the client when starting the sender and
				// receiver thread
				ObjectOutputStream to_client = new ObjectOutputStream(client_socet.getOutputStream());
				
				// When client connects be able to receive the name of the
				// client
				ObjectInputStream from_client = new ObjectInputStream(client_socet.getInputStream());

				// Read the clients nickname
				String client_name = (String) from_client.readObject();

				// Check if the client connecting is in the client table. If not
				// then create a new user
				if (client_table.getQueue(client_name) == null) { // New user
					// Tell to client connection is successful
					to_client.writeObject(true);
					to_client.flush();

					// Add the client to the table
					client_table.add(client_name, packet_queue);

					// Change the status of the user to online
					client_table.changeStatus(client_name, online);

					// Create and start a new thread to read from the client
					Thread receiver = new Thread(new ServerReceiver(client_name, from_client, client_table));
					receiver.start();
					
					// Create and start a new thread to write to the client:
					Thread sender = new Thread(new ServerSender(client_table.getQueue(client_name), to_client));
					sender.start();
					
					// Check if the name is taken, if is close connection and
					// don't add
				} else if (client_table.getStatus(client_name) == online) {
					// Tell to client that there name already exists and choose
					// another
					to_client.writeObject(false);
					to_client.flush();
				} else { // Existing user
					// Tell to client connection is successful
					to_client.writeObject(true);
					to_client.flush();

					// Change the status of the user to online
//					client_table.changeStatus(clientName, online);

					// Create and start a new thread to read from the client
					Thread receiver = new Thread(new ServerReceiver(client_name, from_client, client_table));
					receiver.start();

					// Create and start a new thread to write to the client:
					(new ServerSender(client_table.getQueue(client_name), to_client)).start();
				}
			}
		} catch (IOException e) {
			server_socket.close();
			System.err.println(e);
		}
	}
}