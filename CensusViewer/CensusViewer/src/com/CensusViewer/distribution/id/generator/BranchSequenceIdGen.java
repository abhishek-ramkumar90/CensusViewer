package com.mars.distribution.id.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mars.HibernateUtility.HibernateUtil;
import com.mars.census.datasource.DBConnectionManager;

public class BranchSequenceIdGen {

	static DBConnectionManager dbc;
	
	public static long getBranchSequenceIdGen() {
		dbc = new DBConnectionManager();
		Connection connection = null;
		String prefix = "B";
		try {
			connection = dbc.getConnection();
			 PreparedStatement ps = connection.prepareStatement("SELECT nextval('branch_id')");

	         ResultSet rs = ps.executeQuery();
	         if (rs.next()) {
	             int id = rs.getInt("nextval");

	             return id;
	         }
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if(!connection.isClosed()) {
					connection.close();
				}
			} catch(Exception e ) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
		}
		return 0;
	}

	/*
	public static void main(String[] args) {
		BranchSequenceIdGen bsig = new BranchSequenceIdGen();
		System.out.println(bsig.getBranchSequenceIdGen());
	}
*/
}
