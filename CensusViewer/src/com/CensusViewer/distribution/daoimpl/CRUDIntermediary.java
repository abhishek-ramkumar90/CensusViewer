package com.CensusViewer.distribution.daoimpl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.CensusViewer.HibernateUtility.HibernateUtil;
import com.CensusViewer.distribution.ServicePojo.JacksonUpdateChannel;
import com.CensusViewer.distribution.ServicePojo.jacksonIntermediaryUpdate;
import com.CensusViewer.distribution.config.DistributionConstants;
import com.CensusViewer.distribution.dao.CRUDIntermediaryDao;
import com.CensusViewer.distribution.model.Intermediary;
import com.CensusViewer.distribution.model.IntermediaryFreeFlow;
import com.rits.cloning.Cloner;
public class CRUDIntermediary implements CRUDIntermediaryDao {
	String CLASS_NAME = this.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);
	   ByteArrayOutputStream baos;
	    ByteArrayInputStream bins;
	    
	@Override
	public void saveIntermediary(String jsonString) {
		SessionFactory sf = null; 
		sf = HibernateUtil.getSessionFactory();
		Session session = null; 
		session = sf.openSession();
		session.beginTransaction();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try{
			IntermediaryFreeFlow interDetails=mapper.readValue(jsonString,IntermediaryFreeFlow.class );
			session.save(interDetails);
			  session.getTransaction().commit();
			  session.close();
			System.out.println(mapper.writeValueAsString(interDetails));
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.fatal(e.getMessage());	
		}
	}
	@Override
	public void UpdateIntermediary(String updateDetails) throws JsonParseException, JsonMappingException, IOException
	{
		SessionFactory sf = null; 
		sf = HibernateUtil.getSessionFactory();
		Session session = null; 
		session = sf.openSession();
		session.beginTransaction();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jacksonIntermediaryUpdate interDetails=mapper.readValue(updateDetails,jacksonIntermediaryUpdate.class );
		System.out.println(mapper.writeValueAsString(interDetails));
		

		if( !interDetails.getChildId().isEmpty() && interDetails.getParentId()!=null)
		{
			IntermediaryFreeFlow toChange=null;
			Collection<IntermediaryFreeFlow>collec=interDetails.getIntermediaryDetails();
			for(IntermediaryFreeFlow inter:collec)
			{
				toChange=inter;
			}
			System.out.println("child....."+interDetails.getChildId()+"....parent...."+interDetails.getParentId());
			String childIds=StringUtils.join(interDetails.getChildId(),',').toString();
			System.out.println("commma saperated String........"+childIds);
			IntermediaryFreeFlow parent = (IntermediaryFreeFlow ) session.get(IntermediaryFreeFlow .class, Integer.parseInt(interDetails.getParentId()));
		Collection<IntermediaryFreeFlow> childCollection=filterCollection(parent.getIntermediaryCollection(),session,childIds);
	int 	i=5;
for(IntermediaryFreeFlow inter:childCollection)
{
try {
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(bos);
	oos.writeObject(inter);
	oos.flush();
	oos.close();
	byte[] byteData = bos.toByteArray();
	inter.getIntermediaryCollection().clear();
	inter.setName(toChange.getName());
	inter.setType(toChange.getType());
	ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
	IntermediaryFreeFlow inter2 = (IntermediaryFreeFlow) new ObjectInputStream(bais).readObject();
	inter2.setIntermId(inter.getIntermId()+112);
changeId(inter2.getIntermediaryCollection());
	inter.getIntermediaryCollection().add(inter2);

} catch (Exception e) {
	e.printStackTrace();
}
i++;
}
System.out.println("update Completed");
session.saveOrUpdate(parent);
session.getTransaction().commit();
session.close();
		return ;
		}
		if( interDetails.getParentId()!=null)
		{
			 IntermediaryFreeFlow newChild=new IntermediaryFreeFlow();
			System.out.println("Parent...."+interDetails.getParentId());
			IntermediaryFreeFlow parent = (IntermediaryFreeFlow ) session.get(IntermediaryFreeFlow .class, Integer.parseInt(interDetails.getParentId()));
			for(IntermediaryFreeFlow intr:interDetails.getIntermediaryDetails())
			{
				parent.getIntermediaryCollection().add(intr);
			}			
	
			session.update(parent);
			  session.getTransaction().commit();
			  session.close();
			
			return;
		}
		if( interDetails.getRoot()!=null)
		{
			System.out.println("root...."+interDetails.getRoot());
			return;
		}

	}
	private void changeId(List<IntermediaryFreeFlow> intermediaryCollection) {
		// TODO Auto-generated method stub
		
		if(intermediaryCollection.isEmpty())
		{
			
			return;
		}
		else
		{
			for(IntermediaryFreeFlow inter:intermediaryCollection)
			{
				inter.setIntermId(inter.getIntermId()+10);
				changeId(inter.getIntermediaryCollection());
				
			}
		}
		
	}
	private Collection<IntermediaryFreeFlow> filterCollection(Collection<IntermediaryFreeFlow> collection, Session s,String childIds) {
		Query filterQuery = s.createFilter(collection, "where intermId IN("+childIds+")" );
		return filterQuery.list();
	}
	@Override
	public void DeleteIntermediary(String DeleteDetails) throws JSONException {
		// TODO Auto-generated method stub
		SessionFactory sf = null; 
		sf = HibernateUtil.getSessionFactory();
		Session session = null; 
		session = sf.openSession();
		session.beginTransaction();
		try{
		System.out.println(DeleteDetails);
		JSONObject jObj=new JSONObject(DeleteDetails);
IntermediaryFreeFlow deleteNode = (IntermediaryFreeFlow ) session.get(IntermediaryFreeFlow .class, Integer.parseInt(jObj.getString("IntermId")));
if(deleteNode.getIntermediaryCollection().isEmpty())
{
deleteNode.setStatus(DistributionConstants.ZERO);	
session.update(deleteNode);
session.getTransaction().commit();
}
else{
	System.out.println("is not a leaf node Cant delete elements");
}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(session.isOpen())
				session.close();
		}
	}
	
	@Override
	public IntermediaryFreeFlow getIntermediary(String InterDetails) {
		SessionFactory sf = null; 
		sf = HibernateUtil.getSessionFactory();
		Session session = null; 
		session = sf.openSession();
		session.beginTransaction();
		IntermediaryFreeFlow inter=null;
		try{
			JSONObject jObj=new JSONObject(InterDetails);
		inter=(IntermediaryFreeFlow ) session.get(IntermediaryFreeFlow .class,Integer.parseInt(jObj.getString("IntermId")));
			System.out.println("hi mate");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(session.isOpen())
				session.close();
		}
		return inter ;
	}
	
	public static void main (String args[]) throws JSONException
	{
		CRUDIntermediary save=new CRUDIntermediary();
	save.saveIntermediary("{'type': 'producer','name': 'producer-1','intermediaryCollection': [{ 'type': 'wholesaler', 'name': 'wholeSaler-1','intermediaryCollection': [{ 'type': 'retailer', 'name': 'retailer-1','intermediaryCollection': [{ 'type': 'retailer', 'name': 'retailer-1'}]}]},{ 'type': 'wholesaler', 'name': 'wholesaler-2','intermediaryCollection': [{ 'type': 'retailer', 'name': 'retailer-1'}]},{'type': 'wholesaler','name': 'wholesaler-3'}]}");
	/*try {
			save.UpdateIntermediary("{'parentId': '60','childId':[61,62,62],'intermediaryDetails':[{'name':'CNF-2','type':'CNF'}]}");
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
//	save.DeleteIntermediary("{'IntermId': '72'}");
	save.getIntermediary("{'IntermId':'60'}");
		
	}
}
