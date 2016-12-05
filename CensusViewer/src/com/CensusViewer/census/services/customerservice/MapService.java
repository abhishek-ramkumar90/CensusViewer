package com.CensusViewer.census.services.customerservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.CensusViewer.census.datasource.MARSQuery;
import com.CensusViewer.census.datasource.MARSQueryInteractor;
import com.CensusViewer.census.servicedef.MapDefinition;
/**
 * @author bhupendras
 *
 */

public class MapService implements MapDefinition{

	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
    
    MARSQueryInteractor marsQueryInteraction = null;
    
	public List getDistrictSelectedData(HashMap selectedDistrict) {
		
		List list = new ArrayList();
		logger.info("Entering " + CLASS_NAME);
		
		try {
			
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayList(MARSQuery.MAP_DISTRICT_SELECTED_DATA, selectedDistrict);
			
			return list;	

		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return list;
	}
	
public List getTehsilSelectedData(HashMap selectedTehsil) {
		
		List list = new ArrayList();
		logger.info("Entering " + CLASS_NAME);
		
		try {
			
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayList(MARSQuery.MAP_TEHSIL_SELECTED_DATA, selectedTehsil);
			
			return list;	

		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return list;
	}
public List getTownsSelectedData(HashMap selectedTowns) {
	
	List list = new ArrayList();
	logger.info("Entering " + CLASS_NAME);
	
	try {
		
		marsQueryInteraction =  new MARSQueryInteractor();
		list = marsQueryInteraction.getArrayList(MARSQuery.MAP_TOWNS_SELECTED_DATA, selectedTowns);
		
		return list;	

	} catch(Exception e ) {
		e.printStackTrace();
		logger.error(e.getMessage());
	}
	return list;
}
public List getVillageSelectedData(HashMap selectedVillage) {
	
	List list = new ArrayList();
	logger.info("Entering " + CLASS_NAME);
	
	try {
		
		marsQueryInteraction =  new MARSQueryInteractor();
		list = marsQueryInteraction.getArrayList(MARSQuery.MAP_VILLAGE_SELECTED_DATA, selectedVillage);
		
		return list;	

	} catch(Exception e ) {
		e.printStackTrace();
		logger.error(e.getMessage());
	}
	return list;
}
	
	/*public static void main(String args[]) {
		
		MapService ms = new MapService();
		
		HashMap hm = new HashMap();
		hm.put(new Integer(1),79.0702529687);
		hm.put(new Integer(2),22.15804625);
		
		ms.getDistrictSelectedData(hm);
		
		
	}*/
}
