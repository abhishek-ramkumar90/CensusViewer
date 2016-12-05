package com.CensusViewer.distribution.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;






import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.CensusViewer.HibernateUtility.HibernateUtil;
import com.CensusViewer.distribution.model.Node1;
import com.CensusViewer.distribution.model.WKT;
public class ShortestPathDao {
	public Collection<String> getWKTList(String wktJson) throws JSONException {
		/*ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		  mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			ShortestPathJackson shortestPath=mapper.readValue(wktJson,ShortestPathJackson.class );
			String startLat=shortestPath.getStartLat();
		}
		catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	 JSONObject obj = new JSONObject(wktJson);
	 String startLat=obj.getString("startLat");
	 double startLatitude = Double.parseDouble(startLat);
	 String startLong=obj.getString("startLong");
	 double startLongitude = Double.parseDouble(startLong);
	 String endLat=obj.getString("endLat");
	 double endLatitude= Double.parseDouble(endLat);
	 String endLong=obj.getString("endLong");
	 double endLongitude = Double.parseDouble(endLong);
	 int node1;
	 int node2;
	 SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		 Query query = session.getNamedQuery("NodeList").setDouble("latitude",startLatitude).setDouble("logitude",startLongitude);;
		List<Node1> StartNode=query.list();
		int startNode = 0; 
		for(Node1 n:StartNode)
		{startNode=n.getId();
		}	
		 Query query1 = session.getNamedQuery("NodeList").setDouble("latitude",endLatitude).setDouble("logitude",endLongitude);;
			List<Node1> EndNode=query1.list();
			int endNode = 0;
			for(Node1 n1:EndNode)
			{endNode=n1.getId();
			}
			System.out.println("startnode is"+startNode);
			System.out.println("endnode is"+endNode);
			 Query query2 = session.getNamedQuery("WKTList").setInteger("startNode",startNode).setInteger("endNode",endNode);;
			 List<WKT> WKTList=query2.list();
			 String wktlist;
			
			 Collection<String> wktList=new ArrayList<String>();
			 for(WKT wkt:WKTList)
				{wktlist=wkt.getWkt();
				wktList.add(wktlist);
				}
			 session.getTransaction().commit();
				session.close();
			 return wktList;
}
}
