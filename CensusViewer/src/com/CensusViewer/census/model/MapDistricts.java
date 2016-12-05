/**
 * 
 */
package com.CensusViewer.census.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author bhupendras
 *
 */
@XmlRootElement(name = "AutoComplete")
public class MapDistricts {
	
	private String distcode;
	private int gid ;
	private String  statecode ;
	private String level ;
	private String name;
	private Integer tot_p;
	private Integer tot_irr;
	private Integer power;
	private Integer education;
	private Integer hospital;
	private Integer  tot_rec;
	private	Integer	no_of_watersrc	;
	private	Integer	entertain;
	private	Integer	tot_exp	;
	private	Integer	tot_work_p;	
	private	Integer	no_of_transport_mode;	
	private	Integer	no_of_comm_inst	;
	private	Integer	tot_inc	;
	private	Integer	no_of_comm_mode;
	private Double     lat;
	private Double      longitude;
	private	Integer service;
	private	Integer pap_mag_src;
   
	
	public Integer getService() {
		return service;
	}
	public void setService(Integer service) {
		this.service = service;
	}
	public Integer getPap_mag_src() {
		return pap_mag_src;
	}
	public void setPap_mag_src(Integer pap_mag_src) {
		this.pap_mag_src = pap_mag_src;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getDistcode() {
		return distcode;
	}
	public void setDistcode(String distcode) {
		this.distcode = distcode;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTot_p() {
		return tot_p;
	}
	public void setTot_p(int tot_p) {
		this.tot_p = tot_p;
	}
	public Integer getTot_irr() {
		return tot_irr;
	}
	public void setTot_irr(Integer tot_irr) {
		this.tot_irr = tot_irr;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	public Integer getEducation() {
		return education;
	}
	public void setEducation(Integer education) {
		this.education = education;
	}
	
	public Integer getHospital() {
		return hospital;
	}
	public void setHospital(Integer hospital) {
		this.hospital = hospital;
	}
	public void setTot_p(Integer tot_p) {
		this.tot_p = tot_p;
	}
	
	public Integer getNo_of_watersrc() {
		return no_of_watersrc;
	}
	public void setNo_of_watersrc(Integer no_of_watersrc) {
		this.no_of_watersrc = no_of_watersrc;
	}
	public Integer getEntertain() {
		return entertain;
	}
	public void setEntertain(Integer entertain) {
		this.entertain = entertain;
	}
	
	
	public Integer getNo_of_transport_mode() {
		return no_of_transport_mode;
	}
	public void setNo_of_transport_mode(Integer no_of_transport_mode) {
		this.no_of_transport_mode = no_of_transport_mode;
	}
	public Integer getNo_of_comm_inst() {
		return no_of_comm_inst;
	}
	public void setNo_of_comm_inst(Integer no_of_comm_inst) {
		this.no_of_comm_inst = no_of_comm_inst;
	}
	
	public Integer getNo_of_comm_mode() {
		return no_of_comm_mode;
	}
	public void setNo_of_comm_mode(Integer no_of_comm_mode) {
		this.no_of_comm_mode = no_of_comm_mode;
	}
	public Integer getTot_rec() {
		return tot_rec;
	}
	public void setTot_rec(Integer tot_rec) {
		this.tot_rec = tot_rec;
	}
	public Integer getTot_exp() {
		return tot_exp;
	}
	public void setTot_exp(Integer tot_exp) {
		this.tot_exp = tot_exp;
	}
	public Integer getTot_work_p() {
		return tot_work_p;
	}
	public void setTot_work_p(Integer tot_work_p) {
		this.tot_work_p = tot_work_p;
	}
	public Integer getTot_inc() {
		return tot_inc;
	}
	public void setTot_inc(Integer tot_inc) {
		this.tot_inc = tot_inc;
	}
	
	

}
