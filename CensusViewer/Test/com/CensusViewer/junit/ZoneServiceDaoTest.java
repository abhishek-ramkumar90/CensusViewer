package com.CensusViewer.junit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.CensusViewer.distribution.ServicePojo.ZoneListJson;
import com.CensusViewer.distribution.daoimpl.ZoneListDao;

public class ZoneServiceDaoTest {

	ZoneListDao zoneListDao = new ZoneListDao();
	
	@Test
	public void getZonesTest(){
		
		String zoneDetails = "{'zoneDetails':'ZN281'}";
		Collection<ZoneListJson> zoneList = zoneListDao.getZoneList();

		List actualZoneList = new ArrayList();
		
		List expectedZoneList = new ArrayList();
		expectedZoneList.add("ZN278");
		expectedZoneList.add("ZN279");
		expectedZoneList.add("ZN280");
		expectedZoneList.add("ZN281");
		expectedZoneList.add("ZN282");
		expectedZoneList.add("ZN283");
		expectedZoneList.add("ZN284");
		expectedZoneList.add("ZN285");
		
		for(ZoneListJson zone : zoneList){
			actualZoneList.add(zone.getZoneId());
		}
		
		Assert.assertEquals(expectedZoneList, actualZoneList);		
	}
}
