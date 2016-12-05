package com.mars.census.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.mars.census.impl.VillageDetails;
import com.mars.census.model.Village;





@Path("/VillageDetailService")
@Produces({"application/json","application/xml"})
public class VillageRestService {

	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);

	private VillageDetails villageDAO = new VillageDetails();


	public VillageDetails getVillageDAO() {

		return villageDAO;
	}

	public void setVillageDAO(VillageDetails villageDAO ) {
		this.villageDAO = villageDAO;

	}
	
	

	
	@POST
	@Path("/MARS/Villagedetails")
	@Consumes({"application/json","application/xml"})
	@Produces({"application/json"})
	public Response getState2(String jacksonVillageDetails) {
		logger.info("Entering " + CLASS_NAME);
      
		Village  village=new Village();
        village.setVillages(getVillageDAO().VillageDetails(jacksonVillageDetails));
		//State state = (State)getStateDAO().getState();
		    if (village == null) {
			   ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			   builder.type("application/json");
			   builder.entity("<error>State Not Found</error>");
			   throw new WebApplicationException(builder.build());
		    } 
		    else {

	return	Response.ok(village).build(); 
		}

	}

}

	
	
	


