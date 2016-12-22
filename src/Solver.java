/**
 * This is the class used to solve the puzzle given from Driver.java
 * 
 * These methods are individually developed unless cited in the method
 * description
 * 
 * Static class
 * 
 * @date December 20, 2016
 * @author Kyle
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
		// while it is not yet solved
		while (!Checker.check(model)) {
			// first check for simple solutions
			SimpleSolver.simpleSolve(model);
			// iterate through every space on the board for a call to the
			// solving algorithm
			for (int row = 0; row < model.board.length; row++) {
				for (int column = 0; column < model.board[0].length; column++) {
					// TODO implement use of utility methods
					System.out.println("testing purposes");
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
		int rowStart = (int) ((row/Math.sqrt(length))*Math.sqrt(length));
		int colStart = (int) ((col/Math.sqrt(length))*Math.sqrt(length));
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
