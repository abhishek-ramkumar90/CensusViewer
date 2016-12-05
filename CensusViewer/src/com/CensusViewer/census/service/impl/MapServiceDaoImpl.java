/**
 * 
 */
package com.CensusViewer.census.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.CensusViewer.census.model.MapDistricts;
import com.CensusViewer.census.model.MapTehsil;
import com.CensusViewer.census.model.MapTowns;
import com.CensusViewer.census.model.MapVillage;
import com.CensusViewer.census.service.dao.MapServiceDao;



/**
 * @author bhupendras
 *
 */

@Path("/MapService")
@Produces({"application/json","application/xml"})
public class MapServiceDaoImpl {
	
	String CLASS_NAME = this.getClass().getName();


	private MapServiceDao mapDao = new MapServiceDao();


	public MapServiceDao getMapDAO() {

		return mapDao;
	}

	
	
	
	@GET
	@Path("/District/{jsonString}")
	@Consumes("application/xml,application/json")
	@Produces({"application/json"})
	public Response getMapinputInfo4dist(@PathParam("jsonString") String jsonString)
	{
		System.out.println("in side District");
			//System.out.println("tempLat=="+tempLat+"::tempLon=="+tempLon+"::temlayer=="+temlayer);
		
		MapDistricts mapdis=getMapDAO().getDistMapInfo(jsonString);
		
		if (mapdis == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>District Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
		return Response.ok(mapdis).build();
		}
	}

	

	@GET
	@Path("/Tehsil/{jsonString}")
	@Consumes("application/xml,application/json")
	@Produces({"application/json"})
	public Response getMapinputInfo4tehsil(@PathParam("jsonString") String jsonString)
	{
		System.out.println("in side Tehsil");
			//System.out.println("tempLat=="+tempLat+"::tempLon=="+tempLon+"::temlayer=="+temlayer);
		
		MapTehsil mapdis=getMapDAO().gettehsilMapInfo(jsonString);
		
		if (mapdis == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Tehsil Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
		return Response.ok(mapdis).build();
		}
	}

	@GET
	@Path("/Towns/{jsonString}")
	@Consumes("application/xml,application/json")
	@Produces({"application/json"})
	public Response getMapinputInfo4towns(@PathParam("jsonString") String jsonString)
	{
		System.out.println("in side Towns");
			//System.out.println("tempLat=="+tempLat+"::tempLon=="+tempLon+"::temlayer=="+temlayer);
		
		MapTowns mapdis=getMapDAO().gettownsMapInfo(jsonString);
		
		if (mapdis == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Towns Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
		return Response.ok(mapdis).build();
		}
	}


	
	@GET
	@Path("/Village/{jsonString}")
	@Consumes("application/xml,application/json")
	@Produces({"application/json"})
	public Response getMapinputInfo4village(@PathParam("jsonString") String jsonString)
	{
		System.out.println("in side Village");
			//System.out.println("tempLat=="+tempLat+"::tempLon=="+tempLon+"::temlayer=="+temlayer);
		
		MapVillage mapdis=getMapDAO().getvillageMapInfo(jsonString);
		
		if (mapdis == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Village Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
		return Response.ok(mapdis).build();
		}
	}
}
