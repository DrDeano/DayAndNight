package serverside;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A blocking queue that contains messages to clients and their scores and
 * playing status and online status
 * 
 * @author The_Dean
 *
 */
public class MessageQueue {

	// Using a linked list as the queue
	private BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
	private List<String> requests = new ArrayList<String>();
	private int wins = 0;
	private int losses = 0;
	private int draws = 0;

	// The status of the client
	// true - online
	// false - offline
	private boolean status = false;
	private boolean isPlaying = false;

	/**
	 * Add a specified message into this queue
	 * 
	 * @param msg
	 *            The message to be added
	 */
	public void offer(Message msg) {
		queue.offer(msg);
	}

	/**
	 * Retrieves and removes the head of this queue waiting if necessary until
	 * an element becomes available
	 * 
	 * @return The first item of the queue
	 */
	public Message take() {
		while (true) {
			try {
				return (queue.take());
			} catch (InterruptedException e) {
				System.err.println("Message queue interrupted");
				System.err.println(e);
			}

		}
	}

	/**
	 * Return the status of the client
	 * 
	 * @return The status of the client
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * Set the online status of the client
	 * 
	 * @param status
	 *            The new online status of the client
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Return the playing status (whether he/she is playing another player)
	 * 
	 * @return The playing status of the player
	 */
	public boolean getIsPlaying() {
		return isPlaying;
	}

	/**
	 * Set the playing status of the player
	 * 
	 * @param isPlay
	 *            The change condition
	 */
	public void setIsPlaying(boolean isPlay) {
		isPlaying = isPlay;
	}

	/**
	 * Get the number of wins that the player has had
	 * 
	 * @return The number of wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Increase the number of wins by 1
	 */
	public void increasesWins() {
		this.wins++;
	}

	/**
	 * Get the number of losses that the player has had
	 * 
	 * @return The number of losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * Increase the number of losses by 1
	 */
	public void increasesLosses() {
		this.losses++;
	}

	/**
	 * Get the number of draws
	 * 
	 * @return The number of draws
	 */
	public int getDraws() {
		return this.draws;
	}

	/**
	 * Increase the number of draws by 1
	 */
	public void increasesDraws() {
		this.draws++;
	}

	/**
	 * Return a string that contains the scores
	 * 
	 * @return The scores as a string
	 */
	public String scoresToString() {
		return " wins: " + getWins() + ", losses: " + getLosses() + ", draws: " + getDraws();
	}

	/**
	 * Add a request to the client
	 * 
	 * @param name
	 *            The name of the person who sent the request
	 */
	public void addRequest(String name) {
		requests.add(name);
	}

	/**
	 * Remove a request
	 * 
	 * @param name
	 *            The name to remove
	 */
	public void removeRequest(String name) {
		requests.remove(name);
	}

	/**
	 * Check whether the request list contains a specified name
	 * 
	 * @param name
	 *            The name to check whether in the list
	 * @return True if the person is in the list and false if not
	 */
	public boolean containsRequest(String name) {
		return requests.contains(name);
	}

	/**
	 * Return the requests list
	 * 
	 * @return The requests list
	 */
	public String[] getRequests() {
		return requests.toArray(new String[requests.size()]);
	}
}