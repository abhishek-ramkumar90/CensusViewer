package com.CensusViewer.census.test;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;
@Entity(name="maharashtra_district")
@Table(name = "maharashtra_district")


public class maharashtraDistrict1 {
	@Id
		@Column(name = "gid")
	private BigDecimal gid ;
	@Column(name = "id")
	private BigDecimal id ;
	@Column(name = "statecode")
	private String  statecode ;
	@Column(name = "dist_code")
	private String dist_code ;



	@Column(name = "level")
	private String level ;

	@Column(name = "name")
	private String name;

	@Column(name = "no_hh")
	private BigDecimal no_hh ;

	@Column(name = "tot_p")
	private BigDecimal tot_p;

	@Column(name = "tot_m")
	private BigDecimal tot_m ;

	@Column(name = "tot_f")
	private BigDecimal tot_f ; 

	@Column(name = "p_06") 	 
	private BigDecimal p_06 ;

	@Column(name = "m_06")
	private BigDecimal m_06 ;

	@Column(name = "f_06")
	private BigDecimal f_06;

	@Column(name = "p_sc")
	private BigDecimal p_sc ;

	@Column(name = "m_sc")
	private BigDecimal m_sc ;

	@Column(name = "f_sc")
	private BigDecimal f_sc ;

	@Column(name = "p_st")
	private BigDecimal p_st ;

	@Column(name = "m_st")
	private BigDecimal m_st ;

	@Column(name = "f_st")
	private BigDecimal f_st ;

	@Column(name = "p_lit")
	private BigDecimal p_lit ;

	@Column(name = "m_lit")
	private BigDecimal m_lit ;

	@Column(name = "f_lit")
	private BigDecimal f_lit ;

	@Column(name = "p_ill")
	private BigDecimal p_ill ;

	@Column(name = " m_ill")
	private BigDecimal m_ill ;

	@Column(name = "f_ill")
	private BigDecimal f_ill ;

	@Column(name = "tot_work_p")
	private BigDecimal tot_work_p;

	@Column(name = "tot_work_m")
	private BigDecimal  tot_work_m ;

	@Column(name = "tot_work_f")
	private BigDecimal tot_work_f ;

	@Column(name = "mainwork_p")
	private BigDecimal mainwork_p ;

	@Column(name = "mainwork_m")
	private BigDecimal mainwork_m ;

	@Column(name = "mainwork_f")
	private BigDecimal mainwork_f ;

	@Column(name = "main_cl_p")
	private BigDecimal main_cl_p ;

	@Column(name = "main_cl_m")
	private BigDecimal main_cl_m ;

	@Column(name = "main_cl_f")
	private BigDecimal main_cl_f ;

	@Column(name = "main_al_p")
	private BigDecimal main_al_p ;

	@Column(name = "main_al_m")
	private BigDecimal main_al_m ;

	@Column(name = "main_al_f")
	private BigDecimal main_al_f ;

	@Column(name = "main_hh_p")
	private BigDecimal main_hh_p ;

	@Column(name = "main_hh_m")
	private BigDecimal  main_hh_m ;

	@Column(name = "main_hh_f")
	private BigDecimal main_hh_f ;

	@Column(name = "main_ot_p")
	private BigDecimal main_ot_p ;

	@Column(name = "main_ot_m")
	private BigDecimal  main_ot_m ;

	@Column(name = "main_ot_f")
	private BigDecimal  main_ot_f ;

	@Column(name = "margwork_p")
	private BigDecimal margwork_p ;

	@Column(name = "margwork_m")
	private BigDecimal margwork_m ;

	@Column(name = "margwork_f")
	private BigDecimal margwork_f ;

	@Column(name = "marg_cl_p")
	private BigDecimal marg_cl_p ;

	@Column(name = "marg_cl_m")
	private BigDecimal marg_cl_m ;

	@Column(name = "marg_cl_f")
	private BigDecimal marg_cl_f ;

	@Column(name = "marg_al_p")
	private BigDecimal marg_al_p ;

	@Column(name = "marg_al_m")
	private BigDecimal marg_al_m ;

	@Column(name = "marg_al_f")
	private BigDecimal marg_al_f ;

	@Column(name = "marg_hh_p")
	private BigDecimal marg_hh_p ;

	@Column(name = "marg_hh_m")
	private BigDecimal marg_hh_m ;

	@Column(name = "marg_hh_f")
	private BigDecimal  marg_hh_f ;

	@Column(name = "marg_ot_p")
	private BigDecimal marg_ot_p ;

	@Column(name = "marg_ot_m")
	private BigDecimal marg_ot_m ;

	@Column(name = "marg_ot_f")
	private BigDecimal marg_ot_f ;

	@Column(name = "non_work_p")
	private BigDecimal non_work_p ;

	@Column(name = "non_work_m")
	private BigDecimal non_work_m ;

	@Column(name = "non_work_f")
	private BigDecimal non_work_f ;

	@Column(name = "sexratio")
   private Integer sexratio ;

	@Column(name = "sex_rt_sc")
	 private Integer sex_rt_sc ;

	@Column(name = "sex_rt_st")
	private Integer sex_rt_st ;

	@Column(name = "hh_size")
	private BigDecimal hh_size ;

	@Column(name = "sc_per")
	private BigDecimal sc_per ;

	@Column(name = "st_per")
	private BigDecimal st_per ;

	@Column(name = "m_lit_rate")
	private BigDecimal m_lit_rate ;

	@Column(name = "f_lit_rate")
	private BigDecimal  f_lit_rate ;

	@Column(name = "gender_gap")
	private BigDecimal gender_gap ;

	@Column(name = "p_lit_per")
	private BigDecimal p_lit_per ;

	@Column(name = "m_lit_per")
	private BigDecimal m_lit_per ;

	@Column(name = "f_lit_per")
	private BigDecimal f_lit_per ;

	@Column(name = "tot_p_per")
	private BigDecimal tot_p_per ;

	@Column(name = "t_wor_pr_p")
	private BigDecimal t_wor_pr_p ;

	@Column(name = "t_wor_pr_m")
	private BigDecimal t_wor_pr_m ;

	@Column(name = "t_wor_pr_f")
	private BigDecimal t_wor_pr_f ;

	@Column(name = "t_mn_pr_p")
	private BigDecimal t_mn_pr_p ;

	@Column(name = "t_mn_pr_m")
	private BigDecimal t_mn_pr_m ;

	@Column(name = "t_mn_pr_f")
	private BigDecimal t_mn_pr_f ;

	@Column(name = "t_mrg_pr_p")
	private BigDecimal t_mrg_pr_p ;

	@Column(name = "t_mrg_pr_m")
	private BigDecimal t_mrg_pr_m ;

	@Column(name = "t_mrg_pr_f")
	private BigDecimal t_mrg_pr_f ;

	@Column(name = "n_wor_pr_p")
	private BigDecimal n_wor_pr_p ;

	@Column(name = "n_wor_pr_m")
	private BigDecimal n_wor_pr_m ;

	@Column(name = "n_wor_pr_f")
	private BigDecimal n_wor_pr_f ;

	@Column(name = "cl_per_p")
	private BigDecimal cl_per_p ;

	@Column(name = "cl_per_m")
	private BigDecimal cl_per_m ;

	@Column(name = "cl_per_f")
	private BigDecimal cl_per_f ;

	@Column(name = "al_per_p")
	private BigDecimal al_per_p ;

	@Column(name = "al_per_m")
	private BigDecimal al_per_m ;

	@Column(name = "al_per_f")
	private BigDecimal al_per_f ;

	@Column(name = "hh_per_p")
	private BigDecimal hh_per_p ;

	@Column(name = "hh_per_m")
	private BigDecimal hh_per_m ;

	@Column(name = "hh_per_f")
	private BigDecimal hh_per_f ;

	@Column(name = "ow_per_p")
	private BigDecimal ow_per_p ;

	@Column(name = "ow_per_m")
	private BigDecimal ow_per_m ;

	@Column(name = "ow_per_f")
	private BigDecimal ow_per_f ;

	@Column(name = "cl_mm_p")
	private BigDecimal cl_mm_p ;

	@Column(name = "cl_mm_m")
	private BigDecimal cl_mm_m ;

	@Column(name = "cl_mm_f")
	private BigDecimal cl_mm_f ;

	@Column(name = "al_mm_p")
	private BigDecimal al_mm_p ;

	@Column(name = "al_mm_m")
	private BigDecimal al_mm_m ;

	@Column(name = "al_mm_f")
	private BigDecimal al_mm_f ;

	@Column(name = "hh_mm_p")
	private BigDecimal  hh_mm_p ;

	@Column(name = "hh_mm_m")
	private BigDecimal hh_mm_m ;

	@Column(name = "hh_mm_f")
	private BigDecimal hh_mm_f ;

	@Column(name = "ow_mm_p")
	private BigDecimal  ow_mm_p ;

	@Column(name = "ow_mm_m")
	private BigDecimal ow_mm_m ;

	@Column(name = "ow_mm_f")
	private BigDecimal ow_mm_f ;

	@Column(name = "wpr")
	private BigDecimal wpr ;


 
	
	@Column(name="stateid")
	private String stateid;
	
	
	@Column(name="distid")
	private String distid;
	
	@Column(name = "key")
	private String key ;

	public BigDecimal getGid() {
		return gid;
	}

	public void setGid(BigDecimal gid) {
		this.gid = gid;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getDist_code() {
		return dist_code;
	}

	public void setDist_code(String dist_code) {
		this.dist_code = dist_code;
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

	public BigDecimal getNo_hh() {
		return no_hh;
	}

	public void setNo_hh(BigDecimal no_hh) {
		this.no_hh = no_hh;
	}

	public BigDecimal getTot_p() {
		return tot_p;
	}

	public void setTot_p(BigDecimal tot_p) {
		this.tot_p = tot_p;
	}

	public BigDecimal getTot_m() {
		return tot_m;
	}

	public void setTot_m(BigDecimal tot_m) {
		this.tot_m = tot_m;
	}

	public BigDecimal getTot_f() {
		return tot_f;
	}

	public void setTot_f(BigDecimal tot_f) {
		this.tot_f = tot_f;
	}

	public BigDecimal getP_06() {
		return p_06;
	}

	public void setP_06(BigDecimal p_06) {
		this.p_06 = p_06;
	}

	public BigDecimal getM_06() {
		return m_06;
	}

	public void setM_06(BigDecimal m_06) {
		this.m_06 = m_06;
	}

	public BigDecimal getF_06() {
		return f_06;
	}

	public void setF_06(BigDecimal f_06) {
		this.f_06 = f_06;
	}

	public BigDecimal getP_sc() {
		return p_sc;
	}

	public void setP_sc(BigDecimal p_sc) {
		this.p_sc = p_sc;
	}

	public BigDecimal getM_sc() {
		return m_sc;
	}

	public void setM_sc(BigDecimal m_sc) {
		this.m_sc = m_sc;
	}

	public BigDecimal getF_sc() {
		return f_sc;
	}

	public void setF_sc(BigDecimal f_sc) {
		this.f_sc = f_sc;
	}

	public BigDecimal getP_st() {
		return p_st;
	}

	public void setP_st(BigDecimal p_st) {
		this.p_st = p_st;
	}

	public BigDecimal getM_st() {
		return m_st;
	}

	public void setM_st(BigDecimal m_st) {
		this.m_st = m_st;
	}

	public BigDecimal getF_st() {
		return f_st;
	}

	public void setF_st(BigDecimal f_st) {
		this.f_st = f_st;
	}

	public BigDecimal getP_lit() {
		return p_lit;
	}

	public void setP_lit(BigDecimal p_lit) {
		this.p_lit = p_lit;
	}

	public BigDecimal getM_lit() {
		return m_lit;
	}

	public void setM_lit(BigDecimal m_lit) {
		this.m_lit = m_lit;
	}

	public BigDecimal getF_lit() {
		return f_lit;
	}

	public void setF_lit(BigDecimal f_lit) {
		this.f_lit = f_lit;
	}

	public BigDecimal getP_ill() {
		return p_ill;
	}

	public void setP_ill(BigDecimal p_ill) {
		this.p_ill = p_ill;
	}

	public BigDecimal getM_ill() {
		return m_ill;
	}

	public void setM_ill(BigDecimal m_ill) {
		this.m_ill = m_ill;
	}

	public BigDecimal getF_ill() {
		return f_ill;
	}

	public void setF_ill(BigDecimal f_ill) {
		this.f_ill = f_ill;
	}

	public BigDecimal getTot_work_p() {
		return tot_work_p;
	}

	public void setTot_work_p(BigDecimal tot_work_p) {
		this.tot_work_p = tot_work_p;
	}

	public BigDecimal getTot_work_m() {
		return tot_work_m;
	}

	public void setTot_work_m(BigDecimal tot_work_m) {
		this.tot_work_m = tot_work_m;
	}

	public BigDecimal getTot_work_f() {
		return tot_work_f;
	}

	public void setTot_work_f(BigDecimal tot_work_f) {
		this.tot_work_f = tot_work_f;
	}

	public BigDecimal getMainwork_p() {
		return mainwork_p;
	}

	public void setMainwork_p(BigDecimal mainwork_p) {
		this.mainwork_p = mainwork_p;
	}

	public BigDecimal getMainwork_m() {
		return mainwork_m;
	}

	public void setMainwork_m(BigDecimal mainwork_m) {
		this.mainwork_m = mainwork_m;
	}

	public BigDecimal getMainwork_f() {
		return mainwork_f;
	}

	public void setMainwork_f(BigDecimal mainwork_f) {
		this.mainwork_f = mainwork_f;
	}

	public BigDecimal getMain_cl_p() {
		return main_cl_p;
	}

	public void setMain_cl_p(BigDecimal main_cl_p) {
		this.main_cl_p = main_cl_p;
	}

	public BigDecimal getMain_cl_m() {
		return main_cl_m;
	}

	public void setMain_cl_m(BigDecimal main_cl_m) {
		this.main_cl_m = main_cl_m;
	}

	public BigDecimal getMain_cl_f() {
		return main_cl_f;
	}

	public void setMain_cl_f(BigDecimal main_cl_f) {
		this.main_cl_f = main_cl_f;
	}

	public BigDecimal getMain_al_p() {
		return main_al_p;
	}

	public void setMain_al_p(BigDecimal main_al_p) {
		this.main_al_p = main_al_p;
	}

	public BigDecimal getMain_al_m() {
		return main_al_m;
	}

	public void setMain_al_m(BigDecimal main_al_m) {
		this.main_al_m = main_al_m;
	}

	public BigDecimal getMain_al_f() {
		return main_al_f;
	}

	public void setMain_al_f(BigDecimal main_al_f) {
		this.main_al_f = main_al_f;
	}

	public BigDecimal getMain_hh_p() {
		return main_hh_p;
	}

	public void setMain_hh_p(BigDecimal main_hh_p) {
		this.main_hh_p = main_hh_p;
	}

	public BigDecimal getMain_hh_m() {
		return main_hh_m;
	}

	public void setMain_hh_m(BigDecimal main_hh_m) {
		this.main_hh_m = main_hh_m;
	}

	public BigDecimal getMain_hh_f() {
		return main_hh_f;
	}

	public void setMain_hh_f(BigDecimal main_hh_f) {
		this.main_hh_f = main_hh_f;
	}

	public BigDecimal getMain_ot_p() {
		return main_ot_p;
	}

	public void setMain_ot_p(BigDecimal main_ot_p) {
		this.main_ot_p = main_ot_p;
	}

	public BigDecimal getMain_ot_m() {
		return main_ot_m;
	}

	public void setMain_ot_m(BigDecimal main_ot_m) {
		this.main_ot_m = main_ot_m;
	}

	public BigDecimal getMain_ot_f() {
		return main_ot_f;
	}

	public void setMain_ot_f(BigDecimal main_ot_f) {
		this.main_ot_f = main_ot_f;
	}

	public BigDecimal getMargwork_p() {
		return margwork_p;
	}

	public void setMargwork_p(BigDecimal margwork_p) {
		this.margwork_p = margwork_p;
	}

	public BigDecimal getMargwork_m() {
		return margwork_m;
	}

	public void setMargwork_m(BigDecimal margwork_m) {
		this.margwork_m = margwork_m;
	}

	public BigDecimal getMargwork_f() {
		return margwork_f;
	}

	public void setMargwork_f(BigDecimal margwork_f) {
		this.margwork_f = margwork_f;
	}

	public BigDecimal getMarg_cl_p() {
		return marg_cl_p;
	}

	public void setMarg_cl_p(BigDecimal marg_cl_p) {
		this.marg_cl_p = marg_cl_p;
	}

	public BigDecimal getMarg_cl_m() {
		return marg_cl_m;
	}

	public void setMarg_cl_m(BigDecimal marg_cl_m) {
		this.marg_cl_m = marg_cl_m;
	}

	public BigDecimal getMarg_cl_f() {
		return marg_cl_f;
	}

	public void setMarg_cl_f(BigDecimal marg_cl_f) {
		this.marg_cl_f = marg_cl_f;
	}

	public BigDecimal getMarg_al_p() {
		return marg_al_p;
	}

	public void setMarg_al_p(BigDecimal marg_al_p) {
		this.marg_al_p = marg_al_p;
	}

	public BigDecimal getMarg_al_m() {
		return marg_al_m;
	}

	public void setMarg_al_m(BigDecimal marg_al_m) {
		this.marg_al_m = marg_al_m;
	}

	public BigDecimal getMarg_al_f() {
		return marg_al_f;
	}

	public void setMarg_al_f(BigDecimal marg_al_f) {
		this.marg_al_f = marg_al_f;
	}

	public BigDecimal getMarg_hh_p() {
		return marg_hh_p;
	}

	public void setMarg_hh_p(BigDecimal marg_hh_p) {
		this.marg_hh_p = marg_hh_p;
	}

	public BigDecimal getMarg_hh_m() {
		return marg_hh_m;
	}

	public void setMarg_hh_m(BigDecimal marg_hh_m) {
		this.marg_hh_m = marg_hh_m;
	}

	public BigDecimal getMarg_hh_f() {
		return marg_hh_f;
	}

	public void setMarg_hh_f(BigDecimal marg_hh_f) {
		this.marg_hh_f = marg_hh_f;
	}

	public BigDecimal getMarg_ot_p() {
		return marg_ot_p;
	}

	public void setMarg_ot_p(BigDecimal marg_ot_p) {
		this.marg_ot_p = marg_ot_p;
	}

	public BigDecimal getMarg_ot_m() {
		return marg_ot_m;
	}

	public void setMarg_ot_m(BigDecimal marg_ot_m) {
		this.marg_ot_m = marg_ot_m;
	}

	public BigDecimal getMarg_ot_f() {
		return marg_ot_f;
	}

	public void setMarg_ot_f(BigDecimal marg_ot_f) {
		this.marg_ot_f = marg_ot_f;
	}

	public BigDecimal getNon_work_p() {
		return non_work_p;
	}

	public void setNon_work_p(BigDecimal non_work_p) {
		this.non_work_p = non_work_p;
	}

	public BigDecimal getNon_work_m() {
		return non_work_m;
	}

	public void setNon_work_m(BigDecimal non_work_m) {
		this.non_work_m = non_work_m;
	}

	public BigDecimal getNon_work_f() {
		return non_work_f;
	}

	public void setNon_work_f(BigDecimal non_work_f) {
		this.non_work_f = non_work_f;
	}

	public Integer getSexratio() {
		return sexratio;
	}

	public void setSexratio(Integer sexratio) {
		this.sexratio = sexratio;
	}

	public Integer getSex_rt_sc() {
		return sex_rt_sc;
	}

	public void setSex_rt_sc(Integer sex_rt_sc) {
		this.sex_rt_sc = sex_rt_sc;
	}

	public Integer getSex_rt_st() {
		return sex_rt_st;
	}

	public void setSex_rt_st(Integer sex_rt_st) {
		this.sex_rt_st = sex_rt_st;
	}

	public BigDecimal getHh_size() {
		return hh_size;
	}

	public void setHh_size(BigDecimal hh_size) {
		this.hh_size = hh_size;
	}

	public BigDecimal getSc_per() {
		return sc_per;
	}

	public void setSc_per(BigDecimal sc_per) {
		this.sc_per = sc_per;
	}

	public BigDecimal getSt_per() {
		return st_per;
	}

	public void setSt_per(BigDecimal st_per) {
		this.st_per = st_per;
	}

	public BigDecimal getM_lit_rate() {
		return m_lit_rate;
	}

	public void setM_lit_rate(BigDecimal m_lit_rate) {
		this.m_lit_rate = m_lit_rate;
	}

	public BigDecimal getF_lit_rate() {
		return f_lit_rate;
	}

	public void setF_lit_rate(BigDecimal f_lit_rate) {
		this.f_lit_rate = f_lit_rate;
	}

	public BigDecimal getGender_gap() {
		return gender_gap;
	}

	public void setGender_gap(BigDecimal gender_gap) {
		this.gender_gap = gender_gap;
	}

	public BigDecimal getP_lit_per() {
		return p_lit_per;
	}

	public void setP_lit_per(BigDecimal p_lit_per) {
		this.p_lit_per = p_lit_per;
	}

	public BigDecimal getM_lit_per() {
		return m_lit_per;
	}

	public void setM_lit_per(BigDecimal m_lit_per) {
		this.m_lit_per = m_lit_per;
	}

	public BigDecimal getF_lit_per() {
		return f_lit_per;
	}

	public void setF_lit_per(BigDecimal f_lit_per) {
		this.f_lit_per = f_lit_per;
	}

	public BigDecimal getTot_p_per() {
		return tot_p_per;
	}

	public void setTot_p_per(BigDecimal tot_p_per) {
		this.tot_p_per = tot_p_per;
	}

	public BigDecimal getT_wor_pr_p() {
		return t_wor_pr_p;
	}

	public void setT_wor_pr_p(BigDecimal t_wor_pr_p) {
		this.t_wor_pr_p = t_wor_pr_p;
	}

	public BigDecimal getT_wor_pr_m() {
		return t_wor_pr_m;
	}

	public void setT_wor_pr_m(BigDecimal t_wor_pr_m) {
		this.t_wor_pr_m = t_wor_pr_m;
	}

	public BigDecimal getT_wor_pr_f() {
		return t_wor_pr_f;
	}

	public void setT_wor_pr_f(BigDecimal t_wor_pr_f) {
		this.t_wor_pr_f = t_wor_pr_f;
	}

	public BigDecimal getT_mn_pr_p() {
		return t_mn_pr_p;
	}

	public void setT_mn_pr_p(BigDecimal t_mn_pr_p) {
		this.t_mn_pr_p = t_mn_pr_p;
	}

	public BigDecimal getT_mn_pr_m() {
		return t_mn_pr_m;
	}

	public void setT_mn_pr_m(BigDecimal t_mn_pr_m) {
		this.t_mn_pr_m = t_mn_pr_m;
	}

	public BigDecimal getT_mn_pr_f() {
		return t_mn_pr_f;
	}

	public void setT_mn_pr_f(BigDecimal t_mn_pr_f) {
		this.t_mn_pr_f = t_mn_pr_f;
	}

	public BigDecimal getT_mrg_pr_p() {
		return t_mrg_pr_p;
	}

	public void setT_mrg_pr_p(BigDecimal t_mrg_pr_p) {
		this.t_mrg_pr_p = t_mrg_pr_p;
	}

	public BigDecimal getT_mrg_pr_m() {
		return t_mrg_pr_m;
	}

	public void setT_mrg_pr_m(BigDecimal t_mrg_pr_m) {
		this.t_mrg_pr_m = t_mrg_pr_m;
	}

	public BigDecimal getT_mrg_pr_f() {
		return t_mrg_pr_f;
	}

	public void setT_mrg_pr_f(BigDecimal t_mrg_pr_f) {
		this.t_mrg_pr_f = t_mrg_pr_f;
	}

	public BigDecimal getN_wor_pr_p() {
		return n_wor_pr_p;
	}

	public void setN_wor_pr_p(BigDecimal n_wor_pr_p) {
		this.n_wor_pr_p = n_wor_pr_p;
	}

	public BigDecimal getN_wor_pr_m() {
		return n_wor_pr_m;
	}

	public void setN_wor_pr_m(BigDecimal n_wor_pr_m) {
		this.n_wor_pr_m = n_wor_pr_m;
	}

	public BigDecimal getN_wor_pr_f() {
		return n_wor_pr_f;
	}

	public void setN_wor_pr_f(BigDecimal n_wor_pr_f) {
		this.n_wor_pr_f = n_wor_pr_f;
	}

	public BigDecimal getCl_per_p() {
		return cl_per_p;
	}

	public void setCl_per_p(BigDecimal cl_per_p) {
		this.cl_per_p = cl_per_p;
	}

	public BigDecimal getCl_per_m() {
		return cl_per_m;
	}

	public void setCl_per_m(BigDecimal cl_per_m) {
		this.cl_per_m = cl_per_m;
	}

	public BigDecimal getCl_per_f() {
		return cl_per_f;
	}

	public void setCl_per_f(BigDecimal cl_per_f) {
		this.cl_per_f = cl_per_f;
	}

	public BigDecimal getAl_per_p() {
		return al_per_p;
	}

	public void setAl_per_p(BigDecimal al_per_p) {
		this.al_per_p = al_per_p;
	}

	public BigDecimal getAl_per_m() {
		return al_per_m;
	}

	public void setAl_per_m(BigDecimal al_per_m) {
		this.al_per_m = al_per_m;
	}

	public BigDecimal getAl_per_f() {
		return al_per_f;
	}

	public void setAl_per_f(BigDecimal al_per_f) {
		this.al_per_f = al_per_f;
	}

	public BigDecimal getHh_per_p() {
		return hh_per_p;
	}

	public void setHh_per_p(BigDecimal hh_per_p) {
		this.hh_per_p = hh_per_p;
	}

	public BigDecimal getHh_per_m() {
		return hh_per_m;
	}

	public void setHh_per_m(BigDecimal hh_per_m) {
		this.hh_per_m = hh_per_m;
	}

	public BigDecimal getHh_per_f() {
		return hh_per_f;
	}

	public void setHh_per_f(BigDecimal hh_per_f) {
		this.hh_per_f = hh_per_f;
	}

	public BigDecimal getOw_per_p() {
		return ow_per_p;
	}

	public void setOw_per_p(BigDecimal ow_per_p) {
		this.ow_per_p = ow_per_p;
	}

	public BigDecimal getOw_per_m() {
		return ow_per_m;
	}

	public void setOw_per_m(BigDecimal ow_per_m) {
		this.ow_per_m = ow_per_m;
	}

	public BigDecimal getOw_per_f() {
		return ow_per_f;
	}

	public void setOw_per_f(BigDecimal ow_per_f) {
		this.ow_per_f = ow_per_f;
	}

	public BigDecimal getCl_mm_p() {
		return cl_mm_p;
	}

	public void setCl_mm_p(BigDecimal cl_mm_p) {
		this.cl_mm_p = cl_mm_p;
	}

	public BigDecimal getCl_mm_m() {
		return cl_mm_m;
	}

	public void setCl_mm_m(BigDecimal cl_mm_m) {
		this.cl_mm_m = cl_mm_m;
	}

	public BigDecimal getCl_mm_f() {
		return cl_mm_f;
	}

	public void setCl_mm_f(BigDecimal cl_mm_f) {
		this.cl_mm_f = cl_mm_f;
	}

	public BigDecimal getAl_mm_p() {
		return al_mm_p;
	}

	public void setAl_mm_p(BigDecimal al_mm_p) {
		this.al_mm_p = al_mm_p;
	}

	public BigDecimal getAl_mm_m() {
		return al_mm_m;
	}

	public void setAl_mm_m(BigDecimal al_mm_m) {
		this.al_mm_m = al_mm_m;
	}

	public BigDecimal getAl_mm_f() {
		return al_mm_f;
	}

	public void setAl_mm_f(BigDecimal al_mm_f) {
		this.al_mm_f = al_mm_f;
	}

	public BigDecimal getHh_mm_p() {
		return hh_mm_p;
	}

	public void setHh_mm_p(BigDecimal hh_mm_p) {
		this.hh_mm_p = hh_mm_p;
	}

	public BigDecimal getHh_mm_m() {
		return hh_mm_m;
	}

	public void setHh_mm_m(BigDecimal hh_mm_m) {
		this.hh_mm_m = hh_mm_m;
	}

	public BigDecimal getHh_mm_f() {
		return hh_mm_f;
	}

	public void setHh_mm_f(BigDecimal hh_mm_f) {
		this.hh_mm_f = hh_mm_f;
	}

	public BigDecimal getOw_mm_p() {
		return ow_mm_p;
	}

	public void setOw_mm_p(BigDecimal ow_mm_p) {
		this.ow_mm_p = ow_mm_p;
	}

	public BigDecimal getOw_mm_m() {
		return ow_mm_m;
	}

	public void setOw_mm_m(BigDecimal ow_mm_m) {
		this.ow_mm_m = ow_mm_m;
	}

	public BigDecimal getOw_mm_f() {
		return ow_mm_f;
	}

	public void setOw_mm_f(BigDecimal ow_mm_f) {
		this.ow_mm_f = ow_mm_f;
	}

	public BigDecimal getWpr() {
		return wpr;
	}

	public void setWpr(BigDecimal wpr) {
		this.wpr = wpr;
	}

	public String getStateid() {
		return stateid;
	}

	public void setStateid(String stateid) {
		this.stateid = stateid;
	}

	public String getDistid() {
		return distid;
	}

	public void setDistid(String distid) {
		this.distid = distid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
}