/**
 * @author 	Luke Hedt
 * StuID:	832153
 */

package proj2;

public class Mage extends Moveable {

	public Mage(int x, int y) {
		super("mage", x, y);
	}
	
	public boolean moveToDest(Dirs dir) {
		return true;
	}

}
