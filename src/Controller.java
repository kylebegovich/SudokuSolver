/**
 * The class that mediates communication between view and model
 * 
 * @since December 22, 2016
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
		if (this.model.isSolved()) {
			endSequence(this.model.toString());
		} else {
			Solver.solve(this.model); // this may take a while whilst running
			if (this.model.isSolved()) {
				endSequence(this.model.toString());
			} else {
				errorSequence(this.model.toString());
			}
		}

	}
	
	// makes a call to View that stops the program, solved model
	public void endSequence(String modelRepresentation) {
		this.view.output(modelRepresentation, true);
	}

	// makes a call to View that stops the program, unsolved model
	public void errorSequence(String modelRepresentation) {
		this.view.output(modelRepresentation, false);
	}
}
