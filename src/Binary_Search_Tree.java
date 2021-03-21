// Code created and modified by Emily Peguero Marmolejos, Tashi Dolma, and Jeffrey Torres
import java.time.*;
import java.util.*;

/*
 * Initial code is from geeks for geeks
 */
public class Binary_Search_Tree { 
    /* Class containing left and right child of current node and key value*/
    class Node { 
        int value;
        String key;
        Node left, right; 
  
        public Node(String item, int freq) { 
            key = item; 
            value = freq;
            left = right = null; 
        } 
    } 
   
    // Root of BST 
    Node root; 
    
    //empirical data
    public int comparisons;
    public int iterations;
    public int comparisons_for_creationFREQ=0;
	public int comparisons_for_creationLEX=0;
	public int comparisons_for_searchFREQ=0;
	public int comparisons_for_searchLEX=0;
	public int iterations_for_MAXsearch=0;
	public int iterations_for_MINsearch=0;
	
    // Constructor 
    Binary_Search_Tree() {  
        root = null;  
    }
    
    /* A recursive function to insert a new key in BST */
    Node insertFreqRec(Node root, Node new_node) { 
        /* If the tree is empty, return a new node */
        if (root == null) { 
        	comparisons_for_creationFREQ ++;//keep track of number of comparisons
            root = new_node; 
            return root; 
        } 
        else if(root.value < new_node.value) {
        	comparisons_for_creationFREQ ++;
        	root.right = insertFreqRec(root.right, new_node);	
        }else {
        	root.left = insertFreqRec(root.left, new_node);    
        }
        /* return the (unchanged) node pointer */
        return root; 
    } 
    
    // This method mainly calls insertFreqRec() 
    void insertFreq(String word, int freq) { 
    	
    	root = insertFreqRec(root, new Node(word,freq)); 
    }  
    
    /* A recursive function to insert a new key in BST in lexographical order */
    Node insertLexRec(Node root, Node new_node) { 
        /* If the tree is empty, return a new node */
        if (root == null) { 
        	comparisons_for_creationLEX ++;
            root = new_node; 
            return root; 
        } 
        /* Otherwise, recur down the tree */
        if (new_node.key.equalsIgnoreCase(root.key)){
        	comparisons_for_creationLEX ++;
        	root.value += new_node.value;
        }
        else if (root.key.compareToIgnoreCase(new_node.key) < 0) { 
        	comparisons_for_creationLEX ++;
        	root.right = insertLexRec(root.right, new_node); 
        }else {
        	root.left = insertLexRec(root.left, new_node);
        }
        /* return the (unchanged) node pointer */
        return root; 
    } 
    
    // This method mainly calls insertLexRec() 
    void insertLex(String word, int freq) { 
    	root = insertLexRec(root, new Node(word,freq)); 
    } 
    
    //recursive function to find first node with given freq
    public Node searchFreqRec(Node root, int freq) { 
        // Base Cases: root is null or key is present at root 
        if (root == null ||root.value == freq) { 
        	comparisons_for_searchFREQ++;
           	return root;  
         }
        // val is greater than root's key 
        if (root.value < freq) {
        	comparisons_for_searchFREQ++;
            return searchFreqRec(root.right, freq); 
        }
        // val is less than root's key 
        return searchFreqRec(root.left, freq); 
    } 
    
    //calls recursive function that returns a node searched on freq & prints result to console
    public void searchFreq(int frequency) {
    	Node result = searchFreqRec(root, frequency);
    	if(result != null) {
    		comparisons_for_searchFREQ++;
    		System.out.println("Keyword : " + result.key + " Frequency : " + result.value);
    	}else {
    		System.out.println("That frequency is not in the search tree");
    	}
    }
    
  //recursive function that returns a word that is sorted lexicographically
    public Node searchLexRec(Node root, String key) { 	
        // Base Cases: root is null or key is present at root 
        if (root==null || root.key.equalsIgnoreCase(key)) { 
        	comparisons_for_searchLEX+=2;
        	return root;         
        }
        // val is greater than root's key 
        if (root.key.compareToIgnoreCase(key) < 0) {
        	comparisons_for_searchLEX+=2;
            return searchLexRec(root.right, key); 
        }
        // val is less than or equal to root's key 
        return searchLexRec(root.left, key); 
    } 
    
    //calls recursive function that returns a node searched by lexicographical sort & prints result to console
    public void searchLex(String key) {
    	Node result = searchLexRec(root, key);
    	if(result != null) {
    		comparisons_for_searchLEX++;
    		System.out.println("Keyword : " + result.key);
    	}else {
    		System.out.println("That word is not in the search tree");
    	}
    }
    
 // Function to find the node with maximum value 
 // i.e. rightmost leaf node 
    void maxValue(){  
     /* loop down to find the rightmost leaf */
    	Node current = root; 
    	while (current.right != null) { 
    		current = current.right; 
    		iterations_for_MAXsearch++;
    	}
    	System.out.println("Word with Max Value is : " + current.key + " : " + current.value);
    } 
    
    // Function to find the node with maximum value 
    // i.e. rightmost leaf node 
    void minValue(){  
        /* loop down to find the rightmost leaf */
       	Node current = root; 
       	while (current.left != null) {
       		current = current.left; 
       		iterations_for_MINsearch++;
       	}
       	
       	System.out.println("'" + current.key + "' with frequency : " + current.value);
    } 
    
    // A utility function to do inorder traversal of BST 
    void inorderRec(Node root) { 
        if (root != null) { 
            inorderRec(root.left); 
            System.out.println(root.key + " : " + root.value); 
            inorderRec(root.right); 
        } 
    }
    
    // This method mainly calls InorderRec() 
    void inorder()  { 
    	System.out.println("Inorder traversal");
    	inorderRec(root); 
    } 
    
    //returns some empirical data
    public void getDatas() {
    	this.comparisons = this.comparisons_for_creationFREQ + this.comparisons_for_creationLEX + this.comparisons_for_searchFREQ + this.comparisons_for_searchFREQ + this.comparisons_for_searchLEX;
    	this.iterations = this.iterations_for_MAXsearch + this.iterations_for_MINsearch;

    	System.out.println("Number of comparisons to create the frequency BST : " + this.comparisons_for_creationFREQ);
    	System.out.println("Number of comparisons to create lexographical BST : " + this.comparisons_for_creationLEX);
    	System.out.println("Number of comparisons for frequency search : " + this.comparisons_for_searchFREQ);
    	System.out.println("Number of comparisons for lexographical search : " + this.comparisons_for_searchLEX);
    	System.out.println("Number of iterations for max search : " + this.iterations_for_MAXsearch);
    	System.out.println("Number of iterations for min search : " + this.iterations_for_MINsearch);
    	System.out.println("Total comparisons: " + this.comparisons);
    	System.out.println("Total iterations: " + this.iterations);

    }

    //to find depth of tree
    int maxDepth(Node node)  
    { 
        if (node == null) 
            return 0; 
        else 
        { 
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left); 
            int rDepth = maxDepth(node.right); 
   
            /* use the larger one */
            if (lDepth > rDepth) 
                return (lDepth + 1); 
             else 
                return (rDepth + 1); 
        } 
    } 
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in); 
		Instant start = Instant.now();
		String URL = "https://www.tutorialspoint.com/java/lang/string_split.htm";
		

		Parser word_list_article1 = new Parser(URL);
		

		Binary_Search_Tree lex_tree1 = new Binary_Search_Tree();//BST in lexographical order
		Binary_Search_Tree freq_tree1 = new Binary_Search_Tree();//BST in order by word frequency
		
		Hashtable <String, Integer> ht = word_list_article1.get_word_list();
		
		Set <String> keySet = ht.keySet(); //to iterate the hash table
		
		for(String keys : keySet){
			lex_tree1.insertLex(keys, ht.get(keys));
			freq_tree1.insertFreq(keys, ht.get(keys));
		}
	
		System.out.println("What word would you like to search in the BinarySearch Tree? Enter a number to find frequency, a string to search for a word.\n Enter : 'QUIT!' to end search.");
    	String input = scanner.next();
    	while(!(input.equalsIgnoreCase("Quit!"))){
    		try{
    			int number = Integer.parseInt(input);
    			freq_tree1.searchFreq(number);
    		}catch(NumberFormatException nfe) {
    			lex_tree1.searchLex(input);
    		}
    		System.out.println("What word would you like to search in the BinarySearch Tree? Enter a number to find frequency, a string to search for a word.\n Enter : 'QUIT!' to end search.");
        	input = scanner.next();
    	}
		
		System.out.println("___________Random Search terms BST : _____________");
		System.out.println("Finding the max freq : ");
		long freq_max_search = System.currentTimeMillis();
		freq_tree1.maxValue();
		long freq_max_end = System.currentTimeMillis();
		long freq_max_searchTime = freq_max_end - freq_max_search; 
		System.out.print(freq_max_searchTime);
		System.out.println("Finding the lex max : ");
		lex_tree1.maxValue();
		System.out.println("Finding the min freq : ");
		freq_tree1.minValue();
		System.out.println("Finding the lex min : ");
		lex_tree1.minValue();
		Instant end = Instant.now();
		System.out.println();
		System.out.println("__________Empirical Evidence BST : ______________");
		System.out.println();
		System.out.println("Parser data : ");
		System.out.println();
		word_list_article1.get_data();
		System.out.println();
		System.out.println("Frequency Binary Search  Tree Data : ");
		System.out.println();
		freq_tree1.getDatas();
		System.out.println();
		System.out.println("Lexographical Binary Search Tree Data : ");
		System.out.println();
		lex_tree1.getDatas();
		System.out.println();

	    Duration timeElapsed = Duration.between(start, end);
	    System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds ");

	    System.out.println("N: size of wordlist "+ keySet.size() +" nodes including root.");
	    System.out.println("Number of comparisons in this run in total : " + freq_tree1.comparisons + lex_tree1.comparisons);
	    System.out.println("Number of iterations in this run in total : " + freq_tree1.iterations + lex_tree1.iterations);
    }
  } 


// This code is contributed by Ankur Narain Verma via GeeksforGeeks