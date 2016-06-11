package serverside;

import java.util.Set;
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

	private ConcurrentMap<String, PacketQueue> queueTable = new ConcurrentHashMap<String, PacketQueue>();

	/**
	 * Add a message queue containing the name of the sender and the message to
	 * be received by the recipient to the map
	 * 
	 * @param nickname
	 *            The nickname of the recipient
	 * @param msg
	 *            The message queue to be added to the map
	 */
	public synchronized void add(String nickname, PacketQueue msgQueue) {
		queueTable.put(nickname, msgQueue);
	}

	/**
	 * Get the queue from the map
	 * 
	 * @param nickname
	 *            The key for the map
	 * @return The queue
	 */
	public synchronized PacketQueue getQueue(String nickname) {
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
	 * Remove a specified key from the map
	 * 
	 * @param key
	 *            The key to remove
	 */
	public synchronized void remove(String key) {
		queueTable.remove(key);
	}
	
	public synchronized void changeStatus(String name, boolean status) {
		getQueue(name).setStatus(status);
	}
	
	public synchronized boolean getStatus(String name) {
		return getQueue(name).getStatus();
	}
	
	public synchronized Set<String> getNames() {
		return queueTable.keySet();
	}
}