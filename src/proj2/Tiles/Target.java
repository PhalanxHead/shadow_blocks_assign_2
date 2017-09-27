/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry
 */

package proj2.Tiles;

import proj2.Loader;
import proj2.Tile;

public class Target extends Tile {
	public Target(float x, float y) {
		super(Loader.OBJ_RES + "Target.png", x, y);
	}
}
