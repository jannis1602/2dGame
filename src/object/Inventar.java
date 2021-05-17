package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Image.SpriteSheet;
import game.Game;

public class Inventar {
	public static Weapon inv1, inv2;
	public static Weapon selectedweapon;
	public static int selectedInv = 1;
	public SpriteSheet weaponsss;
	public BufferedImage pistoleimg = null, pumpgunimg = null, lasergunimg = null, assaultrifleimg = null;

	public Inventar(SpriteSheet weaponsss) {
		this.weaponsss = weaponsss;
		pistoleimg = weaponsss.grabImage64(3, 1, 64, 64);
		pumpgunimg = weaponsss.grabImage64(3, 2, 64, 64);
		lasergunimg = weaponsss.grabImage64(1, 2, 64, 64);
		assaultrifleimg = weaponsss.grabImage64(2, 2, 64, 64);
		inv1 = Weapon.Pistole;
//		inv1 = inv1.LaserGun;
//		inv2 = inv2.AssaultRifle;
		inv2 = Weapon.Leer;
		selectedweapon = inv1;
	}

	public void render(Graphics g) {
//		inv1=inv1.Pistole;

		g.setColor(new Color(180, 180, 180, 200));
		g.fillRoundRect(1, 100, 60, 60, 20, 20);
		g.fillRoundRect(1, 160, 60, 60, 20, 20);
//		g.fillRect(0, 100, 60, 180);
		g.setColor(new Color(0, 0, 0, 200));
		g.drawRoundRect(1, 100, 60, 60, 20, 20);
		g.drawRoundRect(1, 160, 60, 60, 20, 20);
//		g.drawRect(0, 220, 60, 60);
		if (selectedInv == 1) {
			g.setColor(new Color(60, 150, 0, 200));
			g.fillRoundRect(1, 100, 60, 60, 20, 20);
			g.setColor(new Color(0, 0, 0, 200));
			g.drawRoundRect(1, 100, 60, 60, 20, 20);
		}
		if (selectedInv == 2) {
			g.setColor(new Color(60, 150, 0, 200));
			g.fillRoundRect(1, 160, 60, 60, 20, 20);
			g.setColor(new Color(0, 0, 0, 200));
			g.drawRoundRect(1, 160, 60, 60, 20, 20);
		}
		if (selectedInv == 1)
			selectedweapon = inv1;
		if (selectedInv == 2)
			selectedweapon = inv2;
		switch (inv1) {
		case Leer:
			break;
		case Pistole:
			g.drawImage(pistoleimg, 0, 100, 64, 64, null);
			if (selectedInv == 1 && 30 - Game.ammodelay > 0) {
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(30 - Game.ammodelay), 72, 110);
				g.setColor(Color.RED);
				g.fillRect(62, 100, 10, 60 - Game.ammodelay * 2);
			}
			break;
		case Pumpgun:
			g.drawImage(pumpgunimg, 0, 100, 64, 64, null);
			if (selectedInv == 1 && 50 - Game.ammodelay > 0) {
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(50 - Game.ammodelay), 72, 110);
				g.setColor(Color.RED);
				g.fillRect(62, 100, 10, 50 - Game.ammodelay);
			}
			break;
		case LaserGun:
			g.drawImage(lasergunimg, 0, 100, 64, 64, null);
			if (selectedInv == 1 && 80 - Game.ammodelay > 0) {
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(80 - Game.ammodelay), 72, 110);
				g.setColor(Color.RED);
				g.fillRect(62, 100, 10, 80 - Game.ammodelay);
			}
			break;
		case AssaultRifle:
			g.drawImage(assaultrifleimg, 0, 100, 64, 64, null);
			if (selectedInv == 1 && 60 - Game.ammodelay > 0) {
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(60 - Game.ammodelay), 72, 110);
				g.setColor(Color.RED);
				g.fillRect(62, 100, 10, 60 - Game.ammodelay);
			}

		default:
			break;
		}
		switch (inv2) {
		case Leer:
			break;
		case Pistole:
			g.drawImage(pistoleimg, 0, 160, 64, 64, null);
			if (selectedInv == 2 && 30 - Game.ammodelay > 0) {
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(30 - Game.ammodelay), 72, 170);
				g.setColor(Color.RED);
				g.fillRect(62, 160, 10, 60 - Game.ammodelay * 2);
			}
			break;
		case Pumpgun:
			g.drawImage(pumpgunimg, 0, 160, 64, 64, null);
			if (selectedInv == 2 && 50 - Game.ammodelay > 0) {
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(50 - Game.ammodelay), 72, 170);
				g.setColor(Color.RED);
				g.fillRect(62, 160, 10, 50 - Game.ammodelay);
			}
			break;
		case LaserGun:
			g.drawImage(lasergunimg, 0, 160, 64, 64, null);
			if (selectedInv == 2 && 80 - Game.ammodelay > 0) {
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(80 - Game.ammodelay), 72, 170);
				g.setColor(Color.RED);
				g.fillRect(62, 160, 10, (80 - Game.ammodelay) / 3 * 2);
			}
			break;
		case AssaultRifle:
			g.drawImage(assaultrifleimg, 0, 160, 64, 64, null);
			if (selectedInv == 2 && 60 - Game.ammodelay > 0) {
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(60 - Game.ammodelay), 72, 170);
				g.setColor(Color.RED);
				g.fillRect(62, 160, 10, (60 - Game.ammodelay) / 3 * 2);
			}
			break;

		default:
			break;
		}
	}
	public void invclear() {
		inv1 = Weapon.Pistole;
		inv2 = Weapon.Leer;
		selectedInv=1;
	}

}
