package gameConfiguration;

import java.util.ArrayList;

import serverLogic.CoffeeMachine;
import serverLogic.Computer;
import serverLogic.Sofa;
import serverLogic.Pooltable;

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
