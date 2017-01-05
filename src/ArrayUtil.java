/**
 * Class to be used for static utility methods in arrays of various sizes and dimensions
 *
 * Static Class
 *
 * @since January 5, 2017
 * @author Kyle Begovich
 * @version unreleased
 */
public class ArrayUtil {
    // Methods replacing ones already in place in other classes

    // initialize standard array to make checking possible
    // value = index + 1 for entire array
    public static int[] getStandardArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }


    //Utility methods I'm not sure I'll need yet

    // TODO method description
    public static void iterateRow(int rowIndex) {
        // TODO implement
    }

    // TODO method description
    public static void iterateCol(int colIndex) {
        // TODO implement
    }

    // TODO method description
    public static void iterateBox(int rowIndex, int colIndex) {
        // TODO implement
    }
}
