package com.mars.census.model;

import java.util.Collection;


public class DetailStateJackson implements java.io.Serializable {
private String	st_code;
private String st_name;

public String getSt_name() {
	return st_name;
}

public void setSt_name(String st_name) {
	this.st_name = st_name;
}

private Collection<CategoryJackson> category;
public String getSt_code() {
	return st_code;
}

public void setSt_code(String st_code) {
	this.st_code = st_code;
}
public Collection<CategoryJackson> getCategory() {
	return category;
}

public void setCategory(Collection<CategoryJackson> category) {
	this.category = category;
}


}
