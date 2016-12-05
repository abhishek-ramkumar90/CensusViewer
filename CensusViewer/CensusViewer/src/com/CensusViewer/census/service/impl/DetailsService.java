package com.mars.census.service.impl;


//JAX-RS Imports

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.mars.census.model.DetailsWb;
import com.mars.census.model.maharashtraDistrict1;
import com.mars.census.service.dao.DetailsServiceDaoImp;


@Path("/detailsservice")
@Produces({"application/json","application/json"})
public class DetailsService {
	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);
	private DetailsServiceDaoImp detailsDaoImp=new DetailsServiceDaoImp();
	public DetailsServiceDaoImp getDetailsDaoImp() {
		return detailsDaoImp;
	}

	public void setDetailsDaoImp(DetailsServiceDaoImp detailsDaoImp) {
		this.detailsDaoImp = detailsDaoImp;
	}
	
	//get the list ditails of a state/district/tehsil as per selected Category.
	@GET
	@Path("/details")
	@Produces({"application/json","application/json"})
	public Response getDetailsByCaregorySubCategory() {
		logger.debug("get Details service called");
		DetailsWb detailsW=new DetailsWb();
		System.out.println("service called");
	String	 detailsString= " {'stateDetails': [ { 'st_code': '27','st_name':'Maharashtra', 'category':[ {'cat_ids': [{'id': ' 6 ','sub_category': [ { 'name': 'tot_f', 'condition': ' <= ' , ";
	detailsString+=" 'val': '2000000' }, { 'name': 'tot_m', 'condition': ' <=  ','val': '2000000' }] }, {  'id': '3 ','sub_category': [ {  'name': 'marg_cl_p', 'condition': ' <= ','val': '2000000' ";
	detailsString+=	"    },{ 'name': 'marg_cl_m', 'condition': ' <=  ','val': '2000000'  } ]}] }] }, {  'st_code': '28','st_name':'Karnataka', 'category':[ {  'cat_ids': [    {   'id': '5',  'sub_category': [ ";
	detailsString+=	" { 'name': 'tot_f','condition': ' >=  ', 'val': '2000' },{ 'name': 'tot_m', 'condition': ' >= ','val': '2000'}]  },{ 'id': '3 ','sub_category': [ {'name': 'marg_cl_p', ";
	detailsString+=	 " 'condition': ' >= ','val': '2000'  }, { 'name': 'marg_cl_m',  'condition': ' >=  ','val': '2000'  }] } ]} ]     }  ] }  ";             	  
		                                                                                                                                            	                                                                         	                                     	   
		List<maharashtraDistrict1> details=getDetailsDaoImp().getDetails(detailsString);
         System.out.println("list...."+details);
		if (details== null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			builder.entity("<error>Category Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			detailsW.setDistDetails(details);
			return Response.ok(detailsW).build();
		}
	}//end of getTownList

}

