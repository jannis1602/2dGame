package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;

public class WinEnd {

	public Btn[] WinEndbtns;

	public WinEnd() {
		WinEndbtns = new Btn[3];

		WinEndbtns[0] = new Btn(200, 800, 400, 80, "Retry", Color.BLACK, Color.GREEN, true, 200);
		WinEndbtns[1] = new Btn(Game.getFrameWidth() / 2 - 200, 800, 400, 80, "New Game", Color.BLACK, Color.GREEN,
				true, 200);
		WinEndbtns[2] = new Btn(Game.getFrameWidth() - 200 - 400, 800, 400, 80, "Launcher", Color.BLACK, Color.GREEN,
				true, 200);
	}

	public void render(Graphics g) {
//		g.setColor(new Color(150, 150, 150));
//		g.fillRect(0, 0, Game.getFrameWidth(), Game.getFrameHeight());
		g.setColor(Color.YELLOW.brighter());
		g.setFont(new Font("Century Gothic", Font.BOLD, 50));
		g.drawString("You Win!!!", Game.getFrameWidth() / 2 - 120, Game.getFrameHeight() / 4);
		g.drawString("Good Game", Game.getFrameWidth() / 2 - 120, Game.getFrameHeight() / 4 + 100);

		for (int i = 0; i < WinEndbtns.length; i++) {
			WinEndbtns[i].render(g);
		}
	}
}
