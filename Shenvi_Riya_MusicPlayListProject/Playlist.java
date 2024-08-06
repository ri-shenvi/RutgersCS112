package music;

/** YOU WILL SUBMIT THIS CLASS FILE
 * 
 * @name Riya Shenvi
 * @date 2.16.24
 * @class 3

 * This class represents a playlist containing many songs.
 * 
 * The Playlist stores a Circular Linked List of SongNode objects.
 * 
 * @author Jeremy Hui
 * @author Vian Miranda
 * @author kmascola (updated January 2024)
 */
public class Playlist {
    private SongNode last; 	// reference to the last node in the Circular Linked List
    private int size; 		// the number of SongNodes (songs) in this Playlist

    /******DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you.
     * 
     * Default constructor.
     * Used to create an empty Playlist with no songs.
     * Initialized size to 0.
     */
    public Playlist() {
        this(null, 0);
    }

    /******DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you.
     * 
     * Constructor. 
     * 
     * This method is used to create a Playlist with a given CLL of SongNods and size.
     * 
     *  @param last: a reference of the last node in a CLL
     *  @param size: the current size of the CLL referenced by last.
     */
    public Playlist(SongNode last, int size) {
        this.last = last;
        this.size = size;
    }


    /******DO NOT**** UPDATE THESE METHODS
     * These method are already implemented for you.
     *  
     *  Accessor and Mutator methods
     *  
     *  The getter and setter methods provide public access and
     *  mutability to the CLL stored in this Playlist.
     */
    public SongNode getLast() {return last;}
    public void setLast(SongNode last) {this.last = last;}

    public int getSize() {return size;}
    public void setSize(int size) {this.size = size;}
    

    /** METHOD 1: Implement the add(Song) method of the Playlist
     *  (5 points)
     *  
     * This method adds Song to the end of this Playlist.
     * 
     * POSTCONDITION: this.last is a reference to a SongNode with Song s, 
     * 		the size of the Playlist accurately reflects the current number of
     * 		songs in this Playlist.
     * 
     * @param s: a Song object to be added to the end of this Playlist
     */
    public void add(Song s) {
    	// Create a new SongNode with the given Song s
        SongNode newNode = new SongNode();
        newNode.setSong(s);
        
        //If the playlist is not empty:
    	if (this.last != null) {
	    	//Create a link from newNode to last.next
	    	newNode.setNext(this.last.next());
	    	
	    	//Create a link from last to newNode
	    	this.last.setNext(newNode);
	    	
	    	this.last = newNode; // Update the last node of the Playlist to the new node
    	}
    	
    	//If the playlist is empty:
    	else if (this.last == null) {
	        // If the Playlist is empty, set the new node as the last node
    		this.last = newNode;
    		
    		// Set the next pointer of the new node to itself, to form a circular linked list
    		newNode.setNext(newNode);
	        }
    	
    	this.size++; //Increment the size of the Playlist
    }
    
    /** METHOD 2: Implement the add(int, Song) method of the Playlist
     *  (5 points)
     * 
     * This method adds Song to this Playlist at a given index, as long as
     * the given index is valid.
     * 
     * The first node of the circular linked list is at position 1, the 
     * second node is at position 2 and so forth.
     * 
     * Return true if the song can be added at the given position within the 
     * specified playlist (and thus has been added to the playlist), false 
     * otherwise (and the song shall not be added). Valid positions are 1 to (size + 1).
     * 
     * Increment the size of the playlist if the song has been successfully
     * added to the playlist.
     * 
     * 
     * POSTCONDITION: Song s is stored a the correct position in this Playlist if 
     * the given position is valid, the size of the Playlist accurately reflects 
     * the current number of songs in this Playlist. 
     * 
     * @param position: the position in the playlist to which the song 
     * is to be added, the first SongNode is at position 1 and the last
     * SongNode is at position size. 
     * @param s: a Song object to be added to this Playlist
     * @return true if Song s was added, false otherwise
     */
    public boolean add(int position, Song s) {	
    	// Check if the given position is valid (i.e., 1 <= position <= size + 1)
        if (position < 1 || position > this.size + 1) {
            return false;
        }

        // Create a new SongNode with the given Song s
        SongNode newNode = new SongNode();
        newNode.setSong(s);

        // If the Playlist is empty, set the new node as the last node
        if (this.last == null) {
            this.last = newNode;
            // Set the next pointer of the new node to itself, to form a circular linked list
            newNode.setNext(newNode);
        } 
        else {
            // If the position is 1, add the new node at the beginning of the Playlist
            if (position == 1) {
                // Set the next pointer of the new node to the current first node
                newNode.setNext(this.last.next());
                // Set the next pointer of the current last node to the new node
                this.last.setNext(newNode);
            } 
            else if (position == this.size + 1) {
            	newNode.setNext(this.last.next());
            	this.last.setNext(newNode);
            	this.last = newNode;
            }
            else {
                // Otherwise, add the new node at the given position in the Playlist
                SongNode currentNode = this.last.next();
                for (int i = 1; i < position - 1; i++) {
                    currentNode = currentNode.next();
                }
                // Set the next pointer of the new node to the current node at the given position
                newNode.setNext(currentNode.next());
                // Set the next pointer of the current node at the given position to the new node
                currentNode.setNext(newNode);
            }
        }

        // Increment the size of the Playlist
        this.size++;

        return true;
    }
    
    /** METHOD 3: Implement the remove(Song) method of the Playlist
     *  (5 points)
     * 
     * This method removes all instances of a song from this playlist, if the song exists. 
     *
     * Use the .equals() method of the Song class to check if an element of 
     * the circular linkedlist matches the specified song.
     * 
     * Return true if the song is found in the playlist (and thus has been 
     * removed), false otherwise (and thus nothing is removed). 
     * 
     * Decrease the playlist size if the song has been successfully removed from 
     * the playlist to ensure the size attribute accurately reflects the currect
     * number of songs in the playlist. 
     * 
     * POSTCONDITION: The order of the songs in this Playlist remains unchanged, 
     * the size of this Playlist accurately reflects the current number of songs 
     * in this Playlist. 
     * 
     * @param s: the song to remove.
     * @return true if the song was present in the playlist and therefore has 
     * been removed, false otherwise.
     */
    public boolean remove(Song s) {
        // If the Playlist is empty, return false
        if (this.last == null) {
            return false;
        }
        //if only 1 node
        if (this.size == 1) {
        	this.last = null;
        	this.size = 0;
        	return true;
        }
        
        boolean removed = false;

        // If the first node is the node to be removed
        if (this.last.next().song().equals(s)) {
            // If there is only one node in the Playlist, set the last node to null
            if (this.last == this.last.next()) {
                this.last = null;
            } 
            else {
            	SongNode tempNode = this.last.next();
            	this.last.setNext(this.last.next().next());
            	tempNode.setNext(null);
            }
            this.size--;
            removed = true;
        }

        // Initialize a variable to keep track of the previous node
        SongNode previousNode = this.last;
        // Initialize a variable to keep track of the current node
        SongNode currentNode = this.last.next();
        
        // Iterate through the Playlist
        while (currentNode != this.last) {
            // If the current node is the node to be removed
            if (currentNode.song().equals(s)) {
                previousNode.setNext(currentNode.next());
                currentNode.setNext(null);
       
                // Decrement the size of the Playlist
                this.size--;
                removed = true;
                break;
            }
            // Otherwise, update the previous node and the current node
            previousNode = currentNode;
            currentNode = currentNode.next();
        }
        
        //If the last node must be removed
        if (this.last.song().equals(s)) {
        	SongNode secondToLast = this.getSecondToLast();
        	secondToLast.setNext(this.last.next());
        	this.last = secondToLast;
        	
        	this.size--;
        	removed = true;
        }

        // If the song is not found in the playlist, return false
        return removed;
    }

     //Helper method to get the second-to-last node in the Playlist
    private SongNode getSecondToLast() {
        // If the Playlist has only one node
        if (this.last == this.last.next()) {
            return null;
        }
        // Initialize a variable to keep track of the current node
        SongNode currentNode = this.last.next();
        // Iterate through the Playlist until the second-to-last node is found
        while (currentNode.next() != this.last) {
            currentNode = currentNode.next();
        }
        // Return the second-to-last node
        return currentNode;
    }
    
    
}
