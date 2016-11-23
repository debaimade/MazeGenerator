package mazeGenerator;

public class maze {
	private cell[][] maze;
	private int height, width;
	private String topWall, rightWall;

	public maze(int x, int y) {
		height = x;
		width = y;
		maze = new cell[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				maze[i][j] = new cell();
			}
//			topWall += "_";
//			rightWall += "|";
		}
	}

	public void printMaze() {
		for (int i = 0; i < height; i++) {
//			System.out.print(topWall.charAt(i));
			for (int j = 0; j < width; j++) {
				if(maze[i][j].getLeft()){
					System.out.print("|");
				}
				if(maze[i][j].getBottom()){
					System.out.print("_");
				}
//				System.out.print(rightWall.charAt(j));
			}
			System.out.println();
		}
	}
	
	public static void main (String[] args){
		maze testMaze = new maze(3,3);
		testMaze.printMaze();
	}
}
