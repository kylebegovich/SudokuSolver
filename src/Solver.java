/**
 * This is the class used to solve the puzzle given from Driver.java
 * 
 * These methods are individually developed unless cited in the method
 * description
 * 
 * Static class
 * 
 * @date December 17, 2016
 * @author Kyle
 * @version 0.0
 */
public class Solver {
	private int[][] board;

	public Solver() {
		super();
	}

	/**
	 * Wrapper method to the rest of the class
	 * 
	 * Destructive method
	 * 
	 * @param model
	 * @return the solved puzzle
	 */
	public static void solve(Model model) {
		// solving algorithm goes here
		// TODO implement use of utility methods
		SimpleSolver.simpleSolve(model);
	}

	// Utility methods start here:
	// TODO write utility methods,
	// reference SimpleSolver when possible
}
