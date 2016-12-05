package com.CensusViewer.distribution.ServicePojo;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "Shortestpath")
public class ShortestPathJson implements java.io.Serializable {
	private Collection<String> WKTList;
	public Collection<String> getWKTList() {
		return WKTList;
	}
	public void setWKTList(Collection<String> wKTList) {
		WKTList = wKTList;
	}
}
