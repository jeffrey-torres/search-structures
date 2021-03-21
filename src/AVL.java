// Code created and modified by Tashi Dolma, Emily Peguero Marmolejos, and Jeffrey Torres
import java.time.Duration;
import java.time.Instant;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set; 
//Java program for insertion in AVL Tree 
class Node { 
	int key, height; 
	Node left, right; 

	Node(int d) { 
		key = d; 
		height = 1; 
	} 
} 
public class AVL {
	

	public static int comparison=0;
	Node root; 

	// A utility function to get the height of the tree 
	int height(Node N) { 
		if (N == null) 
			return 0; 

		return N.height; 
	} 

	// A utility function to get maximum of two integers 
	int max(int a, int b) { 
		return (a > b) ? a : b; 
	} 

	// A utility function to right rotate subtree rooted with y 
	// See the diagram given above. 
	Node rightRotate(Node y) { 
		Node x = y.left; 
		Node T2 = x.right; 

		// Perform rotation 
		x.right = y; 
		y.left = T2; 

		// Update heights 
		y.height = max(height(y.left), height(y.right)) + 1; 
		x.height = max(height(x.left), height(x.right)) + 1; 

		// Return new root 
		return x; 
	} 

	// A utility function to left rotate subtree rooted with x 
	// See the diagram given above. 
	Node leftRotate(Node x) { 
		Node y = x.right; 
		Node T2 = y.left; 

		// Perform rotation 
		y.left = x; 
		x.right = T2; 

		// Update heights 
		x.height = max(height(x.left), height(x.right)) + 1; 
		y.height = max(height(y.left), height(y.right)) + 1; 

		// Return new root 
		return y; 
	} 

	// Get Balance factor of node N 
	int getBalance(Node N) { 
		if (N == null) 
			return 0; 

		return height(N.left) - height(N.right); 
	} 

	Node insert(Node node, int key) { 

		/* 1. Perform the normal BST insertion */
		if (node == null) 
			return (new Node(key)); 

		if (key < node.key) {
			node.left = insert(node.left, key); 
		  comparison++;
		}
		else if (key > node.key) {  comparison++;
			node.right = insert(node.right, key); 
		}
		else // Duplicate keys not allowed 
			return node; 

		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left), 
							height(node.right)); 

		/* 3. Get the balance factor of this ancestor 
			node to check whether this node became 
			unbalanced */
		int balance = getBalance(node); 

		// If this node becomes unbalanced, then there 
		// are 4 cases Left Left Case 
		if (balance > 1 && key < node.left.key) 
			return rightRotate(node); 

		// Right Right Case 
		if (balance < -1 && key > node.right.key) 
			return leftRotate(node); 

		// Left Right Case 
		if (balance > 1 && key > node.left.key) { 
			node.left = leftRotate(node.left); 
			return rightRotate(node); 
		} 

		// Right Left Case 
		if (balance < -1 && key < node.right.key) { 
			node.right = rightRotate(node.right); 
			return leftRotate(node); 
		} 

		/* return the (unchanged) node pointer */
		return node; 
	} 

	

	   private void printTree(Node root) {
	       if (root == null) {
	           return;
	       }
	       String toBePrinted = root.key + ":";
	       if (root.left != null) {
	           toBePrinted += "L:" + root.left.key + ",";
	       }
	      
	       if (root.right != null) {
	           toBePrinted += "R:" + root.right.key;
	       }
	       System.out.println(toBePrinted);
	       printTree(root.left);
	       printTree(root.right);
	   }
	  
	   public void printTree() {
	       printTree(root);
	   }
	   private boolean searchDataHelper(int data, Node root) { //helper function to search data
	       if (root == null) {
	           return false;
	       }
	      
	       if (root.key == data) {
	           comparison++;
	           
	           return true;
	       } else if (data > root.key) {
	           // call right
	           comparison++;
	          
	           return searchDataHelper(data, root.right);
	       } else {
	           // call left
	    	   comparison++;
	    	   
	           return searchDataHelper(data, root.left);
	       }
	   }
	  
	   public boolean searchData(int data) { //function to search data
	       return searchDataHelper(data, root);
	   }

	public static void main(String[] args) { 
		 Instant start = Instant.now();
		AVL tree = new AVL(); 
		 Random rand = new Random(); 
		 String URL = "https://www.tutorialspoint.com/java/lang/string_split.htm";


		 Parser word_list_article3 = new Parser(URL);	  
		 
		 
	     Hashtable <String, Integer> ht2 = word_list_article3.get_word_list();
	     
	     Set <String> keySet2 = ht2.keySet(); //to iterate the hash table

			
			for(String keys : keySet2){
				//trie.insert(keys.toLowerCase());
			// Construct trie 
		 // Generate random integers in range 0 to 999 
	      // for (int i = 0; i < 150; i++) {
	    	//   int rand_int1 = rand.nextInt(100);      	      
	    	   tree.root = tree.insert(tree.root, ht2.get(keys));
	    	 }
	       tree.printTree();
	       System.out.println();
	       System.out.println(tree.searchData(55));   
	       Instant end = Instant.now();
	       System.out.println("Number of comparisons : "+comparison);
	       Duration timeElapsed = Duration.between(start, end);
	       System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");

		
	} 
} 
//This code has been contributed by Mayank Jaiswal 

