package gameConfiguration;

import serverLogic.CoffeeMachine;
import serverLogic.Computer;
import serverLogic.Pooltable;
import serverLogic.Sofa;

public enum MachineType {
	COFFEE(CoffeeMachine.class), SOFA(Sofa.class), COMPUTER(Computer.class), POOL(Pooltable.class);


	private final Class<?> type;

	private MachineType(Class<?> type) {
		this.type = type;
	}

	public Class<?> getType() {
		return type;
	}

}
