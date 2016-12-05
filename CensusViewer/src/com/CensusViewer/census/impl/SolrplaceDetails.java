package com.CensusViewer.census.impl;



import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.CensusViewer.census.model.SolrSearch;
import com.CensusViewer.census.services.customerservice.SolrSearchService;



public class SolrplaceDetails {
	SolrSearch solrsearch =null;


	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);

	public SolrSearch searchDetails(String villageCode) {
		logger.info("Entering " + CLASS_NAME);
		int k = 0;
		HashMap villageCodes = new HashMap();
		villageCodes.put(new Integer(1),villageCode);
		SolrSearchService search=new SolrSearchService();
		List searchList=	search.getVillageList(villageCodes);


		for(int i=1; i<searchList.size(); i++) {

			Object[] ObjList=   (Object[]) searchList.get(i);
			solrsearch=new SolrSearch();
			//int value=((Integer)ObjList[k++]).intValue();
			try{solrsearch.setTot_p(((Integer)ObjList[k++]).intValue());}catch(Exception e){solrsearch.setTot_p(0);}
			//int value1=((Integer)ObjList[k++]).intValue();
			try{solrsearch.setTot_irr(((Integer)ObjList[k++]).intValue());}catch(Exception e){solrsearch.setTot_irr(0);}
			try{solrsearch.setPower((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setPower(0);}
			try{solrsearch.setEducation((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setEducation(0);}
			try{solrsearch.setHospital((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setHospital(0);}
			try{solrsearch.setTot_rec((Integer)(ObjList[k++]));}catch(Exception e){solrsearch.setTot_rec(0);}
			try{solrsearch.setNo_of_comm_inst((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_comm_inst(0);}
			try{solrsearch.setNo_of_watersrc((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_watersrc(0);}
			try{solrsearch.setEntertain((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setEntertain(0);}
			try{solrsearch.setTot_work_p((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_work_p(0);}
			try{solrsearch.setNo_of_transport_mode((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_transport_mode(0);}
			try{solrsearch.setTot_exp((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_exp(0);}
			try{solrsearch.setTot_inc((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_inc(0);}
			try{solrsearch.setNo_of_comm_mode((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_comm_mode(0);}
			
			try{solrsearch.setGid((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setGid(0);}
			
			try{solrsearch.setLevel(( ObjList[k++]).toString());}catch(Exception e){solrsearch.setGid(0);}
			try{solrsearch.setLat((Double) ObjList[k++]);}catch(Exception e){solrsearch.setLat(0.0);}
			try{solrsearch.setLongitude((Double) ObjList[k++]);}catch(Exception e){solrsearch.setLongitude(0.0);}
			try{solrsearch.setName((String) ObjList[k++]);}catch(Exception e){solrsearch.setName("N/A");}
			try{solrsearch.setService((Integer)ObjList[k++]);}catch(Exception e){ solrsearch.setService(0);}
			try{solrsearch.setPap_mag_src((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setPap_mag_src(0);}

						k=0;


		}

		return solrsearch;


	}
	public SolrSearch townSearchDetails(String towncode) {
		logger.info("Entering " + CLASS_NAME);
		int k = 0;
		HashMap townCodes = new HashMap();
		townCodes.put(new Integer(1),towncode);
		SolrSearchService search=new SolrSearchService();
		List searchList=	search.getTownList(townCodes);


		for(int i=1; i<searchList.size(); i++) {

			Object[] ObjList=   (Object[]) searchList.get(i);
			solrsearch=new SolrSearch();
			//int value=((Integer)ObjList[k++]).intValue();
			try{solrsearch.setTot_p(((Integer)ObjList[k++]).intValue());}catch(Exception e){solrsearch.setTot_p(0);}
			//int value1=((Integer)ObjList[k++]).intValue();
			try{solrsearch.setTot_irr(((Integer)ObjList[k++]).intValue());}catch(Exception e){solrsearch.setTot_irr(0);}
			try{ solrsearch.setPower((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setPower(0);}
			try{solrsearch.setEducation((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setEducation(0);}
			try{solrsearch.setHospital((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setHospital(0);}
			try{solrsearch.setTot_rec(Integer.parseInt((ObjList[k++].toString())));}catch(Exception e){solrsearch.setTot_rec(0);}
			try{solrsearch.setNo_of_comm_inst((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_comm_inst(0);}
			try{solrsearch.setNo_of_watersrc((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_watersrc(0);}
			try{solrsearch.setEntertain((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setEntertain(0);}
			try{solrsearch.setTot_work_p((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_work_p(0);}
			try{solrsearch.setNo_of_transport_mode((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_transport_mode(0);}
			try{solrsearch.setTot_exp((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_exp(0);}
			try{solrsearch.setTot_inc((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_inc(0);}
			try{solrsearch.setNo_of_comm_mode((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_comm_mode(0);}
			try{solrsearch.setGid((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setGid(0);}
			try{solrsearch.setLevel(( ObjList[k++]).toString());}catch(Exception e){solrsearch.setGid(0);}
			try{solrsearch.setLat((Double) ObjList[k++]);}catch(Exception e){solrsearch.setLat(0.0);}
			try{solrsearch.setLongitude((Double) ObjList[k++]);}catch(Exception e){solrsearch.setLongitude(0.0);}
			try{solrsearch.setName((String) ObjList[k++]);}catch(Exception e){solrsearch.setName("N/A");}
			try{solrsearch.setService((Integer)ObjList[k++]);}catch(Exception e){ solrsearch.setService(0);}
			try{solrsearch.setPap_mag_src((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setPap_mag_src(0);}
			k=0;


		}





		return solrsearch;


	}

	public SolrSearch districtSearchDetails(String distcode) {
		logger.info("Entering " + CLASS_NAME);
		int k = 0;
		//	Integer distid=Integer.parseInt(distcode);
		HashMap distCodes = new HashMap();
		distCodes.put(new Integer(1),distcode);
		SolrSearchService search=new SolrSearchService();
		List searchList=	search.getDistList(distCodes);


		for(int i=1; i<searchList.size(); i++) {

			Object[] ObjList=   (Object[]) searchList.get(i);
			solrsearch=new SolrSearch();
			try{solrsearch.setTot_p(((Integer)ObjList[k++]).intValue());}catch(Exception e){solrsearch.setTot_p(0);}
			try{solrsearch.setTot_irr(((Integer)ObjList[k++]).intValue());}catch(Exception e){solrsearch.setTot_irr(0);}
			try{ solrsearch.setPower((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setPower(0);}
			try{solrsearch.setEducation((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setEducation(0);}
			try{solrsearch.setHospital((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setHospital(0);}
			try{solrsearch.setTot_rec((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_rec(0);}
			try{solrsearch.setNo_of_comm_inst((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_comm_inst(0);}
			try{solrsearch.setNo_of_watersrc((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_watersrc(0);}
			try{solrsearch.setEntertain((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setEntertain(0);}
			try{solrsearch.setTot_work_p((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_work_p(0);}
			try{solrsearch.setNo_of_transport_mode((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_transport_mode(0);}
			try{solrsearch.setTot_exp((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_exp(0);}
			try{solrsearch.setTot_inc((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_inc(0);}
			try{solrsearch.setNo_of_comm_mode((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_comm_mode(0);}
			try{solrsearch.setGid((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setGid(0);}
			try{solrsearch.setLevel(( ObjList[k++]).toString());}catch(Exception e){solrsearch.setGid(0);}
			try{solrsearch.setLat((Double) ObjList[k++]);}catch(Exception e){solrsearch.setLat(0.0);}
			try{solrsearch.setLongitude((Double) ObjList[k++]);}catch(Exception e){solrsearch.setLongitude(0.0);}
			try{solrsearch.setName((String) ObjList[k++]);}catch(Exception e){solrsearch.setName("N/A");}
			try{solrsearch.setService((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setService(0);}
			try{solrsearch.setPap_mag_src((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setPap_mag_src(0);}
			k=0;




		}





		return solrsearch;


	}
	public SolrSearch stateSearchDetails(String stcode) {
		logger.info("Entering " + CLASS_NAME);
		int k = 0;
		HashMap StateCodes = new HashMap();
		StateCodes.put(new Integer(1),stcode);
		SolrSearchService search=new SolrSearchService();
		List searchList=	search.getStateList(StateCodes );


		for(int i=1; i<searchList.size(); i++) {

			Object[] ObjList=   (Object[]) searchList.get(i);
			solrsearch=new SolrSearch();
			//int value=((Integer)ObjList[k++]).intValue();
			try{solrsearch.setTot_p(((Integer)ObjList[k++]).intValue());}catch(Exception e){solrsearch.setTot_p(0);}
			//int value1=((Integer)ObjList[k++]).intValue();
			try{solrsearch.setTot_irr(((Integer)ObjList[k++]).intValue());}catch(Exception e){solrsearch.setTot_irr(0);}
			try{ solrsearch.setPower((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setPower(0);}
			try{solrsearch.setEducation((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setEducation(0);}
			try{solrsearch.setHospital((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setHospital(0);}
			try{solrsearch.setTot_rec((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_rec(0);}
			try{solrsearch.setNo_of_comm_inst((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_comm_inst(0);}
			try{solrsearch.setNo_of_watersrc((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_watersrc(0);}
			try{solrsearch.setEntertain((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setEntertain(0);}
			try{solrsearch.setTot_work_p((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_work_p(0);}
			try{solrsearch.setNo_of_transport_mode((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_transport_mode(0);}
			try{solrsearch.setTot_exp((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_exp(0);}
			try{solrsearch.setTot_inc((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setTot_inc(0);}
			try{solrsearch.setNo_of_comm_mode((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setNo_of_comm_mode(0);}
			try{solrsearch.setService((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setService(0);}
			try{solrsearch.setPap_mag_src((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setPap_mag_src(0);}
			try{solrsearch.setGid((Integer) ObjList[k++]);}catch(Exception e){solrsearch.setGid(0);}
			try{solrsearch.setLevel(( ObjList[k++]).toString());}catch(Exception e){solrsearch.setGid(0);}
			try{solrsearch.setLat((Double) ObjList[k++]);}catch(Exception e){solrsearch.setLat(0.0);}
			try{solrsearch.setLongitude((Double) ObjList[k++]);}catch(Exception e){solrsearch.setLongitude(0.0);}
			try{solrsearch.setName((String) ObjList[k++]);}catch(Exception e){solrsearch.setName("N/A");}
			
			k=0;



		}





		return solrsearch;


	}

}
