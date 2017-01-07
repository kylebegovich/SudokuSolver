/**
 * The class that initializes the MVC, delegates work
 * 
 * @since January 3, 2017
 * @author Kyle Begovich
 * @version 1.0
 */
public class Driver {

	public static void main(String[] args) {
		// initializing the MVC structure
		Controller controller = new Controller();
		Model model = new Model();
		View view = new View();

		// connecting references
		controller.addView(view);
		controller.addModel(model);

		// does everything else
		controller.run();
	}
}
