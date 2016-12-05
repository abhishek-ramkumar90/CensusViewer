package com.CensusViewer.census.service.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.CensusViewer.census.impl.CategoryDetails;
import com.CensusViewer.census.model.Category;


@Path("/CategoryDetailService")
@Produces({"application/json","application/xml"})
public class CategoryRestService {

	
	
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);


	private CategoryDetails categoryDAO = new CategoryDetails ();


	public CategoryDetails  getCategoryDAO() {

		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDetails  categoryDAO ) {
		this.categoryDAO = categoryDAO;

	}
	
	

	
	@GET
	@Path("/MARS/CategoryDetails/{details}/{isStateSelected}/{isDistrictSelected}/{isTehsilSelected}")
	@Produces({"application/json"})
	public Category getCategoryDetails(@PathParam("details") String details,@PathParam("isStateSelected") String stateselect,@PathParam("isDistrictSelected") String districtselect,@PathParam("isTehsilSelected") String tehsilselect ) {
Category category=new Category();
//System.out.println(details);
category.setCategories(getCategoryDAO().categoryDetails(details,stateselect,districtselect,tehsilselect));

		if (category == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/json");
			builder.entity("<error>State Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {

			return category;
		}

	}

}

	
	
	
	
	
	
	
	


