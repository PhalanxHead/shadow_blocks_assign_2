/**
 * @author 	Luke Hedt
 * StuID:	832153
 */

package proj2;

public class Rogue extends Unit {

	public Rogue(int x, int y) {
		super(Loader.OBJ_RES + "rogue.png", x, y);
	}
	
	public boolean moveToDest(Dirs dir) {
		return true;
	}

}
