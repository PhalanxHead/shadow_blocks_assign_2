/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

/**
 * TNT, acts like a stone but also can explode Explodables
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry
 */
public class TNT extends Pushable {

	public TNT(int x, int y) {
		super("tnt", x, y);
	}
	
	/**
	 * Explodes Explodable blocks if they collide.
	 */
	@Override
	public boolean push(Dirs dir) {
		
		int[] newTilePos = Moveable.newTilePos(dir, this.getTileX(), this.getTileY());
		if(!Board.isBlocked(newTilePos[Board.IND_X], newTilePos[Board.IND_Y])
				&& !Board.isNameTag(newTilePos[Board.IND_X], newTilePos[Board.IND_Y], "Pushable")) {
			this.moveToDest(dir);
			return true;

		// Override if the block is explodable
		} else if(Board.isNameTag(newTilePos[Board.IND_X], newTilePos[Board.IND_Y], "Explodable") ) {
			this.moveToDest(dir);
			return true;
			
		}
		return false;
	}
	
	/**
	 * Destroys TNT, Explodable, and creates the Explosion Object.
	 */
	@Override
	public void onMove(Dirs dir, int curTileX, int curTileY) {
		int[] newTilePos = newTilePos(dir, curTileX, curTileY);
		
		if(Board.isNameTag(newTilePos[Board.IND_X], newTilePos[Board.IND_Y], "Explodable")) {
			// Create Explosion
			Board.createSpecialGameObj(new Explosion(newTilePos[Board.IND_X], newTilePos[Board.IND_Y]));
			// Destroy Explodable
			Board.destroyGameObj(
					Board.getGameObjOfType("Explodable", newTilePos[Board.IND_X], newTilePos[Board.IND_Y]));
			// Destroy the TNT
			Board.destroyGameObj(this);
		}
	}
}
