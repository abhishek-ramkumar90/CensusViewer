package com.CensusViewer.census.service.impl;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.CensusViewer.census.impl.StateDetails;
import com.CensusViewer.census.model.State;
@Path("/StateDetailService")
@Produces({"application/json","application/xml"})
public class StateRestService {
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
	private StateDetails stateDetails = new StateDetails();
	public StateDetails getStateDetails() {
		return stateDetails;
	}
	public void setStateDetails(StateDetails stateDetails) {
		this.stateDetails = stateDetails;
	}
	@GET
	@Path("/MARS/stateDetails")
	@Produces({"application/json"})
	public State getState2() {
		logger.info("Entering " + CLASS_NAME);
		State state=new State();
		state.setStates(getStateDetails().stateDetails());
		if (state == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>State Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return state;
		}
	}
}
