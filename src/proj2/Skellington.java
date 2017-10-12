/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

public class Skellington extends Moveable {

	public Skellington(int x, int y) {
		super("skull", x, y);
		this.addNameTag("Enemy");
	}
	
	public boolean moveToDest(Dirs dir) {
		return true;
	}
	
	@Override
	public void onMove(Dirs dir, int curTileX, int curTileY) {
		// Unimplemented for Skeleton
	}
	
	@Override
	public void undo() {
		// Unimplemented for Skeleton
	}
}
