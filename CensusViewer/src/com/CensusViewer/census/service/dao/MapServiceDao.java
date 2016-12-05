/**
 * 
 */
package com.CensusViewer.census.service.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.CensusViewer.census.model.JacksonMap;
import com.CensusViewer.census.model.MapDistricts;
import com.CensusViewer.census.model.MapTehsil;
import com.CensusViewer.census.model.MapTowns;
import com.CensusViewer.census.model.MapVillage;
import com.CensusViewer.census.services.customerservice.MapService;

/**
 * @author bhupendras
 *
 */
public class MapServiceDao {


	public MapDistricts getDistMapInfo(String jsonString) {
		System.out.println("jsonString=="+jsonString);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MapDistricts mapdis=null;

		try {

			// convert user object to json string, and save to a file
			JacksonMap  map=mapper.readValue(jsonString,JacksonMap.class );
			System.out.println("district");

			float lat=	map.getLat();
			float lon=map.getLon();


			MapService ms = new MapService();

			HashMap hm = new HashMap();
			hm.put(new Integer(1),lon);
			hm.put(new Integer(2),lat);

			List list = ms.getDistrictSelectedData(hm);
			for(int i=1; i< list.size(); i++) {

				Object[] object =(Object[])list.get(i);

				mapdis=new MapDistricts();

				try{	mapdis.setTot_p(Integer.parseInt(object[0].toString()));}catch(Exception e){mapdis.setTot_p(0);}
				try{	mapdis.setTot_irr(Integer.parseInt(object[1].toString()));}catch(Exception e){mapdis.setTot_irr(0);}
				try{	mapdis.setPower(Integer.parseInt(object[2].toString()));}catch(Exception e){mapdis.setPower(0);}
				try{	mapdis.setEducation(Integer.parseInt(object[3].toString()));}catch(Exception e){mapdis.setEducation(0);}
				try{	mapdis.setHospital(Integer.parseInt(object[4].toString()));}catch(Exception e){mapdis.setHospital(0);}
				try{	mapdis.setTot_rec(Integer.parseInt(object[5].toString()));}catch(Exception e){mapdis.setTot_rec(0);}
				try{	mapdis.setNo_of_comm_inst(Integer.parseInt(object[6].toString()));}catch(Exception e){mapdis.setNo_of_comm_inst(0);}
				try{	mapdis.setNo_of_watersrc(Integer.parseInt(object[7].toString()));}catch(Exception e){mapdis.setNo_of_watersrc(0);}
				try{	mapdis.setEntertain(Integer.parseInt(object[8].toString()));}catch(Exception e){mapdis.setEntertain(0);}
				try{	mapdis.setTot_work_p(Integer.parseInt(object[9].toString()));}catch(Exception e){mapdis.setTot_work_p(0);}
				try{	mapdis.setNo_of_transport_mode(Integer.parseInt(object[10].toString()));}catch(Exception e){mapdis.setNo_of_transport_mode(0);}
				try{	mapdis.setTot_exp(Integer.parseInt(object[11].toString()));}catch(Exception e){mapdis.setTot_exp(0);}
				try{	mapdis.setTot_inc(Integer.parseInt(object[12].toString()));}catch(Exception e){mapdis.setTot_inc(0);}
				try{	mapdis.setNo_of_comm_mode(Integer.parseInt(object[13].toString()));}catch(Exception e){mapdis.setNo_of_comm_mode(0);}
				try{	mapdis.setGid(Integer.parseInt(object[14].toString()));}catch(Exception e){mapdis.setGid(0);}
				try{	mapdis.setLevel(object[15].toString());}catch(Exception e){mapdis.setLevel("N/A");}
				try{	mapdis.setName(object[16].toString());}catch(Exception e){mapdis.setName("N/A");}
				try{	mapdis.setStatecode(object[17].toString());}catch(Exception e){mapdis.setStatecode("N/A");}
				try{	mapdis.setDistcode(object[18].toString());}catch(Exception e){mapdis.setDistcode("N/A");}

				try{	mapdis.setLat(Double.parseDouble(object[19].toString()));}catch(Exception e){mapdis.setLat(0.0);}
				try{	mapdis.setLongitude(Double.parseDouble(object[20].toString()));}catch(Exception e){mapdis.setLongitude(0.0);}
				try{	mapdis.setService(Integer.parseInt(object[21].toString()));}catch(Exception e){mapdis.setService(0);}
				try{	mapdis.setPap_mag_src(Integer.parseInt(object[22].toString()));}catch(Exception e){mapdis.setPap_mag_src(0);}



			}





		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();
			return null;
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}


		return mapdis;




	}

	public MapTehsil gettehsilMapInfo(String jsonString) {
		System.out.println("jsonString=="+jsonString);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		MapTehsil maptehsil=null;
		try {

			// convert user object to json string, and save to a file
			JacksonMap  map=mapper.readValue(jsonString,JacksonMap.class );
			System.out.println("Tehsil");


			float lat=	map.getLat();

			float lon=map.getLon();

			MapService ms = new MapService();

			HashMap hm = new HashMap();
			hm.put(new Integer(1),lon);
			hm.put(new Integer(2),lat);
			List list = ms.getTehsilSelectedData(hm);
			for(int i=1; i< list.size(); i++) {

				Object[] object =(Object[])list.get(i);



				//  System.out.println(myPerson.getcountryName());

				maptehsil=new MapTehsil();
//				try{	maptehsil.setTot_p(Integer.parseInt(object[0].toString()));}catch(Exception e){maptehsil.setTot_p(0);}
//				try{	maptehsil.setTot_m(Integer.parseInt(object[1].toString()));}catch(Exception e){maptehsil.setTot_m(0);}
//				try{	maptehsil.setTot_f(Integer.parseInt(object[2].toString()));}catch(Exception e){maptehsil.setTot_f(0);}
//				try{	maptehsil.setP_lit_per(Integer.parseInt(object[3].toString()));}catch(Exception e){maptehsil.setP_lit_per(0);}
//				try{	maptehsil.setM_lit_per(Integer.parseInt(object[4].toString()));}catch(Exception e){maptehsil.setM_lit_per(0);}
//				try{	maptehsil.setF_lit_per(Integer.parseInt(object[5].toString()));}catch(Exception e){maptehsil.setF_lit_per(0);}
//
//				try{	maptehsil.setP_lit(Integer.parseInt(object[6].toString()));}catch(Exception e){maptehsil.setP_lit(0);}
//				try{	maptehsil.setM_lit(Integer.parseInt(object[7].toString()));}catch(Exception e){maptehsil.setM_lit(0);}
//				try{	maptehsil.setF_lit(Integer.parseInt(object[8].toString()));}catch(Exception e){maptehsil.setF_lit(0);}
				maptehsil.setTot_p(0);
			    maptehsil.setTot_irr(0);
				maptehsil.setPower(0);
				maptehsil.setEducation(0);
				maptehsil.setHospital(0);
				maptehsil.setTot_rec(0);
				maptehsil.setNo_of_comm_inst(0);
				maptehsil.setNo_of_watersrc(0);
				maptehsil.setEntertain(0);
				maptehsil.setTot_work_p(0);
				maptehsil.setNo_of_transport_mode(0);
				maptehsil.setTot_exp(0);
				maptehsil.setTot_inc(0);
				maptehsil.setNo_of_comm_mode(0);

				try{	maptehsil.setGid(Integer.parseInt(object[9].toString()));}catch(Exception e){maptehsil.setGid(0);}
				try{	maptehsil.setLevel(object[10].toString());}catch(Exception e){maptehsil.setLevel("N/A");}
				try{	maptehsil.setName(object[11].toString());}catch(Exception e){maptehsil.setName("N/A");}
				try{	maptehsil.setStatecode(object[12].toString());}catch(Exception e){maptehsil.setStatecode("N/A");}
				try{	maptehsil.setDistcode(object[13].toString());}catch(Exception e){maptehsil.setDistcode("N/A");}
				try{	maptehsil.setLat(Double.parseDouble(object[14].toString()));}catch(Exception e){maptehsil.setLat(0.0);}
				try{	maptehsil.setLongitude(Double.parseDouble(object[15].toString()));}catch(Exception e){maptehsil.setLongitude(0.0);}

				
				

//				try{	maptehsil.setTot_p(Integer.parseInt(object[0].toString()));}catch(Exception e){maptehsil.setTot_p(0);}
//				try{	maptehsil.setTot_irr(Integer.parseInt(object[1].toString()));}catch(Exception e){maptehsil.setTot_irr(0);}
//				try{	maptehsil.setPower(Integer.parseInt(object[2].toString()));}catch(Exception e){maptehsil.setPower(0);}
//				try{	maptehsil.setEducation(Integer.parseInt(object[3].toString()));}catch(Exception e){maptehsil.setEducation(0);}
//				try{	maptehsil.setHospital(Integer.parseInt(object[4].toString()));}catch(Exception e){maptehsil.setHospital(0);}
//				try{	maptehsil.setTot_rec(Integer.parseInt(object[5].toString()));}catch(Exception e){maptehsil.setTot_rec(0);}
//				try{	maptehsil.setNo_of_comm_inst(Integer.parseInt(object[6].toString()));}catch(Exception e){maptehsil.setNo_of_comm_inst(0);}
//				try{	maptehsil.setNo_of_watersrc(Integer.parseInt(object[7].toString()));}catch(Exception e){maptehsil.setNo_of_watersrc(0);}
//				try{	maptehsil.setEntertain(Integer.parseInt(object[8].toString()));}catch(Exception e){maptehsil.setEntertain(0);}
//				try{	maptehsil.setTot_work_p(Integer.parseInt(object[9].toString()));}catch(Exception e){maptehsil.setTot_work_p(0);}
//				try{	maptehsil.setNo_of_transport_mode(Integer.parseInt(object[10].toString()));}catch(Exception e){maptehsil.setNo_of_transport_mode(0);}
//				try{	maptehsil.setTot_exp(Integer.parseInt(object[11].toString()));}catch(Exception e){maptehsil.setTot_exp(0);}
//				try{	maptehsil.setTot_inc(Integer.parseInt(object[12].toString()));}catch(Exception e){maptehsil.setTot_inc(0);}
//				try{	maptehsil.setNo_of_comm_mode(Integer.parseInt(object[13].toString()));}catch(Exception e){maptehsil.setNo_of_comm_mode(0);}
//				try{	maptehsil.setGid(Integer.parseInt(object[14].toString()));}catch(Exception e){maptehsil.setGid(0);}
//				try{	maptehsil.setLevel(object[15].toString());}catch(Exception e){maptehsil.setLevel("N/A");}
//				try{	maptehsil.setName(object[16].toString());}catch(Exception e){maptehsil.setName("N/A");}
//				try{	maptehsil.setStatecode(object[17].toString());}catch(Exception e){maptehsil.setStatecode("N/A");}
//				try{	maptehsil.setDistcode(object[18].toString());}catch(Exception e){maptehsil.setDistcode("N/A");}
//
//				try{	maptehsil.setLat(Double.parseDouble(object[19].toString()));}catch(Exception e){maptehsil.setLat(0.0);}
//				try{	maptehsil.setLongitude(Double.parseDouble(object[20].toString()));}catch(Exception e){maptehsil.setLongitude(0.0);}
//

//				
//				maptehsil.setTot_p(0);
//			    maptehsil.setTot_irr(0);
//				maptehsil.setPower(0);
//				maptehsil.setEducation(0);
//				maptehsil.setHospital(0);
//				maptehsil.setTot_rec(0);
//				maptehsil.setNo_of_comm_inst(0);
//				maptehsil.setNo_of_watersrc(0);
//				maptehsil.setEntertain(0);
//				maptehsil.setTot_work_p(0);
//				maptehsil.setNo_of_transport_mode(0);
//				maptehsil.setTot_exp(0);
//				maptehsil.setTot_inc(0);
//				maptehsil.setNo_of_comm_mode(0);
//				maptehsil.setGid(0);
//				maptehsil.setLevel("N/A");
//				maptehsil.setName("N/A");
//				maptehsil.setStatecode("N/A");
//				maptehsil.setDistcode("N/A");
//				maptehsil.setLat(0.0);
//				maptehsil.setLongitude(0.0);
//				
//				

			}



		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();
			return null;
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}


		return maptehsil;




	}


	public MapTowns gettownsMapInfo(String jsonString) {
		System.out.println("jsonString=="+jsonString);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		MapTowns maptowns=null;
		try {

			// convert user object to json string, and save to a file
			JacksonMap  map=mapper.readValue(jsonString,JacksonMap.class );
			System.out.println("Towns");
			float lat=	map.getLat();
			float lon=map.getLon();

			MapService ms = new MapService();

			HashMap hm = new HashMap();
			hm.put(new Integer(1),lon);
			hm.put(new Integer(2),lat);

			List list = ms.getTownsSelectedData(hm);
			for(int i=1; i< list.size(); i++) {

				Object[] object =(Object[])list.get(i);

				//  System.out.println(myPerson.getcountryName());

				maptowns=new MapTowns();
				try{	maptowns.setTot_p(Integer.parseInt(object[0].toString()));}catch(Exception e){maptowns.setTot_p(0);}

				try{	maptowns.setTot_irr(Integer.parseInt(object[1].toString()));}catch(Exception e){maptowns.setTot_irr(0);}
				try{	maptowns.setPower(Integer.parseInt(object[2].toString()));}catch(Exception e){maptowns.setPower(0);}
				try{	maptowns.setEducation(Integer.parseInt(object[3].toString()));}catch(Exception e){maptowns.setEducation(0);}
				try{	maptowns.setHospital(Integer.parseInt(object[4].toString()));}catch(Exception e){maptowns.setHospital(0);}
				try{	maptowns.setTot_rec(Integer.parseInt(object[5].toString()));}catch(Exception e){maptowns.setTot_rec(0);}
				try{	maptowns.setNo_of_comm_inst(Integer.parseInt(object[6].toString()));}catch(Exception e){maptowns.setNo_of_comm_inst(0);}
				try{	maptowns.setNo_of_watersrc(Integer.parseInt(object[7].toString()));}catch(Exception e){maptowns.setNo_of_watersrc(0);}
				try{	maptowns.setEntertain(Integer.parseInt(object[8].toString()));}catch(Exception e){maptowns.setEntertain(0);}
				try{	maptowns.setTot_work_p(Integer.parseInt(object[9].toString()));}catch(Exception e){maptowns.setTot_work_p(0);}
				try{	maptowns.setNo_of_transport_mode(Integer.parseInt(object[10].toString()));}catch(Exception e){maptowns.setNo_of_transport_mode(0);}
				try{	maptowns.setTot_exp(Integer.parseInt(object[11].toString()));}catch(Exception e){maptowns.setTot_exp(0);}
				try{	maptowns.setTot_inc(Integer.parseInt(object[12].toString()));}catch(Exception e){maptowns.setTot_inc(0);}
				try{	maptowns.setNo_of_comm_mode(Integer.parseInt(object[13].toString()));}catch(Exception e){maptowns.setNo_of_comm_mode(0);}
				
				try{	maptowns.setGid(Integer.parseInt(object[14].toString()));}catch(Exception e){maptowns.setGid(0);}
				try{	maptowns.setLevel(object[15].toString());}catch(Exception e){maptowns.setLevel("N/A");}
				try{	maptowns.setName(object[16].toString());}catch(Exception e){maptowns.setName("N/A");}
				try{	maptowns.setStatecode(object[17].toString());}catch(Exception e){maptowns.setStatecode("N/A");}
				try{	maptowns.setDistcode(object[18].toString());}catch(Exception e){maptowns.setDistcode("N/A");}
				try{	maptowns.setLat(Double.parseDouble(object[19].toString()));}catch(Exception e){maptowns.setLat(0.0);}
				try{	maptowns.setLongitude(Double.parseDouble(object[20].toString()));}catch(Exception e){maptowns.setLongitude(0.0);}
				try{	maptowns.setService(Integer.parseInt(object[21].toString()));}catch(Exception e){maptowns.setService(0);}
				try{	maptowns.setPap_mag_src(Integer.parseInt(object[22].toString()));}catch(Exception e){maptowns.setPap_mag_src(0);}
				try{	maptowns.setTownId(Integer.parseInt(object[23].toString()));}catch(Exception e){maptowns.setTownId(0);}

			}



		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();
			return null;
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}


		return maptowns;




	}

	public MapVillage getvillageMapInfo(String jsonString) {
		System.out.println("jsonString=="+jsonString);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		MapVillage mapvillage=null;
		try {

			// convert user object to json string, and save to a file
			JacksonMap  map=mapper.readValue(jsonString,JacksonMap.class );
			System.out.println("village");
			float lat=	map.getLat();
			float lon=map.getLon();

			MapService ms = new MapService();

			HashMap hm = new HashMap();
			hm.put(new Integer(1),lon);
			hm.put(new Integer(2),lat);
			List list = ms.getVillageSelectedData(hm);
			for(int i=1; i< list.size(); i++) {

				Object[] object =(Object[])list.get(i);



				mapvillage=new MapVillage();
				try{	mapvillage.setTot_p(Integer.parseInt(object[0].toString()));}catch(Exception e){mapvillage.setTot_p(0);}

				try{	mapvillage.setTot_irr(Integer.parseInt(object[1].toString()));}catch(Exception e){mapvillage.setTot_irr(0);}
				try{	mapvillage.setPower(Integer.parseInt(object[2].toString()));}catch(Exception e){mapvillage.setPower(0);}
				try{	mapvillage.setEducation(Integer.parseInt(object[3].toString()));}catch(Exception e){mapvillage.setEducation(0);}
				try{	mapvillage.setHospital(Integer.parseInt(object[4].toString()));}catch(Exception e){mapvillage.setHospital(0);}
				try{	mapvillage.setTot_rec(Integer.parseInt(object[5].toString()));}catch(Exception e){mapvillage.setTot_rec(0);}
				try{	mapvillage.setNo_of_comm_inst(Integer.parseInt(object[6].toString()));}catch(Exception e){mapvillage.setNo_of_comm_inst(0);}
				try{	mapvillage.setNo_of_watersrc(Integer.parseInt(object[7].toString()));}catch(Exception e){mapvillage.setNo_of_watersrc(0);}
				try{	mapvillage.setEntertain(Integer.parseInt(object[8].toString()));}catch(Exception e){mapvillage.setEntertain(0);}
				try{	mapvillage.setTot_work_p(Integer.parseInt(object[9].toString()));}catch(Exception e){mapvillage.setTot_work_p(0);}
				try{	mapvillage.setNo_of_transport_mode(Integer.parseInt(object[10].toString()));}catch(Exception e){mapvillage.setNo_of_transport_mode(0);}
				try{	mapvillage.setTot_exp(Integer.parseInt(object[11].toString()));}catch(Exception e){mapvillage.setTot_exp(0);}
				try{	mapvillage.setTot_inc(Integer.parseInt(object[12].toString()));}catch(Exception e){mapvillage.setTot_inc(0);}
				try{	mapvillage.setNo_of_comm_mode(Integer.parseInt(object[13].toString()));}catch(Exception e){mapvillage.setNo_of_comm_mode(0);}
				

				try{	mapvillage.setGid(Integer.parseInt(object[14].toString()));}catch(Exception e){mapvillage.setGid(0);}
				try{	mapvillage.setLevel(object[15].toString());}catch(Exception e){mapvillage.setLevel("Village");}
				try{	mapvillage.setName(object[16].toString());}catch(Exception e){mapvillage.setName("N/A");}
				try{	mapvillage.setStatecode(object[17].toString());}catch(Exception e){mapvillage.setStatecode("N/A");}
				try{	mapvillage.setDistcode(object[18].toString());}catch(Exception e){mapvillage.setDistcode("N/A");}
				try{	mapvillage.setLat(Double.parseDouble(object[19].toString()));}catch(Exception e){mapvillage.setLat(0.0);}
				try{	mapvillage.setLongitude(Double.parseDouble(object[20].toString()));}catch(Exception e){mapvillage.setLongitude(0.0);}
				try{	mapvillage.setService(Integer.parseInt(object[21].toString()));}catch(Exception e){mapvillage.setService(0);}
				try{	mapvillage.setVillId(Integer.parseInt(object[22].toString()));}catch(Exception e){mapvillage.setService(0);}
				// mapvillage.setLevel("Village");

			}



		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();
			return null;
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}


		return mapvillage;




	}

}


