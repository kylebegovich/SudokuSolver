/**
 * The class that saves data states and performs computations
 * 
 * Also stores the board object to abstract away from Solver
 * 
 * @date December 14, 2016
 * @author Kyle
 * @version 0.0
 */
public class Model {
	private Controller controller;
	private int[][] board;

	public Model() {}
	
	public Model(Controller controller) {
		this.controller = controller;
	}
	
	public Model(Controller controller, int[][] board) {
		this.controller = controller;
		this.board = board;
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
