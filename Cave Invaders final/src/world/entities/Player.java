package world.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.studiohartman.jamepad.ControllerState;

import game.Game;
import geometry.Vector;
import input.devices.InputDevice;
import world.World;

public class Player extends Entity {

	boolean sliding;

	BufferedImage buff;

	InputDevice input;

	public Player(int x, int y, int width, int height, InputDevice input) {
		p = new Vector(x, y);

		v = new Vector(0, 0);
		a = new Vector(0, 0);

		setDimension(width, height);

		buff = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

		Graphics g = buff.createGraphics();

		g.fillRect(0, 0, width, height);

		g.dispose();

		this.input = input;
		this.solid = true;
	}

	@Override
	public void tick(World world) {

	}

	public void move(World world) {
		if (input != null)
			v = input.getInputDirection();

		if (!v.equals(new Vector(0, 0)))
			v = Vector.mult(Vector.norm(v), 10);

		super.move(world);
	}

	public InputDevice getInputDevice() {
		return input;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(buff, -width() / 2, -height() / 2, width(), height(), null);
		g.setColor(Color.BLUE);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		g.drawLine(0, 0, (int) v.x(), (int) v.y());
	}
}
