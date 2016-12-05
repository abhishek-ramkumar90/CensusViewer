package com.CensusViewer.census.servicedef;

import java.util.HashMap;
import java.util.List;
/**
 * @author bhupendras
 *
 */

public interface MapDefinition {

	public List getDistrictSelectedData(HashMap selectedDistrict);
	public List getTehsilSelectedData(HashMap selectedTehsil);
	public List getTownsSelectedData(HashMap selectedTowns);
	public List getVillageSelectedData(HashMap selectedVillage);
}
