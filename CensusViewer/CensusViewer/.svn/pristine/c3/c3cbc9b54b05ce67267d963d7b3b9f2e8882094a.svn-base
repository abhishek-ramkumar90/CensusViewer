package com.mars.distribution.ServicePojo;

import java.util.Collection;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlRootElement;

import com.mars.distribution.model.Branch;
@XmlRootElement(name="BranchList")
public class BranchListTerrJson implements Comparator<BranchListTerrJson>  {
	private String branchId;
	private String branchName;
	private String RegnId;
	private Collection<BranchListTerrJson> branchlist;


	public Collection<BranchListTerrJson> getBranchlist() {
		return branchlist;
	}
	public void setBranchlist(Collection<BranchListTerrJson> branchlist) {
		this.branchlist = branchlist;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getRegnId() {
		return RegnId;
	}
	public void setRegnId(String regnId) {
		RegnId = regnId;
	}
	@Override
	public int compare(BranchListTerrJson b1, BranchListTerrJson b2) {

		if(b1.getBranchName().equals(b2.getBranchName()))
		{
			return b1.getBranchName().compareTo(b2.getBranchName());
		}
		return b1.getBranchName().compareTo(b2.getBranchName());


	}

}
