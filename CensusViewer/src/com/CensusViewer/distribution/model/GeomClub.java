package com.CensusViewer.distribution.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.spatial.GeometryType;



import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;

@Entity
@Table(name="GeomClub",schema="distribution")
@NamedNativeQueries( {
@NamedNativeQuery(name = "GeomClub",query = "SELECT (multi(ST_Union(the_geom))) as the_geom  from district where distcode in('27','28') ", resultClass = GeomClub.class)})
public class GeomClub {
@Id
	@Type(type="org.hibernate.spatial.GeometryType")
	private Geometry the_geom;
	public Geometry getThe_geom() {
		return the_geom;
	}
	public void setThe_geom(Geometry the_geom) {
		this.the_geom = the_geom;
	}
 
}
