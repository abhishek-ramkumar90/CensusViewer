package com.CensusViewer.distribution.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;

import com.CensusViewer.HibernateUtility.HibernateUtil;
import com.CensusViewer.config.MARSConstants;
import com.CensusViewer.distribution.ServicePojo.IntermediaryJackson;
import com.CensusViewer.distribution.ServicePojo.IntermediarySerPojo;
import com.CensusViewer.distribution.config.DistributionConstants;
import com.CensusViewer.distribution.model.Intermediary;
import com.CensusViewer.util.MarsUtil;

public class IntermediaryDaoImpl {

	Session session = null;

	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);

	public IntermediaryDaoImpl() {
		logger.info("Entering " + CLASS_NAME);
	}

	public List<HashMap> getAllIntermediary(String orgId,String userId) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		List<Intermediary> type = new ArrayList<Intermediary>();
		List<HashMap> type1 = new ArrayList<HashMap>();
		HashMap hm = new HashMap();
		HashMap hmType = new HashMap();

		try {
			session = sf.openSession();

			Query intermType = session.createQuery("from Intermediary where status='" + DistributionConstants.ONE + "' and  orgId = '" + orgId + "' and createdBy = '" + userId.trim() + "'");

			type = intermType.list();

			for(Intermediary inter : type) {

				if(new Integer(inter.getRootStatus()).equals(DistributionConstants.ONE)) {
					hm.put(inter.getIntermId(), inter.getIntermType());
				} 
			}

			for(Intermediary inter : type) {
				hmType.put(inter.getIntermType(), inter.getRootStatus());
			}
			//type1 = new ArrayList<String>(new HashSet<String, Integer >(hmType));
			type1.add(hm);
			type1.add(hmType);

			//Collections.sort(type1);
			return type1;

		} catch(Exception e ) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		} finally {

			try {
				if(session.isOpen()) {
					session.close();
				}
			} catch(Exception e ) {
				e.printStackTrace();
			} 
		}

		return null;
	}

	public List<IntermediarySerPojo> getIntermediaryByName(String intermType) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		List<Intermediary> intms = new ArrayList<Intermediary>();
		List<IntermediarySerPojo> intmNameList = new  ArrayList<IntermediarySerPojo>();

		try {
			session = sf.openSession();
			Query intermNames = session.createQuery("from Intermediary where intermType='"+intermType.trim()+"' and status = '"+DistributionConstants.ONE +"'");
			//
			intms  = intermNames.list();
			for(Intermediary inter: intms){
				IntermediarySerPojo intmed = new IntermediarySerPojo();
				intmed.setIntmId(inter.getIntermId());
				intmed.setIntmName(inter.getName());
				intmNameList.add(intmed);

			}

		} catch(Exception e ) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		} finally {

			try {
				if(session.isOpen()) {
					session.close();
				}
			} catch(Exception e ) {
				e.printStackTrace();
			} 
		}
		return intmNameList;
		//return null;
	}
	public List<IntermediarySerPojo> getIntermediaryChild(String intermId) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		List<Intermediary> intms = new ArrayList<Intermediary>();
		List<Intermediary> intmChild = new ArrayList<Intermediary>();
		List<IntermediarySerPojo> intmNameList = new  ArrayList<IntermediarySerPojo>();

		try {
			session = sf.openSession();
			Query intermNames = session.createQuery("from Intermediary where intermId='"+intermId.trim()+"' and status = '"+DistributionConstants.ONE +"'");
			//
			intms  = intermNames.list();
			for(Intermediary inter: intms){
				
				intmChild = inter.getIntermediaryCollection();
				
				if(!intmChild.isEmpty()){
					
					for(Intermediary intmch:intmChild){
						
						IntermediarySerPojo intmed = new IntermediarySerPojo();
						
						intmed.setIntmId(intmch.getIntermId());
						intmed.setIntmName(intmch.getName());
						intmed.setIntermType(intmch.getIntermType());
						
						intmNameList.add(intmed);	
					}

				}else{
					return null;
				}
			}
		} catch(Exception e ) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		} finally {
			try {
				if(session.isOpen()) {
					session.close();
				}
			} catch(Exception e ) {
				e.printStackTrace();
			} 
		}
		return intmNameList;
		//return null;
	}

	public boolean saveIntermDetails(final IntermediaryJackson intmdetails){
		Intermediary intermediary1 = null;
		List<Intermediary> intermParents =new ArrayList<Intermediary>();
		List<Intermediary> intermOldRoot =new ArrayList<Intermediary>();
		List<Intermediary> intermParentList =new ArrayList<Intermediary>();
		List<Intermediary> intermParentListDetails =new ArrayList<Intermediary>();
		
		Intermediary oldChild = null;
		Intermediary intmTemp = null;
		
		Session session=null ;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		boolean flag = false;



		try{
			
			
			MarsUtil marsUtil = new MarsUtil();
			String user=DistributionConstants.DEFAULT_USER;
			String orgId=DistributionConstants.CREATED_ORGID;
			final String intermId=marsUtil.getIdGenerated(DistributionConstants.INTERM_ID, DistributionConstants.INTERM_SEQUENCE);

			intermediary1 = new Intermediary();
			
			intermediary1.setIntermType(intmdetails.getIntermType());
			intermediary1.setAddress(intmdetails.getAddress());
			intermediary1.setZoneId(intmdetails.getZoneId());
			intermediary1.setBranchId(intmdetails.getBranchId());
			intermediary1.setRegionId(intmdetails.getRegId());
			intermediary1.setTerritoryId(intmdetails.getTerrId());
			intermediary1.setVillTownId(intmdetails.getVillTownId());
			intermediary1.setCreatedBy(user);
			intermediary1.setCreatedOn(new Date());
			intermediary1.setIntermId(intermId);
			intermediary1.setName(intmdetails.getIntermName());
			intermediary1.setOrgId(orgId);
			intermediary1.setStatus(DistributionConstants.ONE);
			
			if(intmdetails.isRoot()){
				
				intermediary1.setRootStatus(DistributionConstants.ONE);
			
				if(intmdetails.getRootId().trim().equals("")){
				
					session = sf.openSession();
					session.beginTransaction();	
					session.save(intermediary1);
					session.getTransaction().commit();
					
					flag = true;
					
				}else{
					
					session = sf.openSession();
					session.beginTransaction();	
					
					Query intermOldRoottQuery = session.createQuery("from Intermediary where intermId='"+intmdetails.getRootId().trim()+"' and status='"+DistributionConstants.ONE+"'");
					
					intermOldRoot = intermOldRoottQuery.list();
					
					for(Intermediary intm : intermOldRoot ) {
						
						intm.setRootStatus(DistributionConstants.ZERO);
						intermediary1.getIntermediaryCollection().add(intm);
					}
					
					session.save(intermediary1);
					session.getTransaction().commit();
					flag = true;
				
				}

			}else{
				
				intermediary1.setRootStatus(DistributionConstants.ZERO);
				
				if(!intmdetails.getChildId().trim().equals("") && !(intmdetails.getChildId().trim().equals("null"))){
					session = sf.openSession();
					session.beginTransaction();	
					
					session.save(intermediary1);
					session.getTransaction().commit();
					
					session.beginTransaction();
					
					session.doWork(new Work() {
						
						@Override
						public void execute(Connection conn) throws SQLException {
							PreparedStatement pStmt = null;
							StringBuilder sb = new StringBuilder();
							
							try {
								String[] childIds = intmdetails.getChildId().toString().split(",");
								
								String updateInterm2 = "INSERT INTO distribution.intermediary_relation (intermediaries_interm_id, intermediarycollection_interm_id) VALUES (?, ?)";
								pStmt = conn.prepareStatement(updateInterm2);

								pStmt.setString(DistributionConstants.ONE, intmdetails.getParentId().toString());
								pStmt.setString(DistributionConstants.TWO, intermId);

								pStmt.execute();
								String updateInterm = "UPDATE distribution.intermediary_relation SET intermediaries_interm_id = ? WHERE intermediaries_interm_id = ? AND intermediarycollection_interm_id = ?";
								
								pStmt = conn.prepareStatement(updateInterm);
								
								for(String strChild : childIds) {
								
									//pStmt.addBatch();
									//pStmt.executeUpdate();
									pStmt.setString(DistributionConstants.ONE, intermId);
									pStmt.setString(DistributionConstants.TWO, intmdetails.getParentId().toString());
									pStmt.setString(DistributionConstants.THREE, strChild);
									
									pStmt.executeUpdate();
								}
								
								//pStmt.executeBatch();
								//pStmt.executeUpdate();
							} catch(SQLException e) {
								e.printStackTrace();
							} catch(Exception e) {
								e.printStackTrace();
							} finally {
								try {
									
									if(!pStmt.isClosed()) {
										pStmt.close();
									}
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
						}
					});
				
					session.getTransaction().commit();
					flag = true;
					
				} else {
					//if(intmdetails.getChildId().trim().equals(""))
					String intrmParent= intmdetails.getParentId();
					session = sf.openSession();
					session.beginTransaction();	
					Query intmParentQuery = session.createQuery("from Intermediary where intermId='"+intrmParent+"' and status='"+DistributionConstants.ONE+"'");
					intermParents = intmParentQuery.list();
					for(Intermediary intm:intermParents ){
						intm.getIntermediaryCollection().add(intermediary1);
						session.save(intm);
						session.getTransaction().commit();
						flag = true;
					}
				}
				
			}


		}catch(HibernateException e){
			flag = false;
			e.printStackTrace();
		}finally{
			if(session.isOpen()) {	
				session.close();
			}
		}

		return flag;
	}
	
	/*public static void main(String args[]) {

		IntermediaryDaoImpl intermDaoImpl = new IntermediaryDaoImpl();
		List<String> strList = intermDaoImpl.getAllIntermediary(DistributionConstants.DEFAULT_ORGID, DistributionConstants.DEFAULT_USER);

		for(String str : strList) {
			System.out.println("String : " + str );
		}
	}*/

}
