package structures;

import java.util.ArrayList;
/**
 * SimpleSearch class to search an inverted index
 * 
 * @name Riya Shenvi
 * @date 5.24.24
 * @period 3
 */
public class SimpleSearch {
	
	/**
	 * Searches the InvertedIndex indexer for any and all keywords found in 
	 * 		the String query and returns an ArrayList of all subjects 
	 * 		related to the keywords in the String query. 
	 * If the query results in a failed search return null.
	 * @param query a String that includes one or more key words.
	 * @param indexer an Inverted Indexer object to be searched
	 * @return an ArrayList of values or "subjects" that relate to any and all 
	 * 		of the "key" words in the String query. Or null if the key words in
	 * 		query result in a failed search. ie. key NOT in the InvertedIndex
	 * PRECONDTION: query may include upper and lower case letter, period, 
	 * 		comma, semicolon, colon, apostrophe, question mark, exclamation, 
	 * 		and spaces. But no other characters.
	 * POSTCONDITION: the ArrayList returned by the method query does NOT
	 * 		contain any duplicate values and may be in any order.
	 * 
	 * 5 points
	 */
    public static ArrayList<String> query(String query, Indexer indexer) 
    		throws NoMoreTokensException{	
    	Tokenizer queryWords = new Tokenizer(".,;:'!? ", query);
        ArrayList<String> subjectsList = new ArrayList<String>();
        String currentQuery = "";
        
        
        while (queryWords.hasToken()) {
        	currentQuery = queryWords.nextToken().toLowerCase();
        	KeyValue queryKeyvalue = indexer.getKeyValue(currentQuery);
        	if (queryKeyvalue != null) {
        		for (int i = 0; i < queryKeyvalue.getValues().size(); i++) {
        			if (!subjectsList.contains(queryKeyvalue.getValues().get(i))) {
        				subjectsList.add(queryKeyvalue.getValues().get(i));
        			}
        		}
        	}
        }
       
        if (subjectsList.size() == 0)
        return null;
        
        return subjectsList;
    }
    
    }
