package globalClasses;

import java.util.EnumSet;

public enum States {
	DISCONNECT, PLAY, PROGRESS, POSITION, S_COMPUTER, S_COFFIE_MAKER, S_SOFA, S_POOL_TABLE;
	
	public static EnumSet<States> none_initial() {
		return EnumSet.noneOf(States.class);
	}
}