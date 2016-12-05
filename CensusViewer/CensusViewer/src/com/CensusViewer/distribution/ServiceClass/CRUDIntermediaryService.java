package com.mars.distribution.ServiceClass;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.mars.distribution.daoimpl.CRUDIntermediary;
import com.mars.distribution.model.IntermediaryFreeFlow;


@Path("/CRUDIntermediaryService")
@Produces({"application/json","application/xml"})
public class CRUDIntermediaryService {
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
	
	private CRUDIntermediary crud=new CRUDIntermediary();

	public CRUDIntermediary getCrud() {
		return crud;
	}

	public void setCrud(CRUDIntermediary crud) {
		this.crud = crud;
	}

	@POST
	@Path("/getIntermediaryDetails")
	@Produces({"application/json"})
	public IntermediaryFreeFlow getIntermList(String InterDetails) {
		IntermediaryFreeFlow inter=getCrud().getIntermediary(InterDetails);
		if (inter == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Regions Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return inter;
		}
	

}
	
	
}
