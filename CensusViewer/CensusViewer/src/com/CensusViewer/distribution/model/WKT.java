package com.mars.distribution.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
@Entity
@Table(name="WKT")
@NamedNativeQueries( {
@NamedNativeQuery(name = "WKTList",query = "SELECT a.edge_id,astext(n.the_geom) As WKT FROM shortest_path('SELECT gid AS id,the_geom ,start_id::int4 AS source, end_id::int4 AS target,cost::float8 AS cost FROM network',:startNode,:endNode,false,false)a inner join network n on a.edge_id = n.gid;", resultClass = WKT.class)})
public class WKT {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Edge_id")
private int edge_id;
	
	@Column(name="WKT")
private String wkt;
	
public int getEdge_id() {
	return edge_id;
}
public void setEdge_id(int edge_id) {
	this.edge_id = edge_id;
}
public String getWkt() {
	return wkt;
}
public void setWkt(String wkt) {
	this.wkt = wkt;
}
}
