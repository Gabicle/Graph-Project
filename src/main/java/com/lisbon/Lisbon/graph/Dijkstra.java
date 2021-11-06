package com.lisbon.Lisbon.graph;



import java.io.IOException;
import java.util.*;

public class Dijkstra {

	private int sourceNode;
    private boolean[] marked;
    private int[] previous;
    private double[] distance;
    
    
    private boolean isNonNegative(EdgeWeightedGraph G) {
        for (int i = 0; i < G.getNumNodes(); i++) {
            LinkedList<Edge> edges = G.getNode(i);
            for (Edge n : edges) {
                if (n.weight() < 0) {
                	System.out.println("Negative Weight Found at Edge: " +n.toString());
                    return false;
                }
            }
        }
        return true;
    }
    
    
    public void findDijkstraSP(EdgeWeightedGraph G, int s){
		//Number of nodes
		int n = G.getNumNodes();
		
		//Declare arrays with the size
		previous = new int[n];
		distance = new double [n];
		
		//queue = new IndexMinPQ<Double>(n);
		ArrayList queue = new ArrayList<>(n);
		
		// Array of unvisited nodes
        ArrayList<Integer> unvisitedNodes = new ArrayList<>();
		
        ArrayList<Integer> visitOrder = new ArrayList<>();
        
		//Initialize arrays
		for (int i = 0; i < n; i++) { 
	        previous[i] = -1; 
	        distance[i] = Double.MAX_VALUE;	//As distance isn't known, it's set to maximum at the start
	        unvisitedNodes.add(i);	//Add all nodes to list
	    } 
		distance[s] = 0;
		unvisitedNodes.remove(s);
		visitOrder.add(s);
		
		//Get Starting node
		queue.add(s);
		
		while(!queue.isEmpty()) {
			//int iterator = queue.delMin();
			double smallestDistance = Double.MAX_VALUE;
            int smallestNode = -1;
            for (Integer thisNode : unvisitedNodes) {
                if (distance[thisNode] < smallestDistance) {
                    smallestDistance = distance[thisNode];
                    smallestNode = thisNode;
                }
            }
            unvisitedNodes.remove(smallestNode);
            visitOrder.add(smallestNode);
            if (smallestNode == -1) {
                break;
            }
            
        	// Check all neighbours and update distances
            for (Edge directedEdge : G.getNode(smallestNode)) {
                int childNode = directedEdge.other(smallestNode);
                double alt = distance[smallestNode] + directedEdge.weight();
                if (alt < distance[childNode]) {
                    marked[childNode] = true;
                    previous[childNode] = smallestNode;
                    distance[childNode] = alt;
                }
            }
		}
		
	}
    
    public ArrayList<Integer> parse(EdgeWeightedGraph G, int s) {
        // To ensure all weights of edges are positive.
        if (!isNonNegative(G)) {
        	System.out.println("Error! Unable to parse graph as negative weights exist");
            return null;
        }
        
        sourceNode = s;
        int v = G.getNumNodes() + 1;
        marked = new boolean[v];
        previous = new int[v];
        distance = new double[v];
        
        // Array of unvisited nodes
        HashSet<Integer> openedNodes = new HashSet<>();
        
        // Initialize all nodes with maximum distance as not visited yet
        for (int i = 0; i < v; i++) {
            previous[i] = -1; // Undefined
            distance[i] = Double.MAX_VALUE; // Infinite
            openedNodes.add(i);
        }
        // Use an array list to record visit orders.
        ArrayList<Integer> visitOrder = new ArrayList<>();
        // Distance from source to source
        distance[s] = 0; // distance
        marked[s] = true; // mark
        visitOrder.add(s); // visit
        
        while (!openedNodes.isEmpty()) {
            // Choose the smallest distance.
            double smallestDistance = Double.MAX_VALUE;
            int smallestNode = -1;
            for (Integer thisNode : openedNodes) {
                if (distance[thisNode] < smallestDistance) {
                    smallestDistance = distance[thisNode];
                    smallestNode = thisNode;
                }
            }
            // Go to the smallest one.
            openedNodes.remove(smallestNode);
            visitOrder.add(smallestNode);
            // If remained nodes are not available, it is not a connected graph, terminate the progress.
            if (smallestNode == -1) {
                break;
            }
            
            // Check all neighbours and update distances
            for (Edge directedEdge : G.getNode(smallestNode)) {
                int childNode = directedEdge.other(smallestNode);
                double alt = distance[smallestNode] + directedEdge.weight();
                if (alt < distance[childNode]) {
                    marked[childNode] = true;
                    previous[childNode] = smallestNode;
                    distance[childNode] = alt;
                }
            }
        }
        return visitOrder;
    }

    boolean hasPathTo(int v) {
        return marked[v];
    }

    double distTo(int v) {
        return distance[v];
    }

    public ArrayList<Integer> pathTo(int v) {
        ArrayList<Integer> shortestPath = new ArrayList<>();
        int thisNode = v;
        while (thisNode > -1) {
            shortestPath.add(thisNode);
            thisNode = previous[thisNode];
            if (thisNode == sourceNode) {
                shortestPath.add(sourceNode);
                break;
            }
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }

    public static void main(String[] args) throws IOException {
    	
        EdgeWeightedGraph graph = new EdgeWeightedGraph("src/main/java/com/lisbon/Lisbon/Output-Data/edges.txt");

        Dijkstra dijkstraSP = new Dijkstra();
        
        //System.out.println("Nodes # : " + graph.getNodeCount() + "\tEdges # " + graph.getEdgeCount());
        
        //graph.print();
   
        dijkstraSP.parse(graph, 34);
        ArrayList<Integer> suggestedPath = dijkstraSP.pathTo(44);
        System.out.println(suggestedPath);
        System.out.println("Number of nodes:" + suggestedPath.size());
        

		/**/
    }
	
}