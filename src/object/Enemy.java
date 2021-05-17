package object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Image.SpriteSheet;
import game.Game;
import game.Handler;
import game.ID;

public class Enemy extends GameObject {

	Handler handler;
	Random r = new Random();
	Game game;
	int choose = 0;
	int hp = 100;
	int enemyAmmo = 0;
	int wallx = 0, wally = 0;
	boolean wall = false;

	private BufferedImage[] enemy_image = new BufferedImage[3];
	Animation anim;

	public Enemy(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;

		enemy_image[0] = ss.grabImage(4, 1, 32, 32);
		enemy_image[1] = ss.grabImage(5, 1, 32, 32);
		enemy_image[2] = ss.grabImage(6, 1, 32, 32);

		anim = new Animation(3, enemy_image[0], enemy_image[1], enemy_image[2]);

	}

	public void tick() {
		x += velx;
		y += vely;
		if (enemyAmmo < 80)
			enemyAmmo++;

		choose = r.nextInt(16);

		mx = 0;
		my = 0;

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Block) {

				if (getBoundsBig().intersects(tempObject.getBounds())) {
					x += (velx * 5) * -1;
					y += (vely * 5) * -1;
					velx = 0;
					vely = 0;
				} else if (choose == 0) {
					velx = (r.nextInt(4 - -4) + -4);
					vely = (r.nextInt(4 - -4) + -4);
				}
			}

			if (tempObject.getId() == ID.Bullet) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (Inventar.selectedweapon == Weapon.Pumpgun)
						hp -= 15;
					if (Inventar.selectedweapon == Weapon.Pistole)
						hp -= 25;
					if (Inventar.selectedweapon == Weapon.LaserGun)
						hp -= 50;
					if (Inventar.selectedweapon == Weapon.AssaultRifle)
						hp -= 10;
					handler.removeObject(tempObject);
				}
			}

			if (tempObject.getId() == ID.Player) {
				if (getBoundsViewField().intersects(tempObject.getBounds())) {
//					System.out.println("Player" + tempObject.getX() + "  " + tempObject.getY());
					mx = tempObject.getX() + 32;// + 16 * 2;
					my = tempObject.getY() + 48;// + 24 * 2;
					wall = false;

					for (int o = 0; o < handler.object.size(); o++) {
						GameObject wallObject = handler.object.get(o);
						if (wallObject.getId() == ID.Block) {
							if (wallObject.getBounds().intersectsLine(mx, my, x, y)) {
//								System.out.println("Block! " + wallObject.getX() + " " + wallObject.getY());
								wall = true;
							}
						}
						wallx = wallObject.getX();
						wally = wallObject.getY();
					}
					if (enemyAmmo >= 80 && wall == false) {
						handler.addObject(new EnemyBullet(getX() + 16 * 2, getY() + 24 * 2 + 15, ID.EnemyBullet,
								handler, mx, my, ss, 15));
						enemyAmmo = 0;
					}
				}
			}
//			if (tempObject.getId() == ID.Block)
//				if (tempObject.getBounds().intersectsLine(mx, my, playerx, playerx))
//					System.out.println("Block!");
		}

		anim.runAnimation();
		if (hp <= 0) {
			handler.removeObject(this);
			game.enemysleft -= 1;
//			if (game.enemysleft == 0) {
//				game.state = State.Win;
//			}
		}
	}

	int mx;
	int my;

	public void render(Graphics g) {
//		g.setColor(Color.RED);
//		g.fillRect(x, y, 64, 64);

//		anim.drawAnimation(g, x, y, 0);
		anim.drawAnimationBigger(g, x, y, 0, 64, 64);

		Font newFont = new java.awt.Font("Dialog", 0, 20);
		g.setFont(newFont);
		g.setColor(Color.BLACK);
		g.drawString("Hp:" + hp, x + 10, y + 5);

		if (!wall)
			g.setColor(Color.GREEN);
		if (wall) {
			g.setColor(Color.black);
//			g.fillRect(wallx, wally, 64, 64);
		}
		g.fillOval(mx - 5, my - 5, 10, 10);
		g.drawLine(mx, my, x + 32, y + 32);

		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED.brighter());
		g2d.draw(getBoundsViewField());

//		g.setColor(Color.GREEN);
//		g.fillRect(wallx, wally, 10, 10);

//		g.setColor(Color.RED);
//		g2d.draw(getBoundsBig());

		g.setColor(Color.RED.brighter());
		g2d.draw(getBounds());

	}

//	public Rectangle getBoundsHit() {
//		return new Rectangle(x, y, 32, 32);
//	}

	public Rectangle getBounds() {
		return new Rectangle(x - 16, y - 16, 32 * 3, 32 * 3);
	}

	public Rectangle getBoundsBig() {
		return new Rectangle(x - 16 * 2, y - 16 * 2, 32 * 4, 32 * 4);
	}

	public Rectangle getBoundsViewField() {
		return new Rectangle(x - 64 * 8, y - 64 * 8, 64 * 17, 64 * 17);
//		return new Rectangle(x - 16 * 14, y - 16 * 16, 32 * 18, 32 * 18);
	}

}
