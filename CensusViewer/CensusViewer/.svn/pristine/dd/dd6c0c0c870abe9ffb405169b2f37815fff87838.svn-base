package com.mars.census.service.impl;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.mars.census.impl.DistrictDetails;
import com.mars.census.model.District;



@Path("/DistrictDetailService")
@Produces({"application/json","application/xml"})
public class DistrictRestService {
	
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);

	private DistrictDetails districtDAO = new DistrictDetails();


	public DistrictDetails getDistrictDAO() {

		return districtDAO;
	}

	public void setDistrictDAO(DistrictDetails districtDAO ) {
		this.districtDAO = districtDAO;

	}
	
	
	
	@POST
	@Path("/Country/districtDetails")
	@Consumes({"application/json","application/xml"})
	@Produces ({"application/json"})
	public Response getDistricts(String jacksonStateDetails1) {
		
		logger.info("Entering " + CLASS_NAME);
		District dis=new District();
		dis.setDistricts(getDistrictDAO().districtDetails(jacksonStateDetails1));
		return Response.ok(dis).build();
	}

}
