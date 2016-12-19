/**
 * The class that initializes the MVC
 * 
 * @date December 19, 2016
 * @author Kyle
 * @version 0.0
 */
public class Driver {

	public static void main(String[] args) {
		// initializing the MVC structure
		Controller controller = new Controller();
		Model model = new Model(controller);
		View view = new View(controller);

		// connecting references
		controller.addView(view);
		controller.addModel(model);

		// does everything else
		controller.run(); 
	}
}
