/**
 * @author 	Luke Hedt
 * StuID:	832153
 */

package proj2;

public abstract class Block extends GameObj {

	public Block(String image_src, int x, int y) {
		super(image_src, x, y);
	}

	public abstract void moveToDest(Dirs dir);
	
}
