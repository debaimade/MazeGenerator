package mazeGenerator;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.util.Scanner;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class maze extends JPanel{
	private cell[][] maze;
	private ArrayList<cellWall> cellWalls;
	private int height, width;
	private static int numCells;
	
	private int x_cord;
	private int y_cord;
	private int cellSize;
	private int randomWall;
	
//	private String topWall, rightWall;
//
//	public maze(int x, int y) {
//		height = x;
//		width = y;
//		numCells = x * y;
//		maze = new cell[height][width];
//		for (int i = 0; i < height; i++) {
//			for (int j = 0; j < width; j++) {
//				maze[i][j] = new cell();
//			}
//			// topWall += "_";
//			// rightWall += "|";
//		}
//	}
//
//	public void printMaze() {
//		for (int i = 0; i < height; i++) {
//			// System.out.print(topWall.charAt(i));
//			for (int j = 0; j < width; j++) {
//				if (maze[i][j].getLeft()) {
//					System.out.print("|");
//				}
//				if (maze[i][j].getBottom()) {
//					System.out.print("_");
//				}
//				// System.out.print(rightWall.charAt(j));
//			}
//			System.out.println();
//		}
//	}
//
//	public static int randCell() {
//		Random r = new Random();
//		int randomCell = r.nextInt(((numCells - 1) - 0) + 1) + 0;
//
//		return randomCell;
//	}
//
//	public void createmaze() {
//		DisjSets ds = new DisjSets(numCells);
//		int num = height * width;
//		// for (int i = 0; i < numCells - 1; i++){
//		// int cell1 = randCell();
//		// int cell2 = randCell();
//		// if(ds.find(cell1) != ds.find(cell2)){
//		// ds.union(cell1, cell2);
//		// }
//		// }
//
//		while (num > 1) {
//			int cell1 = randCell();
//			int cell2 = randCell();
//			if (ds.find(cell1) != ds.find(cell2)) {
//				if((cell1 - 1) / width == (cell2 - 1) / width){
//					if( Math.abs(cell1)){
//						
//					}
//				}
//				ds.union(cell1, cell2);
//				num--;
//			}
//		}
//		System.out.println(ds.s[1]);
//	}
//
//	public static void main(String[] args) {
//		maze testMaze = new maze(3, 3);
//		testMaze.createmaze();
//	}
	public maze(int height, int width){
		this.height = height;
		this.width = width;
		maze = new cell[height][width];
		cellWalls = new ArrayList<cellWall>((height - 1) * (width - 1));
		createMaze();
		setPreferredSize(new Dimension(800, 700));
	}
	private void createMaze(){
		createCells();
		DisjSets ds = new DisjSets(height * width);
		Random r = new Random();
		int num = height* width;
		
		while(num > 1) {
			int randomWall = r.nextInt(cellWalls.size());
			cellWall tempWall = cellWalls.get(randomWall);
			
			int cell1 = tempWall.currentCell.y + tempWall.currentCell.x * width;
			int cell2 = tempWall.nextCell.y + tempWall.nextCell.x * width;
			
			if(ds.find(cell1) != ds.find(cell2)){
				cellWalls.remove(randomWall);
				ds.union(ds.find(cell1), ds.find(cell2));
				tempWall.isRemoved = true;
				tempWall.currentCell.adjacent.add(tempWall.nextCell);
				tempWall.nextCell.adjacent.add(tempWall.currentCell);
				num--;
			}
		}
	}
	private int cellNumber = 0;
	public void createCells() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				maze[i][j] = new cell(i, j);
				if(i == 0) {
					maze[i][j].top = new cellWall(maze[i][j]);
				}
				else{
					maze[i][j].top = new cellWall(maze[i - 1][j], maze[i][j]);
					cellWalls.add(maze[i][j].top);
				}
				if(i == height - 1) {
					maze[i][j].bottom = new cellWall(maze[i][j]);
				}
				if(j == 0) {
					maze[i][j].left = new cellWall(maze[i][j]);
				}
				else{
					maze[i][j].left = new cellWall(maze[i][j - 1], maze[i][j]);
					cellWalls.add(maze[i][j].left);
				}
				if(j == width - 1) {
					maze[i][j].right = new cellWall(maze[i][j]);
				}
				maze[i][j].cellName = cellNumber++;
			}
		}
		maze[0][0].left.isRemoved = true;
		maze[0][0].cellName = 0;
		maze[height - 1][width - 1].bottom.isRemoved = true;
		maze[height - 1][width - 1].cellName = (height*width);
	}
	public void paintComponent(Graphics g){
		x_cord = 40;
		y_cord = 40;
		
		cellSize = (width - x_cord) / width + 7;
		
		int x = x_cord;
		int y = y_cord;
		
		for (int i = 0; i <= height - 1; i++){
			for (int j = 0; j <= width - 1; j++){
				if(!maze[i][j].top.isRemoved){
					g.drawLine(x, y, x + cellSize, y);
				}
				if(maze[i][j].left.isRemoved == false){
					g.drawLine(x, y, x, y + cellSize);
				}
				if((i == height -1) && maze[i][j].bottom.isRemoved == false){
					g.drawLine(x, y + cellSize, x + cellSize,
                            y + cellSize);
				}
				if ((j == width - 1) && maze[i][j].right.isRemoved == false) {
                    g.drawLine(x + cellNumber, y, x + cellNumber,
                            y + cellNumber);
                }
				x += cellSize;
			}
			x = x_cord;
			y += cellSize;
		}
	}
//	private void printMaze(){
//		for(int i = 0; i < height; i++){
//			for(int j = 0; j < width; j++){
//				if (maze[i][j].top != null && !maze[i][j].top.isRemoved){
//					System.out.print("_");
//				}
//				else 
//					System.out.print("   ");
//				if (maze[i][j].bottom != null && !maze[i][j].bottom.isRemoved){
//					System.out.print("_");
//				}
//				else 
//					System.out.print("   ");
//				if (maze[i][j].left != null && !maze[i][j].left.isRemoved){
//					System.out.print("|");
//				}
//				else 
//					System.out.print("   ");
//				if (maze[i][j].right != null && !maze[i][j].right.isRemoved){
//					System.out.print("|");
//				}
//				else 
//					System.out.print("   ");
//				//System.out.println();
//			}
//			System.out.println();
//			//System.out.println();
//		}
//	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the height and width of your desired maze: ");
		int height = sc.nextInt();
		int width = sc. nextInt();
		sc.close();
		
		//maze yourMaze = new maze(height, width);
		//yourMaze.createMaze();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 800);
        frame.getContentPane().add(new maze(height, width));
        frame.pack();
        frame.setVisible(true);
	}
}
