/**
 * @author 	Luke Hedt
 * StuID:	832153
 *
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 * 
 */

package proj2;

import org.newdawn.slick.Graphics;

public class Door extends GameObj {

	private boolean closed = true;
	
	public Door(int x, int y) {
		super("door", x, y);
	}

	public void render(Graphics g) {
		
	}
	
	public boolean getClosed() {
		return this.closed;
	}
}
