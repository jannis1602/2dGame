package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Image.SpriteSheet;
import game.Game;
import game.Handler;
import game.ID;

public class Bullet extends GameObject {

	private Handler handler;
//	private Game game;

	public Bullet(int x, int y, ID id, Handler handler, Game game, int mx, int my, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;

		velx = (mx - x) / 20;
		vely = (my - y) / 20;
//		game.playsound.play("C:\\Users\\mattl.JANNISPC\\Desktop\\setsound.wav");
//		URL audio=PlaySound.class.getResource("shootSound2.wav");
//		game.playsound.play(audio);
//		if ((mx - x) / 20 < 15 && (mx - x) / 20 > -15)
//			if ((my - y) / 20 < 15 && (my - y) / 20 > -15)
//				System.out.println(velx + " " + vely+"\n");

//		System.out.println(velx + "   " + vely + " " + velx * vely);

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
		g.setColor(Color.YELLOW);
		if (Inventar.selectedweapon == Weapon.LaserGun) {
			g.setColor(Color.CYAN);
		}
		if (Inventar.selectedweapon == Weapon.AssaultRifle) {
			g.setColor(Color.DARK_GRAY);
			g.fillOval(x, y, 12, 12);
//			g.fillOval(x - velx, y - vely, 12, 12);
//			g.fillOval(x - velx * 2, y - vely * 2, 12, 12);
		} else
			g.fillOval(x, y, 8 * 2, 8 * 2);

//		g.setColor(Color.YELLOW.darker());
//		g.fillOval(x-velx*2, y-vely*2, 15, 15);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
	}

}
