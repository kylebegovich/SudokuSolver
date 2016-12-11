/**
 * This is the driver class, used to bring the rest of the classes together
 * 
 * @date Nov 13, 2016
 * @author Kyle
 * @version 0.0
 */
public class Driver {

	public static void main(String[] args) {
		Puzzle puzz = new Puzzle(problem); // to be replaced with input system
		
		if (!Checker.isPossible(puzz)) System.out.println("Puzzle not possible!!!");
		else {
			puzz = Solver.solve(puzz);
			if (Checker.isLegal(puzz)) System.out.println("Puzzle solved! Here it is: ");
			System.out.println(puzz.toString());
		}
	}
	
	
	
	/** 
	 * This is where we'll store some of the easy example puzzles
	 * to check quickly without developing the I/O system yet
	 */
	
	private static int[][] problem =
		   {{0,0,0,0},
		    {3,4,1,2},
		    {2,1,4,3},
		    {4,3,2,1}};

//	private int[][] problemB =
//		   {{0,2,3,4},
//		    {0,4,1,2},
//		    {0,1,4,3},
//		    {0,3,2,1}};
//	
//	private int[][] problemC =
//		   {{0,0,3,4},
//		    {0,0,1,2},
//		    {2,1,4,3},
//		    {4,3,2,1}};
//	
// private int[][] solution =
//		   {{1,2,3,4},
//			{3,4,1,2},
//			{2,1,4,3},
//			{4,3,2,1}};
}
