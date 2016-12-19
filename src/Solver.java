/**
 * This is the class used to solve the puzzle given from Driver.java
 * 
 * These methods are individually developed unless cited in the method
 * description
 * 
 * Static class
 * 
 * @date December 19, 2016
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
}
