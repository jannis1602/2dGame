package game;

public class Timer {
	public long timerstart;
	public float timerzeit;
	public static boolean timerrunning = false;
	public static boolean timerrun = false;
	Thread thread;

	public Timer() {

	}

	public void timerstart() {
		timerstart = System.currentTimeMillis();

	}

	public void timerstop() {
		timerrunning = false;
	}

	public void timerrun() {
		if (timerrun) {
			System.out.println("run");
		}
		if (!timerrunning) {
			timerrunning = true;
		}
	}


}
