/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

/**
 * Mage, chases player using algorithm set in specification. Enemy.
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry.
 */
public class Mage extends Moveable {

	public Mage(int x, int y) {
		super("mage", x, y);
		this.addNameTag("Enemy");
	}
	
	public boolean moveToDest(Dirs dir) {
		return true;
	}

}
