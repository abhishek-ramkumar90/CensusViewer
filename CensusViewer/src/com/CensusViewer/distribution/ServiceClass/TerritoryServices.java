package com.CensusViewer.distribution.ServiceClass;

import java.util.ArrayList;
import java.util.Collection;
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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.CensusViewer.HibernateUtility.HibernateUtil;
import com.CensusViewer.distribution.ServicePojo.BranchJson;
import com.CensusViewer.distribution.ServicePojo.BranchListTerrJson;
import com.CensusViewer.distribution.ServicePojo.RegionListBranch;
import com.CensusViewer.distribution.ServicePojo.TerrVillTownJson;
import com.CensusViewer.distribution.ServicePojo.TerritoryJson;
import com.CensusViewer.distribution.ServicePojo.TerritoryService;
import com.CensusViewer.distribution.ServicePojo.TownListJson;
import com.CensusViewer.distribution.ServicePojo.VillageListJson;
import com.CensusViewer.distribution.daoimpl.BranchServiceDao;
import com.CensusViewer.distribution.daoimpl.TerritoryServiceDao;
import com.CensusViewer.distribution.model.Branch;
import com.CensusViewer.distribution.model.Region;
import com.CensusViewer.distribution.model.Zone;

@Path("/TerritoryService")
@Produces({"application/json","application/xml"})

public class TerritoryServices {
	
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
	private TerritoryServiceDao territoryServ = new TerritoryServiceDao();
	
	public TerritoryServiceDao getTerritoryServ() {
		return territoryServ;
	}
	public void setTerritoryServ(TerritoryServiceDao territoryServ) {
		this.territoryServ = territoryServ;
	}
	
	@POST
	@Path("/branch/list")
	@Produces({"application/json"})
	public BranchListTerrJson getBranchList(String regionDetails) {
		System.out.println("region id=="+regionDetails);
		
		// System.out.println("zoneId=="+zoneId);
		logger.info("Entering " + CLASS_NAME);
		BranchListTerrJson branchJson = new BranchListTerrJson();
		branchJson.setBranchlist(getTerritoryServ().getBranches(regionDetails));
	
		if (branchJson == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Regions Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return branchJson;
		}
	}
	@POST
	@Path("/villageList")
	@Produces({"application/json"})
	public VillageListJson getVillageList(String regionDetails) {
		System.out.println("region id=="+regionDetails);
		
		// System.out.println("zoneId=="+zoneId);
		logger.info("Entering " + CLASS_NAME);
		VillageListJson villageJson = new VillageListJson();
		villageJson.setVillageList(getTerritoryServ().getVillageList(regionDetails));
	
		if (villageJson == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Regions Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return villageJson;
		}
	}
	
	
	@POST
	@Path("/villageListUpdate")
	@Produces({"application/json"})
	public VillageListJson getVillageListUpdate(String regionDetails) {
		System.out.println("region id=="+regionDetails);
		
		// System.out.println("zoneId=="+zoneId);
		logger.info("Entering " + CLASS_NAME);
		VillageListJson villageJson = new VillageListJson();
		villageJson.setVillageList(getTerritoryServ().getVillageListUpdate(regionDetails));
	
		if (villageJson == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Regions Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return villageJson;
		}
	}
	
	@POST
	@Path("/townList")
	@Produces({"application/json"})
	public TownListJson getTownList(String regionDetails) {
		System.out.println("region id=="+regionDetails);
		
		// System.out.println("zoneId=="+zoneId);
		logger.info("Entering " + CLASS_NAME);
		TownListJson townJson = new TownListJson();
		townJson.setTownList(getTerritoryServ().getTownList(regionDetails));
	
		if (townJson == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Regions Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return townJson;
		}
	}
	
	
	@POST
	@Path("/townListUpdate")
	@Produces({"application/json"})
	public TownListJson getTownListUpdate(String regionDetails) {
		System.out.println("region id=="+regionDetails);
		
		// System.out.println("zoneId=="+zoneId);
		logger.info("Entering " + CLASS_NAME);
		TownListJson townJson = new TownListJson();
		townJson.setTownList(getTerritoryServ().getTownListUpdate(regionDetails));
	
		if (townJson == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Regions Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return townJson;
		}
	}
	
	
	@POST
	@Path("/saveTerritory")
	public Response saveBranchDetails(String territoryDetails){
		getTerritoryServ().saveTerritory(territoryDetails);
		return null;
	}
	
	@POST
	@Path("/updateTerritory")
	public Response updateTerritoryDetails(String territoryDetails){
		getTerritoryServ().updateTerritory(territoryDetails);
		return null;
	}
	
	@POST
	@Path("/deleteTerritory")
	public Response deleteTerritoryDetails(String territoryDetails){
		getTerritoryServ().deleteTerritory(territoryDetails);
		return null;
	}
	
	@GET
	@Path("/territoryDetails")
	@Produces({"application/json"})
	public TerritoryJson getALLTerritoryDetails(){
		 System.out.println("getALLTerritoryDetails");
		 TerritoryJson terrJson=new TerritoryJson();
		 terrJson.setTerritoryJsonList((getTerritoryServ().getTerritoryDetails()));
		if (terrJson == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Region District Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return terrJson;
		}
	}
	
	@POST
	@Path("/validateTerritoryName")
	public boolean validateTerritoryName(String territoryDetails){
		return getTerritoryServ().vildateTerritoryName(territoryDetails);
		
	}
	@POST
	@Path("/branch/terrlist")
	public TerritoryService getTerrByBranch(String BranchId){
		TerritoryService terrJson=new TerritoryService();
		terrJson.setTerrList(getTerritoryServ().getTerritoryByBranch(BranchId));
		if (terrJson == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Region District Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return terrJson;
		}			
	}
	@POST
	@Path("/territory/villtown")
	public TerrVillTownJson getVillTownByTerr(String terrId){
		TerrVillTownJson terrVillTownJson=new TerrVillTownJson();
		terrVillTownJson.setTerVillTown(getTerritoryServ().getVillTownByTerritory(terrId));
		if (terrVillTownJson == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>Region District Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return terrVillTownJson;
		}			
	}
	
}
