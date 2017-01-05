/**
 * Simple solving algorithms class: example: if there's all but one in a row,
 * column, or box, find the remaining number and fill it in
 * 
 * Static class
 * 
 * @since January 3, 2017
 * @author Kyle Begovich
 * @version 1.0
 */

public class SimpleSolver {
	// simple solving algorithms, example:
	// if there's all but one in a row, column, or box,
	// find the remaining number and fill it in

	/**
	 * Wrapper method to the rest of the class Destructive method, no return
	 * 
	 * @param model
	 *            the model to be solved
	 */
	public static void simpleSolve(Model model) {
		solveOneMissingRow(model.board);
		solveOneMissingColumn(model.board);
		solveOneMissingBox(model.board);
	}

	public static void solveOneMissingRow(int[][] board) {
		int[] temp; // temporary set of values to check against
		int openPos; // tracks if exactly one spot is left
		int missingValue; // tracks what value is missing

		for (int row = 0; row < board.length; row++) {
			// make sure to reset temporary variables used
			temp = getCheckArray(board.length);
			openPos = -1;
			missingValue = -1;
			columnLoop: for (int col = 0; col < board[0].length; col++) {
				// iterate through temp array
				for (int k = 0; k < temp.length; k++) {
					// set every value matched in temp to 0
					if (temp[k] == board[row][col]) {
						temp[k] = -1;
						if (k + 1 != temp.length) {
							break;
						}
					}
					// if it was not found in temp, it's an open position
					else if (k + 1 == temp.length) {
						if (openPos == -1) {
							// not yet an open position, track it
							openPos = col;
						} else {
							// more than one open position, go to next row
							openPos = -1;
							break columnLoop;
						}
					}
				} // end k loop
			}
			// only try to replace value if there's only one open position
			if (openPos != -1) {
				// find missingValue: only value in temp != -1
				for (int k = 0; k < temp.length; k++) {
					if (temp[k] != -1) {
						missingValue = temp[k];
						break;
					}
				}
				// TODO remove this check when sure it's safe
				// double check there won't be a nullPointer thrown
				if (missingValue >= 0 && openPos >= 0) {
					board[row][openPos] = missingValue;
				}
			}
		} // end rowLoop
	}

	public static void solveOneMissingColumn(int[][] board) {
		int[] temp; // temporary set of values to check against
		int openPos; // tracks if exactly one spot is left
		int missingValue; // tracks what value is missing

		// note row <--> column
		for (int col = 0; col < board.length; col++) {
			// make sure to reset the temporary variables used
			temp = getCheckArray(board.length);
			openPos = -1;
			missingValue = -1;
			rowLoop: for (int row = 0; row < board[0].length; row++) {
				// iterate through temp array
				for (int k = 0; k < temp.length; k++) {
					// set every value matched in temp to 0
					if (temp[k] == board[row][col]) {
						temp[k] = -1;
						if (k + 1 != temp.length) {
							break;
						}
					}
					// if it was not found in temp, it's an open position
					else if (k + 1 == temp.length) {
						if (openPos == -1) {
							// not yet an open position, track it
							openPos = row;
						} else {
							// more than one open position, go to next row
							openPos = -1;
							break rowLoop;
						}
					}
				} // end k loop
			}
			// only try to replace value if there's only one open position
			if (openPos != -1) {
				// find missingValue: only value in temp != -1
				for (int k = 0; k < temp.length; k++) {
					if (temp[k] != -1) {
						missingValue = temp[k];
						break;
					}
				}
				// TODO remove this check when sure it's safe
				// double check there won't be a nullPointer thrown
				if (missingValue >= 0 && openPos >= 0) {
					board[openPos][col] = missingValue;
				}
			}
		} // end columnLoop
	}

	public static void solveOneMissingBox(int[][] board) {
		int[] temp; // temporary set of values to check against
		int openPosRow; // tracks if exactly one spot is left
		int openPosCol; // needed because boxes are 2 dimensional
		int missingValue; // tracks which value is missing

		int boxSize = (int) Math.sqrt(board.length);

		// rowMajor & colMajor iterate which box to be solving
		for (int rowMajor = 0; rowMajor < board.length; rowMajor += boxSize) {
			for (int colMajor = 0; colMajor < board[0].length; colMajor += boxSize) {

				// make sure to reset temporary variables used
				temp = getCheckArray(board.length);
				openPosRow = -1;
				openPosCol = -1;
				missingValue = -1;

				// row & col iterate within a box
				boxLoop: for (int row = rowMajor; row < rowMajor + boxSize; row++) {
					for (int col = colMajor; col < colMajor + boxSize; col++) {
						// k iterates through temp array
						for (int k = 0; k < temp.length; k++) {
							// set every value matched in temp to 0
							if (temp[k] == board[row][col]) {
								temp[k] = -1;
								break;
							}
							// if it was not found in temp, it's an open
							// position
							else if (k + 1 == temp.length) {
								if (openPosRow == -1 && openPosCol == -1) {
									// if there's not yet an open position,
									// track it
									openPosRow = row;
									openPosCol = col;
								} else {
									// if there's more than one open position,
									// go to next box
									openPosRow = -1;
									openPosCol = -1;
									break boxLoop;
								}
							}
						} // end k loop
					}
				} // end boxLoop

				// only try to replace value if there's only one open position
				if (openPosRow != -1 && openPosCol != -1) {
					// find missingValue: only value in temp != -1
					for (int k = 0; k < temp.length; k++) {
						if (temp[k] != -1) {
							missingValue = temp[k];
							break;
						}
					}
					// TODO remove this check when sure it's safe
					// double check there won't be a nullPointer thrown
					if (missingValue >= 0 && openPosRow >= 0 && openPosCol >= 0) {
						board[openPosRow][openPosCol] = missingValue;
					}
				}

			}
		}
	}

	// initialize standard array to make solving possible
	// value = index + 1 for entire array
	private static int[] getCheckArray(int length) {
		int[] check = new int[length];
		for (int k = 0; k < check.length; k++) {
			check[k] = k + 1;
		}
		return check;
	}
}
