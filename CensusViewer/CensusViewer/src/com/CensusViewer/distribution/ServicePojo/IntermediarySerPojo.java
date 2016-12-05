package com.mars.distribution.ServicePojo;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="IntmNameList")
public class IntermediarySerPojo {
private String intmName;
private String IntmId;
private String intermType;

private Collection<IntermediarySerPojo> intmList;
public String getIntmName() {
	return intmName;
}
public void setIntmName(String intmName) {
	this.intmName = intmName;
}
public String getIntmId() {
	return IntmId;
}
public void setIntmId(String intmId) {
	IntmId = intmId;
}
public Collection<IntermediarySerPojo> getIntmList() {
	return intmList;
}
public void setIntmList(Collection<IntermediarySerPojo> intmList) {
	this.intmList = intmList;
}
public String getIntermType() {
	return intermType;
}
public void setIntermType(String intermType) {
	this.intermType = intermType;
}

}
