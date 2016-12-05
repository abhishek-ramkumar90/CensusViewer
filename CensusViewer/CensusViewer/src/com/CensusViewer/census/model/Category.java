package com.mars.census.model;


import java.math.BigDecimal;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;





@XmlRootElement(name = "Category")
public class Category implements java.io.Serializable {
	private Integer cat_id;
	private String cat_name;
	private String cat_alt;
	 private Collection<Category> categories;
	public Collection<Category> getCategories() {
		return categories;
	}
	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
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
	public String getCat_alt() {
		return cat_alt;
	}
	public void setCat_alt(String cat_alt) {
		this.cat_alt = cat_alt;
	}
	

}