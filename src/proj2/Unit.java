/**
 * @author 	Luke Hedt
 * StuID:	832153
 */

package proj2;

public abstract class Unit extends GameObj {

	public Unit(String image_src, int x, int y) {
		super(image_src, x, y);
		// TODO Auto-generated constructor stub
	}
	
	// NEED TO REWORK!!!!
	public abstract void moveToDest(int dir);

}
