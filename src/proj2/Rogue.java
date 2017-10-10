/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

public class Rogue extends Moveable {

	public Rogue(int x, int y) {
		super("rogue", x, y);
	}
	
	public boolean moveToDest(Dirs dir) {
		return true;
	}

}
