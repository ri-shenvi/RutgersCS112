package music;

import java.util.*;
import java.util.ArrayList;

/** YOU WILL SUBMIT THIS CLASS FILE
 * 
 * @name Riya Shenvi
 * @date 2.16.24
 * @class 3
 * 
 * This class represents a library of song playlists. This is essentially
 * the Music Playlist App.
 *
 * An ArrayList of Playlist objects represents the various playlists 
 * within one's library.
 * 
 * @author Jeremy Hui
 * @author Vian Miranda
 * @author kmascola (updated Jan 2024)
 */
public class PlaylistLibrary {

    private ArrayList<Playlist> playlistLibrary; // contains various playlists

    /**
     * DO NOT EDIT!
     * Default constructor for an empty library. 
     */
    public PlaylistLibrary() {
        this(null);
    }
    
    /**
     * DO NOT EDIT!
     * Constructor for Library. Creates a Playlist Library from
     * a given ArrayList of Playlist objects.
     * 
     * @param playlistLibrary passes in ArrayList of playlists
     */
    public PlaylistLibrary(ArrayList<Playlist> playlistLibrary) {
        this.playlistLibrary = playlistLibrary;
    }
 
    /** METHOD 4: Implement the createPlaylist(String) method of the PlaylistLibrary
     *  (10 points)
     *  
     * PRECONDITION: filename is the name of a valid CSV file such as "playlist1.csv"
     * PRECONDITION: the CSV file is correctly formatted as described in the write-up.  
     *  
     * This helper method reads the songs from a single csv file passed by parameter, 
     * and creates a Playlist from it. 
     * 
     * This method is essential for turning csv files into Playlist objects. Without 
     * this method the Driver (or Playlist application) will not work.
     * 
     *  Within a valid csv file, each song is on a different line with Song Name,
     *  artist, year, popularity, and directory link separated by commas. Hence, csv
     *  or comma separated values.
     * 
     * Pseudocode:
     * 1. Open the file using StdIn.setFile(filename);
     * 
     * 2. Instantiate a Playlist object.
     * 
     * 3. While there are still lines in the input file:
     *      1. read a song from the file using StdIn.readline().
     *      2. create an object of type Song with the song information
     *      3. insert the Song object at the END of the Playlist (use the reference from step 2)
     * 
     * 4. Return the Playlist object
     * 
     * Notes:
     * 
     * StdIn.setFile() is a static method that safely creates a file Scanner within
     * the StdIn class.  The StdIn class then acts like an iterator.
     * 
     * Each line of the input file has the following format:
     *      songName,artist,year,popularity,link     
     * To read a line, use StdIn.readline() which returns a line of the file
     * as a String. This is similar to the Scanner nextLine() method.
     * 
     * Then use the String method split(String delims) which splits a String at each 
     * of delims passed by parameter and returns an array of String tokens.  This is
     * silimar to using a StringTokenizer. 
     * 
     * Some of the tokens need to be stored as int values in the Song object.
     * 
     * If the playlist is empty, return a Playlist object with null for its last, 
     * and 0 for its size. The Playlist class has a constructor for this.
     * 
     * The provided input files (csv files) have Songs listed in sorted order,
     * decreasing popularity order. You may modify these or create your own csv 
     * playlist files and use the provided sortPlaylist method of this class
     * after the Playlist has been created using this createPlaylist method.
     *  
     * @param filename: the playlist information input file
     * @return a Playlist object, which contains a reference to the LAST song 
     * in the ciruclar linkedlist playlist and the size of the playlist.
     */
    private Playlist createPlaylist(String filename) {
        // Open the file using StdIn.setFile(filename);
        StdIn.setFile(filename);

        // Instantiate a Playlist object
        Playlist playlist = new Playlist();

        // While there are still lines in the input file
        while (StdIn.hasNextLine()) {
            // Read a line from the file using StdIn.readLine()
            String[] tokens = StdIn.readLine().split(",");

            // Check if the line is not empty
            if (tokens.length > 0) {
                // Create a Song object with the song information
                Song song = new Song(tokens[0].trim(), tokens[1].trim(), Integer.parseInt(tokens[2].trim()),
                        Integer.parseInt(tokens[3].trim()), tokens[4].trim());

                // Insert the Song object at the END of the Playlist
                playlist.add(song);
            }
        }

        // Return the Playlist object
        return playlist;
    }
    
    /** METHOD 5: Implement the addAllPlaylists(String[]) method of the PlaylistLibrary
     *  (5 points)
     *  
     * Adds the playlists from many files into the playlistLibrary
     * 
     * 1. Initialize the playlistLibrary if it has not already been initialized
     * 
     * 2. For each of the filenames
     *       add the playlist into playlistLibrary
     * 
     * Use the addPlaylist() method which has been provided below. 
     * 
     * @param filenames: an array of the filenames of playlists that should be 
     * added to the library
     * PRECONDITION: filenames is not null
     */
    public void addAllPlaylists(String[] filenames) {
        // Initialize the playlistLibrary if it has not already been initialized
        if (this.playlistLibrary == null) {
            this.playlistLibrary = new ArrayList<>();
        }

        // For each of the filenames
        for (String filename : filenames) {
            // Create a Playlist object from the file
            Playlist playlist = createPlaylist(filename);

            // Add the Playlist object to the playlistLibrary
            playlistLibrary.add(playlist);
        }
    }


    /** METHOD 6: Implement the reversePlaylist(int) method of the PlaylistLibrary
     *  (10 points)
     *  
     * This method reverses the Playlist located at playlistIndex
     * 
     * Each node in the circular linked list will point to the element that 
     * came before it.
     * 
     * POSTCONDITION: After the list is reversed, the Playlist located at 
     * playlistIndex will reference the first SongNode in the original 
     * Playlist (new last). The Playlist located at playlistIndex will still 
     * include all of the original songs, but in reverse order.  
     * 
     * If playlistIndex is not valid, no change is made.
     * 
     * @param playlistIndex: the playlist to reverse 
     */
    public void reversePlaylist(int playlistIndex) {
        // Check if the playlistIndex is valid
        if (playlistIndex >= 0 && playlistIndex < this.playlistLibrary.size()) {
            // Get the Playlist object at the specified index
            Playlist playlist = this.playlistLibrary.get(playlistIndex);

            // If the Playlist is empty or has only one node, return
            if (playlist.getSize() <= 1) {
                return;
            }

            // Initialize two variables to keep track of the current and previous nodes
            SongNode currentNode = playlist.getLast().next();
            SongNode previousNode = playlist.getLast();
            
            SongNode t = playlist.getLast().next();  //Create a temp for the first node

            // Iterate through the Playlist, reversing the pointers
            while (currentNode != playlist.getLast()) {
                // Set the next pointer of the current node to the previous node
                SongNode temp = currentNode.next();
                currentNode.setNext(previousNode);

                // Update the current and previous nodes
                previousNode = currentNode;
                currentNode = temp;
            }
            
            // Set the last node to the old first node
            playlist.getLast().setNext(previousNode);
            playlist.setLast(t);
        }
    }

    /** METHOD 7: Implement the mergePlaylists(int, int) method of the PlaylistLibrary
     *  (10 points)
     *  
     * This method creates a new (third) playlist by merging two playlists and stores it
     * at the end of the playlistLibrary.
     * 
     * Using the sortPlaylist method provided in this class, the new (third) Playlist should
     * be sorted to have songs in decreasing popularity order. 
     * 
     * If either playlistIndex1 or playlistIndex2 is not valid, do nothing.
     *  
     * POSTCONDITION: After the lists have been merged:
     *  - the new merged Playlist at the end of the playlistLibrary
     *  - all new merged Playlist is sorted in decreasing popularity order
     *  - the Playlist located at playlistIndex1 and playlistIndex2 are unchanged 
     * 
     * @param playlistIndex1 the first playlist to merge into one playlist
     * @param playlistIndex2 the second playlist to merge into one playlist
     */
    public void mergePlaylists(int playlistIndex1, int playlistIndex2) {
        if (playlistIndex1 < 0 || playlistIndex1 >= playlistLibrary.size() || playlistIndex2 < 0 || playlistIndex2 >= playlistLibrary.size()) {
            return;
        }
        Playlist playlist1 = playlistLibrary.get(playlistIndex1);
        Playlist playlist2 = playlistLibrary.get(playlistIndex2);
   
        Playlist mergedPlaylist = new Playlist();
        
        if (playlist1.getSize() < 1 && playlist2.getSize() >= 1) {
        	mergedPlaylist = playlist2;
        	playlistLibrary.add(mergedPlaylist);
        	return;
        }
        if (playlist2.getSize() < 1 && playlist1.getSize() >= 1) {
        	mergedPlaylist = playlist1;
        	playlistLibrary.add(mergedPlaylist);
        	return;
        }
        if (playlist2.getSize() < 1 && playlist1.getSize() < 1) {
        	playlistLibrary.add(mergedPlaylist);
        	return;
        }
        
        SongNode node1 = playlist1.getLast().next();
        SongNode node2 = playlist2.getLast().next();
        while (node1 != playlist1.getLast() || node2 != playlist2.getLast()) {
            if (node1 == playlist1.getLast()) {
                mergedPlaylist.add(node2.song());
                node2 = node2.next();
            } 
            else if (node2 == playlist2.getLast()) {
                mergedPlaylist.add(node1.song());
                node1 = node1.next();
            } 
            else {
                if (node1.song().getPopularity() > node2.song().getPopularity()) {
                    mergedPlaylist.add(node1.song());
                    node1 = node1.next();
                } else {
                    mergedPlaylist.add(node2.song());
                    node2 = node2.next();
                }
            }
        }
        node1 = playlist1.getLast();
        node2 = playlist2.getLast();
 
        if(node1.song().getPopularity() > node2.song().getPopularity()) {
        	mergedPlaylist.add(node1.song());
        	mergedPlaylist.add(node2.song());
        }
        else{
        	mergedPlaylist.add(node2.song());
        	mergedPlaylist.add(node1.song());
        }
        //SORT PLAYLIST
        if ( mergedPlaylist.getSize() <= 1) 
        	return; //an Empty Playlist or a Playlist with one Song is already sorted
                    
        SongNode tail = mergedPlaylist.getLast();
        SongNode head = tail.next();
        tail.setNext(null); //Splits to be singly linked list
        
        head = mergeSort(head); //results in a sorted singly linked list
        
        tail = head.next(); //finds the new tail node         
        while (tail.next() != null) {
            tail = tail.next();
        }
        
        tail.setNext(head); //reset as a CLL
        mergedPlaylist.setLast(tail); //reassign the CLL of this Playlist 
        
        
        playlistLibrary.add(mergedPlaylist);
    }

    /** METHOD 8: Implement the shufflePlaylist(int) method of the PlaylistLibrary
     *  (10 points)
     * 
     * This method shuffles a specified playlist using the following procedure:
     * 
     * 1. Create a new Playlist to store the shuffled playlist in.
     * 
     * 2. While the size of the original Playlist is not 0
     * 		1. randomly generate a number using Math.random() for a valid song position 
     * 		2. Remove the corresponding Song from the original playlist
     * 		3. insert the song into the END of the new playlist 
     * 			(recall the first Song in a Playlist is at position 1) 
     * 
     * 3. Update the old playlist with the new shuffled playlist.
     * 
     * If playlistIndex is not valid, no change is made.
     * 
     * @param index: the index of the Playlist to shuffle in playlistLibrary
     * 
     * POSTCONDITION: The Playlist at index playlistIndex has 
     */
    public void shufflePlaylist(int playlistIndex) {
        if (playlistIndex < 0 || playlistIndex >= playlistLibrary.size()) {
            return;
        }
        Playlist playlist = playlistLibrary.get(playlistIndex);
        Playlist shuffledPlaylist = new Playlist();
        while (playlist.getSize() > 0) {
            int randomPosition = (int) (Math.random() * playlist.getSize());
            SongNode curNode = playlist.getLast();
            for(int i=0; i==randomPosition; i++) {
            	curNode = curNode.next();
            }
            playlist.remove(curNode.song());
            shuffledPlaylist.add(curNode.song());
        }
        playlistLibrary.set(playlistIndex, shuffledPlaylist);
    }

    /////****** DO NOT UPDATE ANY METHODS BELOW THIS COMMENT ******\\\\\
    
    /******DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you. 
     * 
     * Adds a new playlist into the song library at a certain index.
     * 
     * Creates the playlistLibrary if it doesn't exist.  Calls createPlayList() 
     * with a file containing song information and Adds the newly created Playlist 
     * into the playlistLibrary at the given index.  If the given index is not in the 
     * range [0, playlistLibrary.size()] then the new Playlist will be inserted at
     * the end of the current playlistLibrary.
     * 
     * @param playlistIndex: the index of the location where the playlist will 
     * be added, if the playlistIndex is out of bounds the playlist will be added to
     * the end of the playlistLibrary.
     * @param filename: the playlist information input file
     * 
     * NOTE: This method requires you to complete createPlaylist()
     */
    public void addPlaylist(int playlistIndex, String filename) {    
        /* DO NOT UPDATE THIS METHOD */
        if ( playlistLibrary == null ) {
            playlistLibrary = new ArrayList<Playlist>();
        }
        if ( playlistIndex >= 0 && playlistIndex <= playlistLibrary.size() ) {
            playlistLibrary.add(playlistIndex, createPlaylist(filename));
        } else {
            playlistLibrary.add(createPlaylist(filename));
        }        
    }

    /******DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you.
     * 
     * Removes a Playlist from the playlistLibrary located at the 
     * given playlistIndex, if playlistIndex is a valid index of the
     * playlistLibrary ArrayList.
     * 
     * @param playlistIndex the index of the Playlist to remove
     * @return true if the playlist has been deleted, false otherwise
     */
    public boolean removePlaylist(int playlistIndex) {
        /* DO NOT UPDATE THIS METHOD */
        if ( playlistLibrary == null || playlistIndex >= playlistLibrary.size() ) {
        	StdOut.printf("Index %d is invalid. No Playlist removed.\n", playlistIndex);
        	return false;
        }
        playlistLibrary.remove(playlistIndex);
        return true;
    }
    
    /******DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you.
     *  
     * This method adds a song to a specified playlist at a given position.
     * 
     * @param playlistIndex the index where the playlist will be added
     * @param position the position in the playlist to which the song 
     * is to be added 
     * @param song the song to add
     * @return true if the song can be added and therefore has been added, 
     * false otherwise. 
     * 
     * NOTE: This method requires you to complete the Playlist class method
     * add(int, Song)
     */
    public boolean insertSong(int playlistIndex, int position, Song song) {
        /* DO NOT UPDATE THIS METHOD */
    	if(playlistIndex < 0 || playlistIndex >= playlistLibrary.size())
    	{
    		StdOut.printf("Index %d is invalid. %s was not added.\n", playlistIndex, song );
    		return false;
    	}    	
    	return playlistLibrary.get(playlistIndex).add(position, song);
    }

    /******DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you.
     *  
     * This method removes a song at a specified playlist, if the song exists. 
     *
     * @param playlistIndex: the index of a Playlist within the playlistLibrary where 
     * the song is to be removed.
     * @param song: the Song to remove.
     * @return true: if the song is present in the Playlist at the given 
     * playlistIndex and therefore has been removed, false otherwise.
     * 
     * NOTE: This method requires you to complete the Playlist class method
     * remove(Song)
     */
    public boolean removeSong(int playlistIndex, Song song) {
        /* DO NOT UPDATE THIS METHOD */
    	if(playlistIndex < 0 || playlistIndex >= playlistLibrary.size())
    	{
    		StdOut.printf("Index %d is invalid. %s was not removed.\n", playlistIndex, song );
    		return false;
    	}
        return playlistLibrary.get(playlistIndex).remove(song);       
    }

    /******DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you.
     *  
     * This method sorts a specified Playlist using a merge sort algorithm.
     * 
     * POSTCONDITION: The Playlist located at the corresponding playlistIndex
     * is in decreasing order based on popularity.
     * 
     * @param playlistIndex the playlist to sort
     */
    public void sortPlaylist ( int playlistIndex ) {
        /* DO NOT UPDATE THIS METHOD */
    	if(playlistIndex < 0 || playlistIndex >= playlistLibrary.size())
    	{
    		StdOut.printf("Index %d is invalid. No Playlist has been sorted.\n", playlistIndex );
    		return;
    	}
        Playlist playlist = playlistLibrary.get(playlistIndex);
        if ( playlist.getSize() <= 1) 
        	return; //an Empty Playlist or a Playlist with one Song is already sorted
                    
        SongNode tail = playlist.getLast();
        SongNode head = tail.next();
        tail.setNext(null); //Splits to be singly linked list
        
        head = mergeSort(head); //results in a sorted singly linked list
        
        tail = head.next(); //finds the new tail node         
        while (tail.next() != null) {
            tail = tail.next();
        }
        
        tail.setNext(head); //reset as a CLL
        playlist.setLast(tail); //reassign the CLL of this Playlist 
    }

    private static SongNode mergeSort(SongNode head) {
        /* DO NOT UPDATE THIS METHOD */
        if (head == null || head.next() == null) {
            return head;
        }
        SongNode mid = getMid(head);
        SongNode midNext = mid.next();
        mid.setNext(null);
        SongNode left = mergeSort(head);
        SongNode right = mergeSort(midNext);
        SongNode sorted = sort(left, right);
        return sorted;
    }

    private static SongNode getMid(SongNode head) {
        /* DO NOT UPDATE THIS METHOD */
        if (head == null) return null;
        SongNode pointer1 = head, pointer2 = head;
        while (pointer1.next() != null && pointer1.next().next() != null) {
            pointer2 = pointer2.next();
            pointer1 = pointer1.next().next();
        }
        return pointer2;
    }

    private static SongNode sort(SongNode left, SongNode right) {
        /* DO NOT UPDATE THIS METHOD */
        SongNode sorted = null;
        if (left == null) return right;
        if (right == null) return left;
        if (left.song().compareTo(right.song()) >= 0) {
            sorted = left;
            sorted.setNext(sort(left.next(), right));
        } else {
            sorted = right;
            sorted.setNext(sort(left, right.next()));;
        }
        return sorted;
    }

    /******DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you.
     * 
     * Plays playlist by index; can use this method to debug.
     * 
     * @param playlistIndex the playlist to print
     * @param repeats number of times to repeat playlist
     * @throws InterruptedException
     */
    public void playPlaylist(int playlistIndex, int repeats) {
        /* DO NOT UPDATE THIS METHOD */
    	if(playlistIndex < 0 || playlistIndex >= playlistLibrary.size())
    	{
    		StdOut.printf("Index %d is invalid. No Playlist to play.\n", playlistIndex );
    		return;
    	}    	
        final String NO_SONG_MSG = " has no link to a song! Playing next...";
        Playlist playlist = playlistLibrary.get(playlistIndex);
        if (playlist.getLast() == null) {
            StdOut.printf("Index %d has an empty Playlist. Nothing to play.", playlistIndex);
            return;
        }

        SongNode ptr = playlist.getLast().next(), first = ptr;
        do {
            StdOut.print("\n" + ptr.song().toString());
            if (ptr.song().getLink() != null) {
                StdAudio.play(ptr.song().getLink());
                StdOut.print(" ***next*** ");
            }
            else {
                StdOut.print(NO_SONG_MSG);
                try {
                    Thread.sleep(2000);
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
                StdOut.print(" ***next*** ");
            }

            ptr = ptr.next();
            if (ptr == first) repeats--;
        } while (ptr != first || repeats > 0);
    }

    /******DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you.
     * 
     * Prints the Playlist in the playlistLibrary at the given index; can 
     * use this method to debug.
     * 
     * @param playlistIndex the playlist to print
     */
    public void printPlaylist(int playlistIndex) {
    	if(playlistIndex < 0 || playlistIndex >= playlistLibrary.size())
    	{
    		StdOut.printf("Index %d is invalid. Playlist %d does not exist.\n", playlistIndex, playlistIndex);
    		return;
    	}
        Playlist playlist = playlistLibrary.get(playlistIndex);
    	StdOut.printf("%nPlaylist at index %d (%d song(s)):%n", playlistIndex, playlist.getSize());
        if (playlist.getLast() == null) {
            StdOut.println("EMPTY");
            return;
        }
        SongNode ptr = playlist.getLast();
        int songNumber = 1;
        String ret = new String();
        do {
        	ptr = ptr.next();
        	  ret += ptr.song().toString();
        	if(ptr != playlist.getLast())
        		ret += " -> ";
        	if(ptr != playlist.getLast() && songNumber % 3 == 0)
        		ret += "\n";
        	songNumber++;
        }while(ptr != playlist.getLast());
        StdOut.println(ret+ " -> POINTS TO FRONT");
    }

    public void printLibrary() {
        if (playlistLibrary.size() == 0) {
            StdOut.println("\nYour library is empty!");
        } else {
                for (int i = 0; i < playlistLibrary.size(); i++) {
                printPlaylist(i);
            }
        }
    }

    /*
     * Used to get and set objects.
     * DO NOT edit.
     */
     public ArrayList<Playlist> getPlaylists() { return playlistLibrary; }
     public void setPlaylists(ArrayList<Playlist> p) { playlistLibrary = p; }
}
