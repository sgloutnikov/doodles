package sparsearrays;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/sparse-arrays
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        String[] strings = new String[n];

        for (int i = 0; i < n; i++) {
            String value = in.next();
            strings[i] = value;
        }

        int q = in.nextInt();

        for (int i = 0; i < q; i++) {
            String query = in.next();
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (strings[j].equals(query)) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
