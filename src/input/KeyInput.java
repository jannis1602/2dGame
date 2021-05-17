package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Game;
import game.Handler;
import game.ID;
import gui.State;
import object.GameObject;
import object.Inventar;


public class KeyInput implements KeyListener {

	Handler handler;
	Game game;

	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
//		System.out.println(key);
		switch (Game.state) {
		case Launcher:
			if (key == KeyEvent.VK_SPACE)
				Game.state = State.NewGame;
			break;
		case Options:
			if (key == KeyEvent.VK_ESCAPE)
				Game.state = State.Launcher;

			break;
		case GameMenue:
			if (key == KeyEvent.VK_ESCAPE)
				Game.state = State.Ingame;

			break;
		case Ingame:
			if (key == KeyEvent.VK_ESCAPE)
				Game.state = State.GameMenue;

			if (key == KeyEvent.VK_1 || key == KeyEvent.VK_2) {
				if (Game.mouseklickdelay == 20) {
					Game.mouseklickdelay = 0;
					System.out.print(Inventar.selectedInv);
					System.out.println(Inventar.selectedweapon);
					if (Inventar.selectedInv == 1) {
						Inventar.selectedInv = 2;
						break;
					}
					if (Inventar.selectedInv == 2) {
						Inventar.selectedInv = 1;
						break;
					}
				}
			}
			break;
		case GameOptions:
			if (key == KeyEvent.VK_ESCAPE)
				Game.state = State.GameMenue;

			break;
		case OptionVideo:
			if (key == KeyEvent.VK_ESCAPE)
				Game.state = State.GameOptions;

			break;

		default:
			break;
		}

		if (Game.state == State.Ingame) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempOblject = handler.object.get(i);
				if (tempOblject.getId() == ID.Player) {
					if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
						handler.setUp(true);
					if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
						handler.setDown(true);
					if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
						handler.setLeft(true);
					if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
						handler.setRight(true);
					if (key == KeyEvent.VK_E)
						handler.setE(true);
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (Game.state == State.Ingame) {

			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempOblject = handler.object.get(i);
				if (tempOblject.getId() == ID.Player) {
					if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
						handler.setUp(false);
					if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
						handler.setDown(false);
					if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
						handler.setLeft(false);
					if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
						handler.setRight(false);
					if (key == KeyEvent.VK_E)
						handler.setE(false);

//				if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
//					tempOblject.vely = 0;
//				if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
//					tempOblject.vely = 0;
//				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
//					tempOblject.velx = 0;
//				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
//					tempOblject.velx = 0;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
