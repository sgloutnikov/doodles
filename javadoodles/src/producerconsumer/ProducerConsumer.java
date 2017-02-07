package producerconsumer;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Main class to Run the Producer/Consumer example.
 */
public class ProducerConsumer {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();

        Producer producer = new Producer(queue, 10);
        Consumer consumer = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);

        Thread p1 = new Thread(producer, "Producer 1");
        Thread c1 = new Thread(consumer, "Consumer 1");
        Thread c2 = new Thread(consumer2, "Consumer 2");
        p1.start();
        c1.start();
        c2.start();

        System.out.println("Running");

    }
}
