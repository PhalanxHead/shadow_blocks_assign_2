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
	
	private static ArrayList<GameObj> gameObjs;
	private int numTargets;
	private int coveredTargets = 0;
	private int numMoves = 0;
	private int numBoard;
	//private boolean playerMoved;
	
	/**
	 * Board Constructor.
	 * @param boardNum : int. The current board number.
	 */
	public Board(int boardNum) {
		gameObjs = Loader.loadGameObjs(Loader.LVL_RES + boardNum + ".lvl");
		this.numBoard = boardNum;
		this.numTargets = getNumTargets(gameObjs);

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
	public static boolean isBlocked(int tileX, int tileY) {
		// Do bounds checking!
		if(Loader.inBounds(tileX, tileY) && getGameObjOfType("Blocking", tileX, tileY) == null) {
			return false;
		}
		// Default to blocked
		return true;
	}
	
	/**
	 * Checks for an object that has nameTag specified
	 * @param tileX : int. tileX to check.
	 * @param tileY : int. tileY to check.
	 * @param nameTag : String. Search Key.
	 * @return True if object is present.
	 */
	public static boolean isNameTag(int tileX, int tileY, String nameTag) {
		if(getGameObjOfType(nameTag, tileX, tileY) != null) {
			return true;
		}
		// Default to not present
		return false;
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
	
	/**
	 * Gets all of the objects with the nameTag Specified. NOTE: Only gets the first matching obj.
	 * @param nameTag : String. Search Key
	 * @param gameObjs : ArrayList< GameObj>. The array of gameObjs to search through. 
	 * @return The first object with a matching nameTag.
	 */
	private static GameObj getGameObjOfType(String nameTag, ArrayList<GameObj> gameObjs) {
		// Scan through array and get the nameTag arrays, return the first matching obj.
		for(GameObj obj : gameObjs) {
			if(obj.getNameTags().contains(nameTag)) {
				return obj;
			}
		}
		// Return null on failure.
		return null;
	}
	
	/**
	 * Gets the first object with the specified tag at (tileX, tileY).
	 * @param nameTag : String. The search key.
	 * @param tileX : int. X Tile Coord.
	 * @param tileY : int. Y Tile Coord.
	 * @return First object with the tag that is here.
	 */
	public static GameObj getGameObjOfType(String nameTag, int tileX, int tileY) {
		ArrayList<GameObj> objsHere = getGameObjsHere(tileX, tileY);
		return getGameObjOfType(nameTag, objsHere);
	}
	
	/**
	 * Gets all of the game objects in one position.
	 * @param tileX : int. X tile pos.
	 * @param tileY : int. Y tile pos.
	 * @return Array of the Objects here.
	 */
	private static ArrayList<GameObj> getGameObjsHere(int tileX, int tileY) {
		ArrayList<GameObj> objsHere = new ArrayList<>(2);
		
		for(GameObj obj : gameObjs) {
			if(obj.getTileX() == tileX && obj.getTileY() == tileY) {
				objsHere.add(obj);
			}
		}
		if(!objsHere.isEmpty()) {
			return objsHere;
		}
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
