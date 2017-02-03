package util;

import java.util.ArrayList;

/**
 * Class to be used for static utility methods in arrays of various sizes and dimensions
 *
 * Static Class
 *
 * @since February 2, 2017
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

    public static boolean isPairedPosition(int[][][] complexBoard, Tuple firstPos, Tuple secondPos) {
        /* TODO
         *  determine which box these values are within in order to
         *  extrapolate that they are the only positions with the available value
         */


        int row1 = firstPos.FIRST_VALUE;
        int col1 = firstPos.SECOND_VALUE;
        int row2 = secondPos.FIRST_VALUE;
        int col2 = secondPos.SECOND_VALUE;


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
            int bowRowStart = (row1 / boxSize) * boxSize;
            int bowColStart = (col1 / boxSize) * boxSize;

            // TODO implement the check for exclusivity of the paired number stored in similar
        }



        // TODO make this function return true iff there is a commonality of available numbers between the two positions

        
        return false;
    }

    public static boolean isPairedPosition(int[][][] complexBoard, Tuple firstPos, Tuple secondPos, Tuple thirdPos) {
        int row1 = firstPos.FIRST_VALUE;
        int col1 = firstPos.SECOND_VALUE;
        int row2 = secondPos.FIRST_VALUE;
        int col2 = secondPos.SECOND_VALUE;
        int row3 = thirdPos.FIRST_VALUE;
        int col3 = thirdPos.SECOND_VALUE;
        /* TODO
         *  determine which box these values are within in order to
         *  extrapolate that they are the only positions with the available value
         */


        // TODO make this function return true iff there is a commonality of available numbers between the two positions


        return false;
    }
}
