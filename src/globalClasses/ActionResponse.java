package globalClasses;

import java.util.Optional;

public class ActionResponse {

	public Optional<Double> timeLeft;
	public boolean success;
	public ActionResponse(Optional<Double> timeLeft, boolean success) {
		this.timeLeft = timeLeft;
		this.success = success;
	}


}
