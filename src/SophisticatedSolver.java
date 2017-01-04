/**
 * Sophisticated solving algorithms class
 * 
 * Example, if there's a tuple of size n spaces that both have the same n
 * potential values, treat the tuple as though it is already filled with those
 * values in order to solve for other surrounding spaces
 * 
 * Static class
 * 
 * @since January 4, 2017
 * @author Kyle Begovich
 * @version 1.0
 */
public class SophisticatedSolver {

	public static void findTuples() {
		// TODO figure out how these should be returned
		// TODO implement
	}

	/**
	 * Will make a call to each row, column, and box to solve for missing values
	 * 
	 * Static method
	 */
	public static void solveForStructures() {
		solveRows();
		solveColumns();
		solveBoxes();
	}
	
	
	public static void solveRows() {
		// TODO implement
	}
	
	public static void solveColumns() {
		// TODO implement
	}
	
	public static void solveBoxes() {
		// TODO implement
	}
	

	//Utility methods I'm not sure I'll need yet
	public static void iterateRow(int rowIndex) {
		// TODO implement
	}

	public static void iterateCol(int colIndex) {
		// TODO implement
	}

	public static void iterateBox(int rowIndex, int colIndex) {
		// TODO implement
	}
}
