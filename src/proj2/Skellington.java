/**
 * @author 	Luke Hedt
 * StuID:	832153
 */

package proj2;

public class Skellington extends Moveable {

	public Skellington(int x, int y) {
		super("skull", x, y);
	}
	
	public boolean moveToDest(Dirs dir) {
		return true;
	}

}
