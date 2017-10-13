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
 * Ice block - Slides until it hits another block or Wall.
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry
 */
public class Ice extends Pushable {

	// Time between moving in ms
	public static final int WAIT = 250;
	
	// Member Variables
	private Dirs dir;
	private int lastTileX;
	private int lastTileY;
	private Timer timer;
	private boolean isActive;
	
	public Ice(int x, int y) {
		super("ice", x, y);
		this.addNameTag("Timeable");
		this.addNameTag("Ice");
		this.timer = null;
		this.isActive = false;
		this.lastTileX = this.getTileX();
		this.lastTileY = this.getTileY();
	}

	/**
	 * Only moves if it isn't already sliding
	 */
	@Override
	public boolean push(Dirs dir) {
		if(!this.isActive) {
			this.dir = dir;
			this.lastTileX = this.getTileX();
			this.lastTileY = this.getTileY();
			this.timer = new Timer(WAIT);
			this.getHistStack().pushToStack(this.lastTileX, this.lastTileY);
			
			if(moveToDest(dir)) {
				this.isActive = true;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Moves every time the time expires
	 */
	@Override
	public void update(Input input, int delta) {
		if(this.timer != null) {
			timer.update(delta);
		}
		if(this.isActive && timer.expired()) {
			this.moveToDest(this.dir);
		} 
	}
	
	/**
	 * Move one tile, stop when a block is hit.
	 */
	@Override
	public boolean moveToDest(Dirs dir) {
		int[] newTilePos = newTilePos(dir, this.getTileX(), this.getTileY());
		int newTileX = newTilePos[Board.IND_X];
		int newTileY = newTilePos[Board.IND_Y];
		// Check Not Blocked
		if(Board.isBlocked(newTileX, newTileY)) {
			this.isActive = false;
			this.timer = null;
			return false;
		}
		
		this.setTileX(newTileX);
		this.setTileY(newTileY);
		
		return true;
	}
	
	/**
	 * Undoes a move and stops the block moving.
	 */
	@Override
	public void undo() {
		int[] newPos = this.getHistStack().popFromStack();
		this.isActive = false;
		if(newPos != null) {
			this.setTileX(newPos[Board.IND_X]);
			this.setTileY(newPos[Board.IND_Y]);
		}
	}
	
	/**
	 * Sets the active state of the block.
	 * @param isActive : boolean. True if block is moving.
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * Gets the Last TileX Value.
	 * @return LastTileX
	 */
	public int getLastTileX() {
		return this.lastTileX;
	}
	
	/**
	 * Gets the Last TileY Value.
	 * @return LastTileY
	 */
	public int getLastTileY() {
		return this.lastTileY;
	}
	
	/**
	 * Returns the direction of the object
	 * @return direction as Dirs.
	 */
	public Dirs getDir() {
		return this.dir;
	}
}
