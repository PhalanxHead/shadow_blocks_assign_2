/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry
 */

package Proj2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {
	
	/* Constants */
	public static final String LVL_RES = "res/levels/";
	public static final String OBJ_RES = "res/";
	
	/* Member Variables */
	private static String[][] types;
	
	private static int[] boardSize = new int[2];
	private static int[] boardOffset = new int[2];
	
	/**
	 * Create the appropriate sprite given a name and location.
	 * @param name	the name of the sprite
	 * @param x		the x position
	 * @param y		the y position
	 * @return		the sprite object
	 */
	private static GameObj createGameObj(String name, float x, float y) {
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
		}
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
		x -= boardOffset[Board.IND_X];
		x /= App.TILE_SIZE;
		y -= boardOffset[Board.IND_Y];
		y /= App.TILE_SIZE;
		
		// Rounding is important here
		x = Math.round(x);
		y = Math.round(y);
		
		// Do bounds checking!
		if (x >= 0 && x < boardSize[Board.IND_X] && y >= 0 && y < boardSize[Board.IND_Y]) {
			return types[(int)x][(int)y].equals("wall");
		}
		// Default to blocked
		return true;
	}
		
	/**
	 * Loads the GameObjs from a given file.
	 * @param filename : String. Board layout file.
	 * @return list : ArrayList<GameObj>. Array List of the objects on the board.
	 */
	public static ArrayList<GameObj> loadGameObjs(String filename) {
		ArrayList<GameObj> list = new ArrayList<>();
		
		// Open the file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			
			// Find the world size
			line = reader.readLine();
			String[] parts = line.split(",");
			boardSize[Board.IND_X] = Integer.parseInt(parts[0]);
			boardSize[Board.IND_Y] = Integer.parseInt(parts[1]);
			
			// Create the array of object types
			types = new String[boardSize[Board.IND_X]][boardSize[Board.IND_Y]];
			
			// Calculate the top left of the tiles so that the level is
			// centred
			boardOffset[Board.IND_X] = (App.SCREEN_WIDTH - boardSize[Board.IND_X] * App.TILE_SIZE) / 2;
			boardOffset[Board.IND_Y] = (App.SCREEN_HEIGHT - boardSize[Board.IND_Y] * App.TILE_SIZE) / 2;

			// Loop over every line of the file
			while ((line = reader.readLine()) != null) {
				String name;
				float x, y;
				
				// Split the line into parts
				parts = line.split(",");
				name = parts[0];
				x = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
				types[(int)x][(int)y] = name;
				
				// Adjust for the grid
				x = boardOffset[Board.IND_X] + x * App.TILE_SIZE;
				y = boardOffset[Board.IND_Y] + y * App.TILE_SIZE;
				
				// Create the sprite
				list.add(createGameObj(name, x, y));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
