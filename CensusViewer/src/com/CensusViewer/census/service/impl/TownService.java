package com.CensusViewer.census.service.impl;


//JAX-RS Imports
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.CensusViewer.census.model.TownWb;


@Path("/townservice")
@Produces({"application/json","application/xml"})
public class TownService {
	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);
	private TownServiceDaoImp townDaoImp=new TownServiceDaoImp();

	
	public TownServiceDaoImp getTownDaoImp() {
		return townDaoImp;
	}


	public void setTownDaoImp(TownServiceDaoImp townDaoImp) {
		this.townDaoImp = townDaoImp;
	}


	//get the list of towns as per selected state,ditrict,tehsil
	@GET
	@Path("/country/state/district/tehsil/townlist")
	@Produces({"application/json","application/json"})
	public Response getTownList() {
		logger.debug("townlist service called");
		TownWb townW=new TownWb();
		System.out.println("service called");
		List<TownWb> towns=getTownDaoImp().getTownList();

		if (towns== null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			builder.entity("<error>Category Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			townW.setTown(towns);
			return Response.ok(townW).build();
		}
	}//end of getTownList

}

