package com.lisbon.Lisbon.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Route {

	private int route_id;
	private String route_name;
	private HashMap<Integer, Trip> tripsList = new HashMap<Integer, Trip>();
	

    public Route(int route_id){
    	this.route_id = route_id;
    }
	
	public int getRoute_id() {
		return route_id;
	}
	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}
	
	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}

	public void addTrip(Trip t) {
		tripsList.put(t.getTrip_id(), t);
	}

	public HashMap<Integer, Trip> getTripsList() {
		return tripsList;
	}

	public void setTripsList(HashMap<Integer, Trip> tripsList) {
		this.tripsList = tripsList;
	}
	
	public Trip getTrip(int n) {
		return tripsList.get(n);
	}
	
	public void updateTrip(Trip t) {
		tripsList.remove(t.getTrip_id());
		tripsList.put(t.getTrip_id(), t);
	}

	
	@Override
	public String toString() {
		String s = "";
		for (Entry<Integer, Trip> trip : tripsList.entrySet()) {
			s+= Integer.toString(trip.getValue().getTrip_id()) + ",";
		}

		return "," + route_name + "\tTrips: " + s;
	}
	
	
	
}
