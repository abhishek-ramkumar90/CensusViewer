package com.CensusViewer.distribution.ServiceClass;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.CensusViewer.distribution.ServicePojo.ZoneEditList;
import com.CensusViewer.distribution.ServicePojo.ZoneShift;
import com.CensusViewer.distribution.daoimpl.ZoneCreationDao;

@Path("/ZoneCreationService")
@Produces({"application/json","application/xml"})
public class ZoneCreation {
	
	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);
	
	private ZoneCreationDao zcd= new ZoneCreationDao();
	
	public ZoneCreationDao getZcd() {
		return zcd;
	}
	
	public void setZcd(ZoneCreationDao zcd) {
		this.zcd = zcd;
	}
	@GET
	@Path("/{JsonString}")
	@Produces("application/json")
	public void zoneCreation(@PathParam("JsonString") String JsonString) throws JSONException
	{

		logger.info("Entering " + CLASS_NAME);
		logger.debug(JsonString);
		
		try {
			getZcd().setZoneNames(JsonString);
		
		} catch (org.json.JSONException e) {
		
			e.printStackTrace();
			logger.fatal(e.getMessage());
		
		}
	}

	
	@GET
	@Path("/ZoneUpdate/{zoneUpdate}")
	@Produces("application/json")
	public ZoneEditList ZoneUpdate(@PathParam("zoneUpdate") String JsonString) 
	{
		ZoneEditList zone=new ZoneEditList();
		Collection<ZoneEditList> zoneList=new ArrayList<ZoneEditList>();
		logger.info("Entering " + CLASS_NAME);
		logger.debug(JsonString);
		getZcd().updateZone(JsonString);
		zone.setZoneList(zoneList);
		return zone;
	}
	
	@POST
	@Path("/deleteZone")
	public Response deleteZones(String zoneDetails){
		getZcd().deleteZone(zoneDetails);
		return null;
	}
	
	@POST
	@Path("/ZoneShift")
	@Produces("application/json")
	public ZoneShift CheckAndShiftZone(String stateId) throws JSONException
	{
		ZoneShift zf=new ZoneShift();
        zf.setShiftCollection(getZcd().checkAndShiftZone(stateId));
		return zf;
	}
	
}
