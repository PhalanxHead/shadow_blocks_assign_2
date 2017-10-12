/**
 * @author Luke Hedt - 832153 
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import org.newdawn.slick.Graphics;

public class Explosion extends GameObj {

	// Lifespan of the explosion in ms
	private static int LIFE = 400;
	private Timer timer;
	
	public Explosion(int tileX, int tileY) {
		super("explosion", tileX, tileY);
		this.addNameTag("Timeable");
		this.timer = new Timer(LIFE);
	}
	
	public void update(int delta) {
		if(this.timer != null) {
			timer.update(delta);
			
			if(timer.expired()) {
				Board.destroySpecialGameObj(this);
			}
		}
	}
}
