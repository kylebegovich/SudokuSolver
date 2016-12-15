import java.util.Scanner;

/**
 * The class that displays data to the user, accepts inputs, and communicates
 * with controller
 * 
 * @date December 15, 2016
 * @author Kyle
 * @version 0.0
 */
public class View {
	private Controller controller;

	public View() {
	}

	public View(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Version 0.0-1.0 will be only terminal input / output
	 */
	private static final Scanner scanner = new Scanner(System.in);
	private static final String getSizeMessage = "Please enter the number of rows in your Sudoku puzzle";
	private static final String fillArrayMessage = "Please fill your Sudoku puzzle, row by row, as prompted, 0 for unknown";
	private static final String invalidInputMessage = "Invalid input! please try again";
	private static final String separator = " : ";

	// Prompt user for input Sudoku size
	public int getSudokuSize() {
		System.out.println(getSizeMessage);
		int temp = scanner.nextInt();
		//condition makes sure only square numbers are input
		while (Math.floor(Math.sqrt(temp)) != Math.sqrt(temp)) {
			System.out.println(invalidInputMessage);
			temp = scanner.nextInt();
		}
		return temp;
	}

	// Prompt user for input Sudoku puzzle
	public int[][] getInputArrayFromUser(int size) {
		int[][] array = new int[size][size];
		System.out.println(fillArrayMessage);
		for (int i = 0; i < size; i++) {
			System.out.println("Row" + (i + 1) + ": ");
			for (int j = 0; j < size; j++) {
				int temp = scanner.nextInt();
				while (temp < 0 || temp > size) {
					System.out.println(invalidInputMessage);
					temp = scanner.nextInt();
				}
				array[i][j] = temp;
			}
		}
		return array;
	}

	// TODO output data in an organized, readable fashion
	// output a visual representation of the Sudoku puzzle to the console
	public void output(String representation) {
		System.out.println(representation);
	}
}
