package util;

import java.util.ArrayList;

/**
 * Class to be used for static utility methods in arrays of various sizes and dimensions
 *
 * Static Class
 *
 * @since February 6, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class ArrayUtil {
    // Methods replacing ones already in place in other classes

    /*
     * initialize standard array to make checking possible
     * value = index + 1 for entire array
     */
    public static int[] getStandardArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    // returns an array of the available numbers for a single location
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
                ", rowStart = " + rowStart + ", sqrt = " + Math.sqrt(length));

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

                    // if the position isn't one of the ones passed in
                    if ((currRow != row1 || currCol != col1) && (currRow != row2 || currCol != col2)) {

                        // if it's in the list of similar available values, remove it, as it's not exclusive
                        for (int avail : complexBoard[currRow][currCol]) {
                            if (similar.size() < 1) {
                                break;
                            }
                            for (int sim : similar) {
                                if (avail == sim) {
                                    similar.remove(new Integer(avail));
                                }
                            }
                        }
                    }

                }
            }
        }
        return similar;
    }

    public static ArrayList<Integer> getPairedPositions(Tuple firstPos, Tuple secondPos, Tuple thirdPos, int[][][] complexBoard) {
        int row1 = firstPos.FIRST_VALUE;
        int col1 = firstPos.SECOND_VALUE;
        int row2 = secondPos.FIRST_VALUE;
        int col2 = secondPos.SECOND_VALUE;
        int row3 = thirdPos.FIRST_VALUE;
        int col3 = thirdPos.SECOND_VALUE;

        // TODO make this do what the other one does :)

        return null;
    }

    public static void dealWithPairedPositions(Tuple firstPos, Tuple secondPos, ArrayList<Integer> pairedValues) {

    }
}
