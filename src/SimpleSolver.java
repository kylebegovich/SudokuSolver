/**
 * Simple solving algorithms class: example: if there's all but one in a row,
 * column, or box, find the remaining number and fill it in
 * 
 * Static class
 * 
 * @since December 22, 2016
 * @author Kyle
 * @version 0.0
 */

public class SimpleSolver {
	// TODO write simple solving algorithms,
	// example: if there's all but one in a row, column, or box,
	// find the remaining number and fill it in

	/**
	 * Wrapper method to the rest of the class
	 * Destructive method, no return
	 * 
	 * @param the model to be solved
	 */
	public static void simpleSolve(Model model) {
		solveOneMissingRow(model.board);
		solveOneMissingColumn(model.board);
		solveOneMissingBox(model.board);
	}

	public static void solveOneMissingRow(int[][] board) {
		int[] temp = getCheckArray(board.length);
		int openPos = -1; // tracks if exactly one spot is left
		int missingValue = -1; // tracks what value is missing

		for (int row = 0; row < board.length; row++) {
			columnLoop: for (int col = 0; col < board[0].length; col++) {
				for (int k = 0; k < temp.length; k++) {
					// set every value matched in temp to 0
					if (temp[k] == board[row][col]) {
						temp[k] = 0;
						break;
					}
					// if it was not found in temp, it's an open position
					else if (k + 1 == temp.length) {
						if (openPos == -1) {
							// if there's not yet an open position, track it
							openPos = col;
						} else {
							// if there's more than one open position, go to
							// next row
							break columnLoop;
						}
					}
				} // end temp loop
			}
			// only try to replace value if there's only one open position
			if (openPos != -1) {
				// find missingValue: only value in temp != 0
				for (int k = 0; k < temp.length; k++) {
					if (temp[k] != 0) {
						missingValue = k;
						break;
					}
				}
				// double check there won't be a nullPointer thrown
				if (missingValue >= 0 && openPos >= 0) {
					board[row][openPos] = missingValue;
				}
			}
		} // end rowLoop
	}

	public static void solveOneMissingColumn(int[][] board) {
		int[] temp = getCheckArray(board.length);
		int openPos = -1; // tracks if exactly one spot is left
		int missingValue = -1; // tracks what value is missing

		// note row <--> column
		for (int col = 0; col < board.length; col++) {
			rowLoop: for (int row = 0; row < board[0].length; row++) {
				for (int k = 0; k < temp.length; k++) {
					// set every value matched in temp to 0
					if (temp[k] == board[row][col]) {
						temp[k] = 0;
						break;
					}
					// if it was not found in temp, it's an open position
					else if (k + 1 == temp.length) {
						if (openPos == -1) {
							// if there's not yet an open position, track it
							openPos = row;
						} else {
							// if there's more than one open position, go to
							// next row
							break rowLoop;
						}
					}
				} // end temp loop
			}
			// only try to replace value if there's only one open position
			if (openPos != -1) {
				// find missingValue: only value in temp != 0
				for (int k = 0; k < temp.length; k++) {
					if (temp[k] != 0) {
						missingValue = k;
						break;
					}
				}
				// double check there won't be a nullPointer thrown
				if (missingValue >= 0 && openPos >= 0) {
					board[openPos][col] = missingValue;
				}
			}
		} // end columnLoop
	}

	public static void solveOneMissingBox(int[][] board) {
		int[] temp = getCheckArray(board.length);
		int openPosRow = -1; // tracks if exactly one spot is left
		int openPosCol = -1; // needed because boxes are 2d
		int missingValue = -1; // tracks which value is missing

		int boxSize = (int) Math.sqrt(temp.length);

		// i & j iterate which box to be solving
		for (int i = 0; i < board.length; i += boxSize) {
			for (int j = 0; j < board[0].length; j += boxSize) {

				// u & v iterate within a box
				boxLoop: for (int row = i; row < i + boxSize; row++) {
					for (int col = j; col < j + boxSize; col++) {
						// k iterates through temp array
						for (int k = 0; k < temp.length; k++) {
							// set every value matched in temp to 0
							if (temp[k] == board[row][col]) {
								temp[k] = 0;
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
									break boxLoop;
								}
							}
						} // end temp loop
					}
				} // end boxLoop

				// only try to replace value if there's only one open position
				if (openPosRow != -1 && openPosCol != -1) {
					// find missingValue: only value in temp != 0
					for (int k = 0; k < temp.length; k++) {
						if (temp[k] != 0) {
							missingValue = k;
							break;
						}
					}
					// double check there won't be a nullPointer thrown
					if (missingValue >= 0 && openPosRow >= 0 && openPosCol >= 0) {
						board[openPosRow][openPosCol] = missingValue;
					}
				}

			}
		}
	}

	// initialize standard array to make solving possible
	// values = index + 1: [1,length]
	private static int[] getCheckArray(int length) {
		int[] check = new int[length];
		for (int k = 0; k < check.length; k++) {
			check[k] = k + 1;
		}
		return check;
	}
}
