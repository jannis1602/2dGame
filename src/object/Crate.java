package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Image.SpriteSheet;
import game.ID;

public class Crate extends GameObject {

	private BufferedImage crate_image;
	public Crate(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);

		crate_image = ss.grabImage(6, 2, 32, 32);
	}

	public void tick() {

	}

	public void render(Graphics g) {
//		int alpha = 127;
//		g.setColor(new Color(0,255,255,alpha));
//		g.setColor(Color.CYAN);
//		g.fillRect(x, y, 64, 64);
		g.drawImage(crate_image, x, y, 32*2, 32*2, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32*2, 32*2);
	}

}
