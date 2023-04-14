package game.states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import graphics.Camera;
import input.devices.KeyBoard;
import world.World;
import world.entities.Player;
import world.generators.Seed;
import world.generators.WorldGenerator;

public class RogueLite extends GameState {

	int playerCount;
	Player[] players;
	World[] playerWorlds;

	Seed seed;

	public RogueLite(int playerCount, WorldGenerator generator, Seed seed) {
		this.playerCount = playerCount;
		this.players = new Player[playerCount];
		this.playerWorlds = new World[playerCount];

		KeyBoard keyboard = new KeyBoard(KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_W, KeyEvent.VK_S);

		
		for (int i = 0; i < playerCount; i++) {
			players[i] = new Player(108, 108, 25, 25, keyboard); // TODO actual Player constructor
			players[i].setCamera(new Camera());
			playerWorlds[i] = generator.generate(seed);

			playerWorlds[i].spawn(players[i], 128, 128); // TODO Worldspawnpoint
		}

		
		
//		
//		players[1].setKeyCodeRight(KeyEvent.VK_RIGHT);
//		players[1].setKeyCodeLeft(KeyEvent.VK_LEFT);
//		players[1].setKeyCodeUp(KeyEvent.VK_UP);
//		players[1].setKeyCodeDown(KeyEvent.VK_DOWN);
	}

	@Override
	public void render(Graphics g, int width, int height) {
		for (int i = 0; i < playerCount; i++) {
			players[i].getCamera().setDimension(width / playerCount, height);

			int x = i * (width / playerCount), y = 0;

			g.drawImage(players[i].getCamera().render(playerWorlds[i]), x, y, width / playerCount, height, null);

			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(10));
			g2.drawRect(x, y, width / playerCount, height);
		}
	}

	@Override
	public void update() {
		for (int i = 0; i < playerCount; i++) {
			playerWorlds[i].update();
		}
	}

	@Override
	public GameState getNextGameState() {
		return null;
	}

}
