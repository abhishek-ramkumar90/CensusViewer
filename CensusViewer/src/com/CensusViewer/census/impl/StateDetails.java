package com.CensusViewer.census.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.CensusViewer.census.model.State;
import com.CensusViewer.census.services.customerservice.StateService;



public class StateDetails {

	State state=null;
	List<State> stateCollection = null;
	
	String CLASS_NAME = this.getClass().getName();
    Logger logger = Logger.getLogger(CLASS_NAME);
	@SuppressWarnings("unchecked")
	public Collection<State> stateDetails() {
		logger.info("Entering " + CLASS_NAME);
		int k = 0;
		stateCollection = new ArrayList<State>();
		StateService ss = new StateService();
		List stateList = ss.getStateList();
		for(int i=1; i<stateList.size(); i++) {
			
			Object[] ObjList=(Object[])stateList.get(i);
			state = new State();
			state.setName(ObjList[k++].toString());
			state.setStatecode(ObjList[k++].toString());
			state.setTot_p((BigDecimal)ObjList[k++]);
			state.setTot_irr((BigDecimal)ObjList[k++]);
			state.setPower((Integer)ObjList[k++]);
			state.setEducation((Integer)ObjList[k++]);
			state.setHospital((BigDecimal)ObjList[k++]);
			//state.setTot_rec((BigDecimal)ObjList[k++]);
			state.setNo_of_comm_inst((Integer)ObjList[k++]);
			state.setNo_of_watersrc((Integer)ObjList[k++]);
			state.setEntertain((Integer)ObjList[k++]);
			state.setTot_work_p((BigDecimal)ObjList[k++]);
			state.setNo_of_transport_mode((Integer)ObjList[k++]);
			state.setTot_exp((BigDecimal)ObjList[k++]);
			state.setTot_inc((BigDecimal)ObjList[k++]);
			state.setNo_of_comm_mode((Integer)ObjList[k++]);
			state.setService((Integer)ObjList[k++]);
			state.setPap_mag_src((Integer)ObjList[k++]);
			k=0;
			stateCollection.add(state);
		}
		Collections.sort(stateCollection, new Comparator() {  
            @Override  
            public int compare(Object obj1, Object obj2) {  
            	State dist1 = (State)obj1;  
            	State dist2 = (State)obj2; 
                return dist1.getName().compareToIgnoreCase(dist2.getName());  
            }  
        }); 
	
		return  stateCollection;
	}
}