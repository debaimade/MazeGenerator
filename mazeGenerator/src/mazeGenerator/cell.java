package mazeGenerator;

public class cell {
	private boolean leftWall;
	private boolean bottomWall;
	
	public cell(){
		leftWall = true;
		bottomWall = true;
	}
	
	public void setleft(){
		leftWall = false;
	}
	
	public void setBottom(){
		bottomWall = false;
	}
	
	public boolean getLeft(){
		return leftWall;
	}
	
	public boolean getBottom(){
		return bottomWall;
	}
}
