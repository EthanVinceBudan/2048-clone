package main;
import java.util.Random;

/**
 *	@brief Class for storing board-specific information and performing operations on that data.
 *	@author Ethan Vince-Budan
 *	@date 2021-04-12
 */
public class BoardStorage {

	private int[][] contents;
	private int width, height;

	/**
	 *	@brief Constructor for the BoardStorage class.
	 *	@details When a new board is generated, two random tiles are immediately placed on the
	 *	board. Since there must be room for these tiles, the board size cannot be smaller than 2x2.
	 *	@param x width of the board
	 *	@param y height of the board
	 *	@throws IllegalArgumentException if either the given width or height are less than 2.
	 *	@return new BoardStorage object.
	 */
	public BoardStorage(int x, int y) {
		if (x <= 1 || y <= 1) {
			throw new IllegalArgumentException("Board dimensions must be larger than 1");
		}
		this.width = x;
		this.height = y;
		this.contents = new int[y][x];
		this.addRandomTiles(2);
	}

	/**
	 *	@brief Gets the width of the stored board.
	 *	@return the width of the board.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 *	@brief Gets the height of the stored board.
	 *	@return the height of the board.
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 *	@brief Check to see whether the board is full of tiles. This does not guarantee that
	 *	there are no more moves left.
	 *	@return whether or not every cell on the board is occupied by a tile.
	 */
	public boolean isFull() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (this.contents[y][x] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 *	@brief Returns a copy of all the board data stored in this object.
	 *	@return the board data stored in this object.
	 */
	public int[][] getBoard() {
		return copy2D(this.contents);
	}

	/**
	 *	@brief Used to set the designated row of the board to the provided sequence.
	 *	@param n index of the row to change. This ranges from 0 to height-1 inclusive.
	 *	@param row the new information to add to the board. Must be the same width as
	 *	the currently stored board.
	 *	@throws IllegalArgumentException if n is out of bounds, or the row is not the
	 *	correct size.
	 */
	public void setRow(int n, int[] row) {
		if (0 > n || n > this.height) {
			throw new IllegalArgumentException("out of range");
		}

		if (row.length != this.width) {
			throw new IllegalArgumentException("wrong width");
		}

		this.contents[n] = row;
	}

	/**
	 *	@brief Used to set the designated column of the board to the provided sequence.
	 *	@param n index of the column to change. This ranges from 0 to width-1 inclusive.
	 *	@param col the new information to add to the board. Must be the same height
	 *	as the currently stored board.
	 *	@throws IllegalArgumentException if n is out of bounds, or if the column is not
	 *	the correct size.
	 */
	public void setCol(int n, int[] col) {
		if (0 > n || n > this.width) {
			throw new IllegalArgumentException("out of range");
		}

		if (col.length != this.height) {
			throw new IllegalArgumentException("wrong height");
		}

		for (int i = 0; i < this.height; i++) {
			this.contents[i][n] = col[i];
		}
	}

	private int[][] copy2D(int[][] original) {
		int[][] copy = new int[original.length][original[0].length];

		for (int y = 0; y < original.length; y++) {
			for (int x = 0; x < original[0].length; x++) {
				copy[y][x] = original[y][x];
			}
		}
		return copy;
	}

	/**
	 *	@brief Adds n random tiles to the board.
	 *	@details This method cannot overwrite preexisting tiles, and will throw a runtime error if
	 *	the method is called on a full board.
	 *	@param n the number of tiles to add.
	 *	@throws ArrayStoreException if the board is full.
	 */
	public void addRandomTiles(int n) {
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			if (this.isFull()) {
				throw new ArrayStoreException("Not enough room on board");
			}
			int x, y;
			do {
				y = rand.nextInt(this.getHeight());
				x = rand.nextInt(this.getWidth());
			} while (this.contents[y][x] != 0);
			this.contents[y][x] = (rand.nextFloat() >= 0.9) ? 4 : 2;
		}
	}
	
	/**
	 *	@brief Returns the value of the tile at the given location. If the given location is out
	 *	of bounds, this method will return -1.
	 *	@param x the x position of the tile, ranging from 0 to width-1 inclusive.
	 *	@param y the y position of the tile, ranging from 0 to height-1 inclusive.
	 *	@return the value of the tile at the given location, or -1 if out of bounds.
	 */
	public int getTileAt(int x, int y) {
		if (0 <= x && x < contents[0].length) {
			if (0 <= y && y < contents.length) {
				return contents[y][x];
			}
		}
		return -1;
	}
}
