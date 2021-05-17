package game;

import object.GameObject;

public class Camera {

	private float x, y;

	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject object) {

//		x += ((object.getX() - x) - 1000 / 1) * 0.05f;
//		y += ((object.getY() - y) - 1000 / 2) * 0.05f;

		x += (((object.getX() - x) - Game.getFrameWidth() / 2) + 32) * 0.05f;
		y += (((object.getY() - y) - Game.getFrameHeight() / 2) + 48) * 0.05f;
		


//		if(x<=0) x=0;
//		if(x>=1032*2+100) x=1032*2+100;
//		if(y<=0) y=0;
//		if(y>=1500*2+100) y=1500*2+100;


		if (x <= 0)
			x = 0;
		if (x >= Game.map.getWidth() * 64 - Game.getFrameWidth())// -2*64
			x = Game.map.getWidth() * 64 - Game.getFrameWidth();
		if (y <= 0)
			y = 0;
		if (y >= Game.map.getHeight() * 64 - Game.getFrameHeight())
			y = Game.map.getHeight() * 64 - Game.getFrameHeight();

	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
