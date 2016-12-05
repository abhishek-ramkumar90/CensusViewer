package com.mars.distribution.daoimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mars.HibernateUtility.HibernateUtil;
import com.mars.census.servicedef.TownDefinition;
import com.mars.census.servicedef.VillageDefinition;
import com.mars.census.services.customerservice.TownService;
import com.mars.census.services.customerservice.VillageService;
import com.mars.distribution.ServicePojo.BranchListTerrJson;
import com.mars.distribution.ServicePojo.RegDistJson;
import com.mars.distribution.ServicePojo.TerrVillTownJson;
import com.mars.distribution.ServicePojo.TerritoryJson;
import com.mars.distribution.ServicePojo.TerritoryService;
import com.mars.distribution.ServicePojo.TownListJson;
import com.mars.distribution.ServicePojo.VillageListJson;
import com.mars.distribution.config.DistributionConstants;
import com.mars.distribution.id.generator.BranchSequenceIdGen;
import com.mars.distribution.map.RegionGeomJdbc;
import com.mars.distribution.model.BrDistrict;
import com.mars.distribution.model.Branch;
import com.mars.distribution.model.Region;
import com.mars.distribution.model.Territory;
import com.mars.distribution.model.TerritoryVillTown;
import com.mars.distribution.model.Zone;
import com.mars.distribution.model.ZoneListFinal;

public class TerritoryServiceDao  {

	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);

	public TerritoryServiceDao() {
		logger.info("Entering " + CLASS_NAME);
	}

	Session session = null;
	public Collection<BranchListTerrJson> getBranches(String regId){
		List<Region> regionList=null;
		String regionId = null;
		List<BranchListTerrJson> branches=new ArrayList<BranchListTerrJson>();
		SessionFactory sf = HibernateUtil.getSessionFactory();

		try {
			JSONObject json = new JSONObject(regId);
			regionId = json.getString("regId");
		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}

		try{
			session = sf.openSession();
			session.beginTransaction();
			Query regions=session.createQuery("from Region where regionId='"+regionId+"'");
			regionList=regions.list();

			for(Region reg:regionList)
			{
				String zid=reg.getRegionId();
				System.out.println(zid);
				Collection<Branch> b=reg.getBranches();
				for(Branch br:b) {

					if( br.getStatus() == DistributionConstants.ONE) {

						BranchListTerrJson branch=new BranchListTerrJson();
						branch.setRegnId(reg.getRegionId());
						branch.setBranchId(br.getBranchId());
						branch.setBranchName(br.getBranch());
						branches.add(branch);

					}
				}
			}
		} catch(HibernateException e){
			e.printStackTrace();
		} finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		Collections.sort(branches,new BranchListTerrJson());
		return  branches;	
	}

	public Collection<VillageListJson> getVillageList(String inputString) {
		List<VillageListJson> villageList = null;
		VillageListJson villageListJson = null;
		String branchId = null;
		Collection<Branch> branchList=new ArrayList<Branch>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		VillageDefinition villageService = new VillageService(); 
		int k = 0;
		Multimap<String, String> multiMap = HashMultimap.create();

		HashMap<String,String> villageCodes = new HashMap<String,String>();
		Collection<Territory> territoryCollec = null;
		Collection<TerritoryVillTown> terriVillTowns = null;

		try {
			JSONObject json = new JSONObject(inputString);
			branchId = json.getString("branchId");
		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}

		try{

			session = sf.openSession();
			session.beginTransaction();
			Query branches = session.createQuery("from Branch where branchId='"+branchId+"'");
			branchList = branches.list();

			for(Branch branch : branchList) {

				territoryCollec = branch.getTerritories();

				for(Territory territorys : territoryCollec) {
					if(territorys.getStatus() == DistributionConstants.ONE) {
						terriVillTowns = territorys.getVillTown();

						Iterator iter = terriVillTowns.iterator();
						while(iter.hasNext()) {
							TerritoryVillTown villTown = (TerritoryVillTown)iter.next();
							if(villTown.getLevel().toString().toLowerCase().trim().equals("village") && (villTown.getStatus() == DistributionConstants.ONE)) {
								villageCodes.put(new Integer(villTown.getTownVillCode()).toString(),new Integer(villTown.getTownVillCode()).toString());
							}
						}
					}
				}

				Collection<BrDistrict> branchDist = branch.getDistricts();
				for(BrDistrict branchDistrict : branchDist){
					if( branchDistrict.getStatus() == DistributionConstants.ONE) {
						multiMap.put(new Integer(branchDistrict.getStateCode()).toString(), new Integer(branchDistrict.getDistCode()).toString());
					}
				}
			}

			List stateDistVillageList = villageService.getVillageListOnStateDistrict(multiMap);
			villageList = new ArrayList<VillageListJson>();

			for(int i=1; i<stateDistVillageList.size(); i++) {

				Object[] ObjList = (Object[])stateDistVillageList.get(i);
				logger.info(i + "--" + ObjList[0].toString() + "--" +  ObjList[1].toString() + "--" + ObjList[2].toString());
				if(!villageCodes.containsKey(ObjList[2].toString().trim())) {
					logger.info(i + "--ival");
					villageListJson = new VillageListJson();
					villageListJson.setVillageName(ObjList[k++].toString());
					villageListJson.setStateCode(ObjList[k++].toString());
					villageListJson.setVillageId(ObjList[k++].toString());
					villageListJson.setDistId(ObjList[k++].toString());
					villageListJson.setLat((Double) ObjList[k++]);
					villageListJson.setLongitude((Double) ObjList[k++]);
					villageListJson.setGid(Integer.parseInt(ObjList[k].toString()));
					k = 0;
					villageList.add(villageListJson);
				}
			}

		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		Collections.sort(villageList,new VillageListJson());
		return  villageList;	
	}

	public Collection<TownListJson> getTownList(String inputString) {
		List<TownListJson> townList = null;
		TownListJson townListJson = null;
		String branchId = null;
		Collection<Branch> branchList=new ArrayList<Branch>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		TownDefinition townService = new TownService(); 
		int k = 0;
		Multimap<String, String> multiMap = HashMultimap.create();

		HashMap<String,String> townCodes = new HashMap<String,String>();
		Collection<Territory> territoryCollec = null;
		Collection<TerritoryVillTown> terriVillTowns = null;

		try {
			JSONObject json = new JSONObject(inputString);
			branchId = json.getString("branchId");
		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}

		try{

			session = sf.openSession();
			session.beginTransaction();
			Query branches = session.createQuery("from Branch where branchId='"+branchId+"'");
			branchList = branches.list();

			for(Branch branch : branchList) {

				territoryCollec = branch.getTerritories();

				for(Territory territorys : territoryCollec) {
					if(territorys.getStatus() == DistributionConstants.ONE) {
						terriVillTowns = territorys.getVillTown();

						Iterator iter = terriVillTowns.iterator();
						while(iter.hasNext()) {
							TerritoryVillTown villTown = (TerritoryVillTown)iter.next();
							if(villTown.getLevel().toString().toLowerCase().trim().equals("town") && (villTown.getStatus() == DistributionConstants.ONE)) {
								townCodes.put(new Integer(villTown.getTownVillCode()).toString(),new Integer(villTown.getTownVillCode()).toString());
							}
						}
					}
				}

				Collection<BrDistrict> branchDist = branch.getDistricts();

				for(BrDistrict branchDistrict : branchDist){
					if( branchDistrict.getStatus() == DistributionConstants.ONE) {
						multiMap.put(new Integer(branchDistrict.getStateCode()).toString(), new Integer(branchDistrict.getDistCode()).toString());
					}	
				}
			}

			List stateDistTownList = townService.getTownListOnStateDistrict(multiMap);
			townList = new ArrayList<TownListJson>();

			for(int i=1; i<stateDistTownList.size(); i++) {
				Object[] ObjList = (Object[])stateDistTownList.get(i);
				logger.info(i + "--" + ObjList[0].toString() + "--" +  ObjList[1].toString() + "--" + ObjList[2].toString());
				if(!townCodes.containsKey(ObjList[2].toString().trim())) {
					logger.info(i + "--ival");
					townListJson = new TownListJson();
					townListJson.setTownName(ObjList[k++].toString());
					townListJson.setStateCode(ObjList[k++].toString());
					townListJson.setTownId(ObjList[k++].toString());
					townListJson.setDistId(ObjList[k++].toString());
					townListJson.setLatitude((Double)ObjList[k++]);
					townListJson.setLongitude((Double)ObjList[k++]);
					townListJson.setGid((Integer)ObjList[k++]);
					k = 0;
					townList.add(townListJson);
				}

			}

		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		Collections.sort(townList, new TownListJson());
		return  townList;	
	}


	public Collection<VillageListJson> getVillageListUpdate(String inputString) {
		List<VillageListJson> villageList = null;
		VillageListJson villageListJson = null;
		String branchId = null;
		String territoryId = null;
		Collection<Branch> branchList=new ArrayList<Branch>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		VillageDefinition villageService = new VillageService(); 
		int k = 0;
		Multimap<String, String> multiMap = HashMultimap.create();

		HashMap<String,String> villageCodes = new HashMap<String,String>();
		Collection<Territory> territoryCollec = null;
		Collection<TerritoryVillTown> terriVillTowns = null;

		try {
			JSONObject json = new JSONObject(inputString);
			branchId = json.getString("branchId");
			territoryId = json.getString("territoryId");
		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}

		try{

			session = sf.openSession();
			session.beginTransaction();
			Query branches = session.createQuery("from Branch where branchId='"+branchId+"'");
			branchList = branches.list();

			for(Branch branch : branchList) {

				territoryCollec = branch.getTerritories();

				for(Territory territorys : territoryCollec) {
					if(territorys.getStatus() == DistributionConstants.ONE && !territorys.getTerritoryId().equals(territoryId) ) {
						terriVillTowns = territorys.getVillTown();

						Iterator iter = terriVillTowns.iterator();
						while(iter.hasNext()) {
							TerritoryVillTown villTown = (TerritoryVillTown)iter.next();
							if(villTown.getLevel().toString().toLowerCase().trim().equals("village") && (villTown.getStatus() == DistributionConstants.ONE)) {
								villageCodes.put(new Integer(villTown.getTownVillCode()).toString(),new Integer(villTown.getTownVillCode()).toString());
							}
						}
					}
				}

				Collection<BrDistrict> branchDist = branch.getDistricts();
				for(BrDistrict branchDistrict : branchDist){
					if( branchDistrict.getStatus() == DistributionConstants.ONE) {
						multiMap.put(new Integer(branchDistrict.getStateCode()).toString(), new Integer(branchDistrict.getDistCode()).toString());
					}
				}
			}

			List stateDistVillageList = villageService.getVillageListOnStateDistrict(multiMap);
			villageList = new ArrayList<VillageListJson>();

			for(int i=1; i<stateDistVillageList.size(); i++) {

				Object[] ObjList = (Object[])stateDistVillageList.get(i);
				logger.info(i + "--" + ObjList[0].toString() + "--" +  ObjList[1].toString() + "--" + ObjList[2].toString());
				if(!villageCodes.containsKey(ObjList[2].toString().trim())) {
					logger.info(i + "--ival");
					villageListJson = new VillageListJson();
					villageListJson.setVillageName(ObjList[k++].toString());
					villageListJson.setStateCode(ObjList[k++].toString());
					villageListJson.setVillageId(ObjList[k++].toString());
					villageListJson.setDistId(ObjList[k].toString());
					k = 0;
					villageList.add(villageListJson);
				}
			}

		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		Collections.sort(villageList,new VillageListJson());
		return  villageList;	
	}

	public Collection<TownListJson> getTownListUpdate(String inputString) {
		List<TownListJson> townList = null;
		TownListJson townListJson = null;
		String branchId = null;
		String territoryId = null;
		Collection<Branch> branchList=new ArrayList<Branch>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		TownDefinition townService = new TownService(); 
		int k = 0;
		Multimap<String, String> multiMap = HashMultimap.create();

		HashMap<String,String> townCodes = new HashMap<String,String>();
		Collection<Territory> territoryCollec = null;
		Collection<TerritoryVillTown> terriVillTowns = null;

		try {
			JSONObject json = new JSONObject(inputString);
			branchId = json.getString("branchId");
			territoryId = json.getString("territoryId");
		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}

		try{

			session = sf.openSession();
			session.beginTransaction();
			Query branches = session.createQuery("from Branch where branchId='"+branchId+"'");
			branchList = branches.list();

			for(Branch branch : branchList) {

				territoryCollec = branch.getTerritories();

				for(Territory territorys : territoryCollec) {
					if(territorys.getStatus() == DistributionConstants.ONE && !territorys.getTerritoryId().equals(territoryId) ) {
						terriVillTowns = territorys.getVillTown();

						Iterator iter = terriVillTowns.iterator();
						while(iter.hasNext()) {
							TerritoryVillTown villTown = (TerritoryVillTown)iter.next();
							if(villTown.getLevel().toString().toLowerCase().trim().equals("town") && (villTown.getStatus() == DistributionConstants.ONE)) {
								townCodes.put(new Integer(villTown.getTownVillCode()).toString(),new Integer(villTown.getTownVillCode()).toString());
							}
						}
					}
				}

				Collection<BrDistrict> branchDist = branch.getDistricts();

				for(BrDistrict branchDistrict : branchDist){
					if( branchDistrict.getStatus() == DistributionConstants.ONE) {
						multiMap.put(new Integer(branchDistrict.getStateCode()).toString(), new Integer(branchDistrict.getDistCode()).toString());
					}	
				}
			}

			List stateDistTownList = townService.getTownListOnStateDistrict(multiMap);
			townList = new ArrayList<TownListJson>();

			for(int i=1; i<stateDistTownList.size(); i++) {

				Object[] ObjList = (Object[])stateDistTownList.get(i);
				logger.info(i + "--" + ObjList[0].toString() + "--" +  ObjList[1].toString() + "--" + ObjList[2].toString());
				if(!townCodes.containsKey(ObjList[2].toString().trim())) {
					logger.info(i + "--ival");
					townListJson = new TownListJson();
					townListJson.setTownName(ObjList[k++].toString());
					townListJson.setStateCode(ObjList[k++].toString());
					townListJson.setTownId(ObjList[k++].toString());
					townListJson.setDistId(ObjList[k++].toString());

					k = 0;
					townList.add(townListJson);
				}

			}

		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		Collections.sort(townList, new TownListJson());
		return  townList;	
	}



	public void saveTerritory(String territoryDetails ){
		boolean updateFlag=false;
		Collection<Territory> territory=new ArrayList<Territory>();
		Collection<Branch> br=new ArrayList<Branch>();
		Collection<TerritoryVillTown> terrVillTown=new ArrayList<TerritoryVillTown>();
		HashMap<Integer,String> villcode=new HashMap<Integer,String>();
		HashMap<Integer,String> towncode=new HashMap<Integer,String>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		String branchId=null;
		String territoryName=null;	
		String stVillTownDist=null;

		try {
			JSONObject json = new JSONObject(territoryDetails);
			branchId=json.getString("branchId");
			territoryName=json.getString("territoryName");
			stVillTownDist=json.getString("stDist");


		} catch (JSONException e) {
			logger.fatal("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}


		System.out.println("" +	""+territoryName);
		System.out.println("stDist=="+stVillTownDist);
		List list =Arrays.asList(stVillTownDist.split(("\\s*,\\s*")));
		for(int i=0;i<list.size();i++){
			int j=0;
			logger.debug(list.size());
			logger.debug("list"+list.get(i));

			String st_dist=list.get(i).toString();
			List list2 =Arrays.asList(st_dist.split(("\\s*:\\s*")));

			TerritoryVillTown villTown=new TerritoryVillTown();
			villTown.setStateCode((Integer.parseInt(list2.get(1).toString())));
			villTown.setDistCode(Integer.parseInt(list2.get(2).toString()));
			villTown.setTownVillCode(Integer.parseInt(list2.get(3).toString()));
			villTown.setStatus(DistributionConstants.ONE);

			if(list2.get(0).toString().equals("v")){
				villTown.setLevel("village");
				villcode.put(new Integer(j++), (list2.get(3).toString()));

			}else{
				villTown.setLevel("TOWN");
				towncode.put(new Integer(j++), (list2.get(3).toString()));
			}

			terrVillTown.add(villTown);

		}
		long currentId =  BranchSequenceIdGen.getBranchSequenceIdGen();

		String terId = DistributionConstants.TERRITORY_CODE + currentId;
		Territory terr=new Territory();
		terr.setTerritory(territoryName);
		terr.setCreatedOn(new java.util.Date());
		terr.setVillTown(terrVillTown);
		terr.setStatus(DistributionConstants.ONE);
		territory.add(terr);	
		try{
			session = sf.openSession();
			session.beginTransaction();
			Query branch=session.createQuery("from Branch where branchId='"+branchId+"'");
			br=branch.list();
			for(Branch branches:br)
			{

				branches.getTerritories().add(terr);
				session.save(branches);
				session.getTransaction().commit();
			}
			RegionGeomJdbc rgjdbc = new RegionGeomJdbc();
			rgjdbc.storeGeomHibernateTerritory( villcode,towncode, terId,territoryName,DistributionConstants.TERRITORY_GEOM,  currentId,updateFlag,DistributionConstants.TERRITORY_SINGLE_DELETE);
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
	}

	public Collection<TerritoryJson> getTerritoryDetails() {

		Collection<ZoneListFinal> zone = new ArrayList<ZoneListFinal>();
		Collection<Branch> branch = new ArrayList<Branch>();
		Collection<Territory> territory = new ArrayList<Territory>();
		List<TerritoryJson> territoryJsonList = new ArrayList<TerritoryJson>();
		Collection<Region> regn = new ArrayList<Region>();
		Collection<BrDistrict> brdist = new ArrayList<BrDistrict>();
		Collection<TerritoryVillTown> terrVillTown = null;
		Collection<TerrVillTownJson> villTown= null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		TerritoryJson terrJson = null;
		TerrVillTownJson terrVillTownJson = null;

		int countzone = 0;
		int countregion = 0;
		int countbranch = 0;
		int countterritory = 0;

		try{
			session = sf.openSession();
			session.beginTransaction();
			Query zoneList = session.createQuery("from ZoneListFinal where status = "+ DistributionConstants.ONE);
			zone = zoneList.list();
			for(ZoneListFinal zoneItr : zone) {

				countzone++;


				regn=zoneItr.getRegions();
				String zoneId = zoneItr.getZoneId().toString();
				String zoneName = zoneItr.getZoneName().toString();
				for(Region r:regn){
					countregion++;
					if( r.getStatus() == DistributionConstants.ONE) {

						branch=r.getBranches();
						for(Branch b:branch){
							countbranch++;
							if( b.getStatus() == DistributionConstants.ONE) {

								territory=b.getTerritories();
								for(Territory ter:territory){
									countterritory++;
									if( ter.getStatus() == DistributionConstants.ONE) {

										terrJson = new TerritoryJson();


										terrJson.setZoneId(zoneId);
										terrJson.setZoneName(zoneName);
										terrJson.setRegionName(r.getRegion());
										terrJson.setRegionId(r.getRegionId());
										terrJson.setBranchId(b.getBranchId());
										terrJson.setBranchName(b.getBranch());
										terrJson.setTerritoyId(ter.getTerritoryId());
										terrJson.setTerritoryName(ter.getTerritory());

										villTown = new ArrayList<TerrVillTownJson>();
										terrVillTown=ter.getVillTown();
										for(TerritoryVillTown tv:terrVillTown ){
											if( tv.getStatus() == DistributionConstants.ONE){
												//System.out.println(tv.getLevel()+":"+tv.getStateCode()+":"+tv.getDistCode() + ": "+ tv.getTownVillCode());
												terrVillTownJson = new TerrVillTownJson();
												terrVillTownJson.setLevel(tv.getLevel());
												terrVillTownJson.setDistCode(new Integer(tv.getDistCode()).toString());
												terrVillTownJson.setStatecode(new Integer(tv.getStateCode()).toString());
												terrVillTownJson.setVillTownCode(new Integer(tv.getTownVillCode()));

												villTown.add(terrVillTownJson);
											}
										}
										terrJson.setStateDistVillTown(villTown);

										territoryJsonList.add(terrJson);
									}
								}
							}
						}
					}
				}
			}

			for(int i=0; i<territoryJsonList.size(); i++) {
				TerritoryJson trr = (TerritoryJson)territoryJsonList.get(i);
				System.out.println(trr.getZoneId() + "--" + trr.getZoneName() + "--" + trr.getRegionName() + "--" + trr.getRegionId() + "--" + trr.getBranchId() + "--" + trr.getBranchName() + "--" + trr.getTerritoyId() + "--" + trr.getTerritoryName() );

				/*for(String str : trr.getStateDistVillTown()) {
					System.out.println("villages----" + str);
				}*/
				//System.out.println(countzone + "--" + countregion + "--" + countbranch + "--" + countterritory);--
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return territoryJsonList;
	}

	public void updateTerritory(String territoryDetails ){
		boolean updateFlag=true;
		long currentId =  BranchSequenceIdGen.getBranchSequenceIdGen();
		Collection<TerritoryVillTown> territoryVillTownList = new ArrayList<TerritoryVillTown>();
		Collection<TerritoryVillTown> terrVillTown = new ArrayList<TerritoryVillTown>();
		HashMap<Integer,String> villcode=new HashMap<Integer,String>();
		HashMap<Integer,String> towncode=new HashMap<Integer,String>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		String territoryId = null;
		String territoryName = null;
		String stVillTownDist=null;

		try {
			JSONObject json = new JSONObject(territoryDetails);
			territoryId =  json.getString("territoryId");
			territoryName = json.getString("territoryName");
			stVillTownDist=json.getString("stDist");

		} catch (JSONException e) {
			logger.fatal("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}

		List list =Arrays.asList(stVillTownDist.split(("\\s*,\\s*")));
		int j=0;
		for(int i=0;i<list.size();i++){

			logger.debug(list.size());
			logger.debug("list"+list.get(i));

			String st_dist=list.get(i).toString();
			List list2 =Arrays.asList(st_dist.split(("\\s*:\\s*")));

			TerritoryVillTown villTown=new TerritoryVillTown();
			villTown.setStateCode((Integer.parseInt(list2.get(1).toString())));
			villTown.setDistCode(Integer.parseInt(list2.get(2).toString()));
			villTown.setTownVillCode(Integer.parseInt(list2.get(3).toString()));
			villTown.setStatus(DistributionConstants.ONE);

			if(list2.get(0).toString().equals("v")){
				villTown.setLevel("village");
				villcode.put(new Integer(j++), (list2.get(3).toString()));
			}else{
				villTown.setLevel("TOWN");
				towncode.put(new Integer(j++), (list2.get(3).toString()));
			}
			terrVillTown.add(villTown);
		}

		try {
			session = sf.openSession();
			Territory territoryDB = (Territory)session.get(Territory.class, territoryId);

			session.beginTransaction();
			territoryVillTownList = territoryDB.getVillTown();

			for(TerritoryVillTown territoryvill : territoryVillTownList) {
				territoryvill.setStatus(DistributionConstants.ZERO);
			}

			session.update(territoryDB);

			territoryDB.getVillTown().addAll(terrVillTown);
			territoryDB.setTerritory(territoryName);

			session.update(territoryDB);
			session.getTransaction().commit();
			RegionGeomJdbc rgjdbc = new RegionGeomJdbc();
			rgjdbc.storeGeomHibernateTerritory( villcode,towncode, territoryId,territoryName,DistributionConstants.TERRITORY_GEOM,  currentId,updateFlag,DistributionConstants.TERRITORY_SINGLE_DELETE);

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
				logger.fatal(e.getMessage());
			}

		}
	}


	public void deleteTerritory(String territoryDetails ){

		Collection<TerritoryVillTown> territoryVillTownDB = new ArrayList<TerritoryVillTown>();
		HashMap<Integer,String> terrId=new HashMap<Integer,String>(); 
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Territory territoryDB = null;
		String territoryId=null;
		org.json.JSONArray territoryIdArray = null;

		try {
			JSONObject json = new JSONObject(territoryDetails);
			territoryIdArray = json.getJSONArray("territoryId");
		} catch (JSONException e) {
			logger.fatal("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}

		try {
			session = sf.openSession();
			int j=0;
			for(int i=0; i<territoryIdArray.length(); i++ ) {
				territoryDB = (Territory)session.get(Territory.class, territoryIdArray.get(i).toString());
				terrId.put(new Integer(j++), territoryIdArray.get(i).toString());
				session.beginTransaction();
				territoryVillTownDB = territoryDB.getVillTown();

				for(TerritoryVillTown territoryVillTown : territoryVillTownDB) {
					territoryVillTown.setStatus(DistributionConstants.ZERO);
				}

				//to update the previous districts in branch status to zero
				territoryDB.setStatus(DistributionConstants.ZERO);
				session.update(territoryDB);
				session.getTransaction().commit();
			}
			RegionGeomJdbc rgjdbc = new RegionGeomJdbc();
			rgjdbc.deleteGeom(terrId,DistributionConstants.TERRITORY_DELETE_ALL);	

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


	public boolean vildateTerritoryName(String territoryDetails) {

		Collection<Branch> branch = new ArrayList<Branch>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		boolean territoryNameStatus = false;
		Collection<Territory> territorys = new ArrayList<Territory>();

		try {
			session = sf.openSession();

			JSONObject json = new JSONObject(territoryDetails);
			String territoryName = json.getString("territoryName");
			String branchId = json.getString("branchId");

			Query branchList = session.createQuery("from Branch where branchId = '"+ branchId.trim() + "'" );
			branch = branchList.list();

			for(Branch branches : branch) {
				if(branches.getStatus()== DistributionConstants.ONE){
					territorys = branches.getTerritories();
					for(Territory territory : territorys) {
						if(territory.getStatus()== DistributionConstants.ONE){
							if(territory.getTerritory().toString().trim().equals(territoryName.trim())) {
								territoryNameStatus = true;
							}
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return territoryNameStatus;
	}
	public List<TerritoryService> getTerritoryByBranch(String branchId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		List<TerritoryService> territory=new ArrayList<TerritoryService>();
		List<Branch> terrList= new ArrayList<Branch>();
		List<Territory> terrLists= new ArrayList<Territory>();
		//session = sf.openSession();
		String brId=null;
		try {
			JSONObject json = new JSONObject(branchId);
			brId=json.getString("branchId");

		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}

		try{

			session = sf.openSession();
			session.beginTransaction();
			Query terr=session.createQuery("from Branch where branchId='"+brId+"'");
			terrList=terr.list();
			for(Branch branch:terrList){
				terrLists = (List<Territory>) branch.getTerritories();
				for(Territory terrs:terrLists){
					TerritoryService ter = new TerritoryService();
					ter.setTerrId(terrs.getTerritoryId());
					ter.setTerrName(terrs.getTerritory());
					territory.add(ter);
				}
			}
			Collections.sort(territory, new TerritoryService());
			
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return territory;
	}
	public List<TerrVillTownJson> getVillTownByTerritory(String territoryId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		List<TerritoryService> territory=new ArrayList<TerritoryService>();
		List<Territory> terrList= new ArrayList<Territory>();
		List<TerritoryVillTown> villTownList= new ArrayList<TerritoryVillTown>();
		HashMap<Integer, Integer> villIdHm = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> townIdHm = new HashMap<Integer, Integer>();
		//session = sf.openSession();
		String terrId=null;
		try {
			JSONObject json = new JSONObject(territoryId);
			terrId=json.getString("territoryId");

		} catch (JSONException e) {
			System.out.println("JSON Exception while Coverting to jSon Object");
			e.printStackTrace();
		}

		try{

			int villIds = DistributionConstants.ONE;
			int townIds = DistributionConstants.TWO;
			
			session = sf.openSession();
			session.beginTransaction();
			Query terr=session.createQuery("from Territory where territoryId='"+terrId+"'");
			terrList=terr.list();
			for( Territory ter:terrList){
				villTownList =  (List<TerritoryVillTown>) ter.getVillTown();
				for(TerritoryVillTown villtown :villTownList){
					
					if(villtown.getLevel().trim().toLowerCase().equals(DistributionConstants.VILLAGE.trim().toLowerCase())) {
						villIdHm.put(new Integer(villIds++), villtown.getTownVillCode());
					} else if(villtown.getLevel().trim().toLowerCase().equals(DistributionConstants.TOWN.trim().toLowerCase())) {
						townIdHm.put(new Integer(townIds++), villtown.getTownVillCode());
					}

				}
			}
			
			VillageDefinition villageService = new VillageService();
			List villDetails = villageService.getListOfVillageDetailsOnVillId(villIdHm);
			
			if(townIdHm.isEmpty()) {
				logger.info("town ids -" + townIdHm.isEmpty());
			}
			
			TownDefinition townService = new TownService();
			List townDetails = townService.getListOfTownDetailsOnTownId(townIdHm);
			
			TerrVillTownJson villTn = null;
			
			List<TerrVillTownJson> terrVillTownJson = new ArrayList<TerrVillTownJson>();
			
			int i=0;
			for(int k=1; k<villDetails.size(); k++) {
				
				villTn =  new TerrVillTownJson();
				Object[] ObjList = (Object[])villDetails.get(k);
				
				villTn.setVillTownName(ObjList[i++].toString());
				villTn.setVillTownCode(new Integer(ObjList[i++].toString()).intValue());
				villTn.setLevel(DistributionConstants.VILLAGE);
				i=0;
				
				terrVillTownJson.add(villTn);
			}
			
			for(int k=1; k<townDetails.size(); k++) {
				
				villTn =  new TerrVillTownJson();
				Object[] ObjList = (Object[])townDetails.get(k);
				
				villTn.setVillTownName(ObjList[i++].toString());
				villTn.setVillTownCode(new Integer(ObjList[i++].toString()).intValue());
				villTn.setLevel(DistributionConstants.TOWN.toUpperCase());
				i=0;
				
				terrVillTownJson.add(villTn);
			}
			
			//Collections.sort(territory, new TerritoryService());
			Collections.sort(terrVillTownJson, new TerrVillTownJson());
			
			return terrVillTownJson;
			
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return null;
	}
	
		public static void main(String args[]) {

			TerritoryServiceDao territoryServiceDao = new TerritoryServiceDao();
			/*String input="{'territoryId': 'T32','territoryName':'territryA','zoneId':'ZN278','regId':'R230','branchId':'B66','stDist':'v:27:5:209,v:27:5:343,v:27:5:738,t:27:5:372'}";
		String territoryDelete = "{'territoryId': ['T64','T65']}";

		String territoryDetails = "{'territoryName':'territory north 2','branchId':'B97'}";
		boolean retval = territoryServiceDao.vildateTerritoryName(territoryDetails);
		System.out.println("retval -" + retval);
			 */

			String input="{'branchId':'B163'}";
			territoryServiceDao.getTerritoryByBranch(input);
			//territoryServiceDao.saveTerritory(input);

			/*Collection<TerritoryJson> terr1 = territoryServiceDao.getTerritoryDetails();

		System.out.println(terr1.size());*/

			/*for(int k=0; k<terr1.size(); k++) {
			System.out.println("------f----" + ((TerritoryJson)terr).getTerritoyId()+"--------"+((TerritoryJson)iter.next()).getStateDistVillTown());
		}*/

			/*Iterator iter = terr1.iterator();

		while(iter.hasNext()) {

			System.out.println("------f----" + ((TerritoryJson)iter.next()).getTerritoyId()+"--------"+((TerritoryJson)iter.next()).getStateDistVillTown());
		} */

			/*for(TerritoryJson t : terr1 ) {
			System.out.println("t--" + t.getTerritoyId());
		}*/
			/*String inputString = "{'regId':'R214'}";
		territoryServiceDao.getBranches(inputString);

		String inputBranchString = "{'branchId':'B64'}";
		Collection<VillageListJson> villageCollection =  territoryServiceDao.getVillageList(inputBranchString);

		Iterator iter = villageCollection.iterator();
		while(iter.hasNext()) {
			VillageListJson villageListJson  =  (VillageListJson)iter.next();
			System.out.println("village----------------" + villageListJson.getDistId() + "--" + villageListJson.getVillageId() + "--" + villageListJson.getVillageName() );
		}

		/*String inputSaveTerritory = "{ 'territorydetails': {'territory': 'jhsalgkjsdhlkgjh','region': '26','branch': '25','zone': '75','state': [{'stateid': '24','village': {'district': [{'distid': '14','villagename': 'panvel','villageid': '15'},{'distid': '14','villagename': 'jhsglk','villageid': '45'}]},'town': {'district': [{'distid': '12','villagename': 'meru','villageid': '14'},{'distid': '14','villagename': 'rtiiu','villageid': '45'}]}},{'stateid': '22','village': {'district': [{'distid': '14','villagename': 'panvel','villageid': '15'},{'distid': '14','villagename': 'jhsglk','villageid': '45'}]},'town': {'district': [{'distid': '14','villagename': 'meru','villageid': '14'},{'distid': '14','villagename': 'rtiiu','villageid': '45'}]}}]}}";*/

		}
	}
