package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TerritoryDetails")
public class TerritoryService implements Comparator<TerritoryService> {
	private String terrId;
	private String terrName;
	private Collection<TerritoryService> terrList;
	public String getTerrId() {
		return terrId;
	}
	public void setTerrId(String terrId) {
		this.terrId = terrId;
	}
	public String getTerrName() {
		return terrName;
	}
	public void setTerrName(String terrName) {
		this.terrName = terrName;
	}
	
	public Collection<TerritoryService> getTerrList() {
		return terrList;
	}
	public void setTerrList(Collection<TerritoryService> terrList) {
		this.terrList = terrList;
	}
	@Override
	public int compare(TerritoryService t1, TerritoryService t2) {
		if(t1.getTerrName().equals(t2.getTerrName()))
		{
			return t1.getTerrName().compareTo(t2.getTerrName());
		}
		return t1.getTerrName().compareTo(t2.getTerrName());

	}
}
