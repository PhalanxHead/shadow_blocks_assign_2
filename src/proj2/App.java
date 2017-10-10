/**
 * @author 	Luke Hedt
 * StuID:	832153
 * 
 * Extends Sample Project for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry. Based on Project 2A Design by Eleanor McMurtry.
 */
package proj2;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame
{
 	/** screen width, in pixels */
    public static final int SCREEN_WIDTH = 800;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 600;
    /** size of the tiles, in pixels */
    public static final int TILE_SIZE = 32;
    
    private Board board, newBoard;

    public App()
    {    	
        super("Shadow Blocks");
    }

    @Override
    public void init(GameContainer gc)
    throws SlickException
    {
    	board = new Board(0);
    }

    /** Update the game state for a frame. Checks for nextBoard condition.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
    throws SlickException
    {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        board.update(input, delta);
        
        if (input.isKeyDown(Input.KEY_ESCAPE)) {
			gc.exit();
		}
        
        if((board.getTargetDiff() <= 0 || input.isKeyPressed(Input.KEY_A)) 
        		&& board.getNumBoard() != 5) {
        	newBoard = loadNextBoard(board.getNumBoard());
        	board.destroy();
        	board = newBoard;
        } else if(board.getNumBoard() > 5) {
        	gc.exit();
        }
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
    throws SlickException
    {
    	board.render(g);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
    throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new App());
        // setShowFPS(true), to show frames-per-second.
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }
    
    /**
     * Loads the next board
     * @param boardNum : int. The number of the current board.
     * @return The next Board object
     */
	private Board loadNextBoard(int boardNum) {
		Board nextBoard = new Board(boardNum + 1);
		return nextBoard;
	}

}