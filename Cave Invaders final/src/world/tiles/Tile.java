package world.tiles;

import java.awt.Graphics;

import world.generators.wavefunctioncollapse.SuperPosable;

public interface Tile{

	public static final int size = 64;

	public abstract void tick();
	
	public abstract boolean isSolid();
	
	public abstract void draw(Graphics g);
}
