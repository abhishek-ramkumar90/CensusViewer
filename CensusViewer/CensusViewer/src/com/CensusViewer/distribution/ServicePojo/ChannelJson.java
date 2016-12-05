package com.mars.distribution.ServicePojo;

import java.util.Collection;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Channels")
public class ChannelJson implements Comparator<ChannelJson> {

	private String channelName;
	private String channelId;
	private int levelCount;
	private String orgId;
	private Collection<ChannelJson> channelJsonCollection;
	
	
	public Collection<ChannelJson> getChannelJsonCollection() {
		return channelJsonCollection;
	}
	public void setChannelJsonCollection( Collection<ChannelJson> channelJsonCollection) {
		this.channelJsonCollection = channelJsonCollection;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public int getLevelCount() {
		return levelCount;
	}
	public void setLevelCount(int levelCount) {
		this.levelCount = levelCount;
	}
	
	@Override
	public int compare(ChannelJson ch1, ChannelJson ch2) {
		if(ch1.getChannelName().equals(ch1.getChannelName()))
		{
			return ch1.getChannelName().compareTo(ch1.getChannelName());
		}
		return ch1.getChannelName().compareTo(ch1.getChannelName());

	}
	
}
