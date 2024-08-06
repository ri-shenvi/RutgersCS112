/**
 * Problem Set 2
 * Writing methods for an IntNode class
 * 
 * @author Riya Shenvi
 * @date 12.13.23
 * @period 3
 */

public class IntNode {
	  public int data;
      public IntNode next;
      
      public IntNode() {
    	  this.data = 0; 
    	  this.next = null;
      }
      public IntNode(int data, IntNode next) {
          this.data = data; 
          this.next = next;
      }
      public String toString() 
      {
    	  if(next != null)
    		  return data + " " + next.toString();
          return data + "";
      }

      /** #1
       * Implement the addBefore method such that newItem will be added before 
       * target in the list starting with front.  If the target does not exist,
       * addBefore should return the original list, unchanged.
       * 
       * @param front, the first node in the linked list
       * @param target 
       * @param newItem item to be inserted
       * @return the front node of the updated linked list
       */
      public static IntNode addBefore(IntNode front, int target, int newItem) {
    	    IntNode newNode = new IntNode(newItem, null);

    	    if (front.data == target) {
    	        newNode.next = front;
    	        return newNode;
    	    }

    	    IntNode current = front;

    	    while (current.next != null) {
    	        if (current.next.data == target) {
    	            newNode.next = current.next;
    	            current.next = newNode;
    	            return front;
    	        }
    	        current = current.next;
    	    }

    	    return front;
    	}

      /** #2
       * Implement the addBeforeLast method such that newItem will be added 
       * just before the last item in the linked list.  If the initial list is 
       * empty addBeforeLast should return null, returning the original list, unchanged.
       * 
       * @param front, the first node in the linked list
       * @param newItem
       * @return the front node of the updated linked list
       */
      public static IntNode addBeforeLast(IntNode front, int newItem) {
    	  if (front == null) {
              return new IntNode(newItem, null);
          }

          IntNode current = front;
          while (current.next != null && current.next.next != null) {
              current = current.next;
          }

          IntNode newNode = new IntNode(newItem, current.next);
          current.next = newNode;
          return front;
      }
      
      /** #3
       * Implement the method numberOfOccurrances that will search 
       * a given linked list for a target int, and return the 
       * number of occurrences of the target
       * 
       * @param front, the first node in the linked list
       * @param target
       * @return the number of occurrences of the target
       */
      public static int numberOfOccurrences(IntNode front, int target) {
    	  int count = 0;
          while (front != null) {
              if (front.data == target) {
                  count++;
              }
              front = front.next;
          }
          return count;
      }
      
      /** #4
       * Implement the method deleteEveryOther to delete EVERY OTHER 
       * item from an integer linked list. 
       * For example:
       * 	before: 3->9->12->15->21
       * 	after: 3->12->21
       * 
       * 	before: 3->9->12->15
       * 	after: 3->12
       *
       * 	before: 3->9
       * 	after: 3
       * 	
       * 	before: 3
       * 	after: 3
       * 
       * If the list is empty, the method should do nothing.
       * @param front, the first node in the linked list
       */
      public static void deleteEveryOther(IntNode front) {
    	  if (front == null || front.next == null)
              return;

          int count = 0;
          IntNode ptr = front;
          IntNode prev = null;

          while (ptr != null) {
              if (count % 2 == 0) {
                  prev = ptr;
                  ptr = ptr.next;
              } else {
                  prev.next = ptr.next;
                  ptr = prev.next;
              }
              ++count;
          }
    	  
//    	  if (front == null) {
//              return;
//          }
//
//          IntNode current = front;
//          while (current.next != null && current.next.next != null) {
//              current.next = current.next.next;
//              current = current.next;
//          }
      }
      
      /** #5
       * Implement the method deleteAllOccurrences that will 
       * delete all occurrences of a given target int from a 
       * linked list, and return a pointer to the first node 
       * of the resulting linked list.
       * 
       * @param front, the first node in the linked list
       * @param target
       * @return the front node of the updated linked list
       */
      public static IntNode deleteAllOccurrences(IntNode front, int target) {
    	  if (front == null) {
              return null;
          }

          IntNode newList = new IntNode();
          IntNode current = front;
          IntNode previous = null;

          while (current != null) {
              if (current.data != target) {
                  newList = copyToNewList(current, newList);
                  previous = current;
              } else if (previous != null) {
            	  previous.next = current.next;
              }
              current = current.next;
          }

          return newList.next;
      }
      
      private static IntNode copyToNewList(IntNode current, IntNode newList) {
          if (newList.next == null) {
              newList.next = new IntNode(current.data, null);
          } else {
              IntNode temp = newList;
              while (temp.next != null) {
                  temp = temp.next;
              }
              temp.next = new IntNode(current.data, null);
          }
          return newList;
      }
      
      /** #6
       * Implement the method commonElements to find the common elements 
       * in two SORTED linked lists, and return the common elements in 
       * sorted order in a NEW linked list. The original linked lists 
       * should not be modified. 
       * For instance:
       *  	l1 = 3->9->12->15->21
       *  	l2 = 2->3->6->12->19
       *  should produce a new linked list:
       *  	3->12
       *  
       * You may assume that the original lists do not have any duplicate items.
       * Return null if no common elements exist.
       * 
       * @param frontL1, the first node in the linked list 1
       * @param frontL2, the first node in the linked list 2
       * @return A reference to the front node of a new linked list
       * 	which holds the common elements of L1 and L2 in sorted order.
       * 	Or null if no common elements exist.
       */
      public static IntNode commonElements(IntNode frontL1, IntNode frontL2) {
    	  IntNode ptr1 = frontL1;
          IntNode ptr2 = frontL2;
          IntNode newList = null;
          
          if (frontL1 == null || frontL2 == null) 
        	  return null;

          while (ptr1 != null && ptr2 != null) {

              if (ptr1.data == ptr2.data) {
                  newList = add(newList, ptr1.data);
                  ptr1 = ptr1.next;
                  ptr2 = ptr2.next;
              } else if (ptr1.data < ptr2.data) {
                  ptr1 = ptr1.next;
              } else {
                  ptr2 = ptr2.next;
              }
          }

          return newList;
      }
      
      public static IntNode add(IntNode front, int data) {

          if (front == null) {
              return new IntNode(data, null);
          } else {
              IntNode ptr;
              for (ptr = front; ptr.next != null; ptr = ptr.next) {
                  ;
              }
              ptr.next = new IntNode(data, null);
          }
          return front;
      }
}
