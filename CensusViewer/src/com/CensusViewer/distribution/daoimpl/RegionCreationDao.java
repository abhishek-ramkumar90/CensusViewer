package com.CensusViewer.distribution.daoimpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;


import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.CensusViewer.HibernateUtility.HibernateUtil;
import com.CensusViewer.distribution.ServicePojo.RegionCreationPojo;
import com.CensusViewer.distribution.ServicePojo.RegionDistIds;
import com.CensusViewer.distribution.ServicePojo.RegionFormGridList;
import com.CensusViewer.distribution.ServicePojo.RegionShift;
import com.CensusViewer.distribution.config.DistributionConstants;
import com.CensusViewer.distribution.geometrytypes.SpatialGeometry;
import com.CensusViewer.distribution.id.generator.BranchSequenceIdGen;
import com.CensusViewer.distribution.map.RegionGeomJdbc;
import com.CensusViewer.distribution.model.BrDistrict;
import com.CensusViewer.distribution.model.Branch;
import com.CensusViewer.distribution.model.DistIds;
import com.CensusViewer.distribution.model.Districts;
import com.CensusViewer.distribution.model.Region;
import com.CensusViewer.distribution.model.Territory;
import com.CensusViewer.distribution.model.TerritoryVillTown;
import com.CensusViewer.distribution.model.ZoneListFinal;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
public class RegionCreationDao  {
	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);
public	static HashMap<String,Set<String>> LogicMap=new HashMap<String,Set<String>>();
	public RegionCreationDao() {
		logger.info("Entering " + CLASS_NAME);
	}
	Region region;
	Districts district;
	Collection<Region> regions;
	Collection<Region> regionSpatial;
	Collection<Districts> districts;
	RegionCreationPojo regionService;
	SessionFactory sf; 
	public RegionCreationPojo setRegionNames(String jsonString)throws JSONException {
		sf = HibernateUtil.getSessionFactory();
		Session session = null; 
		try {
			session = sf.openSession();
			session.beginTransaction();
			regionService=new RegionCreationPojo();
			regions = new ArrayList<Region>();
			JSONObject obj = new JSONObject(jsonString);
			String zoneId2=obj.getString("zoneId");
			System.out.println(zoneId2);
			JSONArray RegionName= obj.getJSONArray("regionName");
			Set<String> mylist = new HashSet<String>();
			Collection<DistIds>distIds=new ArrayList<DistIds>();
			DistIds di=null;
			HashMap hm = new HashMap();
			String rId = null;
			String regionName = null;
			long currentId = 0;
			boolean updateFlag=false;
			for(int i=0;i<RegionName.length();i++)
			{
				region=new Region();
				districts=new ArrayList<Districts>();
				System.out.println("abhishek"+RegionName.getJSONObject(i).getString("regionname"));
				JSONArray districtArray= RegionName.getJSONObject(i).getJSONArray("districts");
				JSONArray districtIdArray= RegionName.getJSONObject(i).getJSONArray("distIds");
				int k=0;
				for(int i1=0;i1<districtIdArray.length();i1++)
				{
				di=new DistIds();
				di.setDistIds(districtIdArray.getJSONObject(i1).getString("districtId"));
				di.setStatus(DistributionConstants.ZERO);
				distIds.add(di);
				}
				int j=0;
				
				while(j<districtArray.length())
				{
					district=new Districts();
					try{
					district.setDistName(districtArray.getJSONObject(j).getString("districtName"));
					j++;}
					catch(Exception e){
						System.out.println(j);
						district.setStateId(districtArray.getJSONObject(j).getString("stateId"));
						j++;
					}
					try{
					district.setDistId(Integer.parseInt(districtArray.getJSONObject(j).getString("districtId")));
                     mylist.add(districtArray.getJSONObject(j).getString("districtId"));

                     hm.put(new Integer(k++),districtArray.getJSONObject(j).getString("districtId") );

					j++;
					
					}
					catch(Exception e)
					{
						System.out.println(j);
						district.setDistName(districtArray.getJSONObject(j).getString("districtName"));
						j++;
					}
					try{
					district.setStateId(districtArray.getJSONObject(j).getString("stateId"));
					j++;
					}
					catch(Exception e)
					{
						
						System.out.println(j);
						district.setDistId(Integer.parseInt(districtArray.getJSONObject(j).getString("districtId")));
	                     mylist.add(districtArray.getJSONObject(j).getString("districtId"));
	                     hm.put(new Integer(k++),districtArray.getJSONObject(j).getString("districtId") );
						j++;
					}
					
					district.setGid(Integer.parseInt(districtArray.getJSONObject(j).getString("gid")));
					j++;
					district.setStatus(DistributionConstants.ONE);
					districts.add(district);
				}

		/*		for(int i1=0;i1<mylist.size();i1++)
				{
					System.out.println(mylist.get(i1));
					
				}*/
				 currentId =  BranchSequenceIdGen.getBranchSequenceIdGen();
				rId = DistributionConstants.REGION_CODE + currentId;
				region.setRegionId(rId);
				region.setGid(currentId);

				region.setCreatedOn(new java.util.Date());
				regionName=RegionName.getJSONObject(i).getString("regionname");
				region.setRegion(RegionName.getJSONObject(i).getString("regionname"));
			//	googleMap.put(RegionName.getJSONObject(i).getString("regionname"), mylist);
				region.setDistricts(districts);
				region.setStatus(DistributionConstants.ONE);
            	region.setIds(distIds);
				regions.add(region);
				
			}
			
			Query zone=session.createQuery("from ZoneListFinal where ZoneId='"+zoneId2+"'");
			List<ZoneListFinal> zoneList=zone.list();
			String ZoneName;
			for(ZoneListFinal z:zoneList)
			{
				z.getRegions().addAll(regions);
				region.setZone(z);
				session.save(z);
				  session.getTransaction().commit();
				ZoneName=z.getZoneName();
				System.out.println(ZoneName);
				regionService.setZoneName(ZoneName);
				regionService.setRegions(regions);
			}
			RegionGeomJdbc rgjdbc = new RegionGeomJdbc();
			System.out.println("hm.."+hm.size());
			rgjdbc.storeGeomHibernate(hm,rId,regionName,DistributionConstants.REGION_GEOM,currentId,updateFlag,DistributionConstants.REGION_SINGLE_DELETE);

		} catch(Exception e) {
			e.printStackTrace();
			
			logger.fatal(e.getMessage());
		} finally {
			try {
				if(session.isOpen()) {
					session.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
				logger.fatal(e.getMessage());
			}
		}
		
		return  regionService;	
	}
	
	public void updateRegion(String regionDetails ){
		Session session = null;
		boolean updateFlag=true;
	
		Collection<Districts> districts = new ArrayList<Districts>();
		Collection<Districts> districtsDB = new ArrayList<Districts>();
		Set<String> regionDistIds = new HashSet<String>();
		HashMap distCode=new HashMap();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		String regName = null;
		String regId = null;
		String stDist = null;
		try {
			JSONObject json = new JSONObject(regionDetails);
			regId = json.getString("regId");
			regName = json.getString("regName");
			stDist = json.getString("stDist");
		} catch (JSONException e) {
			logger.fatal("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}
		List list =Arrays.asList(stDist.split(("\\s*,\\s*")));
		int k=0;
		for(int i=0;i<list.size();i++){
			System.out.println(list.size());
			System.out.println("list"+list.get(i));
			String st_dist=list.get(i).toString();
			List list2 =Arrays.asList(st_dist.split(("\\s*:\\s*")));
			Districts dist=new Districts();
			dist.setStateId(list2.get(0).toString());
			dist.setDistId(Integer.parseInt(list2.get(1).toString()));
			regionDistIds.add("list:"+list2.get(0).toString()+":"+list2.get(1).toString());
			dist.setDistName(list2.get(2).toString());
			dist.setStatus(DistributionConstants.ONE);
			districts.add(dist);
			distCode.put(new Integer(k++),list2.get(1).toString());
		}
		LogicMap.remove(regId);
		LogicMap.put(regId, regionDistIds);
		long currentId =  BranchSequenceIdGen.getBranchSequenceIdGen();
		try {
			session = sf.openSession();
			Region regionDB = (Region)session.get(Region.class, regId);
			session.beginTransaction();
			districtsDB = regionDB.getDistricts();
			for(Districts districtsEach : districtsDB) {
				districtsEach.setStatus(DistributionConstants.ZERO);
			}
			session.update(regionDB);
			regionDB.getDistricts().addAll(districts);
			regionDB.setRegion(regName);
			session.update(regionDB);
			session.getTransaction().commit();

			RegionGeomJdbc rgjdbc = new RegionGeomJdbc();
			rgjdbc.storeGeomHibernate(distCode,regId,regName,DistributionConstants.REGION_GEOM,currentId,updateFlag,DistributionConstants.REGION_SINGLE_DELETE);

			

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
	
	public void deleteRegion(String regionDetails ){

		Collection<Districts> distDB = new ArrayList<Districts>();
		Collection<Territory> territoryDB = new ArrayList<Territory>();
		Collection<BrDistrict> brDistDB = null;
		Collection<Branch> branch = new ArrayList<Branch>();
		Collection<TerritoryVillTown> territoryVillTownList = null;
		sf = HibernateUtil.getSessionFactory();
		Region regionDB = null;
		String branchId=null;
		Session session = null;
		org.json.JSONArray regionIdArray = null;

		HashMap<Integer,String> hm=new HashMap<Integer,String>();
		HashMap<Integer,String> branchIds=new HashMap<Integer,String>();
		HashMap<Integer,String> territoryIds=new HashMap<Integer,String>();
		


		try {
			JSONObject json = new JSONObject(regionDetails);
			//branchId = json.getString("branchId");
			regionIdArray = json.getJSONArray("regionId");
		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}
		try {
			session = sf.openSession();
			for(int i=0; i<regionIdArray.length(); i++ ) {

				hm.put(new Integer(i),regionIdArray.get(i).toString());

				regionDB = (Region)session.get(Region.class, regionIdArray.get(i).toString());

LogicMap.remove(regionDB.getRegionId());

				session.beginTransaction();
				distDB = regionDB.getDistricts();
				for(Districts dist : distDB) {
					dist.setStatus(DistributionConstants.ZERO);
				}
				branch = regionDB.getBranches();
				int k=0;
				for(Branch branchs : branch) {
					branchIds.put(new Integer(k++),branchs.getBranchId());
					brDistDB = branchs.getDistricts();
					for(BrDistrict brdistricts : brDistDB) {
						brdistricts.setStatus(DistributionConstants.ZERO);
					}
					territoryDB = branchs.getTerritories();
					int t=0;
					for(Territory territory : territoryDB)  {
						territoryIds.put(new Integer(t++),territory.getTerritoryId());
						territoryVillTownList = new ArrayList<TerritoryVillTown>();
						territoryVillTownList = territory.getVillTown();
						for(TerritoryVillTown territoryVillTown :  territoryVillTownList) {
							territoryVillTown.setStatus(DistributionConstants.ZERO);
						}
						territory.setStatus(DistributionConstants.ZERO);
					}
					branchs.setStatus(DistributionConstants.ZERO);
				}
				regionDB.setBranches(branch);
				regionDB.setStatus(DistributionConstants.ZERO);
				session.update(regionDB);
				session.getTransaction().commit();
			}
	
			RegionGeomJdbc rgjdbc = new RegionGeomJdbc();
			 rgjdbc.deleteGeom(hm,DistributionConstants.REGION_DELETE_ALL);
			if(!(branchIds.isEmpty())){
				rgjdbc.deleteGeom(branchIds,DistributionConstants.BRANCH_DELETE_ALL);
			}
			if(!(territoryIds.isEmpty())){
				rgjdbc.deleteGeom(territoryIds,DistributionConstants.TERRITORY_DELETE_ALL);
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
	
	
	
	
	

	public Collection<RegionFormGridList> getRegionList() {
		// TODO Auto-generated method stub
		Session session=null;
		Collection<RegionFormGridList>regionGridList=null;
		Set<String>distList=null;
		try{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		 session= sf.openSession();
		regionGridList=new ArrayList<RegionFormGridList>();
		RegionFormGridList reglist=null;
		Query regions=session.createQuery("from Region where status=1");
		List<Region> regionList=regions.list();
		for(Region reg:regionList)
		{
			distList=new HashSet<String>();
			reglist=new RegionFormGridList();
			reglist.setRegionId(reg.getRegionId());
			reglist.setRegion(reg.getRegion());
	        reglist.setZoneId(reg.getZone().getZoneId());	
	        Collection<Districts>toFilter=reg.getDistricts();
	        Collection<Districts>filteredCollection=filterCollection(toFilter,session);
	        reglist.setDistricts(filteredCollection);
	        for(Districts d:filteredCollection)
	        {
	        	distList.add("list:"+d.getStateId()+":"+Integer.toString(d.getDistId()));
	        }
	        LogicMap.put(reg.getRegionId(),distList);
	        reglist.setZoneName(reg.getZone().getZoneName());
	        regionGridList.add(reglist);
		}
	} catch(Exception e) {
		e.printStackTrace();
		logger.fatal(e.getMessage());
	} finally {
		try {
			if(session.isOpen()) {
				//session.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}
	
	}
		return regionGridList;
	}
	private static  Collection<Districts> filterCollection(Collection<Districts> collection, Session s) {
		Query filterQuery = s.createFilter(collection, "where status=1");
		return filterQuery.list();
	}
	
	
	
	
	
	
	
	
	

	public Collection<RegionShift> CheckRegionShift(String distid)  {
		Session session = null;
		Collection<RegionShift> regCollection=new ArrayList<RegionShift>();
		try {
	       sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			JSONObject obj = new JSONObject(distid);
			JSONArray jarray=obj.getJSONArray("paramName");
		List<Integer> intArr=new ArrayList<Integer>();
		StringBuilder Query=new StringBuilder("from Districts where Status=1 AND distId IN ");
Query.append("(");
       for(int i=0;i<jarray.length();i++)
       {
    	   try{
    		   
    	Query.append((Integer)(jarray.get(i)));
    	Query.append(",");
    	   }
    	   catch(Exception e)
    	   {
    		  Query.append(Integer.parseInt((String) jarray.get(i)));
    		  Query.append(",");
    	   }
       }
       Query.deleteCharAt(Query.length()-1);
       Query.append(")");
       String Query1=Query.toString();
			Query query=session.createQuery(Query1);
			List<Districts> checkedDistrict=query.list();
			RegionShift rf=null;
			if(checkedDistrict.size()>0)
			{			
			for(Districts d:checkedDistrict)
			{
				rf=new RegionShift();
				rf.setDistrictId(Integer.toString(d.getDistId()));
				regCollection.add(rf);
			}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
		}
		
		return regCollection;
	}




	public Collection<RegionDistIds> getDistIds(String regionId) {
		Session session = null;
		Collection<RegionDistIds> regCollection=new ArrayList<RegionDistIds>();
		RegionDistIds rid=null;
		try {
		       sf = HibernateUtil.getSessionFactory();
		   	session = sf.openSession();
		   	Query query=session.createQuery("from DistIds where REGION_ID='"+regionId+"'");
		   	List<DistIds> IdList=query.list();
		   	for(DistIds dis:IdList)
		   	{
		   		rid=new RegionDistIds();
		   		rid.setDistIds(dis.getDistIds());
		   		regCollection.add(rid);
		   	}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fatal(e.getMessage());
			
		}
		finally
		{
			if(session.isOpen())
			{
				session.close();
			}
		}
		return regCollection;
		// TODO Auto-generated method stub
		
	}
	
	public Collection<String> getLogicMapIds(String regionId) {
		Collection<String> logicIds=new ArrayList<String>();
		 for (Entry entry : LogicMap.entrySet()) {
			 if(regionId.equals(entry.getKey()))
				 {continue;
				 }
			 logicIds.addAll((Collection<String>) entry.getValue());
			 }
		return logicIds;
	}





}

	

