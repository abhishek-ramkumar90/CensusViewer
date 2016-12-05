package com.mars.distribution.ServiceClass;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;




import org.json.JSONException;

import com.mars.distribution.daoimpl.ZoneListDao;
import com.mars.distribution.ServicePojo.ZoneListJson;

@Path("/ZoneListService")
@Produces({"application/json","application/xml"})
public class ZoneList {
	private ZoneListDao zoneListDao=new ZoneListDao();
	public ZoneListDao getZoneListDao() {
		return zoneListDao;
	}
	public void setZoneListDao(ZoneListDao zoneListDao) {
		this.zoneListDao = zoneListDao;
	}
	@GET
	@Path("/ZoneList")
	@Produces({"application/json"})
	public ZoneListJson getWKTList(@PathParam("JsonString") String LatLongJsonString) throws JSONException {
		ZoneListJson zoneList=new ZoneListJson();
		zoneList.setZoneList(getZoneListDao().getZoneList());   
	    if (zoneList == null) {
	       ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
	       builder.type("application/json");
	       builder.entity("<error>State Not Found</error>");
	       throw new WebApplicationException(builder.build());
	            } else {
	            return zoneList;
			}
		}
	}
	
	
	
	
	
	

