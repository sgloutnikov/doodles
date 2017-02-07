package arrays2dds;

import java.util.*;

/*
https://www.hackerrank.com/challenges/2d-array
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arr[][] = new int[6][6];
        for(int arr_i=0; arr_i < 6; arr_i++){
            for(int arr_j=0; arr_j < 6; arr_j++){
                arr[arr_i][arr_j] = in.nextInt();
            }
        }

        int maxTotal = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int a = arr[i][j];
                int b = arr[i][j+1];
                int c = arr[i][j+2];
                int d = arr[i+1][j+1];
                int e = arr[i+2][j];
                int f = arr[i+2][j+1];
                int g = arr[i+2][j+2];

                int sum = a+b+c+d+e+f+g;
                if (sum > maxTotal) {
                    maxTotal = sum;
                }
            }
        }

        System.out.println(maxTotal);
    }
}
