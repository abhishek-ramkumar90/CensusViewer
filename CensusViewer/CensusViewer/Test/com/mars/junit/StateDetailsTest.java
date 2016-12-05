package com.mars.junit;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.mars.census.impl.StateDetails;
import com.mars.census.model.State;


public class StateDetailsTest extends CommonUtil {
	@Test
	public void stateDetailsTest(){
		String name, stcode,pop,irr,pow,edu,hos,rev,inst,wtr,ent,workp,trans,exp,inc,comm;
		String errMessages = new String();
		StateDetails st_details=new StateDetails();
		Collection<State> state_det=st_details.stateDetails();
		for(State st:state_det){
			stcode=st.getStatecode();
			if(stcode.equals("27")){
				try{
					this.checkProperty(stcode, "27");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				name=st.getName();
				try{
					this.checkProperty(name,"Maharashtra");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				pop=st.getTot_p().toString();
				try{
					this.checkProperty(pop,"96863809.000000000000000");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				irr=st.getTot_irr().toString();
				try{
					this.checkProperty(irr,"3337694.834352176750344");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				edu=st.getEducation().toString();
				try{
					this.checkProperty(edu,"7030");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				hos=st.getHospital().toString();
				try{
					this.checkProperty(hos,"1523");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				rev=st.getTot_rec().toString();
				try{
					this.checkProperty(rev,"117714991.000000000000000");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				inst=st.getNo_of_comm_inst().toString();
				try{
					this.checkProperty(inst,"48537");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				wtr=st.getNo_of_watersrc().toString();
				try{
					this.checkProperty(wtr,"10");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				ent=st.getEntertain().toString();
				try{
					this.checkProperty(ent,"86845");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				workp=st.getTot_work_p().toString();
				try{
					this.checkProperty(workp,"41169736");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				trans=st.getNo_of_transport_mode().toString();
				try{
					this.checkProperty(trans,"5");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				exp=st.getTot_exp().toString();
				try{
					this.checkProperty(exp,"829545659");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				inc=st.getTot_inc().toString();
				try{
					this.checkProperty(inc,"849706028");
				} catch (Exception e){
					errMessages += e.getMessage();
				}
				comm=st.getNo_of_comm_mode().toString();
				try{
					this.checkProperty(comm,"4");
				} catch (Exception e){
					errMessages += e.getMessage();
				}



			}

			Assert.assertTrue(errMessages,errMessages.isEmpty());
		}

	}

}
