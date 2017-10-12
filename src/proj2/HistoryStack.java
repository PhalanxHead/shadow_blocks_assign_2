/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */

package proj2;

import java.util.Stack;

public class HistoryStack {

	private Stack<Integer> xStack;
	private Stack<Integer> yStack;
	private int stackSize;
	
	public HistoryStack() {
		this.xStack = new Stack<>();
		this.yStack = new Stack<>();
		this.stackSize = 0;
	}
	
	public void setStackSize(int newStackSize) {
		if(newStackSize >= 0) {
			this.stackSize = newStackSize;
		}
	}
	
	public int getStackSize() {
		return this.stackSize;
	}

	public void pushToStack(int tileX, int tileY) {
		this.xStack.push(tileX);
		this.yStack.push(tileY);
		this.setStackSize(this.getStackSize() + 1);
	}
	
	public int[] popFromStack() {
		int[] newPos = new int[2];
		if(this.stackSize >= 1) {
			this.setStackSize(this.getStackSize() - 1);
			newPos[Board.IND_X] = this.xStack.pop();
			newPos[Board.IND_Y] = this.yStack.pop();
			return newPos;
		} 
		this.setStackSize(0);
		return null;
	}
	
}
