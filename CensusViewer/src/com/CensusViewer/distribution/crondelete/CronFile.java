package com.CensusViewer.distribution.crondelete;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.CensusViewer.HibernateUtility.HibernateUtil;

public class CronFile implements Job
{
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		SessionFactory sf=HibernateUtil.getSessionFactory();
		Session session= sf.openSession();	
		session.beginTransaction();
		Query query = session.createSQLQuery(
				"select distribution.statusdelete()");
	List result=query.list();
	}

}
