/**
 * This is the class used to check if a puzzle is: 1) full, as in there is no
 * empty spaces, and 2) legal, as in each row, column, and box has exactly one
 * of each number
 * 
 * Static class
 * 
 * @date December 15, 2016
 * @author Kyle
 * @version 0.0
 */
public class Checker {

	// wrapper method for the entirety of the class
	public static boolean check(Model model) {
		return check(model.getBoard());
	}

	public static boolean check(int[][] board) {
		return isFull(board) && isLegal(board);
	}

	// wrapper method, checks no empty spaces
	public static boolean isFull(Model model) {
		return isFull(model.getBoard());
	}

	public static boolean isFull(int[][] doneBoard) {
		for (int i = 0; i < doneBoard.length; i++) {
			for (int j = 0; j < doneBoard[0].length; j++) {
				if (doneBoard[i][j] < 1 || doneBoard[i][j] > doneBoard.length)
					return false;
			}
		}
		return true;
	}

	// wrapper method, checks if solution is genuine
	public static boolean isLegal(Model model) {
		return isLegal(model.getBoard());
	}

	public static boolean isLegal(int[][] board) {
		int[] check = getCheckArray(board.length);

		for (int i = 0; i < board.length; i++) {

			// check legality for each row
			for (int j = 0; j < board[0].length; j++) {
				for (int k = 0; k < check.length; k++) {
					if (check[k] == board[i][j]) {
						check[k] = 0;
						k = check.length;
					}
				}
			}
			// check that each value in check got set to 0 above
			for (int j = 0; j < check.length; j++) {
				if (check[j] != 0)
					return false;
			}
			// reset check array
			check = getCheckArray(board.length);

			// check legality for each column
			for (int j = 0; j < board[0].length; j++) {
				for (int k = 0; k < check.length; k++) {
					if (check[k] == board[j][i]) { // note: i <--> j
						check[k] = 0;
						k = check.length;
					}
				}
			}
			// check that each value in check got set to 0 above
			for (int j = 0; j < check.length; j++) {
				if (check[j] != 0)
					return false;
			}
			// reset check array
			check = getCheckArray(board.length);
		}

		// check legality for each box
		int boxSize = (int) Math.sqrt(check.length);
		// i & j iterate which box to be checking
		for (int i = 0; i < board.length; i += boxSize) {
			for (int j = 0; j < board[0].length; j += boxSize) {
				// u & v iterate within a box
				for (int u = i; u < boxSize; u++) {
					for (int v = j; v < boxSize; v++) {
						// k iterates through check array
						for (int k = 0; k < check.length; k++) {
							if (check[k] == board[u][v]) {
								check[k] = 0;
								k = check.length;
							}
						}
						
					}
				} // end each box
				// check that each value in check got set to 0 above
				for (int c = 0; c < check.length; c++) {
					if (check[c] != 0)
						return false;
				}
				// reset check array
				check = getCheckArray(board.length);
			
			}
		}
		
		// only reaches this line if every check array was filled
		return true;
	} // end isLegal()

	// re-initialize check array to make testing possible
	private static int[] getCheckArray(int length) {
		int[] check = new int[length];
		for (int i = 0; i < check.length; i++) {
			check[i] = i + 1;
		}
		return check;
	}
}
