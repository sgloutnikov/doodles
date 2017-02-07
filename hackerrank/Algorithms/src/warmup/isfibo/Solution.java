package warmup.isfibo;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Set<Long> fibNumberSet = new HashSet<Long>();
        Scanner scanner = new Scanner(System.in);

        long prevPrevFib = 0;
        fibNumberSet.add(prevPrevFib);
        long prevFib = 1;
        fibNumberSet.add(prevFib);

        while(prevFib < Math.pow(10, 10)) {
            long curFib = prevPrevFib + prevFib;
            fibNumberSet.add(curFib);
            prevPrevFib =  prevFib;
            prevFib = curFib;
            fibNumberSet.add(curFib);
        }

        int cases = scanner.nextInt();
        for (int i = 0; i < cases; i ++) {
            long number = scanner.nextLong();
            if (fibNumberSet.contains(number)) {
                System.out.println("IsFibo");
            } else {
                System.out.println("IsNotFibo");
            }
        }
    }
}