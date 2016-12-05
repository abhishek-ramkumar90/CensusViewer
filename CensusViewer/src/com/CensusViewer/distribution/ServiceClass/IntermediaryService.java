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
import com.CensusViewer.distrbution.service.bl.IntermediaryBL;
import com.CensusViewer.distribution.ServicePojo.ChannelJson;
import com.CensusViewer.distribution.ServicePojo.IntermTypes;
import com.CensusViewer.distribution.ServicePojo.IntermediarySerPojo;

@Path("/IntermService")
@Produces({"application/json","application/xml"})
public class IntermediaryService {

	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);

	private IntermediaryBL intermediaryBl = new IntermediaryBL();

	public IntermediaryBL getIntermediaryBl() {
		return intermediaryBl;
	}

	public void setIntermediaryBl(IntermediaryBL intermediaryBl) {
		this.intermediaryBl = intermediaryBl;
	}

	@GET
	@Path("/interm/types")
	@Produces({"application/json"})
	public IntermTypes getIntermList() {

		try {

			IntermTypes intermTypes  = getIntermediaryBl().getIntermediaryTypes();

			if (intermTypes == null) {
				ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
				builder.type("application/json");
				builder.entity("<error>No channels Found</error>");
				throw new WebApplicationException(builder.build());
			} else {
				return intermTypes;
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}
		return null;
	}

	@POST
	@Path("/saveIntermediary")
	@Produces({"application/json"})
	public boolean saveIntermediary(String intmDetails) {
		System.out.println("intmDetails=="+intmDetails);

		try {
			return getIntermediaryBl().saveIntermediaryDetails(intmDetails);
		} catch(Exception e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}
		return false;
	}

	@POST
	@Path("/getIntermediaryName")
	@Produces({"application/json"})
	public IntermediarySerPojo getIntermediaryByType(String intmType) {
		System.out.println("intmDetails=="+intmType);

		try {
			IntermediarySerPojo intmServ = new IntermediarySerPojo();
			intmServ.setIntmList(getIntermediaryBl().getIntmByType(intmType));

			if (intmServ == null) {
				ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
				builder.type("application/json");
				builder.entity("<error>No channels Found</error>");
				throw new WebApplicationException(builder.build());
			} else {
				return intmServ;
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}
		return null;	
	}
	
	@POST
	@Path("/getIntermediaryChild")
	@Produces({"application/json"})
	public IntermediarySerPojo getIntermediaryChild(String intmId) {
		System.out.println("intmDetails=="+intmId);

		try {
			IntermediarySerPojo intmServ = new IntermediarySerPojo();
			intmServ.setIntmList(getIntermediaryBl().getIntmChild(intmId));

			if (intmServ == null) {
				ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
				builder.type("application/json");
				builder.entity("<error>No channels Found</error>");
				throw new WebApplicationException(builder.build());
			} else {
				return intmServ;
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}
		return null;	
	}	
	
	
	
}