package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Image.SpriteSheet;
import game.ID;

public class Healing extends GameObject {

	private BufferedImage healing_image;

	public Healing(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);

		healing_image = ss.grabImage(7, 2, 32, 32);
	}

	public void tick() {

	}

	public void render(Graphics g) {
//		int alpha = 127;
//		g.setColor(new Color(0,255,255,alpha));
//		g.setColor(Color.CYAN);
//		g.fillRect(x, y, 32, 32);
		g.drawImage(healing_image, x, y, 32*2, 32*2, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32*2, 32*2);
	}

}
