/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry
 */

package proj2;

import org.newdawn.slick.Input;

public class Player extends Unit {
	
	public Player(float x, float y) {
		super(Loader.OBJ_RES + "player_left.png", x, y);
	}

	@Override
	public void update(Input input, int delta) {
		int dir = DIR_NONE;

		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dir = DIR_LEFT;
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dir = DIR_RIGHT;
		}
		else if (input.isKeyPressed(Input.KEY_UP)) {
			dir = DIR_UP;
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			dir = DIR_DOWN;
		}
		
		// Move to our destination
		moveToDest(dir);
	}
}