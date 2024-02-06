
/**
 * Counter represents a hand and counts the points that hand would yield in a game of cribbage.
 *
 * @author Jonathan Peters
 * @version 1.0, 27/02/23
 */
public class Counter {

    private PowerSet<Card> cardps;
    private Card starter;

    /**
     * Creating the counter class representing a cribbage hand.
     *
     * @param hand The four cards to play with, not including the
     * @param starter The starter of the cribbage hand
     */
    public Counter(Card[] hand, Card starter) {
        this.starter = starter;
        cardps = new PowerSet<Card>(hand); //Hand already includes the starter
    }

    /**
     * Counting the points for the hand from several different methods.
     *
     * @return int The points the hand scored
     */
    public int countPoints() {
        //Need to count the points in the hand based of cards given
        //WIll be using helper methods to keep the method clean
        int total = 0; //Counting the total points
        total += hisKnobs();
        total += pairs();
        total += runs();
        total += fifteen();
        total += flush();

        return total;
    }

    /**
     * Checking whether the hand gets points for his Knobs.
     *
     * @return int The points from the hand
     */
    private int hisKnobs() {
        //First checking if a jack is present in the hand
        Set<Card> tempSet = new Set<Card>(); //Creating a dummy set object called tempSet
        for (int i = 0; i < cardps.getLength(); i++) {
            //Checking every card in the power set, if its a jack find suit
            tempSet = cardps.getSet(i); //Checking every set

            //Only looking at sets with size of 1 as I just want to look at each card individually, once
            if (tempSet.getLength() == 1) {
                if (hasStarter(tempSet, tempSet.getLength()) == false) { //If it has the starter then 
                    if (tempSet.getElement(0).getLabel().equals("J")) { //If there is a jack
                        if (tempSet.getElement(0).getSuit().equals(starter.getSuit())) { //If it has the same suit as the starter
                            return 1; //If a J is found with the same suit return 1 for 1 point
                        }
                    }
                }

            }
        }
        return 0; //No points for his knobs
    }

    /**
     * Checking how many points the hand gets from pairs.
     *
     * @return int The points from the hand
     */
    private int pairs() {
        //Looping through every element with 2 
        int pairs = 0;

        Set<Card> tempSet = new Set<Card>(); //Creating a dummy set
        for (int i = 0; i < cardps.getLength(); i++) {
            tempSet = cardps.getSet(i); //Checking every set in the powerset
            if (tempSet.getLength() == 2) { //Checking only sets with length 2 for pairs
                if (tempSet.getElement(0).getLabel().equals(tempSet.getElement(1).getLabel())) { //Comparing the label of each card
                    pairs += 2; //Adding 2 for each pair
                }
            }
        }
        return pairs;
    }

    /**
     * Checking if the set is a run.
     *
     * @return set The set to determine whether its a run or not
     */
    private boolean isRun(Set<Card> set) {
        // In this method, we are going through the given set to check if it constitutes a run of 3 or more
        // consecutive cards. To do this, we are going to create an array of 13 cells to represent the
        // range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
        // each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
        // An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
        // contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
        // Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.

        int n = set.getLength();

        if (n <= 2) {
            return false; // Run must be at least 3 in length.
        }
        int[] rankArr = new int[13];
        for (int i = 0; i < 13; i++) {
            rankArr[i] = 0; // Ensure the default values are all 0.
        }
        for (int i = 0; i < n; i++) {
            rankArr[set.getElement(i).getRunRank() - 1] += 1;
        }

        // Now search in the array for a sequence of n consecutive 1's.
        int streak = 0;
        int maxStreak = 0;
        for (int i = 0; i < 13; i++) {
            if (rankArr[i] == 1) {
                streak++;
                if (streak > maxStreak) {
                    maxStreak = streak;
                }
            } else {
                streak = 0;
            }
        }
        if (maxStreak == n) { // Check if this is the maximum streak.
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checking how many points the hand gets from runs.
     *
     * @return int The points from the hand
     */
    private int runs() {
        int longest = 0;
        int timesSeen = 0;

        Set<Card> tempSet = new Set<Card>(); //Creating a dummy set
        for (int i = 0; i < cardps.getLength(); i++) {
            tempSet = cardps.getSet(i);

            if (isRun(tempSet)) { //If the run is a set
                if (tempSet.getLength() > longest) { //Checking if the run is the longest
                    longest = tempSet.getLength(); //If it is set it to longest
                    timesSeen = 1; //And set the times seen to 1
                } else if (tempSet.getLength() == longest) { //If same size
                    timesSeen++;//Increase timesSeen by one
                }
            }

        }
        return (longest * timesSeen); //The points from runs is the longest * timeseen   
    }

    /**
     * Checking how many points the hand gets from cards adding up to 15.
     *
     * @return int The points from the hand
     */
    private int fifteen() {
        int points = 0;
        int val = 0;
        Set<Card> tempSet = new Set<Card>();

        for (int i = 0; i < cardps.getLength(); i++) { //Loooing through every set
            tempSet = cardps.getSet(i); //Setting those sets to temp set
            val = 0; //Resetting val
            for (int j = 0; j < tempSet.getLength(); j++) { //Looping through the values of the set
                val += tempSet.getElement(j).getFifteenRank();//Getting the value of each card
            }
            if (val == 15) { //If the values add up to 15 points add 2 points
                points += 2;
            }
        }
        return points;
    }

    /**
     * Checking if the set of 4 cards contains the starter.
     *
     * @param hand The hand of 4 cards
     * @return boolean Whether it contains the starter
     */
    private boolean hasStarter(Set<Card> hand, int cardsToCheck) { //Helper method for flush
        if (hand.getLength() == cardsToCheck) {
            for (int i = 0; i < hand.getLength(); i++) {
                if (hand.getElement(i).getLabel().equals(starter.getLabel()) && hand.getElement(i).getSuit().equals(starter.getSuit())) { //Checking if card is starter
                    return true; //Return false if any of the cards are the starter
                }
            }
        } else { //If its the wrong size return false
            return true;
        }
        return false; //If none of the cards are the starter, return true
    }

    /**
     * Checking if the 4 cards are a flush.
     *
     * @param hand The hand of cards to check
     * @param cardsToCheck The number of cards the hand must equal
     * @return boolean Whether the hand is a flush
     */
    private boolean isFlush(Set<Card> hand, int cardsToCheck) {
        if (hand.getLength() == cardsToCheck) { //Only checking hands of 4 and assuming the starter has been removed
            String suit = hand.getElement(cardsToCheck - 1).getSuit(); //Setting hand to the label of the last element in the set
            for (int i = 0; i < cardsToCheck - 1; i++) { //Looping through all except the last card
                if (hand.getElement(i).getSuit().equals(suit) == false) { //If the suits dont match
                    return false; //Return false
                }
            }
        } else {
            return false; //If the hand is the wrong length return false
        }
        return true; //If it passes everything returning true
    }

    /**
     * Checking how many points the hand gets from a flush.
     *
     * @return int The points from the hand
     */
    private int flush() {
        int points = 0;
        Set<Card> tempSet = new Set<Card>(); //Creating a dummy set

        for (int i = 0; i < cardps.getLength(); i++) {
            tempSet = cardps.getSet(i); //Looping through the temp set

            if (tempSet.getLength() == 4) { //If the tempSet has a length of 4               
                if (hasStarter(tempSet, 4) == false) { //If it doesnt have a starter
                    if (isFlush(tempSet, 4)) { //If the 4 cards are a flush
                        points = 4; //Set 4 to points
                        if (starter.getSuit().equals(tempSet.getElement(0).getSuit())) { //Checking if the starter matches the suit of any card in the flush
                            points += 1;
                            return points; //You can only have one flush so returning after determining if there is one
                        }
                        return points; //Can only have one flush to returning after
                    }
                }
            }
        }
        return points; //There was no flush so 0 points
    }

    //Testing
    public static void main(String[] args) {
        Card starter = new Card("D", "J");
        Card[] cardHand = new Card[5];
        Card card1 = new Card("C", "4");
        Card card2 = new Card("D", "4");
        Card card3 = new Card("S", "7");
        Card card4 = new Card("D", "7");
        cardHand[0] = card1;
        cardHand[1] = card2;
        cardHand[2] = card3;
        cardHand[3] = card4;
        cardHand[4] = starter;
        PowerSet<Card> handps = new PowerSet<Card>(cardHand);

        Counter counter = new Counter(cardHand, starter);
        System.out.println(counter.countPoints());
    }

}
