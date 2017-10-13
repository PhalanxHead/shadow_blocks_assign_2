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

/**
 * Door Object. Disappears when the related switch is covered by a block.
 * Blocking. Door.
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry
 */
public class Door extends GameObj {

	private boolean closed = true;
	
	public Door(int x, int y) {
		super("door", x, y);
		this.addNameTag("Blocking");
		this.addNameTag("Door");
	}

	/**
	 * Only render if the door is closed.
	 */
	@Override
	public void render(Graphics g) {
		if(this.getClosed()) {
			super.render(g);
		} else {
			return;
		}
	}
	
	/**
	 * Sets the door's state.
	 * @param closed : boolean. True if closed.
	 */
	public void setClosed(boolean closed) {
		this.closed = closed;
		if(closed) {
			this.addNameTag("Blocking");
		} else {
			this.removeNameTag("Blocking");

		}
	}
	
	/**
	 * Returns if the door is closed.
	 * @return True if door is closed.
	 */
	public boolean getClosed() {
		return this.closed;
	}
}
