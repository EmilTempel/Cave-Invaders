package game.states;

import java.awt.Graphics;

import world.generators.Seed;
import world.generators.TestGenerator;

public class Menu extends GameState{

	@Override
	public void render(Graphics g, int width, int height) {
		// TODO Render Titlescreen + Options
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameState getNextGameState() {
		return new RogueLite(3, new TestGenerator(), new Seed());
	}

}
