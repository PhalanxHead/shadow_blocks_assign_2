/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry
 */

package proj2;

import org.newdawn.slick.Input;

public class Player extends Moveable {
	
	public Player(int x, int y) {
		super(Loader.OBJ_RES + "player_left.png", x, y);
	}

	@Override
	public void update(Input input, int delta) {
		Dirs dir = Dirs.NONE;
		
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dir = Dirs.LEFT;
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dir = Dirs.RIGHT;
		}
		else if (input.isKeyPressed(Input.KEY_UP)) {
			dir = Dirs.UP;
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			dir = Dirs.DOWN;
		}
		
		// Move to our destination
		moveToDest(dir);
	}
	
	@Override
	public boolean moveToDest(Dirs dir) {
		int speed = 32;
		// Translate the direction to an x and y displacement
		int delta_x = 0,
			  delta_y = 0;
		switch (dir) {
			case LEFT:
				delta_x = -speed;
				break;
			case RIGHT:
				delta_x = speed;
				break;
			case UP:
				delta_y = -speed;
				break;
			case DOWN:
				delta_y = speed;
				break;
			default:
				break;
		}
		/*
		// Make sure the position isn't occupied!
		if (!Loader.isBlocked(pos.getX() + delta_x, pos.getY() + delta_y)) {
			pos.moveX(delta_x);
			pos.moveY(delta_y);
		}
		 */
		return true;
	}
}
