package proj2;

/**
 * Timer for time-based events
 * @author Luke Hedt - 832153
 *
 */
public class Timer {

	private int time;
	private int target;
	
	public Timer(int target) {
		this.time = 0;
		this.target = target;
	}

	/**
	 * Adds Delta ms to the timer
	 * @param delta : int. Time since last update in ms.
	 */
	public void update(int delta) {
		this.time += delta;
	}
	
	/**
	 * Resets when the timer hits the target, then returns true.
	 * @return True if the timer has expired and been reset.
	 */
	public boolean expired() {
		if(this.time >= this.target) {
			this.time = 0;
			return true;
		}
		return false;
	}
}
