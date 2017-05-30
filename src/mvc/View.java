package mvc;

import java.util.Scanner;

/**
 * The class that displays data to the user, accepts inputs, and communicates
 * with controller
 * 
 * @since May 30, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class View {

	private static final Scanner SCANNER = new Scanner(System.in);
	private static final String GET_SIZE_MESSAGE = "Enter the number of rows in your Sudoku puzzle";
	private static final String FILL_ARRAY_MESSAGE = "Fill your Sudoku puzzle, row by row, as prompted, 0 for unknown";
	private static final String INVALID_INPUT_MESSAGE = "Invalid input! please try again";
	private static final String SOLVED_MESSAGE = "Your puzzle was solved!";
	private static final String ERROR_MESSAGE = "Sorry, your puzzle was not possible to solve";
	private static final String TINY_PUZZLE_EASTER_EGG = " (Well that was easy)";

    /**
     * Default constructor, not specifically important in this version
     */
	public View() {
		super();
	}


	/**
	 * Prompt user for input Sudoku size
     *
	 * @return The length of one side of the puzzle
	 */
	public int getSudokuSizeFromUser() {
		System.out.println(GET_SIZE_MESSAGE);
		int temp = SCANNER.nextInt();

		// condition makes sure only square numbers are input
		while (Math.floor(Math.sqrt(temp)) != Math.sqrt(temp)) {
			System.out.println(INVALID_INPUT_MESSAGE);
			temp = SCANNER.nextInt();
		}
		return temp;
	}


	/**
	 * Prompt user for input Sudoku puzzle, return that puzzle
	 *
	 * @param size The length of one side of the puzzle
	 * @return A 2D integer array that stores the value of the board
	 */
	public int[][] getInputArrayFromUser(int size) {
		int[][] array = new int[size][size];
		System.out.println(FILL_ARRAY_MESSAGE);
		for (int i = 0; i < size; i++) {
			System.out.println("Row" + (i + 1) + ": ");
			for (int j = 0; j < size; j++) {
				int temp = SCANNER.nextInt();
				while (temp < 0 || temp > size) {
					System.out.println(INVALID_INPUT_MESSAGE);
					temp = SCANNER.nextInt();
				}
				array[i][j] = temp;
			}
		}
		return array;
	}


	/**
	 * Output a visual representation of the Sudoku puzzle to the console,
	 * along with a message to the user
	 *
	 * @param representation The String representation of the puzzle
	 * @param solved If the puzzle was solved or not
	 * @param easterEgg Fun secret boolean
	 */
	public void output(String representation, boolean solved, boolean easterEgg) {
		if (easterEgg) {
			System.out.println(SOLVED_MESSAGE + TINY_PUZZLE_EASTER_EGG);
			System.out.println();
			System.out.println(representation + "\n");
		} else if (solved) {
			System.out.println(SOLVED_MESSAGE);
			System.out.println();
			System.out.println(representation + "\n");
		} else {
			System.out.println(ERROR_MESSAGE);
			System.out.println();
			System.out.println(representation + "\n");
		}
	}
}
