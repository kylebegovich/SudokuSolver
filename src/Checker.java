/**
 * This is the class used to check if a puzzle is:
 * 	1) full, as in there is no empty spaces, and
 *  2) legal, as in each row, column, and box has exactly one of each number
 *  
 *  Force pass-in of the board every time as to make sure
 *  it is completely updated every time it's checked
 * 
 * @date December 12, 2016
 * @author Kyle
 * @version 0.0
 */
public class Checker {
	private int[] check;
	

	public Checker(int length) {
		this.check = new int[length];
	}
	
	// wrapper method for the entirety of the class
	public boolean check(Model model) {
		return check(model.getBoard());
	}
	public boolean check(int[][] board) {
		return isFull(board) && isLegal(board);
	}
	
	
	// wrapper method, checks no empty spaces
	public boolean isFull(Model model) {
		return isFull(model.getBoard());
	}
	public boolean isFull(int[][] doneBoard) {
		for (int i = 0; i < doneBoard.length; i ++) {
			for (int j = 0; j < doneBoard[0].length; j ++) {
				if (doneBoard[i][j] < 1 || doneBoard[i][j] > doneBoard.length) return false;
			}
		}
		return true;
	}
	
	// wrapper method, checks if solution is genuine
	public boolean isLegal(Model model) {
		return isLegal(model.getBoard());
	}
	public boolean isLegal(int[][] board) {		
		for (int i = 0; i < board.length; i ++) {
			
			//check legality for each row
			for (int j = 0; j < board[0].length; j ++) {
				for (int k = 0; k < this.check.length; k ++) {
					if (this.check[k] == board[i][j]) {
						this.check[k] = 0;
						k = this.check.length;
					}
				}
			}
			//check that each value in check got set to 0 above
			for (int j = 0; j < check.length; j ++) {
				if (this.check[j] != 0) return false;
			}
			//reset check array
			resetCheckArray();
			
			
			//check legality for each column
			for (int j = 0; j < board[0].length; j ++) {
				for (int k = 0; k < this.check.length; k ++) {
					if (this.check[k] == board[j][i]) { //note: i <--> j
						this.check[k] = 0;
						k = this.check.length;
					}
				}
			}
			//check that each value in check got set to 0 above
			for (int j = 0; j < check.length; j ++) {
				if (this.check[j] != 0) return false;
			}
			//reset check array
			resetCheckArray();
		}
		
		
		
		//check legality for each box
		// TODO implementation
		
		
		return true;
	}
	
	//re-initialize check array to make testing possible
	public void resetCheckArray() {
		for (int i = 0; i < check.length; i ++) {
			check[i] = i + 1;
		}
	}
}
