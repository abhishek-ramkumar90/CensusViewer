package com.mars.distribution.ServiceClass;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mars.HibernateUtility.HibernateUtil;
import com.mars.distribution.model.GeomClub;

public class GeomClub1 {

	public static void main(String args[])
	{
		
		 SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			 Query query = session.getNamedQuery("GeomClub");
		List<GeomClub> geomList=query.list();
		for(GeomClub g:geomList)
		{
			g.getThe_geom();
		}
	}
}
