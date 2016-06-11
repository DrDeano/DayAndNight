package serverside.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Each nickname has a different incoming-message queue, online status and
 * score. Each client has it's own queue with messages
 * 
 * @author The_Dean
 *
 */
public class ClientTable {

	private ConcurrentMap<String, MessageQueue> queueTable = new ConcurrentHashMap<String, MessageQueue>();

	/**
	 * Add a message queue containing the name of the sender and the message to
	 * be received by the recipient to the map
	 * 
	 * @param nickname
	 *            The nickname of the recipient
	 * @param msg
	 *            The message queue to be added to the map
	 */
	public synchronized void add(String nickname, MessageQueue msgQueue) {
		queueTable.put(nickname, msgQueue);
	}

	/**
	 * Get the queue from the map
	 * 
	 * @param nickname
	 *            The key for the map
	 * @return The queue
	 */
	public synchronized MessageQueue getQueue(String nickname) {
		return queueTable.get(nickname);
	}

	/**
	 * Returns true if the key (nickname) is in the map and false if the
	 * nickname isn't in the map
	 * 
	 * @param nickname
	 *            A key to be checked in the map
	 * @return Whether the key (nickname) is in the map or not
	 */
	public synchronized boolean contains(String nickname) {
		return queueTable.containsKey(nickname);
	}

	/**
	 * Return the number of people that are online
	 * 
	 * @return The number of people online
	 */
	private synchronized int onlineNum() {
		int i = 0;

		// Loop through all the keys in the table and if their status is
		// true(online) then increment a counter
		for (String s : queueTable.keySet()) {
			if (getStatus(s)) {
				i++;
			}
		}

		// Return the number of people online
		return i;
	}

	/**
	 * Return all the names on the server
	 * 
	 * @param name
	 *            Exclude this from the list of people online as to not include
	 *            myself
	 * 
	 * @return All the names on the server
	 */
	public synchronized String[] allNames(String name) {
		// Temporary place holder for all the names on the server
		String[] temp = new String[onlineNum() - 1];

		// This is used for the array index
		int i = 0;

		// Loop through all the keys (names of clients connected to the server)
		for (String key : queueTable.keySet()) {

			// If the name isn't myself and is online then add it to the
			// temporary place holder
			if (getStatus(key) && !key.equals(name)) {
				temp[i] = key;
				i++;
			}
		}

		// Return all the names on the server
		// If you are the only person on the server return empty string
		return temp;
	}

	/**
	 * Return all the scores on the server
	 * 
	 * @return All the scores
	 */
	public synchronized String[] allScores() {
		// Temporary place holder for the scores
		String[] temp = new String[queueTable.size()];

		// This is used for the array index
		int i = 0;

		// Loop through all the keys (names of clients connected to the server)
		// and add the scores to the temporary string
		for (String key : queueTable.keySet()) {
			// Add all the scores to a temporary string
			temp[i] = key + " " + getQueue(key).scoresToString();
			i++;
		}

		// Return the names and their scores
		return temp;
	}

	/**
	 * Remove a specified key from the map
	 * 
	 * @param key
	 *            The key to remove
	 */
	public synchronized void remove(String key) {
		queueTable.remove(key);
	}

	/**
	 * Change the online status of the user
	 * 
	 * @param status
	 *            The new online status of the user
	 * @param name
	 *            The name of the client
	 */
	public synchronized void changeStatus(String name, boolean status) {
		getQueue(name).setStatus(status);
	}

	/**
	 * Return the online status of the client
	 * 
	 * @param name
	 *            The name of the client
	 * @return The online status of the client
	 */
	public synchronized boolean getStatus(String name) {
		return getQueue(name).getStatus();
	}

	/**
	 * Check whether a specified name is online
	 * 
	 * @param name
	 *            The name to check
	 * @return Whether the name is online or not
	 */
	public boolean checkOnline(String name) {
		if (getQueue(name) == null) {
			return false;
		} else {
			if (getStatus(name)) {
				return true;
			} else {
				return false;
			}
		}
	}
}