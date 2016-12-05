package com.CensusViewer.distribution.ServicePojo;

import java.util.ArrayList;
import java.util.Collection;

import com.CensusViewer.distribution.model.IntermediaryFreeFlow;

public class jacksonIntermediaryUpdate {

private String parentId;

private Collection<Integer> childId=new ArrayList<Integer>();

private String root;

private Collection<IntermediaryFreeFlow> intermediaryDetails=new ArrayList<IntermediaryFreeFlow>();

public String getParentId() {
	return parentId;
}

public void setParentId(String parentId) {
	this.parentId = parentId;
}

public Collection<IntermediaryFreeFlow> getIntermediaryDetails() {
	return intermediaryDetails;
}

public void setIntermediaryDetails(
		Collection<IntermediaryFreeFlow> intermediaryDetails) {
	this.intermediaryDetails = intermediaryDetails;
}

public Collection<Integer> getChildId() {
	return childId;
}
public void setChildId(Collection<Integer> childId) {
	this.childId = childId;
}

public String getRoot() {
	return root;
}
public void setRoot(String root) {
	this.root = root;
}
	
}
