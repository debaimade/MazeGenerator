package mazeGenerator;

import java.util.Random;

public class maze {
	private cell[][] maze;
	private int height, width;
	private static int numCells;
	private String topWall, rightWall;

	public maze(int x, int y) {
		height = x;
		width = y;
		numCells = x * y;
		maze = new cell[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				maze[i][j] = new cell();
			}
			// topWall += "_";
			// rightWall += "|";
		}
	}

	public void printMaze() {
		for (int i = 0; i < height; i++) {
			// System.out.print(topWall.charAt(i));
			for (int j = 0; j < width; j++) {
				if (maze[i][j].getLeft()) {
					System.out.print("|");
				}
				if (maze[i][j].getBottom()) {
					System.out.print("_");
				}
				// System.out.print(rightWall.charAt(j));
			}
			System.out.println();
		}
	}

	public static int randCell() {
		Random r = new Random();
		int randomCell = r.nextInt(((numCells - 1) - 0) + 1) + 0;

		return randomCell;
	}

	public void createmaze() {
		DisjSets ds = new DisjSets(numCells);
		int num = height * width;
		// for (int i = 0; i < numCells - 1; i++){
		// int cell1 = randCell();
		// int cell2 = randCell();
		// if(ds.find(cell1) != ds.find(cell2)){
		// ds.union(cell1, cell2);
		// }
		// }

		while (num > 1) {
			int cell1 = randCell();
			int cell2 = randCell();
			if (ds.find(cell1) != ds.find(cell2)) {
				ds.union(cell1, cell2);
				num--;
			}
		}
		System.out.println(ds.s[1]);
	}

	public static void main(String[] args) {
		maze testMaze = new maze(3, 3);
		testMaze.createmaze();
	}
}
