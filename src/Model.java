/**
 * The class that saves data states and performs computations
 * 
 * Also stores the board object to abstract away from Solver
 * 
 * @since January 3, 2016
 * @author Kyle Begovich
 * @version 0.0
 */
public class Model {
	protected int[][] board; // protected so solving classes have direct access

	public Model() {
		super();
	}

	public Model(int[][] board) {
		this.board = board;
	}

	// TODO delegate protected calls directly updating model.board to use this
	// method instead
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
		return Checker.check(board);
	}

	public String toString() {
		String output = "";
		int boxSize = (int) Math.sqrt(board.length);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				output += board[i][j] + " ";
				if (i + 1 % boxSize == 0)
					output += " ";
			}
			output += "\n";
			if (i + 1 % boxSize == 0)
				output += "\n";
		}
		return output;
	}
}
