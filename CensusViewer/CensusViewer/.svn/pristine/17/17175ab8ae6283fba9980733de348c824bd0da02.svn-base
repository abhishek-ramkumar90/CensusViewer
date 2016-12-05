package com.mars.distribution.ServiceClass;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.json.JSONException;

import com.mars.distribution.daoimpl.ShortestPathDao;
import com.mars.distribution.ServicePojo.ShortestPathJson;
@Path("/ShortestPathService")
@Produces({"application/json","application/xml"})
public class shortestPath {
private ShortestPathDao dao=new ShortestPathDao();
public ShortestPathDao getDao() {
	return dao;
}
public void setDao(ShortestPathDao dao) {
	this.dao = dao;
}
@GET
@Path("/{JsonString}")
@Produces({"application/json"})
public ShortestPathJson getWKTList(@PathParam("JsonString") String LatLongJsonString) throws JSONException {
	System.out.println("ji");
	ShortestPathJson shortpath=new ShortestPathJson();
    shortpath.setWKTList(getDao().getWKTList(LatLongJsonString));   
    if (shortpath == null) {
       ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
       builder.type("application/json");
       builder.entity("<error>State Not Found</error>");
       throw new WebApplicationException(builder.build());
            } else {
            return shortpath;
		}
	}
}

	
	