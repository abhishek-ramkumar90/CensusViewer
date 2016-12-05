package com.CensusViewer.distribution.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.CensusViewer.HibernateUtility.HibernateUtil;
import com.CensusViewer.distribution.ServicePojo.ZoneStateSelectedPojo;
import com.CensusViewer.distribution.model.ZoneStates;

public class SelectedStateZoneDao {

	
@SuppressWarnings("unchecked")
public Collection<ZoneStates> getStateList(String ZoneID)
{
	SessionFactory sf = HibernateUtil.getSessionFactory();
	Session session = sf.openSession();
	session.beginTransaction();

	Collection<ZoneStates> zoneStateList= new ArrayList<ZoneStates>();
	Query query=session.createQuery("from ZoneStates where ZONE_ID='"+ZoneID+"'" );
zoneStateList=query.list();

Collection<ZoneStates> zoneStateListFinal=new ArrayList<ZoneStates>();
for(ZoneStates zs:zoneStateList)
{
	zoneStateListFinal.add(zs);

}

	return zoneStateListFinal;
}
}




