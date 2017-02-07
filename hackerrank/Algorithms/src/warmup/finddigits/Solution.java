package warmup.finddigits;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numCases  = scanner.nextInt();
        for (int i = 0; i < numCases; i++) {
            int count = 0;
            String numberString = scanner.next();
            long number = Long.parseLong(numberString);

            for(int j = 0; j < numberString.length(); j++) {
                long divisor = Long.parseLong(String.valueOf(numberString.charAt(j)));
                if (divisor == 0) {
                    continue;
                }
                long remainder = number / divisor;
                if(number % remainder == 0) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}