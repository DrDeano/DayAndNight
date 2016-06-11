package ahmed;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	public static Set<Key> keys = new LinkedHashSet<Key>();

	public static ArrayList<Key> pressed = new ArrayList<Key>();
	public static ArrayList<Key> released = new ArrayList<Key>();

	public static HashMap<Character, Key> buttons = new HashMap<Character, Key>();

	public static MouseButton MB1 = new MouseButton("Left mouse button");
	public static MouseButton MB2 = new MouseButton("Right mouse button");
	public static MouseButton MB3 = new MouseButton("Middle mouse button");
	public static MouseButton SCROLL_UP = new MouseButton("Scroll Up");
	public static MouseButton SCROLL_DOWN = new MouseButton("Scroll Down");

	public static int MOUSE_X;
	public static int MOUSE_Y;

	public Input(Component comp) {
		comp.addKeyListener(this);
		comp.addMouseListener(this);
		comp.addMouseMotionListener(this);
		comp.addMouseWheelListener(this);
		init();
	}

	public void init() {

		initRange(65, 90); // Uppercase letters
		initRange(97, 122); // Lowercase letters
		initRange(48, 57); // 0-9
		new Key('$');
		new Key('£');
		new Key('+');
		new Key('-');
		new Key('*');
		new Key('/');
		new Key('=');
		new Key('%');
		new Key('"');
		new Key('\'');
		new Key('#');
		new Key('@');
		new Key('&');
		new Key('_');
		new Key('(');
		new Key(')');
		new Key(',');
		new Key('.');
		new Key(';');
		new Key(':');
		new Key('?');
		new Key('!');
		new Key('\\');
		new Key('|');
		new Key('{');
		new Key('}');
		new Key('<');
		new Key('>');
		new Key('[');
		new Key(']');
		new Key('`');
		new Key('~');
		new Key('^');
		new Key(' ');
		new Key('\n');
	}

	private void initRange(int start, int end) {
		for (int i = start; i <= end; i++) {
			new Key((char) i);
		}
	}

	public void keyPressed(KeyEvent e) {
		Key k = buttons.get(e.getKeyChar());
		if (k != null) {
			k.toggle(true);
			pressed.add(k);
		}
	}

	public void keyReleased(KeyEvent e) {
		Key k = buttons.get(e.getKeyChar());
		if (k != null) {
			k.toggle(false);
			released.add(k);
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void toggleKey(int keyCode, boolean isPressed) {
		//
		// if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
		// UP.toggle(isPressed);
		// }
		// if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
		// DOWN.toggle(isPressed);
		// }
		// if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
		// LEFT.toggle(isPressed);
		// }
		// if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
		// RIGHT.toggle(isPressed);
		// }
		// if (keyCode == KeyEvent.VK_Y) {
		// Y.toggle(isPressed);
		// }
		// if(keyCode == KeyEvent.VK_ENTER){
		// ENTER.toggle(isPressed);
		// }
	}

	public void toggleMouseButton(int button, boolean isPressed) {
		if (button == 1) {
			MB1.toggle(isPressed);
		}
		if (button == 3) {
			MB2.toggle(isPressed);
		}
		if (button == 2) {
			MB3.toggle(isPressed);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		toggleMouseButton(e.getButton(), true);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		toggleMouseButton(e.getButton(), false);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {
			SCROLL_UP.toggle(true);
		} else {
			SCROLL_DOWN.toggle(true);
		}
	}

	public static void releaseAll() {
		for (Key key : keys) {
			key.toggle(false);
		}
	}

}
