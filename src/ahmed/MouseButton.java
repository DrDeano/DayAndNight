package ahmed;

public class MouseButton {

	private String keyName;

	private boolean pressed = false;

	private long lastPress = -1;
	private long lastRelease = -1;

	private int clickX = -1;
	private int clickY = -1;
	private int releaseX = -1;
	private int releaseY = -1;

	private static int HOLD_LENGTH = 200;

	public MouseButton(String keyName) {
		this.keyName = keyName;
	}

	public boolean isPressed() {
		boolean pressed1 = pressed;
	//	pressed = false;
		return pressed1;
	}

	public boolean isHeld() {
		System.out.println(System.currentTimeMillis() - lastRelease);
		return pressed && System.currentTimeMillis() - lastRelease > HOLD_LENGTH;
	}

	public int clickX() {
		return clickX;
	}

	public int clickY() {
		return clickY;
	}

	public int releaseX() {
		return releaseX;
	}

	public int releaseY() {
		return releaseY;
	}

	public void toggle(boolean isPressed) {
		pressed = isPressed;
		if (pressed) {
			lastPress = System.currentTimeMillis();
			clickX = Input.MOUSE_X;
			clickY = Input.MOUSE_Y;
		} else {
			lastRelease = System.currentTimeMillis();
			releaseX = Input.MOUSE_X;
			releaseY = Input.MOUSE_Y;
		}
	}

	public String toString() {
		return keyName;
	}

}
