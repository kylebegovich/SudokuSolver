package mvc;

import util.ArrayUtil;
import util.Checker;
import util.Tuple;

import java.util.ArrayList;


/**
 * The class that stores data and representations of the data
 *
 * @author Kyle Begovich
 * @version 1.5
 * @since February 14, 2017
 */
public class Model {
    private int[][] board;
    private int[][][] complexBoard;


    /**
     * Default constructor, not specifically important in this version
     */
    public Model() {
        super();
    }


    /**
     * Returns the board
     *
     * @return 2D int array representing the board
     */
    public int[][] getBoard() {
        return board;
    }


    /**
     * Sets the instance field board to the input board
     *
     * @param board The board to store the value of
     */
    public void setBoard(int[][] board) {
        this.board = board;
        complexBoard = new int[board.length][board.length][board.length];
        updateComplexBoard();
    }


    /**
     * Returns the complex board
     *
     * @return 3D int array representing the board and the available positions of
     */
    public int[][][] getComplexBoard() {
        return complexBoard;
    }


    // not sure if I will use this method yet, not sure when / where it would be applicable
    public void setComplexBoard(int[][][] complexBoard) {
        this.complexBoard = complexBoard;
    }


    /**
     * Updates the complex board with paired position consideration
     */
    public void updateComplexBoard() {
        int length = board.length;
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                complexBoard[row][col] = ArrayUtil.getAvailable(row, col, board);
            }
        }
    }// This is a mess at the moment
    public int[] complexUpdateComplexBoard(int row, int col, int length) {

        int boxSize = (int) Math.sqrt(length);
        int rowStart = row - (row % boxSize);
        int colStart = col - (col % boxSize);

        // local instance of complexBoard;
        int[] available = complexBoard[row][col];

        // length is the number of rows and columns that affect the box without intersecting (row, col)
        int arrLength = (((int) Math.sqrt(length)) - 1) * 2;

        // an array of all the row and column indices to check for partial positions
        int[] indexArray = new int[arrLength];
        int counter = 0;


        // first half of indexArray is row indices
        for (int r = rowStart; r < boxSize + rowStart; r++) {
            if (r != row) {
                indexArray[counter] = r;
                counter++;
            }
        }

        // second half of indexArray is col indices
        for (int c = colStart; c < boxSize + colStart; c++) {
            if (c != col) {
                indexArray[counter] = c;
                counter++;
            }
        }

        boolean needFirstPos = true;

        // checking rows for doubles
        needFirstPos = checkRowsForPairedDoubles(arrLength, needFirstPos);

        // checking columns for doubles
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
                        if (pairedValues != null) {
                            ArrayUtil.dealWithPairedPositions(firstLocation, secondPos, pairedValues);
                            needFirstPos = true;
                        }
                    }
                }
            }
        }

        return available;
    }

    private boolean checkRowsForPairedDoubles(int arrLength, boolean needFirstPos) {
        for (int r = 0; r < arrLength / 2; r++) {
            Tuple firstLocation = null;
            for (int index = 0; index < board.length; index++) {
                if (board[r][index] == 0) {
                    if (needFirstPos) {
                        firstLocation = new Tuple(r, index);
                        needFirstPos = false;
                    } else {
                        Tuple secondPos = new Tuple(r, index);

                        ArrayList<Integer> pairedValues = ArrayUtil.getPairedPositions(firstLocation, secondPos, complexBoard);
                        if (pairedValues != null) {
                            ArrayUtil.dealWithPairedPositions(firstLocation, secondPos, pairedValues);
                            needFirstPos = true;
                        }
                    }
                }
            }
        }
        return needFirstPos;
    }



    private boolean checkRowsForPairedPos(int arrLength, boolean needFirstPos) {
        for (int r = 0; r < arrLength / 2; r++) {
            Tuple firstLocation = null;
            for (int index = 0; index < board.length; index++) {
                if (board[r][index] == 0) {
                    if (needFirstPos) {
                        firstLocation = new Tuple(r, index);
                        needFirstPos = false;
                    } else {
                        Tuple secondPos = new Tuple(r, index);

                        ArrayList<Integer> pairedValues = ArrayUtil.getPairedPositions(firstLocation, secondPos, complexBoard);
                        if (pairedValues != null) {
                            ArrayUtil.dealWithPairedPositions(firstLocation, secondPos, pairedValues);
                            needFirstPos = true;
                        }
                    }
                }
            }
        }
        return needFirstPos;
    }

    // TODO write method similar to above, but for a set of 3 positions




    /**
     * Returns the value of the solved status of the board
     *
     * @return Boolean, whether the model is solved or not
     */
    public boolean isSolved() {
        return Checker.check(board);
    }


    /**
     * Overrides the default toString()
     * Gives a formatted string representation of the Sudoku puzzle
     *
     * @return String, the puzzle, but pretty
     */
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
