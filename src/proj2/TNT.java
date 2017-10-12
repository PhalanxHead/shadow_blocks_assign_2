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
