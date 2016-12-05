package com.mars.census.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mars.census.dao.CountryDao;
import com.mars.census.model.Country;
import com.mars.census.model.CountryWb;



public class CountryDaoImp implements CountryDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}


	public List retreiveCountryList(String HQL) {
		// TODO Auto-generated method stub
		return hibernateTemplate.find(HQL);
	}


	public  List<CountryWb> countryList()
	{
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"config/spring-configuration.xml");
		CountryDaoImp countryDao = (CountryDaoImp)appContext.getBean("countryDao");
		List<Country> listp = countryDao.retreiveCountryList(" from country_master");
		//List<Country> listp=countryImp.countryList();
		List<CountryWb> countryList = new ArrayList<CountryWb>();
		CountryWb country = null;
		for (Country myCountry : listp) {  



			country = new CountryWb();

			//Integer villageId=myCountry.getId();
			String countryCode= myCountry.getC_code();
			country.setC_code(countryCode);
			String countryName=myCountry.getC_name();
			country.setC_name(countryName);


			countryList.add(country);


		}


		return countryList;

	}




}

