/**
 * Set represents a set of generics in the form of a linked list.
 *
 * @author Jonathan Peters
 * @version 1.0, 27/02/23
 */
public class Set<T> {
    private LinearNode<T> setStart; //The front of the linked list

    
    /**
     * Creates an set with the starting node pointing to null.
     */
    public Set() {
        setStart = new LinearNode<T>(); //Setstart is a node that only points, no value
    }
    
    
    /**
     * Adding an element to the front of the set.
     * 
     * @param element  the element that will be stored in the node
     * @return void
     */
    public void add(T element) {
        //Creating a new node called newElem that must be added to the linked list
        LinearNode<T> newElem = new LinearNode<T>(element);

        newElem.setNext(setStart.getNext()); //Setting the next value of the new node to the element after setStart
        setStart.setNext(newElem); //Setting setStart's next node to the new node
    }
    
    
    /**
     * Gets the length of the set.
     * 
     * @return int  The size of the set
     */
    public int getLength() {
        LinearNode<T> curr = setStart; //Setting curr to setStart
        int ct = 0; //Making a counter

        while (curr.getNext() != null) {//Looping through the linked list
            ct += 1; //Inceasing the counter
            curr = curr.getNext(); //Going to the next node
        }
        return ct;
    }
    
    
    /**
     * Get the element at a specified index of the set.
     * 
     * @param i  The index of the set from which to get the element
     * @return T  The value found at that index
     */
    public T getElement(int i) {
        LinearNode<T> curr = setStart;

        //Changing i to properly represent the index
        i = getLength() - i;

        for (int j = 0; j < i; j++) { //Looping through the linked list i times
            curr = curr.getNext();
        }

        return curr.getElement(); //Returning the element at the specified index
    }
    
    
    /**
     * Checking if the set contains a a specific value.
     * 
     * @param element  Checking if the entire set contains the element
     * @return boolean  Return whether it has it or not
     */
    public boolean contains(T element) {
        LinearNode<T> curr = setStart;
        while (curr.getNext() != null) { //Looping through the linked list
            curr = curr.getNext(); //Putting it the beginning because setStart has no element
            //If the value at curr == element or .equals element then return true
            if (curr.getElement().equals(element)) { //If the value of the node is equal to the element we want
                return true;
            }
        }
        //If not there, return false
        return false;
    }
    
    /**
     * Allows the user to view the elements of the set.
     * 
     * @return String  The set in a way a human can understand
     */
    public String toString() {
        LinearNode<T> curr = setStart;
        String s = ""; //Building on the string
        while (curr.getNext() != null) { //Looping through the list
            curr = curr.getNext(); //Going to the next element, put at the beginning since the set starts are setStart
            s += curr.getElement(); //Adding the element to the string
            s += " "; //Adding a space afterwards
        }
        return s;
    }
    
    
    //Testing Set.java
    public static void main(String[] args) {
        //Creating the set
        Set<Integer> set = new Set<Integer>();

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        System.out.println(set.getLength());
        System.out.println(set.toString());
        System.out.println(set.contains(6));
        System.out.println(set.getElement(4)); //doesnt run
    }

}
