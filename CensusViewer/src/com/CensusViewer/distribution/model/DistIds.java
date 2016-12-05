package com.CensusViewer.distribution.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="distIds",schema="distribution")
public class DistIds {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private int id;
	
	@Column(name="disIds")
	private String distIds;

	public int getId() {
		return id;
	}
	@Column(name="status")
	private int Status;
	
	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getDistIds() {
		return distIds;
	}

	public void setDistIds(String distIds) {
		this.distIds = distIds;
	}
	
	
	

}
