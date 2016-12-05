package com.mars.distribution.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mars.HibernateUtility.HibernateUtil;
import com.mars.distribution.ServicePojo.ZoneEditList;
import com.mars.distribution.ServicePojo.ZoneShift;
import com.mars.distribution.config.DistributionConstants;
import com.mars.distribution.model.Branch;
import com.mars.distribution.model.Districts;
import com.mars.distribution.model.Region;
import com.mars.distribution.model.Territory;
import com.mars.distribution.model.TerritoryVillTown;
import com.mars.distribution.model.ZoneListFinal;
import com.mars.distribution.model.ZoneStateListFinal;

public class ZoneCreationDao extends RegionCreationDao  {

	
	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);
	
	ZoneListFinal zoneFinal;
	ZoneStateListFinal zoneStateFinal;
	Collection<ZoneStateListFinal> stateCollection;
	SessionFactory sf;
	
	public void setZoneNames(String jsonString ) throws JSONException {
	
		Session session = null;
		try {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			session.beginTransaction();
			JSONObject obj = new JSONObject(jsonString);
			zoneFinal=new ZoneListFinal();
			stateCollection=new ArrayList<ZoneStateListFinal>();
			String ZoneName=obj.getString("ZoneName");
			zoneFinal.setZoneName(ZoneName);
			zoneFinal.setStatus(DistributionConstants.ONE);
			JSONArray StateNames= obj.getJSONArray("StateNames");
			int j=0;
			while(j<StateNames.length()) {
				zoneStateFinal=new ZoneStateListFinal();
				zoneStateFinal.setStateName(StateNames.getJSONObject(j).getString("stateName"));
				j++;
				zoneStateFinal.setStateId(StateNames.getJSONObject(j).getString("stateId"));
				j++;  
				zoneStateFinal.setStatus(DistributionConstants.ONE);
				zoneStateFinal.setZone(zoneFinal);
				stateCollection.add(zoneStateFinal);
			}
			zoneFinal.setStates(stateCollection);
			session.save(zoneFinal);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		} finally { 
			
			if(session.isOpen()) {
				try {
					session.close();
				} catch(Exception e ) {
					e.printStackTrace();
					logger.fatal(e.getMessage());
				}
			}
		}

	}

	
	public void updateZone(String zoneDetails) {
		Session session = null;
		sf = HibernateUtil.getSessionFactory();
		session = sf.openSession();
		session.beginTransaction();
		ZoneStateListFinal zf = null;
		String ZoneName1;
		String ZoneId;
		ZoneListFinal zoneFinal ;
		try {
			JSONObject json = new JSONObject(zoneDetails);
			ZoneId=json.getString("ZoneId");
			ZoneName1=json.getString("ZoneName");
			JSONArray jsonArray = json.getJSONArray("StateNames");
			Collection<ZoneStateListFinal>zoneList=new ArrayList<ZoneStateListFinal>();
			int j=0;
			//ZoneListFinal zfw=new ZoneListFinal();
			zoneFinal = (ZoneListFinal)session.get(ZoneListFinal.class, ZoneId);
	
			while(j<jsonArray.length()) {
				zf=new ZoneStateListFinal();
			Collection<ZoneStateListFinal>zf3=zoneFinal.getStates();
			for(ZoneStateListFinal zonenew:zf3)
			{
				zonenew.setStateName(jsonArray.getJSONObject(j).getString("stateName"));
				j++;
				zonenew.setStateId(jsonArray.getJSONObject(j).getString("stateId"));
				j++;
				zonenew.setStatus(DistributionConstants.ONE);
/*			zoneFinal.getStates().add(zf);*/
				zonenew.setZone(zoneFinal);
			}
			}
			zoneFinal.setZoneName(ZoneName1);
			zoneFinal.setStatus(DistributionConstants.ONE);
			session.update(zoneFinal);
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.fatal("JSON Exception while Coverting to jSon Object"); 
			e.printStackTrace();
		} finally { 
			
			if(session.isOpen()) {
				try {
					session.close();
				} catch(Exception e ) {
					e.printStackTrace();
					logger.fatal(e.getMessage());
				}
			}
		}
	}
	
	public void deleteZone(String zoneDetails ){
		Collection<ZoneStateListFinal> zoneStates = new ArrayList<ZoneStateListFinal>();
		Collection<Districts> distDB = null;
		Collection<Region> regions = new ArrayList<Region>();
		Collection<Territory> territoryDB = new ArrayList<Territory>();
		Collection<Branch> branch = new ArrayList<Branch>();
		Collection<TerritoryVillTown> territoryVillTownList = null;
		sf = HibernateUtil.getSessionFactory();
		ZoneListFinal zoneDB = null;
		String branchId=null;
		Session session = null;
		org.json.JSONArray zoneIdArray = null;
		try {
			JSONObject json = new JSONObject(zoneDetails);
			//branchId = json.getString("branchId");
			zoneIdArray = json.getJSONArray("zoneId");
		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}
		try {
			session = sf.openSession();
			for(int i=0; i<zoneIdArray.length(); i++ ) {
				zoneDB = (ZoneListFinal)session.get(ZoneListFinal.class, zoneIdArray.get(i).toString());
				session.beginTransaction();
				zoneStates = zoneDB.getStates();
				for(ZoneStateListFinal zoneStateList : zoneStates) {
					zoneStateList.setStatus(DistributionConstants.ZERO);
				}
				regions = zoneDB.getRegions();
				for(Region regionList : regions) {
					branch = regionList.getBranches();
					distDB = regionList.getDistricts();
					for(Districts dist : distDB) {
						dist.setStatus(DistributionConstants.ZERO);
					}
					for(Branch branchs : branch) {
						territoryDB = branchs.getTerritories();
						for(Territory territory : territoryDB)  {
							territoryVillTownList = new ArrayList<TerritoryVillTown>();
							territoryVillTownList = territory.getVillTown();
							for(TerritoryVillTown territoryVillTown :  territoryVillTownList) {
								territoryVillTown.setStatus(DistributionConstants.ZERO);
							}
							territory.setStatus(DistributionConstants.ZERO);
						}
						branchs.setStatus(DistributionConstants.ZERO);
					}
					regionList.setBranches(branch);
		            LogicMap.remove(regionList.getRegionId());
					regionList.setStatus(DistributionConstants.ZERO);
				}
				zoneDB.setStatus(DistributionConstants.ZERO);
				session.update(zoneDB);
				session.getTransaction().commit();
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if(session.isOpen()) {
					session.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
}
	
	public static void main(String args[]) throws JSONException {
		ZoneCreationDao zoneListDao = new ZoneCreationDao();
	String stateId="27";
	String JsonString="{'StateDetails':[{'stateName':'Maharashtra','stateId':'27'},{'stateName':'Gujrat','stateId':'28'}]}";
		zoneListDao.checkAndShiftZone(JsonString);
	}

	public Collection<ZoneEditList> getFreshZoneList(String jsonString) {
		// TODO Auto-generated method stub
		return null;
	}

public Collection<ZoneShift> checkAndShiftZone(String stateId) throws JSONException {
		Session session = null;
		sf = HibernateUtil.getSessionFactory();
		session = sf.openSession();
		session.beginTransaction();
		JSONObject jObj=new JSONObject(stateId);
		JSONArray jarray=jObj.getJSONArray("StateDetails");
		int j=0;
		Collection<String> stateList=new ArrayList<String>();
		while(j<jarray.length()) {
			j++;
		String stateid=jarray.getJSONObject(j).getString("stateId");
			j++;
		stateList.add(stateid);
		}
		String Query="from ZoneStateListFinal where Status=1 AND stateId IN  ";
		StringBuffer sb=new StringBuffer();
		sb.append(Query.substring(0,Query.length()));
		sb.append("(");
		for(String s:stateList)
		{
			sb.append("'");
			sb.append(s);
			sb.append("'");
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		Query query=session.createQuery(sb.toString());
		List<ZoneStateListFinal> zoneList=query.list();
		Collection<ZoneShift> Details=new ArrayList<ZoneShift>();
		for(ZoneStateListFinal zf:zoneList)
		{
		ZoneShift shiftZone=new ZoneShift();
		shiftZone.setStateId(zf.getStateId());
		shiftZone.setStateName(zf.getStateName());
		shiftZone.setZoneId(zf.getZone().getZoneId());
		shiftZone.setZoneName(zf.getZone().getZoneName());
        Details.add(shiftZone);			
		}
		session.close();
		return Details;
	}
}
