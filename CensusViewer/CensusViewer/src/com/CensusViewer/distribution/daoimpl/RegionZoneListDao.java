package com.mars.distribution.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mars.HibernateUtility.HibernateUtil;
import com.mars.distribution.ServicePojo.ZoneRegionListJson;
import com.mars.distribution.model.Region;
import com.mars.distribution.model.ZoneListFinal;
import com.mars.distribution.model.ZoneStateListFinal;

public class RegionZoneListDao {
	public Collection<ZoneRegionListJson> getZones() {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
	//	session.beginTransaction();
		Query zonelist=(Query) session.createQuery("from ZoneListFinal where status=1");
		List<ZoneListFinal> ZoneList=zonelist.list();  
		ZoneRegionListJson znlist; 
		List<ZoneRegionListJson> znlisto=new ArrayList<ZoneRegionListJson>();
		for(ZoneListFinal zn:ZoneList) {
/*		Hibernate.initialize(zn.getRegions());*/
			znlist=new ZoneRegionListJson(); 
			String ZoneId=zn.getZoneId();
			String ZoneName=zn.getZoneName();
			int status=zn.getStatus();
			Collection<ZoneStateListFinal> zList=zn.getStates();
			System.out.println(zList.size());
			znlist.setZoneName(ZoneName);
			znlist.setZoneId(ZoneId);
			znlist.setStateDetails(zList);
			znlisto.add(znlist);
		}
		Collections.sort(znlisto,new ZoneRegionListJson());
		session.close();
		return znlisto;
	}

	public static void main(String args[]) {

		RegionZoneListDao regionZoneListDao = new RegionZoneListDao();
		regionZoneListDao.getZones();
	}
}
