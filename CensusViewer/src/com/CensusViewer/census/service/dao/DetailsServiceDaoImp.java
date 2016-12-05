package com.CensusViewer.census.service.dao;

import java.util.List;

import com.CensusViewer.census.impl.DetailsDaoImp;
import com.CensusViewer.census.model.maharashtraDistrict1;

public class DetailsServiceDaoImp {

	public List<maharashtraDistrict1> getDetails(String detailsString) {
		DetailsDaoImp districtDetails=new DetailsDaoImp();
		System.out.println(detailsString);
		List<maharashtraDistrict1> detailsList=districtDetails.districtDetails(detailsString);
		return (List<maharashtraDistrict1>) detailsList;
		
}
}