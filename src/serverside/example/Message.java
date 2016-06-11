package serverside.example;

/**
 * A class used to store a message to be received by a client. Also contains a
 * command that tells the server sender what to do with the message
 * 
 * @author The_Dean
 *
 */
public class Message {

	private String msg;
	private String[] msg2;
	private String fromName;
	private int command;

	/**
	 * Create a new message class
	 * 
	 * @param name
	 *            The sender
	 * @param str
	 *            The message
	 * @param command
	 *            The command that has been sent from the user so that the
	 *            server sender knows what to do with the message
	 */
	public Message(String name, String str, int command) {
		this.msg = str;
		this.fromName = name;
		this.command = command;
	}

	/**
	 * Create a new message class
	 * 
	 * @param name
	 *            The sender
	 * @param str
	 *            The message array
	 * @param command
	 *            The command that has been sent from the user so that the
	 *            server sender knows what to do with the message
	 */
	public Message(String name, String[] str, int command) {
		this.msg2 = str;
		this.fromName = name;
		this.command = command;
	}

	/**
	 * Get the message for the receiver
	 * 
	 * @return The message for the receiver
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Get the message for the receiver
	 * 
	 * @return The message for the receiver
	 */
	public String[] getMsgArray() {
		return msg2;
	}

	/**
	 * Get the name of the sender
	 * 
	 * @return The name of the sender
	 */
	public String getName() {
		return fromName;
	}

	/**
	 * Return the command that was entered by the user to send this message
	 * 
	 * @return The command
	 */
	public int getCommand() {
		return command;
	}

	/**
	 * A to string method for displaying the receiver and the message
	 */
	public String toString() {
		return "From " + fromName + ": " + msg;
	}
}