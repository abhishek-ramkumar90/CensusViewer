package com.CensusViewer.distribution.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.CensusViewer.HibernateUtility.HibernateUtil;
import com.CensusViewer.distribution.ServicePojo.RegionListJson;
import com.CensusViewer.distribution.model.Region;


public class RegionListDao {

	RegionListJson regionList;
	List<RegionListJson> regionCollection;
	SessionFactory sf = HibernateUtil.getSessionFactory();
	Session session = sf.openSession();
	public Collection<RegionListJson>getRegionList(String zoneId) {

		regionCollection=new ArrayList<RegionListJson>();
		session.beginTransaction();
		Query regionlist=(Query) session.createQuery("from Region where ZONE_ID='"+zoneId+"'");
		List<Region> regionlist1=regionlist.list();
		for(Region region:regionlist1)
		{
			regionList=new RegionListJson();
			String regionName=region.getRegion();
			regionList.setRegionName(regionName);
			String regionId=regionList.getRegionId();
			regionList.setRegionId(regionId);

			regionCollection.add(regionList);
		}
		session.close();
		return regionCollection;
	}
}




