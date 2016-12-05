package com.CensusViewer.distribution.ServiceClass;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.CensusViewer.distribution.ServicePojo.BranchJson;
import com.CensusViewer.distribution.ServicePojo.RegDistJson;
import com.CensusViewer.distribution.ServicePojo.RegionListBranch;
import com.CensusViewer.distribution.ServicePojo.RegionListJson;
import com.CensusViewer.distribution.daoimpl.BranchServiceDao;
/**
 * @author parnita
 *
 */


@Path("/BranchService")
@Produces({"application/json","application/xml"})

public class BranchServices {
	
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
	private BranchServiceDao branchServ = new BranchServiceDao();
	
	
	public BranchServiceDao getBranchServ() {
		return branchServ;
	}
	
	public void setBranchServ(BranchServiceDao branchServ) {
		this.branchServ = branchServ;
	}

	@POST
	@Path("/regions/list")
	@Produces({"application/json"})
	public RegionListBranch getRegionsList(String zoneDetails) {
		System.out.println("zoneId=="+zoneDetails);
		
		// System.out.println("zoneId=="+zoneId);
		logger.info("Entering " + CLASS_NAME);
		RegionListBranch regions=new RegionListBranch();
		regions.setRegionlist(getBranchServ().getRegions(zoneDetails));
	
		if (regions == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Regions Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return regions;
		}
	}
	@POST
	@Path("/districtlist")
	@Produces({"application/json"})
	public RegDistJson getRegDist(String regDetails){
		System.out.println("regId=="+regDetails);
		RegDistJson dist=new RegDistJson();
		dist.setDists(getBranchServ().getRegDistrict(regDetails));
		if (dist == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Region District Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return dist;
		}
	}
	
	
	@POST
	@Path("/districtlistupdate")
	@Produces({"application/json"})
	public RegDistJson getRegDistUpdate(String regDetails){
		System.out.println("regId=="+regDetails);
		RegDistJson dist=new RegDistJson();
		dist.setDists(getBranchServ().getRegDistrictUpdate(regDetails));
		if (dist == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Region District Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return dist;
		}
	}
	
	@POST
	@Path("/saveBranch")
	public Response saveBranchDetails(String branchDetails){
		getBranchServ().saveBranch(branchDetails);
		return null;
	}
	
	@POST
	@Path("/updateBranch")
	public Response updateBranchDetails(String branchDetails){
		getBranchServ().updateBranch(branchDetails);
		return null;
	}
	
	@POST
	@Path("/deleteBranch")
	public Response deleteBranchs(String branchDetails){
		getBranchServ().deleteBranch(branchDetails);
		return null;
	}
	
	@GET
	@Path("/branchDetails")
	@Produces({"application/json"})
	public BranchJson getBranchDetails(){
		 System.out.println("save branch json");
		 BranchJson brJson=new BranchJson();
		 brJson.setBranchJsonList((getBranchServ().getBranchDetails()));
		if (brJson == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Region District Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return brJson;
		}
	}


	@POST
	@Path("/validateBranchName")
	public boolean validateBranchName(String branchDetails){
		return getBranchServ().vildateBranchName(branchDetails);
	}
	
}

