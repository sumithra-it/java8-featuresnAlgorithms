package kaavya.datastructures;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class Graph2DMatrix {
	
	public static void main(String[] args) {
		// Paint a 2D matrix - paint all the adjacent connected region to opposite color 
		int[][] image = {
		         {0, 0, 1, 0},
		         {0, 0, 1, 0},
		         {0, 0, 1, 0},
		         {0, 0, 1, 0}
		       };
		int row = 0;
		int column = 1;
		int newcolor = 1;
		
		findnFlipAllAdjacentRegion(image, row,column,newcolor);
		print2DMatrix(image);
	}
	
	static int[][] findnFlipAllAdjacentRegion(int[][] image, int row, int column, int newcolor) {
		/* 1. Initialize and start with the first node
		 * 2. Do a breadth first search (use a Queue to store help in BDF search)
		 * 3. Maintain a seen/visited set to track the already visited vertices and not to re-visit them
		 * 4. Work - Actual work is to check if the adjacent node is same color. If different color its not in the connected region.
		 * 			 If within the boundary coordinates, add it to the queue and process them as well.
		 * 			 If not visited yet, add it to queue and add it to visited map. 
		 */
		Queue<Coordinate> queue = new LinkedList<Coordinate>();
		Set<Coordinate> visited = new HashSet<>();
		
		int originalValue = image[row][column];
		Coordinate curVertex = new Coordinate(row, column, originalValue);
		queue.add(curVertex);
		
		int[][] dimensions = {//{rowDelta, colDelta}
			      {-1, 0}, // going up
			      {0 , 1}, // going right
			      {1, 0}, // going down
			      {0, -1} // going left
				};
		while (!queue.isEmpty()) {
			
			curVertex = queue.poll();
			image[curVertex.row][curVertex.column] = newcolor;
			
			for (int[] dimension : dimensions) {
				int xcoordinate = dimension[0];
				int ycoordinate = dimension[1];
				
				int nextxcoord = xcoordinate+curVertex.row;
				int nextycoord = ycoordinate+curVertex.column;
				
				//check if the new x and y coordinate are valid
				if (!isXYCoordinateValid(nextxcoord, nextycoord, image))
					continue;
				
				Coordinate adjVertex = new Coordinate(nextxcoord, nextycoord, newcolor);
				if (!visited.contains(adjVertex) && !(image[nextxcoord][nextycoord] == newcolor)) {
					queue.add(adjVertex);
					visited.add(adjVertex);
				}
				
			}//end for loop
			
		}//end while loop
		return image;
	}
	
	static boolean isXYCoordinateValid(int x, int y, int[][] image){
		return (x >= 0 && x < image.length && y < image[x].length && y >= 0);
	}
	
	static class Coordinate{
		int row;int column;
		int pixelValue;
		public Coordinate(int row, int column, int pixelValue) {
			super();
			this.row = row;
			this.column = column;
			this.pixelValue = pixelValue;
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

			if ((column != other.column) || (row != other.row) || (pixelValue != other.pixelValue))
				return false;

			return true;
		}
	}
	static void print2DMatrix(int[][] matrix) {
		for (int[] row : matrix) {
			System.out.print("{");
			for(int col : row) {
				System.out.print(col + " ");
			}
			System.out.println("}, ");
		}
	}

}
