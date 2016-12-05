package com.CensusViewer.census.services.customerservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.CensusViewer.census.datasource.MARSQuery;
import com.CensusViewer.census.datasource.MARSQueryInteractor;
import com.CensusViewer.census.model.Village;
import com.CensusViewer.census.servicedef.VillageDefinition;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class VillageService implements VillageDefinition {

	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
    
    MARSQueryInteractor marsQueryInteraction = null;
    
    public List getStateVillageList(HashMap stateCodes) {
		
		boolean flag = false;
		List list = new ArrayList();
		logger.info("Entering " + CLASS_NAME);
			
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayListOnIn(MARSQuery.STATE_VILLAGE_LIST,stateCodes);
			
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
		return list;
	}

	@Override
	public List getStateDistrictVillageList(Multimap<String, String> multiMap) {

		boolean flag = false;
		List list = new ArrayList();
		logger.info("Entering " + CLASS_NAME);
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getStateArrayList(MARSQuery.STATE_DISTRICT_VILLAGE_LIST,multiMap);
			
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
		return list;
	}

	
	public List getVillageListOnStateDistrict(Multimap<String, String> multiMap) {
		List list = new ArrayList();
		try {
			boolean flag = false;
			logger.info("Entering " + CLASS_NAME);
			try {
				marsQueryInteraction =  new MARSQueryInteractor();
				list = marsQueryInteraction.getStateArrayList(MARSQuery.VILLAGE_LIST_IN_STATE_DISTRICT,multiMap);
				
				return list;
				
			} catch(Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			} 
			return list;
			
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return list;
	}
	
	public List getListOfVillageDetailsOnVillId(HashMap<Integer,Integer> villIds) {
		List list = new ArrayList();
		try {
			boolean flag = false;
			logger.info("Entering " + CLASS_NAME);
			try {
				marsQueryInteraction =  new MARSQueryInteractor();
				list = marsQueryInteraction.getArrayListOnIn(MARSQuery.LIST_VILLAGE_DETAILS_ON_VILLAGE_ID, villIds);
				
				return list;
				
			} catch(Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			} 
			return list;
			
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("test village ");
		VillageService vs=new VillageService();
		
		
		Multimap<String, String> multiMap = HashMultimap.create();
		
		 multiMap.put("27","06");
		 multiMap.put("27","5");
		 
		 multiMap.put("28","1");
		 multiMap.put("28","23");
		
		//vs. getStateDistrictVillageList(multiMap);
		
		
		List villageList=vs.getVillageListOnStateDistrict(multiMap);
		Village village=null;
		Collection<Village> villageCollection = null;
		villageCollection=new ArrayList<Village>();
		int k=0;
		  for(int i=1; i<villageList.size(); i++) {
				
				Object[] ObjList=(Object[])villageList.get(i);
				System.out.println(ObjList[k++].toString());
				System.out.println(ObjList[k++].toString());
				System.out.println(ObjList[k++].toString());
				System.out.println(ObjList[k++].toString());
				k=0;
				
				//villageCollection.add(village);

      	}
		  
		  
	}



}
