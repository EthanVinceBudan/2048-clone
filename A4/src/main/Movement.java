package main;
import java.util.Arrays;

/**
 *	@brief Class for performing movement operations on the board.
 *	@author Ethan Vince-Budan
 *	@date 2021-04-12
 */

public class Movement {
	
	private static boolean moveMade = false;

	/**
	 * @brief Returns a new array containing the result of a shift in the given direction.
	 * @details If the given direction is vertical, it is assumed that the operation is taking
	 * place on a column. If the given direction is horizontal, it is assumed that the operation
	 * is taking place on a row.
	 * @param d Direction to shift in
	 * @param seq the row/column to operate on
	 * @return the row/column after the shift.
	 */
	public static int[] shift(Direction d, int[] seq) {
		if (d == Direction.UP || d == Direction.LEFT) {
			return shiftLeft(seq);
		}
		return shiftRight(seq);
	}

	private static int[] shiftLeft(int[] seq) {
		boolean[] merged = new boolean[seq.length];
		int[] shifted = Arrays.copyOf(seq, seq.length);
		
		for (int i = 0; i < shifted.length; i++) {
			if (shifted[i] != 0) {
				int newIndex = i;
				while (newIndex > 0) {
					if (shifted[newIndex - 1] == 0) {
						shifted[newIndex - 1] += shifted[newIndex];
						shifted[newIndex] = 0;
						moveMade = true;
					} else if (shifted[newIndex - 1] == shifted[newIndex] && (!merged[newIndex-1] && !merged[newIndex])) {
						shifted[newIndex - 1] += shifted[newIndex];
						shifted[newIndex] = 0;
						Score.updateScore(shifted[newIndex - 1]);
						moveMade = true;
						merged[newIndex-1] = true;
						break;
					}
					newIndex--;
				}
			}
		}
		return shifted;
	}
	
	/**
	 *	@brief Returns whether or not a tile actually moved during the last shift operation.
	 *	@details This field does not automatically reset after every shift, because multiple shifts
	 *	are required per turn. Use the clearMoveMade() method to reset this field.
	 *	@return whether or not a tile actually moved.
	 */
	public static boolean moveMade() {
		return moveMade;
	}
	
	/**
	 *	@brief Used to reset whether or not a tile was moved to false.
	 *	@details This method is required as this field does not automatically reset after
	 *	every shift.
	 */
	public static void clearMoveMade() {
		moveMade = false;
	}

	private static int[] shiftRight(int[] seq) {
		int[] reversed = reverse(Arrays.copyOf(seq, seq.length));
		reversed = shiftLeft(reversed);
		return reverse(reversed);
	}
	
	private static int[] reverse(int[] original) {
		int[] reversedArray = new int[original.length];
		for (int i = 0; i < original.length; i++) {
			reversedArray[i] = original[original.length-1-i];
		}
		return reversedArray;
	}
}
