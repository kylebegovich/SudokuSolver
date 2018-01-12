package util;

import java.util.ArrayList;

/**
 * Class to be used for static utility methods in arrays of various sizes and dimensions
 *
 * Static Class
 *
 * @since February 9, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class ArrayUtil {

    /**
     * initialize standard array to make checking possible
     * value = index + 1 for entire array
     *
     * @param length The length of the board
     * @return Array of ints, an array of numbers from 1 to length
     */
    public static int[] getStandardArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }


    /**
     * returns an array of the available numbers for a single location
     *
     * @param row The index of the row
     * @param col The index of the column
     * @param board The board
     * @return Array of ints, the possible numbers to place at the given position
     */
    public static int[] getAvailable(int row, int col, int[][] board) {
        int length = board.length;
        int[] available = getStandardArray(length);

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
        System.out.println("testing purposes: colStart = " + colStart +
                ", rowStart = " + rowStart + ", sqrt = " + ((int) Math.sqrt(length)));

        // box loop
        for (int r = rowStart; r < rowStart + Math.sqrt(length); r++) {
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


    /**
     *
     * @param board The board
     * @param row The index of the row to place at
     * @param col The index of the col to place at
     * @param neededNumber The number to input at the given location
     * @return Boolean, if you can place the number at the given location
     */
    public static boolean canPlace(int[][] board, int row, int col, int neededNumber) {
        // check if the spot is empty
        if (board[row][col] != 0) return false;

        // temp variables
        int length = board.length;
        int rowStart = (int) ((int) (row / Math.sqrt(length)) * Math.sqrt(length));
        int colStart = (int) ((int) (col / Math.sqrt(length)) * Math.sqrt(length));

        // row and column checks, respectively
        for (int i = 0; i < length; i ++) {
            if (board[row][i] == neededNumber) return false;
            if (board[i][col] == neededNumber) return false;
        }

        // box check
        for (int r = rowStart; r < rowStart + Math.sqrt(length); r++) {
            for (int c = colStart; c < colStart + Math.sqrt(length); c++) {
                if (board[r][c] == neededNumber) return false;
            }
        }

        // if it passes all row, column, and box checks, return true
        return true;
    }

    /**
     * Gives the paired positions, the integers that must go in one of the two given locations
     * so we can remove that integer from all of the available arrays in the same row or column
     *
     * @param firstPos Tuple of the index of the first location
     * @param secondPos Tuple of the index of the second location
     * @param complexBoard The board with available arrays
     * @return ArrayList, a list of the paired integers for the locations
     */
    public static ArrayList<Integer> getPairedPositions(Tuple firstPos, Tuple secondPos, int[][][] complexBoard) {
        int row1 = firstPos.FIRST_VALUE;
        int col1 = firstPos.SECOND_VALUE;
        int row2 = secondPos.FIRST_VALUE;
        int col2 = secondPos.SECOND_VALUE;

        // double check that the positions can be paired in some way
        if (row1 != row2 && col1 != col2) {
            return null;
        }


        boolean oneWayFlag = false;
        ArrayList<Integer> similar = new ArrayList<>();
        for (int avail1 : complexBoard[row1][col1]) {
            for (int avail2 : complexBoard[row2][col2]) {

                if (avail1 == avail2) {
                    oneWayFlag = true;
                    similar.add(avail1);
                }
            }
        }

        if (oneWayFlag) {
            int boxSize = (int) Math.sqrt(complexBoard.length);
            int boxRowStart = (row1 / boxSize) * boxSize;
            int boxColStart = (col1 / boxSize) * boxSize;

            // for the entire box
            for (int currRow = boxRowStart; currRow < boxRowStart + boxSize; currRow ++) {
                for (int currCol = boxColStart; currCol < boxColStart + boxSize; currCol ++) {

                    // removes all potential paired positions that aren't actually paired positions
                    if (cleanSimilar(complexBoard, row1, col1, row2, col2, -1, -1,similar, currRow, currCol)) {
                        return null;
                    }
                }
            }
        }

        if (similar.size() < 1) {
            return null;
        } else {
            return similar;
        }
    }

    /**
     * Similar to the method above, but with three positions instead of two
     *
     * @param firstPos Tuple of the index of the first location
     * @param secondPos Tuple of the index of the second location
     * @param thirdPos Tuple of the index of the third location
     * @param complexBoard The board with available arrays
     * @return ArrayList, a list of the paired integers for the locations
     */
    public static ArrayList<Integer> getPairedPositions(Tuple firstPos, Tuple secondPos, Tuple thirdPos, int[][][] complexBoard) {
        int row1 = firstPos.FIRST_VALUE;
        int col1 = firstPos.SECOND_VALUE;
        int row2 = secondPos.FIRST_VALUE;
        int col2 = secondPos.SECOND_VALUE;
        int row3 = thirdPos.FIRST_VALUE;
        int col3 = thirdPos.SECOND_VALUE;

        // double check that the positions can be paired in some way
        if (row1 != row2 && col1 != col2) {
            return null;
        }


        boolean oneWayFlag = false;
        ArrayList<Integer> similar = new ArrayList<>();
        for (int avail1 : complexBoard[row1][col1]) {
            for (int avail2 : complexBoard[row2][col2]) {
                for (int avail3 : complexBoard[row3][col3]) {

                    if (avail1 == avail2 && avail1 == avail3) {
                        oneWayFlag = true;
                        similar.add(avail1);
                    }
                }
            }
        }

        if (oneWayFlag) {
            int boxSize = (int) Math.sqrt(complexBoard.length);
            int boxRowStart = (row1 / boxSize) * boxSize;
            int boxColStart = (col1 / boxSize) * boxSize;

            // for the entire box
            for (int currRow = boxRowStart; currRow < boxRowStart + boxSize; currRow++) {
                for (int currCol = boxColStart; currCol < boxColStart + boxSize; currCol++) {

                    // removes all potential paired positions that aren't actually paired positions
                    if (cleanSimilar(complexBoard, row1, col1, row2, col2, row3, col3, similar, currRow, currCol)) {
                        return null;
                    }
                }
            }
        }

        if (similar.size() < 1) {
            return null;
        } else {
            return similar;
        }
    }


    /**
     * Utility function to abstract away complexity from getPairedPositions
     *
     * @param complexBoard the full array with potential values
     *
     * @param row1 row index of a potentially paired position
     * @param col1 column index of a potentially paired position
     *
     * @param row2 row index of a potentially paired position
     * @param col2 column index of a potentially paired position
     *
     * @param row3 row index of a potentially paired position
     * @param col3 column index of a potentially paired position
     *
     * @param similar current list of similar values
     * @param currRow current column index
     * @param currCol current row index
     *
     * @return true iff no paired positions are left in similar
     */
    private static boolean cleanSimilar(int[][][] complexBoard, int row1, int col1, int row2, int col2, int row3, int col3,
                                        ArrayList<Integer> similar, int currRow, int currCol) {

        // if the position isn't one of the ones passed in
        boolean isRow1 = (currRow == row1 && currCol == col1);
        boolean isRow2 = (currRow == row2 && currCol == col2);
        boolean isRow3 = (currRow == row3 && currCol == col3);
        if (!isRow1 && !isRow2 && !isRow3) {

            // if it's in the list of similar available values, remove it, as it's not exclusive
            for (int avail : complexBoard[currRow][currCol]) {
                if (similar.size() < 1) {

                    // no potential paired positions left in similar
                    return true;
                }
                for (int sim : similar) {
                    if (avail == sim) {
                        similar.remove(new Integer(avail));
                    }
                }
            }
        }
        return false;
    }

    /**
     * Will return correct assignment tags once implemented
     *
     * @param firstPos coordinates of first value
     * @param secondPos coordinates of second value
     * @param pairedValues which values at the location are paired with each other
     */
    public static void dealWithPairedPositions(Tuple firstPos, Tuple secondPos, ArrayList<Integer> pairedValues) {
        // TODO: implement
    }
}
