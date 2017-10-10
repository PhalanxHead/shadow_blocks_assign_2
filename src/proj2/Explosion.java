/**
 * @author Luke Hedt - 832153 
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

public class Explosion extends GameObj {

	private static double LIFE = 0.4;
	
	private Timer timer;
	
	public Explosion(String image_src, int tileX, int tileY) {
		super("explosion", tileX, tileY);
		this.timer = new Timer(LIFE, 5);
	}
	
	public void update(int delta) {
		// Unimplemented
	}

}
