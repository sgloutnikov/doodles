package week1;

import java.io.*;

/**
 * @author Stefan Gloutnikov
 */
public class CountInversions {

    public long sortAndCount(int[] array, int[] aux, int left, int right) {
        long invCount = 0;
        if (left < right) {
            int mid = (left + right) / 2;

            invCount = invCount + sortAndCount(array, aux, left, mid);
            invCount = invCount + sortAndCount(array, aux, mid + 1, right);
            invCount = invCount + mergeAndCountSplitInv(array, aux, left, right, mid);
        }
        return invCount;
    }

    public long mergeAndCountSplitInv(int[] array, int[] aux, int left, int right, int mid) {
        long invCount = 0;

        for (int i = left; i <= right; i++)
            aux[i] = array[i];

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (aux[i] <= aux[j]) {
                array[k] = aux[i];
                i++;
                k++;
            } else {
                array[k] = aux[j];
                // Remaining elements in left sorted array are inversions since remaining in
                // left is now bigger than sorted right.
                invCount = invCount + (mid + 1 - i);
                j++;
                k++;
            }
        }
        while (i <= mid) {
            array[k] = aux[i];
            i++;
            k++;
        }

        return invCount;
    }

    public static void main(String[] args) throws IOException {
        CountInversions ci = new CountInversions();
        int[] input = new int[100000];

        BufferedReader br = new BufferedReader(new FileReader("C:/IntegerArray.txt"));
        for (int i = 0; i < input.length; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        //int[] input = {1, 3, 5, 2, 4, 6};
        int[] aux = new int[input.length];

        System.out.println(ci.sortAndCount(input, aux, 0, input.length - 1));

    }

}
