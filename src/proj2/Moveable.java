/**
 * @author Luke Hedt - 832153
 *
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */


package proj2;

/**
 * Moveable Abstract Object. Has a historyStack.
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry
 */
public abstract class Moveable extends GameObj {

    private HistoryStack histStack;

    public Moveable(String image_src, int tileX, int tileY) {
        super(image_src, tileX, tileY);
        this.addNameTag("Moveable");
        this.histStack = new HistoryStack();
    }

    /**
     * Works out where the object is moving to.
     * @param dir : Dirs. Direction of Travel.
     * @param tileX : int. Current tileX Coord.
     * @param tileY : int. Current tileY Coord.
     * @return Array of next position.
     */
    public static int[] newTilePos(Dirs dir, int tileX, int tileY) {
        // Moving one tile at a time.
        int speed = 1;
        int[] newTilePos = new int[2];
        // Translate the direction to an x and y displacement
        int delta_x = 0, delta_y = 0;
        switch (dir) {
            case LEFT:
                delta_x = -speed;
                break;
            case RIGHT:
                delta_x = speed;
                break;
            case UP:
                delta_y = -speed;
                break;
            case DOWN:
                delta_y = speed;
                break;
            default:
                break;
        }

        newTilePos[Board.IND_X] = tileX + delta_x;
        newTilePos[Board.IND_Y] = tileY + delta_y;

        return newTilePos;
    }

    /**
     * Moves to destination, pushing blocks if necessary.
     * @param dir : Dirs. The direction to move in.
     * @return True if successful
     */
    public boolean moveToDest(Dirs dir) {
        int[] newTilePos = newTilePos(dir, this.getTileX(), this.getTileY());
        int newTileX = newTilePos[Board.IND_X];
        int newTileY = newTilePos[Board.IND_Y];

        // Bounds Checking
        if(!Loader.inBounds(newTileX, newTileY)) {
            return false;
        }

        if(newTileX != this.getTileX() || newTileY != this.getTileY()) {
            onMove(dir, this.getTileX(), this.getTileY());
        }
        // Check Not Blocked and try and push a pushable if it exists
        if(Board.isBlocked(newTileX, newTileY)
                || (Board.isNameTag(newTileX, newTileY, "Pushable") &&
                        !Board.getGameObjOfType("Pushable", newTileX, newTileY).push(dir))) {
            return false;

        } else {
            this.setTileX(newTileX);
            this.setTileY(newTileY);
            return true;
        }
    }

    /**
     * Executes any move-triggered events.
     * @param dir : Dirs. Direction of travel.
     * @param curTileX : int. The current X tile.
     * @param curTileY : int. Tge current Y tile.
     */
    public void onMove(Dirs dir, int curTileX, int curTileY) {
        // Does nothing by default.
    }

    /**
     * Undoes a move
     */
    @Override
    public void undo() {
        int[] newPos = this.histStack.popFromStack();
        if(newPos != null) {
            this.setTileX(newPos[Board.IND_X]);
            this.setTileY(newPos[Board.IND_Y]);
        }
    }

    /**
     * Returns the object's History Stack.
     */
    @Override
    public HistoryStack getHistStack() {
        return this.histStack;
    }
}
