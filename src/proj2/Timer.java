/**
 * @author Luke Hedt - 832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

public class Timer {

	private double time;
	private int target;
	
	public Timer(double time, int target) {
		this.time = time;
		this.target = target;
	}
	
	public void update(int delta) {
		// Unimplemented
	}
	
	public boolean expired() {
		// Unimplemented
		return true;
	}

}
