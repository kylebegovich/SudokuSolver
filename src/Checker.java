/**
 * This is the class used to check if a puzzle is: 1) full, as in there is no
 * empty spaces, and 2) legal, as in each row, column, and box has exactly one
 * of each number
 * 
 * Static class
 * 
 * @since January 3, 2017
 * @author Kyle Begovich
 * @version 1.0
 */
public class Checker {

	// wrapper method for the entirety of the class
	public static boolean check(Model model) {
		return check(model.getBoard());
	}

	public static boolean check(int[][] board) {
		return isFull(board) && isLegal(board);
	}

	// wrapper method, checks no empty spaces
	public static boolean isFull(Model model) {
		return isFull(model.getBoard());
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

	// wrapper method, checks if solution is genuine
	public static boolean isLegal(Model model) {
		return isLegal(model.getBoard());
	}

	public static boolean isLegal(int[][] board) {
		int length = board.length;
		int[] check = getCheckArray(length);

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
			check = getCheckArray(length);

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
			check = getCheckArray(length);
		}

		// check legality for each box
		int boxSize = (int) Math.sqrt(length);

		// TODO figure out why this is broken, fix it
		// rowMajor & colMajor iterate which box to be solving
		for (int rowMajor = 0; rowMajor < board.length; rowMajor += boxSize) {
			for (int colMajor = 0; colMajor < board[0].length; colMajor += boxSize) {

				// make sure to reset temporary variables used
				check = getCheckArray(length);
				
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

	// initialize standard array to make checking possible
	// value = index + 1 for entire array
	private static int[] getCheckArray(int length) {
		int[] check = new int[length];
		for (int i = 0; i < check.length; i++) {
			check[i] = i + 1;
		}
		return check;
	}
}
