package com.CensusViewer.census.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.CensusViewer.census.model.SubCategory;
import com.CensusViewer.census.services.customerservice.CategoryService;



public class SubCategoryDetails  {

	Collection<SubCategory> SubCategoryCollection=null;
	SubCategory subCat=null;
	int k = 0;
	public Collection<SubCategory>subcategoryDetails (Integer cat_id,
			String details, String stateselect, String districtselect) {
		if(stateselect.equals("Y")&&districtselect.equals("N"))
		{
			 HashMap SubCategoryCodes = new HashMap();
			 SubCategoryCodes.put(new Integer(1),cat_id);
			 SubCategoryCodes.put(new Integer(2),details);
			 SubCategoryCodes.put(new Integer(3),stateselect);
             CategoryService cs=new CategoryService();
             List subCategoryList=cs.getStateSubCategory(SubCategoryCodes);
             SubCategoryCollection=new ArrayList<SubCategory>();
		for(int i=1;i<subCategoryList.size();i++){
			 subCat=new SubCategory();
			    Object[] ObjList=(Object[]) subCategoryList.get(i);
		   subCat.setColumn_name(ObjList[k++].toString());
		    subCat.setSub_category(ObjList[k++].toString());
		    subCat.setCat_name(ObjList[k++].toString());
		    subCat.setSegment(ObjList[k++].toString());
		    subCat.setSub_cat_id(Integer.parseInt(ObjList[k++].toString()));
		    int subcatid=subCat.getSub_cat_id();
		    if(subcatid==18||subcatid==19||subcatid==158||subcatid==154||subcatid==157)
		    {
		    	k=0;
		    	continue;
		    }
		    
		    subCat.setCat_id(Integer.parseInt(ObjList[k++].toString()));
		    subCat.setState(ObjList[k++].toString());
		    subCat.setDistrict(ObjList[k++].toString());
		 //   subCat.setData_type(ObjList[k++].toString());
		  k=0;
		    SubCategoryCollection.add(subCat);
		} 
		}
		if(stateselect.equals("Y")&&districtselect.equals("Y"))
		{
			 HashMap SubCategoryCodes = new HashMap();
			 SubCategoryCodes.put(new Integer(1),cat_id);
			 SubCategoryCodes.put(new Integer(2),details);
			 SubCategoryCodes.put(new Integer(3),stateselect);
			 SubCategoryCodes.put(new Integer(4),districtselect);
             CategoryService cs=new CategoryService();
             List subCategoryList=cs.getDistrictSubCategory(SubCategoryCodes);
             SubCategoryCollection=new ArrayList<SubCategory>();
		
             SubCategoryCollection=new ArrayList<SubCategory>();
     		for(int i=1;i<subCategoryList.size();i++){
     			 subCat=new SubCategory();
     			    Object[] ObjList=(Object[]) subCategoryList.get(i);
     		   subCat.setColumn_name(ObjList[k++].toString());
     		    subCat.setSub_category(ObjList[k++].toString());
     		    subCat.setCat_name(ObjList[k++].toString());
     		    subCat.setSegment(ObjList[k++].toString());
     		    subCat.setSub_cat_id(Integer.parseInt(ObjList[k++].toString()));
     		    int subcatid=subCat.getSub_cat_id();
     		    if(subcatid==18||subcatid==19||subcatid==158||subcatid==154||subcatid==157)
     		    {
     		    	k=0;
     		    	continue;
     		    }
     		    
     		    subCat.setCat_id(Integer.parseInt(ObjList[k++].toString()));
     		    subCat.setState(ObjList[k++].toString());
     		    subCat.setDistrict(ObjList[k++].toString());
     		    subCat.setData_type(ObjList[k++].toString());
     		  k=0;
     		    SubCategoryCollection.add(subCat);
		    
		} 
		}
		
	//	}
		/*if(details.equals("r"))
		{
		
		
		if(stateselect.equals("Y")&&districtselect.equals("N"))
		{
		
			 HashMap SubCategoryCodes = new HashMap();
			 SubCategoryCodes.put(new Integer(1),cat_id);
			 SubCategoryCodes.put(new Integer(2),details);
			 SubCategoryCodes.put(new Integer(3),stateselect);
             CategoryService cs=new CategoryService();
             List subCategoryList=cs.getStateSubCategory(SubCategoryCodes);
             SubCategoryCollection=new ArrayList<SubCategory>();
		
		for(int i=1;i<subCategoryList.size();i++){
			  //  System.out.println(list1.get(i));
		       

			 subCat=new SubCategory();
			    Object[] ObjList = (Object[]) subCategoryList.get(i);
			    
			    
			
			
		   
		    subCat.setSub_category(ObjList[k++].toString());
		    subCat.setCat_name(ObjList[k++].toString());
		    subCat.setSegment(ObjList[k++].toString());
		    subCat.setSub_cat_id(Integer.parseInt(ObjList[k++].toString()));
		    subCat.setCat_id(Integer.parseInt(ObjList[k++].toString()));
		    subCat.setState(ObjList[k++].toString());
		    subCat.setTehsil(ObjList[k++].toString());
		    

k=0;
		   
		    
		    SubCategoryCollection.add(subCat);
		    
		    
		} 
		}
		if(stateselect.equals("Y")&&districtselect.equals("Y"))
		{


			String SQL_QUERY = "select distinct column_name, sub_category,sub_cat_id,cat_id,get_min_of_subcat('"+details+"', column_name) As min,get_max_of_subcat('"+details+"',column_name) As max from sub_category   where cat_id='"+cat_id+"'  AND segment='"+details+"' AND state='Y' AND district='Y'  ";

			  Query query =  session.createSQLQuery(SQL_QUERY);
			  
			  List list1 = query.list();


			
			
		//List  list1 = subDao.RetreiveSubCategory("select distinct sub_category,sub_cat_id,cat_id, from sub_category where cat_id='"+cat_id+"'AND segment='"+details+"' AND state='"+stateselect+"'"  );
		//List<SubcategoryTable> objectList = new ArrayList<SubcategoryTable>(list1);
		
		
		for(int i=0;i<list1.size();i++){
			  //  System.out.println(list1.get(i));
		       

			 subcat=new SubCategory();
			    Object[] obj = (Object[]) list1.get(i);
			    
			    
			    String column_name=(String) obj[0];
			    System.out.println(column_name);
	
	            String subCategoryName=(String) obj[1];
	            System.out.println(subCategoryName);
	            
	            
	            Integer SubCategoryId=(Integer) obj[2];
	            System.out.println(SubCategoryId);
	            
	            Integer CategoryId=(Integer) obj[3];
	            System.out.println(CategoryId);		    
			    
			    BigDecimal min=(BigDecimal) obj[4];
			    System.out.println(min);
			    
			    
			    BigDecimal max=(BigDecimal) obj[5];
			    System.out.println(max);
			
		   
		    subcat.setColumn_name(column_name);
		    subcat.setSub_category(subCategoryName);
		    subcat.setSub_cat_id(SubCategoryId);
		    subcat.setCat_id(CategoryId);
		    subcat.setMin(min);
		    subcat.setMax(max);
		    
		    
		    SubCategoryList.add(subcat);
		    
			 HashMap SubCategoryCodes = new HashMap();
			 SubCategoryCodes.put(new Integer(1),cat_id);
			 SubCategoryCodes.put(new Integer(2),details);
			 SubCategoryCodes.put(new Integer(3),stateselect);
			 SubCategoryCodes.put(new Integer(4),districtselect);
             CategoryService cs=new CategoryService();
             List subCategoryList=cs.getDistrictSubCategory(SubCategoryCodes);
             SubCategoryCollection=new ArrayList<SubCategory>();
		
		for(int i=1;i<subCategoryList.size();i++){
			  //  System.out.println(list1.get(i));
		       

			 subCat=new SubCategory();
			    Object[] ObjList = (Object[]) subCategoryList.get(i);
			    
			    
			
			
		   
		    subCat.setSub_category(ObjList[k++].toString());
		    subCat.setCat_name(ObjList[k++].toString());
		    subCat.setSegment(ObjList[k++].toString());
		    subCat.setSub_cat_id(Integer.parseInt(ObjList[k++].toString()));
		    subCat.setCat_id(Integer.parseInt(ObjList[k++].toString()));
		    subCat.setState(ObjList[k++].toString());
		    subCat.setTehsil(ObjList[k++].toString());
		    

		  k=0;
		   
		    
		    SubCategoryCollection.add(subCat);
		    
		    
		} 
		}
		
		}
		if(details.equals("b"))
		{
		
		
		if(stateselect.equals("Y")&&districtselect.equals("N"))
		{
		
			 HashMap SubCategoryCodes = new HashMap();
			 SubCategoryCodes.put(new Integer(1),cat_id);
			 SubCategoryCodes.put(new Integer(2),details);
			 SubCategoryCodes.put(new Integer(3),stateselect);
             CategoryService cs=new CategoryService();
             List subCategoryList=cs.getStateSubCategory(SubCategoryCodes);
             SubCategoryCollection=new ArrayList<SubCategory>();
		
		for(int i=1;i<subCategoryList.size();i++){
			  //  System.out.println(list1.get(i));
		       

			 subCat=new SubCategory();
		
			    
			    
			    Object[] ObjList=(Object[]) subCategoryList.get(i);
			
		   
		    subCat.setSub_category(ObjList[k++].toString());
		    subCat.setCat_name(ObjList[k++].toString());
		    subCat.setSegment(ObjList[k++].toString());
		    subCat.setSub_cat_id(Integer.parseInt(ObjList[k++].toString()));
		    subCat.setCat_id(Integer.parseInt(ObjList[k++].toString()));
		    subCat.setState(ObjList[k++].toString());
		    subCat.setTehsil(ObjList[k++].toString());
		    

		  k=0;
		   
		    
		    SubCategoryCollection.add(subCat);
		    
		    
		} 
		}
		if(stateselect.equals("Y")&&districtselect.equals("Y"))
		{


			String SQL_QUERY = "select distinct column_name, sub_category,sub_cat_id,cat_id,get_min_of_subcat('"+details+"', column_name) As min,get_max_of_subcat('"+details+"',column_name) As max from sub_category   where cat_id='"+cat_id+"'  AND segment='"+details+"' AND state='Y' AND district='Y'  ";

			  Query query =  session.createSQLQuery(SQL_QUERY);
			  
			  List list1 = query.list();


			
			
		//List  list1 = subDao.RetreiveSubCategory("select distinct sub_category,sub_cat_id,cat_id, from sub_category where cat_id='"+cat_id+"'AND segment='"+details+"' AND state='"+stateselect+"'"  );
		//List<SubcategoryTable> objectList = new ArrayList<SubcategoryTable>(list1);
		
		
		for(int i=0;i<list1.size();i++){
			  //  System.out.println(list1.get(i));
		       

			 subcat=new SubCategory();
			    Object[] obj = (Object[]) list1.get(i);
			    
			    
			    String column_name=(String) obj[0];
			    System.out.println(column_name);
	
	            String subCategoryName=(String) obj[1];
	            System.out.println(subCategoryName);
	            
	            
	            Integer SubCategoryId=(Integer) obj[2];
	            System.out.println(SubCategoryId);
	            
	            Integer CategoryId=(Integer) obj[3];
	            System.out.println(CategoryId);		    
			    
			    BigDecimal min=(BigDecimal) obj[4];
			    System.out.println(min);
			    
			    
			    BigDecimal max=(BigDecimal) obj[5];
			    System.out.println(max);
			
		   
		    subcat.setColumn_name(column_name);
		    subcat.setSub_category(subCategoryName);
		    subcat.setSub_cat_id(SubCategoryId);
		    subcat.setCat_id(CategoryId);
		    subcat.setMin(min);
		    subcat.setMax(max);
		    
		    
		    SubCategoryList.add(subcat);
		    
			 HashMap SubCategoryCodes = new HashMap();
			 SubCategoryCodes.put(new Integer(1),cat_id);
			 SubCategoryCodes.put(new Integer(2),details);
			 SubCategoryCodes.put(new Integer(3),stateselect);
			 SubCategoryCodes.put(new Integer(4),districtselect);
             CategoryService cs=new CategoryService();
             List subCategoryList=cs.getDistrictSubCategory(SubCategoryCodes);
             SubCategoryCollection=new ArrayList<SubCategory>();
		
		for(int i=1;i<subCategoryList.size();i++){
			  //  System.out.println(list1.get(i));
		       

			 subCat=new SubCategory();
			    Object[] ObjList = (Object[]) subCategoryList.get(i);
			    
			    
			
			
		   
		    subCat.setSub_category(ObjList[k++].toString());
		    subCat.setCat_name(ObjList[k++].toString());
		    subCat.setSegment(ObjList[k++].toString());
		    subCat.setSub_cat_id(Integer.parseInt(ObjList[k++].toString()));
		    subCat.setCat_id(Integer.parseInt(ObjList[k++].toString()));
		    subCat.setState(ObjList[k++].toString());
		    subCat.setTehsil(ObjList[k++].toString());
		    

		  k=0;
		   
		    
		    SubCategoryCollection.add(subCat);
		    
		    
		} 
		}
		
		}*/
		
		
		
		
		
		//Dummy implementation to return a new copy of category to 
		//avoid getting overridden by service
	return SubCategoryCollection;
	}
	
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
