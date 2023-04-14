package graphics.fonts;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import graphics.loader.ImageLoader;

public class Font {

	private final BufferedImage src;
	private final int charWidth, charLength, range, columns, rows;
	private final HashMap<Character, BufferedImage> chars;

	public Font(BufferedImage src, int charWidth, int charLength, int range) {
		this.src = src;
		this.charWidth = charWidth;
		this.charLength = charLength;
		this.range = range;
		this.columns = src.getWidth() / charWidth;
		this.rows = src.getHeight() / charLength;
		chars = new HashMap<>();
	}

	public BufferedImage getChar(char c) {
		if (c >= range) {
			throw new IllegalArgumentException("The char: " + c + " is not supported by this Font.");
		}

		if (chars.get(c) == null) {
			chars.put(c, src.getSubimage((c % columns) * charWidth, (c / columns) * charLength, charWidth, charLength));
		}
		return chars.get(c);
	}

	public BufferedImage convert(String s) {
		BufferedImage img = new BufferedImage(s.length() * charWidth, charLength, src.getType());
		Graphics2D g = img.createGraphics();
		for (int i = 0; i < s.length(); i++) {
			g.drawImage(getChar(s.charAt(i)), i * charWidth, 0, charWidth, charLength, null);
		}
		g.dispose();
		return img;
	}

	public static void main(String[] args) {
		Font f = new Font(ImageLoader.load("res/fonts/default.png"), 8, 16, 128);
		ImageLoader.store(f.convert("bruder du verwirrst mich. bedenk bitte das ich dumm bin"), "png", "test/fontTest2.png");
//		ImageLoader.store(f.getChar('@'), "png", "test/fontTest1.png");
		
	}
}
