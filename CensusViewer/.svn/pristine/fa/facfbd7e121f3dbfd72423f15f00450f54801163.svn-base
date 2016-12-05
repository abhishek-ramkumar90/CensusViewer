package com.mars.census.service.impl;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.mars.census.impl.SolrplaceDetails;
import com.mars.census.model.SolrSearch;


@Path("/SolrPlaceDetailService")
@Produces({"application/json","application/xml"})
public class SolrSearchRestService {
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
	private SolrplaceDetails placeDAO = new SolrplaceDetails ();
	

	
	public SolrplaceDetails getPlaceDAO() {
		return placeDAO;
	}







	public void setPlaceDAO(SolrplaceDetails placeDAO) {
		this.placeDAO = placeDAO;
	}






	@POST
	@Path("/MARS/village/SolrSearchDetails")
	@Produces({"application/json"})
	public SolrSearch getPlaceDetails(String villagecode ) {
		SolrSearch search=(getPlaceDAO().searchDetails(villagecode));
		if (search == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>State Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return search;
		}
	}
	
	@POST
	@Path("/MARS/towns/SolrSearchDetails")
	@Produces({"application/json"})
	public SolrSearch getTownPlaceDetails(String towncode) {
		SolrSearch search=(getPlaceDAO().townSearchDetails(towncode));
		if (search == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Towns Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {

			return search;
		}

	}
	@POST
	@Path("/MARS/districts/SolrSearchDetails")
	@Produces({"application/json"})
	public SolrSearch getDistrictPlaceDetails(String distcode) {
		SolrSearch search=(getPlaceDAO().districtSearchDetails(distcode));
		if (search == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Towns Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return search;
		}
	}
	
	@POST
	@Path("/MARS/states/SolrSearchDetails")
	@Produces({"application/json"})
	public SolrSearch getStatePlaceDetails(String statecode) {
		SolrSearch search=(getPlaceDAO().stateSearchDetails(statecode));
		if (search == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error> Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return search;
		}

	}

}
