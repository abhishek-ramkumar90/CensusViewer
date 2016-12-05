package com.CensusViewer.distribution.ServiceClass;
import java.util.Collection;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.json.JSONException;

import com.CensusViewer.distribution.ServicePojo.RegionCreationPojo;
import com.CensusViewer.distribution.ServicePojo.RegionDistIds;
import com.CensusViewer.distribution.ServicePojo.RegionFormGridList;
import com.CensusViewer.distribution.ServicePojo.RegionLogicMap;
import com.CensusViewer.distribution.ServicePojo.RegionShift;
import com.CensusViewer.distribution.daoimpl.RegionCreationDao;
@Path("/RegionCreationService")
@Produces({"application/json","application/xml"})
public class RegionCreation {
	
	private RegionCreationDao rdao=new RegionCreationDao();
	public RegionCreationDao getRdao() {
		return rdao;
	}
	public void setRdao(RegionCreationDao rdao) {
		this.rdao = rdao;
	}
	@POST
	@Path("/CreateRegion")
	@Produces("application/json")
	public RegionCreationPojo setRegionName(String JsonString) throws JSONException
	{
		RegionCreationPojo region=new RegionCreationPojo();
		try {
			region.setRegionPojo(getRdao().setRegionNames(JsonString));
		} catch (org.json.JSONException e) {
			e.printStackTrace();
		}
		if(region==null)
		{
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<Sucess>State Not Found</Sucess>");
			throw new WebApplicationException(builder.build());
		}
		return region;
	}

	@GET
	@Path("/RegionGridList")
	@Produces("application/json")
	public RegionFormGridList getRegionGridList()
	{
		System.out.println("hi");
		RegionFormGridList regList=new RegionFormGridList();
		regList.setRegions(getRdao().getRegionList());
		return regList;
	}
	
	@POST
	@Path("/LogicMap")
	public RegionLogicMap getLogicMap(String regionId)
	{ 
		RegionLogicMap rmap=new RegionLogicMap();
		rmap.setDistIdCollection(getRdao().getLogicMapIds(regionId));
		return rmap;
	}
	
	
	
	
	
	@POST
	@Path("/updateRegion")
	public Response updateBranchDetails(String regionDetails){
		getRdao().updateRegion(regionDetails);
		return null;
	}
	
	@POST
	@Path("/deleteRegion")
	public Response deleteBranchDetails(String regionDetails){
		getRdao().deleteRegion(regionDetails);
		return null;
	}
	
	@POST
	@Path("/RegionCheckShift")
	public RegionShift checkRegion(String distid)
	{
		RegionShift regionShift=new RegionShift();
		regionShift.setRegionCollection(getRdao().CheckRegionShift(distid));
		return regionShift;
	}

	@POST
	@Path("/distList")
	public RegionDistIds distIds(String regionId)
	{
		RegionDistIds rids=new RegionDistIds();
	rids.setDistids(getRdao().getDistIds(regionId));
		return rids;
	}
}
