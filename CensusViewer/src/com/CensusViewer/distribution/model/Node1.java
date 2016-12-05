package com.CensusViewer.distribution.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;




@Entity

@Table(name="Node1")
/*@org.hibernate.annotations.Table(appliesTo="Node1",
indexes = { @Index(name="ranking_ix", columnNames = { "id", "d" } ) })*/
@NamedNativeQueries( {
@NamedNativeQuery(name = "NodeList",query = "SELECT st_distance(the_geom,setsrid(makepoint(:latitude,:logitude), 4326) ) as d,id FROM node WHERE (id < 100000000000000) AND (st_dwithin(setsrid(makepoint(:latitude,:logitude),4326) , the_geom,500)) order by d limit 1", resultClass = Node1.class)})

public class Node1 {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
/*	@Column(name="lat")
	private double Lat;
	
	@Column(name="long")
	private double Long;*/
	
	@Column(name="d")
	private double d;
	
	
	public double getD() {
		return d;
	}
	public void setD(double d) {
		this.d = d;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
/*	public double getLat() {
		return Lat;
	}
	public void setLat(double lat) {
		Lat = lat;
	}
	public double getLong() {
		return Long;
	}
	public void setLong(double l) {
		Long = l;
	}*/
	
}
