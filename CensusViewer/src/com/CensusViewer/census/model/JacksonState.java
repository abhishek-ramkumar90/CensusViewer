package com.CensusViewer.census.model;

import java.math.BigDecimal;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "JacksonState")
public class JacksonState implements java.io.Serializable {

	private Collection<maharashtraState> jacksonStateDetails;

	public Collection<maharashtraState> getJacksonStateDetails() {
		return jacksonStateDetails;
	}

	public void setJacksonStateDetails(
			Collection<maharashtraState> jacksonStateDetails) {
		this.jacksonStateDetails = jacksonStateDetails;
	}




		
//"{'villages':[{'districtco':'071','name':'GHUIKHED'},{'districtco':'071','name':'Mamdabad'}],'districtCode':'071','districtName':'Amravati','serialNo':0}}";



		
		
	 
	

}
