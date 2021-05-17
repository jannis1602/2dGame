package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Image.SpriteSheet;
import game.ID;

public class Block extends GameObject {
	private BufferedImage block_image;

	public Block(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		block_image = ss.grabImage(5, 2, 32, 32);

	}

	@Override
	public void render(Graphics g) {
//		g.setColor(Color.BLACK);
//		g.fillRect(x, y, 32, 32);
		g.drawImage(block_image, x, y, 64, 64, null);
	}

	@Override
	public void tick() {

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}


}
