package com.mars.distribution.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mars.HibernateUtility.HibernateUtil;
import com.mars.distribution.ServicePojo.ZoneListJson;
import com.mars.distribution.model.Zone;

public class ZoneListDao {
	ZoneListJson zoneList;
	List<ZoneListJson> zoneCollection;
	SessionFactory sf = HibernateUtil.getSessionFactory();
	Session session = null;
	public Collection<ZoneListJson> getZoneList() {
		try {
			session = sf.openSession();
			zoneCollection=new ArrayList<ZoneListJson>();
			session.beginTransaction();
			Query zonelist=(Query) session.createQuery("from Zone");
			List<Zone> ZoneList=zonelist.list();
			session.getTransaction().commit();
			for(Zone zone:ZoneList) {
					zoneList=new ZoneListJson();
					String zoneName=zone.getZone();
					zoneList.setZoneName(zoneName);
					String zoneId=zone.getZoneId();
					zoneList.setZoneId(zoneId);
					zoneCollection.add(zoneList);
                    Collections.sort(zoneCollection,new ZoneListJson());
			}
		} catch(Exception e ) {
			System.out.println(e.getMessage());
		} finally { 
			if(session.isOpen()) {
				session.close();
			}
		}
		return zoneCollection;
	}
}
