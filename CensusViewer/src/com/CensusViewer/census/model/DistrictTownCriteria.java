package com.CensusViewer.census.model;

public class DistrictTownCriteria {
	private String stateDistrict;
	private String columns;
	private String criteria;
	private String value;
	

	public String getStateDistrict() {
		return stateDistrict;
	}
	public void setStateDistrict(String stateDistrict) {
		this.stateDistrict = stateDistrict;
	}
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}