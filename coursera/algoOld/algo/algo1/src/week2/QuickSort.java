package week2;


import java.io.*;
import java.net.URL;

public class QuickSort {

    public long compCount = 0;

    public void quickSort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        compCount += r - l;
        //uncomment for last element as pivot
        //swap(array, l, r);
        // ChoosePivot, General Case
        int p = partition(array, l, r);
        // Recursive calls to sort 1st and 2nd part
        quickSort(array, l, p - 1);
        quickSort(array, p + 1, r);
    }

    // Partitions and returns location of pivot. Implemented as discussed in lecture.
    private int partition(int[] array, int l, int r) {
        int p = array[l];
        int i = l + 1;
        for (int j = l + 1; j <= r; j++) {
            if(array[j] < p) {
                swap(array, j, i);
                i++;
            }
        }
        swap(array, l, i - 1);
        return i - 1;
    }


    private void swap(int[] array, int a, int b) {
        if (a == b) {
            return;
        }
        int tmp = array[b];
        array[b] = array[a];
        array[a] = tmp;
    }



    public static void main(String[] args) throws IOException {
        QuickSort qs = new QuickSort();

        int[] array = new int[10000];

        URL url = QuickSort.class.getResource("QuickSort.txt");
        File file = new File(url.getPath());
        BufferedReader br = new BufferedReader(new FileReader(file));

        for (int i = 0; i < 10000; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        qs.quickSort(array, 0, array.length - 1);
        System.out.println(qs.compCount);
    }
}
