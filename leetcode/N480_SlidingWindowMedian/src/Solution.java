import java.util.Collections;
import java.util.PriorityQueue;

class Solution {

    // From Median stream problem. Can optimize on swapping heap for BST where remove is O(logn) instead of O(n)
    private PriorityQueue<Integer> lowerMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> higherMinHeap = new PriorityQueue<>();

    public void addNum(int num) {
        // Add num to appropriate lower or higher heap
        // Both are empty
        if (lowerMaxHeap.isEmpty() && higherMinHeap.isEmpty()) {
            lowerMaxHeap.add(num);
        }
        // One is empty
        else if (lowerMaxHeap.isEmpty()) {
            if (num < higherMinHeap.peek()) {
                lowerMaxHeap.add(num);
            } else {
                higherMinHeap.add(num);
            }
        } else if (higherMinHeap.isEmpty()) {
            if (num <= lowerMaxHeap.peek()) {
                lowerMaxHeap.add(num);
            } else {
                higherMinHeap.add(num);
            }
        }
        // None empty
        else {
            if (num <= higherMinHeap.peek()) {
                lowerMaxHeap.add(num);
            } else {
                higherMinHeap.add(num);
            }
        }

        rebalance();
    }

    public void removeNum(int num) {
        if (num <= lowerMaxHeap.peek()) {
            lowerMaxHeap.remove(num);
        } else {
            higherMinHeap.remove(num);
        }
        rebalance();
    }

    public void rebalance() {
        // Re-balance the heap so none is more than 1 bigger than the other
        PriorityQueue<Integer> biggerHeap = lowerMaxHeap.size() > higherMinHeap.size() ? lowerMaxHeap  : higherMinHeap;
        PriorityQueue<Integer> smallerHeap = lowerMaxHeap.size() > higherMinHeap.size() ? higherMinHeap : lowerMaxHeap;

        if (biggerHeap.size() - smallerHeap.size() > 1) {
            smallerHeap.add(biggerHeap.poll());
        }
    }

    public double findMedian() {
        // get larger quantity heap, if total elements odd return top of larger else average of top 2
        PriorityQueue<Integer> biggerHeap = lowerMaxHeap.size() > higherMinHeap.size() ? lowerMaxHeap  : higherMinHeap;
        PriorityQueue<Integer> smallerHeap = lowerMaxHeap.size() > higherMinHeap.size() ? higherMinHeap : lowerMaxHeap;

        if (biggerHeap.size() == smallerHeap.size()) {
            return ((double)smallerHeap.peek() + biggerHeap.peek()) / 2;
        } else {
            return biggerHeap.peek();
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            if (i >= k) {
                result[i-k] = findMedian();
                removeNum(nums[i-k]);
                addNum(nums[i]);
            } else {
                addNum(nums[i]);
            }
        }

        result[result.length-1] = findMedian();

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] test = {1,3,-1,-3,5,3,6,7};
        //int[] test = {1,2,3,4,5};

        for (double median : solution.medianSlidingWindow(test, 3)) {
            System.out.println(median);
        }
    }
}