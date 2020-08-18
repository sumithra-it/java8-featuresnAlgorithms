package kaavya.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.w3c.dom.Node;

public class BinaryTree {
	
	static class TreeNode{
		int data;
		TreeNode left;
		TreeNode right;		
		TreeNode(int d){
			data = d;
		}
	}
	
	//pre, in, post order tree traversal
	static void readTree(TreeNode node) {
		if (node != null) {
		System.out.print(node.data + ", ");
		readTree(node.left);
		readTree(node.right);
		}
	}
	
	//iteartive read
	static void breadthWiseRead(TreeNode node) {
		//read one level at  time using Queue
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(node);
		
		while (queue.peek() != null) {
			node = queue.poll();
			System.out.print(node.data + ", ");
			if ( node.left != null) 
				queue.add(node.left);
			if ( node.right != null)
				queue.add(node.right);	
		}//end while 
	}
	
	//iterative read - Read all the children/last level first.
	static void reverseLevelOrderRead(TreeNode node) {
		//keep bisecting left and right and add into a Queue and insert into a stack
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(node); //initialized the queue
		
		Stack<TreeNode> stack = new Stack<>();
		
		while (!queue.isEmpty()) {
			node = queue.poll();
			if ( node.left != null) 
				queue.add(node.left);
			if ( node.right != null)
				queue.add(node.right);
			
			stack.push(node);	
		}//end while 
		
		while (!stack.isEmpty())
			System.out.print(stack.pop().data + ", ");
		
	}
	
	public static void main(String[] args) {
		//BinaryTree btree = new BinaryTree();
		
		TreeNode rootNode = createBinaryTree();
		//pre-traversal
		System.out.println("\n pre order traversal");
		readTree(rootNode);
		
		//breadthwise traversal
		System.out.println("\nBTree breadth wise traversal:");
		breadthWiseRead(rootNode);
		
		System.out.println("\nBTree reverse traversal(last level traversal):");
		reverseLevelOrderRead(rootNode);
		
		System.out.println("\n\nBTree boundary traversal(left down, leaf and right up traversal):");
		TreeNode rootNode2 = createBinaryTreeNonSymetrical();
		boundaryLevelTraversal(rootNode2);
		
		System.out.println("\n\nBTree: Count of leaf nodes in binary tree:" + 
				countLeafNodes(rootNode2) );
		
		System.out.println("\n\nBTree: Count of leaf nodes in binary tree:" + 
				countLeafNodes2(rootNode2) );
		
		System.out.println("\n\nBTree: Max element in rootNode tree is:" +
		findMaxElement(rootNode, -1));
		
		System.out.println("\n\nAll paths from root to leaf nodes: ");
		findPaths(rootNode, new int[10], 0);
		
		System.out.println("\n\nPrint all elements and node levels:");
		findNodeLevels(rootNode, 1);
		
		System.out.println("\nLevels processing:");
		System.out.println("\nrootNode.right.left is: " + rootNode.right.left.data); 
		System.out.println("\n\nPrint the level of a given node:" + 
				findLevelforGivenNode2(rootNode, rootNode.right.left, 1));
		
		System.out.println("Parent Hierarchy: ");
		findParentHierarchy(rootNode, rootNode.left.right, new int[8], 0);
		
		System.out.println("\n\nLCA:" +
		findLowestCommonAncestor(rootNode, rootNode.left.left.left, rootNode.left.left).data);
		
			
	}

	private static TreeNode createBinaryTree() {
		TreeNode rootNode =new TreeNode(40);
		TreeNode node20=new TreeNode(20);
		TreeNode node10=new TreeNode(10);
		TreeNode node30=new TreeNode(30);
		TreeNode node60=new TreeNode(60);
		TreeNode node50=new TreeNode(50);
		TreeNode node70=new TreeNode(70);
		TreeNode node80=new TreeNode(80);
		TreeNode node90=new TreeNode(90);
		
		rootNode.left=node20;
		rootNode.right=node60;
 
		node20.left=node10;
		node20.right=node30;
 
		node60.left=node50;
		node60.right=node70;
 
		node50.left=node80;
		node50.right=node90;
		System.out.println("\nBTree #1:");
		return rootNode;
	}
	private static TreeNode createBinaryTreeNonSymetrical() {
		TreeNode rootNode =new TreeNode(40);
		TreeNode node20=new TreeNode(20);
		TreeNode node10=new TreeNode(10);
		TreeNode node30=new TreeNode(30);
		TreeNode node60=new TreeNode(60);
		TreeNode node50=new TreeNode(50);
		TreeNode node70=new TreeNode(70);
		TreeNode node5=new TreeNode(5);
		TreeNode node45=new TreeNode(45);
		TreeNode node55=new TreeNode(55);
		
		rootNode.left=node20;
		rootNode.right=node60;
		
		node20.left=node10;
		node20.right = node30;
		
		node10.right = node5;
		node5.right=node45;
		
		node60.left=node50;
		node60.right=node70;
		
		node50.right=node55;
		
		return rootNode;
/*		
							Root
							40
					20				60
			10		  30		  50     70
				5					55
				  45
*/
		}

	public static void boundaryLevelTraversal(TreeNode rootNode) {
		if (rootNode == null) return;
		printLeftEdgeNodes(rootNode.left);
		
		//DEBUG: System.out.print("\nLeaf:");
		printLeafNodes(rootNode);
		
		//DEBUG: System.out.print("\nRight:");
		printRightUpwardsEdgeNodes(rootNode.right);
	}

	private static void printLeftEdgeNodes(TreeNode node) {
		if (node == null) return;
		if (node.left == null && node.right == null) return; //if its a leaf node return
		
		System.out.print(node.data + ", ");
		if (node.left != null) {
			printLeftEdgeNodes(node.left);
		} else if (node.right != null) {
			printLeftEdgeNodes(node.right);
		}	
	}
	
	private static void printLeafNodes(TreeNode node) {
		if (node == null) return;
		
		if (node.left != null)
			printLeafNodes(node.left);
		if (node.right != null)
			printLeafNodes(node.right);
		
		//execution comes here if its a leaf node with null right and left
		if (node.left == null && node.right == null)
			System.out.print(node.data + ", ");
	}
	
	private static void printRightUpwardsEdgeNodes(TreeNode node) {
		if (node == null) return;
		if (node.left == null && node.right == null) return; //if its a leaf node return
		
		if (node.right != null)
			printRightUpwardsEdgeNodes(node.right);
		else if(node.left != null)
			printRightUpwardsEdgeNodes(node.left);
		System.out.print(node.data + ", ");
	}
	
	//recursive function call using using static variables
	static int counter = 0;
	private static int countLeafNodes(TreeNode node) {
		if(node == null) return counter;
		
		if (node.left == null && node.right == null) {
			//DEBUG: System.out.print("\nLeaf: " + node.data + "; ");
			++counter;
			return counter;
		}
		
		if (node.left != null)
			countLeafNodes(node.left);
		if (node.right != null)
			countLeafNodes(node.right);
		return counter;
	}
	
	//recursive function call returning a function value
	private static int countLeafNodes2(TreeNode node) {
		if(node == null) return 0;
		
		if (node.left == null && node.right == null) {
			//DEBUG: System.out.print("\nLeaf: " + node.data + "; ");
			return 1;
		} 
		int left = countLeafNodes2(node.left); 
		int right = countLeafNodes2(node.right);
		
//		System.out.println("At node:" + node.data + " Left is:" + left);
//		System.out.println("At node:" + node.data + " right is:" + right);
		return left + right;
		
	}
	
	/* Recursive function passing data as method arguments
	 * When A recursive function calls itself, the memory for the called function is allocated on top of memory allocated to calling 
	 * function and different copy of local variables is created for each function call.
	 * 
	 * When the last call returns to the root node, the copy of the variable max which was from the node 40 call stack is returned
	 * The max value of 90 is lost as there are multiple copies of max for each recursive call. If you keep passing the value as part of the 
	 * method call, we can retain the max value.
	 * 
	 *  When we have 2 compute statements for left side and right side, both return independent values and we go to compare them
	 *  and track them in 2 separate variables leftmax and rightmax
	 */
	private static int findMaxElement(TreeNode node, int max) {
		int leftmax = -1, rightmax = -1;
		if(node == null) return max;
		
		if (node.data > max) {
			max = node.data;
		}
		if (node.left != null ) {
			System.out.print("\nLeft: " + node.left.data + "; ");
			leftmax = findMaxElement(node.left, max) ;
			if (leftmax > max) max = leftmax;
		} 
		if (node.right != null ) {
		    System.out.print("\nRight: " + node.right.data + "; ");
		    rightmax = findMaxElement(node.right, max);
		    if (rightmax > max) max = rightmax;
		}
				
		System.out.println("Leftmax: " + leftmax + " Rightmax: " + rightmax + " Current max:" + max);
		return max;
		
	}	
	
	//working code with recursion
	private static int findMaxElement2(TreeNode node) {
		int max = -1; int leftmax = -1, rightmax = -1;
		if(node == null) return max;
		
		if (node.data > max) {
			max = node.data;
		}
		if (node.left != null ) {
			System.out.print("\nLeft: " + node.left.data + "; ");
			leftmax = findMaxElement2(node.left);
		} 
		if (node.right != null ) {
		    System.out.print("\nRight: " + node.right.data + "; ");
		    rightmax = findMaxElement2(node.right);
		}
		if (leftmax > rightmax && leftmax > max)
			max = leftmax;
		else if (rightmax > leftmax && rightmax > max)
			max = rightmax;
				
		System.out.println("Leftmax: " + leftmax + " Rightmax: " + rightmax + " Current max:" + max);
		return max;
		
	}
	
	
	/*
	 * Print all paths from root to leaf 
	 */
	private static void findPaths(TreeNode node, int[] arr, int len) {
		if (node == null) return;

		//keep adding to the array until you reach the leaf node
		arr[len] = node.data;
		++len;

		//print the array if its a leaf node
		if (node.left ==null && node.right==null) {
			printArr(arr, len);
		}

		//go to next level of node
		if (node.left != null) {
			findPaths(node.left, arr, len);
		}
		
		if (node.right != null) {
			findPaths(node.right, arr, len);
		}
		
	}
	
	private static void printArr(int[] arr, int len) {
		System.out.println("\n");
		for (int i=0; i< len; i++)
			System.out.print(arr[i] + ", ");
	}
	
	//print all nodes of tree along with levels
	private static void findNodeLevels(TreeNode node, int level) {
		if (node == null) return;
		
		System.out.print(node.data + "("+ level + ")" + ", ");

		//if leaf node, no further execution
		if (node.left == null && node.right == null) {
			return;
		}
		
		++level;
		if (node.left != null) {
			findNodeLevels(node.left, level);
		}

		if (node.right != null) {
			findNodeLevels(node.right, level);
		}
		--level;
	}
	
	
	/*
	 * Get level of a node in Binary Tree. Input: Node. Output - level
	 * Search for the element, if element found, return the corresponding level number
	 */
	//TBD: Bug gives wrong result due to recursion not ending once the node is found
	private static int findLevelforGivenNode(TreeNode cur, TreeNode searchNode, int level)	{
		if (cur == null) return 0;
		boolean isFound = false;
		System.out.print(cur.data + "("+ level + ")" + ", ");
		
		if (cur.data == searchNode.data) {
			isFound = true;
			return level;
		} 
		if (cur.left == null && cur.right == null && !isFound) {//if leaf node, no further execution
			return 0;
		} 
		
		if(!isFound) {
			++level; int result=0;
			if (cur.left != null) {
				result = findLevelforGivenNode(cur.left, searchNode, level);
			}
			if (cur.right != null) {
				result = findLevelforGivenNode(cur.right, searchNode, level);
			}
			--level;
			
			if (result != 0) return result;
		}
		return level;
	}
//  Fix complete & working code, but not the best code	
	private static int findLevelforGivenNode2(TreeNode cur, TreeNode searchNode, int level)	{
		if (cur == null) return 0;

		System.out.print(cur.data + "("+ level + ")" + ", ");
		
		if (cur.data == searchNode.data) {
			return level;
		} 
		
		++level; int result=0;
		if (cur.left != null) {
			result = findLevelforGivenNode2(cur.left, searchNode, level);
		}
		if(result!=0) return result;// If found in left subtree , return 
		if (cur.right != null) {
			result = findLevelforGivenNode2(cur.right, searchNode, level);
		}
		--level;
		

		return result;
	}
	//Best code - Increment the variable "level+1" just during the recursive call and don't need to deal with old copies of the variable
	//The code will use the previous variable value for the previous call stack. No need of incrementing and storing it.
	public static int getLevelOfNode(TreeNode root,int key,int level)
	{
		if(root==null)
			return 0;
		if(root.data==key)
			return level;
 
		int result=getLevelOfNode(root.left,key,level+1) ;
		if(result!=0)
		{ 
			// If found in left subtree , return 
			return result;
		}
		result= getLevelOfNode(root.right,key,level+1);
 
		return result;
	}
	
	//ArrayList doesn't work. Use only int[]
	public static void findParentHierarchy(TreeNode cur, TreeNode searchNode, int[] arr, int len) {
		
		if (cur == null) return ;
		
		//list.add(cur.data); ////keep adding to the list until you reach the searchNode
		arr[len]=cur.data;
		len++;
		
		//print the array if its a searchNode
		if (cur.data == searchNode.data) {	
			//list.forEach((p) -> System.out.print(p + ", "));
			printArr(arr, len);
			return ;
		}

		//go to next level of node
		if (cur.left != null) findParentHierarchy(cur.left, searchNode, arr, len);
		
		if (cur.right != null) findParentHierarchy(cur.right, searchNode, arr, len);
		
		return ;
	}

	
	public static TreeNode findLowestCommonAncestor(TreeNode root, TreeNode cur1, TreeNode cur2) {
		//If both values are less than root, the LCA is on the left of the tree
		if (cur1.data < root.data && cur2.data < root.data)
		return findLowestCommonAncestor(root.left, cur1, cur2);
		
		else if  (cur1.data > root.data && cur2.data > root.data) //if both values are > root, then LCA is on the right
			return findLowestCommonAncestor(root.right, cur1, cur2);
		
		else return root; //if one value is less than root and one greater than root, then root is the only common ancestor
	}
	
	
	
}