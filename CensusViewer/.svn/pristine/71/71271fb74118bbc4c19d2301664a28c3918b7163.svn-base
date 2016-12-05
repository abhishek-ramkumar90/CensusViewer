package com.mars.distribution.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TERRITORY_VILL_TOWN_MASTER",schema = "distribution")
public class TerritoryVillTown {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE )
	@Column(name="SERIAL")
    private int serial;
	
	@Column(name="STATE_CODE")
	private int stateCode;
	
	@Column(name="DIST_CODE")
	private int distCode;
	
	@Column(name="TOWM_VILLAGE_CODE")
	private int townVillCode;
	
	@Column(name="LEVEL")
	private String level;

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
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	public int getDistCode() {
		return distCode;
	}
	public void setDistCode(int distCode) {
		this.distCode = distCode;
	}
	public int getTownVillCode() {
		return townVillCode;
	}
	public void setTownVillCode(int townVillCode) {
		this.townVillCode = townVillCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
