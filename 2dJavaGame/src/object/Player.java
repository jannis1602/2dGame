package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Image.SpriteSheet;
import game.Game;
import game.Handler;
import game.ID;
import gui.State;

public class Player extends GameObject {
	Handler handler;
	Game game;
	Animation anim;
	private BufferedImage[] player_image = new BufferedImage[3];

	public Player(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;
		player_image[0] = ss.grabImage(1, 1, 32, 64);
		player_image[1] = ss.grabImage(2, 1, 32, 64);
		player_image[2] = ss.grabImage(3, 1, 32, 64);

		anim = new Animation(4, player_image[0], player_image[1], player_image[2]);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width, height);

//		g.drawImage(player_image[0], x, y, 32 * 2, 64 * 2, null);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(getBounds());

		if (velx == 0 && vely == 0)
			g.drawImage(player_image[0], x, y, 32 * 2, 64 * 2, null);
		else
			anim.drawAnimationBigger(g, x, y, 0, 64, 128);
	}

	@Override
	public void tick() {		
		x += velx;
		y += vely;

		collision();

		// movement
		int movespeed = 5;
		movespeed = 8;
		if (handler.isUp())
			vely = -movespeed;
		else if (!handler.isDown())
			vely = 0;

		if (handler.isDown())
			vely = movespeed;
		else if (!handler.isUp())
			vely = 0;

		if (handler.isRight())
			velx = movespeed;
		else if (!handler.isLeft())
			velx = 0;

		if (handler.isLeft())
			velx = -movespeed;
		else if (!handler.isRight())
			velx = 0;

		anim.runAnimation();

	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					x += velx * -1;
					y += vely * -1;
				}
			}
			if (tempObject.getId() == ID.Creat) {
				if (getBounds().intersects(tempObject.getBounds())) {
					game.ammo += 20;
					handler.removeObject(tempObject);
				}
			}
			if (tempObject.getId() == ID.Healing) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (game.hp != 200) {
						game.hp += 20;
						handler.removeObject(tempObject);
					}
					if (game.hp >= 180 && game.hp != 200) {
						game.hp -= game.hp - 200;
						handler.removeObject(tempObject);
					}
				}
			}
			if (tempObject.getId() == ID.Enemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					game.hp--;
					if (game.hp <= 0) {
						Game.state = State.Death;
					}
				}
			}
			if (tempObject.getId() == ID.EnemyBullet) {
				if (getBounds().intersects(tempObject.getBounds())) {
					game.hp -= 20;
					handler.removeObject(tempObject);
					if (game.hp <= 0) {
						Game.state = State.Death;
					}
				}
			}
//			if (tempObject.getId() == ID.EnemyBig) {
//				if (getBounds().intersects(tempObject.getBounds())) {
//					game.hp -= 2;
//					if (game.hp <= 0) {
//						Game.state=Game.State.MENU;
//					}
//				}
//			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 96);
	}

}
