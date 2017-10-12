/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import org.newdawn.slick.Input;

public class Player extends Moveable {
	
	public Player(int tileX, int tileY) {
		super("player_left", tileX, tileY);
		this.addNameTag("Player");
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
	
	public void onMove(Dirs dir, int curTileX, int curTileY) {
		for(GameObj obj : Board.getAllGameObjsOfType("Moveable", Board.getAllGameObjs())) {
			obj.getHistStack().pushToStack(obj.getTileX(), obj.getTileY());
		}
	}
}
