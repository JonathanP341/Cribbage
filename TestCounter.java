
public class TestCounter {

    private static String[][] tests = new String [][] {
        new String[] {"C","6","D","4","S","5","C","J","C","3"},//1
        new String[] {"D","2","S","4","C","6","H","8","D","Q"},//2
        new String[] {"H","Q","D","K","D","J","S","Q","C","9"},//3
        new String[] {"C","K","H","A","H","8","H","10","H","3"},//4
        new String[] {"H","K","H","A","H","8","H","10","H","3"},//5
        new String[] {"D","2","S","2","C","8","H","9","D","Q"},//6
        new String[] {"D","2","S","2","C","2","H","9","D","Q"},//7
        new String[] {"D","8","S","Q","C","8","H","2","D","2"},//8
        new String[] {"D","2","S","2","C","2","H","8","D","8"},//9
        new String[] {"D","1","S","2","C","2","H","8","D","8"},//10
        new String[] {"H","4","H","2","C","K","C","3","D","9"},//11
        new String[] {"S","5","S","J","C","Q","D","Q","H","10"},//12
        new String[] {"C","4","C","J","C","K","H","4","D","A"},//13
        new String[] {"S","10","H","7","S","4","S","2","S","Q"},//14
        new String[] {"C","5","C","4","S","A","S","6","S","J"},//15
        new String[] {"C","Q","H","4","H","9","S","3","H","2"},//16
        new String[] {"H","5","S","6","D","6","H","3","C","4"},//17
        new String[] {"D","5","C","5","S","5","H","J","H","5"},//18
        new String[] {"D","4","C","4","D","J","S","7","D","7"},//19
        
        new String[] {"D","J","C","4","D","4","S","7","D","7"},//20
        new String[] {"D","4","C","4","D","J","S","J","D","7"},
        new String[] {"C","5","D","8","D","3","D","J","D","5"},
        new String[] {"H","2","H","6","H","8","H","5","H","7"},
        new String[] {"S","7","H","6","H","8","H","5","H","2"},
        new String[] {"H","2","S","7","H","6","H","8","H","5"},
    };
    private static int[] results = new int[] {
        9, 0, 8, 4, 5,2,8,4,8,4,7,17,11,0,9,7,16,28,9,8,7,10,15,14,10
    };


    public static void main(String[] args) {

        for (int i = 0; i < tests.length; i++) {
            runTest(i);
        }

    }

    private static void runTest(int i) {
        String[] strArray = tests[i];
        Card starter = new Card(strArray[0], strArray[1]);
        Card[] cardArray = new Card[strArray.length / 2];
        int c = 0;
        for (int j = 2; j < strArray.length; j += 2) {
            cardArray[c++] = new Card(strArray[j], strArray[j + 1]);
        }
        cardArray[c] = starter;

        Counter counter = new Counter(cardArray, starter);
        int act = counter.countPoints();
        int exp = results[i];

        if (act == exp) {
            System.out.println("Test " + (i + 1) + " Passed");
        } else {
            System.out.println("Test " + (i + 1) + " Failed\tYour score is: " + act + " but it should be: " + exp);
        }
    }

}
