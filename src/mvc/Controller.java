package mvc;

import solvingAlgorithms.Solver;

/**
 * The class that mediates communication between view and model
 * 
 * @since May 30, 2017
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


    /**
     * Starts program by making all necessary calls to
     * Model, View, and Solver
     */
	public void run() {
		int size = this.view.getSudokuSizeFromUser();
		if (size == 0) {
            int[][][] boards = this.view.loadBoardFromFile();

		} else {
            int[][] board = this.view.getBoardFromUser(size);

            this.model.setBoard(board);

            // this may take a while, calls solving algorithms
            Solver.solve(this.model);

            // ends program, mvc.View closes program with isSolved() as a parameter
            endSequence(this.model.toString(), this.model.isSolved());
		}
	}


    /**
     * Last call in the controller before handing off control, all solving is done
     *
     * @param modelRepresentation A String representation of the puzzle
     * @param isSolved Boolean, if the puzzle got solved
     */
	public void endSequence(String modelRepresentation, boolean isSolved) {
		this.view.output(modelRepresentation, isSolved);
	}
}
