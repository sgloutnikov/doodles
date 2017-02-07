package dynamicarray;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/dynamic-array
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int q = in.nextInt();
        int lastAns = 0;

        List<List<Integer>> seqList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            seqList.add(new ArrayList<>());
        }

        for (int i = 0; i < q; i++) {
            int t = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();

            if (t == 1) {
                int index = (x ^ lastAns) % n;
                List<Integer> seq = seqList.get(index);
                seq.add(y);
            }
            else {
                int index = (x ^ lastAns) % n;
                List<Integer> seq = seqList.get(index);
                int value = seq.get(y % seq.size());
                lastAns = value;
                System.out.println(lastAns);
            }
        }
    }
}
