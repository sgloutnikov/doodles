package week3;

import java.io.*;
import java.net.URL;
import java.util.*;


public class RandomizedContraction {

    Map<Integer, List<Integer>> graph;
    Random rng;

    public int computeMinCut(int seed) throws IOException {
        graph = new HashMap<Integer, List<Integer>>();
        rng = new Random(seed);

        //TODO: Read once and clone
        URL url = RandomizedContraction.class.getResource("kargerMinCut.txt");
        File file = new File(url.getPath());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String in;
        while ((in = br.readLine()) != null) {
            String[] inputLine = in.split("\\s+");
            List<Integer> node = new ArrayList<Integer>();
            int nodeId = Integer.parseInt(inputLine[0]);
            for (int i = 1; i < inputLine.length; i++) {
                node.add(Integer.parseInt(inputLine[i]));
            }
            graph.put(nodeId, node);
        }

        while (graph.size() > 2) {
            List<Integer> vertexList = new ArrayList<Integer>(graph.keySet());
            int keepVertex = vertexList.get(rng.nextInt(vertexList.size()));
            List<Integer> keepVertexAdjList = graph.get(keepVertex);
            int delVertex = keepVertexAdjList.get(rng.nextInt(keepVertexAdjList.size()));
            List<Integer> delVertexAdjList = graph.get(delVertex);

            graph.remove(delVertex);

            // Replace delVertex with fused keepVertex in delVertex connections
            for (int vertex : delVertexAdjList) {
                List<Integer> tmpVertexAdjList = graph.get(vertex);
                for (int i = 0; i < tmpVertexAdjList.size(); i++) {
                    if (tmpVertexAdjList.get(i) == delVertex) {
                        tmpVertexAdjList.set(i, keepVertex);
                    }
                }
            }

            keepVertexAdjList.addAll(delVertexAdjList);

            graph.put(keepVertex, removeLoop(keepVertexAdjList, keepVertex));
        }

        int minCutSize = 0;
        for (int vertKey : graph.keySet()) {
            minCutSize = graph.get(vertKey).size();
        }
        return minCutSize;
    }

    public List<Integer> removeLoop(List<Integer> adjList, int vertex) {
        List<Integer> tmpKeepVertexAdjList = new ArrayList<Integer>();
        for (int i = 0; i < adjList.size(); i++) {
            if (adjList.get(i) != vertex) {
                tmpKeepVertexAdjList.add(adjList.get(i));
            }
        }
        return tmpKeepVertexAdjList;
    }

    public static void main (String[] args) throws IOException {
        RandomizedContraction rc = new RandomizedContraction();

        int minCut = Integer.MAX_VALUE;
        for (int i = 2; i < 1000; i++) {
            int cut = rc.computeMinCut(i);
            if (cut < minCut) {
                minCut = cut;
            }
        }

        System.out.println(minCut);
    }
}
