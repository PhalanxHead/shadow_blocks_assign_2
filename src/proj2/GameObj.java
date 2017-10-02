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
