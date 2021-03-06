package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;

import com.CensusViewer.census.model.DistrictTownCriteria;

public class JacksonChannelDetails {
private String channelName;
private int levelCount;
private Collection<JacksonInterm> interMediaryType;

public String getChannelName() {
	return channelName;
}
public void setChannelName(String channelName) {
	this.channelName = channelName;
}
public Collection<JacksonInterm> getInterMediaryType() {
	return interMediaryType;
}
public void setInterMediaryType(Collection<JacksonInterm> interMediaryType) {
	this.interMediaryType = interMediaryType;
}
public int getLevelCount() {
	return levelCount;
}
public void setLevelCount(int levelCount) {
	this.levelCount = levelCount;
}

 
}
