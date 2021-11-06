package com.lisbon.Lisbon.model;

public class Edge {
	
	private String id;
	private int node1;
	private int node2;
	private double distance;
	
	//Constructor
	public Edge(String id, int node1, int node2, double distance) {
		super();
		this.id = id;
		this.node1 = node1;
		this.node2 = node2;
		this.distance = distance;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNode1() {
		return node1;
	}
	public void setNode1(int node1) {
		this.node1 = node1;
	}
	public int getNode2() {
		return node2;
	}
	public void setNode2(int node2) {
		this.node2 = node2;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Edge: " +id +  " [node1=" + node1 + ", node2=" + node2 + ", distance=" + distance + "]";
	}
	
	
	
}
