package com.lisbon.Lisbon.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Trip {

	private int trip_id;
	private int route_id;
	private HashMap<String,Stop> stopsList;
	
	//Constructor
	public Trip(int trip_id) {
		this.trip_id = trip_id;
		stopsList = new HashMap<>();
	}
	
	public Trip(int trip_id, int route_id) {
		this.route_id = route_id;
		this.trip_id = trip_id;
		stopsList = new HashMap<>();
	}
	
	public int getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(int trip_id) {
		this.trip_id = trip_id;
	}
	public HashMap<String, Stop> getStopsList() {
		return stopsList;
	}
	public void setStopsList(HashMap<String,Stop> stopsList) {
		this.stopsList = stopsList;
	}
	public void addStopList(Stop s) {
		stopsList.put(s.getStop_id(),s);
	}
	
	public Stop getStop(String s) {
		return stopsList.get(s);
	}

	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}
	
	public String getStopsString() {
		String s = "";
		for (Entry<String, Stop> stop : stopsList.entrySet()) {
			//s+= "("+ Integer.toString(stop.getValue().getId()) +") " + stop.getValue().getStop_id() +",";
			s+= Integer.toString(stop.getValue().getId()) + ",";
		}
		return s;
	}


	

	
	
}
