package com.rais.manager.database;

import org.hibernate.Session;

public class LoadCompanies {

	public static void main(String[] args) {

		Grupo[] grupo = new Grupo [4];
		for (int i = 0; i < grupo.length; i++) {
			grupo[i] = new Grupo();
			grupo[i].setNombre("Compañía " + Integer.toString(i));
		}

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		for (int i = 0; i < grupo.length; i++) {
			session.save(grupo[i]);
		}

		session.getTransaction().commit();
		session.close();

	}

}
