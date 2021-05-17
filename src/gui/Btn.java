package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JFrame;

import game.Game;

public class Btn {

	public int x, y;
	public int width, height;
	public int transparent = 0;
	public int arc = 20;

	public String label;

	public Color color;
	public Color textcolor;

	public boolean fill;

	public Btn(int x, int y, int width, int height, String label, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
		this.color = color;
		this.textcolor = color;
		this.fill = false;
	}

	public Btn(int x, int y, int width, int height, String label, Color color, Color textcolor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
		this.color = color;
		this.textcolor = textcolor;
		this.fill = false;
	}

	public Btn(int x, int y, int width, int height, String label, Color color, Color textcolor, boolean fill) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
		this.color = color;
		this.textcolor = textcolor;
		this.fill = fill;
	}

	public Btn(int x, int y, int width, int height, String label, Color color, Color textcolor, boolean fill,
			int transparent) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
		this.color = color;
		this.textcolor = textcolor;
		this.fill = fill;
		this.transparent = transparent;
	}

	public Btn(int x, int y, int width, int height, String label, Color color, Color textcolor, boolean fill,
			int transparent, int arc) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
		this.color = color;
		this.textcolor = textcolor;
		this.fill = fill;
		this.transparent = transparent;
		this.arc = arc;
	}

	public void render(Graphics g) {
		g.setColor(textcolor);
		g.setFont(new Font("Century Gothic", Font.BOLD, 30));
		g.setColor(color);
		if (transparent != 0) {
			Color.BLACK.getRGB();
			g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), transparent));
		}
		if (fill == true)
			g.fillRoundRect(getX(), getY(), getWidth(), getHeight(), arc, arc);
		g.setColor(textcolor);
		if (transparent != 0) {
			Color.BLACK.getRGB();
			g.setColor(new Color(textcolor.getRed(), textcolor.getGreen(), textcolor.getBlue(), transparent));
		}
		g.drawRoundRect(getX(), getY(), getWidth(), getHeight(), arc, arc);

//		g.drawRoundRect(getX() - 100, getY(), getWidth() + 200, getHeight(), 10, 10);

//		g.setColor(Color.RED);
//		g.drawRoundRect(getX(), getY(), getWidth(), getHeight(), 10, 10);

		FontMetrics fm = g.getFontMetrics();
		int stringx = (getWidth() - fm.stringWidth(getLabel())) / 2;
		int stringy = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(getLabel(), getX() + stringx, getY() + stringy);
	}

	public void triggerEvent() {
		switch (Game.state) {
		case Launcher:
			if (getLabel().toLowerCase().contains("play")) {
				System.out.println("play");
				Game.state = State.NewGame;
//				Game.playing = true;
			}

			if (getLabel().toLowerCase().contains("options")) {
				System.out.println("options");
				Game.playing = false;
				Game.state = State.Options;
			}
			if (getLabel().toLowerCase().contains("exit")) {
				System.exit(0);
			}

			break;
		case Options:

			if (getLabel().toLowerCase().contains("keys")) {
				System.out.println("keys");
//				Game.state=State.Options;
			}

			if (getLabel().toLowerCase().contains("sound")) {
			}
			if (getLabel().toLowerCase().contains("video")) {
				Game.state = State.OptionVideo;
			}
			if (getLabel().toLowerCase().contains("back")) {
				if (Game.playing == true) {
					Game.state = State.GameMenue;
				}
				if (Game.playing == false) {
					Game.state = State.Launcher;
				}
			}

			break;
		case OptionVideo:

			if (getLabel().toLowerCase().contains("cursor")) {
				Options.nextcursor();
				Options.changeCursor();
			}

			if (getLabel().toLowerCase().contains("frame undecoated")) {
				Options.undecorated = !Options.undecorated;
				System.out.println("frame change");
//				Game.frame.setVisible(false);
//				Game.frame.dispose();
//				Game.frame.setUndecorated(Options.undecorated);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				Game.frame.setVisible(true);
//				Game.frame.add(Game);

			}
			if (getLabel().toLowerCase().contains("fullscreen")) {
				if (Game.frame.isResizable()) {
					Game.frame.setResizable(false);
					Game.frame.setSize(Game.width + 4, Game.height + 20);
					Game.frame.setLocation(-2, -20);
					System.out.println("frame change");
					break;
				}
				if (!Game.frame.isResizable()) {
					Game.frame.setResizable(true);
					Game.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					System.out.println("frame change");
					break;
				}
			}
			if (getLabel().toLowerCase().contains("back")) {
				Game.state = State.Options;

			}

			break;
//		case GameOptions:
//
//			if (getLabel().toLowerCase().contains("keys")) {
////				Game.state=State.Options;
//			}
//
//			if (getLabel().toLowerCase().contains("video")) {
//				Game.state = State.OptionVideo;
//			}
//			if (getLabel().toLowerCase().contains("back")) {
//				Game.state = State.Ingame;
//			}
//
//			break;

		case GameMenue:
			if (getLabel().toLowerCase().contains("play")) {
				Game.state = State.Ingame;
				Game.ammodelay = 0;
			}

			if (getLabel().toLowerCase().contains("retry")) {
				Game.state = State.NewGame;
			}
			if (getLabel().toLowerCase().contains("options")) {
				Game.state = State.Options;
				Game.playing = true;
			}
			if (getLabel().toLowerCase().contains("launcher")) {
				Game.state = State.Launcher;
			}

			break;

		case Win:
			if (getLabel().toLowerCase().contains("launcher")) {
				Game.state = State.Launcher;
			}
			if (getLabel().toLowerCase().contains("retry")) {
				Game.state = State.NewGame;
			}
			if (getLabel().toLowerCase().contains("next level")) {
//				Game.state=State.Ingame;
				Game.state = State.NextLevel;
			}
			break;
		case WinEnd:
			if (getLabel().toLowerCase().contains("launcher")) {
				Game.state = State.Launcher;
			}
			if (getLabel().toLowerCase().contains("retry")) {
				Game.state = State.NewGame;
				System.out.println("retry");
			}
			if (getLabel().toLowerCase().contains("new game")) {
				System.out.println("new Game");
				Game.level = 1;
				Game.loadLevelImage();
				Game.state = State.NewGame;
			}
			break;

		default:
			break;
		}
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
