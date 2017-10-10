/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

public class Ice extends Pushable {

	public static final double WAIT = 0.25;
	
	Dirs dir;
	private int lastTileX;
	private int lastTileY;	
	private Timer timer;
	
	public Ice(int x, int y) {
		super("ice", x, y);
		this.timer = new Timer(WAIT, 5);
	}
	
	public void addToHistory() {
		// Unimplemented
	}
	
	public boolean active() {
		// Unimplemented
		return true;
	}
	
	public void undo() {
		// Unimplemented
	}
	
	@Override
	public boolean push(Dirs dir) {
		this.dir = dir;
		
		// Unimplemented
		return false;
	}
	
	public void update(int delta) {
		// Unimplemented
	}
}
