package com.CensusViewer.census.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Result")
public class Result {
	
	private Collection<Rows> result;

	public Collection<Rows> getResult() {
		return result;
	}

	public void setResult(Collection<Rows> result) {
		this.result = result;
	}

}
