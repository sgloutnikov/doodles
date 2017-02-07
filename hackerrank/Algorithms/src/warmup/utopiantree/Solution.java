package warmup.utopiantree;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = scanner.nextInt();

        for(int i = 0; i < numTestCases; i++) {
            int cycles = scanner.nextInt();
            if (cycles == 0) {
                System.out.println(1);
            }
            else {
                int height = 1;
                //Compute Height
                for (int j = 1; j <= cycles; j++) {
                    //Spring are odd cycles, double
                    if(j%2==1) {
                        height = height*2;
                    }
                    else
                        height = height + 1;
                }
                System.out.println(height);
            }
        }
    }
}