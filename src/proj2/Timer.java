package proj2;

public class Timer {

	private int time;
	private int target;
	
	public Timer(int target) {
		this.time = 0;
		this.target = target;
	}

	public void update(int delta) {
		this.time += delta;
	}
	
	public boolean expired() {
		if(this.time >= this.target) {
			this.time = 0;
			return true;
		}
		return false;
	}
}
