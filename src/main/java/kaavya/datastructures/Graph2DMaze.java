package kaavya.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Graph2DMaze {
//Given a maze with an end point end and a start point start, find a path from start to end and return that path. The path must be a simple path (no points can be repeated and "re-traversed")
	private static enum Color{WHITE, BLACK};
	
	public static void main(String[] args) {
		List<List<Color>> maze = new ArrayList<List<Color>>();
		
		//maze columns for row 1
		List<Color> colorList = new ArrayList<Color>();
		colorList.add(Color.WHITE);colorList.add(Color.WHITE);colorList.add(Color.WHITE);colorList.add(Color.BLACK);colorList.add(Color.WHITE);
		maze.add(colorList);
		
		//maze columns for row 2
		colorList = new ArrayList<Color>();
		colorList.add(Color.WHITE);colorList.add(Color.BLACK);colorList.add(Color.WHITE);colorList.add(Color.BLACK);colorList.add(Color.WHITE);
		maze.add(colorList);
		
		//maze columns for row 3
		colorList = new ArrayList<Color>();
		colorList.add(Color.WHITE);colorList.add(Color.BLACK);colorList.add(Color.WHITE);colorList.add(Color.BLACK);colorList.add(Color.WHITE);
		maze.add(colorList);
		
		//maze columns for row 4
		colorList = new ArrayList<Color>();
		colorList.add(Color.BLACK);colorList.add(Color.WHITE);colorList.add(Color.WHITE);colorList.add(Color.WHITE);colorList.add(Color.WHITE);
		maze.add(colorList);
				
		//maze columns for row 5
		colorList = new ArrayList<Color>();
		colorList.add(Color.WHITE);colorList.add(Color.WHITE);colorList.add(Color.BLACK);colorList.add(Color.BLACK);colorList.add(Color.WHITE);
		maze.add(colorList);				

		Coordinate start = new Coordinate(4,0);
		Coordinate end = new Coordinate(0,4);
		
		List<Coordinate> path = findPath(maze, start, end);
		
		System.out.println("\nPossible Path in the maze between start and finish is:");
		printPath(path);
	}
	
	static void printPath(List<Coordinate> path){
		System.out.println("\n");
		path.forEach((Coordinate c) -> System.out.print("{" + c.row + ", " + c.column + "}"));
	}
	
	static List<Coordinate> findPath(List<List<Color>> maze, Coordinate start, Coordinate end) {
		List<Coordinate> path = new ArrayList<>();
		
		//color the start square black and add it to the path
		maze.get(start.row).set(start.column, Color.BLACK);
		path.add(start);
		
		//check if there exists a path to end, if yes return the path and finish. If no path exists remove this node from the path
		if (!isPathtoEndExists(maze, start, end, path))
			path.remove(path.size()-1);
		return path;
	}
	
	static boolean isPathtoEndExists(List<List<Color>> maze, Coordinate node, Coordinate end, List<Coordinate> path) {
		if (node.equals(end)) {
		      return true;
		    }
		
		/*Indexes:
	          0          1
	      {rowDelta, colDelta}
	    */
		int[][] dimensions = {
			      {-1, 0}, // going up
			      {0 , 1}, // going right
			      {1, 0}, // going down
			      {0, -1} // going left
				}; 
		
		//check what is the adjacent connected square/vertex and add it to path if its valid
		for (int[] dimension : dimensions) {
			int nextrow = dimension[0] + node.row;
			int nextcolumn = dimension[1] + node.column;
			
			Coordinate nextCoord = new Coordinate (nextrow, nextcolumn);
			
			//is this new Coordinate valid to be walked upon?
			if (isNewCoordValid(maze, nextrow, nextcolumn)) { 
				path.add(nextCoord);
				printPath(path);
				maze.get(nextrow).set(nextcolumn, Color.BLACK);
				
				//Recurse and find if we can finish the path
				if (isPathtoEndExists(maze, nextCoord, end, path))
					return true;

				//If we cannot find the path, this last added node to the path needs to be removed and we need to continue the loop for other possible directions
				path.remove(path.size()-1);
				printPath(path);
			}//end if 
			

		}//end for loop for all dimensions
		
		//No 'next' Coordinates worked from this Coordinate. Return false to our caller. Path not possible, caller will have to keep searching.
		return false;
	}
	
	static boolean isNewCoordValid(List<List<Color>> maze, int row, int column) {
		//if color is already black its either visited already or not in the connected region
		return (row >= 0 && row <= maze.size()-1 && column >= 0 && column <= maze.get(row).size()-1 && maze.get(row).get(column) != Color.BLACK);
	}
	
	static class Coordinate{
		int row;int column;
		public Coordinate(int row, int column) {
			super();
			this.row = row;
			this.column = column;
		}
		@Override
		public int hashCode() {
			return Objects.hash(this.row, this.column);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Coordinate other = (Coordinate) obj;

			if ((column != other.column) || (row != other.row))
				return false;

			return true;
		}
	}//end class Coordinate

}
