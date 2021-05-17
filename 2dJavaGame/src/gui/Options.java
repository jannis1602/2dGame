package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;

import bufferedtxt.Txtoptions;
import game.Game;

public class Options {

	public static boolean fullscreen = true;
	public static boolean undecorated = true;

	public Btn[] optionsbtns;

	public Options() {
		optionsbtns = new Btn[4];

		optionsbtns[0] = new Btn(Game.getFrameWidth() / 2 - 150, Game.getFrameHeight()/12*4, 300, 60, "Keys", Color.LIGHT_GRAY,
				Color.DARK_GRAY, true, 0);
		optionsbtns[1] = new Btn(Game.getFrameWidth() / 2 - 150, Game.getFrameHeight()/12*5, 300, 60, "Sound", Color.LIGHT_GRAY,
				Color.DARK_GRAY, true, 0);
		optionsbtns[2] = new Btn(Game.getFrameWidth() / 2 - 150, Game.getFrameHeight()/12*6, 300, 60, "Video", Color.LIGHT_GRAY,
				Color.DARK_GRAY, true, 0);
		optionsbtns[3] = new Btn(Game.getFrameWidth() / 2 - 150, Game.getFrameHeight()/12*7, 300, 60, "Back", Color.LIGHT_GRAY,
				Color.DARK_GRAY, true, 0);
	}

	public void render(Graphics g) {
		g.setColor(new Color(150, 150, 150));
		g.fillRect(0, 0, Game.getFrameWidth(), Game.getFrameHeight());
//		g.setColor(Color.red);
//		g.fillRect(100, Game.getFrameHeight()/2, 1800, 10);
		for (int i = 0; i < optionsbtns.length; i++) {
			optionsbtns[i].render(g);
		}
	}

	public static Cursor cucursor;
	public static int cursornum;
	public static int cursor;

	@SuppressWarnings("deprecation")
	public static void changeCursor() {
		switch (cursornum) {
		case 1:
			cursor = Cursor.CROSSHAIR_CURSOR;
			break;
		case 2:
			cursor = Cursor.HAND_CURSOR;
			break;
		case 3:
			cursor = Cursor.MOVE_CURSOR;
			break;
		case 4:
			cursor = Cursor.WAIT_CURSOR;
			break;
		case 5:
			cursor = Cursor.DEFAULT_CURSOR;
			break;
		case 6:
			cucursor = Toolkit.getDefaultToolkit().createCustomCursor(Game.cursorImage, new Point(0, 0),
					"customCursor");
			cursornum = 0;
			break;
		default:
			break;
		}

		Game.frame.setCursor(cursor);
		if (cursornum == 0)
			Game.frame.setCursor(cucursor);
		Txtoptions.optionCursor = Integer.valueOf(cursornum);
	}
	
	public static void nextcursor() {
		cursornum++;
//		new Writer();
	}

}