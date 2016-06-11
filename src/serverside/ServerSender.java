package serverside;

import java.io.PrintStream;

/**
 * Continuously reads from message queue for a particular client the forward to
 * the client
 * 
 * @author The_Dean
 *
 */
public class ServerSender extends Thread {

	private MessageQueue queue;
	private PrintStream toClient;

	/**
	 * Create a new server sender with the message queue and the ability to talk
	 * to the client receiver
	 * 
	 * @param q
	 *            The message queue
	 * @param c
	 *            Be able to talk to the client receiver
	 * @param name
	 *            The clients name
	 */
	public ServerSender(MessageQueue q, PrintStream c) {
		queue = q;
		toClient = c;
	}

	/**
	 * The main method that override the run method in thread
	 */
	public void run() {
		// Infinite loop taking messages off the queue and processing them
		while (true) {
			// Takes the first item off the queue and removes it
			Message msg = queue.take();

			// Get the command from the message to know what to do with the
			// message
			int command = msg.getCommand();

			if (command == 0) { // End program
				// Set the online status to false as I'm disconnecting
				queue.setStatus(false);

				// Tell the client to end
				toClient.println(command);

				// End myself
				break;
			} else if (command == 1) { // online
				// Send command to client receiver so know what to do next
				toClient.println(command);

				// Send the length of the array
				toClient.println(msg.getMsgArray().length);

				// Send the name of the people online
				for (String name : msg.getMsgArray()) {
					toClient.println(name);
				}
			} else if (command == 2) { // talkTo <name> <message>
				// Send command to client receiver so know what to do next
				toClient.println(command);

				// Send the chat message
				toClient.println(msg.toString());
			} else if (command == 3) { // play <name>
				// Add sender to request list
				queue.addRequest(msg.getName());

				// Send command to client receiver so know what to do next
				toClient.println(command);

				// Send the name of the opponent
				toClient.println(msg.getName());

				// Tell client that you have received a request to play a game
				toClient.println(msg.getMsg());
			} else if (command == 4) { // accept <name>
				// Remove the request as the game has started
				if (queue.containsRequest(getName(msg.getMsg()))) {
					queue.removeRequest(getName(msg.getMsg()));
				}

				// Change the play status to true
				queue.setIsPlaying(true);

				// Tell the client to start the tic tac toe game
				toClient.println(command);

				// Send the name of the opponent
				toClient.println(getName(msg.getMsg()));

				// Send who goes first
				toClient.println(getY(msg.getMsg()));
			} else if (command == 5) { // decline <name>
				// Send the command to print the message
				toClient.println(command);

				// Send the message to the client receiver
				toClient.println(msg.getMsg());
			} else if (command == 6) { // showRequests
				// Send command to client receiver so know what to do next
				toClient.println(command);

				// Send the length of the array
				toClient.println(queue.getRequests().length);

				// Send the all the requests to the client
				for (String req : queue.getRequests()) {
					toClient.println(req);
				}
			} else if (command == 7) { // scores <name>
				// Send the command to the client receiver
				toClient.println(command);

				// Send the message to the client receiver
				toClient.println(msg.getMsg());
			} else if (command == 8) { // allScores
				// Send command to client receiver so know what to do next
				toClient.println(command);

				// Send the length of the array
				toClient.println(msg.getMsgArray().length);

				// Send the scores
				for (String name : msg.getMsgArray()) {
					toClient.println(name);
				}
			} else if (command == 9) { // checkOnline
				// Send command to client receiver so know what to do next
				toClient.println(command);

				// Send the message to the client
				toClient.println(msg.getMsg());
			} else if (command == 11) { // playing the game
				// Get the x position from the message
				int x = getX(msg.getMsg());

				// Get the y position from the message
				int y = getY(msg.getMsg());

				// Send command to client receiver so know what to do next
				toClient.println(command);

				// Send the x, y position of the opponents play to the client
				toClient.println(x);
				toClient.println(y);
			} else if (command == 12) { // Who has won
				// Set play status to false
				queue.setIsPlaying(false);

				// Send command to client receiver so know what to do next
				toClient.println(command);

				// Tell the client who won
				toClient.println(msg.getMsg());
			} else if (command == 13) { // End the current game
				// Set play status to false
				queue.setIsPlaying(false);

				// Send command to client receiver so know what to do next
				toClient.println(command);

				// Send the person who closed the game
				toClient.println(msg.getName());
			} else if (command == 20) { // General notifications
				// Send command to client receiver so know what to do next
				toClient.println(command);

				// Send the message to the client
				toClient.println(msg.getMsg());
			}
		}
	}

	/**
	 * Return the name of the opponent from the message
	 * 
	 * @param input
	 *            The raw input from the message
	 * @return The name of the opponent
	 */
	private String getName(String input) {
		// The final name that will be returned
		String name = "";

		// A temporary character place holder
		char c;

		// Loop through the message provided
		for (int i = 0; i <= input.length() - 1; i++) {
			// Character at position i
			c = input.charAt(i);

			// If a space is found then return the name so far else add the
			// character to the temporary string as the name is a single
			// string of characters
			if (c == ' ') {
				return name;
			} else {
				name += c;
			}
		}

		// This won't happen, just to make the program compile
		return name;
	}

	/**
	 * Get the y position from the message (the second argument)
	 * 
	 * @param input
	 *            The raw input from the message
	 * @return The y position as integer
	 */
	private int getY(String input) {
		// The final y position that will be returned
		String y = "";

		// A temporary character place holder
		char c;

		// Loop through the message provided
		for (int i = 0; i <= input.length() - 1; i++) {
			// Character at position i
			c = input.charAt(i);

			// If a space is found then return the y position so far else add
			// the character to the temporary string as the y position is a
			// single string of characters
			if (c == ' ') {
				for (int j = i + 1; j <= input.length() - 1; j++) {
					c = input.charAt(j);
					if (c != ' ') {
						y += c;
					} else {
						return Integer.parseInt(y);
					}
				}
			}
		}

		// This wouldn't happen, just to make the program compile
		return Integer.parseInt(y);
	}

	/**
	 * Get the x position from the message (the first argument)
	 * 
	 * @param input
	 *            The raw input from the message
	 * @return The x position as integer
	 */
	private int getX(String input) {
		// The final x position that will be returned
		String x = "";

		// A temporary character place holder
		char c;

		// Loop through the message provided
		for (int i = 0; i <= input.length() - 1; i++) {
			// Character at position i
			c = input.charAt(i);

			// If a space is found then return the x position so far else add
			// the character to the temporary string as the x position is a
			// single string of characters
			if (c == ' ') {
				return Integer.parseInt(x);
			} else {
				x += c;
			}
		}

		// This won't happen, just to make the program compile
		return Integer.parseInt(x);
	}
}