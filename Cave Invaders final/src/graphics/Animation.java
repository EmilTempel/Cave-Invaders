package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation extends Renderable {

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

	public void draw(Graphics g) {
		BufferedImage img = getImage();
		g.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, img.getWidth(), img.getHeight(), null);
	}

}
