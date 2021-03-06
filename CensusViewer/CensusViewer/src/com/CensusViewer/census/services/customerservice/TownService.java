package com.mars.census.services.customerservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mars.census.datasource.MARSQuery;
import com.mars.census.datasource.MARSQueryInteractor;
import com.mars.census.model.Town;
import com.mars.census.model.Village;
import com.mars.census.servicedef.TownDefinition;

public class TownService implements TownDefinition {

	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
    
    MARSQueryInteractor marsQueryInteraction = null;
    
	public List getTownList(HashMap stateCodes) {
		
		boolean flag = false;
		List list = new ArrayList();
		logger.info("Entering " + CLASS_NAME);
			
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayListOnIn(MARSQuery.STATE_TOWN_LIST,stateCodes);
			
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
		return list;
	}

	public List getStateDistrictTownList(Multimap<String, String> multiMap) {

		boolean flag = false;
		List list = new ArrayList();
		logger.info("Entering " + CLASS_NAME);
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getStateArrayList(MARSQuery.STATE_DISTRICT_TOWN_LIST,multiMap);
			
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
		return list;
	}
	

	public List getTownListOnStateDistrict(Multimap<String, String> multiMap) {
		
		boolean flag = false;
		List list = new ArrayList();
		logger.info("Entering " + CLASS_NAME);
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getStateArrayList(MARSQuery.TOWN_LIST_IN_STATE_DISTRICT,multiMap);
			
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
		
		return list;

	}
	
	public List getListOfTownDetailsOnTownId(HashMap<Integer, Integer> valueMap) {
		
		boolean flag = false;
		List list = new ArrayList();
		logger.info("Entering " + CLASS_NAME);
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayListOnIn(MARSQuery.LIST_TOWN_DETAILS_ON_TOWN_ID, valueMap);
			
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
		
		return null;
	}
	
	public static void main(String[] args) {
		TownService vs=new TownService();
		
		
		Multimap<String, String> multiMap = HashMultimap.create();
		
		 multiMap.put("27","31");
		 multiMap.put("27","26");
		 
		/* multiMap.put("28","1");
		 multiMap.put("28","23");*/
		
		vs. getTownListOnStateDistrict(multiMap);
		
		List townList =vs.getTownListOnStateDistrict(multiMap);
		Town town=null;
		Collection<Town> townCollection = null;
		townCollection=new ArrayList<Town>();
		int k=0;
		  for(int i=1; i<townList.size(); i++) {
				
				Object[] ObjList=(Object[])townList.get(i);
				System.out.println(ObjList[k++].toString());
				System.out.println(ObjList[k++].toString());
				System.out.println(ObjList[k++].toString());
				System.out.println(ObjList[k++].toString());
				k=0;
				
				//villageCollection.add(village);

      	}

	}

	


}
