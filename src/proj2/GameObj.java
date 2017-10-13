/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import java.util.ArrayList;

/**
 * The main abstract object for every visible item on screen.
 * Once was Sprite, though I grew up with a different def of Sprite
 * (And also I changed it at some point and just cbf changing everything back,
 * Sorry 'Bout it)
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry
 */
public abstract class GameObj {
	
	private Image image = null;
	private ArrayList<String> nameTag;
	private int tileX;
	private int tileY;
	
	public GameObj(String image_src, int tileX, int tileY) {
		try {
			image = new Image(Loader.OBJ_RES + image_src + Loader.STD_SUFF);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.setTileX(tileX);
		this.setTileY(tileY);
		nameTag = new ArrayList<>(1);
	}
	
	/**
	 * Compares a GameObj to another.
	 * Code from https://www.sitepoint.com/implement-javas-equals-method-correctly/
	 */
	@Override
	public boolean equals(Object o) {
	    // self check
	    if (this == o)
	        return true;
	    // null check
	    if (o == null)
	        return false;
	    // type check and cast
	    if (getClass() != o.getClass())
	        return false;
	    GameObj obj = (GameObj) o;
	    // field comparison
	    return ((this.getTileX() == obj.getTileX()) && (this.getTileY() == obj.getTileY())
	    		&& this.getNameTags().equals(obj.getNameTags()));
	}
	
	/**
	 * Returns a copy of the ArrayList of the object's NameTags.
	 * @return nameTags.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getNameTags() {
		return (ArrayList<String>)this.nameTag.clone();
	}
	
	/**
	 * Add a nameTag to an Object if it doens't already have it.
	 * @param nameTag : String. The nameTag to add.
	 */
	public void addNameTag(String nameTag) {
		if(!getNameTags().contains(nameTag)) {
				this.nameTag.add(nameTag);
		}
	}
	
	/**
	 * Deletes the nameTag if it exists.
	 * @param nameTag : String. The nameTag to delete.
	 */
	public void removeNameTag(String nameTag) {
		for(String tag : getNameTags()) {
			if(tag.equals(nameTag)) {
				this.nameTag.remove(this.nameTag.indexOf(nameTag));
			}
		}	
	}
	
	/**
	 * Attempt to push the object.
	 * @param dir : Dirs. The direction to push in.
	 * @return True on Success.
	 */
	public boolean push(Dirs dir) {
		// Only implemented for Pushable
		return false;
	}
	
	/**
	 * Gets the object's History Stack.
	 * @return HistoryStack : The object's position history.
	 */
	public HistoryStack getHistStack() {
		// Only implemented for  moveable
		return null;
	}
	
	/**
	 * Undoes a move!
	 */
	public void undo() {
		// Only implemented for moveable
	}

	/**
	 * Updates any object attributes.
	 * @param input : Input. Slick user input.
	 * @param delta : int. Time since last update (ms).
	 */
	public void update(Input input, int delta) {
		// Nothing
	}

	/**
	 * Renders the Object at it's given position.
	 * @param g : Graphics. Slick's Graphics Object.
	 */
	public void render(Graphics g) {
		image.drawCentered(Loader.tilesToPix(getTileX(), true), Loader.tilesToPix(getTileY(), false));
	}

	/**
	 * Gets this object's tileX value.
	 * @return int : TileX
	 */
	public int getTileX() {
		return tileX;
	}

	/**
	 * Sets this object's tileX value.
	 * @param tileX : int. The new tileX.
	 */
	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	/**
	 * Gets this object's tileY value.
	 * @return int : TileY
	 */
	public int getTileY() {
		return tileY;
	}

	/**
	 * Sets this object's tileY value.
	 * @param tileX : int. The new tileY.
	 */
	public void setTileY(int tileY) {
		this.tileY = tileY;
	}
}
