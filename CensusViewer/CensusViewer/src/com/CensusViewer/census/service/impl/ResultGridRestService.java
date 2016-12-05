package com.mars.census.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.mars.census.impl.StateSubcategoryResult;
import com.mars.census.model.Result;

@Path("/ResultService")
@Produces({"application/json","application/xml"})
public class ResultGridRestService {

	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
	private StateSubcategoryResult gridDAO = new StateSubcategoryResult();

 


	public StateSubcategoryResult getGridDAO() {
		return gridDAO;
	}
	public void setGridDAO(StateSubcategoryResult gridDAO) {
		this.gridDAO = gridDAO;
	}

// get districts 
	@POST
	@Path("/state/criteria/result")
	@Consumes({"application/json","application/xml"})
	@Produces ({"application/json"})
	public Result getCriteriaDetailsByState(String CriteriaByState ) {
		//String CriteriaByState="{'jacksondetails':  [ { 'stcode': '27', 'columns': 'tot_m,tot_f', 'criteria':'>,<' , 'value':'10000,20000' }]}";
		logger.info("Entering " + CLASS_NAME);
		logger.info("recieved data district"+CriteriaByState);
		Result result=(getGridDAO().stateCriteriaDetails(CriteriaByState));
		return result;
	}
 
	// get towns 
	@POST
	@Path("/state/dist/town/criteria/result")
	@Consumes({"application/json","application/xml"})
	@Produces ({"application/json"})
	public Result getTownCriteriaDetailsByDist(String CriteriaByDist ) {
	//	String CriteriaByDist="{'jacksondetails':  [ { 'distCode': '02:27,03:27,05:28', 'columns': 'tot_m,tot_f', 'criteria':'>,<' , 'value':'10000,20000' }]}";
		logger.info("Entering " + CLASS_NAME);
		logger.info("recieved data district"+CriteriaByDist);
		Result result=(getGridDAO().distCriteriaDetails(CriteriaByDist));
		return result;
	}
	
	
//get villages
	@POST
	@Path("/district/village/criteria/result")
	@Consumes({"application/json","application/xml"})
	@Produces ({"application/json"})
	public Result getCriteriaDetailsByDistrict(String CriteriaByDistrict) {
	//	String CriteriaByDistrict="{'jacksondetails':  [ { 'stateDistrict': '02:27,03:27,05:28', 'columns': 'tot_m,tot_f', 'criteria':'>,<' , 'value':'10000,20000' }]}";
		logger.info("Entering " + CLASS_NAME);
		logger.info("recieved data town"+CriteriaByDistrict);
		Result result=(getGridDAO().districtCriteriaDetails(CriteriaByDistrict));
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
