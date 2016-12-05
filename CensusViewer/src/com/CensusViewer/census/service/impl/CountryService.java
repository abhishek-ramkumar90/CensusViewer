package com.CensusViewer.census.service.impl;


//JAX-RS Imports

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

import com.CensusViewer.census.model.CountryWb;



@Path("/countryservice")
@Produces({"application/json","application/xml"})
public class CountryService {
	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);
	private CountryServiceDaoImp countryDaoImp=new CountryServiceDaoImp();

	
	


	public CountryServiceDaoImp getCountryDaoImp() {
		return countryDaoImp;
	}





	public void setCountryDaoImp(CountryServiceDaoImp countryDaoImp) {
		this.countryDaoImp = countryDaoImp;
	}





	//get the list of countries
	@GET
	@Path("/countrylist")
	@Produces({"application/json","application/json"})
	public Response getTownList() {
		logger.debug("townlist service called");
		CountryWb countryW=new CountryWb();
		System.out.println("service called");
		List<CountryWb> country=getCountryDaoImp().getCountryList();

		if (country== null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			builder.entity("<error>Category Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			countryW.setCountry(country);
			return Response.ok(countryW).build();
		}
	}//end of getTownList

}

