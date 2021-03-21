// Code created and modified by Jeffrey Torres, Emily Peguero Marmolejos, and Tashi Dolma
// Please see the text files attached within the folder
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class MainHashTable {
	// Size of the hash table (this is n on the chart)
	private static final int DEFAULT_TABLE_SIZE = 10000;
	
    public static void main(String[] args) throws FileNotFoundException, IOException {	
    	// Counts number of operations
    	int operationCount = 0;
    	try {
    	Instant start = Instant.now();
    	// Here, the file could be any of the three text files, they are provided within the folder
    	BufferedReader read = new BufferedReader(new FileReader("patient_categories.txt"));
    	// This will cause the table not to store the first row of data, whose columns are just the label of its contents
        read.readLine();
    	String thisLine = null;
    	
    	QuadraticProbingHashTable table = new QuadraticProbingHashTable(DEFAULT_TABLE_SIZE);
    	
    	// Stores each line of the text file into the table
    	int i = 0;
    	while((thisLine = read.readLine()) != null) {
    		operationCount++;
    		table.insert(Integer.toString(i++), thisLine);
    	}
    	
    	table.printHashTable();
    	
    	Instant end = Instant.now();
    	// Total number of operations
    	System.out.println("Number of operations: " + (operationCount + table.getOperationCount()));
    	Duration interval = Duration.between(start, end);
    	System.out.println("Execution time: " + interval.toMillis() + " milliseconds");
    
    	read.close();	
    	}
    	catch (Exception e) {
    		System.out.print("The file couldn't be found...");
    	}
    }
}