package object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Image.SpriteSheet;
import game.Game;
import game.Handler;
import game.ID;

public class WeaponBox extends GameObject {
	private BufferedImage weapon_box;
	Random r = new Random();
	int choose = 0;
	public Weapon weaponInBox, newWeaponInBox;
	private boolean showE = false;
	public BufferedImage pistoleimg = null, pumpgunimg = null, lasergunimg = null, assaultrifleimg = null;

	public WeaponBox(int x, int y, ID id, SpriteSheet ss, SpriteSheet weaponsss, Handler handler, Game game) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;
		weapon_box = ss.grabImage(6, 2, 32, 32);

		pistoleimg = weaponsss.grabImage64(3, 1, 64, 64);
		pumpgunimg = weaponsss.grabImage64(3, 2, 64, 64);
		lasergunimg = weaponsss.grabImage64(1, 2, 64, 64);
		assaultrifleimg = weaponsss.grabImage64(2, 2, 64, 64);
		choose = r.nextInt(4);
		System.out.println(choose);
		if (choose == 0)
			weaponInBox = Weapon.Pistole;
		if (choose == 1)
			weaponInBox = Weapon.Pumpgun;
		if (choose == 2)
			weaponInBox = Weapon.LaserGun;
		if (choose == 3)
			weaponInBox = Weapon.AssaultRifle;
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.CYAN);
//		g2d.draw(getBounds());
		g2d.draw(getHitBounds());
//		g.setColor(Color.BLACK);
//		g.fillRect(x, y, 64, 64);
		g.drawImage(weapon_box, x, y, 64, 64, null);
		switch (weaponInBox) {
		case Pistole:
			g.drawImage(pistoleimg, x, y, 64, 64, null);
			break;
		case Pumpgun:
			g.drawImage(pumpgunimg, x, y, 64, 64, null);
			break;
		case LaserGun:
			g.drawImage(lasergunimg, x, y, 64, 64, null);
			break;
		case AssaultRifle:
			g.drawImage(assaultrifleimg, x, y, 64, 64, null);
			break;

		default:
			break;
		}
		if (showE == true) {
			g.setColor(Color.WHITE);
			g.drawRect(x + 66, y - 20, 20, 20);
			g.drawRect(x + 67, y - 19, 19, 19);
			g.setFont(new Font("Plain", 1, 20));
			g.drawString("E", x + 70, y - 2);
			g.setFont(new Font("Plain", 1, 16));
			g.drawString(weaponInBox.toString(), x - 20, y);

		}
	}

	public void tick() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (getHitBounds().intersects(tempObject.getBounds())) {
					showE = true;
				} else if (!getHitBounds().intersects(tempObject.getBounds())) {
					showE = false;
				}
			}
		}
		if (showE && handler.isE() == true && Game.mouseklickdelay >= 20) {
			Game.mouseklickdelay = 0;
			if (Inventar.inv2 == Weapon.Leer)
				Inventar.selectedInv = 2;
			if (Inventar.inv1 == Weapon.Leer)
				Inventar.selectedInv = 1;
			if (Inventar.selectedInv == 1) {
				newWeaponInBox = Inventar.inv1;
				Inventar.inv1 = weaponInBox;
				Inventar.selectedweapon = Inventar.inv1;
				weaponInBox = newWeaponInBox;
			}
			if (Inventar.selectedInv == 2) {
				newWeaponInBox = Inventar.inv2;
				Inventar.inv2 = weaponInBox;
				Inventar.selectedweapon = Inventar.inv2;
				weaponInBox = newWeaponInBox;
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32 * 2, 32 * 2);
	}

	public Rectangle getHitBounds() {
		return new Rectangle(x - 64, y - 64, 64 * 3, 64 * 3);
	}

}
