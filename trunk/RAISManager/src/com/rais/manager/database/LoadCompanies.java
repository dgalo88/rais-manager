package com.rais.manager.database;

import org.hibernate.Session;

public class LoadCompanies {

	public static void main(String[] args) {

		Group[] group = new Group [3];
		for (int i = 0; i < group.length; i++) {
			group[i] = new Group();
			group[i].setName("Compañía " + Integer.toString(i + 1));
		}

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		for (int i = 0; i < group.length; i++) {
			session.save(group[i]);
		}

		session.getTransaction().commit();
		session.close();

	}

}
