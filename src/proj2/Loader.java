/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry
 */

package proj2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Loader {
	
	/* Constants */
	public static final String LVL_RES = "res/levels/";
	public static final String OBJ_RES = "res/";
	
	/* Interactive Tile Types */
	public static final String[] blockingTileTypes = {"wall", "door", "cracked"};
	public static final String[] unitTypes = {"player", "rogue", "skeleton", "mage"};
	public static final String[] blockTypes = {"stone", "ice", "tnt"};
	public static final String[] specialTypes = {"switch"};
	
	/* Member Variables */
	private static int[] boardSize = new int[2];
	private static int[] boardOffset = new int[2];
	
		/* Position Arrays for interactive tile types */
	private static boolean[][] unitPresent;
	private static boolean[][] blockerTilePresent;
	private static boolean[][] blockPresent;
	private static boolean[][] specialPresent;
	
	/**
	 * Loads the GameObjs from a given file.
	 * Also creates a series of tables to check against for interactions.
	 * @param filename : String. Board layout file.
	 * @return list : ArrayList< GameObj>. Array List of the objects on the board.
	 */
	public static ArrayList<GameObj> loadGameObjs(String filename) {
		ArrayList<GameObj> list = new ArrayList<>();
		
		/* Open the file */
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			
			/* Find the world size */
			line = reader.readLine();
			String[] parts = line.split(",");
			boardSize[Board.IND_X] = Integer.parseInt(parts[0]);
			boardSize[Board.IND_Y] = Integer.parseInt(parts[1]);
			
			/*  Calculate the top left of the tiles so that the level is
		 		centred */
			boardOffset[Board.IND_X] = (App.SCREEN_WIDTH - boardSize[Board.IND_X] * App.TILE_SIZE) / 2;
			boardOffset[Board.IND_Y] = (App.SCREEN_HEIGHT - boardSize[Board.IND_Y] * App.TILE_SIZE) / 2;

			/* Loop over every line of the file */
			while ((line = reader.readLine()) != null) {
				String name;
				int x, y;
				
				/* Split the line into parts */
				parts = line.split(",");
				name = parts[0];
				x = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
				
				/* Adjust for the grid */
				x = boardOffset[Board.IND_X] + x * App.TILE_SIZE;
				y = boardOffset[Board.IND_Y] + y * App.TILE_SIZE;
				
				/* Create the sprite */
				list.add(createGameObj(name, x, y));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		blockerTilePresent = createBlockerTileTable(list);
		blockPresent = createInitBlockTable(list);
		unitPresent = createInitUnitTable(list);
		specialPresent = createSpecialTable(list);
		
		return list;
	}
	
	/**
	 * @return The array containing the size of the board.
	 */
	public static int[] getBoardSize() {
		return boardSize;
	}
	
	/**
	 * @return The array containing the Offset of the board.
	 */
	public static int[] getBoardOffset() {
		return boardOffset;
	}
	
	/**
	 * Converts a pixel position to a tile value.
	 * @param pixels : int[]. The pixels from (0,0) to convert.
	 * @return The Position object equivalent.
	 */
	public static int[] pixToTiles(float x, float y) {
		int[] position = new int[2]; 
		x -= getBoardOffset()[Board.IND_X];
		x /= App.TILE_SIZE;
		y -= getBoardOffset()[Board.IND_Y];
		y /= App.TILE_SIZE;
		
		// Rounding is important here
		position[Board.IND_X] = Math.round(x);
		position[Board.IND_Y] = Math.round(y);
		return position;
	}
	
	/**
	 * Converts a tile position to the pixel equivalent from
	 * (0,0) in the top left.
	 * @param pos : Position. The tile position object.
	 * @return Pixel number array of ints.
	 */
	public static int[] tilesToPix(Position pos) {
		// Unimplemented
		return null;
	}
	
	/** 
	 * Converts a world coordinate to a tile coordinate,
	 * and returns if that location is a blocked tile
	 * @param x : float. Pixel X position in world.
	 * @param y : float. Pixel Y position in world.
	 * @return blocked : boolean. True if position is blocked.
	 */
	public static boolean isBlocked(float x, float y) {
		int[] position;
		/* Convert to Tile */
		position = Loader.pixToTiles(x, y);
		
		// Do bounds checking!
		if (x >= 0 && x < Loader.getBoardSize()[Board.IND_X] 
					&& y >= 0 && y < Loader.getBoardSize()[Board.IND_Y]) {
			return blockerTilePresent[position[Board.IND_X]][position[Board.IND_Y]];
		}
		// Default to blocked
		return true;
	}
	
	
	/**
	 * Creates the table of Blocking Tiles for this board.
	 * @return The array of blocked positions.
	 */
	private static boolean[][] createBlockerTileTable(ArrayList<GameObj> gameObjs) {
		blockerTilePresent = new boolean[Loader.getBoardSize()[Board.IND_X]][Loader.getBoardSize()[Board.IND_Y]];
		
		/* Iterate through the object list and find the blocking types */
		for(GameObj obj : gameObjs) {
			blockerTilePresent[obj.pos.getX()][obj.pos.getY()] = false;
			
			if(Arrays.asList(blockingTileTypes).contains(obj.getName())) {
				blockerTilePresent[obj.pos.getX()][obj.pos.getY()] = true;
			}
		}
		
		return blockerTilePresent;
	}
	
	/**
	 * Creates the table of Blocks for this board.
	 * @return The array of blocks positions.
	 */
	private static boolean[][] createInitBlockTable(ArrayList<GameObj> gameObjs) {
		blockPresent = new boolean[Loader.getBoardSize()[Board.IND_X]][Loader.getBoardSize()[Board.IND_Y]];
		
		/* Iterate through the object list and find the blocking types */
		for(GameObj obj : gameObjs) {
			blockPresent[obj.pos.getX()][obj.pos.getY()] = false;
			
			if(Arrays.asList(blockTypes).contains(obj.getName())) {
				blockPresent[obj.pos.getX()][obj.pos.getY()] = true;
			}
		}
		
		return blockPresent;
	}
	
	/**
	 * Creates the table of Units for this board.
	 * @return The array of Unit positions.
	 */
	private static boolean[][] createInitUnitTable(ArrayList<GameObj> gameObjs) {
		unitPresent = new boolean[Loader.getBoardSize()[Board.IND_X]][Loader.getBoardSize()[Board.IND_Y]];
		
		/* Iterate through the object list and find the blocking types */
		for(GameObj obj : gameObjs) {
			unitPresent[obj.pos.getX()][obj.pos.getY()] = false;
			
			if(Arrays.asList(unitTypes).contains(obj.getName())) {
				unitPresent[obj.pos.getX()][obj.pos.getY()] = true;
			}
		}
		
		return unitPresent;
	}
	
	/**
	 * Creates the table of Units for this board.
	 * @return The array of Unit positions.
	 */
	private static boolean[][] createSpecialTable(ArrayList<GameObj> gameObjs) {
		specialPresent = new boolean[Loader.getBoardSize()[Board.IND_X]][Loader.getBoardSize()[Board.IND_Y]];
		
		/* Iterate through the object list and find the blocking types */
		for(GameObj obj : gameObjs) {
			specialPresent[obj.pos.getX()][obj.pos.getY()] = false;
			
			if(Arrays.asList(specialTypes).contains(obj.getName())) {
				specialPresent[obj.pos.getX()][obj.pos.getY()] = true;
			}
		}
		
		return specialPresent;
	}
	
	/**
	 * Create the appropriate sprite given a name and location.
	 * @param name : String. The name of the sprite
	 * @param x : int. The x position
	 * @param y : int. The y position
	 * @return	The sprite object
	 */
	private static GameObj createGameObj(String name, int x, int y) {
		switch (name) {
			case "wall":
				return new Wall(x, y);
			case "floor":
				return new Floor(x, y);
			case "stone":
				return new Stone(x, y);
			case "target":
				return new Target(x, y);
			case "player":
				return new Player(x, y);
			case "cracked":
				return new CrackedWall(x, y);
			case "door":
				return new Door(x, y);
			case "ice":
				return new Ice(x, y);
			case "mage":
				return new Mage(x, y);
			case "rogue":
				return new Rogue(x, y);
			case "skeleton":
				return new Skellington(x, y);
			case "switch":
				return new Switch(x, y);
			case "tnt":
				return new TNT(x, y);
		}
		return null;
	}
}
