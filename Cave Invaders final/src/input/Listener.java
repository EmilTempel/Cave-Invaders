package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class Listener implements KeyListener {

	HashMap<Integer, Boolean> keys;
	ControllerManager controllers;

	public Listener() {
		keys = new HashMap<>();
		controllers = new ControllerManager();
		controllers.initSDLGamepad();
	}

	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys.put(e.getKeyCode(), false);
	}

	public boolean isKeyDown(int keyCode) {
		if (keys.get(keyCode) != null) {
			return keys.get(keyCode);
		} else {
			return false;
		}
	}
	
	public ControllerState getControllerState(int i) {
		return controllers.getState(i);
	}

}
