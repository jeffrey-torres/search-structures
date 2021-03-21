// Code created and modified by Emily Peguero Marmolejos, Tashi Dolma, and Jeffrey Torres
import java.util.*;
import java.io.*;
import java.time.Duration;
import java.time.Instant;

//Java implementation of search and insert operations 
//on Trie 
public class Trie { 
   
 // Alphabet size (# of symbols) 
 static final int ALPHABET_SIZE = 26; //a-z with digits
 
 //empirical data
 public static int iteration_for_creation = 0;
 public static int comparison_for_creation = 0;
 public static int iteration_for_search = 0;
 public static int comparison_for_search = 0;
 public static int comparisons = 0;
 public static int iterations = 0;
   
 // trie node 
static class TrieNode 
 { 
     TrieNode[] children = new TrieNode[ALPHABET_SIZE]; 
    
     // isEndOfWord is true if the node represents 
     // end of a word 
     boolean isEndOfWord; 
       
     TrieNode(){ 
         isEndOfWord = false; 
         for (int i = 0; i < ALPHABET_SIZE; i++) {
        	 iteration_for_creation++;
        	 children[i] = null;
         }
              
     } 
 }; 
    

 static TrieNode root;  
   
 // If not present, inserts key into trie 
 // If the key is prefix of trie node,  
 // just marks leaf node 
 public void insert(String key) 
 { 
     int level; 
     int length = key.length(); 
     int index; 
    
     TrieNode pCrawl = root; 
    
     for (level = 0; level < length; level++) 
     { 
    	 iteration_for_creation++;
         index = key.charAt(level) - 'a'; 
         if (pCrawl.children[index] == null) {
        	 comparison_for_creation++;
             pCrawl.children[index] = new TrieNode(); 
         }
         pCrawl = pCrawl.children[index]; 
     } 
    
     // mark last node as leaf 
     pCrawl.isEndOfWord = true; 
 } 
    
 // Returns true if key presents in trie, else false 
public static void search(String key) 
 { 
	 key = key.toLowerCase();
     int level; 
     int length = key.length(); 
     int index; 
     TrieNode pCrawl = root; 
    
     for (level = 0; level < length; level++) 
     { 
    	 iteration_for_search ++;
         index = key.charAt(level) - 'a'; 
    
         if (pCrawl.children[index] != null) { 
        	 comparison_for_search++;
         	pCrawl = pCrawl.children[index]; 
         }
         
     } 
    
     if (pCrawl != null && pCrawl.isEndOfWord) {
    	 comparison_for_search+=2;
    	 System.out.println(key + " --- Present in trie"); 
     }
   if(pCrawl == null || pCrawl.isEndOfWord == false) {
	   comparison_for_search++;
	   System.out.println(key + " --- Not present in trie"); 
   }
     
}    
 
public static void data() {
	comparisons = comparison_for_creation + comparison_for_search;
	iterations = iteration_for_creation + iteration_for_search;
	System.out.println("Number of comparisons to create trie " + comparison_for_creation);
	System.out.println("Number of iterations to create trie " + iteration_for_creation);
	System.out.println("Number of comparisons to search the trie " + comparison_for_search);
	System.out.println("Number of iterations to search the trie " + iteration_for_search);
	System.out.println();
}
    
 // Driver 
 public static void main(String args[]) { 
	 Instant start =Instant.now();
	
	 String URL = "https://www.tutorialspoint.com/java/lang/string_split.htm"; 
	 Parser word_list_article2 = new Parser(URL);
     Hashtable <String, Integer> ht = word_list_article2.get_word_list();

     Scanner scanner = new Scanner(System.in);
    
     root = new TrieNode(); 
     Trie trie = new Trie();
		
     Set <String> keySet = ht.keySet(); //to iterate the hash table
     
     // Construct trie 
     for(String keys : keySet){
    	 trie.insert(keys.toLowerCase());
     } 
     
    System.out.println("What keyword would you like to search?");
    while(!(scanner.hasNextInt())) {
		System.out.println();
		System.out.println("What keyword would you like to search? enter an integer to quit.");
		String request = scanner.nextLine();
		search(request);
	} 
	
	Instant end =Instant.now();

	System.out.println("__________Empirical Evidence BST : ______________");
	System.out.println("Parser Data : ");
	
	word_list_article2.get_data();

	System.out.println("Trie Data");
	trie.data();
	
	Duration timeElapsed = Duration.between(start, end);
	System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds ");
	System.out.println();
	System.out.println("N :  " + ht.size() + " words were added to the Trie.");
	System.out.println("Number of comparisons in this run in total : " + comparisons);
	System.out.println("Number of iterations in this run in total : " + iterations);
	
 } 
}
//This code is contributed by Sumit Ghosh via Geeksforgeeks
