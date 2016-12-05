package com.mars.distribution.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Geometry;
@Entity
@Table(name="DISTRICT_MASTER",schema = "distribution")

public class Districts {
	private String distName;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE )
	@Column(name="SERIAL")
    private int serial; 
	
	@Column(name="gid")
	private int gid;

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}
	@Column(name="STATUS")
	private int status;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}
	@Column(name="DIST_ID")
	private int distId;
	
	@Column(name="STATE_ID")
	private String stateId;
	
	@Column(name="THE_GEOM")
	@Type(type="org.hibernate.spatial.GeometryType")

	private Geometry the_geom;

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	@Transient
	private Collection<Districts> districts1;
	
	public Collection<Districts> getDistricts() {
		return districts1;
	}

	public void setDistricts(Collection<Districts> districts1) {
		this.districts1 = districts1;
	}
	@XmlTransient
	public Geometry getThe_geom() {
		return the_geom;
	}

	public void setThe_geom(Geometry the_geom) {
		this.the_geom = the_geom;
	}

	@Column(name="DIST_NAME")
   public String getDistName() {
		return distName;
	}
	
	public void setDistName(String distName) {
		this.distName = distName;
	}

	public int getDistId() {
		return distId;
	}

	public void setDistId(int distId) {
		this.distId = distId;
	}

	public Collection<Districts> getDistricts1() {
		return districts1;
	}

	public void setDistricts1(Collection<Districts> districts1) {
		this.districts1 = districts1;
	}
}
