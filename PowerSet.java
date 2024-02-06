import java.lang.*;

/**
 * PowerSet takes a set and creates every possible subset of set.
 *
 * @author Jonathan Peters
 * @version 1.0, 27/02/23
 */
public class PowerSet<T> {
    private Set<T>[] set;

    /**
     * Creating the power set using binary to determine when to add certain elements.
     * 
     * @param elements  A list of elements to be put into the power set
     */
    public PowerSet(T[] elements) {
        //Creating the power set 
        int loop = (int) Math.pow(2, elements.length);
        String s = "";
        set = (Set<T>[]) new Set[loop]; //Creating the size of the set
        for (int i = 0; i < loop; i++) { //Looping 2^elements times 
            s = Integer.toBinaryString(i); //Setting s to a binary value 
            //Making s have the correct number of digits in it
            while (s.length() < elements.length) { //If too small
                s = "0" + s; //Padding the value with a 0
            }
            //Now with the correct value for the binary number we can add the corresponding values from the array
            Set<T> tempSet = new Set<T>(); //Resetting amd creating a new tempSet
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    tempSet.add(elements[j]); //Putting all of the elements from this set in a temporary set
                }
            }
            //Appending the tempSet to the main set
            set[i] = tempSet;
        }
    }
    
    /**
     * Getting the length of the power set.
     * 
     * @return int  The length of the power set
     */
    public int getLength() {
        return set.length;
    }
    
    /**
     * Finding the set at the specified index of the power set.
     * 
     * @param i  The index of the set
     * @return Set<T>  The set found at that index
     */
    public Set<T> getSet(int i) {
        return set[i];
    }

}
