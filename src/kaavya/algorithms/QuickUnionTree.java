package kaavya.algorithms;
import java.util.ArrayList;

public class QuickUnionTree {

	int[] id; //Id array - has the parent/root of each element 
			  // Elements are the integers 1 to 10
	
	QuickUnionTree(int N){
		//Integer array id[] of length N. Interpretation: id[i] is parent of i.
		//Elements that are all by themselves in just, in their own connected component, point to themselves
		//Initialize the id array
		//ArrayList[] id;
		id = new int[N];
		for (int i=0; i < N; i++) {
			id[i] = i;
		}
	}
	
	int findRoot(int num){
		while (id[num] != num) 
			num = id[num];
		return num;
	}
	
	//To merge components containing p and q, set the id of p's root to the id of q's root.
	void union (int p, int q) {
		int proot = findRoot(p);
		int qroot = findRoot(q);
		id[p] = qroot;
		
	}
	
	boolean isConnected(int p, int q){
		return (findRoot(p) == findRoot(q));
	}
	
	public static void main(String args[]) {
		int size = 10;
		QuickUnionTree qu = new QuickUnionTree(10);
		
		//make some sample connections - to test
		//3 -> 4 -> 9
		qu.id[3] = 4;
		qu.id[4] = 9;
		
		//5 -> 6
		qu.id[5] = 6;
		
		//test if 5 and 3 are connected.
		System.out.println("Is 3 and 5 conected: " + qu.isConnected(3,5));
		
		//test if 3 and 9 are connected
		System.out.println("Is 3 and 9 conected: " + qu.isConnected(3,9));
		
		//merge contents of 3 and 5
		qu.union(3,5);
		System.out.println("Is 3 and 5 conected after union: " + qu.isConnected(3,5));
	}
}
