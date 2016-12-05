package com.CensusViewer.junit;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.CensusViewer.census.impl.StateSubcategoryResult;
import com.CensusViewer.census.model.Column;
import com.CensusViewer.census.model.Result;
import com.CensusViewer.census.model.Rows;

public class StateSubcategoryResultTest {
	boolean flag=false;
	@Test
	public void stateCriteriaDetailsTest(){
		String CriteriaByState="{'jacksondetails':[{'stcode':'27','columns':'a_c_soc','criteria':'>','value':'1'}]}";
		StateSubcategoryResult stateres=new StateSubcategoryResult();
		//Result res=new Result();
		Result state_details=stateres.stateCriteriaDetails(CriteriaByState);
		Collection<Rows> rows=state_details.getResult();
		for(Rows rw:rows){
			Collection<Column> cols=rw.getRow();
			for(Column col:cols){
				String name=col.getName();
				if(name.equals("Gid") )
				{	int IntValue =col.getIntValue();
				if(IntValue==17)
				{
					flag =true;
				}
				}
			}
		}
		Assert.assertTrue(flag);
	}
	}


