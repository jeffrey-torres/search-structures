/* Code from https://www.sanfoundry.com/java-program-implement-hash-tables-quadratic-probing/
 * Hashing by open addressing using quadratic probing
 * Modified by Jeffrey Torres, Emily Peguero Marmolejos, and Tashi Dolma
*/
public class QuadraticProbingHashTable {   
    private int currentSize, maxSize, operationCount = 0;       
    private String[] keys;   
    private String[] values;
 
    public QuadraticProbingHashTable(int capacity) {
        currentSize = 0;
        maxSize = capacity;
        keys = new String[maxSize];
        values = new String[maxSize];
    }  
 
    public int getSize() { return currentSize; }
 
    public boolean isFull() { return currentSize == maxSize; }
 
    public boolean isEmpty() { return getSize() == 0; }
 
    public boolean contains(String key) { return get(key) !=  null; }
 
    private int hash(String key) { return key.hashCode() % maxSize; }    
 
    // Retrieve the number of operations
    public int getOperationCount() { return operationCount; }
    
    // Clear the hash table
    public void makeEmpty() {
        currentSize = 0;
        keys = new String[maxSize];
        values = new String[maxSize];
    }
    
    public void insert(String key, String val) {                
        int temp = hash(key);
        int i = temp, h = 1;
        do {
            if (keys[i] == null) {
            	operationCount++;
                keys[i] = key;
                values[i] = val;
                currentSize++;
                return;
            }
            if (keys[i].equals(key)) { 
            	operationCount++;
                values[i] = val; 
                return; 
            }            
            i = (i + h * h++) % maxSize;
            operationCount++;
        } while (i != temp);       
    }
 
    // Gets the value for a certain key
    public String get(String key) {
        int i = hash(key), h = 1;
        while (keys[i] != null) {
        	operationCount++;
            if (keys[i].equals(key)) {
            	operationCount++;
                return values[i];
            }
            i = (i + h * h++) % maxSize;
            operationCount++;
            System.out.println("i "+ i);
        }            
        return null;
    }
 
    // Remove the key and its value
    public void remove(String key) {
        if (!contains(key)) {
        	operationCount++;
            return;
        }
 
        // Find the position of the key and remove it
        int i = hash(key), h = 1;
        while (!key.equals(keys[i])) {
        	operationCount++;
            i = (i + h * h++) % maxSize;
            operationCount++;
        }
        keys[i] = values[i] = null;
        
       
        // Re-hash all of the keys        
        for (i = (i + h * h++) % maxSize; keys[i] != null; i = (i + h * h++) % maxSize) {
        	operationCount++;
            String temp1 = keys[i], temp2 = values[i];
            keys[i] = values[i] = null;
            currentSize--;  
            insert(temp1, temp2);            
        }
        currentSize--;        
    }   
    
    public void printHashTable() {
        System.out.println("Hash Table: ");
        for (int i = 0; i < maxSize; i++) {
            if (keys[i] != null) {
            	operationCount++;
                System.out.println(keys[i] + ", "+ values[i]);
            }
        }
        System.out.println();
    }   
}