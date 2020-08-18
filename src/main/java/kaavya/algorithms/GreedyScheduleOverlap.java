package kaavya.algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class GreedyScheduleOverlap {

	public static void main(String[] args) {

		//Given array of intervals.
		int[][] intervals = {{1, 2}, {2, 3}, {4, 6}, {2, 5}};
		
		//Given a sequence of intervals return the minimum amount of intervals that need to be erased to eliminate all overlap conflicts. 
		System.out.println("Min amount of inetrval to remove to fix the overlap:" +
		removeOverlaps(intervals));
		
		System.out.println("Min amount of operations needed to reduce the number to 1 is:" +
		minimumStepsToReachOne(1));
	  
	}

	static int removeOverlaps(int[][] intervals) {
		
		//sort the intervals by end time
		Arrays.sort(intervals, new ArrayComparator());
		int prevIntervalEnd = intervals[0][1];
		int totalnoconflict = 1;
		int[] nextInterval;
		
		for ( int i=1; i < intervals.length ; i++) {//count the non-overlapping intervals
			nextInterval = intervals[i];
			int nextIntervalStart = nextInterval[0];
			int nextIntervalEnd = nextInterval[1];
			
			if (prevIntervalEnd <= nextIntervalStart) { //if curinterval.endtime is greater/after the next interval.starttime, there is a conflict
				++totalnoconflict;
				prevIntervalEnd = nextIntervalEnd;
			}
		}
			
		return intervals.length - totalnoconflict;
	}
	
	static class ArrayComparator implements Comparator<int[]> {
		public int compare(int[] elem1, int[] elem2) {
			return elem1[1] - elem2[1]; //sort by ending time. elem1[0] is starting time and elem1[1] is ending time
		}
	}
	
	public static int minimumStepsToReachOne(int input) {
	    int numoperations = 0;
	    while (input >= 2) {
	    	++numoperations;
	    	if (input % 2 ==0) {
	    		input /= 2; //divide by 2
	    	}else {
	    		input -= 1;
	    	}	
	    }
	  //  ++numoperations; //for last operation to reduce 2 to 1
	    return 	numoperations;	    	
	}
}
