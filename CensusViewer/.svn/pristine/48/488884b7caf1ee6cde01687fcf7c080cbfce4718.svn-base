package com.mars.census.Download;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mars.census.impl.StateSubcategoryResult;
import com.mars.census.model.Result;
import com.mars.census.util.Util;
import com.mars.census.services.customerservice.SolrSearchService;
import com.mars.census.services.customerservice.MapService;
import com.mars.census.model.Rows;
import com.mars.census.model.Column;



/**
 * Servlet implementation class DownloadXLS
 */
@WebServlet("/Downloadxls")
public class DownloadXLS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadXLS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked", "resource" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = null;
		JSONArray jsonArrayColumn = null;
		JSONArray jsonArrayValues = null;
		JSONArray jsonArrayValuesTemp = null;
		JSONArray jsonArrayColumnNames = null;
		
		// TODO Auto-generated method stub
		System.out.println("doPostdoPostdoPostdoPost");
		Result rs=null;
		String webMethod=request.getParameter("webMethod");
		String inputcol=request.getParameter("inputcol");
		String inputData = request.getParameter("inputData");
		
		
		System.out.println("webMethod==="+webMethod);
		System.out.println("inputcol==="+inputcol);
		System.out.println("inputData=="+inputData);
		
		String filePath= "Download.xls";
		String name;
		String statename;
		String values;	
		int k=0,i=0,col=0,row=0;
		
		if(inputData != null) {
			
			System.out.println("in inputData");
			try{ 
				Properties properties = new Properties();
				File Path =new File("resources/autocomplete1.properties");
		        System.out.println(Path.getAbsolutePath());
			
		        properties.load(new FileInputStream(Path.getAbsolutePath()));
					
				JSONParser parser = new JSONParser();
		
				Object obj = parser.parse(inputData);
				
				jsonObject = (JSONObject)obj;
				
				jsonArrayColumn = (JSONArray)jsonObject.get("columns");
				jsonArrayValues = (JSONArray)jsonObject.get("rows");
				jsonArrayColumnNames = (JSONArray)jsonObject.get("columnnames");
				
				BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
				HSSFWorkbook hwb=new HSSFWorkbook();
				HSSFSheet sheet =  hwb.createSheet("new sheet");
				
				HSSFRow rowhead=   sheet.createRow(0);
				
				//to add the column names to the PDF
				for(int k1=0; k1<jsonArrayColumnNames.size(); k1++) {
					try{rowhead.createCell(k1).setCellValue(properties.getProperty(jsonArrayColumnNames.get(k1).toString()));}catch(Exception e){rowhead.createCell(k1).setCellValue("N/A");}
				}
				 
				ArrayList<Integer> tempIndex = new ArrayList<Integer>();
				
				for(int n=0; n<jsonArrayColumnNames.size(); n++) {
					
					for(int n1=0; n1<jsonArrayColumn.size(); n1++) {
						
						if(jsonArrayColumnNames.get(n).toString().trim().equals(jsonArrayColumn.get(n1).toString().trim())) {
							tempIndex.add(new Integer(n1));
						}
					}
				}
				
				//to add the values to the PDF
				for(int k1=0; k1<jsonArrayValues.size(); k1++) {
					
					rowhead=   sheet.createRow((short)k1+1);
					jsonArrayValuesTemp = (JSONArray) jsonArrayValues.get(k1);
					for(int m=0; m<jsonArrayValuesTemp.size(); m++) {
						try{
							if(Util.isNumeric(jsonArrayValuesTemp.get(tempIndex.get(m).intValue()).toString())) {
								rowhead.createCell(m).setCellValue(new Integer(Util.cielNumber(jsonArrayValuesTemp.get(tempIndex.get(m).intValue()).toString())).toString());
							} else {
								rowhead.createCell(m).setCellValue(jsonArrayValuesTemp.get(tempIndex.get(m).intValue()).toString());
							}
						}catch(Exception e){
							rowhead.createCell(m).setCellValue("N/A");
						}
						
					}
				}

				FileOutputStream fileOut =  new FileOutputStream(filePath);
				hwb.write(fileOut);
				fileOut.close();
				out.flush();
				out.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} else {
		
		if(webMethod.contains("/SolrPlaceDetailService")){
			System.out.println("SolrPlaceDetailService");
				try{
				
					
					BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
					HSSFWorkbook hwb=new HSSFWorkbook();
					HSSFSheet sheet =  hwb.createSheet("new sheet");
					 HSSFRow rowhead=   sheet.createRow((short)row);
					List searchList=null;
				
					 HashMap villageCodes = new HashMap();
					 villageCodes.put(new Integer(1),inputcol);
					 SolrSearchService search=new SolrSearchService();
					 if(webMethod.contains("/village/SolrSearchDetails")){
							System.out.println("village");
					 searchList=	search.getVillageList(villageCodes);
			            	}
					 if(webMethod.contains("/towns/SolrSearchDetails")){
						 searchList=	search.getTownList(villageCodes);
				            	}
					 if(webMethod.contains("/districts/SolrSearchDetails")){
						 searchList=	search.getDistList(villageCodes);
				            	}
					 if(webMethod.contains("/states/SolrSearchDetails")){
						 searchList=	search.getStateList(villageCodes);
				            	}
					
					 System.out.println("searchList=="+searchList.size());
					
					 try{rowhead.createCell((short)col).setCellValue("Name");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
					 try{rowhead.createCell((short)col).setCellValue("Total Population");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Total Male Population");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					 try{rowhead.createCell((short)col).setCellValue("Total Female Population");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Literates Percentages");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Literates Percentages Male");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Literates Percentages Female");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Total Literate");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Male Literate");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Female Literate");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
			
					   for(int j=1; j< searchList.size(); j++) {
						   row++;
						   col=0;
						   rowhead=   sheet.createRow((short)row);
						Object[] object=(Object[])searchList.get(j);
						try{
							if(Util.isNumeric(object[13].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[13].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[13].toString().trim());
							}
							
						}catch(Exception e){
							rowhead.createCell((short) col).setCellValue("N/A");
						}
						col++;
						k=0;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
						}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
			}
						FileOutputStream fileOut =  new FileOutputStream(filePath);
						hwb.write(fileOut);
						fileOut.close();
						out.flush();
						out.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
			 	
			
		}
		
		else if(webMethod.contains("/MapService")){
			System.out.println("MapService");
				try{
				
					
					BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
					HSSFWorkbook hwb=new HSSFWorkbook();
					HSSFSheet sheet =  hwb.createSheet("new sheet");
					 HSSFRow rowhead=   sheet.createRow((short)row);
					List searchList=null;
					String[] Lat;
					String[] Lon;
					String[] st=inputcol.split("':'");
					Lat=st[1].split("','");
					Lon=st[2].split("','");
					 HashMap data = new HashMap();
					 data.put(new Integer(1),Float.parseFloat(Lon[0]));
					 data.put(new Integer(2),Float.parseFloat(Lat[0]));
					 MapService search=new MapService();
					 if(webMethod.contains("/MapService/District")){
							System.out.println("District");
					 searchList=	search.getDistrictSelectedData(data);
			            	}
					 if(webMethod.contains("/MapService/Tehsil")){
						 searchList=	search.getTehsilSelectedData(data);
				            	}
					 if(webMethod.contains("/MapService/Towns")){
						 searchList=	search.getTownsSelectedData(data);
				            	}
					 if(webMethod.contains("/MapService/Village")){
						 searchList=	search.getVillageSelectedData(data);
				            	}
					
					 System.out.println("searchList=="+searchList.size());
					
					 try{rowhead.createCell((short)col).setCellValue("Name");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					 col++;
					 try{rowhead.createCell((short)col).setCellValue("Total Population");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Total Male Population");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					 try{rowhead.createCell((short)col).setCellValue("Total Female Population");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Literates Percentages");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Literates Percentages Male");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Literates Percentages Female");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Total Literate");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Male Literate");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
					try{rowhead.createCell((short)col).setCellValue("Female Literate");}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
					col++;
			
					   for(int j=1; j< searchList.size(); j++) {
						   row++;
						   col=0;
						   rowhead=   sheet.createRow((short)row);
						Object[] object=(Object[])searchList.get(j);
						try{
							if(Util.isNumeric(object[11].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[11].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[11].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						k=0;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								rowhead.createCell((short)col).setCellValue(new Integer(Util.cielNumber(object[k++].toString())).toString());
							} else {
								rowhead.createCell((short)col).setCellValue(object[k++].toString().trim());
							}
							
							}catch(Exception e){rowhead.createCell((short) col).setCellValue("N/A");}
						col++;
			}
						FileOutputStream fileOut =  new FileOutputStream(filePath);
						hwb.write(fileOut);
						fileOut.close();
						out.flush();
						out.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
			 	
			
		}	else if(webMethod.contains("/ResultService")) {

			try{	
				BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
				HSSFWorkbook hwb=new HSSFWorkbook();
				HSSFSheet sheet =  hwb.createSheet("new sheet");
				
				List list = null;
				StateSubcategoryResult gridDAO = new StateSubcategoryResult();
				
				if(webMethod.contains("/state/criteria/result")){
					list = gridDAO.stateCriteriaDetailsForPDF(inputcol);
				} else if(webMethod.contains("/state/dist/town/criteria/result")) {
					list = gridDAO.distCriteriaDetailsForPDF(inputcol);
				} else if(webMethod.contains("/district/village/criteria/result")) {
					list = gridDAO.districtCriteriaDetailsForPDF(inputcol);
				}
				
				Object[] columnNames =  (Object[])list.get(0);
				 HSSFRow rowhead=   sheet.createRow(0);
				 
				for(int m=0; m<(columnNames.length-4); m++) {
					 try{rowhead.createCell(m).setCellValue(columnNames[m].toString());}catch(Exception e){rowhead.createCell(m).setCellValue("N/A");}
				}
				
				for(int n=1; n< list.size(); n++) {
					Object[] object=(Object[])list.get(n);
					int k1 = 0;
					 rowhead=   sheet.createRow((short)n);
					while(k1 < (columnNames.length-4)) {
						try{
							if(Util.isNumeric(object[k1].toString().trim())) {
								rowhead.createCell((short)k1).setCellValue(new Integer(Util.cielNumber(object[k1++].toString())).toString());
							} else {
								rowhead.createCell((short)k1).setCellValue(object[k1++].toString().trim());
							}
							
							
						}catch(Exception e){rowhead.createCell((short) k1++).setCellValue("N/A");}
					}
				}
				
				FileOutputStream fileOut =  new FileOutputStream(filePath);
				hwb.write(fileOut);
				fileOut.close();
				out.flush();
				out.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		
		else if(webMethod.contains("district")){

			try{
				
				BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
				HSSFWorkbook hwb=new HSSFWorkbook();
				HSSFSheet sheet =  hwb.createSheet("new sheet");
			    
				
				
				
				rs= StateSubcategoryResult.stateDistColumnDetails(inputcol);
				 List<Rows> key=(List<Rows>) rs.getResult();
				
				 for (Rows stcol : key) {
					 List<Column> value=(List<Column>) stcol.getRow();
					 HSSFRow rowhead=   sheet.createRow((short)i);
					 int j=0;
					 for (Column coll : value) {	
						 
						 try{ name=coll.getName();}catch(Exception e){name="NA";}
						 try{ statename=coll.getStringValue();}catch(Exception e){statename="NA";}
						 try{ values=coll.getValue().toString();}catch(Exception e){values="NA";}
								 
						 if(k==0){
							 
						for(Column col1 : value)
						{
							//System.out.println("HIIIIIIIIIIIIIIIIIII2222223333"+col1.getName().toString());
							try{
								if(Util.isNumeric(col1.getName().toString().trim())) {
									rowhead.createCell((short)j).setCellValue(new Integer(Util.cielNumber(col1.getName().toString())).toString());
								} else {
									rowhead.createCell((short)j).setCellValue(col1.getName().toString().trim());
								}
								
								//rowhead.createCell((short) j).setCellValue(col1.getName().toString());
								}catch(Exception e){rowhead.createCell((short) j).setCellValue("N/A");}
							j++; 
						}
						k++;
						i++;
						rowhead=   sheet.createRow((short)i);
						j=0;
						
						 }
						
						
						 if(statename==null ){
							 try{
								 if(Util.isNumeric(values.toString().trim())) {
										rowhead.createCell((short)j).setCellValue(new Integer(Util.cielNumber(values.toString())).toString());
									} else {
										rowhead.createCell((short)j).setCellValue(values.toString().trim());
									}
								 
								 }catch(Exception e){rowhead.createCell((short) j).setCellValue("N/A");}
							 j++; 
						 }
						
					      else{
					    	   try{rowhead.createCell((short) j).setCellValue(statename);}catch(Exception e){rowhead.createCell((short) j).setCellValue("N/A");}
					    	 j++; 
					      }
						
									 	
						}
					 i++;
					 
				 }
				
					
				FileOutputStream fileOut =  new FileOutputStream(filePath);
				hwb.write(fileOut);
				fileOut.close();
				out.flush();
				out.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
			
			
			
		}
		else{
			


			try{
				
				BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
				HSSFWorkbook hwb=new HSSFWorkbook();
				HSSFSheet sheet =  hwb.createSheet("new sheet");
			    
				
				
				
				rs= StateSubcategoryResult.stateColumnDetails(inputcol);
				 List<Rows> key=(List<Rows>) rs.getResult();
		
				 for (Rows stcol : key) {
					 List<Column> value=(List<Column>) stcol.getRow();
					 HSSFRow rowhead=   sheet.createRow((short)i);
					 int j=0;
					 for (Column coll : value) {	
						 
						 try{ name=coll.getName();}catch(Exception e){name="NA";}
						 try{ statename=coll.getStringValue();}catch(Exception e){statename="NA";}
						 try{ values=coll.getValue().toString();}catch(Exception e){values="NA";}
						/*System.out.println("name============"+name);
						System.out.println("statename==========="+statename);
						System.out.println("values==============="+values);*/
										 
						 if(k==0){
							 
						for(Column col1 : value)
						{
							System.out.println("HIIIIIIIIIIIIIIIIIII2222223333"+col1.getName().toString());
							try{
								 if(Util.isNumeric(col1.getName().toString().trim())) {
									rowhead.createCell((short)j).setCellValue(new Integer(Util.cielNumber(col1.getName().toString())).toString());
								} else {
									rowhead.createCell((short)j).setCellValue(col1.getName().toString().trim());
								}
								}catch(Exception e){rowhead.createCell((short) j).setCellValue("N/A");}
							j++; 
						}
						k++;
						i++;
						rowhead=   sheet.createRow((short)i);
						j=0;
						
						 }
						
						
						 if(statename==null ){
							 try{
								 if(Util.isNumeric(values.toString().trim())) {
										rowhead.createCell((short)j).setCellValue(new Integer(Util.cielNumber(values.toString())).toString());
									} else {
										rowhead.createCell((short)j).setCellValue(values.toString().trim());
									}
								 
								 }catch(Exception e){rowhead.createCell((short) j).setCellValue("N/A");}
							 j++; 
						 }
						
					      else{
					    	   try{ 
					    		   if(Util.isNumeric(statename.toString().trim())) {
									rowhead.createCell((short)j).setCellValue(new Integer(Util.cielNumber(statename.toString())).toString());
								} else {
									rowhead.createCell((short)j).setCellValue(statename.toString().trim());
								}
					    		   }catch(Exception e){rowhead.createCell((short) j).setCellValue("N/A");}
					    	 j++; 
					      }
						
									 	
						}
					 i++;
					 
				 }
				
					
				FileOutputStream fileOut =  new FileOutputStream(filePath);
				hwb.write(fileOut);
				fileOut.close();
				out.flush();
				out.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
			
			
			
		
			
		}
		
		}
		
	response.setContentType("application/vnd.ms-excel");
	response.setHeader("Content-Disposition", "attachment; filename=" + filePath);
	InputStream fis = null;
	OutputStream outp = response.getOutputStream();
	fis = new BufferedInputStream(new FileInputStream(filePath));
	byte[] buffer = new byte[3048];
	int bytes = -1;
	while ((bytes = fis.read(buffer, 0, buffer.length)) != -1)
	{
	   outp.write(buffer, 0, bytes);
	}
	File fileToDelete = new File(filePath);
	fileToDelete.delete();  
	outp.flush();
	outp.close();
	}

}
