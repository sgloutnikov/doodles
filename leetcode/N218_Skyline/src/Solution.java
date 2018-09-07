import java.util.*;

public class Solution {

    static class BuildingWall implements Comparable<BuildingWall> {
        int x;
        int height;
        boolean isStart;

        public BuildingWall(int x, int height, boolean isStart) {
            this.x = x;
            this.height = height;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(BuildingWall other) {
            if (this.x != other.x) {
                return this.x - other.x;
            } else {
                // If Both are start, higher is first
                // If Both are end, lower is first
                // If one start one end, start is first
                if (this.isStart && other.isStart) {
                    return (-this.height - (-other.height));
                } else if (!this.isStart && !other.isStart) {
                    return this.height - other.height;
                } else {
                    if (this.isStart) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        }

        @Override
        public String toString() {
            return "BuildingWall{" +
                    "x=" + x +
                    ", height=" + height +
                    ", isStart=" + isStart +
                    '}';
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        // From input create and store start and end BuildingWall points.
        // Sort BuildingWall points per defined rules
        // Add max heap to keep track of current max height as traversing x. Add 0 to make life easier at the end.
        // Keep track of previous max height before moving to new point
        // If point is starting point, add to max heap
        // if end point
        List<BuildingWall> buildingWalls = new ArrayList<>();
        List<int[]> result = new ArrayList<>();

        for (int[] building : buildings) {
            BuildingWall startWall = new BuildingWall(building[0], building[2], true);
            BuildingWall endWall = new BuildingWall(building[1], building[2], false);
            buildingWalls.add(startWall);
            buildingWalls.add(endWall);
        }

        Collections.sort(buildingWalls);
        // Max heap to keep track of currently considering heights and what is max
        // TODO: Can improve runtime with TreeMap, removal O(logn) instead of O(n).
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // Add 0 for always existing 0 height
        maxHeap.add(0);
        int prevMaxHeight = 0;

        for (BuildingWall wall : buildingWalls) {
            if (wall.isStart) {
                maxHeap.add(wall.height);
            } else {
                maxHeap.remove(wall.height);
            }

            int currMaxHeight = maxHeap.peek();
            // New critical point here if new max height in skyline
            if (currMaxHeight != prevMaxHeight) {
                int[] skylinePoint = {wall.x, currMaxHeight};
                result.add(skylinePoint);
                prevMaxHeight = currMaxHeight;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] testBuildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8} };

        for (int[] res : solution.getSkyline(testBuildings)) {
            System.out.print(res[0]);
            System.out.print(" , ");
            System.out.println(res[1]);
        }

    }

}