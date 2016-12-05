/**
 * 
 */
package com.mars.census.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.mars.census.dao.MapDao;
import com.mars.census.model.maharashtraDistrict;



/**
 * @author bhupendras
 *
 */
@Transactional
public class MapDaoImpl implements MapDao {
	
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		
		
		
		
	}
	@Override
	public void Dist_Map_Info(maharashtraDistrict $dist_map_info) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate($dist_map_info);
	}
	@Override
	public List<maharashtraDistrict> get_Dist_Map_Info(String HQL) {
		// TODO Auto-generated method stub
		return hibernateTemplate.find(HQL);
	}

	  @Override
	  @Transactional
		public HibernateTemplate  getHibernateTemplate() {
		  
		return hibernateTemplate;
	}
	  
	  
	  
}
