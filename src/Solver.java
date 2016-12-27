/**
 * This is the class used to solve the puzzle given from Driver.java
 * 
 * These methods are individually developed unless cited in the method
 * description
 * 
 * Static class
 * 
 * @since December 26, 2016
 * @author Kyle Begovich
 * @version 0.0
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
		// TODO implement use of utility methods

		// while it is not yet solved
		int length = model.board.length;
		while (!Checker.check(model)) {
			// first check for simple solutions
			SimpleSolver.simpleSolve(model);
			// iterate through every space on the board for a call to the
			// solving algorithm
			SimpleSolver.solveOneMissingRow(model.board);
			for (int row = 0; row < length; row++) {
				SimpleSolver.solveOneMissingColumn(model.board);
				for (int column = 0; column < length; column++) {
					if (model.board[row][column] == 0) {
						int[] available = updateAvailable(row, column, length, model.board);
						// availableIndex tracks what to set board[row][column] equal to
						int availableIndex = -1;
						for (int i = 0; i < length; i++) {
							if (available[i] != 0) {
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
						}
						System.out.println("testing purposes");
					}
				}

			}
		}
	}

	// Utility methods start here:
	// TODO write utility methods

	public static int[] updateAvailable(int row, int col, int length, int[][] board) {
		int[] available = getNewAvailableArray(length);
		// row loop
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (board[i][col] == available[j]) {
					available[j] = 0;
				}
			}
		}
		// column loop
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (board[row][i] == available[j]) {
					available[j] = 0;
				}
			}
		}
		// used to offset the location of the current square within the box
		int rowStart = (int) ((row / Math.sqrt(length)) * Math.sqrt(length));
		int colStart = (int) ((col / Math.sqrt(length)) * Math.sqrt(length));
		// box loop
		for (int r = rowStart; r < rowStart + Math.sqrt(length); r++) {
			for (int c = rowStart; c < colStart + Math.sqrt(length); c++) {
				for (int j = 0; j < length; j++) {
					if (board[r][c] == available[j]) {
						available[j] = 0;
					}
				}
			}
		}

		return available;
	}

	public static int[] getNewAvailableArray(int length) {
		int[] available = new int[length];
		for (int k = 0; k < available.length; k++) {
			available[k] = k + 1;
		}
		return available;
	}
}
