package com.mars.census.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mars.census.model.CategoryJackson;
import com.mars.census.model.CategoryJacksonId;
import com.mars.census.model.DetailStateJackson;
import com.mars.census.model.SubCategoryJackson;
import com.mars.census.model.maharashtraDistrict1;
import com.mars.census.model.JacksonDetails;




public class DetailsDaoImp {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public List retreiveDetails(String HQL) {

		return hibernateTemplate.find(HQL);
	}

	public List<maharashtraDistrict1> districtDetails(String detailsString) {
		String subname = null;
		String condition=null;
		String val=null;
		List<maharashtraDistrict1> listD=new ArrayList();
		List<maharashtraDistrict1> listp = new ArrayList();
		System.out.println("Mapping class");
		System.out.println("Mapping class"+detailsString);
		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//mapper.configure (SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);

		try {

			// convert user object to json string, and save to a file
			JacksonDetails details=mapper.readValue(detailsString,JacksonDetails.class );
			//System.out.println(district);
			// display to console
			StringBuilder whereCluase = new StringBuilder();
			System.out.println(mapper.writeValueAsString(details));
			List<DetailStateJackson> list1= (List<DetailStateJackson>) details.getStateDetails();

			for (DetailStateJackson myPerson : list1) {
				String st_code=myPerson.getSt_code();
				String st_name=myPerson.getSt_name();
				System.out.println("StateCode"+st_code);
				List<CategoryJackson> list2=(List<CategoryJackson>) myPerson.getCategory();
				for (CategoryJackson myCate : list2){
					List<CategoryJacksonId>	list3=(List<CategoryJacksonId>) myCate.getCat_ids();
					for(CategoryJacksonId cate:list3){
						String catId=cate.getId();
						System.out.println("categoryId"+catId);
						List<SubCategoryJackson> list4=(List<SubCategoryJackson>) cate.getSub_category();
						for(SubCategoryJackson subcate:list4){
							subname=subcate.getName();
							//col_name.add(subname);
							System.out.println(subname);
							condition=subcate.getCondition();
							// condt.add( condition);
							System.out.println(condition);
							val=subcate.getVal();
							// vals.add(val);
							Map map = new HashMap();

							whereCluase.append(subcate.getName());
							whereCluase.append(" ");
							whereCluase.append(subcate.getCondition());
							whereCluase.append(" '");
							whereCluase.append(subcate.getVal());
							whereCluase.append("' ");
							whereCluase.append("and ");
							//whereClause.append(" and ");
							//Adding values to the HashMap
							//map.put(subname, val);

						}

					}
				}

				ApplicationContext appContext = new ClassPathXmlApplicationContext(
						"config/spring-configuration.xml");
				DetailsDaoImp detailsDao = (DetailsDaoImp)appContext.getBean("detailsDao");
				//StringBuilder hql = new StringBuilder();
				String whrpart=whereCluase.toString();
				String hql=" from district";
				hql+=" where "+whrpart+" statecode='"+st_code+"'";
				//hql+= " "+subname+" "+condition+" '1000' and statecode='"+st_code+"'" ;
				System.out.println(hql);
				List<maharashtraDistrict1> listp1 =( List<maharashtraDistrict1>)detailsDao.retreiveDetails(hql);
				System.out.println("aaaaaaaa"+listp1);

				for (maharashtraDistrict1 distD : listp1) {  

					maharashtraDistrict1 dw=new maharashtraDistrict1();

					//Integer villageId=myCountry.getId();
					String distName= distD.getName();
					System.out.println(distName);
					dw.setName(distName);
					BigDecimal tot_m=distD.getMarg_cl_p();
					dw.setMarg_cl_p(tot_m);
					dw.setSt_name(st_name);
					String dist_code=distD.getDistcode();
					dw.setDist_code(dist_code);
					//String countryName=myCountry.getC_name();
					//country.setC_name(countryName);
					listD.add(dw);

					System.out.println("abccc..."+listD);

				}

			}

			return listD;

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return listp;
	}

}
