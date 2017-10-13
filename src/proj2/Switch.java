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
		GameObj pushable = Board.getGameObjOfType("Pushable", this.getTileX(), this.getTileY()); 
		if(pushable != null) {
			this.door.setClosed(false);
		 
			if(pushable.getNameTags().contains("Ice")) {
				Ice ice = (Ice)pushable;
				ice.setIsActive(false);
				return;
			}
			
		} else {
			this.door.setClosed(true);
		}
	}

}
