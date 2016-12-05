package com.CensusViewer.census.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="sub_category")
@Table(name="sub_category")
public class SubcategoryTable {
	
	@Column(name="column_name")
	private String column_name;
	@Column(name="category")
	private String category;
	@Column(name="sub_category")
	private String sub_category;
	@Column(name="segment")
	private String segment;
	@Id
	@Column(name="sub_cat_id")
	private Integer sub_cat_id;
	@Column(name="cat_id")
	private Integer cat_id;
	@Column(name="state")
	private String state;
	
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSub_category() {
		return sub_category;
	}
	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public Integer getSub_cat_id() {
		return sub_cat_id;
	}
	public void setSub_cat_id(Integer sub_cat_id) {
		this.sub_cat_id = sub_cat_id;
	}
	public Integer getCat_id() {
		return cat_id;
	}
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


}
