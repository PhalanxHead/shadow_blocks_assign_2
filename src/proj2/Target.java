/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

public class Target extends GameObj {
	
	private boolean activated = false;
	
	public Target(int x, int y) {
		super("target", x, y);
		this.addNameTag("Target");
	}
	
	public void update(int delta) {
		
	}
	
	public boolean isActivated() {
		return this.activated;
	}
}
