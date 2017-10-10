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
	
	public void onMove(Dirs dir, int testX, int testY) {
		//Unimplemented
	}
	
}
