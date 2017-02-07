package week5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Naive implementation, 200 size undirected graph
 */
public class Dijkstra {

    //!! NOTE: Vertices start from 1 not 0
    int[][] graph;
    int[] dist;
    int[] prev;
    Set<Integer> Q = new HashSet<Integer>();


    public int[] dijkstra(String fileName, int source) throws IOException {
        initialize(fileName, source);

        while (!Q.isEmpty()) {
            int u = findMinVertexInQ();
            Q.remove(u);

            //For each neighbor v of u, where v is still in Q
            for (int v = 1; v < graph.length; v++) {
                // Does edge exist in graph and is it still in Q
                if (graph[u][v] > 0 && Q.contains(v)) {
                    int alt = dist[u] + graph[u][v];
                    // Is there a shorter path to v
                    if (alt < dist[v]) {
                        dist[v] = alt;
                        prev[v] = u;
                    }
                }
            }
        }
        return dist;
    }

    private int findMinVertexInQ() {
        int minWeight = Integer.MAX_VALUE;
        int minU = -1;
        for (int u : Q) {
            if (dist[u] < minWeight) {
                minWeight = dist[u];
                minU = u;
            }
        }
        return minU;
    }

    private String shortestPath(int source, int target) {
        String path = "";
        int node = target;
        while (node != source) {
            int prevVert = prev[node];
            node = prevVert;
            path = path + node + ",";
        }
        return path;
    }

    private void initialize(String fileName, int source) throws IOException {
        URL url = Dijkstra.class.getResource(fileName);
        File file = new File(url.getPath());

        int numLines = Files.readAllLines(Paths.get(file.getPath()), Charset.defaultCharset()).size();

        graph = new int[numLines+1][numLines+1];
        dist = new int[numLines+1];
        prev = new int[numLines+1];

        for (int v[] : graph) {
            Arrays.fill(v, -1);
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[source] = 0;
        for (int v = 1; v < graph.length; v++) {
            Q.add(v);
        }

        readInput(file);
    }

    private void readInput(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        String in;
        while ((in = br.readLine()) != null) {
            String[] input = in.split("\\s+");
            int vertex = Integer.parseInt(input[0]);
            for (int i = 1; i < input.length; i++) {
                String[] edgeAndWeight = input[i].split(",");
                int vertex2 = Integer.parseInt(edgeAndWeight[0]);
                int weight = Integer.parseInt(edgeAndWeight[1]);
                graph[vertex][vertex2] = weight;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Dijkstra d = new Dijkstra();
        int[] result = d.dijkstra("testGraph", 1);

        /*
        //7,37,59,82,99,115,133,165,188,197
        System.out.print(result[7] + ",");
        System.out.print(result[37] + ",");
        System.out.print(result[59] + ",");
        System.out.print(result[82] + ",");
        System.out.print(result[99] + ",");
        System.out.print(result[115] + ",");
        System.out.print(result[133] + ",");
        System.out.print(result[165] + ",");
        System.out.print(result[188] + ",");
        System.out.print(result[197] + ",");
        */
    }
}
