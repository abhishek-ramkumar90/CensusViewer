package com.mars.census.model;

public class MapDto {
	
	private String bbox;
	private String Xcord;
	private String Ycord;
	
	
	private String gid;
	private String name;
	private String statscode;
	private String dist_code; 
	private String	tehsilcode;
	private Float Lat;
	private Float Lon;
	public Float getLat() {
		return Lat;
	}
	public void setLat(Float lat) {
		Lat = lat;
	}
	public Float getLon() {
		return Lon;
	}
	public void setLon(Float lon) {
		Lon = lon;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatscode() {
		return statscode;
	}
	public void setStatscode(String statscode) {
		this.statscode = statscode;
	}
	public String getDist_code() {
		return dist_code;
	}
	public void setDist_code(String dist_code) {
		this.dist_code = dist_code;
	}
	public String getTehsilcode() {
		return tehsilcode;
	}
	public void setTehsilcode(String tehsilcode) {
		this.tehsilcode = tehsilcode;
	}
	public String getBbox() {
		return bbox;
	}
	public void setBbox(String bbox) {
		this.bbox = bbox;
	}
	public String getXcord() {
		return Xcord;
	}
	public void setXcord(String xcord) {
		Xcord = xcord;
	}
	public String getYcord() {
		return Ycord;
	}
	public void setYcord(String ycord) {
		Ycord = ycord;
	}
	

}
