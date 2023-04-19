package world.tiles;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import graphics.Camera;
import graphics.loader.ImageLoader;

public enum SimpleTile implements Tile {

	Void(true), Wall(true), Floor(false);

	boolean solid;

	BufferedImage buff;
	Image img;

	SimpleTile(boolean solid) {
		this.solid = solid;

		buff = ImageLoader.load("res/" + toString() + ".png");
		img = Camera.gc.createCompatibleImage(buff.getWidth(), buff.getHeight());
		Graphics g = img.getGraphics();
		g.drawImage(buff, 0, 0, buff.getWidth(), buff.getHeight(), null);
		g.dispose();
	}

	@Override
	public void tick() {

	}

	@Override
	public boolean isSolid() {
		return solid;
	}

	@Override
	public void draw(Graphics g) {
		if (this != Void)
			g.drawImage(img, -Tile.size / 2, -Tile.size / 2, Tile.size, Tile.size, null);
	}
}
