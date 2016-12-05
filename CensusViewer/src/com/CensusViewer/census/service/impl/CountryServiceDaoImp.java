package com.CensusViewer.census.service.impl;

import java.util.List;

import com.CensusViewer.census.impl.CountryDaoImp;
import com.CensusViewer.census.model.CountryWb;



public class CountryServiceDaoImp {
	public List<CountryWb> getCountryList(){
		CountryWb country = null;


		CountryDaoImp countryImp=new CountryDaoImp();
		List<CountryWb>  countryList=countryImp.countryList();
		return countryList;
	}//end of getCountryList
}
