/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

/**
 * Cracked Wall Object. Explodable. Blocking.
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry
 */
public class CrackedWall extends GameObj {

	public CrackedWall(int tileX, int tileY) {
		super("cracked_wall", tileX, tileY);
		this.addNameTag("Explodable");
		this.addNameTag("Blocking");
	}
}