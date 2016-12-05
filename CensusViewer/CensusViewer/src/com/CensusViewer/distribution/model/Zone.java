package com.mars.distribution.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ZONE_MASTER",schema = "distribution")
public class Zone {

	@Id
	@GenericGenerator(name="seq_id", strategy="com.mars.distribution.id.generator.ZoneIdGenerator")
	@GeneratedValue(generator="seq_id")
	@Column(name = "ZONE_ID", unique = true, nullable = false, length = 20)
	private String zoneId;

	@Column(name="ZONE_NAME")
	private String zone;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="THE_GEOM")
	private String geom;

	@OneToMany(cascade= CascadeType.ALL, mappedBy="zone")
	private Collection<Region> regions;

	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="ZONE_ID")
	private Collection<ZoneStates> zoneStates;

	public Collection<ZoneStates> getZoneStates() {
		return zoneStates;
	}
	public void setZoneStates(Collection<ZoneStates> zoneStates) {
		this.zoneStates = zoneStates;
	}

	public Collection<Region> getRegions() {
		return regions;
	}
	public void setRegions(Collection<Region> regions) {
		this.regions = regions;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getGeom() {
		return geom;
	}
	public void setGeom(String geom) {
		this.geom = geom;
	}
}
