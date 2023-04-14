package graphics.loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {

	private static HashMap<String, BufferedImage> loaded = new HashMap<>();
	private static HashMap<String, BufferedImage[]> loadedSheets = new HashMap<>();

	private ImageLoader() {
	}

	public static BufferedImage load(String path) {
		try {
			if (loaded.get(path) == null) {
				loaded.put(path, ImageIO.read(new File(path)));
			}
			return loaded.get(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static BufferedImage[] loadSpriteSheet(String path, int spriteWidth, int spriteHeight, int count) {
		if(loadedSheets.get(path) == null) {
			BufferedImage src = load(path);
			int width = src.getWidth()/spriteWidth, height = src.getHeight()/spriteHeight;
			BufferedImage[] erg = new BufferedImage[count];
			for(int j = 0; j < height; j++) {
				for(int i = 0; i < width; i++) {
					int idx = j*width + i;
					erg[idx] = src.getSubimage(i*spriteWidth, j*spriteHeight, spriteWidth, spriteHeight);
				}
			}
			loadedSheets.put(path, erg);
		}
		
		return loadedSheets.get(path);
	}

	public static void store(BufferedImage img, String type, String path) {
		try {
			ImageIO.write(img, type, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
