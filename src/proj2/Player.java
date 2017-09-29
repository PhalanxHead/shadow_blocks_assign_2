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
	
	public Player(int x, int y) {
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
	
	public void moveToDest(int dir) {
		int speed = 32;
		// Translate the direction to an x and y displacement
		int delta_x = 0,
			  delta_y = 0;
		switch (dir) {
			case DIR_LEFT:
				delta_x = -speed;
				break;
			case DIR_RIGHT:
				delta_x = speed;
				break;
			case DIR_UP:
				delta_y = -speed;
				break;
			case DIR_DOWN:
				delta_y = speed;
				break;
		}
		
		// Make sure the position isn't occupied!
		if (!Loader.isBlocked(pos.getX() + delta_x, pos.getY() + delta_y)) {
			pos.moveX(delta_x);
			pos.moveY(delta_y);
		}
	}
}
