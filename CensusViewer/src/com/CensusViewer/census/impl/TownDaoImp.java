package com.CensusViewer.census.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;



public class TownDaoImp implements com.CensusViewer.census.dao.TownDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	
	public List retreiveTownList(String HQL) {
		// TODO Auto-generated method stub
		return hibernateTemplate.find(HQL);
	}

	
	public  List townDetails()
	{
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"config/spring-configuration.xml");
		TownDaoImp townDao = (TownDaoImp)appContext.getBean("townDao");
		List list = townDao.retreiveTownList("from towns ");
            return list;
	}
	
	
	

}

