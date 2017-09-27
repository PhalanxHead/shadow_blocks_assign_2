/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry
 */

package Proj2;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Board {
	/* Constants/Index Defs */
	public static final int IND_X = 0;
	public static final int IND_Y = 1;
	
	
	private ArrayList<GameObj> gameObjs;
	private int numTargets = 0;
	private int coveredTargets = 0;
	private int numMoves = 0;
	private int numBoard;
	
	public Board() {
		gameObjs = Loader.loadGameObjs(Loader.LVL_RES + "0.lvl");
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
	
	private void undo() {
		// Unimplemented
	}
	
	private Board loadNextBoard(int boardNum) {
		// Unimplemented
		return null;
	}
}
