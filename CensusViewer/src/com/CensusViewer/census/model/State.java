package com.CensusViewer.census.model;
import java.math.BigDecimal;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "State")
public class State implements java.io.Serializable{
	private Integer	stateid;
	private String  name ;
	private String	statecode;
	private BigDecimal  tot_p;
	private BigDecimal  tot_irr;
	private Integer  power;
	private Integer  education;
	private BigDecimal  hospital;
	private BigDecimal  tot_rec;
	private	Integer	no_of_watersrc	;
	private	Integer	entertain;
	private	BigDecimal	tot_exp	;
	private	BigDecimal	tot_work_p;	
	private	Integer	no_of_transport_mode;	
	private	Integer	no_of_comm_inst	;
	private	BigDecimal	tot_inc	;
	private	Integer	no_of_comm_mode;
	private Integer service;
	private Integer pap_mag_src;
	public BigDecimal getTot_p() {
		return tot_p;
	}
	public void setTot_p(BigDecimal tot_p) {
		this.tot_p = tot_p;
	}
	
	public BigDecimal getTot_irr() {
		return tot_irr;
	}
	public void setTot_irr(BigDecimal tot_irr) {
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
	public BigDecimal getHospital() {
		return hospital;
	}
	public void setHospital(BigDecimal hospital) {
		this.hospital = hospital;
	}
	public BigDecimal getTot_rec() {
		return tot_rec;
	}
	public void setTot_rec(BigDecimal tot_rec) {
		this.tot_rec = tot_rec;
	}
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	public Integer getStateid() {
		return stateid;
	}
	public void setStateid(Integer stateid) {
		this.stateid = stateid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private Collection<State> states;
	public Collection<State> getStates() {
		return states;
	}
	public void setStates(Collection<State> states) {
		this.states = states;
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
	public BigDecimal getTot_exp() {
		return tot_exp;
	}
	public void setTot_exp(BigDecimal tot_exp) {
		this.tot_exp = tot_exp;
	}
	public BigDecimal getTot_work_p() {
		return tot_work_p;
	}
	public void setTot_work_p(BigDecimal tot_work_p) {
		this.tot_work_p = tot_work_p;
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
	public BigDecimal getTot_inc() {
		return tot_inc;
	}
	public void setTot_inc(BigDecimal tot_inc) {
		this.tot_inc = tot_inc;
	}
	public Integer getNo_of_comm_mode() {
		return no_of_comm_mode;
	}
	public void setNo_of_comm_mode(Integer no_of_comm_mode) {
		this.no_of_comm_mode = no_of_comm_mode;
	}
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

}
