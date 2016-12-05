package com.mars.distribution.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ZONE_STATES",schema = "distribution")
public class ZoneStates {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE )
	@Column(name="SERIAL")
	private int serial;
	
	@Column(name="STATE_ID")
private String stateId;

	@Column(name="STATE_NAME")
private String stateName;
	
	

public String getStateId() {
	return stateId;
}

public void setStateId(String stateId) {
	this.stateId = stateId;
}

public String getStateName() {
	return stateName;
}

public void setStateName(String stateName) {
	this.stateName = stateName;
}




	
}
