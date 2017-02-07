package producerconsumer;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Producer class, demonstrating inter thread communication.
 */
public class Producer implements Runnable {

    private Queue<Integer> queue;
    private int maxSize;

    public Producer(Queue q, int maxSize) {
        this.queue = q;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        Random random = new Random();
        while(true) {
            synchronized (queue) {
                // Wait if queue is full.
                while (queue.size() == maxSize) {
                    try {
                        System.out.println("Queue is full. Waiting.");
                        queue.wait();
                    } catch (InterruptedException ex) {
                    }
                }
                // Add work to queue and notify consumers that work is ready.
                int work = random.nextInt(1000);
                queue.add(work);
                System.out.println("Added Work to Queue: " + work);
                // Random sleep up to a second to simulate work.
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(1000));
                } catch (InterruptedException ex) {
                }
                queue.notifyAll();
            }
        }
    }

}
