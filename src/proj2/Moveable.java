/**
 * @author Luke Hedt - 832153
 *  
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */


package proj2;

public abstract class Moveable extends GameObj {

	private HistoryStack histStack;
	
	public Moveable(String image_src, int tileX, int tileY) {
		super(image_src, tileX, tileY);
		this.addNameTag("Moveable");
		this.histStack = new HistoryStack();
	}

	public boolean hasHistory() {
		// Unimplemented
		return false;
	}
	
	public void addToHistory() {
		// Unimplemented
	}
	
	public void undo() {
		int[] newPos = this.histStack.popFromStack();
		if(newPos != null) {
			this.setTileX(newPos[Board.IND_X]);
			this.setTileY(newPos[Board.IND_Y]);
		}
	}
	
	public static int[] newTilePos(Dirs dir, int tileX, int tileY) {
		int speed = 1;
		int[] newTilePos = new int[2];
		// Translate the direction to an x and y displacement
		int delta_x = 0, delta_y = 0;
		switch (dir) {
			case LEFT:
				delta_x = -speed;
				break;
			case RIGHT:
				delta_x = speed;
				break;
			case UP:
				delta_y = -speed;
				break;
			case DOWN:
				delta_y = speed;
				break;
			default:
				break;
		}
		
		// Make sure the position isn't occupied!
		newTilePos[Board.IND_X] = tileX + delta_x;
		newTilePos[Board.IND_Y] = tileY + delta_y;
		
		return newTilePos;
	}
	
	/**
	 * Moves to destination
	 * @param dir : Dirs. The direction to move in.
	 * @return True if successful
	 */
	public boolean moveToDest(Dirs dir) {
		int[] newTilePos = newTilePos(dir, this.getTileX(), this.getTileY());
		int newTileX = newTilePos[Board.IND_X];
		int newTileY = newTilePos[Board.IND_Y];
		// Check Not Blocked
		if (!Board.isBlocked(newTileX, newTileY)) {
			// Check Pushable
			if(Board.isNameTag(newTileX, newTileY, "Pushable") 
					&& !Board.getGameObjOfType("Pushable", newTileX, newTileY).push(dir)) {
				return false;
			}
			if(newTileX != this.getTileX() || newTileY != this.getTileY()) {
				onMove(dir, this.getTileX(), this.getTileY());
			}
			
			this.setTileX(newTileX);
			this.setTileY(newTileY);
			return true;
		}
		return false;
	}
	
	public void onMove(Dirs dir, int curTileX, int curTileY) {
		// No Default
	}
	
	@Override
	public HistoryStack getHistStack() {
		return this.histStack;
	}
}
