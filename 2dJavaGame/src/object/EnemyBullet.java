package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Image.SpriteSheet;
import game.Handler;
import game.ID;

public class EnemyBullet extends GameObject {

	private Handler handler;

	public EnemyBullet(int x, int y, ID id, Handler handler, int mx, int my,SpriteSheet ss,int speed) {
		super(x, y, id,ss);
		this.handler = handler;

		velx = (mx - x) / speed;
		vely = (my - y) / speed;
		
//		velx = (mx - x) / 15;
//		vely = (my - y) / 15;
//		System.out.println(velx+"   "+vely);
	}

	public void tick() {
		x += velx;
		y += vely;
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}
		}

	}

	public void render(Graphics g) {
		g.setColor(Color.RED.brighter());
		g.fillOval(x, y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
