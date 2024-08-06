package structures;
import java.io.*;
import java.util.Scanner;

/**
 * Indexer class to create a HashTable of KeyValues
 * 
 * @name Riya Shenvi
 * @date 5.24.24
 * @period 3
 */
public class Indexer {
	private HashTable table;
	
	public Indexer(String filename) throws IOException, NoMoreTokensException {
		table = new HashTable();
		buildInvertedIndex(filename);
	}
	
	/**
	 * Fills a HashTable of KeyValue objects to act as the storage unit for
	 * 		this InvertedIndex.  The HashTable and KeyValues are built using
	 * 		Strings from the File filename passed by parameter.
	 * @param filename the name of a txt file to be parsed into KeyValue objects
	 * 		and indexed into a HashTable
	 * 
	 * 6 points
	 */
	private void buildInvertedIndex(String filename) 
			throws IOException, NoMoreTokensException{
		File input = new File(filename);
		if (!input.exists()) {
			throw new IOException("File doesn't exist");
		}

		BufferedReader inputLocal = new BufferedReader(new FileReader(input));

		String currentLine = inputLocal.readLine();
		while(currentLine != null) {
			Tokenizer splitLine = new Tokenizer(": ", currentLine);
			String subject = "";
		
			if (splitLine.hasToken()) {
				subject = splitLine.nextToken();
				//System.out.println(subject);
			}
		
			else {
				continue;
			}
			while (splitLine.hasToken()) {
				String currentKey = splitLine.nextToken().toLowerCase();
				if (getKeyValue(currentKey) != null) {
					getKeyValue(currentKey).addValue(subject);
				}
				else {
					KeyValue newKey = new KeyValue(currentKey);
					newKey.addValue(subject);
					table.insertKeyValue(index(currentKey), newKey);
				}
			}
			currentLine = inputLocal.readLine();
			}
		inputLocal.close();
	}
	
	/**
	 * Returns the KeyValue object in the HashTable of this Indexer
	 * 		with the key of key. If no such object exists, return null.
	 * @param key the key of a KeyValue object
	 * @return the KeyValue object for which key exist, null if no such
	 * 		object exists in the HashTable of this Indexer.
	 * 
	 * 2 points
	 */
	public KeyValue getKeyValue(String key) {
		int index = index(key);
        return table.lookUpKey(index, key);
	}
    
	/**
	 * Determines the index at which String key should be found/located 
	 * 		in HashTable for this Indexer as calculated with the hashCode 
	 * 		method of the KeyValue class and the current size of the 
	 * 		HashTable attribute of this Indexer object.
	 * 
	 * @param key - A String of the key for a KeyValue object
	 * @return a valid index in the HashTable attribute of this Indexer
	 * 		object as calculated with the KeyValue hashCode and the size of
	 * 		the HashTable.
	 * 
	 * 2 points
	 */
	public int index(String key){
		return Math.abs(new KeyValue(key).hashCode() % table.size());
	}
	
    /**
     * @return a String representation of this InvertedIndex...which is being
     * 		stored using a HashTable
     */
    public String toString() {
    	return table.toString();
    }
}
