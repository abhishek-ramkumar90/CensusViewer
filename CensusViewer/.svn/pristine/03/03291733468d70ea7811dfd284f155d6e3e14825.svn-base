package com.mars.census.service.impl;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.mars.census.impl.SubCategoryDetails;
import com.mars.census.model.SubCategory;



@Path("/SubCategoryDetailService")
@Produces({"application/json","application/xml"})
public class SubCategoryRestService {
	
	
	
	String CLASS_NAME = this.getClass().getName();


	private SubCategoryDetails   subcategoryDAO = new SubCategoryDetails();


	public SubCategoryDetails getSubCategoryDAO() {

		return subcategoryDAO;
	}

	public void setSubCategoryDAO(SubCategoryDetails  subcategoryDAO ) {
		this.subcategoryDAO = subcategoryDAO;

	}
	
	

	
	@GET
	@Path("/MARS/SubCategoryDetails/{cat_id}/{details}/{isStateSelected}/{isDistrictSelected}")
	@Produces({"application/json"})
	public SubCategory getCategoryDetails(@PathParam("cat_id") Integer cat_id,@PathParam("details") String details,@PathParam("isStateSelected") String stateselect,@PathParam("isDistrictSelected") String districtselect ) {
SubCategory subcategory=new SubCategory();
subcategory.setSubcategories(getSubCategoryDAO().subcategoryDetails(cat_id,details,stateselect,districtselect));
		//State state = (State)getStateDAO().getState();
		if (subcategory == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>State Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {

			return subcategory;
		}

	}

}
