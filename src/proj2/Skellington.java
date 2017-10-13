/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import org.newdawn.slick.Input;

public class Skellington extends Moveable {

	private static final int WAIT = 1000;
	private Timer timer;
	private Dirs dir;
	
	public Skellington(int x, int y) {
		super("skull", x, y);
		this.addNameTag("Enemy");
		this.dir = Dirs.UP;
		this.timer = new Timer(WAIT);
	}
	
	@Override
	public void update(Input input, int delta) {
		this.timer.update(delta);
		
		if(timer.expired()) {
			if(!this.moveToDest(this.dir)) {
				this.reverseDirection();
			}
		} 
	}
	
	@Override
	public void onMove(Dirs dir, int curTileX, int curTileY) {
		// Unimplemented for Skeleton
	}
	
	@Override
	public void undo() {
		// Unimplemented for Skeleton
	}
	
	/**
	 * Reverses the direction of the object
	 */
	private void reverseDirection() {
		if(this.dir == Dirs.UP) {
			this.dir = Dirs.DOWN;
		} else {
			this.dir = Dirs.UP;
		}
	}
}
