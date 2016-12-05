package com.CensusViewer.distribution.map;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.postgis.Geometry;
@Entity
@Table(name="DISTRICT_GEOM",schema = "distribution")

public class Geometryall implements Serializable  {
/*	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE )
	@Column(name="gid")
	private int serial; */

@Id
	@Column(name="THE_GEOM")
	private Geometry the_geom;

	public Geometry getThe_geom() {
		return the_geom;
	}

	public void setThe_geom(Geometry the_geom) {
		this.the_geom = the_geom;
	}


}
