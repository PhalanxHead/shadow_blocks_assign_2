/**
 * @author     Luke Hedt
 * StuID:    832153
 *
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

/**
 * Mage, chases player using algorithm set in specification. Enemy.
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry.
 */
public class Mage extends Moveable {

    public Mage(int x, int y) {
        super("mage", x, y);
        this.addNameTag("Enemy");
        this.addNameTag("Mage");
    }

    /**
     * Move the Mage according to the Specified Algorithm
     */
    @Override
    public boolean moveToDest(Dirs dir) {
        Player player = (Player)Board.getGameObjOfType("Player", Board.getAllGameObjs());
        int distX = player.getTileX() - this.getTileX();
        int distY = player.getTileY() - this.getTileY();

        // Move in direction according to longest distance.
        if(Math.abs(distX) > Math.abs(distY)) {
            if(sgn(distX) > 0) {
                return super.moveToDest(Dirs.RIGHT);
            } else {
                return super.moveToDest(Dirs.LEFT);
            }
        } else {
            if(sgn(distY) > 0) {
                return super.moveToDest(Dirs.DOWN);
            } else {
                return super.moveToDest(Dirs.UP);
            }
        }
    }

    /**
     * Return 1 if Positive, -1 if Negative.
     * @param x : int. Value to test for positivity.
     * @return  1 if Positive, -1 if Negative.
     */
    private static int sgn(int x) {
        if(x < 0) {
            return -1;
        }
        return 1;
    }
}
