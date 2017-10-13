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
		this.addNameTag("Blocking");
		this.addNameTag("Door");
	}

	@Override
	public void render(Graphics g) {
		if(this.getClosed()) {
			super.render(g);
		} else {
			return;
		}
	}
	
	public void setClosed(boolean closed) {
		this.closed = closed;
		if(closed) {
			this.addNameTag("Blocking");
		} else {
			this.removeNameTag("Blocking");

		}
	}
	
	public boolean getClosed() {
		return this.closed;
	}
}
