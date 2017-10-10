/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

public class Wall extends GameObj {
	
	public Wall(int x, int y) {
		super("wall", x, y);
		this.addNameTag("Blocking");
	}
}
