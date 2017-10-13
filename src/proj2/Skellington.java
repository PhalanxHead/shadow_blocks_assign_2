/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import org.newdawn.slick.Input;

/**
 * Skeleton, an enemy Class. Moves Up and Down once per second.
 * @author Luke Hedt - 832153 ||
 * Based on Design by Eleanor McMurtry
 */
public class Skellington extends Moveable {

	// Time between Moves in ms
	private static final int WAIT = 1000;
	// Member Variables
	private Timer timer;
	private Dirs dir;
	
	public Skellington(int x, int y) {
		super("skull", x, y);
		this.addNameTag("Enemy");
		this.dir = Dirs.UP;
		this.timer = new Timer(WAIT);
	}
	
	/**
	 * Moves once per second, reverses if it can't move.
	 */
	@Override
	public void update(Input input, int delta) {
		this.timer.update(delta);
		
		if(timer.expired()) {
			if(!this.moveToDest(this.dir)) {
				this.reverseDirection();
			}
		} 
	}
	
	/**
	 * Skeletons don't roll back on undo.
	 */
	@Override
	public void undo() {
		// Unimplemented for Skeleton
	}
	
	/**
	 * Moves one Tile in Direction dir, doesn't push blocks.
	 */
	@Override
	public boolean moveToDest(Dirs dir) {
		int[] newTilePos = newTilePos(dir, this.getTileX(), this.getTileY());
		int newTileX = newTilePos[Board.IND_X];
		int newTileY = newTilePos[Board.IND_Y];
		
		// Bounds Checking
		if(!Loader.inBounds(newTileX, newTileY)) {
			return false;
		}
		
		if(newTileX != this.getTileX() || newTileY != this.getTileY()) {
			onMove(dir, this.getTileX(), this.getTileY());
		}
		// Check Not Blocked by blocking or Pushable
		if (Board.isBlocked(newTileX, newTileY) || Board.isNameTag(newTileX, newTileY, "Pushable")) {
			return false;
		} else {
			this.setTileX(newTileX);
			this.setTileY(newTileY);
			return true;
		}
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
