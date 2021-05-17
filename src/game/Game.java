package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Image.BufferedImageLoader;
import Image.SpriteSheet;
import gui.GameMenu;
import gui.Launcher;
import gui.Options;
import gui.State;
import gui.Win;
import gui.WinEnd;
import gui.options.OptionVideo;
import input.KeyInput;
import input.MouseInput;
import object.Block;
import object.Crate;
import object.Enemy;
import object.GameObject;
import object.Healing;
import object.Inventar;
import object.Player;
import object.Weapon;
import object.WeaponBox;
import sound.PlaySound;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static JFrame frame;

	public static State state;
	public static int width = 1920;
	public static int height = (width / 16) * 9;
	public int newframes = 0, newupdates = 0;
	public int hp = 200, enemysleft = 0, ammo = 50, weaponbox = 0;
	public static boolean mg = false;
	float althp = 200;
	public int spawnx, spawny;
	public static int ammodelay = 10;
	public static int mouseklickdelay = 20;
	public static int level = 1, maxlevel = 3;
	public float timersec;
	public int timermin;
	public static Weapon[] weaponsOnMap;

	public static boolean running = false, playing;
//	public boolean showDeathScreen = false;
	private Thread thread;
	public static Handler handler;
	public static GameMenu gamemenu;
	public static Camera cam;
	public static Launcher launcher;
	public static Options options;
	public static OptionVideo optionvideo;
	public static Win win;
	public static WinEnd winend;
	public static Timer timer;
	public Inventar inventar;
	public PlaySound playsound;
	private BufferedImage sprite_sheet = null, weapons_sprite_sheet = null;
	private BufferedImage floor = null;
	public static Image cursorImage;
	public static BufferedImageLoader loader;

	static BufferedImage map = null;
	public SpriteSheet ss;
	public SpriteSheet weaponsss;

	public Game() {

	}

	private void init() {
		loader = new BufferedImageLoader();
		handler = new Handler();
		timer = new Timer();
//		sprite_sheet = loader.loadImage("res/SpriteSheet2DGame5.png");
		sprite_sheet = loader.loadImage("SpriteSheet2DGame6.png");
		weapons_sprite_sheet = loader.loadImage("weaponsSprite2.png");
		map = loader.loadImage("GameMap10Level1.png");
		cursorImage = Game.loader.loadImage("cursor2.png");

		ss = new SpriteSheet(sprite_sheet);
		weaponsss = new SpriteSheet(weapons_sprite_sheet);
//		loadLevel(map);

		floor = ss.grabImage(4, 2, 32, 32);
		cam = new Camera(100, 100);
		launcher = new Launcher();
		options = new Options();
		inventar = new Inventar(weaponsss);
		playsound = new PlaySound();

//		options.changeCursor();

		optionvideo = new OptionVideo();
		gamemenu = new GameMenu();
		win = new Win();
		winend = new WinEnd();
		MouseInput mouse = new MouseInput(this, handler, cam);

		addKeyListener(new KeyInput(handler, this));
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		frame.setVisible(true);
	}

	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0, updates = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (Timer.timerrunning == true)
				Game.timer.timerzeit = System.currentTimeMillis() - Game.timer.timerstart;
			if (System.currentTimeMillis() - timer > 1000) {
				timer = System.currentTimeMillis(); // timer += 1000;
				if (frames <= 60)
					System.out.print("LOW FPS!!!   ");
				newframes = frames;
				newupdates = updates;
//				System.out.println("FPS: " + frames + " updates" + updates);
				frames = 0;
				updates = 0;

			}
		}
		stop();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		////////////////////////////////

//			g.setColor(Color.gray.darker());
//			g.fillRect(0, 0, 2000, 1200);

//			g2d.translate(-cam.getX(), -cam.getY());

//			if (running)g.translate(-cam.getX(), -cam.getY());

//			for (int xx = 0; xx < 30 * 72 * 2; xx += 32 * 2) {
//				for (int yy = 0; yy < 30 * 72 * 2; yy += 32 * 2) {
//					g.drawImage(floor, xx, yy, 64 * 2, 64 * 2, null);
//				}
//			}

//			g2d.translate(cam.getX(), cam.getY());

//			int alpha = 150;
//			g.setColor(new Color(0, 255, 255, alpha));
//
//			g.setColor(new Color(153, 153, 153, alpha));
//			g.fillRect(5, 5, 200 * 2, 32);
//			g.setColor(new Color(0, 204, 0, alpha));
//			if (hp <= 50)
//				g.setColor(new Color(255, 0, 0, alpha));
//			g.fillRect(5, 5, hp * 2, 32);
//			g.setColor(new Color(0, 0, 0, alpha));
//			g.drawRect(5, 5, 200 * 2, 32);

//			Font newFont = new java.awt.Font("Dialog", 0, 14);
//			g.setFont(newFont);
//			g.setColor(Color.WHITE);
//			g.drawString("Ammo: " + ammo, 5, 50);
//			g.setColor(Color.WHITE);
//			g.drawString("Enemys left: " + enemysleft, 80, 50);

//		PointerInfo pi = MouseInfo.getPointerInfo();
//		Point mp = pi.getLocation();
//		int mousex = (int) MouseInfo.getPointerInfo().getLocation().getX() - getX() - 5 * 2;
//		int mousey = (int) MouseInfo.getPointerInfo().getLocation().getY() - getY() - 25 - 20;// mp.getY();
//		System.out.print("X" + mousex);
//		System.out.println("Y" + mousey);
//		g.drawImage(cursor, mousex - 30, mousey - 16, 16 * 4, 16 * 4, null);

		if (state == State.Ingame || state == State.GameMenue || state == State.Win || state == State.WinEnd) {

			if (state == State.Ingame)
				Game.timer.timerrun();

//			g.setColor(Color.gray.darker());
//			g.fillRect(0, 0, 5000, 5000);

			g2d.translate(-cam.getX(), -cam.getY());

//			g.setColor(Color.WHITE);
//			g2d.draw(getVisibleArea());

//			if (x >= Game.map.getWidth() * 34)
//			x = Game.map.getWidth() * 34;
//		if (y >= Game.map.getHeight() * 34 + 900)
//			y = Game.map.getHeight() * 34 + 900;

			// Floor
//			for (int xx = 0; xx < 64 * 100; xx += 64) {
//				for (int yy = 0; yy < 64 * 100; yy += 64) {
//					if (xx >= cam.getX() - 64 && xx <= cam.getX() + frame.getWidth() + 64)
//						if (yy >= cam.getY() - 64 && yy <= cam.getY() + frame.getHeight() + 64)
//							g.drawImage(floor, xx, yy, 64 * 2, 64 * 2, null);
//				}
//			}

			for (int xx = 0; xx <= 64 * map.getWidth(); xx += 64 * 2) {
				for (int yy = 0; yy < 64 * map.getHeight(); yy += 64 * 2) {
					g.drawImage(floor, xx, yy, 64 * 2, 64 * 2, null);
				}
			}

//			for (int xx = 0; xx < 30 * 72 * 2; xx += 32 * 2) {
//				for (int yy = 0; yy < 30 * 72 * 2; yy += 32 * 2) {
//					// hitbox!!
//					if (xx >= cam.getX() - 64 && xx <= cam.getX() + frame.getWidth() + 64)
//						if (yy >= cam.getY() - 64 && yy <= cam.getY() + frame.getHeight() + 64)
//							g.drawImage(floor, xx, yy, 64 * 2, 64 * 2, null);
//				}
//			}

			handler.render(g);

			g.setColor(Color.WHITE);
			g2d.draw(getVisibleArea());
			g.setColor(Color.YELLOW);
			g2d.draw(getTickArea());

			g2d.translate(cam.getX(), cam.getY());

			int alpha = 150;
			g.setColor(new Color(0, 255, 255, alpha));

			g.setColor(new Color(153, 153, 153, alpha));
			g.fillRect(5, 5, 200 * 2, 32);
			g.setColor(new Color(0, 204, 0, alpha));
			if (hp <= 50)
				g.setColor(new Color(255, 0, 0, alpha));
			if (hp < althp) {
				g.setColor(new Color(255, 100, 0));
				althp -= 0.05f;
			}
			g.fillRect(5, 5, hp * 2, 32);
			g.setColor(new Color(0, 0, 0, alpha));
			g.drawRect(5, 5, 200 * 2, 32);
			Font newFont = new java.awt.Font("Dialog", 1, 14);
			g.setFont(newFont);
			g.setColor(new Color(15, 130, 0, 180));
			g.drawString(hp + " / 200 ", 10, 26);
			g.setColor(Color.WHITE);
			g.drawString("Ammo: " + ammo, 5, 50);
			g.setColor(Color.WHITE);
			g.drawString("Enemys left: " + enemysleft, 80, 50);
			g.setColor(Color.WHITE);
			g.drawString("Level: " + level, 190, 50);

			inventar.render(g);

			Font font = new java.awt.Font("Dialog", 1, 20);
			g.setFont(font);
			g.setColor(Color.WHITE);
			timersec = timer.timerzeit / 1000 - (60 * timermin);
			if (timersec >= 60) {
				timersec = 0;
				timermin++;
			}
			g.drawString("Time: " + timermin + ":" + timersec, getFrameWidth() - 160, 30);

			newFont = new java.awt.Font("Dialog", 1, 14);
			g.setFont(newFont);

			if (state == State.GameMenue) {
				gamemenu.render(g);
				timer.timerstop();
			}
			if (state == State.Win) {
				win.render(g);
				timer.timerstop();
			}
			if (state == State.WinEnd) {
				winend.render(g);
				timer.timerstop();
			}
		}
		if (state == State.Launcher) {
			launcher.render(g);
		}
		if (state == State.OptionVideo) {
			optionvideo.render(g);
		}
		if (state == State.Options || state == State.GameOptions) {
//			System.out.println("options");
			options.render(g);
		}
		if (state == State.Death) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Player)
					handler.removeObject(tempObject);
			}
			handler.addObject(new Player(spawnx * 64, spawny * 64, ID.Player, handler, this, ss));
			handler.wasdup();
			hp = 200;
			if (ammo <= 10)
				ammo += 20;
			state = State.Ingame;
		}
		if (state == State.NewGame) {
			if (state == State.NewGame && !Timer.timerrunning) {
				Game.timer.timerstop();
				Game.timer.timerstart();
			}
			timermin=0;
			timersec=0;
			timer.timerzeit=0;
			inventar.invclear();
			handler.wasdup();
			hp = 200;
			enemysleft = 0;
			ammo = 50;
			handler.object.clear();
			loadLevelImage();
			loadLevel(map);
			state = State.Ingame;
		}
		if (state == State.NextLevel) {
			nextlevel();
			state = State.NewGame;
		}
//		if (state == State.NextLevel) {
//			Game.timer.timerstop();
//			nextlevel();
//			handler.wasdup();
//			timermin = 0;
//			timersec = 0;
//			hp = 200;
//			enemysleft = 0;
//			ammo = 50;
//			handler.object.clear();
//			loadLevel(map);
//			state = State.Ingame;
//		}

		// Frame Mitte
//		g.setColor(Color.CYAN);
//		g.drawRect(frame.getWidth()/2, 0, 2, frame.getHeight());

		Font newFont = new java.awt.Font("Dialog", 1, 16);
		g.setFont(newFont);
		g.setColor(Color.WHITE);
		g.drawString("FPS: " + newframes, 5, getHeight() - 80);
		g.drawString("Updates:" + newupdates, 5 + 100, getHeight() - 80);
		g.dispose();
		bs.show();
	}

	public void tick() {
		if (mouseklickdelay < 20)
			mouseklickdelay++;
		if (state == State.Ingame) {
			handler.tick();
			for (int i = 0; i < handler.object.size(); i++) {
				if (handler.object.get(i).getId() == ID.Player) {
					cam.tick(handler.object.get(i));
				}
			}

//			System.out.println(ammodelay);
			if (ammodelay < 100)
				ammodelay++;
			if (enemysleft == 0) {
				if (level == maxlevel) {
					state = State.WinEnd;
					System.out.println("WinEnd");
				} else
					state = State.Win;
			}
		}

	}

	// loading level
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 60)
					handler.addObject(new Block(xx * 64, yy * 64, ID.Block, ss));
				if (red == 0 && green == 0 && blue == 0)
					handler.addObject(new Block(xx * 64, yy * 64, ID.Block, ss));
				if (red == 255 && green == 0 && blue == 0) {
					handler.addObject(new Enemy(xx * 64, yy * 64, ID.Enemy, handler, this, ss));
					enemysleft++;
				}
				if (red == 30 && green == 255 && blue == 0) {
					handler.addObject(new WeaponBox(xx * 64, yy * 64, ID.WeaponBox, ss, weaponsss, handler, this));
					weaponbox++;
				}
//				if (red == 120 && green == 0 && blue == 0) {
//					handler.addObject(new EnemyBig(xx * 64, yy * 64, ID.EnemyBig, handler, this, ss));
//					enemysleft++;
//				}

				if (red == 0 && green == 0 && blue == 255) {
					handler.addObject(new Player(xx * 64, yy * 64, ID.Player, handler, this, ss));
					spawnx = xx;
					spawny = yy;
//					handler.addObject(new Enemy(xx * 64, yy * 64, ID.Enemy, handler, this, ss));
//					enemysleft++;
				}
				if (red == 0 && green == 255 && blue == 255)
					handler.addObject(new Crate(xx * 64, yy * 64, ID.Creat, ss));
				if (red == 255 && green == 100 && blue == 100)
					handler.addObject(new Healing(xx * 64, yy * 64, ID.Healing, ss));
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		Game.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		Game.height = height;
	}

	public static int getFWidth() {
		return width;
	}

	public static int getFHeight() {
		return height;
	}

	public static int getFrameWidth() {
		return frame.getWidth();
	}

	public static int getFrameHeight() {
		return frame.getHeight();
	}

//	public static Rectangle getVisibleArea() {
//		for (int i = 0; i < handler.object.size(); i++) {
//			GameObject tempObject = handler.object.get(i);
//			if (tempObject.getId() == ID.Player)
//				return new Rectangle(tempObject.getX() - (getFrameWidth() / 2 - 5),
//						tempObject.getY() - (getFrameHeight() / 2 - 5), getFrameWidth() + 10, getFrameWidth() + 10);
//		}
//		return null;
//	}

	public static Rectangle getVisibleArea() {
//		for (int i = 0; i < handler.object.size(); i++) {
//			GameObject tempObject = handler.object.get(i);
			return new Rectangle((int) cam.getX()-10, (int) cam.getY()-10, getFrameWidth() + 10, getFrameHeight() + 10);
//			return new Rectangle((int) cam.getX() + 60, (int) cam.getY() + 60, getFrameWidth() - 120,
//					getFrameHeight() - 120);
//		}
//		return null;
	}

	public static Rectangle getTickArea() {
//		for (int i = 0; i < handler.object.size(); i++) {
//			GameObject tempObject = handler.object.get(i);
//			return new Rectangle((int) cam.getX() + 50, (int) cam.getY() + 50, getFrameWidth() - 100,
//					getFrameHeight() - 100);
			return new Rectangle((int) cam.getX() -200, (int) cam.getY() -200, getFrameWidth() +400,
					getFrameHeight() +400);
//		}
//		return null;
	}

	public void newgame() {
		handler.clearObject();
		loadLevel(map);

	}

	public static void nextlevel() {
		level++;
		loadLevelImage();
	}

	public static void loadLevelImage() {
		switch (level) {
		case 1:
			handler.clearObject();
			map = loader.loadImage("GameMap10Level1.png");
			break;
		case 2:
			handler.clearObject();
			map = loader.loadImage("GameMap10Level2.png");
			break;
		case 3:
			handler.clearObject();
			map = loader.loadImage("GameMap10Level1.png");
			break;
		case 4:
			handler.clearObject();
			map = loader.loadImage("GameMap10Level2.png");
			break;
		case 5:
			handler.clearObject();
			map = loader.loadImage("GameMap10Level1.png");
			break;

		default:
			break;
		}
	}

	public static void main(String[] args) {
//		new Reader();

		System.out.println(Toolkit.getDefaultToolkit().getScreenSize().width + " x "
				+ Toolkit.getDefaultToolkit().getScreenSize().height);
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		state = State.Launcher;
		Game game = new Game();
		frame = new JFrame();
		BufferedImageLoader iconload = new BufferedImageLoader();
		Image frameicon = iconload.loadImage("GameMap10Level1.png");
		frame.setIconImage(frameicon);
		frame.add(game);
		frame.setSize(width, height);
		frame.setResizable(true);
		// frame.setCursor(Cursor.MOVE_CURSOR);
//		frame.setCursor(Cursor.WAIT_CURSOR);
//		frame.setCursor(Txtoptions.optionCursor);
//		frame.setCursor(Txtoptions.optionCursor);
		Image customImage = iconload.loadImage("cursor2.png");
		Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(customImage, new Point(0, 0),
				"customCursor");
		frame.setCursor(customCursor);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setUndecorated(true);
//		frame.setVisible(true);
		game.start();
		level = 1;

	}
}
