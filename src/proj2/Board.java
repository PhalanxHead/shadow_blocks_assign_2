/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurty.
 */

package proj2;

import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Board {
	/* Constants/Index Defs */
	public static final int IND_X = 0;
	public static final int IND_Y = 1;
	
	private ArrayList<GameObj> gameObjs;
	private int numTargets;
	private int coveredTargets = 0;
	private int numMoves = 0;
	private int numBoard;
	private boolean playerMoved;
	
	/**
	 * Board Constructor.
	 * @param boardNum : int. The current board number.
	 */
	public Board(int boardNum) {
		this.gameObjs = Loader.loadGameObjs(Loader.LVL_RES + boardNum + ".lvl");
		this.numBoard = boardNum;
		this.numTargets = getNumTargets(this.gameObjs);

	}
	
	/**
	 * Create the appropriate sprite given a name and location.
	 * @param name : String. The name of the sprite
	 * @param x : int. The x position
	 * @param y : int. The y position
	 * @return	The sprite object
	 */
	public GameObj createGameObj(GameObj gameobj) {
		// Unimplemented
		return null;
	}
	
	public boolean destroyGameObj(GameObj gameObj) {
		// Unimplemented
		return true;
	}
	
	/** 
	 * Converts a world coordinate to a tile coordinate,
	 * and returns if that location is a blocked tile
	 * @param x : float. Pixel X position in world.
	 * @param y : float. Pixel Y position in world.
	 * @return blocked : boolean. True if position is blocked.
	 */
	public static boolean isBlocked(float x, float y) {
		// Unimplemented
		int[] position;
		// Convert to Tile
		position = Loader.pixToTiles(x, y);
		
		// Do bounds checking!
		if (x >= 0 && x < Loader.getBoardSize()[Board.IND_X] 
					&& y >= 0 && y < Loader.getBoardSize()[Board.IND_Y]) {
			// return blockerTilePresent[position[Board.IND_X]][position[Board.IND_Y]];
		}
		// Default to blocked
		return true;
	}
	
	public void updateMoveableHistory() {
		// Unimplemented
	}
	
	
	/**
	 * Reverse a move!
	 */
	private void undoMovables() {
		// Unimplemented
	}
	
	public GameObj getGameObjOfType(String nameTag) {
		// Unimplemented
		return null;
	}
	
	public GameObj getGameObjOfType(String nameTag, int tileX, int tileY) {
		// Unimplemented
		return null;
	}
	
	/**
	 * Start the board again!
	 */
	public void resetBoard() {
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
	
	/**
	 * Updates all of the elements of the board.
	 * @param input : Input. The input device.
	 * @param delta : int. The time between new frames.
	 */
	public void update(Input input, int delta) {
		for (GameObj gameObj : gameObjs) {
			if (gameObj != null) {
				gameObj.update(input, delta);
			}
		}
		
		if(input.isKeyPressed(Input.KEY_Z)) {
			undoMovables();
		}
	}
	
	/**
	 * Renders the board once again
	 * @param g : Graphics. The graphics engine to use for rendering.
	 */
	public void render(Graphics g) {
		for (GameObj gameObj : gameObjs) {
			if (gameObj != null) {
				gameObj.render(g);
			}
		}
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
