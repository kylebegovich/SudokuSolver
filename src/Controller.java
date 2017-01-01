/**
 * The class that mediates communication between view and model
 * 
 * @since January 1, 2017
 * @author Kyle Begovich
 * @version 0.0
 */
public class Controller {
	private View view;
	private Model model;

	// default constructor
	public Controller() {
	}

	// write methods for initialization
	public void addView(View view) {
		this.view = view;
	}

	public void addModel(Model model) {
		this.model = model;
	}

	// constructor if the view and model already exist
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	// starts program
	public void run() {
		int size = this.view.getSudokuSize();
		int[][] board = this.view.getInputArrayFromUser(size);
		this.model.setBoard(board);

		// this may take a while, calls solving algorithms
		Solver.solve(this.model);

		// ends program, View closes program with isSolved() as a parameter
		endSequence(this.model.toString(), this.model.isSolved());

	}

	// makes a call to View that stops the program
	public void endSequence(String modelRepresentation, boolean isSolved) {
		this.view.output(modelRepresentation, isSolved);
	}
}
