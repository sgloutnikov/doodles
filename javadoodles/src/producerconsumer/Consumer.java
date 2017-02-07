package producerconsumer;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Consumer class, using inter thread communication.
 */
public class Consumer implements Runnable {

    private Queue<Integer> queue;


    public Consumer(Queue q) {
        this.queue = q;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                // If queue is empty, wait for work.
                while (queue.size() == 0) {
                    try {
                        System.out.println("Queue is empty. Waiting for work.");
                        queue.wait();
                    } catch (InterruptedException ex) {

                    }
                }
                // Consume work and notify producers if they ae waiting on a full queue
                int work = queue.remove();
                System.out.println(Thread.currentThread().getName()+ " Removed Work from Queue: " + work);
                // Random sleep up to a second to simulate work.
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(2000));
                } catch (InterruptedException ex) {
                }
                queue.notifyAll();
            }

        }
    }
}
