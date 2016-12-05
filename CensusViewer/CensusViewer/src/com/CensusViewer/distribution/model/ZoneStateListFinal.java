package com.mars.distribution.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


@Entity
@Table(name="Zone_State_List_Final",schema = "distribution")
public class ZoneStateListFinal {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE )
	@Column(name="SERIAL")
	private int serial;

	@Column(name="state_name")
	private String stateName;

	public int getStatus() {
		return Status;
	}
	public void setStatus(int one) {
		Status = one;
	}

	@Column(name="Status")
	private int Status;

	@ManyToOne(fetch=FetchType.LAZY,cascade= CascadeType.ALL)
		@JoinColumn(name="ZONE_ID")
	private ZoneListFinal zone;

	@XmlTransient
	public ZoneListFinal getZone() {
		return zone;
	}
	public void setZone(ZoneListFinal zone) {
		this.zone = zone;
	}

	@Column(name="state_id")
	private String stateId;

	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
}
