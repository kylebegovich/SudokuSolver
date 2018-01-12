package mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	private static final String INTRO_MSG = "Enter the number of rows in your Sudoku puzzle. Enter \"0\" for from file";
	private static final String FILL_ARRAY_MESSAGE = "Fill your Sudoku puzzle, row by row, as prompted, 0 for unknown";
	private static final String INVALID_INPUT_MESSAGE = "Invalid input! please try again";
	private static final String SOLVED_MESSAGE = "Your puzzle was solved!";
	private static final String ERROR_MESSAGE = "Sorry, your puzzle was not possible to solve";
	private static final String FROM_FILE_MSG = "Loading Puzzles from file";

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
		System.out.println(INTRO_MSG);
		int temp = SCANNER.nextInt();

		// condition makes sure only square numbers are input
		while (temp != 0 && Math.floor(Math.sqrt(temp)) != Math.sqrt(temp)) {
			System.out.println(INVALID_INPUT_MESSAGE);
			temp = SCANNER.nextInt();
		}

		if (temp == 0) {
			System.out.println(FROM_FILE_MSG);
		}

		return temp;
	}


	/**
	 * Prompt user for input Sudoku puzzle, return that puzzle
	 *
	 * @param size The length of one side of the puzzle
	 * @return A 2D integer array that stores the value of the board
	 */
	public int[][] getBoardFromUser(int size) {
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
	 */
	public void output(String representation, boolean solved) {
		if (solved) {
			System.out.println(SOLVED_MESSAGE);
			System.out.println();
			System.out.println(representation + "\n");
		} else {
			System.out.println(ERROR_MESSAGE);
			System.out.println();
			System.out.println(representation + "\n");
		}
	}

	public int[][][] loadBoardFromFile() {
		String fileName = "puzzles.txt";

		// hard coded for now, could parameterize but am lazy
		int[][][] puzzles = new int[50][9][9];
		String tempLine;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader =
					new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader =
					new BufferedReader(fileReader);

			int puzzle = 0;
			int row = 0;
			while((tempLine = bufferedReader.readLine()) != null) {
				if (!tempLine.contains("Grid")) {
					if (tempLine.isEmpty()) {
                        puzzle ++;
                        row = 0;
                    } else {
						System.out.println(tempLine);
                        String[] stringArray = tempLine.split(" ");
                        puzzles[puzzle][row] = new int[stringArray.length];
                        for (int i = 0; i < stringArray.length; i++) {
                            String numberAsString = stringArray[i];
                            puzzles[puzzle][row][i] = Integer.parseInt(numberAsString);
                        }
                    }
				} else {
					System.out.println(tempLine);
				}
			}

			// Always close files.
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '" +
							fileName + "'");
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading file '"
							+ fileName + "'");
		}

		return puzzles;
	}
}
