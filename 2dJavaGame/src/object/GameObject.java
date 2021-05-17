package object;

import java.awt.Graphics;
import java.awt.Rectangle;

import Image.SpriteSheet;
import game.Game;
import game.Handler;
import game.ID;

public abstract class GameObject {

	Handler handler;
	Game game;

	public int x, y;
	public int width, height;
	public boolean solid;
	public SpriteSheet ss;
	public int velx, vely;

	public ID id;

	public GameObject(int x, int y, ID id, SpriteSheet ss) {

		this.x = x;
		this.y = y;
//		this.width = width;
//		this.height = height;
//		this.solid=solid;
		this.id = id;
		this.ss = ss;
//		this.handler=handler;

	}

	public abstract void render(Graphics g);

	public abstract void tick();

	public abstract Rectangle getBounds();

//	public void render(Graphics g) {	
//	}

//	public void tick() {
//		x+=velx;
//		y+=vely;
//	}

	public void die() {
		handler.removeObject(this);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

}
