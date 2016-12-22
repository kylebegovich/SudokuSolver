import java.util.Scanner;

/**
 * The class that displays data to the user, accepts inputs, and communicates
 * with controller
 * 
 * @since December 22, 2016
 * @author Kyle Begovich
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
	 * Version 0.0 will be only terminal input / output
	 */
	private static final Scanner scanner = new Scanner(System.in);
	private static final String getSizeMessage = "Please enter the number of rows in your Sudoku puzzle";
	private static final String fillArrayMessage = "Please fill your Sudoku puzzle, row by row, as prompted, 0 for unknown";
	private static final String invalidInputMessage = "Invalid input! please try again";
	private static final String solvedMessage = "Your Sudoku puzzle was solved! \n";
	private static final String errorMessage = "Sorry, your sudoku puzzle was not possible to solve \n";

	// Prompt user for input Sudoku size
	public int getSudokuSize() {
		System.out.println(getSizeMessage);
		int temp = scanner.nextInt();
		// condition makes sure only square numbers are input
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

	// output a visual representation of the Sudoku puzzle to the console,
	// along with a message to the user
	public void output(String representation, boolean solved) {
		if (solved) {
			System.out.println(solvedMessage);
			System.out.println(representation);
		} else {
			System.out.println(errorMessage);
			System.out.println(representation);
		}
	}
}
