package game.states;

import java.awt.Graphics;

import world.entities.Player;

public class BattleRoyale extends GameState{

	public BattleRoyale(Player[] players) {
		
	}
	
	
	@Override
	public void render(Graphics g, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameState getNextGameState() {
		return new Menu();
	}

}
