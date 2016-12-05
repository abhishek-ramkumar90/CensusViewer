package com.CensusViewer.census.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;





public class main {
	public static void main(String[] args) throws JSONException, SolrServerException {

/*String jacksonVillageDetails="{'jacksonVillageDetails':[{'distCode':'26:27,05:27,07:27,06:29'}]}";
StringBuilder statecodes;
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
		String key = parts[0];
		String value = parts[1];
		 multiMap.put( value, key);
		
		



System.out.println("value="+value);
System.out.println("key="+key);


		}
		Multimap<String, String> multiMap = HashMultimap.create();
		for (java.util.Map.Entry<String, String> entry : map.entrySet()) {
		  multiMap.put( entry.getValue(), entry.getKey());
		}

		

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
			 village.setVillageName(villageName);
			 VillageList.add(village);
	
	}
	
			}
			
  
  
  
  
}
	
			
	
				
			
			
			
	

			

		
		}
		catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}*/
	
	
	
	
	
	
	
	
	
	
	
	
/*		SolrServer server = new HttpSolrServer("http://localhost:8080/solr/collection1");
		

	    ModifiableSolrParams params = new ModifiableSolrParams();
	    String id="sat*";
	    params.set("q", "name:"+id);
	    params.set("defType", "edismax");
	    params.set("start", "0");

	    QueryResponse response = server.query(params);
	    Gson gson = new Gson();
	    
		// convert java object to JSON format,
		// and returned as JSON formatted string
		String json = gson.toJson(response);
		//JSONObject jsonObj = new JSONObject(json);
	
		json23.setSolrjson(json);
//	
*/	
	
	
	
	
	
	}
}


		
		
		
			

				
				
			
		
		
		
		
		
		
		
	
		
		
		
		
		



