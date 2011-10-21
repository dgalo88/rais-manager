package com.rais.manager.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class SessionHibernate {

	//singleton
	private static SessionHibernate instance = new SessionHibernate();

	private SessionFactory sessionFactory;

	// --------------------------------------------------------------------------------

	private SessionHibernate() {

		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("/com/rais/manager/database/hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();

	}

	// --------------------------------------------------------------------------------

	public static SessionHibernate getInstance() {
		return instance;
	}

	// --------------------------------------------------------------------------------

	public Session getSession() {
		return sessionFactory.openSession();
	}

	// --------------------------------------------------------------------------------

}
