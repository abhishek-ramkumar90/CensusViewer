package com.CensusViewer.census.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import com.CensusViewer.census.impl.TownDaoImp;
import com.CensusViewer.census.model.Town;
import com.CensusViewer.census.model.TownWb;


public class TownServiceDaoImp  {
	//get the list of towns as per state,district
	public List<TownWb> getTownList(){
		TownWb towns = null;
		String jacksoncode="{ 'codes': { 'state': [ { 'stcode': ' 27 ', 'distcode': [  '7','9']}, {  'stcode': ' 28 ', 'distcode': [   '7', '9' ] }  ]}}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		  mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);	
		
		
		TownDaoImp townImp=new TownDaoImp();
		List<Town> listp=townImp.townDetails();
		List<TownWb> townList = new ArrayList<TownWb>();
		 
		for (Town myTown : listp) {  
			
	           
	             
			            towns = new TownWb();
			          
	            		//Integer villageId=myTown.getId();
	            		String townName= myTown.getTown_name();
	            		towns.setTown_name(townName);
	            		
	            		
	            		townList.add(towns);
	            		
	             
	         }
		
		
		return townList;
	}//end of getTownList
}
