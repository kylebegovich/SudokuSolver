/**
 * This is the class used to solve the puzzle given from Driver.java
 * 
 * These methods are individually developed unless cited in the method
 * description
 * 
 * Static class
 * 
 * @since January 3, 2017
 * @author Kyle Begovich
 * @version 1.0
 */
public class Solver {

	/**
	 * Wrapper method to the rest of the class
	 * 
	 * Destructive method, no returns
	 * 
	 * @param model
	 */
	public static void solve(Model model) {
		int length = model.board.length;

		// used to prevent infinite loops
		int[][] lastIterationBoard;
		int counter = 0;

		// tracks what to set borad[row][column] equal to
		int availableIndex;

		// only iterate while not solved and not stuck
		while (!Checker.check(model) && counter < 100) {
			// update most recent board
			lastIterationBoard = model.board;

			// check for simple solutions
			SimpleSolver.simpleSolve(model);

			// iterate through every space on the board for a call to the
			// solving algorithm iff space is empty (equal to 0)
			for (int row = 0; row < length; row++) {
				for (int column = 0; column < length; column++) {
					if (model.board[row][column] == 0) {
						// reset variables that got updated
						int[] available = updateAvailable(row, column, length, model.board);
						availableIndex = -1;
						for (int i = 0; i < length; i++) {
							if (available[i] != -1) {
								if (availableIndex >= 0) {
									availableIndex = -1;
									break;
								} else {
									availableIndex = i;
								}
							}
						}
						if (availableIndex >= 0) {
							model.board[row][column] = available[availableIndex];
							System.out.println("testing purposes: updated board at (" + row + "," + column + ") to "
									+ available[availableIndex]);
						}
					}
				}

			}
			if (lastIterationBoard.equals(model.board)) {
				counter++;
			} else {
				counter = 0;
			}
		}
	}

	// Utility methods start here:
	public static int[] updateAvailable(int row, int col, int length, int[][] board) {
		// modified from this previous call: getNewAvailableArray(length);
		int[] available = ArrayUtil.getStandardArray(length);
		System.out.println("testing purposes: updateAvailable(): col = " + col + ", row = " + row);

		// row loop
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (board[i][col] == available[j]) {
					available[j] = -1;
				}
			}
		}
		// column loop
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (board[row][i] == available[j]) {
					available[j] = -1;
				}
			}
		}

		// used to offset the location of the current square within the box
		int rowStart = (int) ((int) (row / Math.sqrt(length)) * Math.sqrt(length));
		int colStart = (int) ((int) (col / Math.sqrt(length)) * Math.sqrt(length));
		System.out.println("testing purposes: colStart = " + colStart + ", rowStart = " + rowStart + ", sqrt = "
				+ Math.sqrt(length));
		// box loop
		for (int r = rowStart; r < rowStart + Math.sqrt(length); r++) {
			// insert c++ joke here
			for (int c = colStart; c < colStart + Math.sqrt(length); c++) {
				for (int j = 0; j < length; j++) {
					System.out.println("testing purposes: r = " + r + ", c = " + c + ", j = " + j);
					if (board[r][c] == available[j]) {
						available[j] = -1;
					}
				}
			}
		}

		return available;
	}

//	public static int[] getNewAvailableArray(int length) {
//		int[] available = new int[length];
//		for (int k = 0; k < available.length; k++) {
//			available[k] = k + 1;
//		}
//		return available;
//	}
}
