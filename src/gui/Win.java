package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;

public class Win {

	public Btn[] Winbtns;

	public Win() {
		Winbtns = new Btn[3];

		Winbtns[0] = new Btn(200, 800, 400, 80, "Retry", Color.BLACK, Color.GREEN, true, 200);
		Winbtns[1] = new Btn(Game.getFrameWidth() / 2 - 200, 800, 400, 80, "Next Level", Color.BLACK, Color.GREEN, true,
				200);
		Winbtns[2] = new Btn(Game.getFrameWidth() - 200 - 400, 800, 400, 80, "Launcher", Color.BLACK, Color.GREEN, true,
				200);
	}

	public void render(Graphics g) {
//		g.setColor(new Color(150, 150, 150));
//		g.fillRect(0, 0, Game.getFrameWidth(), Game.getFrameHeight());
		g.setColor(Color.YELLOW.brighter());
		g.setFont(new Font("Century Gothic", Font.BOLD, 50));
		g.drawString("You Win!!!", Game.getFrameWidth() / 2 - 120, Game.getFrameHeight() / 4);

		for (int i = 0; i < Winbtns.length; i++) {
			Winbtns[i].render(g);
		}
	}
}
