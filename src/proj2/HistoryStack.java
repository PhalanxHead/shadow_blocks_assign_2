/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import java.util.Stack;

/**
 * Holds all the old positions of the objects.
 * @author Luke hedt - 832153 || Based on Design by Eleanor McMurtry
 */
public class HistoryStack {
	
	// Member Variables
	private Stack<Integer> xStack;
	private Stack<Integer> yStack;
	private int stackSize;
	
	public HistoryStack() {
		this.xStack = new Stack<>();
		this.yStack = new Stack<>();
		this.setStackSize(0);
	}
	
	/**
	 * Sets the stack size
	 * @param newStackSize : int. The size to set the stack to.
	 */
	public void setStackSize(int newStackSize) {
		if(newStackSize >= 0) {
			this.stackSize = newStackSize;
		}
	}
	
	/**
	 * Gets the size of the stack.
	 * @return Size of the stack.
	 */
	public int getStackSize() {
		return this.stackSize;
	}

	/**
	 * Add a position to the History Stack.
	 * @param tileX : int. TileX Position.
	 * @param tileY : int. TileY Position.
	 */
	public void pushToStack(int tileX, int tileY) {
		this.xStack.push(tileX);
		this.yStack.push(tileY);
		this.setStackSize(this.getStackSize() + 1);
	}
	
	/**
	 * Retrieves the last entered Position.
	 * @return int[] Position array.
	 */
	public int[] popFromStack() {
		int[] newPos = new int[2];
		// Don't try to pop an empty stack.
		if(this.stackSize >= 1) {
			this.setStackSize(this.getStackSize() - 1);
			newPos[Board.IND_X] = this.xStack.pop();
			newPos[Board.IND_Y] = this.yStack.pop();
			return newPos;
		} 
		// Return null if stack is empty.
		this.setStackSize(0);
		return null;
	}
	
}
