package globalClasses;

public class ClientConnect {

	private boolean connection_status;
	private boolean loby_join;
	
	public ClientConnect(boolean status, boolean loby_join) {
		connection_status = status;
		this.loby_join = loby_join;
	}
	
	public boolean isConnected() {
		return connection_status;
	}
	
	public boolean had_joing_loby() {
		return loby_join;
	}
}