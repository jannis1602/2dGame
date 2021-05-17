package gui;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;

public class GameMenu {

	public Btn[] GameMenubtns;

	public GameMenu() {
		GameMenubtns = new Btn[4];

//		GameMenubtns[0] = new Btn(Game.getFrameWidth()/2-150, 300, 300, 80, "Play",Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
//		GameMenubtns[1] = new Btn(Game.getFrameWidth()/2-150, 400, 300, 80, "Retry",Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
//		GameMenubtns[2] = new Btn(Game.getFrameWidth()/2-150, 500, 300, 80, "Options",Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
//		GameMenubtns[3] = new Btn(Game.getFrameWidth()/2-150, 600, 300, 80, "Launcher",Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
		
		GameMenubtns[0] = new Btn(Game.getFrameWidth()/2-150, Game.getFrameHeight()/10*3, 300, 80, "Play",Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
		GameMenubtns[1] = new Btn(Game.getFrameWidth()/2-150, Game.getFrameHeight()/10*4, 300, 80, "Retry",Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
		GameMenubtns[2] = new Btn(Game.getFrameWidth()/2-150, Game.getFrameHeight()/10*5, 300, 80, "Options",Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
		GameMenubtns[3] = new Btn(Game.getFrameWidth()/2-150, Game.getFrameHeight()/10*6, 300, 80, "Launcher",Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
	}

	public void render(Graphics g) {
//		g.setColor(new Color(150, 150, 150));
//		g.fillRect(0, 0, Game.getFrameWidth(), Game.getFrameHeight());
		
//		g.setColor(Color.red);
//		g.fillRect(100, Game.getFrameHeight()/2, 1800, 10);

		for (int i = 0; i < GameMenubtns.length; i++) {
			GameMenubtns[i].render(g);
		}
	}

}