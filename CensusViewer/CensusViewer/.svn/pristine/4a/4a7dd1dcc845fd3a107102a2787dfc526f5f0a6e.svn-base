package com.mars.census.Download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mars.census.model.Result;
import com.mars.census.util.Util;
import com.mars.census.services.customerservice.SolrSearchService;
import com.mars.census.services.customerservice.MapService;
import com.mars.census.impl.StateSubcategoryResult;
import com.mars.census.model.Rows;
import com.mars.census.model.Column;


/**
 * @author bhupendras
 *
 */

/**
 * Servlet implementation class DownloadPDF
 */
@WebServlet("/Downloadpdf")
public class DownloadPDF extends HttpServlet {
	JSONObject jsonObject = null;
	JSONArray jsonArrayColumn = null;
	JSONArray jsonArrayValues = null;
	JSONArray jsonArrayColumnNames = null;
	JSONArray jsonArrayValuesTemp = null;
	private static final long serialVersionUID = 1L;
       
    public DownloadPDF() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@SuppressWarnings({"rawtypes", "unchecked"})
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Result rs=null;
		 String webMethod=request.getParameter("webMethod");
			String inputcol=request.getParameter("inputcol");
			String inputData = request.getParameter("inputData");

			System.out.println("webMethod=="+webMethod);
			System.out.println("inputcol=="+inputcol);
			System.out.println("inputData=="+inputData);
		
		String filePath="Download.pdf";
		PdfPTable table =null;
		response.setContentType("application/pdf"); // Code 1
		response.setHeader("Content-Disposition","attachment; filename=" + filePath);
		Document document = new Document(PageSize.A2,5,5,10,10);
		
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
				
				PdfWriter.getInstance(document,response.getOutputStream());
				document.open();
				
				table= new PdfPTable(jsonArrayColumn.size());
				
				//to add the column names to the PDF
				for(int k=0; k<jsonArrayColumnNames.size(); k++) {
					table.addCell(properties.getProperty(jsonArrayColumnNames.get(k).toString()));
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
				for(int k=0; k<jsonArrayValues.size(); k++) {
					
					jsonArrayValuesTemp = (JSONArray) jsonArrayValues.get(k);
					
					for(int m=0; m<jsonArrayValuesTemp.size(); m++) {
						try{
							
							if(Util.isNumeric(jsonArrayValuesTemp.get(tempIndex.get(m).intValue()).toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(jsonArrayValuesTemp.get(tempIndex.get(m).intValue()).toString().trim())).toString()) ;
							} else {
								table.addCell(jsonArrayValuesTemp.get(tempIndex.get(m).intValue()).toString().trim());
							}
						/*	if(Util.isNumeric(jsonArrayValuesTemp.get(tempIndex.get(m).intValue()).toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(jsonArrayValuesTemp.get(tempIndex.get(m).intValue()).toString().trim())).toString()) ;
							}*/
							
						}catch(Exception e){
							e.printStackTrace(); table.addCell("N/A"); 
						}
					}
				}

				document.add(table);		
				document.close();
				
			} catch(com.lowagie.text.DocumentException e){
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} else {
		
		if(webMethod.contains("/SolrPlaceDetailService")){
			System.out.println("SolrPlaceDetailService");
				try{
				 PdfWriter.getInstance(document,response.getOutputStream()); // Code 2
					document.open();
					List searchList=null;
					
					
					int k = 0;
					 HashMap datainput = new HashMap();
					 datainput.put(new Integer(1),inputcol);
					 SolrSearchService search=new SolrSearchService();
					 if(webMethod.contains("/village/SolrSearchDetails")){
							System.out.println("village");
					 searchList=	search.getVillageList(datainput);
			            	}
					 if(webMethod.contains("/towns/SolrSearchDetails")){
						 searchList=	search.getTownList(datainput);
				            	}
					 if(webMethod.contains("/districts/SolrSearchDetails")){
						 searchList=	search.getDistList(datainput);
				            	}
					 if(webMethod.contains("/states/SolrSearchDetails")){
						 searchList=	search.getStateList(datainput);
				            	}
					
					 System.out.println("searchList=="+searchList.size());
					 table= new PdfPTable(10);
					 
					
					 table.addCell("Name");
					table.addCell("Total Population");
					table.addCell("Total Male Population");
					table.addCell("Total Female Population");
					table.addCell("Literates Percentages");
					table.addCell("Literates Percentages Male");
					table.addCell("Literates Percentages Female");
					table.addCell("Total Literate");
					table.addCell("Male Literate");
					table.addCell("Female Literate");
		
					
				
					   for(int i=1; i< searchList.size(); i++) {
						Object[] object=(Object[])searchList.get(i);
						try{
							if(Util.isNumeric(object[13].toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(object[13].toString().trim())).toString()) ;
							} else {
								table.addCell(object[13].toString().trim()) ;
							}
							
							}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
						k=0;
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
							} else {
								table.addCell(object[k++].toString().trim()) ;
							}
							
						}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
							} else {
								table.addCell(object[k++].toString().trim()) ;
							}
						}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
							} else {
								table.addCell(object[k++].toString().trim()) ;
							}
							//table.addCell(object[k++].toString()) ;
						}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
							} else {
								table.addCell(object[k++].toString().trim()) ;
							}
						}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
							} else {
								table.addCell(object[k++].toString().trim()) ;
							}
						}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
							} else {
								table.addCell(object[k++].toString().trim()) ;
							}
						}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
							} else {
								table.addCell(object[k++].toString().trim()) ;
							}
						}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
							} else {
								table.addCell(object[k++].toString().trim()) ;
							}
						}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
						try{
							if(Util.isNumeric(object[k].toString().trim())) {
								table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
							} else {
								table.addCell(object[k++].toString().trim()) ;
							}
						}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
						
						
					}
					
				 document.add(table);		
				document.close(); 
			
			 
			 }catch(com.lowagie.text.DocumentException e){
					e.printStackTrace();
				}catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				
			 	
			
		} else if(webMethod.contains("/MapService")){
		System.out.println("MapService");
			try{
			 PdfWriter.getInstance(document,response.getOutputStream()); // Code 2
				document.open();
				List searchList=null;
				int k = 0;
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
				 table= new PdfPTable(10);
				
				 table.addCell("Name");
				table.addCell("Total Population");
				table.addCell("Total Male Population");
				table.addCell("Total Female Population");
				table.addCell("Literates Percentages");
				table.addCell("Literates Percentages Male");
				table.addCell("Literates Percentages Female");
				table.addCell("Total Literate");
				table.addCell("Male Literate");
				table.addCell("Female Literate");
	
				
			
				   for(int i=1; i< searchList.size(); i++) {
					Object[] object=(Object[])searchList.get(i);
					try{
						if(Util.isNumeric(object[11].toString().trim())) {
							table.addCell(new Integer(Util.cielNumber(object[11].toString().trim())).toString()) ;
						} else {
							table.addCell(object[11].toString().trim()) ;
						}
					}catch(Exception e) {
						e.printStackTrace(); 
						table.addCell("N/A"); 
					}
					k=0;
					try{
						if(Util.isNumeric(object[k].toString().trim())) {
							table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
						} else {
							table.addCell(object[k++].toString().trim()) ;
						}
					}catch(Exception e) {
						e.printStackTrace(); 
						table.addCell("N/A"); 
					}
					try{
						if(Util.isNumeric(object[k].toString().trim())) {
							table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
						} else {
							table.addCell(object[k++].toString().trim()) ;
						}
					}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
					try{
						if(Util.isNumeric(object[k].toString().trim())) {
							table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
						} else {
							table.addCell(object[k++].toString().trim()) ;
						}
					}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
					try{
						if(Util.isNumeric(object[k].toString().trim())) {
							table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
						} else {
							table.addCell(object[k++].toString().trim()) ;
						}
					}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
					try{
						if(Util.isNumeric(object[k].toString().trim())) {
							table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
						} else {
							table.addCell(object[k++].toString().trim()) ;
						}
					}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
					try{
						if(Util.isNumeric(object[k].toString().trim())) {
							table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
						} else {
							table.addCell(object[k++].toString().trim()) ;
						}
					}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
					try{
						if(Util.isNumeric(object[k].toString().trim())) {
							table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
						} else {
							table.addCell(object[k++].toString().trim()) ;
						}
					}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
					try{
						if(Util.isNumeric(object[k].toString().trim())) {
							table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
						} else {
							table.addCell(object[k++].toString().trim()) ;
						}
					}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
					try{
						if(Util.isNumeric(object[k].toString().trim())) {
							table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
						} else {
							table.addCell(object[k++].toString().trim()) ;
						}
					}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
					
					
				}
				
			 document.add(table);		
			document.close(); 
		
		 
		 }catch(com.lowagie.text.DocumentException e){
				e.printStackTrace();
			}catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			
		 	
		
	} else if(webMethod.contains("/ResultService")) {
	System.out.println("ResultService");
		try{	
			PdfWriter.getInstance(document,response.getOutputStream()); // Code 2
			document.open();
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
			table= new PdfPTable(columnNames.length-4);
			for(int i=0; i<(columnNames.length-4); i++) {
				 table.addCell(columnNames[i].toString());
			}
			
			for(int i=1; i< list.size(); i++) {
				Object[] object=(Object[])list.get(i);
				int k = 0;
				while(k < (columnNames.length-4)) {
					//System.out.println("object val - " + object[k++].toString());
					try {
						if(Util.isNumeric(object[k].toString())) {
							table.addCell(new Integer(Util.cielNumber(object[k++].toString().trim())).toString()) ;
						} else {
							table.addCell(object[k++].toString().trim());
						}
						//table.addCell(object[k++].toString()) ;
					} catch(Exception e) {
						e.printStackTrace(); 
						table.addCell("N/A"); 
					}
				}
			}
			
			document.add(table);		
			document.close(); 
		}catch(com.lowagie.text.DocumentException e){
			e.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
	} else if(webMethod.contains("district")){
	
		System.out.println("district");
		try{
			 PdfWriter.getInstance(document,response.getOutputStream()); // Code 2
				document.open();
				String statename;
				String values;
			rs= StateSubcategoryResult.stateDistColumnDetails(inputcol);
		 List<Rows> key=(List<Rows>) rs.getResult();
		 int k=0;
		 for (Rows stcol : key) {
		
			 List<Column> list2=(List<Column>) stcol.getRow();
			 if(k==0){table= new PdfPTable(list2.size());}
			 for (Column col : list2) {	
				 
				
				 try{ statename=col.getStringValue();}catch(Exception e){statename="NA";}
				 try{ values=col.getValue().toString();}catch(Exception e){values="NA";}
				if(k==0){
				for(Column col1 : list2)
				{
					//if(k<list2.size())
					try{table.addCell(col1.getName());}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
					
				}
				}
				k++;
				 if(statename==null ){
					try {
						if(Util.isNumeric(values.trim())) {
							table.addCell(new Integer(Util.cielNumber(values.trim())).toString()) ;
						} else {
							table.addCell(values.trim()) ;
						}
						
						/*if(Util.isNumeric(values.trim())) {
							table.addCell(new Integer(values.trim()).toString()) ;
						}*/
						// table.addCell(values);
					}catch(Exception e) {
						System.out.println("33333333");e.printStackTrace();table.addCell("N/A");
					}
				 }
			      else
					 try{table.addCell(statename);}catch(Exception e){System.out.println("2222222");e.printStackTrace();table.addCell("N/A");}
				
				
					
				// Code 4
				
				
					
				}
			}
	
		 document.add(table);		
			document.close(); 
		
		 
		 }catch(com.lowagie.text.DocumentException e){
				e.printStackTrace();
			}catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
	
	} else {
	
		System.out.println("else");
		try{
			 PdfWriter.getInstance(document,response.getOutputStream()); // Code 2
				document.open();
				String statename;
				String values;
			rs= StateSubcategoryResult.stateColumnDetails(inputcol);
		 List<Rows> key=(List<Rows>) rs.getResult();
		 int k=0;
		 for (Rows stcol : key) {
		
			 List<Column> value=(List<Column>) stcol.getRow();
			 if(k==0){table= new PdfPTable(value.size());}
			 for (Column col : value) {	
				
				 try{ statename=col.getStringValue();}catch(Exception e){statename="NA";}
				 try{ values=col.getValue().toString();}catch(Exception e){values="NA";}
				if(k==0){
				for(Column col1 : value)
				{
					//if(k<value.size())
					try{table.addCell(col1.getName());}catch(Exception e){e.printStackTrace(); table.addCell("N/A"); }
					
				}
				}
				k++;
				 if(statename==null ){
					try{
						if(Util.isNumeric(values.trim())) {
							table.addCell(new Integer(Util.cielNumber(values.trim())).toString()) ;
						} else {
							table.addCell(values.trim()) ;
						}
						
					}catch(Exception e){
						System.out.println("33333333");e.printStackTrace();table.addCell("N/A");
					}
				 }
			      else
					 try{table.addCell(statename);}catch(Exception e){System.out.println("2222222");e.printStackTrace();table.addCell("N/A");}
				
				
					
				// Code 4
				
				
					
				}
			}
	
		 document.add(table);		
			document.close(); 
		
		 
		 }catch(com.lowagie.text.DocumentException e){
				e.printStackTrace();
			}catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
	}
	
	}

		
		
		
		
	}

}
