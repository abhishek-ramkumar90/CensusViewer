package com.CensusViewer.junit;

public class CommonUtil {
	public boolean checkProperty(String propertyValue, String expectedvalue) throws Exception{
		if(propertyValue.equals(expectedvalue)){
			return true;
		}else {
			throw new Exception("Property has value: " + propertyValue + ", expected: " + expectedvalue);
		}
	}

}
