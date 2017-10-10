package proj2;

public abstract class Moveable extends GameObj {

	public Moveable(String image_src, int tileX, int tileY) {
		super(image_src, tileX, tileY);
	}

	public boolean hasHistory() {
		// Unimplemented
		return false;
	}
	
	public void addToHistory() {
		// Unimplemented
	}
	
	public void undo() {
		// Unimplemented
	}
	
	public boolean moveToDest(Dirs dir) {
		// Unimplemented
		return true;
	}
	
	public void onMove(Dirs dir, int testX, int testY) {
		// Unimplemented
	}
}
