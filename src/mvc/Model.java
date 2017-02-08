package mvc;

import util.ArrayUtil;
import util.Checker;
import util.Tuple;

import java.util.ArrayList;


/**
 * The class that stores data and representations of the data
 * 
 * @since February 6, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class Model {
	private int[][] board;
	private int[][][] complexBoard;
	// TODO don't want to store these values, trying to deal with them live with an ArrayUtil method
    //private ArrayList<Integer> pairedPositions;
    //private Tuple pair;

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
                complexBoard[row][col] = ArrayUtil.getAvailable(row, col, board);
            }
        }
    }

	public int[] complexUpdateComplexBoard(int row, int col, int length) {

        int boxSize = (int) Math.sqrt(length);
	    int rowStart = row - (row % boxSize);
	    int colStart = col - (col % boxSize);

		// local instance of complexBoard;
        int[] available = complexBoard[row][col];

		// length is the number of rows and columns that affect the box without intersecting (row, col)
		int arrLength = (((int)Math.sqrt(length)) - 1) * 2;

		// an array of all the row and column indices to check for partial positions
		int[] indexArray = new int[ arrLength ];
		int counter = 0;


		// first half of indexArray is row indices
        for (int r = rowStart; r < boxSize + rowStart; r ++) {
            if (r != row) {
                indexArray[counter] = r;
				counter ++;
            }
        }

        // second half of indexArray is col indices
        for (int c = colStart; c < boxSize + colStart; c ++) {
            if (c != col) {
                indexArray[counter] = c;
				counter ++;
            }
        }

		boolean needFirstPos = true;

        // checking rows
		for (int r = 0; r < arrLength / 2; r ++) {
			Tuple firstLocation = null;
			for (int index = 0; index < board.length; index++) {
				if (board[r][index] == 0) {
					if (needFirstPos) {
						firstLocation = new Tuple(r, index);
                        needFirstPos = false;
					} else {
						Tuple secondPos = new Tuple(r, index);
						ArrayList<Integer> pairedValues = ArrayUtil.getPairedPositions(firstLocation, secondPos, complexBoard);
						ArrayUtil.dealWithPairedPositions(firstLocation, secondPos, pairedValues);
                        needFirstPos = true;
					}
				}
			}
		}

		// checking columns
		for (int c = arrLength / 2 + 1; c < arrLength; c++) {
			Tuple firstLocation = null;
			for (int index = 0; index < board.length; index++) {
				if (board[index][c] == 0) {
					if (needFirstPos) {
						firstLocation = new Tuple(index, c);
						needFirstPos = false;
					} else {
						Tuple secondPos = new Tuple(index, c);
						ArrayList<Integer> pairedValues = ArrayUtil.getPairedPositions(firstLocation, secondPos, complexBoard);
						ArrayUtil.dealWithPairedPositions(firstLocation, secondPos, pairedValues);
						needFirstPos = true;
					}
				}
			}
		}


        return available;
	}

    // TODO write method similar to above, but for a set of 3 positions


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
