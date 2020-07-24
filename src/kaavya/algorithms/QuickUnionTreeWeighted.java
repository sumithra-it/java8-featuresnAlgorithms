package kaavya.algorithms;
import java.util.ArrayList;

public class QuickUnionTreeWeighted {

	//Integer array id[] of length N. Interpretation: id[i] is parent of i.
	int[] id;
	
	//maintain extra array sz[i] to count number of objects in the tree rooted at i.
	int[] sz;
	
	QuickUnionTreeWeighted(int N){
		//Initialize the array
		id = new int[N];
		sz = new int[N];
		for (int i=0; i < N; i++) {
			id[i] = i; //number is equal to the root-index initially 
			sz[i] = 1; //number of children for each number is one.  
		}
	}
	
	//find root and Make every other node in path point to its grandparent to flatten the tree and 
	//avoid tall trees (thereby halving path length).
	int findRootandCompress(int num){
		while (id[num] != num) {
			id[num] = id[id[num]]; //modify big trees to be directly under the root node
			num = id[num];
			
		}
		return num;
	}
	
	//To merge components containing p and q, set the id of p's root to the id of q's root.
	void union (int p, int q) {
		int proot = findRootandCompress(p);
		int qroot = findRootandCompress(q);
		
		//if the root are already same, do nothing
		if (proot == qroot) return;
		
		//if number of children for p <= q then q is the bigger tree.
		if (sz[proot] < sz[qroot]) {
			//p is the smaller tree, merge it with q tree
			id[p] = qroot; 
			
			//update the size array for q
			sz[qroot] += sz[proot];
		} else {
			//q is the smaller tree, merge it with p tree
			id[q] = proot; 
			
			//update the size array for p
			sz[proot] += sz[qroot];
		}
	}
	
	boolean isConnected(int p, int q){
		return (findRootandCompress(p) == findRootandCompress(q));
	}
	
	public static void main(String args[]) {
		int size = 20;
		QuickUnionTreeWeighted qu = new QuickUnionTreeWeighted(10);
		
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
