package com.mars.distribution.model;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="ZONE_LIST_FINAL",schema = "distribution")
public class ZoneListFinal {
	@Id
	@GenericGenerator(name="seq_id", strategy="com.mars.distribution.id.generator.ZoneIdGenerator")
	@GeneratedValue(generator="seq_id")
	@Column(name = "ZONE_ID_CREATED", unique = true, nullable = false, length = 20)
	private String ZoneId;

	@Column(name="ZONE_NAME")
	private String ZoneName;

	@Column(name="STATUS")
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="zone",fetch = FetchType.LAZY,orphanRemoval=true)
	private Collection<ZoneStateListFinal> states=new ArrayList<ZoneStateListFinal>();
	
	/*@OneToMany(cascade=CascadeType.ALL,mappedBy="zoneId")
	private Collection<DistrictChannelIntermediaryDetail> distChannelInterDetail;

	public Collection<DistrictChannelIntermediaryDetail> getDistChannelInterDetail() {
		return distChannelInterDetail;
	}

	public void setDistChannelInterDetail(
			Collection<DistrictChannelIntermediaryDetail> distChannelInterDetail) {
		this.distChannelInterDetail = distChannelInterDetail;
	}*/

	public Collection<Region> getRegions() {
	/*	Hibernate.initialize(regions);*/
		return regions;
	}

	public void setRegions(Collection<Region> regions) {
		this.regions = regions;
	}

	@OneToMany(cascade= CascadeType.ALL, mappedBy="zone",fetch=FetchType.EAGER)
	private Collection<Region> regions;

	public String getZoneId() {
		return ZoneId;
	}

	public void setZoneId(String zoneId) {
		ZoneId = zoneId;
	}

	public String getZoneName() {
		return ZoneName;
	}

	public Collection<ZoneStateListFinal> getStates() {
		return states;
	}

	public void setStates(Collection<ZoneStateListFinal> states) {
		this.states = states;
	}

	public void setZoneName(String zoneName) {
		ZoneName = zoneName;
	}
}
