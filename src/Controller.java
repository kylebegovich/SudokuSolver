/**
 * The class that mediates communication between
 * view and model
 * 
 * @date December 12, 2016
 * @author Kyle
 * @version 0.0
 */
public class Controller {
	private View view;
	private Model model;

	//default constructor
	public Controller() {}
	
	//write methods for initialization
	public void addView(View view) {
		this.view = view;
	}
	public void addModel(Model model) {
		this.model = model;
	}
	
	//constructor if the view and model already exist
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}
	
	//starts program
	public void run() {
		// TODO delegate to view and model to perform operations
	}
	
	// TODO write communication methods here

}
