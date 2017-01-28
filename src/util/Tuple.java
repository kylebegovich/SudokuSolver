package util;

/**
 * util.Tuple data structure, as implemented for use in SophisticatedSolver.java
 *
 * @since January 28, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class Tuple {
    private int firstValue;
    private int secondValue;

    public Tuple (int firstValue, int secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public void setFirstValue(int firstValue) {
        this.firstValue = firstValue;
    }

    public void setSecondValue(int secondValue) {
        this.secondValue = secondValue;
    }
}
