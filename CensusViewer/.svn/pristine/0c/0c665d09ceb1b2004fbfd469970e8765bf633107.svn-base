package com.mars.census.services.customerservice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.google.common.collect.Multimap;
import com.mars.census.config.MARSConstants;
import com.mars.census.datasource.DBConnectionManager;
import com.mars.census.datasource.MARSQuery;
import com.mars.census.datasource.MARSQueryInteractor;
import com.mars.census.datasource.util.DBUtility;
import com.mars.census.model.Result;
import com.mars.census.servicedef.ResultGridDefinition;
import com.mars.census.util.Util;

public class ResultGridService implements ResultGridDefinition  {
	
	private static DBConnectionManager manager = new DBConnectionManager();
	
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
    
    MARSQueryInteractor marsQueryInteraction = null;

	public Result getVillageDetailsBySubCategory(ArrayList subCategorys,Multimap<String, String> multiMap,HashMap details) {
		logger.info("Entering " + CLASS_NAME);
		Connection moDbConn=null;
		ResultSet rs = null;
		Result result = null;
		StringBuilder sb = null;
		StringBuilder sb2 = null;
		StringBuilder sb3 = null;
		StringBuilder sb4 = null;
		StringBuilder sb5 = null;
		try {
			moDbConn = manager.getConnection();
			moDbConn.setAutoCommit(false);
			sb = new StringBuilder();
			sb4=new StringBuilder();
			sb5=new StringBuilder();
			marsQueryInteraction =  new MARSQueryInteractor();
			String modified=marsQueryInteraction.createQueryColumnAppend(MARSQuery.RESULT_STATE_DISTRICT, subCategorys, MARSConstants.VILLAGE);
			sb=new StringBuilder();
			sb.append(modified);
			String queryLocal=sb.toString();
			for (Entry<String, Collection<String>> entry : multiMap.asMap().entrySet()) {
				logger.debug("Original values: " +entry.getValue() + " was mapped to key: " + entry.getKey());
				System.out.println(queryLocal);
				sb2=new StringBuilder();
				int firstIn = queryLocal.indexOf("IN");
				int lastIn = queryLocal.lastIndexOf("IN");
				sb2.append(queryLocal.substring(MARSConstants.ZERO, firstIn + MARSConstants.THREE));
				sb2.append(MARSConstants.OPEN_BRACES);
				sb2.append(Util.getStringSingleQuoted(entry.getKey()));
				sb2.append(MARSConstants.CLOSE_BRACES);
				sb2.append(queryLocal.substring(firstIn + MARSConstants.TWO, lastIn + MARSConstants.THREE));
				sb2.append(MARSConstants.OPEN_BRACES);
				sb3= new StringBuilder();
				for (String elem : entry.getValue()) {
					sb3.append(MARSConstants.SINGLE_QUOTE);
					 sb3.append(elem);
					 sb3.append(MARSConstants.SINGLE_QUOTE);
					 sb3.append(MARSConstants.DELIMETER_COMA);
				}
				sb3.deleteCharAt(sb3.length() - MARSConstants.ONE);
				String stateCodes=sb3.toString();
				sb2.append(stateCodes);
				sb2.append(MARSConstants.CLOSE_BRACES);
				sb2.append(MARSConstants.SPACE);
				String modifiedquery4=sb2.toString();
				String modified2=marsQueryInteraction.createQueryColumnAppendLast1(modifiedquery4, details, MARSConstants.VILLAGE);
				sb4.append(modified2);
				sb4.append(MARSConstants.UNION);
				sb4.append(MARSConstants.SPACE);
			}
			int lastIndexUnion = sb4.lastIndexOf(MARSConstants.UNION);
			queryLocal = sb4.substring(MARSConstants.ZERO, lastIndexUnion).toString();
sb5.append(queryLocal);
String modifiedQuery1=sb5.toString();
Statement stmt = moDbConn.createStatement();
		rs = stmt.executeQuery(modifiedQuery1);
		result = marsQueryInteraction.getResultCollection(rs);
		}catch(Exception e ) {
			e.printStackTrace();
		} finally {
			DBUtility.closeResultSet(rs);
			DBUtility.closeConnection(moDbConn);
		}
		return result;
	}
		
	
public List getVillageDetailsBySubCategoryForPDF(ArrayList subCategorys,Multimap<String, String> multiMap,HashMap details) {
		logger.info("Entering " + CLASS_NAME);
		Connection moDbConn=null;
		ResultSet rs = null;
		List list = null;
		StringBuilder sb = null;
		StringBuilder sb2 = null;
		StringBuilder sb3 = null;
		StringBuilder sb4 = null;
		StringBuilder sb5 = null;
		try {
			moDbConn = manager.getConnection();
			moDbConn.setAutoCommit(false);
			sb = new StringBuilder();
			sb4=new StringBuilder();
			sb5=new StringBuilder();
			marsQueryInteraction =  new MARSQueryInteractor();
			String modified=marsQueryInteraction.createQueryColumnAppend(MARSQuery.RESULT_STATE_DISTRICT, subCategorys, MARSConstants.VILLAGE);
			sb=new StringBuilder();
			sb.append(modified);
			String queryLocal=sb.toString();
			for (Entry<String, Collection<String>> entry : multiMap.asMap().entrySet()) {
				logger.debug("Original values: " +entry.getValue() + " was mapped to key: " + entry.getKey());
				System.out.println(queryLocal);
				sb2=new StringBuilder();
				int firstIn = queryLocal.indexOf("IN");
				int lastIn = queryLocal.lastIndexOf("IN");
				sb2.append(queryLocal.substring(MARSConstants.ZERO, firstIn + MARSConstants.THREE));
				sb2.append(MARSConstants.OPEN_BRACES);
				sb2.append(Util.getStringSingleQuoted(entry.getKey()));
				sb2.append(MARSConstants.CLOSE_BRACES);
				sb2.append(queryLocal.substring(firstIn + MARSConstants.TWO, lastIn + MARSConstants.THREE));
				sb2.append(MARSConstants.OPEN_BRACES);
				sb3= new StringBuilder();
				for (String elem : entry.getValue()) {
					 sb3.append(MARSConstants.SINGLE_QUOTE);
					 sb3.append(elem);
					 sb3.append(MARSConstants.SINGLE_QUOTE);
					 sb3.append(MARSConstants.DELIMETER_COMA);
				}
				sb3.deleteCharAt(sb3.length() - MARSConstants.ONE);
				String stateCodes=sb3.toString();
				sb2.append(stateCodes);
				sb2.append(MARSConstants.CLOSE_BRACES);
				sb2.append(MARSConstants.SPACE);
				String modifiedquery4=sb2.toString();
				String modified2=marsQueryInteraction.createQueryColumnAppendLast1(modifiedquery4, details, MARSConstants.VILLAGE);
				sb4.append(modified2);
				sb4.append(MARSConstants.UNION);
				sb4.append(MARSConstants.SPACE);
			}
			int lastIndexUnion = sb4.lastIndexOf(MARSConstants.UNION);
			queryLocal = sb4.substring(MARSConstants.ZERO, lastIndexUnion).toString();
sb5.append(queryLocal);
String modifiedQuery1=sb5.toString();
Statement stmt = moDbConn.createStatement();
		rs = stmt.executeQuery(modifiedQuery1);
		list = marsQueryInteraction.getListForPDF(rs);
		}catch(Exception e ) {
			e.printStackTrace();
		} finally {
			DBUtility.closeResultSet(rs);
			DBUtility.closeConnection(moDbConn);
		}
		return list;
	}
	

	
	public Result getStateDetailsBySubCategory(ArrayList subCategorys, HashMap stateCodes,HashMap details) {
		logger.info("Entering " + CLASS_NAME);
		List list = null;
		Connection moDbConn=null;
		ResultSet rs = null;
		Result result = null;
		StringBuilder sb = null;
		StringBuilder sb2 = null;
		
		try {
			list = new ArrayList();
			moDbConn = manager.getConnection();
			moDbConn.setAutoCommit(false);

			sb = new StringBuilder();
			sb2=new StringBuilder();
			
			
			marsQueryInteraction =  new MARSQueryInteractor();
			String modifiedQuery = marsQueryInteraction.createQueryColumnAppend(MARSQuery.STATE_SUB_CAT_DETAILS, subCategorys, MARSConstants.DISTRICT);
		//String modifiedQuery=	SELECT state.al_per_f, state.al_mm_p,  name FROM state WHERE statecode IN 
			
			/*SELECT district.other_soc, district.a_c_soc, district.ac_soc,  district.name,state.name FROM state, district 
			WHERE district.statecode IN('27','28')   AND state.statecode=district.statecode  AND
			district.other_soc <100 and district.a_c_soc<100 and district.ac_soc>100;*/	
			sb.append(modifiedQuery);
			
           int andIndext=sb.indexOf(MARSConstants.AND);			
			int length=sb.length();
			String queryend=sb.substring(andIndext,length);
		//	sb.append(MARSConstants.OPEN_BRACES);
			int selectIndex=sb.indexOf(MARSConstants.SELECT);
			int inIndex=sb.indexOf(MARSConstants.IN);
			
			sb2.append(sb.substring(selectIndex, selectIndex +  inIndex+MARSConstants.IN.length()));
			sb2.append(MARSConstants.OPEN_BRACES);
			
			for (Iterator itr = stateCodes.keySet().iterator(); itr.hasNext(); ) {
				Object key = itr.next();
				if (key instanceof Integer) {
					logger.info("HashMap key for the query="+ key.toString());
					Object value = stateCodes.get(key);
				
					sb2.append(MARSConstants.SINGLE_QUOTE);
					sb2.append(value.toString());
					sb2.append(MARSConstants.SINGLE_QUOTE);
					sb2.append(MARSConstants.DELIMETER_COMA);
					
					
				}
			}
			
			sb2.deleteCharAt(sb2.length()-1);
			sb2.append(MARSConstants.CLOSE_BRACES);
			sb2.append(queryend);
			//SELECT district.tot_m, district.tot_f,  district.name,state.name FROM state,district WHERE district.statecode IN('27','28')AND state.statecode=district.statecode  AND
		//	String modifiedquery2=marsQueryInteraction.createQueryColumnAppend();
			
			String modifiedQuery1=sb2.toString();
			String modifiedQuery2=marsQueryInteraction.createQueryColumnAppendLast(modifiedQuery1,details,MARSConstants.DISTRICT);
			Statement stmt = moDbConn.createStatement();
			rs = stmt.executeQuery(modifiedQuery2);
			
			result = marsQueryInteraction.getResultCollection(rs);
			return result;
			
		} catch(Exception e ) {
			e.printStackTrace();
		} finally {
			DBUtility.closeResultSet(rs);
			DBUtility.closeConnection(moDbConn);
		}
		
		return result;
	}
	
	
	public List getStateDetailsBySubCategoryForPDF(ArrayList subCategorys, HashMap stateCodes,HashMap details) {
		
		logger.info("Entering " + CLASS_NAME);
		List list = null;
		Connection moDbConn=null;
		ResultSet rs = null;
		Result result = null;
		StringBuilder sb = null;
		StringBuilder sb2 = null;
		
		try {
			list = new ArrayList();
			moDbConn = manager.getConnection();
			moDbConn.setAutoCommit(false);

			sb = new StringBuilder();
			sb2=new StringBuilder();
			
			
			marsQueryInteraction =  new MARSQueryInteractor();
			String modifiedQuery = marsQueryInteraction.createQueryColumnAppend(MARSQuery.STATE_SUB_CAT_DETAILS, subCategorys, MARSConstants.DISTRICT);
		//String modifiedQuery=	SELECT state.al_per_f, state.al_mm_p,  name FROM state WHERE statecode IN 
			
			/*SELECT district.other_soc, district.a_c_soc, district.ac_soc,  district.name,state.name FROM state, district 
			WHERE district.statecode IN('27','28')   AND state.statecode=district.statecode  AND
			district.other_soc <100 and district.a_c_soc<100 and district.ac_soc>100;*/	
			sb.append(modifiedQuery);
			
           int andIndext=sb.indexOf(MARSConstants.AND);			
			int length=sb.length();
			String queryend=sb.substring(andIndext,length);
		//	sb.append(MARSConstants.OPEN_BRACES);
			int selectIndex=sb.indexOf(MARSConstants.SELECT);
			int inIndex=sb.indexOf(MARSConstants.IN);
			
			sb2.append(sb.substring(selectIndex, selectIndex +  inIndex+MARSConstants.IN.length()));
			sb2.append(MARSConstants.OPEN_BRACES);
			
			for (Iterator itr = stateCodes.keySet().iterator(); itr.hasNext(); ) {
				Object key = itr.next();
				if (key instanceof Integer) {
					logger.info("HashMap key for the query="+ key.toString());
					Object value = stateCodes.get(key);
				
					sb2.append(MARSConstants.SINGLE_QUOTE);
					sb2.append(value.toString());
					sb2.append(MARSConstants.SINGLE_QUOTE);
					sb2.append(MARSConstants.DELIMETER_COMA);
					
					
				}
			}
			
			sb2.deleteCharAt(sb2.length()-1);
			sb2.append(MARSConstants.CLOSE_BRACES);
			sb2.append(queryend);
			//SELECT district.tot_m, district.tot_f,  district.name,state.name FROM state,district WHERE district.statecode IN('27','28')AND state.statecode=district.statecode  AND
		//	String modifiedquery2=marsQueryInteraction.createQueryColumnAppend();
			
			String modifiedQuery1=sb2.toString();
			String modifiedQuery2=marsQueryInteraction.createQueryColumnAppendLast(modifiedQuery1,details,MARSConstants.DISTRICT);
			Statement stmt = moDbConn.createStatement();
			rs = stmt.executeQuery(modifiedQuery2);
			
			list = marsQueryInteraction.getListForPDF(rs);
			return list;
			
		} catch(Exception e ) {
			e.printStackTrace();
		} finally {
			DBUtility.closeResultSet(rs);
			DBUtility.closeConnection(moDbConn);
		}
		
		return list;
	}
	
	
	public Result getStateDistrictDetailsBySubCategory(ArrayList subCategorys, HashMap stateDistrictCodes) {
	
		logger.info("Entering " + CLASS_NAME);
		
		Connection moDbConn=null;
		ResultSet rs = null;
		Result result = null;
		StringBuilder sb = null;
		StringTokenizer stringTokenizer = null;
		List<String> codes=null;
		
		try {
		
			moDbConn = manager.getConnection();
			moDbConn.setAutoCommit(false);

			sb = new StringBuilder();
			
			marsQueryInteraction = new MARSQueryInteractor();
			
			String modifiedQuery = marsQueryInteraction.createQueryColumnAppend(MARSQuery.STATE_DISTRICT_SUB_CAT_DETAILS, subCategorys, MARSConstants.DISTRICT);
			String modifiedQueryCopy = modifiedQuery;
			
			Iterator it = stateDistrictCodes.entrySet().iterator();
			
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();

		        stringTokenizer = new StringTokenizer(pairs.getValue().toString(),":");
		        codes=new ArrayList<String>();
		        while (stringTokenizer.hasMoreTokens()) {
		            
		            codes.add(stringTokenizer.nextToken());
		       }
		        logger.info(codes.get(0));
		     
		        String scode= codes.get(1);
		        String dcode=codes.get(0);
		        modifiedQueryCopy= modifiedQueryCopy.replace("state_code", scode);
		        modifiedQueryCopy= modifiedQueryCopy.replace("dist_code",dcode );
			    
		        sb.append(modifiedQueryCopy);
		        sb.append(MARSConstants.SPACE);
		        sb.append(MARSConstants.UNION);
		        sb.append(MARSConstants.SPACE);
		        
		        modifiedQueryCopy = modifiedQuery;
		    }
		    
		    int lastIndexUnion = sb.lastIndexOf(MARSConstants.UNION);
		    modifiedQuery = sb.substring(MARSConstants.ZERO, lastIndexUnion).toString();
		    logger.info(modifiedQuery);
		    Statement stmt = moDbConn.createStatement();
			rs = stmt.executeQuery(modifiedQuery);
			
			result = marsQueryInteraction.getResultCollection(rs);

			return result;
			

		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	public Result getTownDetailsByDistSubCatCriteria(ArrayList subCategorys, HashMap stateDistrictCodes,HashMap details) {
		
		logger.info("Entering " + CLASS_NAME);
		
		Connection moDbConn=null;
		ResultSet rs = null;
		Result result = null;
		StringBuilder sb = null;
		StringTokenizer stringTokenizer = null;
		List<String> codes=null;
		
		try {
		
			moDbConn = manager.getConnection();
			moDbConn.setAutoCommit(false);

			sb = new StringBuilder();
			
			marsQueryInteraction = new MARSQueryInteractor();
			
			String modifiedQuery = marsQueryInteraction.createQueryColumnAppend(MARSQuery.STATE_DISTRICT_SUB_CAT_CRITERIA_DETAILS, subCategorys, MARSConstants.TOWN);
			String modifiedQueryCopy = modifiedQuery;
			
			Iterator it = stateDistrictCodes.entrySet().iterator();
			
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();

		        stringTokenizer = new StringTokenizer(pairs.getValue().toString(),":");
		        codes=new ArrayList<String>();
		        while (stringTokenizer.hasMoreTokens()) {
		            
		            codes.add(stringTokenizer.nextToken());
		       }
		        logger.info(codes.get(0));
		     
		        String scode= codes.get(1);
		        String dcode=codes.get(0);
		        modifiedQueryCopy= modifiedQueryCopy.replace("state_code", scode);
		        modifiedQueryCopy= modifiedQueryCopy.replace("dist_code",dcode );
			    
		        
		       String modifiedQueryCopy1=marsQueryInteraction.createQueryColumnAppendLast(modifiedQueryCopy,details,MARSConstants.TOWN);
		       sb.append(modifiedQueryCopy1);
		        sb.append(MARSConstants.SPACE);
		        sb.append(MARSConstants.UNION);
		        sb.append(MARSConstants.SPACE);
		        
		        modifiedQueryCopy = modifiedQuery;
		    }
		    
		    int lastIndexUnion = sb.lastIndexOf(MARSConstants.UNION);
		    modifiedQuery = sb.substring(MARSConstants.ZERO, lastIndexUnion).toString();
		    logger.info(modifiedQuery);
		    Statement stmt = moDbConn.createStatement();
			rs = stmt.executeQuery(modifiedQuery);
			
			result = marsQueryInteraction.getResultCollection(rs);

			return result;
			

		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
	
public List getTownDetailsByDistSubCatCriteriaForPDF(ArrayList subCategorys, HashMap stateDistrictCodes,HashMap details) {
		
		logger.info("Entering " + CLASS_NAME);
		
		Connection moDbConn=null;
		ResultSet rs = null;
		List list = null;
		StringBuilder sb = null;
		StringTokenizer stringTokenizer = null;
		List<String> codes=null;
		
		try {
		
			moDbConn = manager.getConnection();
			moDbConn.setAutoCommit(false);

			sb = new StringBuilder();
			
			marsQueryInteraction = new MARSQueryInteractor();
			
			String modifiedQuery = marsQueryInteraction.createQueryColumnAppend(MARSQuery.STATE_DISTRICT_SUB_CAT_CRITERIA_DETAILS, subCategorys, MARSConstants.TOWN);
			String modifiedQueryCopy = modifiedQuery;
			
			Iterator it = stateDistrictCodes.entrySet().iterator();
			
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();

		        stringTokenizer = new StringTokenizer(pairs.getValue().toString(),":");
		        codes=new ArrayList<String>();
		        while (stringTokenizer.hasMoreTokens()) {
		            
		            codes.add(stringTokenizer.nextToken());
		       }
		        logger.info(codes.get(0));
		     
		        String scode= codes.get(1);
		        String dcode=codes.get(0);
		        modifiedQueryCopy= modifiedQueryCopy.replace("state_code", scode);
		        modifiedQueryCopy= modifiedQueryCopy.replace("dist_code",dcode );
			    
		        
		       String modifiedQueryCopy1=marsQueryInteraction.createQueryColumnAppendLast(modifiedQueryCopy,details,MARSConstants.TOWN);
		       sb.append(modifiedQueryCopy1);
		        sb.append(MARSConstants.SPACE);
		        sb.append(MARSConstants.UNION);
		        sb.append(MARSConstants.SPACE);
		        
		        modifiedQueryCopy = modifiedQuery;
		    }
		    
		    int lastIndexUnion = sb.lastIndexOf(MARSConstants.UNION);
		    modifiedQuery = sb.substring(MARSConstants.ZERO, lastIndexUnion).toString();
		    logger.info(modifiedQuery);
		    Statement stmt = moDbConn.createStatement();
			rs = stmt.executeQuery(modifiedQuery);
			
			list = marsQueryInteraction.getListForPDF(rs);

			return list;
			

		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return list;
	}


	public static void main(String[] args) {
		
		

		ArrayList columnNames = new ArrayList();
		HashMap distcode=new HashMap();
		HashMap<Integer,List> details=new HashMap<Integer,List>();
		
		columnNames.add("tot_m");
		columnNames.add("tot_f");
		distcode.put(new Integer(1),"06:27");
		distcode.put(new Integer(2),"26:27");
		List det=new ArrayList();
		det.add("tot_m");
		det.add(">");
		det.add("100");
		List det1=new ArrayList();
		det1.add("tot_f");
		det1.add(">");
		det1.add("100");
		details.put(new Integer(1),det);
		details.put(new Integer(2),det1);
		ResultGridService rgs=new ResultGridService();
		rgs.getTownDetailsByDistSubCatCriteria(columnNames,distcode,details);
		
		

		

	}




}
