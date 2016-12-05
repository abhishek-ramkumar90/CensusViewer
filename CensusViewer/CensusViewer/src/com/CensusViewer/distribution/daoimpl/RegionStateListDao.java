package com.mars.distribution.daoimpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.mars.HibernateUtility.HibernateUtil;
import com.mars.distribution.ServicePojo.RegionStateListJson;
import com.mars.distribution.model.ZoneStateListFinal;

public class RegionStateListDao {

	public Collection<RegionStateListJson> getRegionStateList(String zoneId) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query zonelist=(Query) session.createQuery("from ZoneStateListFinal where ZONE_ID='"+zoneId+"'");
		List<ZoneStateListFinal> StateList=zonelist.list(); 
		Collection<RegionStateListJson> StateCollection=new ArrayList<RegionStateListJson>(); 
		for(ZoneStateListFinal zf:StateList)
		{RegionStateListJson stateCollection=new RegionStateListJson();
			stateCollection.setState_name(zf.getStateName());
			stateCollection.setState_id(zf.getStateId());
			StateCollection.add(stateCollection);
		}
		session.close();
		return StateCollection;
		
	}

}
