/**
 * @author 	Luke Hedt
 * StuID:	832153
 */

package proj2;

public class Unit extends GameObj {

	public Unit(String image_src, int x, int y) {
		super(image_src, x, y);
		// TODO Auto-generated constructor stub
	}
	
	// NEED TO REWORK!!!!
	public void moveToDest(int dir) {
		float speed = 32;
		// Translate the direction to an x and y displacement
		float delta_x = 0,
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
