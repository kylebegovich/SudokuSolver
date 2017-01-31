package util;

import mvc.Model;

/**
 * This is the class used to check if a puzzle is: 1) full, as in there is no
 * empty spaces, and 2) legal, as in each row, column, and box has exactly one
 * of each number
 * 
 * Static class
 * 
 * @since January 31, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class Checker {

	// wrapper method for the entirety of the class
	public static boolean check(Model model) {
		return check(model.getBoard());
	}


	public static boolean check(int[][] board) {
		return isFull(board) && isLegal(board);
	}


	public static boolean isFull(int[][] doneBoard) {
		for (int i = 0; i < doneBoard.length; i++) {
			for (int j = 0; j < doneBoard[0].length; j++) {
				if (doneBoard[i][j] < 1 || doneBoard[i][j] > doneBoard.length) {
					return false;
				}
			}
		}
		return true;
	}


	public static boolean isLegal(int[][] board) {
		int length = board.length;

		// modified from this previous call: getCheckArray(length);
		int[] check = ArrayUtil.getStandardArray(length);

		for (int i = 0; i < length; i++) {

			// check legality for each row
			for (int j = 0; j < length; j++) {
				for (int k = 0; k < length; k++) {
					if (check[k] == board[i][j]) {
						check[k] = 0;
						break;
					}
				}
			}

			// check that each value in check got set to 0 above
			for (int j = 0; j < length; j++) {
				if (check[j] != 0) {
					return false;
				}
			}

			// reset check array
			check = ArrayUtil.getStandardArray(length);

			// check legality for each column
			for (int j = 0; j < length; j++) {
				for (int k = 0; k < length; k++) {
					if (check[k] == board[j][i]) { // note: i <--> j
						check[k] = 0;
						break;
					}
				}
			}

			// check that each value in check got set to 0 above
			for (int j = 0; j < length; j++) {
				if (check[j] != 0) {
					return false;
				}
			}

			// reset check array
			check = ArrayUtil.getStandardArray(length);
		}


		// check legality for each box
		int boxSize = (int) Math.sqrt(length);

		// rowMajor & colMajor iterate which box to be solving
		for (int rowMajor = 0; rowMajor < board.length; rowMajor += boxSize) {
			for (int colMajor = 0; colMajor < board[0].length; colMajor += boxSize) {

				// make sure to reset temporary variables used
				check = ArrayUtil.getStandardArray(length);
				
				// row & col iterate within a box
				for (int row = rowMajor; row < rowMajor + boxSize; row++) {
					for (int col = colMajor; col < colMajor + boxSize; col++) {

						// k iterates through check array
						for (int k = 0; k < length; k++) {
							if (check[k] == board[row][col]) {
								check[k] = -1;
								break;
							}
						}
					}
				} // end each box

				// check that each value in check got set to -1 above
				for (int c = 0; c < length; c++) {
					if (check[c] != -1) {
						return false;
					}
				}
			}
		}

		// only reaches this line if every check array was legal
		return true;
	}
}
