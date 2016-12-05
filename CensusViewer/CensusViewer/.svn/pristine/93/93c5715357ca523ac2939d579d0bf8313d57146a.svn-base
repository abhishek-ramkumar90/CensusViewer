package com.mars.census.services.customerservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.mars.census.datasource.MARSQuery;
import com.mars.census.datasource.MARSQueryInteractor;
import com.mars.census.servicedef.CategoryDefinition;

public class CategoryService implements CategoryDefinition {

	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
    MARSQueryInteractor marsQueryInteraction = null;
    
	public List getStateCategoryList(HashMap stateSelect) {

		logger.info("Entering state " + CLASS_NAME);
		
		try {
			boolean flag = false;
			List list = new ArrayList();
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayList(MARSQuery.CATEGORY_STATE_LIST,stateSelect);
			logger.debug("district heading " +(Object)list.get(0));
			
			return list;
			
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	public List getDistrictCategoryList(HashMap districtSelect) {

		logger.info("Entering district " + CLASS_NAME);
		
		try {
			
			boolean flag = false;
			List list = new ArrayList();
		
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayList(MARSQuery.CATEGORY_DISTRICT_LIST,districtSelect);
			
			return list;
			
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List getVillageTownCategoryList(HashMap villageTownSelect) {

		boolean flag = false;
		List list = new ArrayList();
		
		logger.info("Entering district " + CLASS_NAME);
		
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayList(MARSQuery.CATEGORY_DISTRICT_TOWN_VILLAGE_LIST,villageTownSelect);

			return list;
			
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return list;
	}
	
	public List getStateSubCategory(HashMap categoryDetails) {
	
		boolean flag = false;
		List list = new ArrayList();
		
		logger.info("Entering district " + CLASS_NAME);
		
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayList(MARSQuery.STATE_SUB_CATEGORY_LIST_ON_CATEGORY,categoryDetails);
			
			return list;
			
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return list;
	}
	
	public List getDistrictSubCategory(HashMap categoryDetails) {
		
		boolean flag = false;
		List list = new ArrayList();
		
		logger.info("Entering district " + CLASS_NAME);
		
		try {
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayList(MARSQuery.DISTRICT_SUB_CATEGORY_LIST_ON_CATEGORY,categoryDetails);
			
			return list;
			
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		CategoryService cs = new CategoryService();
		
		HashMap stateSelect = new HashMap();
		stateSelect.put(new Integer(1), "r");
		
		
		cs.getVillageTownCategoryList(stateSelect);
	}

}
