/**
 * The class that mediates communication between view and model
 * 
 * @date December 15, 2016
 * @author Kyle
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
			endSequence();
		} else {
			Solver.solve(this.model); // this may take a while while running
			if (this.model.isSolved()) {
				endSequence();
			} else {
				errorSequence();
			}
		}

	}

	public void endSequence() {
		// TODO make call to View that sends the solved puzzle
		// and a trigger to stop the program
	}

	public void errorSequence() {
		// TODO make call to View that sends the un/partially solved puzzle
		// back to the user along with an error message
	}

	// TODO write communication helper methods here

}
