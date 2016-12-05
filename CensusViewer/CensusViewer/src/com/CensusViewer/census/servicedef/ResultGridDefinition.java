package com.mars.census.servicedef;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.collect.Multimap;
import com.mars.census.model.Result;

public interface ResultGridDefinition {
	
	public Result getVillageDetailsBySubCategory(ArrayList subCategorys,Multimap<String, String> multiMap,HashMap details);
	
	public Result getStateDetailsBySubCategory(ArrayList subCategorys, HashMap stateCodes,HashMap details);
	
	public Result getStateDistrictDetailsBySubCategory(ArrayList subCategorys, HashMap stateDistrictCodes);
	
}
