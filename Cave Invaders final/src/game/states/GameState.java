package game.states;

import java.awt.Graphics;

public abstract class GameState {

	public abstract void render(Graphics g, int width, int height);
	
	public abstract void update();
	
	public abstract GameState getNextGameState();
}
