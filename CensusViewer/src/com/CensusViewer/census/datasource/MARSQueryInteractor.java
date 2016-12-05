package com.CensusViewer.census.datasource;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.CensusViewer.census.datasource.util.DBUtility;
import com.CensusViewer.census.model.Column;
import com.CensusViewer.census.model.Result;
import com.CensusViewer.census.model.Rows;
import com.CensusViewer.census.util.Util;
import com.CensusViewer.config.MARSConstants;
import com.google.common.collect.Multimap;


public class MARSQueryInteractor {

	//** Log Initialisation *//*
	private Logger logger = Logger.getLogger(this.getClass().getName());
	//** Connection Initialisation *//*
	private static DBConnectionManager manager = new DBConnectionManager();
	//** Connection Object declaration *//*
	private boolean flag = false;
	 private boolean toput=false;
	public ArrayList getArrayListOnIn(String pQryStmt, HashMap valueMap) {
		ArrayList lo_ResAList = new ArrayList();
		ResultSet lo_ResultSet1      = null;
		logger.debug("inside getArrayListOnIn() ");
		logger.debug("Query String "+pQryStmt);
		Connection moDbConn=null;
		PreparedStatement lo_PrepareStmt = null;
		try {
			moDbConn = manager.getConnection();
			moDbConn.setAutoCommit(false);
			Statement stmt = moDbConn.createStatement();
			ResultSet rs = stmt.executeQuery(createQuery(pQryStmt, valueMap));
			if(rs != null )
			{
				Object[] listMetaObj = null;
				ResultSetMetaData lo_RsMeta      = null;
				int lo_ColCount     = 0;
				int l_RowCount      = 0;
				int i=0;
				lo_ResultSet1 	 = rs;
				lo_RsMeta        = rs.getMetaData();
				lo_ColCount      = lo_RsMeta.getColumnCount();
				// Getting Meta Data for the Query
				listMetaObj = new Object[lo_ColCount];
				for ( i=0; i<lo_ColCount ; i++)	{
					listMetaObj[i] = lo_RsMeta.getColumnLabel(i+1);
				}
				lo_ResAList.add(l_RowCount++, listMetaObj.toString());

				// Getting DataPart
				while (lo_ResultSet1.next())	{
					Object[] listColumnObj = new Object[lo_ColCount];
					for ( i=0; i<lo_ColCount; i++ )   {
						listColumnObj[i] = lo_ResultSet1.getObject(i+1);
						//               logger.debug("Resuleset Value"+listColumnObj[i].toString());
					}
					lo_ResAList.add(l_RowCount++, listColumnObj);
				}
				logger.debug("Fetch from database");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fatal("Failed to fetch from database");
		}
		finally
		{

			DBUtility.closeResultSet(lo_ResultSet1);
			DBUtility.closeConnection(moDbConn);

		}
		return lo_ResAList;
	}

	public ArrayList getList(String pQryStmt) {

		ArrayList lo_ResAList = new ArrayList();
		ResultSet lo_ResultSet1      = null;
		PreparedStatement lo_PrepareStmt1 = null;
		logger.debug("inside getList() ");
		logger.debug("Query String "+pQryStmt);
		Connection moDbConn=null;
		PreparedStatement lo_PrepareStmt = null;
		try {

			moDbConn = manager.getConnection();

			moDbConn.setAutoCommit(false);

			lo_PrepareStmt1 = moDbConn.prepareStatement(pQryStmt);
		
			logger.debug("Query String Before execute "+pQryStmt);
			boolean flag1= lo_PrepareStmt1.execute();
			flag=flag1;
			logger.debug("The value of flag = " + flag);
			if(flag1)
			{
				Object[] listMetaObj = null;
				ResultSetMetaData lo_RsMeta      = null;
				int lo_ColCount     = 0;
				int l_RowCount      = 0;
				int i=0;

				lo_ResultSet1     = lo_PrepareStmt1.getResultSet();
				lo_RsMeta        = lo_ResultSet1.getMetaData();
				lo_ColCount      = lo_RsMeta.getColumnCount();

				// Getting Meta Data for the Query
				listMetaObj = new Object[lo_ColCount];
				for ( i=0; i<lo_ColCount ; i++)	{
					listMetaObj[i] = lo_RsMeta.getColumnLabel(i+1);
				}
				lo_ResAList.add(l_RowCount++, listMetaObj.toString());

				// Getting DataPart
				while (lo_ResultSet1.next())	{
					Object[] listColumnObj = new Object[lo_ColCount];
					for ( i=0; i<lo_ColCount; i++ )   {
						listColumnObj[i] = lo_ResultSet1.getObject(i+1);
						//               logger.debug("Resuleset Value"+listColumnObj[i].toString());
					}
					lo_ResAList.add(l_RowCount++, listColumnObj);
				}
				logger.debug("Fetch from database");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fatal("Failed to fetch from database");
		}
		finally
		{

			DBUtility.closeResultSet(lo_ResultSet1);
			DBUtility.closePreparedStatement(lo_PrepareStmt1);
			DBUtility.closeConnection(moDbConn);


		}
		return lo_ResAList;
	}
	
	public String createQuery(String query, HashMap valueMap) {
		StringBuilder sb = null;
		try {
			sb = new StringBuilder();
			if(valueMap != null )
			{
				sb.append(query);
				sb.append(MARSConstants.OPEN_BRACES);
				for (Iterator itr = valueMap.keySet().iterator(); itr.hasNext(); ) {
					Object key = itr.next();
					if (key instanceof Integer) {
						logger.info("HashMap key for the query="+ key.toString());
						Object value = valueMap.get(key);
						sb.append(MARSConstants.SINGLE_QUOTE);
						sb.append(value.toString());
						sb.append(MARSConstants.SINGLE_QUOTE);
						sb.append(MARSConstants.DELIMETER_COMA);
					}
				}
				sb.deleteCharAt(sb.length()-1);
				sb.append(MARSConstants.CLOSE_BRACES);
				query=sb.toString();
				logger.debug("Dynamic query " + query);
			}
			return query;
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
		
		return query;
	}

	
	public ArrayList getArrayList(String pQryStmt, HashMap valueMap) {

		ArrayList lo_ResAList = new ArrayList();
		ResultSet lo_ResultSet1      = null;
		PreparedStatement lo_PrepareStmt1 = null;
		PreparedStatement lo_PrepareStmt2 = null;
		logger.debug("inside getArrayList() ");
		logger.debug("Query String "+pQryStmt);
		Connection moDbConn=null;
		PreparedStatement lo_PrepareStmt = null;
		try {

			moDbConn = manager.getConnection();

			moDbConn.setAutoCommit(false);

			lo_PrepareStmt1 = moDbConn.prepareStatement(pQryStmt);
			

			if(valueMap != null )
			{
				for (Iterator itr = valueMap.keySet().iterator(); itr.hasNext(); ) {
					Object key = itr.next();
					if (key instanceof Integer) {
						logger.info("HashMap key for the query="+ key.toString());
						Object value = valueMap.get(key);
						lo_PrepareStmt1.setObject(((Integer) key).intValue(), value);
						logger.debug("HashMap Value for the query "+value.toString());
					}
				}
				logger.debug("Hash MAp Retrieved");
			}
			logger.debug("Query String Before execute "+pQryStmt);
			boolean flag1= lo_PrepareStmt1.execute();
			flag=flag1;
			logger.debug("The value of flag = " + flag);
			if(flag1)
			{
				Object[] listMetaObj = null;
				ResultSetMetaData lo_RsMeta      = null;
				int lo_ColCount     = 0;
				int l_RowCount      = 0;
				int i=0;

				lo_ResultSet1     = lo_PrepareStmt1.getResultSet();
				lo_RsMeta        = lo_ResultSet1.getMetaData();
				lo_ColCount      = lo_RsMeta.getColumnCount();

				// Getting Meta Data for the Query
				listMetaObj = new Object[lo_ColCount];
				for ( i=0; i<lo_ColCount ; i++)	{
					listMetaObj[i] = lo_RsMeta.getColumnLabel(i+1);
				}
				lo_ResAList.add(l_RowCount++, listMetaObj.toString());

				// Getting DataPart
				while (lo_ResultSet1.next())	{
					Object[] listColumnObj = new Object[lo_ColCount];
					for ( i=0; i<lo_ColCount; i++ )   {
						listColumnObj[i] = lo_ResultSet1.getObject(i+1);
						//               logger.debug("Resuleset Value"+listColumnObj[i].toString());
					}
					lo_ResAList.add(l_RowCount++, listColumnObj);
				}
				logger.debug("Fetch from database");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fatal("Failed to fetch from database");
		}
		finally
		{

			DBUtility.closeResultSet(lo_ResultSet1);
			DBUtility.closePreparedStatement(lo_PrepareStmt1);
			DBUtility.closeConnection(moDbConn);


		}
		return lo_ResAList;
	}

	public List getStateArrayList(String pQryStmt , Multimap<String, String> multiMap) {
		
		ArrayList lo_ResAList = new ArrayList();
		ResultSet lo_ResultSet1 = null;
	
		logger.debug("inside getStateVillageArrayList() ");
		logger.debug("Query String "+pQryStmt);
		
		Connection moDbConn=null;
		
		try {
			
			moDbConn = manager.getConnection();
	
			moDbConn.setAutoCommit(false);
	
			Statement stmt = moDbConn.createStatement();
			ResultSet rs = stmt.executeQuery(createQuery(pQryStmt, multiMap));
			
			if(rs != null )
			{
				Object[] listMetaObj = null;
				ResultSetMetaData lo_RsMeta      = null;
				int lo_ColCount     = 0;
				int l_RowCount      = 0;
				int i=0;
			
				lo_ResultSet1 	 = rs;
				lo_RsMeta        = rs.getMetaData();
				lo_ColCount      = lo_RsMeta.getColumnCount();

				// Getting Meta Data for the Query
				listMetaObj = new Object[lo_ColCount];
				for ( i=0; i<lo_ColCount ; i++)	{
					listMetaObj[i] = lo_RsMeta.getColumnLabel(i+1);
				}
				lo_ResAList.add(l_RowCount++, listMetaObj.toString());

				// Getting DataPart
				while (lo_ResultSet1.next())	{
					Object[] listColumnObj = new Object[lo_ColCount];
					for ( i=0; i<lo_ColCount; i++ )   {
						listColumnObj[i] = lo_ResultSet1.getObject(i+1);
						//               logger.debug("Resuleset Value"+listColumnObj[i].toString());
					}
					lo_ResAList.add(l_RowCount++, listColumnObj);
					
				}
				logger.debug("Fetch from database");
				logger.debug("final query = " + lo_ResAList);
			}
			
			return lo_ResAList;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			DBUtility.closeResultSet(lo_ResultSet1);
			DBUtility.closeConnection(moDbConn);
			
		}
		
		return lo_ResAList;
	}

	private String createQuery(String query, Multimap<String, String> multiMap) {

		StringBuilder statecodes = null;
		String queryLocal = query;
		StringBuilder sb = new StringBuilder();
		
		try {
			
			for (Entry<String, Collection<String>> entry : multiMap.asMap().entrySet()) {
				logger.debug("Original values: " +entry.getValue() + " was mapped to key: " + entry.getKey());
				
				int firstIn = queryLocal.indexOf("IN");
				int lastIn = queryLocal.lastIndexOf("IN");
				
				sb.append(queryLocal.substring(MARSConstants.ZERO, firstIn + MARSConstants.THREE));
				sb.append(MARSConstants.OPEN_BRACES);
				sb.append(Util.getStringSingleQuoted(entry.getKey()));
				sb.append(MARSConstants.CLOSE_BRACES);
				sb.append(queryLocal.substring(firstIn + MARSConstants.TWO, lastIn + MARSConstants.THREE));
				sb.append(MARSConstants.OPEN_BRACES);
				
				statecodes= new StringBuilder();
				
				for (String elem : entry.getValue()) {
					 statecodes.append(MARSConstants.SINGLE_QUOTE);
					 statecodes.append(elem);
					 statecodes.append(MARSConstants.SINGLE_QUOTE);
					 statecodes.append(MARSConstants.DELIMETER_COMA);
				}
				
				statecodes.deleteCharAt(statecodes.length() - MARSConstants.ONE);
				String stateCodes=statecodes.toString();
				
				sb.append(statecodes);
				sb.append(MARSConstants.CLOSE_BRACES);
				sb.append(MARSConstants.SPACE);
				sb.append(MARSConstants.UNION);
				sb.append(MARSConstants.SPACE);
			}
			
			int lastIndexUnion = sb.lastIndexOf(MARSConstants.UNION);
			queryLocal = sb.substring(MARSConstants.ZERO, lastIndexUnion).toString();
			query = queryLocal;
			
			logger.debug("final query = " + query);
			return query;
			
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
		
		return query;
	}

	
	
	public String createQueryColumnWithCriteria(String query, HashMap regionCategoryDetails, ArrayList selectedSubCategorys) {
		
		StringBuilder sb = null;
		
		try {
			sb = new StringBuilder();
			int selectIndex = query.indexOf(MARSConstants.SELECT);
			int selectIndexLen = selectIndex + MARSConstants.SELECT.length(); 

			//append the select statement
			sb.append(query.substring(selectIndex, selectIndex + MARSConstants.SELECT.length()));
			//append a space after select statement
			sb.append(MARSConstants.SPACE);
			
			//append the selected sub category's dynamically to query string 
			for(int i=0; i < selectedSubCategorys.size(); i++) {
				sb.append(MARSConstants.v + MARSConstants.DELIMETER_DOT+ selectedSubCategorys.get(i));
				sb.append(MARSConstants.DELIMETER_COMA);
				sb.append(MARSConstants.SPACE);
			}
			
			//append the rest of the column names to the query string 
			sb.append(query.substring(selectIndexLen , query.length()));
			
			String subQuery =  sb.toString();
			String subQueryCopy = subQuery;
			
			sb = null;
			sb = new StringBuilder();

			//adds criteria and also creates a UNION query
			if(regionCategoryDetails != null )
			{
				for (Iterator itr = regionCategoryDetails.keySet().iterator(); itr.hasNext(); ) {
					Object key = itr.next();
					if (key instanceof Integer) {
						
						logger.info("HashMap key for the query="+ key.toString());
						Object value = regionCategoryDetails.get(key);
						
						subQuery = subQueryCopy;
						
						subQuery = subQuery.replace("v_code", ((ArrayList)value).get(MARSConstants.ZERO).toString());
						subQuery = subQuery.replace("th_code", ((ArrayList)value).get(MARSConstants.ONE).toString());
						subQuery = subQuery.replace("d_code", ((ArrayList)value).get(MARSConstants.TWO).toString());
						subQuery = subQuery.replace("st_code", ((ArrayList)value).get(MARSConstants.THREE).toString());
						
						sb.append(subQuery);
						sb.append(MARSConstants.SPACE);
						sb.append(MARSConstants.UNION);
						sb.append(MARSConstants.SPACE);
						logger.debug("subquery "+sb);
					}
				}
				
				logger.debug(subQuery);
			}
			
			int lastIndexUnion = sb.lastIndexOf(MARSConstants.UNION);
			query = sb.substring(MARSConstants.ZERO, lastIndexUnion).toString();
	
			return query;
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			
		}
		return query;
	}
	
	
	public String createQueryColumnAppend(String query, ArrayList selectedSubCategorys, String tableName) {
		
		StringBuilder sb = null;
		
		try {
			sb = new StringBuilder();
			int selectIndex = query.indexOf(MARSConstants.SELECT);
			int selectIndexLen = selectIndex + MARSConstants.SELECT.length(); 

			//append the select statement
			sb.append(query.substring(selectIndex, selectIndex + MARSConstants.SELECT.length()));
			//append a space after select statement
			sb.append(MARSConstants.SPACE);
			
			//append the selected sub category's dynamically to query string 
			for(int i=0; i < selectedSubCategorys.size(); i++) {
				sb.append(tableName + MARSConstants.DELIMETER_DOT+ selectedSubCategorys.get(i));
				sb.append(MARSConstants.DELIMETER_COMA);
				sb.append(MARSConstants.SPACE);
			}
			
			//append the rest of the column names to the query string 
			sb.append(query.substring(selectIndexLen , query.length()));
			
			query=  sb.toString();
				
			return query;
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return query;
	}
	

	public List getArrayListResult(String query, HashMap regionCategoryDetails, ArrayList selectedSubCategorys) {

		ArrayList lo_ResAList = new ArrayList();
		ResultSet lo_ResultSet1      = null;
	
		logger.debug("inside getArrayListOnIn() ");
		logger.debug("Query String "+query);
		Connection moDbConn=null;
		PreparedStatement lo_PrepareStmt = null;
		try {

			moDbConn = manager.getConnection();

			moDbConn.setAutoCommit(false);

			Statement stmt = moDbConn.createStatement();
			ResultSet rs = stmt.executeQuery(createQueryColumnWithCriteria(query, regionCategoryDetails, selectedSubCategorys));
			
			if(rs != null )
			{
				Object[] listMetaObj = null;
				ResultSetMetaData lo_RsMeta      = null;
				int lo_ColCount     = 0;
				int l_RowCount      = 0;
				int i=0;
			
				lo_ResultSet1 	 = rs;
				lo_RsMeta        = rs.getMetaData();
				lo_ColCount      = lo_RsMeta.getColumnCount();

				// Getting Meta Data for the Query
				listMetaObj = new Object[lo_ColCount];
				for ( i=0; i<lo_ColCount ; i++)	{
					listMetaObj[i] = lo_RsMeta.getColumnLabel(i+1);
				}
				lo_ResAList.add(l_RowCount++, listMetaObj.toString());

				// Getting DataPart
				while (lo_ResultSet1.next())	{
					Object[] listColumnObj = new Object[lo_ColCount];
					for ( i=0; i<lo_ColCount; i++ )   {
						listColumnObj[i] = lo_ResultSet1.getObject(i+1);
						//               logger.debug("Resuleset Value"+listColumnObj[i].toString());
					}
					lo_ResAList.add(l_RowCount++, listColumnObj);
				}
				logger.debug("Fetch from database");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fatal("Failed to fetch from database");
		}
		finally
		{
			DBUtility.closeResultSet(lo_ResultSet1);
			DBUtility.closeConnection(moDbConn);
		}
		return lo_ResAList;
	}
	
	public Result getResultCollection(ResultSet result) {
		
		ResultSet lo_ResultSet1      = null;
		Column column = null;
		Collection<Column> columnsCollection = null;
		Collection<Rows> rowsCollection = null;
		Result result1 = null;
		Rows rows = null;
	
		
		try {

		    Properties properties = new Properties(); 
			

         File Path =new File("resources/autocomplete1.properties");
         System.out.println(Path.getAbsolutePath());
	
			properties.load(new FileInputStream(Path.getAbsolutePath()));
		 
       //    File Path =new File("D:/mars/src/com/mars/config/autocomplete1.properties");
		
       //    properties.load(new FileInputStream(Path));
            
            
            
            
			
				if(result != null )
			{
				Object[] listMetaObj = null;
				ResultSetMetaData lo_RsMeta      = null;
				int lo_ColCount     = 0;
				int l_RowCount      = 0;
				int i=0;
			
				lo_ResultSet1 	 = result;
				lo_RsMeta        = result.getMetaData();
				lo_ColCount      = lo_RsMeta.getColumnCount();

				// Getting Meta Data for the Query

				rowsCollection = new ArrayList();
				// Getting DataPart
	
				while (lo_ResultSet1.next())	{

					columnsCollection = new ArrayList();
					
					for ( i=0; i<lo_ColCount; i++ )   {

						column = new Column();
						
						
					//	column.setName(properties.getProperty(lo_RsMeta.getColumnLabel(i+1)));
						column.setName(properties.getProperty(lo_RsMeta.getColumnLabel(i+1)));
						//column.setName(lo_RsMeta.getColumnLabel(i+1));
						if( lo_ResultSet1.getObject(i+1) instanceof String ) {
							column.setStringValue((String)lo_ResultSet1.getObject(i+1));
						}else if( lo_ResultSet1.getObject(i+1) instanceof Integer ) {
							column.setIntValue((Integer)lo_ResultSet1.getObject(i+1));
						}else if( lo_ResultSet1.getObject(i+1) instanceof Double ) {
							column.setDoubleValue((Double)lo_ResultSet1.getObject(i+1));
						}
						else {
							int value=((BigDecimal)lo_ResultSet1.getObject(i+1)).intValue();
							/*if(value!=0)
							{*/
							column.setValue(((BigDecimal)lo_ResultSet1.getObject(i+1)).intValue());
							toput=true;
						/*	}
							
							else
							{
								toput=false;
								continue;
							}*/
						}
						if(toput==true)
						{
						columnsCollection.add(column);
						}
					}
					if(toput==true)
					{
					rows = new Rows();
					
					rows.setRow(columnsCollection);
					rowsCollection.add(rows);
					}
				}
				result1 = new Result();
				
				result1.setResult(rowsCollection);
				logger.debug("Fetch from database");
			}
			
			return result1;
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
			
		} 
		return result1;
	}
	
	public List getListForPDF(ResultSet result) {
		
		ArrayList lo_ResAList = new ArrayList();
		ResultSet lo_ResultSet1      = null;
		
		try {

		    Properties properties = new Properties(); 
           File Path =new File("resources/autocomplete1.properties");
			properties.load(new FileInputStream(Path.getAbsolutePath()));
		 
        //   File Path =new File("D:/mars/src/com/mars/config/autocomplete1.properties");
		
           properties.load(new FileInputStream(Path));
				if(result != null ) {
				Object[] listMetaObj = null;
				ResultSetMetaData lo_RsMeta      = null;
				int lo_ColCount     = 0;
				int l_RowCount      = 0;
				int i=0;
			
				lo_ResultSet1 	 = result;
				lo_RsMeta        = result.getMetaData();
				lo_ColCount      = lo_RsMeta.getColumnCount();

				// Getting Meta Data for the Query
				listMetaObj = new Object[lo_ColCount];
				for ( i=0; i<lo_ColCount ; i++)	{
					listMetaObj[i] = properties.getProperty(lo_RsMeta.getColumnLabel(i+1));
				}
				lo_ResAList.add(l_RowCount++, listMetaObj);

				
				// Getting DataPart
				while (lo_ResultSet1.next())	{
					Object[] listColumnObj = new Object[lo_ColCount];
					for ( i=0; i<lo_ColCount; i++ )   {
						listColumnObj[i] = lo_ResultSet1.getObject(i+1);
						//               logger.debug("Resuleset Value"+listColumnObj[i].toString());
					}
					lo_ResAList.add(l_RowCount++, listColumnObj);
				}
				logger.debug("Fetch from database");
				
			}
			
			return lo_ResAList;
		} catch(Exception e ) {
			e.printStackTrace();
			logger.error(e.getMessage());
			
		} 
		return lo_ResAList;
	}

	public String createQueryColumnAppendLast(String modifiedQuery1,HashMap details, String district) {
		// TODO Auto-generated method stub
		StringBuilder sb = null;
		StringBuilder sb2 = null;
		sb = new StringBuilder();
		sb2=new StringBuilder();
		sb.append(modifiedQuery1);
		String modifiedQuery2=sb.toString();
		if(details != null )
		{
			for (Iterator itr = details.keySet().iterator(); itr.hasNext(); ) {
				Object key = itr.next();
				if (key instanceof Integer) {
					
					logger.info("HashMap key for the query="+ key.toString());
					Object value = details.get(key);
					sb2.append(district);
					sb2.append(MARSConstants.DELIMETER_DOT);
					List detailList=(List) details.get(key);
					
					for(int i=0;i<detailList.size();i++)
					{
						String columnsDetails=(String) detailList.get(i++);
						String details2=	((String)detailList.get(i++));

						if (details2.trim().equals("<>")) {
							sb2.append(columnsDetails);
							sb2.append(MARSConstants.SPACE);
							sb2.append(">");
							String[] values = ((String)detailList.get(i)).split(":");
							sb2.append(values[0]);
							sb2.append(MARSConstants.SPACE);
							sb2.append(MARSConstants.AND);
							sb2.append(MARSConstants.SPACE);
							sb2.append(district);
							sb2.append(MARSConstants.DELIMETER_DOT);
							sb2.append(columnsDetails);
							sb2.append("<");
							sb2.append(values[1]);
							sb2.append(MARSConstants.SPACE);
							sb2.append(MARSConstants.AND);
							sb2.append(MARSConstants.SPACE);
							break;
						}
						if (details2.trim().equals("<")) {
							sb2.append(columnsDetails);
							sb2.append(details2);
							sb2.append(((String)detailList.get(i++)));
							i++;
							sb2.append(MARSConstants.SPACE);
							sb2.append(MARSConstants.AND);
							sb2.append(MARSConstants.SPACE);
							sb2.append(district);
							sb2.append(MARSConstants.DELIMETER_DOT);
							sb2.append(columnsDetails);
							sb2.append(">");
							sb2.append(0);
							sb2.append(MARSConstants.SPACE);
							sb2.append(MARSConstants.AND);
							sb2.append(MARSConstants.SPACE);
						}
						else{
			
					sb2.append(columnsDetails);
					sb2.append(details2);
					sb2.append(((String)detailList.get(i++)));
					sb2.append(MARSConstants.SPACE);
					sb2.append(MARSConstants.AND);
					sb2.append(MARSConstants.SPACE);
						

					logger.debug("subquery "+sb2);
						}
				}
			}
			}
			sb2.deleteCharAt(sb2.length()-1);
			sb2.deleteCharAt(sb2.length()-1);
			sb2.deleteCharAt(sb2.length()-1);
			sb2.deleteCharAt(sb2.length()-1);
			
String detailString=sb2.toString();
sb.append(detailString);
String detailString2=sb.toString();
return detailString2;
			
		}
		return null;
	}



	public String createQueryColumnAppendLast1(String modifiedquery4,
			HashMap details, String village) {
		// TODO Auto-generated method stub
		
		
		
		StringBuilder sb = null;
		StringBuilder sb2 = null;
		sb = new StringBuilder();
		sb2=new StringBuilder();
		sb.append(modifiedquery4);
		String modifiedQuery2=sb.toString();
		
		if(details != null )
		{
			sb2.append(MARSConstants.AND);
			for (Iterator itr = details.keySet().iterator(); itr.hasNext(); ) {
				Object key = itr.next();
				if (key instanceof Integer) {
					
					logger.info("HashMap key for the query="+ key.toString());
					Object value = details.get(key);
					
					sb2.append(MARSConstants.SPACE);
					sb2.append(village);
				
					sb2.append(MARSConstants.DELIMETER_DOT);
					List detailList=(List) details.get(key);
					
					for(int i=0;i<detailList.size();i++)
					{
							
						String columnsDetails=(String) detailList.get(i++);
						String details2=	((String)detailList.get(i++));

						if (details2.trim().equals("<>")) {
							sb2.append(columnsDetails);
							sb2.append(MARSConstants.SPACE);
							sb2.append(">");
							String[] values = ((String)detailList.get(i)).split(":");
							sb2.append(values[0]);
							sb2.append(MARSConstants.SPACE);
							sb2.append(MARSConstants.AND);
							sb2.append(MARSConstants.SPACE);
							sb2.append(village);
							sb2.append(MARSConstants.DELIMETER_DOT);
							sb2.append(columnsDetails);
							sb2.append("<");
							sb2.append(values[1]);
							sb2.append(MARSConstants.SPACE);
							sb2.append(MARSConstants.AND);
							sb2.append(MARSConstants.SPACE);
							break;
						}
						if (details2.trim().equals("<")) {
							sb2.append(columnsDetails);
							sb2.append(details2);
							sb2.append(((String)detailList.get(i++)));
							i++;
							sb2.append(MARSConstants.SPACE);
							sb2.append(MARSConstants.AND);
							sb2.append(MARSConstants.SPACE);
							sb2.append(village);
							sb2.append(MARSConstants.DELIMETER_DOT);
							sb2.append(columnsDetails);
							sb2.append(">");
							sb2.append(0);
							sb2.append(MARSConstants.SPACE);
							sb2.append(MARSConstants.AND);
							sb2.append(MARSConstants.SPACE);
						}
						else{
					sb2.append(columnsDetails);
					sb2.append(details2);
					sb2.append(((String)detailList.get(i++)));
					sb2.append(MARSConstants.SPACE);
					sb2.append(MARSConstants.AND);
					sb2.append(MARSConstants.SPACE);
						

					logger.debug("subquery "+sb2);
						}
				}
			}
			}
		
			sb2.deleteCharAt(sb2.length()-1);
			sb2.deleteCharAt(sb2.length()-1);
			sb2.deleteCharAt(sb2.length()-1);
			sb2.deleteCharAt(sb2.length()-1);
			
String detailString=sb2.toString();
sb.append(detailString);
String detailString2=sb.toString();
return detailString2;
			
		}
		return null;
	}

		
		
		
		
		
		
		
		
		

	
	
	/*public static void main(String args[]) {
		MARSQueryInteractor marsqi = new MARSQueryInteractor();
		
		ArrayList al = new ArrayList();
		al.add("al_mm_p");
		al.add("al_per_f");
		
		String retval = marsqi.createQueryColumnAppend(MARSQuery.STATE_SUB_CAT_DETAILS, al , MARSConstants.STATE);
		System.out.println("returned value is " + retval);
	}
*/
	
}
