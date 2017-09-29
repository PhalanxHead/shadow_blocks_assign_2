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
import org.newdawn.slick.Graphics;

public abstract class GameObj {
	// Used to decide what direction an object is moving
	// Look up enums to find a more elegant solution!
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;
	
	private Image image = null;
	public Position pos;
	
	public GameObj(String image_src, int x, int y) {
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.pos = new Position(x,y);
	}
	
	public void update(Input input, int delta) {
		
	}
	
	public void render(Graphics g) {
		image.drawCentered(pos.getX(), pos.getY());
	}
}
