/**
 * @author     Luke Hedt
 * StuID:    832153
 *
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

/**
 * Place to put pushables, covering them all finishes the level
 * @author Luke Hedt - 832153 || Based on Design by Eleanor McMurtry
 */
public class Target extends GameObj {
    public Target(int x, int y) {
        super("target", x, y);
        this.addNameTag("Target");
    }
}
