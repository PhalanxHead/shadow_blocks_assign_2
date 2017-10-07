/**
 * @author 	Luke Hedt
 * StuID:	832153
 */

package proj2;

public abstract class Block extends GameObj {

	public Block(String image_src, int x, int y) {
		super(image_src, x, y);
	}

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
		
		// Make sure the position isn't occupied!
		if (!Loader.isBlocked(pos.getX() + delta_x, pos.getY() + delta_y)) {
			pos.moveX(delta_x);
			pos.moveY(delta_y);
		}

		return true;
	}
	
}
