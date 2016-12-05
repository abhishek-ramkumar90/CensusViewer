package com.mars.census.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.mars.census.model.Category;
import com.mars.census.services.customerservice.CategoryService;
public class CategoryDetails {
	Category category=null;
	Collection<Category> CategoryCollection = null;
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
	
	public Collection<Category> categoryDetails(String details,String stateselect,String districtselect,String tehsilselect) {
		// TODO Auto-generated method stub
		logger.info("Entering " + CLASS_NAME);
		int k = 0;
		Category cat=null;
		Collection<Category> CategoryList=new ArrayList<Category>();
		
		if(stateselect.equals("Y")&&districtselect.equals("N")&&tehsilselect.equals("N"))
		{
			 HashMap categoryCodes = new HashMap();
			 categoryCodes.put(new Integer(1),details);
			 categoryCodes.put(new Integer(2),stateselect);
             CategoryService cs=new CategoryService();
             
			 List categoryList=	cs.getStateCategoryList(categoryCodes);
			 
			 Properties properties = new Properties(); 
				

	         File Path =new File("resources/autocomplete1.properties");
	         System.out.println(Path.getAbsolutePath());
	         try {
				properties.load(new FileInputStream(Path.getAbsolutePath()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 CategoryCollection=new ArrayList<Category>();
	            
	            for(int i=1; i<categoryList.size(); i++) {
					
					Object[] ObjList=(Object[])categoryList.get(i);
					category=new Category();
					String catName=ObjList[k++].toString();
					category.setCat_name(catName);
					int catId=Integer.parseInt(ObjList[k++].toString());
					if(catId==11)
					{
						k=0;
						continue;
					}
					category.setCat_id(catId);
					category.setCat_alt( properties.getProperty(Integer.toString(catId)));
					
					k=0;
					CategoryCollection.add(category);

          	}
			 
			 
		}
			 
			 
			 
		/*List  list1 = subDao.RetreiveSubCategory(" select distinct category,cat_id from sub_category where segment='"+details+"' AND state='"+stateselect+"'"  );*/
		//List<SubcategoryTable> objectList = new ArrayList<SubcategoryTable>(list1);
		
		
/*		for(int i=0;i<list1.size();i++){
			  //  System.out.println(list1.get(i));
		       

			 cat=new Category();
			    Object[] obj = (Object[]) list1.get(i);
			    String categoryName=(String) obj[0];

	System.out.println(categoryName);
	Integer CategoryId=(Integer) obj[1];
	System.out.println(CategoryId);
			    
			    
			    
			
		   
		    cat.setCat_name(categoryName);
		    cat.setCat_id(CategoryId);
		    
		    CategoryList.add(cat);
		    
		    
		} 
		}*/
		if(stateselect.equals("Y")&&districtselect.equals("Y")&&tehsilselect.equals("N"))
		{

		/*List  list1 = subDao.RetreiveSubCategory(" select distinct category,cat_id from sub_category where segment='"+details+"' AND state='"+stateselect+"' AND district='"+districtselect+"' "  );*/
		//List<SubcategoryTable> objectList = new ArrayList<SubcategoryTable>(list1);
		
		
			 HashMap categoryCodes = new HashMap();
			 categoryCodes.put(new Integer(1),details);
			 categoryCodes.put(new Integer(2),stateselect);
			 categoryCodes.put(new Integer(3),districtselect);
             CategoryService cs=new CategoryService();
			 List categoryList=	cs.getDistrictCategoryList(categoryCodes);
			 
			 
			 CategoryCollection=new ArrayList<Category>();
	            
	            for(int i=1; i<categoryList.size(); i++) {
					
					Object[] ObjList=(Object[])categoryList.get(i);
					category=new Category();
					
					category.setCat_name(ObjList[k++].toString());
					category.setCat_id(Integer.parseInt(ObjList[k++].toString()));
					k=0;
					
					CategoryCollection.add(category);

          	}
		}
/*		if(stateselect.equals("Y")&&districtselect.equals("Y")&&tehsilselect.equals("Y"))
		{

		//List  list1 = subDao.RetreiveSubCategory(" select distinct category,cat_id from sub_category where segment='"+details+"' AND state='"+stateselect+"' AND district='"+districtselect+"' AND tehsil='"+tehsilselect+"'"  );
		//List<SubcategoryTable> objectList = new ArrayList<SubcategoryTable>(list1);
		
		
		for(int i=0;i<list1.size();i++){
			  //  System.out.println(list1.get(i));
		       

			 cat=new Category();
			    Object[] obj = (Object[]) list1.get(i);
			    String categoryName=(String) obj[0];

	System.out.println(categoryName);
	Integer CategoryId=(Integer) obj[1];
	System.out.println(CategoryId);
			    
			    
			    
			
		   
		    cat.setCat_name(categoryName);
		    cat.setCat_id(CategoryId);
		    
		    CategoryList.add(cat);
		    
		    
		} 
		}*/
		
		//Dummy implementation to return a new copy of category to 
		//avoid getting overridden by service
	return CategoryCollection;
	}
	
	

}
