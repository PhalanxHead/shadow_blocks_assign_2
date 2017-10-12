/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import org.newdawn.slick.Input;

public class Ice extends Pushable {

	// Time between moving in ms
	public static final int WAIT = 250;
	
	Dirs dir;
	private int lastTileX;
	private int lastTileY;
	private Timer timer;
	private boolean isActive;
	
	public Ice(int x, int y) {
		super("ice", x, y);
		this.addNameTag("Timeable");
		this.timer = null;
		this.isActive = false;
	}

	@Override
	public boolean push(Dirs dir) {
		this.dir = dir;
		this.lastTileX = this.getTileX();
		this.lastTileY = this.getTileY();
		this.timer = new Timer(WAIT);
		this.getHistStack().pushToStack(this.lastTileX, this.lastTileY);
		if(moveToDest(dir)) {
			this.isActive = true;
		}
		return false;
	}
	
	@Override
	public void update(Input input, int delta) {
		if(this.timer != null) {
			timer.update(delta);
		}
		if(this.isActive && timer.expired()) {
			this.moveToDest(this.dir);
		} 
	}
	
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
}
