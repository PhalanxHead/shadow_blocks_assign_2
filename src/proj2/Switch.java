/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import org.newdawn.slick.Input;

/**
 * Switch. Controls Door Objects.
 * @author lhedt
 *
 */
public class Switch extends GameObj {

	public Door door = null;
	
	public Switch(int x, int y) {
		super("switch", x, y);
	}
	
	/**
	 * Sets the door state based on if the switch is covered.
	 */
	@Override
	public void update(Input input, int delta) {
		if(this.door == null ) {
			this.door = getDoor();
		}
		
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
	
	/**
	 * Gets the door object from the main ArrayList.
	 * @return The Door.
	 */
	private Door getDoor() {
		return (Door)Board.getGameObjOfType("Door", Board.getAllGameObjs());
	}
}
