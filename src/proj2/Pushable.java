/**
 * @author Luke hedt - 832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

/**
 * Pushable Objects can be pushed.
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry.
 */
public class Pushable extends Moveable {

	public Pushable(String image_src, int tileX, int tileY) {
		super(image_src, tileX, tileY);
		this.addNameTag("Pushable");
	}
	
	@Override
	public boolean push(Dirs dir) {
		int[] newTilePos = Moveable.newTilePos(dir, this.getTileX(), this.getTileY());
		
		// Bounds Checking
		if(!Loader.inBounds(newTilePos[Board.IND_X], newTilePos[Board.IND_Y])) {
			return false;
		}
		
		if(!Board.isBlocked(newTilePos[Board.IND_X], newTilePos[Board.IND_Y])
				&& !Board.isNameTag(newTilePos[Board.IND_X], newTilePos[Board.IND_Y], "Pushable")) {
			this.moveToDest(dir);
			return true;
		}
		return false;
	}
}
