package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

/**
 *	@brief Class for displaying the game board on the screen.
 *	@author Ethan Vince-Budan
 *	@date 2021-04-12
 */
public class BoardDisplay {

	private HashMap<Integer, Color> colorSet = new HashMap<Integer, Color>();

	/**
	 *	@brief Constructor for BoardDisplay.
	 *	@details This method updates all of the needed colours for displaying the board.
	 *	@return new BoardDisplay object.
	 */
	public BoardDisplay() {
		colorSet.put(0, new Color(0xcdc1b4));
		colorSet.put(2, new Color(0xeee4da));
		colorSet.put(4, new Color(0xede0c8));
		colorSet.put(8, new Color(0xf2b179));
	}

	/**
	 *	@brief Paints the game board within the given dimensions.
	 *	@details This method does not yet dynamically resize, however this is
	 *	possible to implement.
	 *	@param g the Graphics object to paint the board to
	 *	@param xPos the x position of the top-left corner of the display area.
	 *	@param yPos the y position of the top-left corner of the display area.
	 *	@param w the total width of the display area.
	 *	@param h the total height of the display area.
	 */
	public void paintBoard(Graphics g, int xPos, int yPos, int w, int h) {
		g.setColor(new Color(0xbbada0));
		g.fillRoundRect(xPos, yPos, w + xPos, h + yPos, 60, 60);

		int[][] currentBoard = Board.getBoard();

		int tileWidth = 94;
		int tileHeight = 94;
		int spacing = 10;
		int fontOffsetX = 15;
		int fontOffsetY = 55;

		xPos += 30;
		yPos += 20;

		for (int y = 0; y < currentBoard.length; y++) {
			for (int x = 0; x < currentBoard[0].length; x++) {
				int val = currentBoard[y][x];
				if (val <= 8) {
					g.setColor(colorSet.get(currentBoard[y][x]));
				} else {
					g.setColor(new Color(0xedcf72));
				}
				g.fillRoundRect(xPos + x * (tileWidth + spacing), yPos + y * (tileHeight + spacing), tileWidth,
						tileHeight, 20, 20);
				if (val != 0) {
					g.setColor(new Color(0x776e65));
					g.drawString(String.valueOf(val), xPos + x * (tileWidth + spacing) + fontOffsetX,
							yPos + y * (tileHeight + spacing) + fontOffsetY);
				}
			}
		}
	}

}
