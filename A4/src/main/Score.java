package main;

/**
 *	@brief Class for updating score information in the Board class.
 * 	@details This class could be extended to include more intricate scoring algorithms
 *	in the future.
 *	@author Ethan Vince-Budan
 *	@date 2021-04-12
 */
public class Score {

	/**
	 *	@brief Updates the score information in the Board class.
	 *	@param s The value to add to the current score.
	 */
	public static void updateScore(int s) {
		Board.setScore(Board.getScore() + s);
	}
	
}
