/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry
 */

package proj2;

public class Wall extends Tile {
	public Wall(float x, float y) {
		super(Loader.OBJ_RES + "wall.png", x, y);
	}
}
