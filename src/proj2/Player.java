/**
 * @author     Luke Hedt
 * StuID:    832153
 *
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import org.newdawn.slick.Input;

/**
 * Main User-Controllable Object in the game. Player.
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry.
 */
public class Player extends Moveable {

    public Player(int tileX, int tileY) {
        super("player_left", tileX, tileY);
        this.addNameTag("Player");
    }

    /**
     * Translates input to a direction and tries to move the player.
     */
    @Override
    public void update(Input input, int delta) {
        Dirs dir = Dirs.NONE;

        if (input.isKeyPressed(Input.KEY_LEFT)) {
            dir = Dirs.LEFT;
        }
        else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            dir = Dirs.RIGHT;
        }
        else if (input.isKeyPressed(Input.KEY_UP)) {
            dir = Dirs.UP;
        }
        else if (input.isKeyPressed(Input.KEY_DOWN)) {
            dir = Dirs.DOWN;
        }

        // Move to our destination
        moveToDest(dir);
    }

    /**
     * Execute Actions triggered by a player move.
     */
    @Override
    public void onMove(Dirs dir, int curTileX, int curTileY) {
        // Add positions to historyStacks
        for(GameObj obj : Board.getAllGameObjsOfType("Moveable", Board.getAllGameObjs())) {
            // Check for ice special cases
            if(!obj.getNameTags().contains("Ice")) {
                obj.getHistStack().pushToStack(obj.getTileX(), obj.getTileY());
            } else {
                Ice ice = (Ice)obj;
                ice.getHistStack().pushToStack(ice.getLastTileX(), ice.getLastTileY());
            }
        }
        // Move the rogue if it exists
        Rogue rogue = (Rogue)Board.getGameObjOfType("Rogue", Board.getAllGameObjs());
        if(rogue != null) {
            rogue.moveToDest(rogue.getDir());
        }

        // Move the Mage if it exists
        Mage mage = (Mage)Board.getGameObjOfType("Mage", Board.getAllGameObjs());
        if(mage != null) {
            mage.moveToDest(Dirs.NONE);
        }
    }
}
