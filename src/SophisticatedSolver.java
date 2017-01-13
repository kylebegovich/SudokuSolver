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
 * @since January 13, 2017
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
	    // local versions of model's fields
        int length = model.getBoard().length;
        int[][] board = model.getBoard();

        // used to record status of the board and it's properties
        int counter;
        int[] standardArray;

        // for every row, check if any value can go in only one location, and if so, put it there
        for (int row = 0; row < length; row ++) {
            // update variables used and modified
            counter = 0;
            standardArray = ArrayUtil.getStandardArray(length);

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

            // at this point, neededNumbers[] is the set of numbers from
            // which every empty element in row[] == 0 needs to select one of

            // an array of all the numbers needed in the row and their respective possible locations
            int[][] numberAvailabilityArray = new int[neededNumbers.length][length];
            // initialize every spot to -1
            for (int i = 0; i < numberAvailabilityArray.length; i ++) {
                for (int j = 0; j < numberAvailabilityArray[0].length; j ++) {
                    numberAvailabilityArray[i][j] = -1;
                }
            }

            // set any possible location as it's index value, instead of -1
            for (int i = 0; i < neededNumbers.length; i ++) {
                for (int col = 0; col < length; col ++) {
                    if (ArrayUtil.canPlace(board, row, col, neededNumbers[i])) {
                        numberAvailabilityArray[i][col] = col;
                    }
                }
            }

            // tracks where to put the number within the row
            int availableIndex = -1;

            for (int i = 0; i < numberAvailabilityArray.length; i ++) {
                for (int j = 0; j < numberAvailabilityArray[0].length; j ++) {
                    if (numberAvailabilityArray[i][j] != -1) {
                        if (availableIndex == -1) {
                            availableIndex = numberAvailabilityArray[i][j];
                        } else {
                            availableIndex = -1;
                            break;
                        }
                    }
                }
                if (availableIndex != -1) {
                    board[row][availableIndex] = neededNumbers[i];
                    model.setBoard(board);
                }
            }
        }
	}
	
	public static void solveColumns(Model model) {
        // local versions of model's fields
        int length = model.getBoard().length;
        int[][] board = model.getBoard();



        // for every column, check if any value can go in only one location, and if so, put it there
        for (int col = 0; col < length; col ++) {
            // used to record status of the board and it's properties
            // update variables used and modified
            int counter = 0;
            int[] standardArray = ArrayUtil.getStandardArray(length);

            // how to track a column as it's own entity
            int[] currentColumn = new int[length];
            for (int row = 0; row < length; row ++) {
                currentColumn[row] = board[row][col];
            }

            // how to figure out how many number are needed
            for (int row = 0; row < length; row ++) {
                if (currentColumn[row] == 0) counter ++;
                else standardArray[currentColumn[row]-1] = -1;
            }

            // resetting counter and setting up the array to hold the missing values
            int[] neededNumbers = new int[counter];
            counter = 0;

            // filling the needed numbers array
            for (int i = 0; i < length; i ++) {
                if (standardArray[i] != -1) {
                    neededNumbers[counter] = standardArray[i];
                    counter ++;
                }
            }

            // an array of all the numbers needed in the column and their respective possible locations
            int[][] numberAvailabilityArray = new int[neededNumbers.length][length];

            // initialize every spot to -1
            for (int i = 0; i < numberAvailabilityArray.length; i ++) {
                for (int j = 0; j < numberAvailabilityArray[0].length; j ++) {
                    numberAvailabilityArray[i][j] = -1;
                }
            }

            // set any possible location as it's index value, instead of -1
            for (int i = 0; i < neededNumbers.length; i ++) {
                for (int row = 0; row < length; row ++) {
                    if (ArrayUtil.canPlace(board, row, col, neededNumbers[i])) {
                        numberAvailabilityArray[i][row] = row;
                    }
                }
            }

            // tracks where to put the number within the column
            int availableIndex = -1;

            for (int i = 0; i < numberAvailabilityArray.length; i ++) {
                for (int j = 0; j < numberAvailabilityArray[0].length; j ++) {
                    if (numberAvailabilityArray[i][j] != -1) {
                        if (availableIndex == -1) {
                            availableIndex = numberAvailabilityArray[i][j];
                        } else {
                            // if there's more than one available index
                            availableIndex = -1;
                            break;
                        }
                    }
                }
                if (availableIndex != -1) {
                    board[availableIndex][col] = neededNumbers[i];
                    model.setBoard(board);
                }
            }
        }
	}
	
	public static void solveBoxes(Model model) {
		// TODO implement
	}



    public static void findTuples() {
        // TODO figure out how these should be returned or stored
        // TODO implement
    }
}
