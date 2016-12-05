package com.mars.distribution.ServicePojo;

import java.util.Comparator;

public class ChannelIntermJson  implements Comparator<ChannelIntermJson>   {

	private String type;
	private int level;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public int compare(ChannelIntermJson ch1, ChannelIntermJson ch2) {
		if(new Integer(ch1.getLevel()).toString().equals(new Integer(ch1.getLevel()).toString()))
		{
			return new Integer(ch1.getLevel()).toString().compareTo(new Integer(ch1.getLevel()).toString());
		}
		return new Integer(ch1.getLevel()).toString().compareTo(new Integer(ch1.getLevel()).toString());

	}
	
}
