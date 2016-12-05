package com.CensusViewer.census.services.customerservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.CensusViewer.census.datasource.MARSQuery;
import com.CensusViewer.census.datasource.MARSQueryInteractor;
import com.CensusViewer.census.servicedef.DistrictDefinition;

public class DistrictService implements DistrictDefinition {

	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
    
    MARSQueryInteractor marsQueryInteraction = null;
	
	public List getDistrictList(HashMap stateCodes) {

		boolean flag = false;
		List list = new ArrayList();
		logger.info("Entering " + CLASS_NAME);
			
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayListOnIn(MARSQuery.DISTRICT_LIST,stateCodes);
			
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
		return list;
	}
	
	public List getVillageList(HashMap stateDistrictCodes) {

		boolean flag = false;
		List list = new ArrayList();
		logger.info("Entering " + CLASS_NAME);
			
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayListOnIn(MARSQuery.DISTRICT_LIST,stateDistrictCodes);
			
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
		return list;
	}

	/*public static void main(String[] args) {
		DistrictService ds = new DistrictService();
		HashMap stateCodes1 = new HashMap();
		
		stateCodes1.put(new Integer(1), "27");
		stateCodes1.put(new Integer(2), "28");
		
		ds.getDistrictList(stateCodes1);
		
	}

	*/

}
