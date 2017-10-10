/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry
 */

package proj2;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

/**
 * The main abstract object for every visible item on screen.
 * Once was Sprite, though I grew up with a different def of Sprite
 * (And also I changed it at some point and just cbf changing everything back
 * Sorry Bout it)
 * @author Luke Hedt - 832153
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
		this.tileX = tileX;
		this.tileY = tileY;
	}
	
	public void update(Input input, int delta) {
		
	}
	
	public void render(Graphics g) {
		image.drawCentered(Loader.tilesToPix(tileX, true), Loader.tilesToPix(tileY, false));
	}
	
	public ArrayList<String> getNameTags() {
		return (ArrayList<String>)this.nameTag.clone();
	}
	
	public void addNameTag(String nameTag) {
		this.nameTag.add(nameTag);
	}
	
	public void removeNameTag(String nameTag) {
		for(String tag : getNameTags()) {
			if(tag.equals(nameTag)) {
				this.nameTag.remove(this.nameTag.indexOf(nameTag));
			}
		}	
	}
	
	public boolean compareNameTag(String compare) {
		for(String tag : getNameTags()) {
			if(tag.equals(compare)) {
				return true;
			}
		}
		return false;
	}
}
