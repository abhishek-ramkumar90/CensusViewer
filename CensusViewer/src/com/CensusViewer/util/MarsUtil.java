package com.CensusViewer.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.CensusViewer.census.datasource.DBConnectionManager;
import com.CensusViewer.census.datasource.MARSQuery;
import com.CensusViewer.config.MARSConstants;
import com.CensusViewer.distribution.config.DistributionConstants;

public class MarsUtil {

	static DBConnectionManager dbc;
	
	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);

	public MarsUtil() {
		logger.info("Entering " + CLASS_NAME);
	}
	
	public static String getIdGenerated(String id, String sequence) {
		
		dbc = new DBConnectionManager();
		Connection connection = null;
		
		try {
			
			connection = dbc.getConnection();
			PreparedStatement ps = connection.prepareStatement(MARSQuery.SEQUENCE_QUERY);
			ps.setString(MARSConstants.ONE, sequence);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
                int idVal = rs.getInt(MARSQuery.NEXTVAL);
                String code = id + idVal;
                return code;
            }
			 
		} catch(Exception e ) {
			e.printStackTrace();
			//logger.fatal(e.getMessage());
		} finally  {
			try {
				if(!connection.isClosed()) {
					connection.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static void main(String args[]) {
		MarsUtil marsUtil = new MarsUtil();
		@SuppressWarnings("static-access")
		String channelId = marsUtil.getIdGenerated(DistributionConstants.INTERM_ID, DistributionConstants.INTERM_SEQUENCE);
		System.out.println(channelId);
	}
}
