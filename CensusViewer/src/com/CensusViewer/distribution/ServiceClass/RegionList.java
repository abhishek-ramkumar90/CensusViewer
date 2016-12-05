package com.CensusViewer.distribution.ServiceClass;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;






import org.json.JSONException;

import com.CensusViewer.distribution.ServicePojo.RegionListJson;
import com.CensusViewer.distribution.daoimpl.RegionListDao;

@Path("/RegionListService")
@Produces({"application/json","application/xml"})
public class RegionList {
	private RegionListDao regionListDao=new RegionListDao();


	public RegionListDao getRegionListDao() {
		return regionListDao;
	}


	public void setRegionListDao(RegionListDao regionListDao) {
		this.regionListDao = regionListDao;
	}


	@GET
	@Path("/{JsonString}")
	@Produces({"application/json"})
	public RegionListJson getRegionList(@PathParam("JsonString") String JsonString) throws JSONException {
		RegionListJson region=new RegionListJson();
	region.setRegionList(getRegionListDao().getRegionList(JsonString));
	if(region==null)
	{
	 ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
     builder.type("application/json");
     builder.entity("<Sucess>State Not Found</Sucess>");
     throw new WebApplicationException(builder.build());
	}
	return region;

}
}
