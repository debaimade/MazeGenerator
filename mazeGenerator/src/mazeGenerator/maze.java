package mazeGenerator;

public class maze {
	private cell[][] maze;
	
	public maze(int x, int y){
		
		for (int i = 0; i < x; i++){
			for (int j = 0; j < y; j++){
				maze[i][j] = new cell();
			}
		}
	}
}
