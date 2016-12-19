/**
 * Simple solving algorithms class: example: if there's all but one in a row,
 * column, or box, find the remaining number and fill it in
 * 
 * Static class
 * 
 * @date December 19, 2016
 * @author Kyle
 * @version 0.0
 */

public class SimpleSolver {
	// TODO write simple solving algorithms,
	// example: if there's all but one in a row, column, or box,
	// find the remaining number and fill it in

	/**
	 * Wrapper method to the rest of the class
	 * 
	 * Destructive method
	 * 
	 * @param model
	 * @return the solved puzzle
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

		for (int row = 0; row < board.length; row ++) {
			for (int column = 0; column < board[0].length; column ++) {
				for (int k = 0; k < temp.length; k ++) {
					if (temp[k] == board[row][column]) {
						temp[k] = 0;
						break;
					} else if (k + 1 == temp.length) {
						openPos = column;
					}
				}
			}
			// check that exactly one value didn't get set to 0
			for (int i = 0; i < temp.length; i ++) {
				if (temp[i] != 0) {
					// if there's already been another value found, break
					if (missingValue >= 0) {
						missingValue = -1;
						break;
					}
					missingValue = i;
				}
			}
			if (missingValue >= 0) {
				board[row][openPos] = missingValue;
			}
		} // end row loop
	}

	public static void solveOneMissingColumn(int[][] board) {
		int[] temp = getCheckArray(board.length);
		int openPos = -1; // tracks if exactly one spot is left
		int missingValue = -1;

		// note row <--> column
		for (int column = 0; column < board.length; column ++) {
			for (int row = 0; row < board[0].length; row ++) {
				for (int k = 0; k < temp.length; k ++) {
					if (temp[k] == board[row][column]) {
						temp[k] = 0;
						break;
					} else if (k + 1 == temp.length) {
						openPos = column;
					}
					
				}
			}
			// check that exactly one value didn't get set to 0
			for (int i = 0; i < temp.length; i ++) {
				if (temp[i] != 0) {
					// if there's already been another value found, break
					if (missingValue >= 0) {
						missingValue = -1;
						break;
					}
					missingValue = i;
				}
			}
			if (missingValue >= 0) {
				board[openPos][column] = missingValue;
			}
		} // end column loop
	}

	public static void solveOneMissingBox(int[][] board) {
		// TODO Auto-generated method stub

	}

	// initialize standard array to make solving possible
	private static int[] getCheckArray(int length) {
		int[] check = new int[length];
		for (int i = 0; i < check.length; i ++) {
			check[i] = i + 1;
		}
		return check;
	}
}
