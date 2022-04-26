package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 *	@brief Class to handle inputs from main GUI and to update the display.
 *	@author Ethan Vince-Budan
 *	@date 2021-04-12
 */
public class Controller implements KeyListener, ActionListener {

	private HashMap<String, Direction> controls = new HashMap<String, Direction>();
	private int boardWidth, boardHeight;

	private GUI parent;
	private boolean gameOver = false;

	/**
	 *	@brief Constructor for Controller.
	 *	@details This method updates the controls to be used during gameplay.
	 *	@param p the GUI object that this controller will communicate with.
	 *	@return new Controller object.
	 */
	public Controller(GUI p) {
		this.boardWidth = 4;
		this.boardHeight = 4;
		this.parent = p;
		Board.init(boardWidth, boardHeight);
		controls.put("a", Direction.LEFT);
		controls.put("w", Direction.UP);
		controls.put("s", Direction.DOWN);
		controls.put("d", Direction.RIGHT);
	}

	/**
	 *	@brief Handles any keyboard input from the attached GUI object, and sends movement commands
	 *	to the Board.
	 *	@details Keystrokes will only be registered as movement commands if their corresponding
	 *	characters are stored within the controls. Otherwise, nothing will happen.
	 *	@param e the received KeyEvent from the GUI.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		Direction dir = controls.get(String.valueOf(e.getKeyChar()));
		if (dir != null ) {
			Board.move(dir);
		}
		this.gameOver = !Board.movesPossible();

		this.parent.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 *	@brief Returns whether or not the current game is over.
	 *	@details The game is considered finished when there are no more available moves to be made.
	 *	@return whether or not the game is over.
	 */
	public boolean isGameOver() {
		return this.gameOver;
	}

	/**
	 *	@brief Handles any button presses detected by the attached GUI object, and updates the
	 *	Board respectively.
	 *	@details Since the only button on the GUI is the "New Game" button, this method simply
	 *	resets the game board and the score.
	 *	@param e the received ActionEvent object from the GUI. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Board.init(boardWidth,boardHeight);
		this.gameOver = false;
		parent.repaint();
	}

}
