package graphics;

import java.awt.Graphics;

public abstract class Renderable {

	public Renderable() {
		
	}
	
	public abstract void draw(Graphics g);
	public abstract void tick();
}
