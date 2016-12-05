package com.mars.junit;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.mars.census.impl.DistrictDetails;
import com.mars.census.model.District;

public class DistrictDetailsTest extends CommonUtil {
	@Test
	public void districtDetailsTest(){
		String name, distcode,pop,irr,pow,edu,hos,rev,inst,wtr,ent,workp,trans,exp,inc,comm;
		String errMessages = new String();
		DistrictDetails districtdet=new DistrictDetails();
		String jacksonStateDetails1="{'jacksonStateDetails':[{'statecode':'27'}]}";
		Collection<District> dists=districtdet.districtDetails(jacksonStateDetails1);
		for(District dist:dists){

			distcode=dist.getDist_code();
			if(distcode.equals("14")){
				try{
					this.checkProperty(distcode,"14");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				name=dist.getName();
				try{
					this.checkProperty(name,"Yavatmal");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				pop=dist.getTot_p().toString();
				try{
					this.checkProperty(pop,"2458271.0000000000000000");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				irr=dist.getTot_irr().toString();
				try{
					this.checkProperty(irr,"36586.898785425098663");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				edu=dist.getEducation().toString();
				try{
					this.checkProperty(edu,"159");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				hos=dist.getHospital().toString();
				try{
					this.checkProperty(hos,"17");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				rev=dist.getTot_rec().toString();
				try{
					this.checkProperty(rev,"11663646.0000000");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				inst=dist.getNo_of_comm_inst().toString();
				try{
					this.checkProperty(inst,"1297");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				wtr=dist.getNo_of_watersrc().toString();
				try{
					this.checkProperty(wtr,"10");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				ent=dist.getEntertain().toString();
				try{
					this.checkProperty(ent,"3825");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				workp=dist.getTot_work_p().toString();
				try{
					this.checkProperty(workp,"1118937");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				trans=dist.getNo_of_transport_mode().toString();
				try{
					this.checkProperty(trans,"5");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				exp=dist.getTot_exp().toString();
				try{
					this.checkProperty(exp,"10494721");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				inc=dist.getTot_inc().toString();
				try{
					this.checkProperty(inc,"575287");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				comm=dist.getNo_of_comm_mode().toString();
				try{
					this.checkProperty(comm,"4");
				} catch (Exception e){
					errMessages += e.getMessage();
				}



			}
			Assert.assertTrue(errMessages, errMessages.isEmpty());

		}


	}
}
