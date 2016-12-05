package com.mars.census.servicedef;

import java.util.HashMap;
import java.util.List;

public interface CategoryDefinition {
	
	public List getStateCategoryList(HashMap stateSelect);
	
	public List getDistrictCategoryList(HashMap districtSelect);
	
	public List getStateSubCategory(HashMap categoryDetails);
	
	public List getDistrictSubCategory(HashMap categoryDetails);
}
