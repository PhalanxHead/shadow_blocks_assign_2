/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

public class Switch extends GameObj {

	public Door door;
	
	public Switch(int x, int y) {
		super("switch", x, y);
	}
	
	public boolean getDoor() {
		
		try {
			this.door = (Door)Board.getGameObjOfType("Door", Board.getAllGameObjs());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void update(int delta) {
		if(Board.getGameObjOfType("Pushable", this.getTileX(), this.getTileY()) != null) {
			this.door.setClosed(false);
		} else {
			this.door.setClosed(true);
		}
	}

}
