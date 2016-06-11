package serverside;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PacketQueue {

	private BlockingQueue<Packet> queue = new LinkedBlockingQueue<Packet>();
	private boolean status = false;
	
	/**
	 * Add a specified message into this queue
	 * 
	 * @param msg
	 *            The message to be added
	 */
	public void offer(Packet data) {
		queue.offer(data);
	}
	
	/**
	 * Retrieves and removes the head of this queue waiting if necessary until
	 * an element becomes available
	 * 
	 * @return The first item of the queue
	 */
	public Packet take() {
		while (true) {
			try {
				return (queue.take());
			} catch (InterruptedException e) {
				System.err.println("Message queue interrupted");
				System.err.println(e);
			}
		}
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
}