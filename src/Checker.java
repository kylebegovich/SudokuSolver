/**
 * This is the class used to check if a puzzle is:
 * 	1) complete, as in there is no empty spaces
 * 	2) legal, as in there are no repeated numbers in any row, column, or box
 *  3) possible, as there are a few preconditions to be met 
 *  	before bothering to try to solve an impossible puzzle,
 *  	as described in the method isPossible()
 * 
 * @date Nov 13, 2016
 * @author Kyle
 * @version 0.0
 */
public class Checker {
	// wrapper method for the entirety of the class
	public static boolean check(Puzzle p) {
		return check(p.getBoard());
	}
	public static boolean check(int[][] aBoard) {
		return isFull(aBoard) &&
				isPossible(aBoard) &&
				isLegal(aBoard);
	}
	
	// wrapper method, checks no empty spaces
	public static boolean isFull(Puzzle p) {
		return isFull(p.getBoard());
	}
	public static boolean isFull(int[][] doneBoard) {
		for (int i = 0; i < doneBoard.length; i ++) {
			for (int j = 0; j < doneBoard[0].length; j ++) {
				if (doneBoard[i][j] < 1 || doneBoard[i][j] > doneBoard.length) return false;
			}
		}
		return true;
	}
	
	// wrapper method, checks if solution is genuine
	public static boolean isPossible(Puzzle p) {
		return isPossible(p.getBoard());
	}
	// expectation: isComplete(doneBoard) == true
	public static boolean isPossible(int[][] doneBoard) {		
		//initializing a temporary array to check equality against
		int[] check = new int[doneBoard.length];
		for (int i = 0; i < check.length; i ++) {
			check[i] = i;
		}
		
		for (int i = 0; i < doneBoard.length; i ++) {
			for (int j = 0; j < doneBoard[0].length; j ++) {
				for (int k = 0; i < check.length; i ++) {
					if (check[k] == doneBoard[i][j]) {
						check[k] = 0;
						break;
					}
					if (k == check.length - 1) return false;
				}
				//reset check array
			}
		}
		return true;
	}
	
	// wrapper method, checks if board is possible to solve
	public static boolean isLegal(Puzzle p) {
		return isPossible(p.getBoard());
	}
	// expectation: isComplete(doneBoard) == true
	public static boolean isLegal(int[][] newBoard) {
		return true; // needs implementation
	}
}
