/**
 * The class that stores data and representations of the data
 * 
 * @since January 14, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class Model {
	// protected so solving classes have direct access for now
	private int[][] board;
	private int[][][] complexBoard;

	public Model() {
		super();
	}

	public Model(int[][] board) {
		this.board = board;

		// initialize complexBoard with an available array.
		for (int row = 0; row < board.length; row ++) {
            for (int col = 0; col < board.length; col ++) {
                complexBoard[row][col] = updateAvailable(row, col, board.length);
            }
        }
	}
	
	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public int[][][] getComplexBoard() {
		return complexBoard;
	}

	public void setComplexBoard(int[][][] complexBoard) {
		this.complexBoard = complexBoard;
	}

	public boolean isSolved() {
		return Checker.check(board);
	}

	public int[] updateAvailable(int row, int col, int length) {
		// modified from this previous call: getNewAvailableArray(length);
		int[] available = ArrayUtil.getStandardArray(length);
		System.out.println("testing purposes: updateAvailable(): col = " + col + ", row = " + row);

		// row loop
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (board[i][col] == available[j]) {
					available[j] = -1;
				}
			}
		}
		// column loop
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (board[row][i] == available[j]) {
					available[j] = -1;
				}
			}
		}

		// used to offset the location of the current square within the box
		int rowStart = (int) ((int) (row / Math.sqrt(length)) * Math.sqrt(length));
		int colStart = (int) ((int) (col / Math.sqrt(length)) * Math.sqrt(length));
		System.out.println("testing purposes: colStart = " + colStart + ", rowStart = " + rowStart + ", sqrt = "
				+ Math.sqrt(length));
		// box loop
		for (int r = rowStart; r < rowStart + Math.sqrt(length); r++) {
			// insert c++ joke here
			for (int c = colStart; c < colStart + Math.sqrt(length); c++) {
				for (int j = 0; j < length; j++) {
					System.out.println("testing purposes: r = " + r + ", c = " + c + ", j = " + j);
					if (board[r][c] == available[j]) {
						available[j] = -1;
					}
				}
			}
		}

		return available;
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
