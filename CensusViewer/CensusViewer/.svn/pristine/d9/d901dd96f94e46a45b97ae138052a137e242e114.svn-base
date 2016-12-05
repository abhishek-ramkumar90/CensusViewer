package com.mars.distribution.ServiceClass;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mars.distribution.daoimpl.BranchCreationDao;
import com.mars.distribution.ServicePojo.BranchCreationPojo;

@Path("/BranchCreation")
@Produces({"application/json","application/xml"})
public class BranchCreation {
	private BranchCreationDao bdao=new BranchCreationDao();

	public BranchCreationDao getBdao() {
		return bdao;
	}

	public void setBdao(BranchCreationDao bdao) {
		this.bdao = bdao;
	}
	
	@GET
	@Path("/{JsonString}")
	@Produces("application/json")
	public BranchCreationPojo getBranches(@PathParam("JsonString") String JsonString)
	{
		
		BranchCreationPojo branches=new BranchCreationPojo();
		branches.setBranchPojo(getBdao().setBranchNames(JsonString));
		return null;
	}
	
	
}
