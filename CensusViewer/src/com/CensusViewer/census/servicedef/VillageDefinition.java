package com.CensusViewer.census.servicedef;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Multimap;

public interface VillageDefinition {

	public List getStateVillageList(HashMap statecodes);
	public List getStateDistrictVillageList(Multimap<String, String> multiMap);
	public List getVillageListOnStateDistrict(Multimap<String, String> multiMap);
	public List getListOfVillageDetailsOnVillId(HashMap<Integer,Integer> villIds);
	
}
