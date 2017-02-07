package week4;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Kosaraju {

    //!!!NOTE!!! Nodes start at 1 not 0.

    //Graphs
    Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
    Map<Integer, List<Integer>> graphRev = new HashMap<Integer, List<Integer>>();
    int nodeCount = 0;
    //SCC -- sccId -> sccSize
    int sccId = -1;
    int sccSize = 0;
    List<Integer> sccList = new ArrayList<Integer>();
    //Explored List -- node -> explored?
    List<Boolean> exploredList;
    //Finishing Time List -- finishingTime -> node
    List<Integer> finishingTimeList;
    int finishingTime = 0;



    private void readInput() throws IOException {
        URL url = Kosaraju.class.getResource("SCC.txt");
        File file = new File(url.getPath());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String in;
        while ((in = br.readLine()) != null) {
            String[] edge = in.split("\\s+");
            int source = Integer.parseInt(edge[0]);
            int dest = Integer.parseInt(edge[1]);

            //Add to graph. Create list if does not exist.
            List<Integer> adjList = graph.get(source);
            if (adjList == null) {
                adjList = new ArrayList<Integer>();
                adjList.add(dest);
            }
            else {
                adjList.add(dest);
            }
            graph.put(source, adjList);

            //Create dest node if not added yet
            if (graph.get(dest) == null) {
                graph.put(dest, new ArrayList<Integer>());
            }

            //Add to Reverse Graph
            adjList = graphRev.get(dest);
            if(adjList == null) {
                adjList = new ArrayList<Integer>();
                adjList.add(source);
            }
            else {
                adjList.add(source);
            }
            graphRev.put(dest, adjList);

            //Create source (now dest in rev graph) if not added yet
            if (graphRev.get(source) == null) {
                graphRev.put(source, new ArrayList<Integer>());
            }
        }
    }

    public List<Integer> findSCCs() {
        if (graph.size() != graphRev.size()) {
            System.out.println("Problem with Reading Graph file. Graph and Reverse Graph different size.");
            return sccList;
        }

        //Initialize helpers
        nodeCount = graph.size();
        exploredList = new ArrayList<Boolean>(Arrays.asList(new Boolean[nodeCount+1]));
        Collections.fill(exploredList, false);
        finishingTimeList = new ArrayList<Integer>(Arrays.asList(new Integer[nodeCount+1]));
        Collections.fill(finishingTimeList, 0);

        //Run DFS from last node on Reverse Graph to find finishing times.
        for (int i = nodeCount; i >= 1; i--) {
            if (!exploredList.get(i)) {
                DFSFirstPass(i);
            }
        }

        Collections.fill(exploredList, false);

        //Run DFS from last finishTime on Normal Graph to find SCC and size
        for (int i = nodeCount; i>= 1; i--) {
            //finishTime -> node
            int node = finishingTimeList.get(i);
            if (!exploredList.get(node)) {
                //Run DFS on regular graph with same leader = SCC identified
                sccId++;
                sccSize = 0;
                DFSSecondPass(node);
                sccList.add(sccId, sccSize);
            }
        }

        return sccList;
    }

    private void DFSSecondPass(int node) {
        exploredList.set(node, true);
        //Part of same SCC
        sccSize++;
        List<Integer> adjList = graph.get(node);
        for(Integer j : adjList) {
            if (!exploredList.get(j)) {
                DFSSecondPass(j);
            }
        }
    }

    private void DFSFirstPass(int node) {
        exploredList.set(node, true);
        List<Integer> adjList = graphRev.get(node);
        for(Integer j : adjList) {
            if (!exploredList.get(j)) {
                DFSFirstPass(j);
            }
        }
        finishingTime++;
        finishingTimeList.set(finishingTime, node);
    }

    public static void main(String[] args) throws IOException {
        Kosaraju kosaraju = new Kosaraju();
        kosaraju.readInput();
        List<Integer> scc = kosaraju.findSCCs();

        //Get 5 largest
        Collections.sort(scc, Collections.reverseOrder());
        for(int i = 0; i < 5; i++) {
            System.out.print(scc.get(i) + ",");
        }
        System.out.println();

        /*
        //SCC2 example graph
        System.out.println(scc.get(0));
        System.out.println(scc.get(1));
        System.out.println(scc.get(2));
        */
    }

}
