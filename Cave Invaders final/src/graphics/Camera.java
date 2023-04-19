package graphics;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JFrame;

import game.physics.PhysicsObject;
import geometry.Vector;
import input.Listener;
import world.Scene;
import world.TileEntityMap;
import world.TileMap;
import world.World;
import world.entities.Entity;
import world.tiles.SimpleTile;
import world.tiles.Tile;

public class Camera extends PhysicsObject {
	public static final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	public static final GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();

	public static ArrayList<int[]> loaded = new ArrayList<>();

	public Camera(int x, int y, int w, int h) {
		super(x, y, w, h);

	}

	public Camera() {

	}

	public Image render(Scene scene) {

		Image img = gc.createCompatibleImage(s(0), s(1));

		Graphics graphics = translate(img.getGraphics(), -x() + width() / 2, -y() + height() / 2);

		for (Entry<Integer, TileEntityMap> map : scene.getMapLayersIndexed()) {
			for (int i = TileMap.getGridCoordinate(x() - width() / 2); i < TileMap.getGridCoordinate(x() + width() / 2)
					+ 1; i++) {
				for (int j = TileMap.getGridCoordinate(y() - height() / 2); j < TileMap
						.getGridCoordinate(y() + height() / 2) + 1; j++) {

					Tile tile = map.getValue().get(i, j);

					if (tile != null) {
						Graphics translated = translate(graphics, i * Tile.size, j * Tile.size);
						tile.draw(translated);
						translated.dispose();
					}
				}
			}

			for (Entity e : map.getValue().getEntities()) {
				Graphics translated = translate(graphics, e.x(), e.y());
				e.draw(translated);
				translated.dispose();
			}
		}

//		for (int[] indeces : loaded) {
//			Graphics translated = translate(graphics, indeces[0] * Tile.size, indeces[1] * Tile.size);
//
//			translated.drawRect(-Tile.size / 2, -Tile.size / 2, Tile.size, Tile.size);
//			translated.dispose();
//		}

		graphics.dispose();

		return img;

	}

	public Image render(World world) {
		return render(world.getCurrentScene());
	}

	public static Graphics translate(Graphics g, int x, int y) {
		Graphics g2 = g.create();
		g2.translate(x, y);
		return g2;
	}

	public static void main(String[] args) {

		Scene scene = new Scene(400, 400);

		for (int i = 0; i < 400; i++) {
			for (int j = 0; j < 400; j++) {
				for (int l = 0; l < 1; l++)
					scene.set(i, j, SimpleTile.values()[(int) (2 * Math.random())], l);

			}
		}

		Camera c = new Camera(0, 0, 1920, 1080);

		JFrame f = new JFrame();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1920, 1080);

		f.createBufferStrategy(2);

		Listener input = new Listener();

		int speed = 10;

		f.addKeyListener(input);

		BufferStrategy buff = f.getBufferStrategy();

		long nanos = System.nanoTime(), fpsNanos = System.nanoTime();
		int counter = 0;

		double timeStep = 1E9 / 60;

		while (true) {

			while (System.nanoTime() - nanos >= timeStep) {
				if (input.isKeyDown(KeyEvent.VK_W)) {
					c.setY(c.getY() - speed);
				}
				if (input.isKeyDown(KeyEvent.VK_S)) {
					c.setY(c.getY() + speed);
				}
				if (input.isKeyDown(KeyEvent.VK_A)) {
					c.setX(c.getX() - speed);
				}
				if (input.isKeyDown(KeyEvent.VK_D)) {
					c.setX(c.getX() + speed);
				}

				nanos += timeStep;
			}

			do {
				Graphics g = buff.getDrawGraphics();

				g.drawImage(c.render(scene), 0, 0, 1920, 1080, null);

				g.dispose();

			} while (buff.contentsRestored());

			buff.show();

			counter++;

			if (System.nanoTime() - fpsNanos >= 1E9) {
				f.setTitle(counter + "FPS");

				counter = 0;

				fpsNanos += 1E9;
			}
		}
	}
}
