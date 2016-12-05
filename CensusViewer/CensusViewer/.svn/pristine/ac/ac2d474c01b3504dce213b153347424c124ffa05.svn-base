package com.mars.census.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;

import com.google.gson.Gson;

public class solrsearchDetails {
	
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
    Properties props = new Properties();
    
    
	public String solrsearchDetails(String SearchChar) {
		
		try {
			SearchChar = SearchChar.trim();
			SearchChar = SearchChar.replaceAll("\\s+"," ");
			SearchChar = SearchChar.replaceAll("\\s+"," AND "); 
			
			System.out.println("test-" + SearchChar);
			//FileInputStream fis = new FileInputStream("G:/marsjkasdhjasgdjh/resources/mars.properties");

			SolrServer server = new HttpSolrServer("http://172.16.1.7:8080/solr/collection1");
			ModifiableSolrParams params = new ModifiableSolrParams();

		    params.set("q", "name:("+SearchChar+"*)");
		   
		    QueryResponse response = server.query(params);
		    Gson gson = new Gson();
			String solrString = gson.toJson(response);
			return solrString;
	}catch(Exception e)
	{
		e.printStackTrace();
	}
		// TODO Auto-generated method stub
		return null;
	}
    
   /* public static void main(String args[]){
    	solrsearchDetails  ssd = new solrsearchDetails();
    	
    	String str = ssd.solrsearchDetails(" Satala   Bk");
    	System.out.println("----"+str);
    	
    	String str1 = ssd.solrsearchDetails("SatalaBk.");
    	System.out.println("----"+str1);
    }*/

}
