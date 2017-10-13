/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Design for 2A sample by Eleanor.
 */

package proj2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Loads objects from a .lvl file.
 * @author Luke Hedt || Based on Design by Eleanor McMurtry.
 */
public class Loader {
	
	/* Constants */
	public static final String LVL_RES = "res/levels/";
	public static final String OBJ_RES = "res/";
	public static final String STD_SUFF = ".png";

	/* Member Variables */
	private static int[] boardSize = new int[2];
	private static int[] boardOffset = new int[2];
	
	
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
				int tileX, tileY;
				
				/* Split the line into parts */
				parts = line.split(",");
				name = parts[0];
				tileX = Integer.parseInt(parts[1]);
				tileY = Integer.parseInt(parts[2]);

				/* Create the sprite */
				list.add(newGameObj(name, tileX, tileY));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
	 * Checks if the object is in the bounds of the board.
	 * @param tileX : int. TileX value to be checked.
	 * @param tileY : int. TileY value to be checked.
	 * @return True is the position is within the board's bounds.
	 */
	public static boolean inBounds(int tileX, int tileY) {
		if(tileX >= 0 && tileX < getBoardSize()[Board.IND_X]
				&& tileY >= 0 && tileY < getBoardSize()[Board.IND_Y]) {
			return true;
		}
		return false;
	}
	
	/**
	 * Converts a pixel position to a tile value.
	 * @param pixels : int[]. The pixels from (0,0) to convert.
	 * @return The Position object equivalent.
	 */
	public static int[] pixToTiles(float pixX, float pixY) {
		int[] position = new int[2]; 
		pixX -= getBoardOffset()[Board.IND_X];
		pixX /= App.TILE_SIZE;
		pixY -= getBoardOffset()[Board.IND_Y];
		pixY /= App.TILE_SIZE;
		
		// Rounding is important here
		position[Board.IND_X] = Math.round(pixX);
		position[Board.IND_Y] = Math.round(pixY);
		return position;
	}
	
	/**
	 * Converts a tile value to a pixel board value.
	 * @param tilePos : int. The Tile Position.
	 * @param Horz : boolean. True if Horizontal.
	 * @return Pixel value of top or left of the tile position.
	 */
	public static int tilesToPix(int tilePos, boolean Horz) {
		
		tilePos *= App.TILE_SIZE;
		
		if(Horz) {
			return tilePos += getBoardOffset()[Board.IND_X];
		} else {
			return tilePos += getBoardOffset()[Board.IND_Y];
		}
	}
	
	/**
	 * Converts a tile position to a pixel board value.
	 * @param tileX : X tile coord.
	 * @param tileY : Y tile coord.
	 * @return Array of int pixel position of top-left of tile.
	 */
	public static int[] tilesToPix(int tileX, int tileY) {
		int pixPos[] = new int[2];
		pixPos[Board.IND_X] = tilesToPix(tileX, true);
		pixPos[Board.IND_Y] = tilesToPix(tileY, false);
		return pixPos;
	}
	
	private static GameObj newGameObj(String name, int tileX, int tileY) {
		switch (name) {
			case "wall":
				return new Wall(tileX, tileY);
			case "floor":
				return new Floor(tileX, tileY);
			case "stone":
				return new Stone(tileX, tileY);
			case "target":
				return new Target(tileX, tileY);
			case "player":
				return new Player(tileX, tileY);
			case "cracked":
				return new CrackedWall(tileX, tileY);
			case "door":	
				return new Door(tileX, tileY);
			case "ice":
				return new Ice(tileX, tileY);
			case "mage":
				return new Mage(tileX, tileY);
			case "rogue":
				return new Rogue(tileX, tileY);
			case "skeleton":
				return new Skellington(tileX, tileY);
			case "switch":
				return new Switch(tileX, tileY);
			case "tnt":
				return new TNT(tileX, tileY);
		}
		return null;
	}

}
