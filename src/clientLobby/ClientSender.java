package clientLobby;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;

import globalClasses.Action;
import globalClasses.ActionResponse;
import globalClasses.Pos;
import serverNetworking.Packet;

public class ClientSender implements Runnable {

	private ObjectOutputStream to_server;
	private String client_name;
	
	public ClientSender(ObjectOutputStream to_server, String client_name) {
		this.to_server = to_server;
		this.client_name = client_name;
	}

	@Override
	public void run() {
		
		
		switch (state) {
		case PLAY:

			break;

		case POSITION:

			break;

		case COMPUTER:

			break;

		case COFFIE_MAKER:

			break;

		case SOFA:
			
			break;

		case POOL_TABLE:

			break;

		case DISCONNECT:

			break;

		default:
			break;
		}
	}
}