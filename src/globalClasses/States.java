package globalClasses;

/**
 * Client:
 * Send:
 * Play: null
 * Progress: (don't need to send any thing)
 * Position: Pos
 * Computer: Action
 * Coffie_maker: Action
 * Sofa: Action
 * Pool_table: Action
 * SABOTAGE_INTERRUPTED: (don't need to send any thing)
 * NIHGT_STARTED: (don't need to send any thing)
 * GAME_ENDED: (don't need to send any thing)
 * INITIAL_CONNECT: (don't need to send any thing)
 * UPDATE_LOBBY: (don't need to send any thing)
 * 
 * Receiver:
 * Play: (don't need to receiver any thing)
 * Progress: Packet
 * Position: Packet
 * Computer: Packet
 * Coffie_maker: Packet
 * Sofa: Packet
 * Pool_table: Packet
 * SABOTAGE_INTERRUPTED: null
 * NIHGT_STARTED: null
 * GAME_ENDED: (don't need to receiver any thing)
 * INITIAL_CONNECT: ClientConnect
 * UPDATE_LOBBY: See Ed
 * @author Edward Dean
 *
 */
public enum States {
	DISCONNECT, PLAY, PROGRESS, POSITION, COMPUTER, COFFIE_MAKER, SOFA, POOL_TABLE, SABOTAGE_INTERRUPTED, NIHGT_STARTED, GAME_ENDED, INITIAL_CONNECT, UPDATE_LOBBY;
}