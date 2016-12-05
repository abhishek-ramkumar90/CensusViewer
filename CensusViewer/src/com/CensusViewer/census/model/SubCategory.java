package com.CensusViewer.census.model;


import java.math.BigDecimal;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;





@XmlRootElement(name = "SubCategory")
public class SubCategory implements java.io.Serializable {
	private Integer cat_id;
	private String cat_name;
	private BigDecimal min;
	private BigDecimal max;
	private String sub_category;
	private Integer sub_cat_id;
	private String column_name;
	private String segment;
	private String State;
	private String Tehsil;
	private String district;
    private String data_type;
	
	
	 public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getTehsil() {
		return Tehsil;
	}
	public void setTehsil(String tehsil) {
		Tehsil = tehsil;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public Integer getSub_cat_id() {
		return sub_cat_id;
	}
	public void setSub_cat_id(Integer sub_cat_id) {
		this.sub_cat_id = sub_cat_id;
	}
	public BigDecimal getMin() {
		return min;
	}
	public void setMin(BigDecimal min) {
		this.min = min;
	}
	public BigDecimal getMax() {
		return max;
	}
	public void setMax(BigDecimal max) {
		this.max = max;
	}
	public String getSub_category() {
		return sub_category;
	}
	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}
	private Collection<SubCategory> subcategories;
	
	public Collection<SubCategory> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(Collection<SubCategory> subcategories) {
		this.subcategories = subcategories;
	}
	public Integer getCat_id() {
		return cat_id;
	}
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	

}