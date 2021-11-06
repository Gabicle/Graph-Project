package com.lisbon.Lisbon.graph;

import java.io.IOException;
import java.util.*;

public class GraphCluster {
    private HashSet<Edge> edgeSet;

    public GraphCluster(EdgeWeightedGraph G)
    {
        edgeSet = new HashSet<>();
        for (int node:G.getNodeset()) {
            edgeSet.addAll(G.getNode(node));
        }
        edgeSet.forEach(Edge::cleanBetweenness);
        BFSShortestPath bfsShortestPath = new BFSShortestPath();

        //Loop through all nodes and perform BFS
        for (int node:G.getNodeset())
        {
            bfsShortestPath.bfs(G, node);
            for (Integer integer : G.getNodeset()) {
            	//add 'Betweenness' to every edge 
                for (int t = integer; t != node; t = bfsShortestPath.edgeTo(t).other(t)) {
                    bfsShortestPath.edgeTo(t).addBetweenness(1);
                }
            }

        }
    }

    public ArrayList<Edge> edgesSortByBetweenness() {
        ArrayList<Edge> edgeArray = new ArrayList<>(edgeSet);
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        edgeArray.sort((o1, o2) -> {
            if (o1.getBetweenness() < o2.getBetweenness()) return 1;
            return -1;
        });
        return edgeArray;
    }

    public static void main(String[] args) throws IOException {

        EdgeWeightedGraph graph = new EdgeWeightedGraph("src/main/java/com/lisbon/Lisbon/Output-Data/edges.txt");

        GraphCluster bfsGraphCluster = new GraphCluster(graph);
        while (true) {
            ArrayList<Edge> edgeSortByBetweenness = bfsGraphCluster.edgesSortByBetweenness();
            if (edgeSortByBetweenness.size() > 0) {
                Edge e = edgeSortByBetweenness.get(0);
                System.out.println("Edge to remove: " + e.from() + "->" + e.other(e.from()) + ", betweenness = " + Integer.toString(e.getBetweenness()));
                graph.removeEdge(e);
                if (!BFSShortestPath.isConnectedGraph(graph))
                {
                    break;
                }
                bfsGraphCluster = new GraphCluster(graph);
            }
        }
        BFSShortestPath shortestPath = new BFSShortestPath();
        ArrayList<ArrayList<Integer>> bfsResultAll = shortestPath.bfsAll(graph);
        for (ArrayList<Integer> bfsResult : bfsResultAll) {
            System.out.println("\n" + bfsResult + ", length = " + bfsResult.size());
        }

    }
}