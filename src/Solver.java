/**
 * This is the class used to solve the puzzle given from Model, makes calls to other solving classes
 *
 * These methods are all individually developed
 * 
 * Static class
 * 
 * @since January 17, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class Solver {

	/**
	 * Wraps standard solving algorithms with calls to SimpleSolver and SophisticatedSolver
	 *
	 * Static method
	 * 
	 * @param model
	 */
	public static void solve(Model model) {
		int length = model.getBoard().length;
		int[][] board = model.getBoard();
		int[][][] complexBoard = model.getComplexBoard();

		// used to prevent infinite loops
		int[][] lastIterationBoard;
		int counter = 0;

		// tracks what to set board[row][column] equal to
		int availableIndex;

		// only iterate while not solved and not stuck
		while (!Checker.check(model) && counter < 100) {
			// update most recent board
			lastIterationBoard = board;

			// check for simple solutions
			SimpleSolver.simpleSolve(model);

			// iterate through every space on the board for a call to the
			// standard solving algorithm iff space is empty (equal to 0)
			for (int row = 0; row < length; row++) {
				for (int column = 0; column < length; column++) {
					if (board[row][column] == 0) {
						// reset variables that got changed
                        model.updateComplexBoard();
						availableIndex = -1;
						for (int i = 0; i < length; i++) {
							if (complexBoard[row][column][i] != -1) {
								if (availableIndex >= 0) {
									availableIndex = -1;
									break;
								} else {
									availableIndex = i;
								}
							}
						}
						if (availableIndex >= 0) {
							board[row][column] = complexBoard[row][column][availableIndex];
							model.setBoard(board);
						}
					}
				}
			}

			// check for sophisticated solutions
			SophisticatedSolver.sophisticatedSolve(model);

			if (lastIterationBoard.equals(board)) {
				counter++;
			} else {
				counter = 0;
			}
		}
	}
}
