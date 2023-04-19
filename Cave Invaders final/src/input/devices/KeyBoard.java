package input.devices;

import game.Game;
import geometry.Vector;

public class KeyBoard extends InputDevice {

	int keyCodeRight, keyCodeUp, keyCodeLeft, keyCodeDown;

	public KeyBoard(int keyCodeRight, int keyCodeLeft, int keyCodeUp, int keyCodeDown) {
		this.keyCodeRight = keyCodeRight;
		this.keyCodeUp = keyCodeUp;
		this.keyCodeLeft = keyCodeLeft;
		this.keyCodeDown = keyCodeDown;
	}

	public Vector getInputDirection() {
		Vector v = new Vector(0, 0);

		if (Game.isKeyDown(keyCodeRight)) {
			v = Vector.add(v, new Vector(1, 0));
		}
		if (Game.isKeyDown(keyCodeLeft)) {
			v = Vector.add(v, new Vector(-1, 0));
		}
		if (Game.isKeyDown(keyCodeDown)) {
			v = Vector.add(v, new Vector(0, 1));
		}
		if (Game.isKeyDown(keyCodeUp)) {
			v = Vector.add(v, new Vector(0, -1));
		}

		System.out.println(v);
		return v;
	}

	public int getKeyCodeRight() {
		return keyCodeRight;
	}

	public void setKeyCodeRight(int keyCodeRight) {
		this.keyCodeRight = keyCodeRight;
	}

	public int getKeyCodeUp() {
		return keyCodeUp;
	}

	public void setKeyCodeUp(int keyCodeUp) {
		this.keyCodeUp = keyCodeUp;
	}

	public int getKeyCodeLeft() {
		return keyCodeLeft;
	}

	public void setKeyCodeLeft(int keyCodeLeft) {
		this.keyCodeLeft = keyCodeLeft;
	}

	public int getKeyCodeDown() {
		return keyCodeDown;
	}

	public void setKeyCodeDown(int keyCodeDown) {
		this.keyCodeDown = keyCodeDown;
	}

}
