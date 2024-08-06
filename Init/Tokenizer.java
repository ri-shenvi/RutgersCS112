package structures;
import java.util.ArrayList;
/**
 * A String Tokenizer class
 * 
 * @author Riya Shenvi
 * @date 5.10.24
 * @period 3
 */
public class Tokenizer{
	private ArrayList<String> tokens;

    /**
     * Constructor for the Tokenizer object.  A tokenizer takes in a String and
     * 	delimiters and splits the String into tokens based on the demlimiters.
     * The individual tokens should be stored in the tokens arraylist in the 
     * 	order for which they exist in the String passed by parameter.
     * @param delims a String of character for which string should be split
     * @param string the String to be split into tokens
     * 
     * 5 points
     */
	public Tokenizer(String delims, String string){
		if (delims == null) {
            throw new IllegalArgumentException("Delimiters cannot be null or empty");
        }
        this.tokens = new ArrayList<String>();
        tokenize(string, delims);
    }
	
	 /**
     * Tokenize the input string based on the delimiters
     * @param string the input string to be tokenized
     */
    private void tokenize(String string, String delims) {
    	int start = 0;
        while (start < string.length()) {
            int end = getDelimiterIndex(string, start, delims);
            if (end == -1) {
                end = string.length();
            }
            String token = string.substring(start, end).trim();
            if (!token.isEmpty()) {
                tokens.add(token);
            }
            start = end + 1;
        }
    }

    /**
     * Returns the index of the first delimiter found in the string starting from the given index
     * @param string the input string
     * @param startIndex the starting index to search for delimiters
     * @return the index of the first delimiter found, or -1 if no delimiter is found
     */
    private int getDelimiterIndex(String string, int startIndex, String delims) {
    	for (int i = startIndex; i < string.length(); i++) {
            if (delims.indexOf(string.charAt(i)) != -1) {
                return i;
            }
        }
        return -1;
    }

    /**
	 * Returns one word at a time from the String that was split
	 * 		into tokens
	 * @return the first token from the tokens list or 
	 * 			null if the list is empty
	 */
    public String nextToken() throws NoMoreTokensException{
    	if(this.hasToken())
    		return tokens.remove(0);
    	throw new NoMoreTokensException();
    }
    
    /**
     * @return true if more tokens exist
     */
    public boolean hasToken() 
    {	return tokens.size() > 0;  }
    
    
    public ArrayList<String> returnToken(){
    	return tokens;
    }
   
}



