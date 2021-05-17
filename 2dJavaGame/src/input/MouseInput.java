package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.Camera;
import game.Game;
import game.Handler;
import game.ID;
import gui.Btn;
import gui.State;
import object.Bullet;
import object.GameObject;
import object.Inventar;
import object.Weapon;

public class MouseInput implements MouseListener, MouseMotionListener {
	public Game game;
	public Handler handler;
	public Camera camera;

	public MouseInput(Game game, Handler handler, Camera camera) {
		this.game = game;
		this.handler = handler;
		this.camera = camera;
	}

	public int x, y;

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Game.state == State.Launcher) {
			if (Game.mouseklickdelay == 20) {
				Game.mouseklickdelay = 0;
				for (int i = 0; i < Game.launcher.btns.length; i++) {
					Btn btns = Game.launcher.btns[i];

					if (x >= btns.getX() && y >= btns.getY() && x <= btns.getX() + btns.getWidth()
							&& y <= btns.getY() + btns.getHeight()) {
						System.out.println("mouse");
						btns.triggerEvent();
					}
				}
			}
		}

		if (Game.state == State.Options) {
			if (Game.mouseklickdelay == 20) {
				Game.mouseklickdelay = 0;
				for (int i = 0; i < Game.options.optionsbtns.length; i++) {
					Btn btns = Game.options.optionsbtns[i];

					if (x >= btns.getX() && y >= btns.getY() && x <= btns.getX() + btns.getWidth()
							&& y <= btns.getY() + btns.getHeight()) {
						System.out.println("mouse");
						btns.triggerEvent();
					}
				}
			}
		}
		if (Game.state == State.GameOptions) {
			if (Game.mouseklickdelay == 20) {
				Game.mouseklickdelay = 0;
				for (int i = 0; i < Game.options.optionsbtns.length; i++) {
					Btn btns = Game.options.optionsbtns[i];

					if (x >= btns.getX() && y >= btns.getY() && x <= btns.getX() + btns.getWidth()
							&& y <= btns.getY() + btns.getHeight()) {
						System.out.println("mouse");
						btns.triggerEvent();
					}
				}
			}
		}

		if (Game.state == State.GameMenue) {
			if (Game.mouseklickdelay == 20) {
				Game.mouseklickdelay = 0;
				for (int i = 0; i < Game.gamemenu.GameMenubtns.length; i++) {
					Btn btns = Game.gamemenu.GameMenubtns[i];

					if (x >= btns.getX() && y >= btns.getY() && x <= btns.getX() + btns.getWidth()
							&& y <= btns.getY() + btns.getHeight()) {
						System.out.println("mouse");
						btns.triggerEvent();
						Game.ammodelay = 0;
					}
				}
			}
		}
		if (Game.state == State.Ingame) {

			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() == ID.Player && game.ammo >= 1) {
					int mx = (int) (e.getX() + camera.getX());
					int my = (int) (e.getY() + camera.getY());

					if (Inventar.selectedweapon == Weapon.Pistole && game.ammo >= 1 && Game.ammodelay >= 30) {

						handler.addObject(new Bullet(tempObject.getX() + 16 * 2, tempObject.getY() + 24 * 2 + 15,
								ID.Bullet, handler, game, mx, my, null));
						game.ammo--;
						Game.ammodelay = 0;
					}

					if (Inventar.selectedweapon == Weapon.Pumpgun && game.ammo >= 3 && Game.ammodelay >= 50) {
						handler.addObject(new Bullet(tempObject.getX() + 16 * 2, tempObject.getY() + 24 * 2 + 15,
								ID.Bullet, handler, game, mx, my, null));
						handler.addObject(new Bullet(tempObject.getX() + 16 * 2, tempObject.getY() + 24 * 2 + 15,
								ID.Bullet, handler, game, mx + 40, my + 40, null));
						handler.addObject(new Bullet(tempObject.getX() + 16 * 2, tempObject.getY() + 24 * 2 + 15,
								ID.Bullet, handler, game, mx - 40, my - 40, null));
						game.ammo -= 3;
						Game.ammodelay = 0;
					}

					if (Inventar.selectedweapon == Weapon.LaserGun && game.ammo >= 1 && Game.ammodelay >= 80) {

						handler.addObject(new Bullet(tempObject.getX() + 16 * 2, tempObject.getY() + 24 * 2 + 15,
								ID.Bullet, handler, game, mx, my, null));
						game.ammo--;
						Game.ammodelay = 0;
					}
					if (Inventar.selectedweapon == Weapon.AssaultRifle && game.ammo >= 1 && Game.ammodelay >= 60) {
						Game.mg = true;

						Thread threeBullets = new Thread(new Runnable() {
							public void run() {
								try {
									handler.addObject(new Bullet(tempObject.getX() + 16 * 2,
											tempObject.getY() + 24 * 2 + 15, ID.Bullet, handler, game, mx, my, null));
									Thread.sleep(50);
									handler.addObject(new Bullet(tempObject.getX() + 16 * 2,
											tempObject.getY() + 24 * 2 + 15, ID.Bullet, handler, game, mx, my, null));
									Thread.sleep(50);
									handler.addObject(new Bullet(tempObject.getX() + 16 * 2,
											tempObject.getY() + 24 * 2 + 15, ID.Bullet, handler, game, mx, my, null));
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
						});
						threeBullets.start();
						game.ammo -= 3;
						Game.ammodelay = 0;

					}
//					System.out.println(Math.sqrt((mx-(tempObject.getX()+32))*(mx-(tempObject.getX()+32)) + (my-(tempObject.getY()+48+15))*(my-(tempObject.getY()+48+15))));
				}
			}
		}

		if (Game.state == State.Win) {

			for (int i = 0; i < Game.win.Winbtns.length; i++) {
				Btn btns = Game.win.Winbtns[i];

				if (x >= btns.getX() && y >= btns.getY() && x <= btns.getX() + btns.getWidth()
						&& y <= btns.getY() + btns.getHeight()) {
					btns.triggerEvent();
				}
			}
		}
		if (Game.state == State.WinEnd) {

			for (int i = 0; i < Game.winend.WinEndbtns.length; i++) {
				Btn btns = Game.winend.WinEndbtns[i];

				if (x >= btns.getX() && y >= btns.getY() && x <= btns.getX() + btns.getWidth()
						&& y <= btns.getY() + btns.getHeight()) {
					btns.triggerEvent();
				}
			}
		}

		if (Game.state == State.OptionVideo) {
			if (Game.mouseklickdelay == 20) {
				Game.mouseklickdelay = 0;
				for (int i = 0; i < Game.optionvideo.videobtns.length; i++) {
					Btn btns = Game.optionvideo.videobtns[i];

					if (x >= btns.getX() && y >= btns.getY() && x <= btns.getX() + btns.getWidth()
							&& y <= btns.getY() + btns.getHeight()) {
						btns.triggerEvent();
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
