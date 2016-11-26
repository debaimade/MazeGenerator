package mazeGenerator;
import java.util.*;

public class cell {
//	private boolean leftWall;
//	private boolean bottomWall;
//	
//	public cell(){
//		leftWall = true;
//		bottomWall = true;
//	}
//	
//	public void setleft(){
//		leftWall = false;
//	}
//	
//	public void setBottom(){
//		bottomWall = false;
//	}
//	
//	public boolean getLeft(){
//		return leftWall;
//	}
//	
//	public boolean getBottom(){
//		return bottomWall;
//	}
	public cellWall top, bottom, left, right;
	public int x, y;
	public List<cell> adjacent;
	public int cellName;
	public cell prev;
	
	public cell(int x, int y){
		this.x = x;
		this.y =y;
		adjacent = new LinkedList<cell>();
		prev = null;
		cellName = 0;
	}
	
	public int getCellName(){
		return cellName++;
	}
}
