package serverside.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

/**
 * The server receiver thread for collecting data from the client then
 * forwarding them to the appropriate client
 * 
 * @author The_Dean
 *
 */
public class ServerReceiver extends Thread {

	private String clientsName;
	private BufferedReader fromClient;
	private ClientTable clientTable;

	/**
	 * Create a new server receiver class when a client connects
	 * 
	 * @param n
	 *            The nickname of the client
	 * @param c
	 *            The buffered reader for reading inputs to the thread
	 * @param t
	 *            The client table holding all the clients online and messages
	 *            for that client
	 */
	public ServerReceiver(String n, BufferedReader c, ClientTable t) {
		clientsName = n;
		fromClient = c;
		clientTable = t;
	}

	/**
	 * Return all the people that are online (on the server)
	 * 
	 * @param clientName
	 *            The name of the client connected to this server so not to
	 *            print itself
	 * @return All the people online
	 */
	public String[] online(String clientName) {
		return clientTable.allNames(clientName);
	}

	/**
	 * Add a message to the queue then to the client table to be received be the
	 * client that this message is intended for
	 * 
	 * @param name
	 *            The recipient
	 * @param message
	 *            The message for the recipient
	 * @param command
	 *            The command for the message
	 */
	public void addToTable(String name, Message message) {
		// Get the queue of the recipient
		MessageQueue whoQueue = clientTable.getQueue(name);

		// Check whether the recipient is in the client table
		if (whoQueue != null) {

			// Add the message to the queue
			whoQueue.offer(message);

			// Add the queue to the client table with the new message
			clientTable.add(name, whoQueue);
		} else {
			// Get the queue of the client
			MessageQueue clientQueue = clientTable.getQueue(clientsName);

			// The message to be sent to
			Message whoMessage = new Message(clientsName, name + " doesn't exist", 1);

			// Add the message to the queue
			clientQueue.offer(whoMessage);

			// Add the queue to the client table with the new message
			clientTable.add(clientsName, clientQueue);
		}
	}

	/**
	 * Return all the scores from the score table to the person how requested
	 * them
	 * 
	 * @return All the scores for all the people
	 */
	public String[] allScores() {
		return clientTable.allScores();
	}

	/**
	 * Check if the name provided is online
	 * 
	 * @param name
	 *            The name to check
	 * @return True if the user is online an false if not
	 */
	public boolean checkOnline(String name) {
		return clientTable.checkOnline(name);
	}

	/**
	 * Overrides the thread's run method - the main program
	 */
	public void run() {
		// The message from the client sender
		String input;

		// The command sent from the client sender
		int command;

		try {
			// Read the command sent from the client sender and check that it
			// isn't Null: End program
			while ((input = fromClient.readLine()) != null) {
				// Convert the message from the client sender to a command
				command = Integer.parseInt(input);

				if (command == 0) { // End program
					// Send the close command to myself
					addToTable(clientsName, new Message(clientsName, "", command));

					// End the server receiver thread
					break;
				} else if (command == 1) { // online
					// Send a message to itself with all the people online
					addToTable(clientsName, new Message(clientsName, online(clientsName), command));
				} else if (command == 2) { // talkTo <name> <message>
					// Receive the recipient and message from the client sender
					String toName = fromClient.readLine();
					String msg = fromClient.readLine();

					// Check what has been send from the client isn't null
					if (toName != null && msg != null) {
						// Check if the player is online
						if (checkOnline(toName)) {
							// Send the message to the recipient
							addToTable(toName, new Message(clientsName, msg, command));
						} else {
							// This person isn't online
							addToTable(clientsName, new Message(clientsName, toName + " isn't online", 9));
						}
					} else {
						throw new IOException();
					}
				} else if (command == 3) { // play <name>
					// Receive the recipient from the client sender
					String toName = fromClient.readLine();

					// Check what has been send from the client isn't null
					if (toName != null) {
						// Get the queue for the recipient
						MessageQueue toClientQueue = clientTable.getQueue(toName);

						// Check if the recipient exist
						if (toClientQueue != null) {
							// Check if the recipient is online
							if (toClientQueue.getStatus() == true) {
								// Send the message to the recipient
								addToTable(toName,
										new Message(clientsName, clientsName + " would like to play a game", command));
							} else {
								// Tell me that the person received isn't online
								addToTable(clientsName, new Message(clientsName, toName + " isn't online", 20));
							}
						} else {
							// Tell me that the person received doesn't exist
							addToTable(clientsName, new Message(clientsName, toName + " doesn't exist", 20));
						}
					} else {
						throw new IOException();
					}
				} else if (command == 4) { // accept <name>
					// Receive the recipient from the client sender
					String toName = fromClient.readLine();

					// Check what has been send from the client isn't null
					if (toName != null) {
						// Get the queue for the recipient
						MessageQueue toClientQueue = clientTable.getQueue(toName);

						// Check if the recipient exist
						if (toClientQueue != null) {
							// Check if the recipient is online
							if (toClientQueue.getStatus() == true) {
								// Check if the player you accepted is already
								// in a game
								if (toClientQueue.getIsPlaying() != true) {
									// Check if is in the requests list
									if (clientTable.getQueue(clientsName).containsRequest(toName)) {
										// Random starting player
										Random ran = new Random();
										double player1 = ran.nextInt(1000);
										double player2 = ran.nextInt(1000);

										// Decide who goes first
										if (player1 > player2) {
											// Player 1 plays first

											// Tell both clients to start the
											// game
											addToTable(clientsName, new Message(clientsName, toName + " 1", command));
											addToTable(toName, new Message(clientsName, clientsName + " 2", command));
										} else { // Player 2 plays first
											// Tell both clients to start the
											// game
											addToTable(clientsName, new Message(clientsName, toName + " 2", command));
											addToTable(toName, new Message(clientsName, clientsName + " 1", command));
										}
									} else {
										// Tell the client that you don't have a
										// request from that person
										addToTable(clientsName, new Message(clientsName,
												"You don't have a request from " + toName, 20));
									}
								} else {
									// If the player is already in a game, you
									// will have to wait
									addToTable(clientsName, new Message(clientsName,
											toName + " is already playing a game. Try later", 20));
								}
							} else {
								// Tell the client that that player isn't online
								addToTable(clientsName, new Message(clientsName, toName + " isn't online", 20));
							}
						} else {
							// Tell the client that player doesn't exist
							addToTable(clientsName, new Message(clientsName, toName + " doesn't exist", 20));
						}
					} else {
						throw new IOException();
					}
				} else if (command == 5) { // decline <name>
					// Receive the recipient from the client sender
					String toName = fromClient.readLine();

					// Check what has been send from the client isn't null
					if (toName != null) {
						// Check if I have a request to decline
						if (clientTable.getQueue(clientsName).containsRequest(toName)) {
							// Check if the recipient is online where if the
							// queue is null then not online

							// Get the queue for the recipient
							MessageQueue toClientQueue = clientTable.getQueue(toName);

							// Check if the queue isn't null as the player might
							// not
							// exist
							if (toClientQueue != null) {
								// Request declined
								addToTable(toName, new Message(clientsName,
										clientsName + " has declines your request to a game", command));

								// Remove sender's name from the request list
								clientTable.getQueue(clientsName).removeRequest(toName);
							} else {
								// This person isn't online, send message back
								// to the client saying this person isn't online
								addToTable(clientsName, new Message(clientsName, toName + " doesn't exist", 20));
							}
						} else {
							// Tell the client that you don't have a request
							// from that person
							addToTable(clientsName,
									new Message(clientsName, "You don't have a request from " + toName, 20));
						}
					} else {
						throw new IOException();
					}
				} else if (command == 6) { // showRequests
					// Send a message to myself to print the requests that I've
					// received
					addToTable(clientsName, new Message(clientsName, "The list of requests", command));
				} else if (command == 7) { // scores <name>
					// Get the name of the person to get the scores
					String toName = fromClient.readLine();

					// Check what has been send from the client isn't null
					if (toName != null) {
						// Get the queue from the name given
						MessageQueue queue = clientTable.getQueue(toName);

						// Check if the queue isn't null as the player might not
						// exist
						if (queue != null) {
							// Send a message to myself contain the score of the
							// person provided
							addToTable(clientsName, new Message(clientsName, toName + queue.scoresToString(), command));
						} else {
							// This person isn't online to send message back to
							// the client sender saying this person isn't online
							addToTable(clientsName, new Message(clientsName, toName + " doesn't exist", 20));
						}
					} else {
						throw new IOException();
					}
				} else if (command == 8) { // allScores
					// Send a message to itself with all the people online
					addToTable(clientsName, new Message(clientsName, allScores(), command));
				} else if (command == 9) { // checkOnline <name>
					// Get the name to check
					String toName = fromClient.readLine();

					// Check if not null
					if (toName != null) {
						// Send a message to myself contain whether the player
						// provided is online
						addToTable(clientsName,
								new Message(clientsName, Boolean.toString(checkOnline(toName)), command));
					} else {
						throw new IOException();
					}
				} else if (command == 11) { // playing the game
					// Receive the name of the opponent
					String toName = fromClient.readLine();

					// Receive the x, y position that I played
					String x = fromClient.readLine();
					String y = fromClient.readLine();

					// Check what has been send from the client isn't null
					if (toName != null && x != null && y != null) {
						// Convert the string x, y to an integer
						int X = Integer.parseInt(x);
						int Y = Integer.parseInt(y);

						// Add the move to the opponents queue
						addToTable(toName, new Message(clientsName, X + " " + Y, command));
					} else {
						throw new IOException();
					}
				} else if (command == 12) { // Who has won
					// Receive whether I have won, lost or draw with the
					// opponent
					String answer = fromClient.readLine();
					String opponent = fromClient.readLine();

					// Check what has been send from the client isn't null
					if (answer != null && opponent != null) {
						// Get the queue of me
						MessageQueue obj = clientTable.getQueue(clientsName);

						// Check if I have won, lost or draw
						if (answer.equals("y")) { // I have won
							// +1 to wins
							obj.increasesWins();

							// Send a message to myself saying that I've won
							addToTable(clientsName, new Message(clientsName, "You have won", command));
						} else if (answer.equals("n")) { // I have lost
							// +1 to losses
							obj.increasesLosses();

							// Send a message to myself saying that I've lost
							addToTable(clientsName, new Message(clientsName, opponent + " has won", command));
						} else if (answer.equals("d")) { // I have draw
							// +1 to draw
							obj.increasesDraws();

							// Send a message to myself saying that I've draw
							addToTable(clientsName, new Message(clientsName, "It was a draw", command));
						}

						// Update scores in GUI
						addToTable(clientsName, new Message(clientsName, allScores(), 8));
					} else {
						throw new IOException();
					}
				} else if (command == 13) { // game ended
					// Receive the opponent
					String oppenent = fromClient.readLine();

					// Check what has been received isn't null
					if (oppenent != null) {
						// Tell both clients playing the game to close
						Message message = new Message(clientsName, "", command);
						addToTable(clientsName, message);
						addToTable(oppenent, message);
					} else {
						throw new IOException();
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Something went wrong with the client " + clientsName);
			System.err.println(e);
		}
	}
}