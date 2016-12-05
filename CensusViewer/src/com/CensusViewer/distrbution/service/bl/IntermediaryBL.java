package com.CensusViewer.distrbution.service.bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.CensusViewer.distribution.ServicePojo.IntermTypes;
import com.CensusViewer.distribution.ServicePojo.IntermediaryJackson;
import com.CensusViewer.distribution.ServicePojo.IntermediarySerPojo;
import com.CensusViewer.distribution.config.DistributionConstants;
import com.CensusViewer.distribution.daoimpl.IntermediaryDaoImpl;

public class IntermediaryBL {

	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);
	IntermediaryDaoImpl intermediaryDaoImpl = null;

	public IntermediaryBL() {
		logger.info("Entering " + CLASS_NAME);
	}

	public IntermTypes getIntermediaryTypes() {

		List<HashMap> interm = null;
		HashMap hm = null;
		HashMap hmType = null;
		HashMap<String, String> intermVals = new HashMap<String, String>();
		
		intermediaryDaoImpl = new IntermediaryDaoImpl();
		try {
			IntermTypes intermTypes =  new IntermTypes();
			
			interm = intermediaryDaoImpl.getAllIntermediary(DistributionConstants.CREATED_ORGID, DistributionConstants.DEFAULT_USER);
			
			/*for(String intermVal : interm) {
				intermVals.put(intermVal, intermVal);
			} */
			/*HashMap hm = interm.get(DistributionConstants.ZERO);
			HashMap hm = inter*/
			
			for(int k=0; k<interm.size(); k++) {
				
				hm = interm.get(k++);
				hmType = interm.get(k++);
			}

			Iterator it = hm.entrySet().iterator();
		    while (it.hasNext()) {
		        
		    	Map.Entry pairs = (Map.Entry)it.next();
		    	intermTypes.setRootId(pairs.getKey().toString());
		    	//intermTypes.setRootStatus(new Integer(pairs.getKey().toString()).intValue());
		    	intermTypes.setRootType(pairs.getValue().toString());
		    }

		    Iterator it1 = hmType.entrySet().iterator();
		    while (it1.hasNext()) {
		    	Map.Entry pairs = (Map.Entry)it1.next();
		    	intermVals.put(pairs.getKey().toString(), pairs.getKey().toString());
		    }
		    
			intermTypes.setIntermType(intermVals);

			return intermTypes;

		} catch(Exception e ) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}

		return null;
	}

	public boolean saveIntermediaryDetails(String intermDetails) {
		boolean retVal = false;
		intermediaryDaoImpl = new IntermediaryDaoImpl();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {

			// convert user object to json string, and save to a file
			IntermediaryJackson intmDetails=mapper.readValue(intermDetails,IntermediaryJackson.class );
			// display to console
			System.out.println(mapper.writeValueAsString(intmDetails));
			retVal = intermediaryDaoImpl.saveIntermDetails(intmDetails);
			//return getChannelStructure(input);
			return retVal;
		}catch(Exception e){
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}
		return retVal;
	}
	
	public List<IntermediarySerPojo> getIntmByType(String intmType) {
		String intermedId;
		List<IntermediarySerPojo> intmList =new ArrayList<IntermediarySerPojo>();
		
		intermediaryDaoImpl = new IntermediaryDaoImpl();
		try {
			JSONObject json = new JSONObject(intmType);
			intermedId=json.getString("intmType");
			
			intmList = intermediaryDaoImpl.getIntermediaryByName(intermedId);
		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}	
		return intmList;
	}
	
	public List<IntermediarySerPojo> getIntmChild(String intmId) {
		String intermedId;
		List<IntermediarySerPojo> intmChildList =new ArrayList<IntermediarySerPojo>();
		
		intermediaryDaoImpl = new IntermediaryDaoImpl();
		try {
			JSONObject json = new JSONObject(intmId);
			intermedId=json.getString("intmId");
			
			intmChildList = intermediaryDaoImpl.getIntermediaryChild(intermedId);
		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}	
		return intmChildList;
	}	
	
	
	
	public static void main(String[] args){
		String input = "{'intermName':'ABC XY','intermType':'Producer','zoneId':'ZN12','regId':'R123','branchId':'B123','terrId':'T1223','villTownId':'1233','villTownType':'village','address':'Andheri east','parentType':' ','childType':' ','root':'true'}";
		IntermediaryBL intmB = new IntermediaryBL();
		intmB.saveIntermediaryDetails(input);
	}

}
