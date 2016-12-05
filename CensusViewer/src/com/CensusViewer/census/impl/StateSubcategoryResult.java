package com.CensusViewer.census.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.CensusViewer.census.model.DistCriteriaDetails;
import com.CensusViewer.census.model.DistrictTownCriteria;
import com.CensusViewer.census.model.JacksonCriteriaDetails;
import com.CensusViewer.census.model.JacksonDistTownCriteriaDetails;
import com.CensusViewer.census.model.JacksonDistrictCriteriaDetails;
import com.CensusViewer.census.model.JacksonStateColumnResult;
import com.CensusViewer.census.model.JacksonStateDistColumnResult;
import com.CensusViewer.census.model.Result;
import com.CensusViewer.census.model.StateColumn;
import com.CensusViewer.census.model.StateDistColumn;
import com.CensusViewer.census.model.criteriadetails;
import com.CensusViewer.census.services.customerservice.ResultGridService;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;


public class StateSubcategoryResult {
	static String CLASS_NAME = StateSubcategoryResult.class.getName();
	static Logger logger = Logger.getLogger(CLASS_NAME);
	public static Result stateColumnDetails(String codecolumn) {
		System.out.println("CLASS_NAME============="+CLASS_NAME);
		Multimap details=null;
		HashMap stateCodes1=null;
		ArrayList columns=null;
		ResultGridService rgs=null;
		Result result = null;
		logger.info("Entering " + CLASS_NAME);

		StringBuilder statecodes = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {

			// convert user object to json string, and save to a file
			JacksonStateColumnResult statecol=mapper.readValue(codecolumn,JacksonStateColumnResult.class );
			// display to console
			System.out.println(mapper.writeValueAsString(statecol));

			List<StateColumn> list1=(List<StateColumn>) statecol.getJacksonStateColumnResult();

			for (StateColumn stcol : list1) {	  

				String stateCode1=stcol.getStatecode();
				String cols=stcol.getColumnnames();
				List<String> items1 = Arrays.asList(stateCode1.split("\\s*,\\s*"));
				List<String> items2 =Arrays.asList(cols.split("\\s*,\\s*"));
				stateCodes1 = new HashMap();
				int j=1;
				for(int i=0;i<items1.size();i++) {           
					stateCodes1.put(new Integer(j),items1.get(i));
					j++;

				}
				columns=new ArrayList();
				for(int i=0;i<items2.size();i++) {   

					columns.add(items2.get(i));

				}
			}
			rgs = new ResultGridService();
			//	result = rgs.getStateDetailsBySubCategory(columns,stateCodes1,details);
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

		return result;
	}
	public static Result stateDistColumnDetails(String codecolumn){
		System.out.println("CLASS_NAME======"+CLASS_NAME);
		HashMap stateDistCodes1=null;
		ArrayList columns=null;
		ResultGridService rgs=null;
		Result result = null;
		logger.info("Entering " + CLASS_NAME);

		StringBuilder statecodes = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {

			// convert user object to json string, and save to a file
			JacksonStateDistColumnResult statedistcol=mapper.readValue(codecolumn,JacksonStateDistColumnResult.class );
			// display to console
			System.out.println(mapper.writeValueAsString(statedistcol));
			List<StateDistColumn> list1=(List<StateDistColumn>) statedistcol.getJacksonStDistDetails();
			for (StateDistColumn stdistcol : list1) {	 

				String stateDists=stdistcol.getDistCode();
				String cols=stdistcol.getColumnnames();
				List<String> items2 =Arrays.asList(cols.split("\\s*,\\s*"));
				List<String> items1 = Arrays.asList(stateDists.split("\\s*,\\s*"));

				columns=new ArrayList();
				for(int i=0;i<items2.size();i++) {   

					columns.add(items2.get(i));

				}
				stateDistCodes1 =new HashMap();
				int j=1;
				for(int i=0;i<items1.size();i++) {           
					stateDistCodes1.put(new Integer(j),items1.get(i));
					j++;

				}

			}
			rgs = new ResultGridService();
			result = rgs.getStateDistrictDetailsBySubCategory(columns,stateDistCodes1);
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

		return result;
	}
	//function to get state level (districts) data by stcode and criteria
	public Result stateCriteriaDetails(String criteriaByState) {
		ArrayList columns=null;
		ArrayList hashDetails=null;
		HashMap stateCodes=null;
		HashMap Columns=null;
		HashMap Criteria=null;
		HashMap value=null;

		HashMap<Integer,List> details=null;
		Result result=null;
		ResultGridService rgs=new ResultGridService();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {

			// convert user object to json string, and save to a file
			JacksonCriteriaDetails statedistcol=mapper.readValue(criteriaByState,JacksonCriteriaDetails.class );
			// display to console



			System.out.println(mapper.writeValueAsString(statedistcol));
			List<criteriadetails> list1=(List<criteriadetails>) statedistcol.getJacksondetails();
			for (criteriadetails  criteriadetails : list1) {	 

				String statecodes=criteriadetails.getStcode();
				logger.info(statecodes);
				String cols=criteriadetails.getColumns();
				logger.info(cols);
				String criteria=criteriadetails.getCriteria();
				logger.info(criteria);
				String value2=criteriadetails.getValue();
				logger.info(criteria);
				List<String> items2 =Arrays.asList(statecodes.split("\\s*,\\s*"));
				List<String> items1 = Arrays.asList(cols.split("\\s*,\\s*"));
				List<String> items3 = Arrays.asList(criteria.split("\\s*,\\s*"));
				List<String> items4 = Arrays.asList(value2.split("\\s*,\\s*"));

				columns=new ArrayList();
				stateCodes=new HashMap();

				Columns=new HashMap();
				Criteria=new HashMap();
				value=new HashMap();
				details=new HashMap<Integer,List>();
				result=new Result();

				for(int i=0;i<items1.size();i++) {   

					columns.add(items1.get(i));

				}
				int j=1;
				for(int i=0;i<items2.size();i++) { 

					stateCodes.put(new Integer(j),items2.get(i));
					j++;

				}
				int k=1;
				for(int i=0;i<items1.size();i++) {           
					Columns.put(new Integer(k),items1.get(i));
					k++;

				}
				int l=1;
				for(int i=0;i<items3.size();i++) {           
					Criteria.put(new Integer(l),items3.get(i));
					l++;

				}
				int m=1;
				for(int i=0;i<items4.size();i++) {           
					value.put(new Integer(m),items4.get(i));
					m++;

				}
				int n=1;
				for(int i=0;i<items4.size();i++) { 
					hashDetails=new ArrayList();
					String colname1=(String) Columns.get(n);

					hashDetails.add(colname1);
					String criteria1=(String) Criteria.get(n);
					hashDetails.add(criteria1);
					String value1=(String) value.get(n);
					hashDetails.add(value1);

					details.put(new Integer(n),hashDetails );
					n++;
				}



			}
			result=rgs.getStateDetailsBySubCategory(columns,stateCodes,details);

			// TODO Auto-generated method stub
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
		return result;
	}

	
	//function to get state level (districts) data by stcode and criteria
		public List stateCriteriaDetailsForPDF(String criteriaByState) {
			List list = null;
			ArrayList columns=null;
			ArrayList hashDetails=null;
			HashMap stateCodes=null;
			HashMap Columns=null;
			HashMap Criteria=null;
			HashMap value=null;

			HashMap<Integer,List> details=null;
			Result result=null;
			ResultGridService rgs=new ResultGridService();

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			try {

				list = new ArrayList();
				// convert user object to json string, and save to a file
				JacksonCriteriaDetails statedistcol=mapper.readValue(criteriaByState,JacksonCriteriaDetails.class );
				// display to console



				System.out.println(mapper.writeValueAsString(statedistcol));
				List<criteriadetails> list1=(List<criteriadetails>) statedistcol.getJacksondetails();
				for (criteriadetails  criteriadetails : list1) {	 

					String statecodes=criteriadetails.getStcode();
					logger.info(statecodes);
					String cols=criteriadetails.getColumns();
					logger.info(cols);
					String criteria=criteriadetails.getCriteria();
					logger.info(criteria);
					String value2=criteriadetails.getValue();
					logger.info(criteria);
					List<String> items2 =Arrays.asList(statecodes.split("\\s*,\\s*"));
					List<String> items1 = Arrays.asList(cols.split("\\s*,\\s*"));
					List<String> items3 = Arrays.asList(criteria.split("\\s*,\\s*"));
					List<String> items4 = Arrays.asList(value2.split("\\s*,\\s*"));

					columns=new ArrayList();
					stateCodes=new HashMap();

					Columns=new HashMap();
					Criteria=new HashMap();
					value=new HashMap();
					details=new HashMap<Integer,List>();
					result=new Result();

					for(int i=0;i<items1.size();i++) {   

						columns.add(items1.get(i));

					}
					int j=1;
					for(int i=0;i<items2.size();i++) { 

						stateCodes.put(new Integer(j),items2.get(i));
						j++;

					}
					int k=1;
					for(int i=0;i<items1.size();i++) {           
						Columns.put(new Integer(k),items1.get(i));
						k++;

					}
					int l=1;
					for(int i=0;i<items3.size();i++) {           
						Criteria.put(new Integer(l),items3.get(i));
						l++;

					}
					int m=1;
					for(int i=0;i<items4.size();i++) {           
						value.put(new Integer(m),items4.get(i));
						m++;

					}
					int n=1;
					for(int i=0;i<items4.size();i++) { 
						hashDetails=new ArrayList();
						String colname1=(String) Columns.get(n);

						hashDetails.add(colname1);
						String criteria1=(String) Criteria.get(n);
						hashDetails.add(criteria1);
						String value1=(String) value.get(n);
						hashDetails.add(value1);

						details.put(new Integer(n),hashDetails );
						n++;
					}



				}
				list=rgs.getStateDetailsBySubCategoryForPDF(columns,stateCodes,details);

				// TODO Auto-generated method stub
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
			return list;
		}
		
		
	public Result distCriteriaDetails(String criteriaByDist) {
		ArrayList columns=null;
		ArrayList hashDetails=null;
		HashMap stateDistCodes=null;
		HashMap Columns=null;
		HashMap Criteria=null;
		HashMap value=null;
		HashMap<Integer,List> details=null;
		Result result=null;
		ResultGridService rgs=new ResultGridService();;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {

			// convert user object to json string, and save to a file
			JacksonDistTownCriteriaDetails statedistcol=mapper.readValue(criteriaByDist,JacksonDistTownCriteriaDetails.class );
			// display to console



			System.out.println(mapper.writeValueAsString(statedistcol));
			List<DistCriteriaDetails> list1=(List<DistCriteriaDetails>) statedistcol.getJacksondetails();
			for (DistCriteriaDetails  criteriadetails : list1) {	 

				String statecodes=criteriadetails.getStateDistrict();
				logger.info(statecodes);
				String cols=criteriadetails.getColumns();
				logger.info(cols);
				String criteria=criteriadetails.getCriteria();
				logger.info(criteria);
				String value2=criteriadetails.getValue();
				logger.info(criteria);
				List<String> items2 =Arrays.asList(statecodes.split("\\s*,\\s*"));
				List<String> items1 = Arrays.asList(cols.split("\\s*,\\s*"));
				List<String> items3 = Arrays.asList(criteria.split("\\s*,\\s*"));
				List<String> items4 = Arrays.asList(value2.split("\\s*,\\s*"));

				columns=new ArrayList();
				stateDistCodes=new HashMap();

				Columns=new HashMap();
				Criteria=new HashMap();
				value=new HashMap();
				details=new HashMap<Integer,List>();
				result=new Result();

				for(int i=0;i<items1.size();i++) {   

					columns.add(items1.get(i));

				}
				int j=1;
				for(int i=0;i<items2.size();i++) { 

					stateDistCodes.put(new Integer(j),items2.get(i));
					j++;

				}
				int k=1;
				for(int i=0;i<items1.size();i++) {           
					Columns.put(new Integer(k),items1.get(i));
					k++;

				}
				int l=1;
				for(int i=0;i<items3.size();i++) {           
					Criteria.put(new Integer(l),items3.get(i));
					l++;

				}
				int m=1;
				for(int i=0;i<items4.size();i++) {           
					value.put(new Integer(m),items4.get(i));
					m++;

				}
				int n=1;
				for(int i=0;i<items4.size();i++) { 
					hashDetails=new ArrayList();
					String colname1=(String) Columns.get(n);

					hashDetails.add(colname1);
					String criteria1=(String) Criteria.get(n);
					hashDetails.add(criteria1);
					String value1=(String) value.get(n);
					hashDetails.add(value1);

					details.put(new Integer(n),hashDetails );
					n++;
				}



			}
			result=rgs.getTownDetailsByDistSubCatCriteria(columns,stateDistCodes,details);


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
		return result;
	}

	
	public List distCriteriaDetailsForPDF(String criteriaByDist) {
		ArrayList columns=null;
		ArrayList hashDetails=null;
		HashMap stateDistCodes=null;
		HashMap Columns=null;
		HashMap Criteria=null;
		HashMap value=null;
		HashMap<Integer,List> details=null;
		List list=null;
		ResultGridService rgs = new ResultGridService();;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {

			// convert user object to json string, and save to a file
			JacksonDistTownCriteriaDetails statedistcol=mapper.readValue(criteriaByDist,JacksonDistTownCriteriaDetails.class );
			// display to console



			System.out.println(mapper.writeValueAsString(statedistcol));
			List<DistCriteriaDetails> list1=(List<DistCriteriaDetails>) statedistcol.getJacksondetails();
			for (DistCriteriaDetails  criteriadetails : list1) {	 

				String statecodes=criteriadetails.getStateDistrict();
				logger.info(statecodes);
				String cols=criteriadetails.getColumns();
				logger.info(cols);
				String criteria=criteriadetails.getCriteria();
				logger.info(criteria);
				String value2=criteriadetails.getValue();
				logger.info(criteria);
				List<String> items2 =Arrays.asList(statecodes.split("\\s*,\\s*"));
				List<String> items1 = Arrays.asList(cols.split("\\s*,\\s*"));
				List<String> items3 = Arrays.asList(criteria.split("\\s*,\\s*"));
				List<String> items4 = Arrays.asList(value2.split("\\s*,\\s*"));

				columns=new ArrayList();
				stateDistCodes=new HashMap();

				Columns=new HashMap();
				Criteria=new HashMap();
				value=new HashMap();
				details=new HashMap<Integer,List>();
				list=new ArrayList();

				for(int i=0;i<items1.size();i++) {   

					columns.add(items1.get(i));

				}
				int j=1;
				for(int i=0;i<items2.size();i++) { 

					stateDistCodes.put(new Integer(j),items2.get(i));
					j++;

				}
				int k=1;
				for(int i=0;i<items1.size();i++) {           
					Columns.put(new Integer(k),items1.get(i));
					k++;

				}
				int l=1;
				for(int i=0;i<items3.size();i++) {           
					Criteria.put(new Integer(l),items3.get(i));
					l++;

				}
				int m=1;
				for(int i=0;i<items4.size();i++) {           
					value.put(new Integer(m),items4.get(i));
					m++;

				}
				int n=1;
				for(int i=0;i<items4.size();i++) { 
					hashDetails=new ArrayList();
					String colname1=(String) Columns.get(n);

					hashDetails.add(colname1);
					String criteria1=(String) Criteria.get(n);
					hashDetails.add(criteria1);
					String value1=(String) value.get(n);
					hashDetails.add(value1);

					details.put(new Integer(n),hashDetails );
					n++;
				}



			}
			list = rgs.getTownDetailsByDistSubCatCriteriaForPDF(columns,stateDistCodes,details);


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
		return list;
	}

	

	public Result districtCriteriaDetails(String criteriaByDistrict) {
		// TODO Auto-generated method stub
		ArrayList Columns=new ArrayList();
		ArrayList hashDetails=null;
		Multimap<String, String> multiMap = HashMultimap.create();
		HashMap Columns2=new HashMap();
		HashMap Criteria=new HashMap();
		HashMap value3=new HashMap();
		Result result=null;
		ResultGridService rgs=new ResultGridService();
		HashMap<Integer,List> details=new HashMap<Integer,List>() ;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {

			// convert user object to json string, and save to a file
			JacksonDistrictCriteriaDetails districtTownCriteria=mapper.readValue(criteriaByDistrict,JacksonDistrictCriteriaDetails.class );
			// display to console
			System.out.println(mapper.writeValueAsString(districtTownCriteria));


			List<DistrictTownCriteria> list1=(List<DistrictTownCriteria>) districtTownCriteria.getJacksondetails();

			for (DistrictTownCriteria Distcrit : list1) { 

				String DistrictCode=Distcrit.getStateDistrict();
				String columns=Distcrit.getColumns();
				String criteria=Distcrit.getCriteria();
				String value2=Distcrit.getValue();

				logger.info( Distcrit.getStateDistrict());

				List<String> items1 = Arrays.asList(DistrictCode.split(","));
				List<String> items2 = Arrays.asList(columns.split(","));
				List<String> items3 = Arrays.asList(criteria.split(","));
				List<String> items4 = Arrays.asList(value2.split(","));




				for(int j=0;j<items1.size();j++){



					String[] parts = items1.get(j).split(":");
					String value = parts[0];
					String key = parts[1];
					multiMap.put( key, value);


				}

				for(int j=0;j<items2.size();j++){



					Columns.add(items2.get(j));


				}
				int k=1;
				for(int i=0;i<items2.size();i++) {           
					Columns2.put(new Integer(k),items2.get(i));
					k++;

				}
				int l=1;
				for(int i=0;i<items3.size();i++) {           
					Criteria.put(new Integer(l),items3.get(i));
					l++;

				}
				int m=1;
				for(int i=0;i<items4.size();i++) {           
					value3.put(new Integer(m),items4.get(i));
					m++;

				}
				int n=1;
				for(int i=0;i<items4.size();i++) { 
					hashDetails=new ArrayList();
					String colname1=(String) Columns2.get(n);

					hashDetails.add(colname1);
					String criteria1=(String) Criteria.get(n);
					hashDetails.add(criteria1);
					String value1=(String) value3.get(n);
					hashDetails.add(value1);

					details.put(new Integer(n),hashDetails );
					n++;
				}

			}
			result=rgs.getVillageDetailsBySubCategory(Columns, multiMap,details);
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
		return result;

	}
	
	public List districtCriteriaDetailsForPDF(String criteriaByDistrict) {
		// TODO Auto-generated method stub
		ArrayList Columns=new ArrayList();
		ArrayList hashDetails=null;
		Multimap<String, String> multiMap = HashMultimap.create();
		HashMap Columns2=new HashMap();
		HashMap Criteria=new HashMap();
		HashMap value3=new HashMap();
		List list=null;
		ResultGridService rgs=new ResultGridService();
		HashMap<Integer,List> details=new HashMap<Integer,List>() ;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {

			// convert user object to json string, and save to a file
			JacksonDistrictCriteriaDetails districtTownCriteria=mapper.readValue(criteriaByDistrict,JacksonDistrictCriteriaDetails.class );
			// display to console
			System.out.println(mapper.writeValueAsString(districtTownCriteria));


			List<DistrictTownCriteria> list1=(List<DistrictTownCriteria>) districtTownCriteria.getJacksondetails();

			for (DistrictTownCriteria Distcrit : list1) { 

				String DistrictCode=Distcrit.getStateDistrict();
				String columns=Distcrit.getColumns();
				String criteria=Distcrit.getCriteria();
				String value2=Distcrit.getValue();

				logger.info( Distcrit.getStateDistrict());

				List<String> items1 = Arrays.asList(DistrictCode.split(","));
				List<String> items2 = Arrays.asList(columns.split(","));
				List<String> items3 = Arrays.asList(criteria.split(","));
				List<String> items4 = Arrays.asList(value2.split(","));




				for(int j=0;j<items1.size();j++){



					String[] parts = items1.get(j).split(":");
					String value = parts[0];
					String key = parts[1];
					multiMap.put( key, value);


				}

				for(int j=0;j<items2.size();j++){



					Columns.add(items2.get(j));


				}
				int k=1;
				for(int i=0;i<items2.size();i++) {           
					Columns2.put(new Integer(k),items2.get(i));
					k++;

				}
				int l=1;
				for(int i=0;i<items3.size();i++) {           
					Criteria.put(new Integer(l),items3.get(i));
					l++;

				}
				int m=1;
				for(int i=0;i<items4.size();i++) {           
					value3.put(new Integer(m),items4.get(i));
					m++;

				}
				int n=1;
				for(int i=0;i<items4.size();i++) { 
					hashDetails=new ArrayList();
					String colname1=(String) Columns2.get(n);

					hashDetails.add(colname1);
					String criteria1=(String) Criteria.get(n);
					hashDetails.add(criteria1);
					String value1=(String) value3.get(n);
					hashDetails.add(value1);

					details.put(new Integer(n),hashDetails );
					n++;
				}

			}
			list=rgs.getVillageDetailsBySubCategoryForPDF(Columns, multiMap,details);
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
		return list;

	}
}