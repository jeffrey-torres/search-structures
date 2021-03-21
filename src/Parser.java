// Code created and modified by Emily Peguero Marmolejos, Tashi Dolma, and Jeffrey Torres
import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.util.*;

public class Parser {
	private String palabras = "";
	private String[] word_list;
	private Document webpage;
	private Hashtable<String, Integer> wordsCount = new Hashtable<String, Integer>();
	
	public int comparisons = 0;
	public int iterations = 0;
	
	public Parser(String url){
		  try {
			webpage = Jsoup.connect(url).get();
			palabras = webpage.text();// this showed me all the text that could be abstracted from the webpage;about 3,834 words.
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		  String word_listing[] = palabras.split("[^a-zA-Z]+");
		  
		  this.word_list = word_listing;	
	}
	public Hashtable<String, Integer> get_word_list() {
	    for (String s : this.word_list){
	    	iterations ++;
	        if (this.wordsCount.containsKey(s)) {
	        	this.wordsCount.replace(s, this.wordsCount.get(s) + 1);
	        	comparisons ++;
	        }
	        else this.wordsCount.put(s, 1);
	    }
	    return this.wordsCount;
	
	}
	public void get_data() {
		System.out.println("Number of iterations to get data table : " + iterations);
		System.out.println("Number of comparisons to get data table : " + comparisons);
	}
}

		 
