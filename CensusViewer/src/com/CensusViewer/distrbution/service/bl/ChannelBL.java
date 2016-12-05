package com.CensusViewer.distrbution.service.bl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.CensusViewer.census.model.JacksonDistrictCriteriaDetails;
import com.CensusViewer.distribution.ServicePojo.ChannelIntermJson;
import com.CensusViewer.distribution.ServicePojo.ChannelJson;
import com.CensusViewer.distribution.ServicePojo.ChannelStructureJson;
import com.CensusViewer.distribution.ServicePojo.JacksonChannelDetails;
import com.CensusViewer.distribution.ServicePojo.JacksonUpdateChannel;
import com.CensusViewer.distribution.ServicePojo.RegDistJson;
import com.CensusViewer.distribution.config.DistributionConstants;
import com.CensusViewer.distribution.daoimpl.ChannelDaoImpl;
import com.CensusViewer.distribution.model.Channel;

public class ChannelBL {

	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);
	ChannelDaoImpl channelDaoImpl = null;
	public ChannelBL() {
		logger.info("Entering " + CLASS_NAME);
	}

	public Collection<ChannelJson> getChannels() {
		channelDaoImpl = new ChannelDaoImpl();
		List<Channel> channelList = new ArrayList<Channel>();
		List<ChannelJson> channelJsonCollection = new ArrayList<ChannelJson>(); 
		ChannelJson channelJson = null; 
		try {
			channelList = channelDaoImpl.getChannelNames();
			for(Channel channels : channelList) {
				channelJson = new ChannelJson();
				channelJson.setChannelId(channels.getChannelId());
				channelJson.setChannelName(channels.getChannelName());
				channelJson.setLevelCount(channels.getLevelCount());
				channelJson.setOrgId(channels.getOrgId());
				channelJsonCollection.add(channelJson);
			}
		} catch(Exception e) {
		} finally {
		}
		Collections.sort(channelJsonCollection, new ChannelJson());
		return channelJsonCollection;
	}


	public ChannelStructureJson getChannelStructure(String channelDetailsParam) {

		String orgId = "";
		String channelId = "";
		
		try {
			JSONObject json = new JSONObject(channelDetailsParam);
			orgId=json.getString("orgId");
			channelId=json.getString("channelId");

		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}
		
		channelDaoImpl = new ChannelDaoImpl();
		ChannelStructureJson channelStructureJson = new ChannelStructureJson(); 
		ChannelIntermJson channelIntermJson = null;

		try {

			List<LinkedHashMap> channelDetails = channelDaoImpl.getChannelIntermOnKey(channelId, orgId);

			Iterator it = channelDetails.iterator();
			while (it.hasNext()) {

				LinkedHashMap<String, String> channelIdName = (LinkedHashMap<String, String>)it.next();

				Iterator it2 = channelIdName.entrySet().iterator();

				while (it2.hasNext()) {

					Map.Entry pairs = (Map.Entry)it2.next();
					
					channelStructureJson.setChannelName(pairs.getValue().toString());
					channelStructureJson.setChannelId(pairs.getKey().toString());
				}

				LinkedHashMap<String, Integer> channelIntermDetails = (LinkedHashMap<String, Integer>)it.next();

				Iterator it1 = channelIntermDetails.entrySet().iterator();

				while (it1.hasNext()) {

					Map.Entry pairs = (Map.Entry)it1.next();

					channelIntermJson = new ChannelIntermJson();

					channelIntermJson.setType(pairs.getKey().toString());
					channelIntermJson.setLevel(((Integer)pairs.getValue()).intValue());

					channelStructureJson.getChannelIntermjson().add(channelIntermJson);
				}

			}

		    
		    	Collections.sort(channelStructureJson.getChannelIntermjson(), new ChannelIntermJson());
		    

			return channelStructureJson;

		} catch(Exception e ) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}

		return null;
	}

	public void updateChannel(String channelIntrDetails){
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		channelDaoImpl = new ChannelDaoImpl();
		try{
			JacksonUpdateChannel channelDetails=mapper.readValue(channelIntrDetails,JacksonUpdateChannel.class );
			// display to console
			System.out.println(mapper.writeValueAsString(channelDetails));
			 channelDaoImpl.updateChannelData(channelDetails);
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.fatal(e.getMessage());	
		}
	
	}
	
	
	
//to save the created channel
	public ChannelStructureJson saveChannel(String channelIntrDetails){

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		channelDaoImpl = new ChannelDaoImpl();
		try {

			// convert user object to json string, and save to a file
			JacksonChannelDetails channelDetails=mapper.readValue(channelIntrDetails,JacksonChannelDetails.class );
			// display to console
			System.out.println(mapper.writeValueAsString(channelDetails));
			String channelId = channelDaoImpl.saveChannelData(channelDetails);
			
			String input="{'channelId':'"+channelId+"', 'orgId':'" + DistributionConstants.CREATED_ORGID + "'}";
			
			return getChannelStructure(input);

		}catch(Exception e){
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}
		return null;
	}
 public void deleteChannelStructure(String channelId){
	 channelDaoImpl = new ChannelDaoImpl();
	 try {
			JSONObject json = new JSONObject(channelId);
			String chId=json.getString("channelId");
			//String orgId=json.getString("orgId");
			channelDaoImpl.deleteChannelStruct(chId);
		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}
 }

 public boolean chkChannelName(String channelName){
	 channelDaoImpl = new ChannelDaoImpl();
	 boolean chStatus=false;
	 try {
			JSONObject json = new JSONObject(channelName);
			String chName=json.getString("channelName");
			//String orgId=json.getString("orgId");

		chStatus=channelDaoImpl.checkCHName(chName);
			
		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}
	 System.out.println("chStatus===="+chStatus);

	 return chStatus;	
	 
 }
 
 public ChannelStructureJson getALLChannelStructure(){
	 channelDaoImpl = new ChannelDaoImpl();
		ChannelStructureJson channelStructureJson = new ChannelStructureJson(); 
		ChannelIntermJson channelIntermJson = null;

		try {

			List<LinkedHashMap> channelDetails = channelDaoImpl.getChannelInterm();

			Iterator it = channelDetails.iterator();
			while (it.hasNext()) {

				LinkedHashMap<String, String> channelIdName = (LinkedHashMap<String, String>)it.next();

				Iterator it2 = channelIdName.entrySet().iterator();

				while (it2.hasNext()) {

					Map.Entry pairs = (Map.Entry)it2.next();
					channelStructureJson.setChannelName(pairs.getValue().toString());
					channelStructureJson.setChannelId(pairs.getKey().toString());
				}
				LinkedHashMap<String, Integer> channelIntermDetails = (LinkedHashMap<String, Integer>)it.next();
				Iterator it1 = channelIntermDetails.entrySet().iterator();
				while (it1.hasNext()) {
					Map.Entry pairs = (Map.Entry)it1.next();
					channelIntermJson = new ChannelIntermJson();
					channelIntermJson.setType(pairs.getKey().toString());
					channelIntermJson.setLevel(((Integer)pairs.getValue()).intValue());
					channelStructureJson.getChannelIntermjson().add(channelIntermJson);
				}

			}
		    	Collections.sort(channelStructureJson.getChannelIntermjson(), new ChannelIntermJson());
			return channelStructureJson;

		} catch(Exception e ) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}

		return null;
	}



	public static void main(String args[]){
		String channelIntrDetails="{'channelName':'A33445A33445','channelId':'CH275','levelCount':4,'interMediaryType':[{'type':'Producer121212','level':1},{'type':'Wholesaler','level':2},{'type':'Retailer','level':3},{'type':'Other11','level':4}]}";
//		String channelIntrDetails="{'channelName':'A123' ,'levelCount':2,'interMediaryType':[ {'type':'wholeseller1', 'level':'2'},{'type':'Producer1', 'level':'1'}]}";
		String input="{'channelName':'Pwewewewe'}";
		ChannelBL chb= new ChannelBL();
		chb.chkChannelName(input);
		//chb.saveChannel(channelIntrDetails);
	//	chb.chkChannelName(input);

		}

}
