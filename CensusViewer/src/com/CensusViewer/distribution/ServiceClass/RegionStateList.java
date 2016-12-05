package com.CensusViewer.distribution.ServiceClass;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.CensusViewer.distribution.ServicePojo.RegionStateListJson;
import com.CensusViewer.distribution.daoimpl.RegionStateListDao;

@Path("/RegionStateService")
@Produces({"application/json","application/xml"})
public class RegionStateList {

	private RegionStateListDao stateList=new RegionStateListDao();

	public RegionStateListDao getStateList() {
		return stateList;
	}
	public void setStateList(RegionStateListDao stateList) {
		this.stateList = stateList;
	}

	@GET
	@Path("/RegionStateList/{ZoneId}")
	@Produces({"application/json"})	
	public RegionStateListJson  getRegionStateList(@PathParam("ZoneId")  String zoneId)
	{		
		RegionStateListJson rs=new RegionStateListJson();
		rs.setStateDetails(getStateList().getRegionStateList(zoneId));	
		return rs;
	}
}
