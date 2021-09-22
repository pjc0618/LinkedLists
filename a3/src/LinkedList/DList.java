package LinkedList;


/* Time spent on a3:  03 hours and 30 minutes.
 *
 * When you change the above, please do it carefully. Change hh to
 * the hours and mm to the minutes and leave everything else as is.
 * If the minutes are 0, change mm to 0. This will help us in
 * extracting times and giving you the average and max.
 * 
 * Name:  Philip Cipollina
 * Netid: pjc272
 * What I thought about this assignment:
 *	Wasn't too bad; I completed it largely in one sitting.  Hoping I didn't miss any test cases,
 *  because I'm never sure how in-depth they need to be.
 */

/** An instance is a doubly linked list. */
public class DList<E>  {
    private Node head;  // first node of linked list (null if size is 0)
    private Node tail;  // last node of linked list (null if size is 0)
    private int size;   // Number of values in the linked list.

    /** Constructor: an empty linked list. */
    public DList() {
    }

    /** Return the number of values in this list.
     *  This function takes constant time. */
    public int size() {
        return size;
    }

    /** Return the first node of the list (null if the list is empty).
     *  This function takes constant time. */
    public Node head() {
        return head;
    }

    /** Return the last node of the list (null if the list is empty).
     *  This function takes constant time. */
    public Node tail() {
        return tail;
    }

    /** Return the value of node n of this list.
     *  Precondition: n is a node of this list; it may not be null.
     *  This function takes constant time. */
    public E value(Node n) {
        assert n != null;
        return n.data;
    }

    /** Return a representation of this list: its values, with adjacent
     * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * Takes time proportional to the length of this list.<br>
     * E.g. for the list containing 4 7 8 in that order, the result it "[4, 7, 8]".
     * E.g. for the list containing two empty strings, the result is "[, ]" */
    public String toString() {
        String res= "[";
        Node n= head;
        // inv: res contains values of nodes before node n (all of them if n = null),
        //      with ", " after each (except for the tail value)
        while (n != null) {
            res= res + n.data;
            n= n.succ;
            if (n != null) {
                res= res + ", ";
            }
        }

        return res + "]";
    }

    /** Return a representation of this list: its values in reverse, with adjacent
     * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * Takes time proportional to the length of this list.
     * E.g. for the list containing 4 7 8 in that order, the result is "[8, 7, 4]".
     * E.g. for the list containing two empty strings, the result is "[, ]". */
    public String toStringRev() { 
        //TODO 1. Look at toString to see how that was written.
        //        Use the same scheme. Extreme case to watch out for:
        //        E is String and values are the empty string.
        //        You can't test this fully until #2, append, is written.
        String res="[";Node n=tail;
        while (n!=null) {
        	res=res+n.data;n=n.pred;
        	if (n!=null) res+=", ";
        }

        return res+"]";
    }
    
    /** add value v to the end of the list.
     *  This operation takes constant time. */
    public void append(E v) {
        //TODO 2. After writing this method, test this method and
        //        method toStringRev thoroughly before starting on the next
        //        method. These two must be correct in order to be
        //        able to write and test all the others.
        size+=1;
        Node n=new Node(tail,v,null);
        if (tail ==null) {
        	head=n;
        }
        else{
        	tail.succ=n;
        }
        tail=n;
    }

    /** Add value v at the front of the list.
     * This operation takes constant time. */
    public void prepend(E v) {
        //TODO 3. 
        size+=1;
        Node n=new Node(null,v,head);
        if (head!=null) head.pred=n;
        else {tail=n;}
        head=n;
    }

    /** Return node number k. 
     *  Precondition: 0 <= k < size of the list.
     *  If k is 0, return head node; if k = 1, return second node, ... */
    public Node getNode(int k) {
        //TODO 4. This method should take time proportional to min(k, size-k).
        // For example, if k <= size/2, search from the beginning of the
        // list, otherwise search from the end of the list.
        assert k>=0 && k<size;
    	Node n= head;
    	if (k<=size/2) {
        	while (k>0) {
        		n=n.succ();k-=1;
        	}
    	}
        if (k>size/2) {
        	n=tail;
        	while (k<size-1) { 
        		n=n.pred; k+=1;
        	}
        }
    	
        return n;
    }
    
    /** Remove node n from this list.
     * This operation must take constant time.
     * Precondition: n must be a node of this list; it may not be null. */
    public void delete(Node n) {
        //TODO 5. Make sure this method takes constant time. 
        assert n!=null;
    	if (n!=tail) n.succ.pred=n.pred;
    	else {tail=n.pred;}
       	if (n!=head) n.pred.succ=n.succ;
       	else {head=n.succ;}
       	size-=1;
       	n.pred=null;n.succ=null;

    }

    /** Insert value v in a new node after node n.
     * This operation takes constant time.
     * Precondition: n must be a node of this list; it may not be null. */
    public void insertAfter(E v, Node n) {
        //TODO 6. Make sure this method takes constant time. 
        assert n!=null;
        Node k=new Node(n,v,n.succ);
        if(n!=tail) {
        	n.succ.pred=k;
        }
        n.succ=k;
        size+=1;
    }

 

    /*********************/

    /** An instance is a node of this list. */
    public class Node {
        private Node pred; // Previous node on list (null if this is head node)
        private E data;     // The value of this element
        private Node succ; // Next node on list. (null if this is tail node)

        /** Constructor: an instance with predecessor node p (can be null),
         * value v, and successor node n (can be null). */
        Node(Node p, E v, Node n) {
            pred= p;
            data= v;
            succ= n;
        }

        /** Return the predecessor of this node (null if this is the
         * head node of the list). */
        public Node pred() {
            return pred;
        }

        /** Return the value of this node. */
        public E value() {
            return data;
        }

        /** Return the successor of this node in this list (null if this is the
         * tail node of this list). */
        public Node succ() {
            return succ;
        }
    }

}
