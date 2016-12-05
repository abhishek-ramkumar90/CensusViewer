package com.CensusViewer.census.services.customerservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.CensusViewer.census.datasource.MARSQuery;
import com.CensusViewer.census.datasource.MARSQueryInteractor;
import com.CensusViewer.census.servicedef.SolrSearchDefinition;

public class SolrSearchService implements SolrSearchDefinition{

	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
    MARSQueryInteractor marsQueryInteraction = null;
	public List getVillageList(HashMap villcode) {
	logger.info("Entering state " + CLASS_NAME);
		try {
			boolean flag = false;
			List list = new ArrayList();
			marsQueryInteraction =  new MARSQueryInteractor();
			list = marsQueryInteraction.getArrayList(MARSQuery.SOLR_VILLAGE,villcode);
			logger.debug("village heading " +(Object)list.get(0));
			return list;
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List getTownList(HashMap towncode) {
		logger.info("Entering state " + CLASS_NAME);
			
			try {
				
				boolean flag = false;
				List list = new ArrayList();
			
				marsQueryInteraction =  new MARSQueryInteractor();
				list = marsQueryInteraction.getArrayList(MARSQuery.SOLR_TOWN,towncode);
				
				logger.debug("town heading " +(Object)list.get(0));
				
				return list;
				
			} catch(Exception e ) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
			return null;
		}

	public List getDistList(HashMap distid) {
		logger.info("Entering state " + CLASS_NAME);
			
			try {
				
				boolean flag = false;
				List list = new ArrayList();
			
				marsQueryInteraction =  new MARSQueryInteractor();
				list = marsQueryInteraction.getArrayList(MARSQuery.SOLR_DISTRICT,distid);
				
				logger.debug("district heading " +(Object)list.get(0));
				
				return list;
				
			} catch(Exception e ) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
			return null;
		}
	
	public List getStateList(HashMap stateid) {
		logger.info("Entering state " + CLASS_NAME);
			try {
				boolean flag = false;
				List list = new ArrayList();
				marsQueryInteraction =  new MARSQueryInteractor();
				list = marsQueryInteraction.getArrayList(MARSQuery.SOLR_STATE,stateid);
				logger.debug("district heading " +(Object)list.get(0));
				return list;
			} catch(Exception e ) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
			return null;
		}
		
	public static void main(String args[]) {
		SolrSearchService ss = new SolrSearchService();
	//	ss.getStateList();
		HashMap VillageCodes = new HashMap();
		VillageCodes.put(new Integer(1), "32");
		ss.getVillageList(VillageCodes);
	}
}