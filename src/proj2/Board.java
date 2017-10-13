/**
 * @author     Luke Hedt
 * StuID:    832153
 *
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurty.
 */

package proj2;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * The Board, formally known as "World." Handles the objects in the game.
 * @author Luke Hedt - 832153 || Based on design by Eleanor McMurtry
 *
 */
public class Board {
    /* Constants/Index Defs */
    public static final int IND_X = 0;
    public static final int IND_Y = 1;

    private static ArrayList<GameObj> gameObjs;
    private ArrayList<GameObj> targets;
    private static GameObj special;
    private int coveredTargets = 0;
    private int initNumMoves = 0;
    private int curNumMoves;
    private int numBoard;

    /**
     * Board Constructor.
     * @param boardNum : int. The current board number.
     * @param numMoves : int. The number of moves made at the start of the board.
     */
    public Board(int boardNum, int numMoves) {
        gameObjs = Loader.loadGameObjs(Loader.LVL_RES + boardNum + ".lvl");
        special = null;
        this.numBoard = boardNum;
        this.targets = getAllGameObjsOfType("Target", gameObjs);
        this.setInitNumMoves(numMoves);
    }

    /**
     * Creates a "Special" GameObj, usually for an Explosion.
     * @param gameObj : GameObj. The object to assign.
     * @return True on Success.
     */
    public static boolean createSpecialGameObj(GameObj gameObj) {
        if(special == null) {
            special = gameObj;
            return true;
        }
        return false;
    }

    /**
     * Deletes the "Special" GameObj
     */
    public static void destroySpecialGameObj() {
        special = null;
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
     * Finds if a tile has a Blocking Object in it.
     * @param x : int. Tile X position in world.
     * @param y : int. Tile Y position in world.
     * @return blocked : boolean. True if position is blocked.
     */
    public static boolean isBlocked(int tileX, int tileY) {
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
     * Calculates if all the targets have been covered and the level is complete.
     * @return True if the level is complete.
     */
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
     * Resets the board to it's initial state.
     */
    public void resetBoard() {
        App.resetBoard(this);
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

        if(special != null) {
            special.update(input, delta);
        }

        // Undo a move if Z is pressed.
        if(input.isKeyPressed(Input.KEY_Z)) {
            undoMoveables();
        }

        // Update the move count or reset the board on player collision with Enemy.
        GameObj player = getGameObjOfType("Player", gameObjs);

        if(player != null) {
            if(Board.getGameObjOfType("Enemy", player.getTileX(), player.getTileY()) != null) {
                this.resetBoard();
                return;
            }
            this.setCurNumMoves(this.getInitNumMoves() +
                    getGameObjOfType("Player", gameObjs).getHistStack().getStackSize());

        // Case with no player object
        } else {
            this.setCurNumMoves(this.getInitNumMoves());
        }
    }

    /**
     * Renders the board once again
     * @param g : Graphics. The graphics engine to use for rendering.
     */
    public void render(Graphics g) {
        // Render main Game Objects
        for (GameObj gameObj : gameObjs) {
            if (gameObj != null) {
                gameObj.render(g);
            }
        }

        // Render special
        if(special != null) {
            special.render(g);
        }
        // Print the number of moves on the screen.
        g.drawString(String.format("Moves: %d", this.getCurNumMoves()),20.0f,20.0f);

    }

    /**
     * Gets a clone of the Main GameObj ArrayList.
     * @return gameObj ArrayList.
     */
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
    public static GameObj getGameObjOfType(String nameTag, ArrayList<GameObj> gameObjs) {
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
        if(!objsHere.isEmpty()) {
            return getGameObjOfType(nameTag, objsHere);
        }
        return null;
    }

    public static ArrayList<GameObj> getAllGameObjsOfType(String nameTag,
            ArrayList<GameObj> gameObjs) {
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
     * @return The number of moves made so far.
     */
    public int getInitNumMoves() {
        return this.initNumMoves;
    }

    /**
     * Sets the Initial number of moves for the board.
     * @param newNumMoves : int. New initial number of moves.
     */
    public void setInitNumMoves(int newNumMoves) {
        if(newNumMoves > 0) {
            this.initNumMoves = newNumMoves;
        }
    }

    /**
     * Gets the total number of moves made.
     * @return : int. Total moves made.
     */
    public int getCurNumMoves() {
        return this.curNumMoves;
    }

    /**
     * Sets the number of moves made so far.
     * @param newNumMoves : int. The New Number of Moves.
     */
    public void setCurNumMoves(int newNumMoves) {
        if(newNumMoves >= 0) {
            this.curNumMoves = newNumMoves;
        }
    }

    /**
     * @return The number of the current board.
     */
    public int getNumBoard() {
        return this.numBoard;
    }

    /**
     * Reverse a move!
     */
    private void undoMoveables() {
        for(GameObj obj : getAllGameObjsOfType("Moveable", Board.gameObjs)) {
            obj.undo();
        }
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
}
