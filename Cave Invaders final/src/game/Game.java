package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.lang.reflect.Method;

import javax.swing.JFrame;

import game.states.GameState;
import game.states.RogueLite;
import input.Listener;
import world.generators.Seed;
import world.generators.TestGenerator;

public class Game extends JFrame {

	public static final double ticksPerSecond = 60;

	public static Listener input = new Listener();

	BufferStrategy strategy;

	GameState state;

	public Game() {
		setVisible(true);
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(input);
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		state = new RogueLite(1, new TestGenerator(), new Seed());
	}

	public void loop() {
		long nanos = System.nanoTime();
		double timeStep = 1E9 / ticksPerSecond;

		long fpsNanos = System.nanoTime();
		int fps = 0;

		while (true) {
			if (System.nanoTime() - nanos >= timeStep) {
				state.update();
				nanos += timeStep;
			}
			
			do {
				Graphics g = strategy.getDrawGraphics();

				state.render(g, getWidth(), getHeight());

				g.dispose();
			} while (strategy.contentsRestored());

			strategy.show();

			fps++;

			if (System.nanoTime() - fpsNanos >= 1E9) {
				setTitle(fps + " FPS");
				fpsNanos = System.nanoTime();
				fps = 0;
			}
		}
	}

	public static boolean isKeyDown(int keycode) {
		return input.isKeyDown(keycode);
	}

	public void setGameState(GameState state) {
		this.state = state;
	}

	public static void main(String[] args) {
		
		System.out.println(-0.0 <= 0);
		Game g = new Game();
		
		g.loop();
	}
}
