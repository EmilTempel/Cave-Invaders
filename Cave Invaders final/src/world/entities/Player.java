package world.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.studiohartman.jamepad.ControllerState;

import game.Game;
import geometry.Vector;
import world.World;

public class Player extends Entity {

	boolean sliding;

	int keyCodeRight, keyCodeUp, keyCodeLeft, keyCodeDown;

	BufferedImage buff;

	public Player(int x, int y, int width, int height) {
		p = new Vector(x, y);

		v = new Vector(0, 0);
		a = new Vector(0, 0);

		setDimension(width, height);

		buff = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

		Graphics g = buff.createGraphics();

		g.fillRect(0, 0, width, height);

		g.dispose();
	}

	@Override
	public void tick(World world) {

	}

	public void move(World world) {
		stopVelocity();
		if (!sliding) {
			stopAcceleration();
		}

		ControllerState s = Game.input.getControllerState(0);
		v = new Vector(Math.round(s.leftStickX*1000), -Math.round(s.leftStickY*1000));
		
		if (Game.isKeyDown(keyCodeRight)) {
			addVelocity(1, 0);
		}

		if (Game.isKeyDown(keyCodeUp)) {
			addVelocity(0, -1);
		}

		if (Game.isKeyDown(keyCodeLeft)) {
			addVelocity(-1, 0);
		}

		if (Game.isKeyDown(keyCodeDown)) {
			addVelocity(0, 1);
		}
		

		if (!v.equals(new Vector(0, 0)))
			v = Vector.mult(Vector.norm(v), 20);

		super.move(world);
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

	@Override
	public void draw(Graphics g) {
		g.drawImage(buff, -width() / 2, -height() / 2, width(), height(), null);
		g.setColor(Color.BLUE);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		g.drawLine(0, 0, (int) v.x(), (int) v.y());
	}
}
