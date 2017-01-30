package util;

/**
 * util.Tuple data structure, as implemented for use in SophisticatedSolver.java
 *
 * IMMUTABLE
 *
 * @since January 29, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class Tuple {
    public final int FIRST_VALUE;
    public final int SECOND_VALUE;

    public Tuple (int firstValue, int secondValue) {
        this.FIRST_VALUE = firstValue;
        this.SECOND_VALUE = secondValue;
    }
}
