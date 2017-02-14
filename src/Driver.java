import mvc.*;

/**
 * The class that initializes the MVC, delegates work
 * 
 * @since February 13, 2017
 * @author Kyle Begovich
 * @version 1.5
 */
public class Driver {

	/**
	 * Main method to initialize the MVC
     *
	 * @param args Any array, not used at all
	 */
	public static void main(String[] args) {

		// initializing the MVC structure
		Controller controller = new Controller();
		Model model = new Model();
		View view = new View();

		// connecting references
		controller.addView(view);
		controller.addModel(model);

		controller.run();
	}
}
