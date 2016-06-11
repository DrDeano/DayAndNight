package serverside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

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
		ClientTable clientTable = new ClientTable();

		// Initialise a server socket
		ServerSocket serverSocket = null;

		// Initialise the server port to listen on
		int serverPort = 0;

		// See if the port is a number, if not: exit
		try {
			serverPort = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Second argument must be an integer");
		}

		// Check if the server socket can listen on the server port provided
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + args[0]);
			System.exit(1);
		}

		// Main program, if something is wrong, close the port and end
		try {
			while (true) {
				// Wait for a connection
				// Stuck until somebody connects
				Socket clientSocet = serverSocket.accept();

				// Create a new message queue for the client that just connected
				MessageQueue msgQueue = new MessageQueue();

				// When client connects be able to receive the name of the
				// client
				BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocet.getInputStream()));

				// Be able to write to the client when starting the sender and
				// receiver thread
				PrintStream toClient = new PrintStream(clientSocet.getOutputStream());

				// Read the clients nickname
				String clientName = fromClient.readLine();

				// Check if the client connecting is in the client table. If not
				// then create a new user
				if (clientTable.getQueue(clientName) == null) { // New user
					// Tell to client connection is successful
					toClient.println("true");
					toClient.println("Accepted " + clientName);

					// Add the client to the table
					clientTable.add(clientName, msgQueue);

					// Change the status of the user to online
					clientTable.changeStatus(clientName, online);

					// Create and start a new thread to read from the client
					(new ServerReceiver(clientName, fromClient, clientTable)).start();

					// Create and start a new thread to write to the client:
					(new ServerSender(clientTable.getQueue(clientName), toClient)).start();

					// Check if the name is taken, if is close connection and
					// don't add
				} else if (clientTable.getStatus(clientName) == online) {
					// Tell to client that there name already exists and choose
					// another
					toClient.println("false");
				} else { // Existing user
					// Tell to client connection is successful
					toClient.println("true");
					toClient.println("Accepted " + clientName);

					// Change the status of the user to online
					clientTable.changeStatus(clientName, online);

					// Create and start a new thread to read from the client
					(new ServerReceiver(clientName, fromClient, clientTable)).start();

					// Create and start a new thread to write to the client:
					(new ServerSender(clientTable.getQueue(clientName), toClient)).start();
				}
			}
		} catch (IOException e) {
			serverSocket.close();
			System.err.println(e);
		}
	}
}