import java.util.HashMap;
import java.util.Map;

public class LRUCacheSolution {

    public class LRUCache {

        /**
         * Doubly linked list to hold most and least recently used item.
         */
        class CacheNode {
            CacheNode prev;
            CacheNode next;
            int key;
            int data;

            CacheNode(int key, int data) {
                this.key = key;
                this.data = data;
            }
        }


        int capacity;
        Map<Integer, CacheNode> cacheNodeMap;
        CacheNode first;
        CacheNode last;


        public LRUCache(int capacity) {
            this.capacity = capacity;
            cacheNodeMap = new HashMap<>();
            // Init dummy cache boundaries to eliminate edge node cases
            first = new CacheNode(-1, -1);
            last = new CacheNode(-1, -1);
            first.next = last;
            last.prev = first;
        }

        public int get(int key) {
            if (cacheNodeMap.containsKey(key)) {
                CacheNode node = cacheNodeMap.get(key);
                bringNodeToFront(node);
                return node.data;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            CacheNode cachedNode = cacheNodeMap.get(key);
            if (cacheNodeMap.containsKey(key)) {
                cachedNode.data = value;
                bringNodeToFront(cachedNode);
            } else {
                // If at capacity drop last node and later add new at the front
                if (cacheNodeMap.size() == capacity) {
                    CacheNode droppedNode = dropLastNode();
                    cacheNodeMap.remove(droppedNode.key);
                }
                CacheNode newNode = new CacheNode(key, value);
                insertNode(newNode);
                cacheNodeMap.put(key, newNode);
            }
        }

        private void bringNodeToFront(CacheNode node) {
            removeNode(node);
            insertNode(node);
        }

        private void insertNode(CacheNode node) {
            node.prev = first;
            node.next = first.next;
            first.next.prev = node;
            first.next = node;
        }

        private CacheNode dropLastNode() {
            CacheNode droppedNode = last.prev;
            removeNode(droppedNode);
            return droppedNode;
        }

        private void removeNode(CacheNode node) {
            CacheNode prev = node.prev;
            CacheNode next = node.next;
            prev.next = next;
            next.prev = prev;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCacheSolution().new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));        // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));        // returns -1 (not found)
        System.out.println(cache.get(3));        // returns 3
        System.out.println(cache.get(4));        // returns 4
    }
}
