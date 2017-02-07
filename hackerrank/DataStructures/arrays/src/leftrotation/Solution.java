package leftrotation;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/array-left-rotation
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int d = in.nextInt();

        int[] arr = new int[n];
        int[] rotatedArr = new int[n];

        for (int i = 0; i < n; i++) {
            int value = in.nextInt();
            arr[i] = value;
        }

        for (int i = 0; i < n; i++) {
            rotatedArr[i] = arr[(i+d)%n];
            System.out.print(rotatedArr[i] + " ");
        }
    }
}
