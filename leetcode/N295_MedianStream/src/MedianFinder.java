import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

    private PriorityQueue<Integer> lowerMaxHeap;
    private PriorityQueue<Integer> higherMinHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        lowerMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        higherMinHeap  = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // Add num to appropriate lower or higher heap
        if (lowerMaxHeap.isEmpty() || num <= lowerMaxHeap.peek()) {
            lowerMaxHeap.add(num);
        } else {
            higherMinHeap.add(num);
        }
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

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());

    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */