package com.CensusViewer.census.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.CensusViewer.census.model.JacksonVillages;
import com.CensusViewer.census.model.Village;
import com.CensusViewer.census.model.VillageTable;
import com.CensusViewer.census.services.customerservice.VillageService;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;



public class VillageDetails {
	Village village=null;
	Collection<Village> villageCollection = null;
	
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
	
	

	public Collection<Village> VillageDetails(String jacksonVillageDetails) {
		
		
		
		logger.info("Entering " + CLASS_NAME);
		int k = 0;
		// TODO Auto-generated method stub
		Collection<Village> VillageList = new ArrayList<Village>();



		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		  mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			
			// convert user object to json string, and save to a file
		JacksonVillages villages=mapper.readValue(jacksonVillageDetails,JacksonVillages.class );
		//System.out.println(district);
			// display to console
			System.out.println(mapper.writeValueAsString(villages));
		
			List<VillageTable> list1=(List<VillageTable>) villages.getJacksonVillageDetails();
			for (VillageTable myPerson : list1) {  

				//  System.out.println(myPerson.getcountryName());
			
				
					String DistrictCode=myPerson.getDistCode();
		System.out.println("abhishek"+DistrictCode);
		List<String> items1 = Arrays.asList(DistrictCode.split(","));
		 
		
		
		Multimap<String, String> multiMap = HashMultimap.create();
		for(int j=0;j<items1.size();j++){
		
		   
		
		String[] parts = items1.get(j).split(":");
		String value = parts[0];
		String key = parts[1];
		 multiMap.put( key, value);
		
		



/*System.out.println("value="+value);
System.out.println("key="+key);
*/

		}
		
		VillageService vs= new VillageService();
		List villageList=vs.getStateDistrictVillageList(multiMap);
		
		
		villageCollection=new ArrayList<Village>();
		
		  for(int i=1; i<villageList.size(); i++) {
				
				Object[] ObjList=(Object[])villageList.get(i);
				village=new Village();
				village.setVillageName(ObjList[k++].toString());
				village.setSegment(ObjList[k++].toString());
				village.setTehsil(ObjList[k++].toString());
				village.setDistrictName(ObjList[k++].toString());
				village.setStateName(ObjList[k++].toString());
				village.setVillid(Integer.parseInt(ObjList[k++].toString()));
				village.setTehsilCode(ObjList[k++].toString());
				village.setDistCode(ObjList[k++].toString());
				village.setStatecode(ObjList[k++].toString());
				k=0;
				
				villageCollection.add(village);

      	}
		}
		return villageCollection;
		
		
		
		
		
		/*Multimap<String, String> multiMap = HashMultimap.create();
		for (java.util.Map.Entry<String, String> entry : map.entrySet()) {
		  multiMap.put( entry.getValue(), entry.getKey());
		}*/

		
/*
for (Entry<String, Collection<String>> entry : multiMap.asMap().entrySet()) {
  System.out.println("Original values: " +entry.getValue() + " was mapped to key: "
      + entry.getKey());
  
   statecodes= new StringBuilder();
  
  for (String elem : entry.getValue()) {
	
	  statecodes.append("'");
		 statecodes.append(elem);
		 statecodes.append("'");
		 statecodes.append(",");
		
  }
	statecodes.deleteCharAt(statecodes.length()-1);
	String stateCodes=statecodes.toString();
  System.out.println(stateCodes);
  
	
	Village village=null;

	ApplicationContext appContext = new ClassPathXmlApplicationContext(
			"config/spring-configuration.xml");

	VillageDao districtDao = (VillageDao)appContext.getBean("VillageDao");
	
	
	List<VillageTable> list = districtDao.RetreiveVillage("FROM village WHERE statecode ='"+entry.getKey()+"' AND distcode IN ("+stateCodes+") ");

	for (VillageTable myPerson1 : list) {  

		
		
			village=new Village();
		
			
			String DistCode=myPerson1.getDistCode();

			String villageName=myPerson1.getName();

			
			
			System.out.println(DistCode);
			System.out.println(villageName);
		
			 village.setDistCode(DistCode);
			 village.setName(villageName);
			 VillageList.add(village);
	
	}
	
			}*/
			
  
  
  
  

	
			
	
				
			
			
			
	

			

		
		}
		catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return VillageList;

	}
	
	
	

}
