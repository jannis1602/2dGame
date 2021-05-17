package gui.options;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import gui.Btn;

public class OptionVideo {
	public Btn[] videobtns;

	public OptionVideo() {
		videobtns = new Btn[4];

		videobtns[0] = new Btn(Game.getFrameWidth()/2-150, Game.getFrameHeight()/12*4, 300, 60, "Cursor", Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
		videobtns[1] = new Btn(Game.getFrameWidth()/2-150, Game.getFrameHeight()/12*5, 300, 60, "Frame Undecoated",Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
		videobtns[2] = new Btn(Game.getFrameWidth()/2-150, Game.getFrameHeight()/12*6, 300, 60, "Fullscreen",Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
		videobtns[3] = new Btn(Game.getFrameWidth()/2-150, Game.getFrameHeight()/12*7, 300, 60, "Back",Color.LIGHT_GRAY, Color.DARK_GRAY, true, 0);
	}

	public void render(Graphics g) {
		g.setColor(new Color(150, 150, 150));
		g.fillRect(0, 0, Game.getFrameWidth(), Game.getFrameHeight());

		for (int i = 0; i < videobtns.length; i++) {
			videobtns[i].render(g);
		}
	}

}
