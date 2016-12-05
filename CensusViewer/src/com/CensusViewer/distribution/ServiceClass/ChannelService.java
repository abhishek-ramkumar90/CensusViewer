package com.CensusViewer.distribution.ServiceClass;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.CensusViewer.distrbution.service.bl.ChannelBL;
import com.CensusViewer.distribution.ServicePojo.ChannelJson;
import com.CensusViewer.distribution.ServicePojo.ChannelStructureJson;
import com.CensusViewer.distribution.ServicePojo.UpdateStructureJson;
@Path("/ChannelService")
@Produces({"application/json","application/xml"})
public class ChannelService {
	
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
    private ChannelBL channelBl = new ChannelBL();
    
    
	public ChannelBL getChannelBl() {
		return channelBl;
	}
	public void setChannelBl(ChannelBL channelBl) {
		this.channelBl = channelBl;
	}
	
	
	@GET
	@Path("/channel/list")
	@Produces({"application/json"})
	
	public ChannelJson getChannelList() {
		try {
			ChannelJson channelJson = new ChannelJson();
			channelJson.setChannelJsonCollection(getChannelBl().getChannels());
			if (channelJson == null) {
				ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
				builder.type("application/json");
				builder.entity("<error>No channels Found</error>");
				throw new WebApplicationException(builder.build());
			} else {
				return channelJson;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}
		return null;
	}
	
	
	@POST
	@Path("/channel/struct")
	@Produces({"application/json"})
	
	public ChannelStructureJson getChannelStruct(String channelDetails) {
	
		try {
			ChannelStructureJson channelJson = getChannelBl().getChannelStructure(channelDetails);
		
			if (channelJson == null) {
				ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
				builder.type("application/json");
				builder.entity("<error>No channels Found</error>");
				throw new WebApplicationException(builder.build());
			} else {
				return channelJson;
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}
		return null;
	}
	
	
	@POST
	@Path("/saveChannel")
	@Produces({"application/json"})
	
	public ChannelStructureJson saveChannelDetails(String channelIntrDetails){
		return getChannelBl().saveChannel(channelIntrDetails);
	}
	
	
	
	@POST
	@Path("/deleteChannel")
	@Produces({"application/json"})
	
	public Response deleteChannel(String channelId){
		getChannelBl().deleteChannelStructure(channelId);
		return null;
	}
	

	
	
	@POST
	@Path("/updateChannel")
	
	public void updateChannel(String updatedChannelDetails)
	{
		getChannelBl().updateChannel(updatedChannelDetails);		
		
	}
	
	
	@POST
	@Path("/checkChannelName")
	@Produces({"application/json"})
	
	public boolean checkChannelName(String channelName){
		
		return getChannelBl().chkChannelName(channelName);
	}
	
	
	@POST
	@Path("/getAllChannel")
	@Produces({"application/json"})
	
	public ChannelStructureJson getALLChannelStruct(String channelDetails) {
		try {
			ChannelStructureJson channelJson = getChannelBl().getALLChannelStructure();
			if (channelJson == null) {
				ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
				builder.type("application/json");
				builder.entity("<error>No channels Found</error>");
				throw new WebApplicationException(builder.build());
			} else {
				return channelJson;
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}
		return null;
	}
}
