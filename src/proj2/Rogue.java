/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

/**
 * Rogue, moves left and right as the player moves.
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry
 */
public class Rogue extends Moveable {

	private Dirs dir;
	
	public Rogue(int x, int y) {
		super("rogue", x, y);
		this.addNameTag("Enemy");
		this.addNameTag("Rogue");
		this.dir = Dirs.LEFT;
	}

	/**
	 * Tries to move and reverses direction if it can't.
	 */
	@Override
	public boolean moveToDest(Dirs dir) {
		if(!super.moveToDest(dir)) {
			this.reverseDirection();
		}
		// Nothing is dependent on this so return true by default.
		return true;
	}

	/**
	 * Gets the Rogue's current direction.
	 * @return The direction as Dirs.
	 */
	public Dirs getDir() {
		return this.dir;
	}
	
	/**
	 * Reverses the direction of the object
	 */
	private void reverseDirection() {
		if(this.dir == Dirs.LEFT) {
			this.dir = Dirs.RIGHT;
		} else {
			this.dir = Dirs.LEFT;
		}
	}
}
