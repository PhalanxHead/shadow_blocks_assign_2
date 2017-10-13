/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

public class TNT extends Pushable {

	public TNT(int x, int y) {
		super("tnt", x, y);
	}
	
	public boolean active() {
		// Unimplemented
		return false;
	}
	
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
	
	@Override
	public void onMove(Dirs dir, int curTileX, int curTileY) {
		int[] newTilePos = newTilePos(dir, curTileX, curTileY);
		if(Board.isNameTag(newTilePos[Board.IND_X], newTilePos[Board.IND_Y], "Explodable")) {
			Board.createSpecialGameObj(new Explosion(newTilePos[Board.IND_X], newTilePos[Board.IND_Y]));
			Board.destroyGameObj(
					Board.getGameObjOfType("Explodable", newTilePos[Board.IND_X], newTilePos[Board.IND_Y]));
			Board.destroyGameObj(this);
		}
	}
	
}
