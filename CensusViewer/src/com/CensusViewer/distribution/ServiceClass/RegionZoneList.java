package com.CensusViewer.distribution.ServiceClass;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.CensusViewer.distribution.ServicePojo.ZoneRegionListJson;
import com.CensusViewer.distribution.daoimpl.RegionZoneListDao;
@Path("/ZoneRegionService")
@Produces({"application/json","application/xml"})
public class RegionZoneList {

	private RegionZoneListDao rzdao=new RegionZoneListDao();

	public RegionZoneListDao getRzdao() {
		return rzdao;
	}

	public void setRzdao(RegionZoneListDao rzdao) {
		this.rzdao = rzdao;
	}
	
	@GET
	@Path("/ZoneList")
	@Produces({"application/json"})
public ZoneRegionListJson getZoneList()
{
	ZoneRegionListJson znList=new ZoneRegionListJson();
	znList.setZoneRegionList(getRzdao().getZones());
	return znList;
}









}
