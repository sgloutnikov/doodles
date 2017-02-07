package boattrips;

import java.util.*;

/**
 * https://www.hackerrank.com/contests/w28/challenges/boat-trip
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int c = in.nextInt();
        int m = in.nextInt();
        int[] p = new int[n];
        int pMax = 0;
        for(int p_i=0; p_i < n; p_i++){
            p[p_i] = in.nextInt();
            if (p[p_i] > pMax) {
                pMax = p[p_i];
            }
        }

        int maxTransport = m*c;
        if (pMax <= maxTransport) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
