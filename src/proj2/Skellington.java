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
	}
	
	public boolean moveToDest(Dirs dir) {
		return true;
	}

}
