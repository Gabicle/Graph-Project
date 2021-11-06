package com.lisbon.Lisbon.model;


//Implement Comparable to order stops in list
public class Stop implements Comparable<Stop>{
	
	private int id;
	private String stop_id;
	private String stop_name;
	private Double stop_lat;
	private Double stop_lon;
	private int stop_sequence;
	private int route_id;
	private String parent_station;

	
	//Constructor
	public Stop(int id, String stop_id, String stop_name, Double stop_lat, Double stop_lon, String parent_station) {
		super();
		this.id = id;
		this.stop_id = stop_id;
		this.stop_name = stop_name;
		this.stop_lat = stop_lat;
		this.stop_lon = stop_lon;
		this.stop_sequence = -1;	//Unset value
		this.route_id = 0;
		this.parent_station = parent_station;
	}

	public Stop(int id, String stop_id, String stop_name, Double stop_lat, Double stop_lon, int stop_sequence,
			int route_id) {
		super();
		this.id = id;
		this.stop_id = stop_id;
		this.stop_name = stop_name;
		this.stop_lat = stop_lat;
		this.stop_lon = stop_lon;
		this.stop_sequence = stop_sequence;
		this.route_id = route_id;
	}

	public Stop() {
	}

	public Stop(int id, String stop_id, String stop_name) {
		this.id = id;
		this.stop_id = stop_id;
		this.stop_name = stop_name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStop_id() {
		return stop_id;
	}
	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}
	public String getStop_name() {
		return stop_name;
	}
	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}
	public Double getStop_lat() {
		return stop_lat;
	}
	public void setStop_lat(Double stop_lat) {
		this.stop_lat = stop_lat;
	}
	public Double getStop_lon() {
		return stop_lon;
	}
	public void setStop_lon(Double stop_lon) {
		this.stop_lon = stop_lon;
	}

	public int getStop_sequence() {
		return stop_sequence;
	}

	public void setStop_sequence(int stop_sequence) {
		this.stop_sequence = stop_sequence;
	}

	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}
	
	public String getParent_station() {
		return parent_station;
	}

	public void setParent_station(String parent_station) {
		this.parent_station = parent_station;
	}

	@Override
	public String toString() {
		return "Stop [stop_id=" + stop_id + ", stop_name=" + stop_name + ", stop_sequence=" + stop_sequence
				+ ", route_id=" + route_id + "]";
	}

	
	//For ordering according to stop_sequence
	@Override
	public int compareTo(Stop s) {
		return (int)(this.stop_sequence - s.getStop_sequence());
	}
	
	
}
