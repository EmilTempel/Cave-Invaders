package world;

import java.awt.image.BufferedImage;

public class Animation {

	BufferedImage[] images;
	int[] ticks;
	int i, current_tick;

	public Animation(BufferedImage[] images, int[] ticks) {
		assert images.length == ticks.length;

		this.images = images;
		this.ticks = ticks;
		i = 0;
	}
	
	public void tick() {
		if (current_tick <= 0) {
			i = i < ticks.length - 1 ? i + 1 : 0;
			
			current_tick = ticks[i];
		}
		
		current_tick--;
	}

	public BufferedImage getImage() {
		return images[i];
	}
}
