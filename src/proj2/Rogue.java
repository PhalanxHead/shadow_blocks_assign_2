/**
 * @author 	Luke Hedt
 * StuID:	832153
 */

package proj2;

public class Rogue extends Moveable {

	public Rogue(int x, int y) {
		super("rogue", x, y);
	}
	
	public boolean moveToDest(Dirs dir) {
		return true;
	}

}
