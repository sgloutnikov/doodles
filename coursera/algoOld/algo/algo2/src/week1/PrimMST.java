package week1;

import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Prim MST Naive Implementation
 */
public class PrimMST {

    int[][] graph;
    int numVertices = -1;
    Set<Integer> reachableSet = new HashSet<Integer>();
    Set<Integer> reachedSet = new HashSet<Integer>();
    Set<Edge> treeSet = new HashSet<Edge>();


    public void primMst(int startVertex) {
        reachedSet.add(startVertex);
        addReachableFrom(startVertex);

        while (reachedSet.size() < numVertices) {
            int cheapestU = -1;
            int cheapestV = -1;
            int cheapestW = Integer.MAX_VALUE;
            //Cheapest edge from reached to reachable
            for (int u : reachedSet) {
                for (int v : reachableSet) {
                    if (graph[u][v] < cheapestW && !reachedSet.contains(v)) {
                        cheapestU = u;
                        cheapestV = v;
                        cheapestW = graph[u][v];
                    }
                }
            }
            // Add edge to tree
            treeSet.add(new Edge(cheapestU, cheapestV, graph[cheapestU][cheapestV]));
            // Add new discovered vertex to reached, add now discovered from it, and remove it from reachable
            reachedSet.add(cheapestV);
            addReachableFrom(cheapestV);
            reachableSet.remove(cheapestV);
        }
    }

    private void addReachableFrom(int u) {
        for (int v = 1; v <= numVertices; v++) {
            if (graph[u][v] < Integer.MAX_VALUE && !reachedSet.contains(v)) {
                reachableSet.add(v);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        PrimMST pmst = new PrimMST();

        URL url = JobScheduler.class.getResource("edges2.txt");
        File f = new File(url.getPath());
        BufferedReader br = new BufferedReader(new FileReader(f));

        String[] header = br.readLine().split("\\s+");
        pmst.numVertices = Integer.parseInt(header[0]);
        int numEdges = Integer.parseInt(header[1]);
        pmst.graph = new int[pmst.numVertices+1][pmst.numVertices+1];

        // Init blank graph
        for (int i = 0; i <= pmst.numVertices; i++) {
            for (int j = 0; j <= pmst.numVertices; j++) {
                pmst.graph[i][j] = Integer.MAX_VALUE;
            }
        }

        // Read in graph
        for (int i = 0; i < numEdges; i++) {
            String[] edgeString = br.readLine().split("\\s+");
            int u = Integer.parseInt(edgeString[0]);
            int v = Integer.parseInt(edgeString[1]);
            int w = Integer.parseInt(edgeString[2]);
            pmst.graph[u][v] = w;
            pmst.graph[v][u] = w;
        }

        pmst.primMst(1);

        int sum = 0;
        for (Edge e : pmst.treeSet) {
            //System.out.println("Incl: " + e.vertex1 + "," + e.vertex2 + " @ " + e.weight);
            sum += e.weight;
        }
        System.out.println("Tree size: " + pmst.treeSet.size());
        System.out.println("Tree sum: " + sum);
    }
}
