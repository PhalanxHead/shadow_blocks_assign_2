/**
 * @author 	Luke Hedt
 * StuID:	832153
 */

package proj2;

public class Ice extends Pushable {

	public static final double WAIT = 0.25;
	
	Dirs dir;
	private int lastTileX;
	private int lastTileY;
	private Timer timer;
	
	public Ice(int x, int y) {
		super("ice", x, y);
		this.timer = new Timer(WAIT, 5);
	}
	
	public void addToHistory() {
		// Unimplemented
	}
	
	public boolean active() {
		// Unimplemented
		return true;
	}
	
	public void undo() {
		// Unimplemented
	}
	
	public void push(Dirs dir) {
		this.dir = dir;
		
		// Unimplemented
	}
	
	public void update(int delta) {
		// Unimplemented
	}
}
