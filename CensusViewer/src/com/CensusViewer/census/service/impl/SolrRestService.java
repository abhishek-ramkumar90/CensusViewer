package com.CensusViewer.census.service.impl;

import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.json.JSONException;

import com.CensusViewer.census.impl.solrsearchDetails;



@Path("/SolrSearchDetailService")
@Produces({"application/json","application/xml"})
public class SolrRestService {
	
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
    Properties props = new Properties();
	
    
    
    private solrsearchDetails solrSearchDAO = new solrsearchDetails ();
			
		
	public solrsearchDetails getSolrSearchDAO() {
		return solrSearchDAO;
	}


	public void setSolrSearchDAO(solrsearchDetails solrSearchDAO) {
		this.solrSearchDAO = solrSearchDAO;
	}


	@GET
	@Path("/MARS/SolrSeach/{SearchString}")
 	@Consumes({"application/json","application/xml"})
      @Produces ({"application/json"})

	public String SOLR_JSON(@PathParam("SearchString") String SearchChar) throws SolrServerException, JSONException {
		
		logger.info("Entering " + CLASS_NAME);
			
			String solrJson=getSolrSearchDAO().solrsearchDetails(SearchChar);

			return solrJson;
			
	}	

}
