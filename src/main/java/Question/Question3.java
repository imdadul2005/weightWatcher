package Question;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Imdadul Hoq
 */

public class Question3
{
    public static void main( String[] args ) {

        int totalNumber = 500;
        int limit = 10000;
        int nTHNumber = 1;

        // Create an array of 500 random number between (0 to 10000)
        int[] randomList = randomNumberGenerator(totalNumber,limit);

        //Find the n-th number of smallest number
        int nthSmallNumber = nthLowestInt(nTHNumber,randomList);

        System.out.println(nTHNumber + " smallest number is "+nthSmallNumber);
    }

    /**
     *
     * @param n is the number of the smallest number for the list.
     * @param array unsorted random number
     * @return returns the value of the nth number.
     */

    public static int nthLowestInt(int n,int[] array){

        Arrays.sort(array);
        ArrayList<Integer> numList = new ArrayList<>();

        for (int x : array) {
            if(!numList.contains(x))
                numList.add(x);
        }

        //Adjust the index number
        return numList.get(n-1);
    }

    /**
     *
     * @param totalNumber This is the number of generated random number.
     * @param limit This is sets the range
     * @return return an randomly generated integer array.
     */

    public static int[] randomNumberGenerator(int totalNumber, int limit){

        int[] randomList = new int[totalNumber];
        Random t = new Random();
        for (int c = 0; c < randomList.length; c++) {
            randomList[c] = t.nextInt(limit);
        }
        return randomList;
    }
}