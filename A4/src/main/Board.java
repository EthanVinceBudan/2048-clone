package main;

/**
 *	@brief Class for storing all game state information.
 *	@author Ethan Vince-Budan
 *	@date 2021-04-12
 */
public class Board {

	private static BoardStorage contents;
	private static int score = 0;
	private static int highScore = 0;

	/**
	 *	@brief Method to initialize the Board class. This must be called before any other methods.
	 *	@details This method creates a new BoardStorage object with the given dimensions, and
	 *	resets the score back to 0. This does not reset the high score.
	 *	@param x the width of the board.
	 *	@param y the height of the board.
	 */
	public static void init(int x, int y) {
		if (x <= 1 || y <= 1) {
			throw new IllegalArgumentException("Board dimensions must be positive and nonzero");
		}
		contents = new BoardStorage(x, y);
		score = 0;
	}

	/**
	 *	@brief Gets the current score.
	 *	@return the current score.
	 */
	public static int getScore() {
		return score;
	}

	/**
	 *	@brief Gets the highest score acheived since opening the game.
	 *	@return the highest score of the session.
	 */
	public static int getHighScore() {
		return highScore;
	}

	/**
	 *	@brief Sets the current score. Also updates the high score if score surpasses it in value.
	 *	@param s the value to set the current score to.
	 */
	public static void setScore(int s) {
		score = s;
		highScore = (score > highScore) ? score : highScore;
	}

	/**
	 *	@brief Returns the current contents of the game board.
	 *	@details The returned array is a copy, therefore no modification of this data will
	 *	implicitly update the values stored in the BoardStorage.
	 *	@return A copy of the game board contents.
	 */
	public static int[][] getBoard() {
		return contents.getBoard();
	}

	/**
	 *	@brief Performs a move on the current board, in the given direction.
	 *	@details Updates to the game board are done row-by-row or column-by-column, depending on
	 *	whether the given direction is horizontal or vertical. If any tiles are actually moved by
	 *	this operation, a new tile is added to the board at a random position.
	 *	@see Movement for more information on how this movement is actually performed.
	 *	@param dir the direction in which to move.
	 */
	public static void move(Direction dir) {
		switch (dir) {
		case UP:
		case DOWN:
			for (int i = 0; i < contents.getWidth(); i++) {
				int[] oldCol = extractCol(i, contents.getBoard());
				contents.setCol(i, Movement.shift(dir, oldCol));
			}
			break;

		case LEFT:
		case RIGHT:
			for (int i = 0; i < contents.getHeight(); i++) {
				int[] oldRow = contents.getBoard()[i];
				contents.setRow(i, Movement.shift(dir, oldRow));
			}
		}
		if (Movement.moveMade()) {
			contents.addRandomTiles(1);
		}

		Movement.clearMoveMade();
	}

	private static int[] extractCol(int n, int[][] board) {
		int[] col = new int[board.length];
		for (int i = 0; i < board.length; i++) {
			col[i] = board[i][n];
		}
		return col;
	}

	/**
	 *	@brief Returns whether or not there are any moves left to make.
	 *	@details If the board is not full, then automatically there are possible moves to make.
	 *	If the board is full, then the neighbors of each cell are checked for any potential
	 *	merges.
	 *	@return whether or not there are any moves left to make.
	 */
	public static boolean movesPossible() {
		if (!contents.isFull()) {
			return true;
		}

		int[][] currentBoard = contents.getBoard();
		for (int y = 0; y < contents.getHeight(); y++) {
			for (int x = 0; x < contents.getWidth(); x++) {
				if (contents.getTileAt(x - 1, y) == currentBoard[y][x]) {
					return true;
				}
				if (contents.getTileAt(x + 1, y) == currentBoard[y][x]) {
					return true;
				}
				if (contents.getTileAt(x, y + 1) == currentBoard[y][x]) {
					return true;
				}
				if (contents.getTileAt(x, y - 1) == currentBoard[y][x]) {
					return true;
				}
			}
		}

		return false;
	}
}
