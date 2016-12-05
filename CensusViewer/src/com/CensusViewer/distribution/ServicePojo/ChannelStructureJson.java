package com.CensusViewer.distribution.ServicePojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ChannelStruct")
public class ChannelStructureJson {

	private String channelName;
	private String channelId;
	private List<ChannelIntermJson> channelIntermjson = new ArrayList<ChannelIntermJson>();
	private List<ChannelStructureJson> channelStructureJsons;
	
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
	public List<ChannelIntermJson> getChannelIntermjson() {
		return channelIntermjson;
	}
	public void setChannelIntermjson(List<ChannelIntermJson> channelIntermjson) {
		this.channelIntermjson = channelIntermjson;
	}
	public List<ChannelStructureJson> getChannelStructureJsons() {
		return channelStructureJsons;
	}
	public void setChannelStructureJsons(
			List<ChannelStructureJson> channelStructureJsons) {
		this.channelStructureJsons = channelStructureJsons;
	}
	
	
	
}
