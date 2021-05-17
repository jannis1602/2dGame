package game;

import java.awt.Graphics;
import java.util.LinkedList;

import object.GameObject;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private boolean up = false, down = false, right = false, left = false, e = false;

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			//tick box
			if (Game.getTickArea() != null && tempObject.getBounds().intersects(Game.getTickArea()))
				tempObject.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			if (Game.getVisibleArea() != null && tempObject.getBounds().intersects(Game.getVisibleArea()))
				tempObject.render(g);
		}
	}

	public void addObject(GameObject tempObject) {
		object.add(tempObject);
	}

	public void removeObject(GameObject temObject) {
		object.remove(temObject);
	}

	public void clearObject() {
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void wasdup() {
		setRight(false);
		setLeft(false);
		setUp(false);
		setDown(false);
	}

	public boolean isE() {
		return e;
	}

	public void setE(boolean e) {
		this.e = e;
	}
}
