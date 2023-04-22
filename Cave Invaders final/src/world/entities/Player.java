package world.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import geometry.Vector;
import graphics.Animation;
import graphics.loader.ImageLoader;
import input.devices.InputDevice;
import world.World;

public class Player extends Entity {

	boolean sliding;

	InputDevice input;

	public Player(int x, int y, int width, int height, InputDevice input) {
		p = new Vector(x, y);

		v = new Vector(0, 0);
		a = new Vector(0, 0);

		setDimension(width, height);

		BufferedImage[] animation = ImageLoader.loadSpriteSheet("res/HandMonster.png", 64, 64, 32);
		int[] ticks = new int[animation.length];
		for(int i = 0; i < ticks.length; i++) {
			ticks[i] = 5;
		}
		renderable = new Animation(animation, ticks);

		this.input = input;
		this.solid = true;
	}

	@Override
	public void tick(World world) {
		super.tick(world);
	}

	public void move(World world) {
		if (input != null)
			v = input.getInputDirection();

		if (!v.equals(new Vector(0, 0)))
			v = Vector.mult(Vector.norm(v), 3);

		super.move(world);
	}

	public InputDevice getInputDevice() {
		return input;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.BLUE);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		g.drawLine(0, 0, (int) v.x(), (int) v.y());
	}
}
