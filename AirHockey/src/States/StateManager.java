package States;

public class StateManager {
	public enum STATES {
		GAME,
		MENU
	}

	public STATES getState() {

		return State;
	}

	public void setState(STATES state) {

		State = state;
	}

	private STATES State = STATES.MENU;

}
