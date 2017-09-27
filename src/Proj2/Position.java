/**
 * @author 	Luke Hedt
 * StuID:	832153
 */

package Proj2;

/**
 * Holds the current X,Y position of the associated object in tiles
 */
public class Position {

	private int tileX;
	private int tileY;
	
	/**
	 * Position Constructor
	 * @param tileX : int. Takes X in tiles of the object.
	 * @param tileY : int. Takes Y in tiles of the object.
	 */
	public Position(int tileX, int tileY) {
		this.tileX = tileX;
		this.tileY = tileY;
	}
	
	/**
	 * @return tileX : int. Tile X  value of Position Obj
	 */
	public int getX() {
		return tileX;
	}
	
	/**
	 * @return tileY : int. Tile Y value of Position Obj
	 */
	public int getY() {
		return tileY;
	}
	
	/**
	 * Moves Position x tiles on X axis.
	 * @param x : int. Number of tiles to move. Negative x moves left.
	 */
	public void moveX(int x) {
		this.tileX += x;
	}
	
	/**
	 * Moves Position y tiles on Y axis.
	 * @param y : int. Number of tiles to move. Negative y moves up.
	 */
	public void moveY(int y) {
		this.tileY += y;
	}
}
