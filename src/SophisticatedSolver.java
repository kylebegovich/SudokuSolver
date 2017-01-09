/**
 * Sophisticated solving algorithms class
 * 
 * Example, if there's a tuple of size n spaces that both have the same n
 * potential values, treat the tuple as though it is already filled with those
 * values in order to solve for other surrounding spaces
 * 
 * Static class
 * ^Not sure if this will be maintained
 * 
 * @since January 9, 2017
 * @author Kyle Begovich
 * @version 1.0
 */
public class SophisticatedSolver {

	public static void sophisticatedSolve(Model model) {
		solveForStructures(model);
	}

	/**
	 * Will make a call to each row, column, and box to solve for missing values
	 * 
	 * Static method
	 */
	public static void solveForStructures(Model model) {
		solveRows(model);
		solveColumns(model);
		solveBoxes(model);
	}

	public static void solveRows(Model model) {
	    // note that this is a rough draft and not necessarily
        // reflective of the final version of this method
        int length = model.getBoard().length;
        int[][] board = model.getBoard();
        int counter = 0;
        int[] standardArray = ArrayUtil.getStandardArray(length);

        for (int row = 0; row < length; row ++) {
            int[] currentRow = board[row];

            for (int col = 0; col < length; col ++) {
                if (currentRow[col] == 0) counter ++;
                else standardArray[currentRow[col]-1] = 0;
            }

            int[] neededNumbers = new int[counter];
            counter = 0;

            for (int i = 0; i < length; i ++) {
                if (standardArray[i] != 0) {
                    neededNumbers[counter] = standardArray[i];
                    counter ++;
                }
            }
            counter = 0;

            // at this point, neededNumbers[] is the set of numbers from
            // which every empty element in row[] == 0 needs to select one of
        }
	}
	
	public static void solveColumns(Model model) {
		// TODO implement
	}
	
	public static void solveBoxes(Model model) {
		// TODO implement
	}



    public static void findTuples() {
        // TODO figure out how these should be returned or stored
        // TODO implement
    }
}
