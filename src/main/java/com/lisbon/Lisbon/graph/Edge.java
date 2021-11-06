package com.lisbon.Lisbon.graph;

public class Edge implements Comparable<Edge> {
	
    private final int v;
    private final int w;
    private final double weight;
    private int betweenness;

    Edge(int from, int to, double weight) {
        this.v = from;
        this.w = to;
        this.weight = weight;
        betweenness = 0;
    }

    //For Unweighted Graph
    Edge(int from, int to) {
        this.v = from;
        this.w = to;
        this.weight = 1;
        betweenness = 0;
    }
    
    public int from() {
        return v;
    }

    public int to() {
        return w;
    }
    
    //Returns the endpoint in an edge
    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        throw new IllegalArgumentException("Illegal endpoint");
    }

    public double weight() {
        return weight;
    }
    
    public int getBetweenness() {
        return betweenness;
    }

    public void addBetweenness(int delta) {
        betweenness += delta;
    }

    public void cleanBetweenness() {
        betweenness = 0;
    }


    @Override
    public int compareTo(Edge o) {
        return (this.weight > o.weight()) ? 1 : -1;
    }

    @Override
    public String toString() {
        return "(" + Integer.toString(v) + ", " + Integer.toString(w) + ", " + Double.toString(weight) + ")";
    }
}
