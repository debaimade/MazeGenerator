package mazeGenerator;

public class cellWall {
	public cell currentCell, nextCell;
	public boolean isRemoved = false;
	
	public cellWall(cell a, cell b){
		currentCell = a;
		nextCell = b;
	}
	
	public cellWall(cell r){
		currentCell = r;
		nextCell = null;
	}
}
