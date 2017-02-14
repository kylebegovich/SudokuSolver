package mvc;

// TODO replace this import with the wrapper for the solving algorithms, once it exists
import solvingAlgorithms.Solver;

/**
 * The class that mediates communication between view and model
 * 
 * @since February 14, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class Controller {
	private View view;
	private Model model;

	/**
	 * Default constructor, not specifically important in this version
	 */
	public Controller() {
		super();
	}


    /**
     * Sets the instance field, view
     *
     * @param view A View object
     */
	public void setView(View view) {
		this.view = view;
	}


    /**
     * Sets the instance field, model
     *
     * @param model A View object
     */
	public void setModel(Model model) {
		this.model = model;
	}


    /**
     * For future use, if necessary
     *
     * @param view A View object
     * @param model A Model object
     */
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	// starts program

    /**
     * Starts program by making all necessary calls to
     * Model, View, and Solver to give the desired behavior
     */
	public void run() {
		int size = this.view.getSudokuSizeFromUser();
		int[][] board = this.view.getInputArrayFromUser(size);
		this.model.setBoard(board);

		// this may take a while, calls solving algorithm(s)
		Solver.solve(this.model);

		// ends program, mvc.View closes program with isSolved() as a parameter
		endSequence(this.model.toString(), this.model.isSolved(), (size == 0));
	}


	// makes a call to mvc.View that stops the program

    /**
     * Used here as a framework for when more complex outputs will be added
     *
     * @param modelRepresentation A String representation of the puzzle
     * @param isSolved Boolean, if the puzzle got solved
     * @param easterEgg Boolean, is a secret ;)
     */
	public void endSequence(String modelRepresentation, boolean isSolved, boolean easterEgg) {
		this.view.output(modelRepresentation, isSolved, easterEgg);
	}
}
