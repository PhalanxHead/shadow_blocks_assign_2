/**
 * @author Luke Hedt - 832153 
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import org.newdawn.slick.Input;

public class Explosion extends GameObj {

	// Lifespan of the explosion in ms
	private static int LIFE = 400;
	private Timer timer;
	
	public Explosion(int tileX, int tileY) {
		super("explosion", tileX, tileY);
		this.timer = new Timer(LIFE);
	}
	
	@Override
	public void update(Input input, int delta) {
		timer.update(delta);
		
		if(timer.expired()) {
			this.destroy();
		}
	}
	
	public void destroy() {
		Board.destroySpecialGameObj();
	}
}
