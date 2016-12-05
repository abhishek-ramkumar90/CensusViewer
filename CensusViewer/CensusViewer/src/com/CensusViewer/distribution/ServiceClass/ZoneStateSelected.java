package com.mars.distribution.ServiceClass;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mars.distribution.daoimpl.SelectedStateZoneDao;
import com.mars.distribution.ServicePojo.ZoneStateSelectedPojo;

@Path("/ZoneStateCreationService")
@Produces({"application/json","application/xml"})
public class ZoneStateSelected {
	private SelectedStateZoneDao selectedStates=new SelectedStateZoneDao();

	public SelectedStateZoneDao getSelectedStates() {
		return selectedStates;
	}

	public void setSelectedStates(SelectedStateZoneDao selectedStates) {
		this.selectedStates = selectedStates;
	}

	@GET
	@Path("/ZoneStateList/{ZoneId}")
	@Produces({"application/json"})
	public ZoneStateSelectedPojo getZoneStates(@PathParam("ZoneId") String ZoneId)
	{
		ZoneStateSelectedPojo zp=new ZoneStateSelectedPojo();
	    zp.setZoneStateList(getSelectedStates().getStateList(ZoneId));
		return zp;
	}
	
	
	
}
