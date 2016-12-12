//Old solver.java:
//
//
//
//
//public void recursiveSolve(int i, int j) {
//		
//		if (j == COLUMNS) {
//			if (i == ROWS - 1) return;
//			else recursiveSolve(i + 1, 0);
//		}
//		
//		if (problem[i][j] != 0) {	
//			recursiveSolve(i, j + 1);
//		}
//		
//		if (simpleSolver(i, j)) {
//			return;
//		}
//		
//		complexSolver(i, j);
//		return;
//	}
//	
//	public void complexSolver(int i, int j) {
//		for (int r = 0; r < Math.sqrt(ROWS); r ++) {
//			
//		}
//	}
//	
//	public boolean simpleSolver(int i, int j) {
//		int acc = 0;
//		for (int r = 0; r < ROWS; r ++) {
//			if (problem[i][r] == 0) acc ++;
//		}
//		if (acc == 1) {
//			problem[i][j] = findMissingNumRow(i);
//			recursiveSolve(i + 1, 0);
//		}
//		if (acc != 1) {
//			
//			acc = 0;
//			for (int c = 0; c < COLUMNS; c ++) {
//				if (problem[c][j] == 0) acc ++;
//			}
//			if (acc == 1) {
//				problem[i][j] = findMissingNumColumn(j);
//				recursiveSolve(i, j + 1);
//			}
//		}
//		if (acc != 1) {
//			
//			acc = 0;
//			for (int r2 = 0; r2 < Math.sqrt(ROWS); r2 ++) {
//				for (int c2 = 0; c2 < Math.sqrt(COLUMNS); c2 ++) {
//					if (problem[i + r2][j + c2] == 0) acc ++;
//				}
//			}
//			if (acc == 1) {
//				problem[i][j] = findMissingNumBox(i, j);
//				recursiveSolve(i + 1, j);
//			}
//		}
//		
//		return acc == 1 ? true : false;
//	}
//	
//	public int findMissingNumRow(int r) {
//		int[] row = new int[COLUMNS];
//		for (int i = 0; i < row.length; i ++) {
//			row[i] = problem[r][i];
//		}
//		int[] blank = new int[ROWS];
//		for (int j = 0; j < row.length; j ++) {
//			blank[j] = j;
//		}
//		
//		for (int k = 0; k < row.length; k ++) {
//			for (int l = 0; l < blank.length; l ++) {
//				if (row[k] == blank[l]) {
//					blank[l] = 0;
//					break;
//				}
//			}
//		}
//		for (int m = 0; m < blank.length; m ++) {
//			if (blank[m] != 0) {
//				return blank[m];
//			}
//		}
//		return -1;
//	}
//	
//	public int findMissingNumColumn(int c) {
//		int[] col = new int[ROWS];
//		for (int i = 0; i < col.length; i ++) {
//			col[i] = problem[i][c];
//		}
//		int[] blank = new int[ROWS];
//		for (int j = 0; j < col.length; j ++) {
//			blank[j] = j;
//		}
//		
//		for (int k = 0; k < col.length; k ++) {
//			for (int l = 0; l < blank.length; l ++) {
//				if (col[k] == blank[l]) {
//					blank[l] = 0;
//					break;
//				}
//			}
//		}
//		for (int m = 0; m < blank.length; m ++) {
//			if (blank[m] != 0) {
//				return blank[m];
//			}
//		}
//		return -1;
//	}
//	
//	public int findMissingNumBox(int r, int c) {
//		int[] box = new int[ROWS];
//		if (r % 3 == 2) r --;
//		if (r % 3 == 1) r --;
//		if (c % 3 == 2) c --;
//		if (c % 3 == 1) c --;
//		
//		for (int r2 = r; r2 < r + Math.sqrt(ROWS); r2 ++) {
//			for (int c2 = c; c2 < c + Math.sqrt(COLUMNS); c2 ++) {
//				box[3 * r2 + c2] = problem[r2][c2];
//			}
//		}
//		
//		int[] blank = new int[ROWS];
//		for (int j = 0; j < box.length; j ++) {
//			blank[j] = j;
//		}
//		for (int k = 0; k < box.length; k ++) {
//			for (int l = 0; l < blank.length; l ++) {
//				if (box[k] == blank[l]) {
//					blank[l] = 0;
//					break;
//				}
//			}
//		}
//		for (int m = 0; m < blank.length; m ++) {
//			if (blank[m] != 0) {
//				return blank[m];
//			}
//		}
//		return -1;
//	}
//	
//	//will print out a reader-friendly version of the given 2D array
//	public void print(int[][] a) {
//		
//		for (int i = 0; i < ROWS; i ++) {
//			for (int j = 0; j < COLUMNS; j ++) {
//				System.out.print(a[i][j] + "");
//				if (j % 3 == 2) System.out.print(" ");
//				
//			}
//			System.out.println("");
//			if (i % 3 == 2) System.out.println("");
//		}
//		System.out.println("");
//	}
//	
//	//checks to see if the arrays p and a match, returns boolean
//	public boolean check(int[][] p, int[][] a) {
//		
//		for (int i = 0; i < ROWS; i ++) {
//			for (int j = 0; j < COLUMNS; j ++) {
//				
//				if (p[i][j] != a[i][j]) return false;
//			}
//		}
//		return true;
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	/**
//	
//	//This block of code is where you would input a puzzle to solve
//	//0 means it is unknown
//	//Manually done for now (V 1.0)
//	private int[][] problem =
//	   {{0,0,0,  0,0,0,  0,0,0},
//		{4,5,6,  7,8,9,  1,2,3},
//		{7,8,9,  1,2,3,  4,5,6},
//		
//		{2,3,4,  5,6,7,  8,9,1},
//		{5,6,7,  8,9,1,  2,3,4},
//		{8,9,1,  2,3,4,  5,6,7},
//		
//		{3,4,5,  6,7,8,  9,1,2},
//		{6,7,8,  9,1,2,  3,4,5},
//	    {9,1,2,  3,4,5,  6,7,8}};
//	
//	
//	//This block of code is where you would input the sudoku's solution
//	//Manually done for now (V 1.0)
//	private int[][] solved = 
//	   {{0,0,0,  0,0,0,  0,0,0},
//		{4,5,6,  7,8,9,  1,2,3},
//		{7,8,9,  1,2,3,  4,5,6},
//			
//		{2,3,4,  5,6,7,  8,9,1},
//		{5,6,7,  8,9,1,  2,3,4},
//		{8,9,1,  2,3,4,  5,6,7},
//			
//		{3,4,5,  6,7,8,  9,1,2},
//		{6,7,8,  9,1,2,  3,4,5},
//		{9,1,2,  3,4,5,  6,7,8}};
//	
//	
//	
//	
//	*/