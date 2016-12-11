/**
 * This is the class used for storing the puzzle object 
 * to abstract away some of the complexity from Solver.java
 * 
 * @date Nov 13, 2016
 * @author Kyle
 * @version 0.0
 */
public class Puzzle {
	private int[][] board;
	
	public Puzzle() {
		super();
	}
	
	public Puzzle(int[][] newBoard) {
		setBoard(newBoard);
	}
	
	public void setBoard(int[][] board) {
		this.board = board;
	}

	public int[][] getBoard() {
		return board;
	}

	public boolean isNull() {
		return board == null;
	}

	public boolean isSolved() {
		return Checker.isLegal(board);
	}
	
	public String toString() {
		String output = "";
		for (int i = 0; i < board.length; i ++) {
			for (int j = 0; j < board[0].length; j ++) {
				output += board[i][j] + " ";
			}
			output += "\n";
		}
		return output;
	}
}
