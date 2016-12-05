package com.mars.census.servicedef;

import java.util.HashMap;
import java.util.List;

public interface DistrictDefinition {

	public List getDistrictList(HashMap stateCodes);
	public List getVillageList(HashMap stateDistrictCodes);
	
}
