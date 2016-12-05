package com.mars.junit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.mars.distribution.daoimpl.TerritoryServiceDao;
import com.mars.distribution.ServicePojo.BranchListTerrJson;
import com.mars.distribution.ServicePojo.VillageListJson;

public class TerritoryServiceDaoTest {

	TerritoryServiceDao territoryServDao=new TerritoryServiceDao();
	@Test
	public void getBranchesTest(){
		String regId="{'regId':'R218'}";
		Collection<BranchListTerrJson> regList=territoryServDao.getBranches(regId);

		List actualBranchList=new ArrayList();

		List expectedBranchList=new ArrayList();
		expectedBranchList.add("B64");

		for(BranchListTerrJson rg : regList){
			actualBranchList.add(rg.getBranchId());
		}

		Assert.assertEquals(expectedBranchList, actualBranchList);		
	}
	
	@Test
	public void getVillagesOnBranchTest(){
		
		String branchId="{'branchId':'B64'}";
		Collection<VillageListJson> villageList = territoryServDao.getVillageList(branchId);

		List actualBranchList=new ArrayList();

		List expectedBranchList=new ArrayList();
		expectedBranchList.add("5");
		expectedBranchList.add("5");
		expectedBranchList.add("5");
		expectedBranchList.add("5");
		expectedBranchList.add("5");
		expectedBranchList.add("5");
		expectedBranchList.add("5");
		expectedBranchList.add("5");
		expectedBranchList.add("5");
		expectedBranchList.add("5");
		expectedBranchList.add("5");
		
		//expectedBranchList.add("41765");

		for(VillageListJson rg : villageList){
			actualBranchList.add(rg.getDistId());
		}

		Assert.assertEquals(expectedBranchList, actualBranchList);		
	}
}
