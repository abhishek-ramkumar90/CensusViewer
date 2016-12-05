package com.mars.census.services.customerservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.mars.census.datasource.MARSQuery;
import com.mars.census.datasource.MARSQueryInteractor;
import com.mars.census.servicedef.StateDefinition;

public class StateService implements StateDefinition{

	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
    
    MARSQueryInteractor marsQueryInteraction = null;
    
	public List getStateList() {
		boolean flag = false;
		List list = new ArrayList();
		logger.info("Entering " + CLASS_NAME);
		
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getList(MARSQuery.STATE_LIST);
			
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
		return list;
	}
	
	
	
	public static void main(String args[]) {
		StateService ss = new StateService();
	//	ss.getStateList();
		HashMap stateCodes = new HashMap();
		stateCodes.put(new Integer(1), "27");
		stateCodes.put(new Integer(2), "28");
		
		//ss.getVillageList(stateCodes);
	}
}
