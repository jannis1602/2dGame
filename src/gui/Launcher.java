package gui;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;

public class Launcher {

	public Btn[] btns;

	public Launcher() {
		btns = new Btn[3];

//		btns[0] = new Btn(Game.getFrameWidth() / 2 - 150, 300, 300, 100, "Play", Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0,40);
//		btns[1] = new Btn(Game.getFrameWidth() / 2 - 150, 450, 300, 100, "Options", Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0,40);
//		btns[2] = new Btn(Game.getFrameWidth() / 2 - 150, 600, 300, 100, "Exit", Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0,40);

//		btns[0] = new Btn(Game.getFrameWidth() / 2 - 150, Game.getFrameHeight()/4, 300, 100, "Play", Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0,40);
//		btns[1] = new Btn(Game.getFrameWidth() / 2 - 150, Game.getFrameHeight()/8*3, 300, 100, "Options", Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0,40);
//		btns[2] = new Btn(Game.getFrameWidth() / 2 - 150, Game.getFrameHeight()/8*4, 300, 100, "Exit", Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0,40);

		btns[0] = new Btn(Game.getFrameWidth() / 2 - 150, Game.getFrameHeight() / 16 * 5, 300, 100, "Play",
				Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0, 40);
		btns[1] = new Btn(Game.getFrameWidth() / 2 - 150, btns[0].getY() + 150, 300, 100, "Options", Color.LIGHT_GRAY,
				Color.DARK_GRAY, true, 0, 40);
		btns[2] = new Btn(Game.getFrameWidth() / 2 - 150, btns[1].getY() + 150, 300, 100, "Exit", Color.LIGHT_GRAY,
				Color.DARK_GRAY, true, 0, 40);
	}

	public void render(Graphics g) {
		g.setColor(new Color(150, 150, 150));
		g.fillRect(0, 0, Game.getFrameWidth(), Game.getFrameHeight());
//		g.setColor(Color.red);
//		g.fillRect(100, Game.getFrameHeight() / 2, 1800, 10);
		for (int i = 0; i < btns.length; i++) {
			btns[i].render(g);
//			btns[i].setXw(Game.getFrameWidth() / 2 - 150);
		}
	}

}
