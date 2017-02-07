package crush;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/crush
 */
public class Solution {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] startRange = new long[n];
        long[] endRange = new long[n];

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            long k = in.nextLong();

            startRange[a-1] += k;
            endRange[b-1] +=k;
        }

        long sum = 0;
        long max = 0;

        /*
         We have previously noted where we started to add to our range and where we have ended.
         Keeping track of an overall sum across the entire array will result in finding the peak, ie max element.
         */
        for (int i = 0; i < n; i++) {
            sum += startRange[i];
            if (sum > max) {
                max = sum;
            }
            sum -= endRange[i];
        }

        System.out.println(max);
    }
}
