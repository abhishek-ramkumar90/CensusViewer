package com.CensusViewer.census.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;




import com.CensusViewer.census.model.District;
import com.CensusViewer.census.model.JacksonState;
import com.CensusViewer.census.model.maharashtraState;
import com.CensusViewer.census.services.customerservice.DistrictService;




public class DistrictDetails   {
	
	District district1=null;
	List<District> districtCollection = null;
	
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);

	@SuppressWarnings("unchecked")
	public Collection<District> districtDetails(String jacksonStateDetails1) {
		
		logger.info("Entering " + CLASS_NAME);
		int k = 0;
		StringBuilder statecodes = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {

		// convert user object to json string, and save to a file
		JacksonState district=mapper.readValue(jacksonStateDetails1,JacksonState.class );
		// display to console
		System.out.println(mapper.writeValueAsString(district));

		Collection<District> DistrictList = new ArrayList<District>();
		List<maharashtraState> list1=(List<maharashtraState>) district.getJacksonStateDetails();

			for (maharashtraState myPerson : list1) {	  
	
				if(myPerson.getStatecode() != null) { 
	
					String stateCode1=myPerson.getStatecode();
				    List<String> items1 = Arrays.asList(stateCode1.split("\\s*,\\s*"));
			    	DistrictService ds = new DistrictService();
		            HashMap stateCodes1 = new HashMap();
		            int j=1;
		            
		            for(int i=0;i<items1.size();i++) {
	                  stateCodes1.put(new Integer(j),items1.get(i));
	                  j++;
	            	}
	
		            List districtList=	ds.getDistrictList(stateCodes1);
		            districtCollection=new ArrayList<District>();
		            
		            for(int i=1; i<districtList.size(); i++) {
						
						Object[] ObjList=(Object[])districtList.get(i);
						district1=new District();
						district1.setDist_code(ObjList[k++].toString());
						district1.setName(ObjList[k++].toString());
						district1.setStateCode(ObjList[k++].toString());
						district1.setTot_p((BigDecimal)ObjList[k++]);
						district1.setTot_irr((BigDecimal)ObjList[k++]);
						district1.setPower((Integer)ObjList[k++]);
						try{district1.setEducation((Integer)ObjList[k++]);}catch(Exception e){district1.setEducation((0));}
						district1.setHospital((BigDecimal)ObjList[k++]);
						//district1.setTot_rec((BigDecimal)ObjList[k++]);
						district1.setNo_of_comm_inst((Integer)ObjList[k++]);
						district1.setNo_of_watersrc((Integer)ObjList[k++]);
						district1.setEntertain((Integer)ObjList[k++]);
						district1.setTot_work_p((BigDecimal)ObjList[k++]);
						district1.setNo_of_transport_mode((Integer)ObjList[k++]);
						district1.setTot_exp((BigDecimal)ObjList[k++]);
						district1.setTot_inc((BigDecimal)ObjList[k++]);
						district1.setNo_of_comm_mode((Integer)ObjList[k++]);
						district1.setService((Integer)ObjList[k++]);
						district1.setPap_mag_src((Integer)ObjList[k++]);
						district1.setGid((Integer)ObjList[k++]);
						k=0;
						districtCollection.add(district1);
	            	}
				}
				Collections.sort(districtCollection, new Comparator() {  
		            @Override  
		            public int compare(Object obj1, Object obj2) {  
		            	District dist1 = (District)obj1;  
		            	District dist2 = (District)obj2;  
		                return dist1.getName().compareToIgnoreCase(dist2.getName());  
		            }  
		        });  
				return districtCollection;
			}
		}catch (JsonGenerationException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return null;
	}
}

		



