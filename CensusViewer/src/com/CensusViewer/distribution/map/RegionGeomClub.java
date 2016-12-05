package com.CensusViewer.distribution.map;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.CensusViewer.HibernateUtility.HibernateUtil;
import com.CensusViewer.distribution.model.Region;
public class RegionGeomClub {
	public void saveRegionGeom(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		try{
			session = sf.openSession();
			session.beginTransaction();
			String sql = "SELECT st_AsText((multi(ST_Union(the_geom)))) as the_geom from district where distcode in('27','28') ";
			//String sql = "SELECT multi(ST_Union(the_geom)) as the_geom from district where distcode in('27','28') ";
			SQLQuery query = session.createSQLQuery(sql);
			//query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.addEntity(Geometryall.class);
			List results = query.list();
			for (Iterator iterator = 
					results.iterator(); iterator.hasNext();){
				Geometryall geom = (Geometryall) iterator.next(); 
     			System.out.print("First Name: " + geom.getThe_geom()); 
			}	
			 			//System.out.println("results=="+results);

			/*for(Object object : results)
			{
				Map row = (Map)object;
				System.out.print("GEOM Value: " + row.get("the_geom")); 
				// System.out.println(", Salary: " + row.get("salary")); 
			}
*/
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
	}
	public static void main(String[] args){
		RegionGeomClub rg=new RegionGeomClub();
		rg.saveRegionGeom();
	}

}

