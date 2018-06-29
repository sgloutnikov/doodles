import sun.misc.Cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheSolution {

    /**
     * Doubly linked list to hold most and least recently used item.
     */
    class CacheNode {
        CacheNode next;
        CacheNode prev;
        int key;
        int data;
    }

    class LRUCache {

        int capacity;
        Map<Integer, CacheNode> cacheNodeMap;
        CacheNode first;
        CacheNode last;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cacheNodeMap = new HashMap<>();
            // Init dummy cache
            CacheNode initNode = new CacheNode();
            this.first = initNode;
            this.last = initNode;
        }

        public int get(int key) {
            CacheNode cachedNode = cacheNodeMap.get(key);
            if (cachedNode != null) {
                // Bring to front of line

                return cachedNode.data;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            // If capacity reached drop last element and add new to front
            if (cacheNodeMap.size() == capacity) {


            } else {

            }

        }

        private void bringNodeToFront(CacheNode node) {
            // Is node already the first node
            if (node.prev == null) {
                return;
            }

            // Is node a last node
            if (node.next != null) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            } else {
                node.prev.next = null;
                last = node.prev;
            }

            first.prev = node;
            node.next = first;
            node.prev = null;
            first = node;
        }

        private void insertNode(CacheNode node) {
            node.next = first;
            first.prev = node;
            node.prev = null;
            cacheNodeMap.put(node.key, node);
        }

        private void dropLastNode() {
            
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    public static void main(String[] args) {
        System.out.println("Hello");
        LRUCache cache = new LRUCacheSolution().new LRUCache(2);
    }
}
