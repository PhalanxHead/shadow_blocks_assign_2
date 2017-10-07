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
	
	/**
	 * Moves the object and returns true if successful
	 * @param dir : Dirs. The direction to move in.
	 * @return True on successful move.
	 */
	public abstract boolean moveToDest(Dirs dir);

}
