package com.CensusViewer.junit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.CensusViewer.distribution.ServicePojo.BranchJson;
import com.CensusViewer.distribution.ServicePojo.RegDistJson;
import com.CensusViewer.distribution.ServicePojo.RegionListBranch;
import com.CensusViewer.distribution.daoimpl.BranchServiceDao;

/**
 * 
 * @author parnita
 *
 */
public class BranchServiceDaoTest extends CommonUtil {
	BranchServiceDao branchServDao=new BranchServiceDao();
	@Test
	public void getRegionsTest(){
		String zoneDetails="{'zoneDetails':'ZN2812'}";
		Collection<RegionListBranch> regList=branchServDao.getRegions(zoneDetails);
		List actualRegList=new ArrayList();
		List expectedRegList=new ArrayList();
		expectedRegList.add("R218");
		expectedRegList.add("R219");
		for(RegionListBranch rg:regList){
			actualRegList.add(rg.getRegionId());
		}
		Assert.assertEquals(expectedRegList, actualRegList);		
	}
	/*//@Test
	public void getRegDistrictTest(){
		String regId="{'regionId':'R218'}";
		Collection<RegDistJson> stDists = branchServDao.getRegDistrict(regId);
		List expectedStDist = new ArrayList();
		expectedStDist.add("12122");
		if(stDists.contains(expectedStDist)){
			System.out.println("Done");
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
	}*/
	@Test
	public void getBranchDetailsTest(){
		String errMessages = new String();
		Collection<BranchJson> branchDetails = branchServDao.getBranchDetails();
		for(BranchJson br:branchDetails){
			if(br.getBranchId().equals("B64")){
				try {
					this.checkProperty(br.getBranchName(),"ABC");
					this.checkProperty(br.getRegionId(),"R218");
				} catch (Exception e) {
					errMessages += e.getMessage();
				}
			}
		}
		Assert.assertTrue(errMessages,errMessages.isEmpty());
		
	}

}
