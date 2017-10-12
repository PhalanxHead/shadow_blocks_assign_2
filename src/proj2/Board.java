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
	private ArrayList<GameObj> targets;
	private int coveredTargets = 0;
	private int initNumMoves = 0;
	private int curNumMoves;
	private int numBoard;
	//private boolean playerMoved;
	
	/**
	 * Board Constructor.
	 * @param boardNum : int. The current board number.
	 */
	public Board(int boardNum, int numMoves) {
		gameObjs = Loader.loadGameObjs(Loader.LVL_RES + boardNum + ".lvl");
		this.numBoard = boardNum;
		this.targets = getAllGameObjsOfType("Target", gameObjs);
		this.setInitNumMoves(numMoves);
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
	
	/**
	 * Delete a GameObj from the board.
	 * @param gameObj : GameObj. The object to be destroyed.
	 * @return True if removal successful.
	 */
	public static boolean destroyGameObj(GameObj gameObj) {
		for(GameObj obj : gameObjs) {
			if(obj.equals(gameObj)) {
				gameObjs.remove(obj);
				return true;
			}
		}
		// Backup if something fails/object not found.
		return false;
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
	private void undoMoveables() {
		for(GameObj obj : getAllGameObjsOfType("Moveable", Board.gameObjs)) {
			obj.undo();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<GameObj> getAllGameObjs() {
		return (ArrayList<GameObj>)gameObjs.clone();
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
	
	public static ArrayList<GameObj> getAllGameObjsOfType(String nameTag, ArrayList<GameObj> gameObjs) {
		ArrayList<GameObj> ofType = new ArrayList<>(2);
		for(GameObj obj : gameObjs) {
			if(obj.getNameTags().contains(nameTag)) {
				ofType.add(obj);
			}
		}
		
		// Return null on failure
		if(ofType.isEmpty()) {
			return null;
		}
		return ofType;
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
		GameObj playerObj = getGameObjOfType("Player", gameObjs);
		while(playerObj.getHistStack().getStackSize() >= 0) {
			undoMoveables();
		}
	}
	
	/**
	 * @return The number of moves made so far.
	 */
	public int getInitNumMoves() {
		return this.initNumMoves;
	}
	
	public void setInitNumMoves(int newNumMoves) {
		if(newNumMoves > 0) {
			this.initNumMoves = newNumMoves;
		}
	}
	
	public int getCurNumMoves() {
		return this.curNumMoves;
	}
	
	public void setCurNumMoves(int newNumMoves) {
		if(newNumMoves >= 0) {
			this.curNumMoves = newNumMoves;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<GameObj> getGameObjs() {
		return (ArrayList<GameObj>)gameObjs.clone();
	}
	
	/**
	 * @return The number of the current board.
	 */
	public int getNumBoard() {
		return this.numBoard;
	}
	
	public boolean isLevelComplete() {
		if(this.targets != null) {
			return this.targets.size() - this.coveredTargets == 0;
		}
		// If there are no targets, return an arbitrary non-zero number.
		return false;
	}
	
	/**
	 * Sets all member variables to null, marking as
	 * "disposable" to the garbage collector.
	 */
	public void destroy() {
		this.targets = null;
		this.coveredTargets = 0;
		this.initNumMoves = 0;
		this.numBoard = 0;
	}
	
	/**
	 * Works out if a single block is covered.
	 * @param tileX : int. Tile X Coord to check.
	 * @param tileY : int. Tile Y Coord to check.
	 * @return If there's a "Pushable" Block type in that place.
	 */
	public boolean isCovered(int tileX, int tileY) {
		if(getGameObjOfType("Pushable", tileX, tileY) != null) {
			return true;
		}
		return false;
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
		
		// Calculate the number of targets covered in this frame.
		// (There's probably a better place to put this, don't need to
		// Do it once a frame)
		if(this.targets != null) {
			this.coveredTargets = 0;
			for(GameObj target : this.targets) {
				if(isCovered(target.getTileX(), target.getTileY())) {
					this.coveredTargets++;
				}
			}
		}
		
		if(input.isKeyPressed(Input.KEY_Z)) {
			undoMoveables();
		}
		
		if(input.isKeyPressed(Input.KEY_R)) {
			resetBoard();
		}
		
		this.setCurNumMoves(this.getInitNumMoves() + getGameObjOfType("Player", gameObjs).getHistStack().getStackSize());
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
		
		g.drawString(String.format("Moves: %d", this.getCurNumMoves()),20.0f,20.0f);
		
	}
}
