/**
 * The class that stores data and representations of the data
 * 
 * @since January 27, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class Model {
	private int[][] board;
	private int[][][] complexBoard;

	public Model() {
		super();
	}


	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
		complexBoard = new int[board.length][board.length][board.length];
		updateComplexBoard();
	}


	public int[][][] getComplexBoard() {
		return complexBoard;
	}

	// not sure if I will use this method yet, not sure when / where it would be applicable
	public void setComplexBoard(int[][][] complexBoard) {
		this.complexBoard = complexBoard;
	}

    public void updateComplexBoard() {
        int length = board.length;
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                complexBoard[row][col] = getAvailable(row, col, length);
            }
        }
    }


	// Simple version for each individual position
	public int[] getAvailable(int row, int col, int length) {
		int[] available = ArrayUtil.getStandardArray(length);

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

	public int[] getAvailableComplex(int row, int col, int length) {

        int boxSize = (int) Math.sqrt(length);
	    int rowStart = row - (row % boxSize);
	    int colStart = col - (col % boxSize);

		// local instance of complexBoard;
        int[] available = complexBoard[row][col];

		// length is the number of rows and columns that affect the box without intersecting (row, col)
		int arrLength = (((int)Math.sqrt(length)) - 1) * 2;

		// an array of all the row and column indices to check for partial positions
		int[] indexArray = new int[ arrLength ];
		int i = 0;


		// first half of indexArray is row indices
        for (int r = rowStart; r < boxSize + rowStart; r ++) {
            if (r != row) {
                indexArray[i] = r;
                i ++;
            }
        }

        // second half of indexArray is col indices
        for (int c = colStart; c < boxSize + colStart; c ++) {
            if (c != col) {
                indexArray[i] = c;
                i ++;
            }
        }

        // TODO remove once confident this condition is met
        if (i != arrLength) return null;





        return available;
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
