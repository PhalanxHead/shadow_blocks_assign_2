/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry
 */

package proj2;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Board {
	/* Constants/Index Defs */
	public static final int IND_X = 0;
	public static final int IND_Y = 1;
	
	/* Interactive Tile Types */
	public static final String[] blockingTileTypes = {"wall", "door", "cracked"};
	public static final String[] unitTypes = {"player", "rogue", "skeleton", "mage"};
	public static final String[] blockTypes = {"stone", "ice", "tnt"};
	public static final String[] specialTypes = {"switch"};
	
	
	private ArrayList<GameObj> gameObjs;
	private int numTargets;
	private int coveredTargets = 0;
	private int numMoves = 0;
	private int numBoard;
	
	public Board(int boardNum) {
		this.gameObjs = Loader.loadGameObjs(Loader.LVL_RES + boardNum + ".lvl");
		this.numBoard = boardNum;
		this.numTargets = getNumTargets(this.gameObjs);
	}
	
	public static Position pixToTiles(int pixels[]) {
		// Unimplemented
		return null;
	}
	
	public static int[] tilesToPix(Position pos) {
		// Unimplemented
		return null;
	}
	
	public void update(Input input, int delta) {
		for (GameObj gameObj : gameObjs) {
			if (gameObj != null) {
				gameObj.update(input, delta);
			}
		}
	}
	
	public void render(Graphics g) {
		for (GameObj gameObj : gameObjs) {
			if (gameObj != null) {
				gameObj.render(g);
			}
		}
	}
	
	public void reset() {
		// Unimplemented
	}
	
	/**
	 * @return Gets the number of targets left to cover in this board.
	 */
	public int getTargetDiff() {
		return this.numTargets - this.coveredTargets;
	}
	
	/**
	 * @return The number of moves made so far.
	 */
	public int getnumMoves() {
		return this.numMoves;
	}
	
	/**
	 * @return The number of the current board.
	 */
	public int getNumBoard() {
		return this.numBoard;
	}
	
	/**
	 * Sets all member variables to null, marking as
	 * "disposable" to the garbage collector.
	 */
	public void destroy() {
		this.gameObjs = null;
		this.numTargets = 0;
		this.coveredTargets = 0;
		this.numMoves = 0;
		this.numBoard = 0;
	}
	
	private void undo() {
		// Unimplemented
	}
	
	/**
	 * Returns the number of Targets on the board.
	 * @param gameObjs: ArrayList< GameObj>. The List of pieces on the board.
	 * @return The number of target pieces on the board.
	 */
	private int getNumTargets(ArrayList<GameObj> gameObjs) {
		int numTargets = 0;
		for(GameObj obj : gameObjs) {
			if(obj.getClass().equals(Target.class)) {
				numTargets++;
			}
		}
		return numTargets;
	}


	
}
