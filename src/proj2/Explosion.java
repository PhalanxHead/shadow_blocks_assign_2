/**
 * @author Luke Hedt - 832153
 *
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import org.newdawn.slick.Input;

/**
 * Explosion Object. Appears when TNT hits an Explodable Object for LIFE ms.
 * @author lhedt
 *
 */
public class Explosion extends GameObj {

    // Lifespan of the explosion in ms
    private static int LIFE = 400;
    private Timer timer;

    public Explosion(int tileX, int tileY) {
        super("explosion", tileX, tileY);
        this.timer = new Timer(LIFE);
    }

    /**
     * Destroy after LIDE ms.
     */
    @Override
    public void update(Input input, int delta) {
        timer.update(delta);

        if(timer.expired()) {
            this.destroy();
        }
    }

    /**
     * Destroys the Object.
     */
    public void destroy() {
        Board.destroySpecialGameObj();
    }
}
