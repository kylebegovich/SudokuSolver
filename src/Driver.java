/**
 * The class that initializes the MVC
 * 
 * @author Kyle
 * @version 1.0
 */
public class Driver {

	public static void main(String[] args){
		Controller controller = new Controller();
		Model model = new Model(controller);
		View view = new View(controller);
		
		controller.addView(view);
		controller.addModel(model);
		
		controller.run();
	}
}
