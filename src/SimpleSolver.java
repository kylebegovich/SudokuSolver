/**
 * Simple solving algorithms class
 *
 * Example: if there's all but one in a row,
 * column, or box, find the remaining number and fill it in
 *
 * These methods are all individually developed
 *
 * Static class
 * 
 * @since January 158, 2017
 * @author Kyle Begovich
 * @version 1.5
 */

public class SimpleSolver {

	/**
	 * Wrapper method to the rest of the class Destructive method, no return
	 * 
	 * @param model
	 *            the model to be solved
	 */
	public static void simpleSolve(Model model) {
		solveOneMissingRow(model);
		solveOneMissingColumn(model);
		solveOneMissingBox(model);
	}

	public static void solveOneMissingRow(Model model) {
		int[][] board = model.getBoard(); // the board from the model
		int[] temp; // temporary set of values to check against
		int availableIndex; // tracks if exactly one spot is left
		int missingValue; // tracks what value is missing

		for (int row = 0; row < board.length; row++) {

			// make sure to reset temporary variables used
            temp = ArrayUtil.getStandardArray(board.length);
			availableIndex = -1;
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
						if (availableIndex == -1) {

							// not yet an open position, track it
							availableIndex = col;

						} else {

							// more than one open position, go to next row
							availableIndex = -1;
							break columnLoop;

						}
					}
				} // end k loop
			}

			// only try to replace value if there's only one open position
			if (availableIndex != -1) {

				// find missingValue: only value in temp != -1
				for (int k = 0; k < temp.length; k++) {
					if (temp[k] != -1) {
						missingValue = temp[k];
						break;
					}
				}

				// the actual update to the board
				board[row][availableIndex] = missingValue;

				// updating the state in model
				model.setBoard(board);
			}
		} // end rowLoop
	}

	public static void solveOneMissingColumn(Model model) {
		int[][] board = model.getBoard(); // the board from the model
		int[] temp; // temporary set of values to check against
		int availableIndex; // tracks if exactly one spot is left
		int missingValue; // tracks what value is missing

		// note row <--> column
		for (int col = 0; col < board.length; col++) {

			// make sure to reset the temporary variables used
            temp = ArrayUtil.getStandardArray(board.length);
			availableIndex = -1;
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
						if (availableIndex == -1) {

							// not yet an open position, track it
							availableIndex = row;

						} else {

							// more than one open position, go to next row
							availableIndex = -1;
							break rowLoop;

						}
					}
				} // end k loop
			}

			// only try to replace value if there's only one open position
			if (availableIndex != -1) {

				// find missingValue: only value in temp != -1
				for (int k = 0; k < temp.length; k++) {
					if (temp[k] != -1) {
						missingValue = temp[k];
						break;
					}
				}

				// the actual update to the board
				board[availableIndex][col] = missingValue;

				// updating the state in model
				model.setBoard(board);
			}
		} // end columnLoop
	}

	public static void solveOneMissingBox(Model model) {
		int[][] board = model.getBoard(); // the board from the model
		int[] temp; // temporary set of values to check against
		int availableIndexRow; // tracks if exactly one spot is left
		int availableIndexCol; // needed because boxes are 2 dimensional
		int missingValue; // tracks which value is missing

		int boxSize = (int) Math.sqrt(board.length);

		// rowMajor & colMajor iterate which box to be solving
		for (int rowMajor = 0; rowMajor < board.length; rowMajor += boxSize) {
			for (int colMajor = 0; colMajor < board[0].length; colMajor += boxSize) {

				// make sure to reset temporary variables used
                temp = ArrayUtil.getStandardArray(board.length);
				availableIndexRow = -1;
				availableIndexCol = -1;
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

							// if it was not found in temp, it's an open position
							else if (k + 1 == temp.length) {
								if (availableIndexRow == -1 && availableIndexCol == -1) {

									// if there's not yet an open position,
									// track it
									availableIndexRow = row;
									availableIndexCol = col;

								} else {

									// if there's more than one open position,
									// go to next box
									availableIndexRow = -1;
									availableIndexCol = -1;
									break boxLoop;
								}
							}
						} // end k loop
					}
				} // end boxLoop

				// only try to replace value if there's only one open position
				if (availableIndexRow != -1 && availableIndexCol != -1) {

					// find missingValue: only value in temp != -1
					for (int k = 0; k < temp.length; k++) {
						if (temp[k] != -1) {
							missingValue = temp[k];
							break;
						}
					}

					// the actual update to the board
					board[availableIndexRow][availableIndexCol] = missingValue;

					// updating the state in model
					model.setBoard(board);
				}

			}
		} // end row and column Major loops
	}
}
