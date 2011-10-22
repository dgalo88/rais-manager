package com.rais.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.rais.manager.database.Estudiante;
import com.rais.manager.database.SessionHibernate;

public class Polls {

	// --------------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public static List<String> loadPartnersData() {

		List<String> partnersList;

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			List<Estudiante> students = session.createCriteria( //
					Estudiante.class).addOrder(Order.asc("nombre")).list();

			partnersList = new ArrayList<String>();
			for (int i = 0; i < students.size(); i++) {
				partnersList.add(students.get(i).getNombre());
			}

		} finally {

			// ----------------------------------------
			// whatever happens, always close
			// ----------------------------------------

			if (session != null) {

				if (session.getTransaction() != null) {
					session.getTransaction().commit();
				}

				session.close();

			}

		}
		return partnersList;

	}

	// --------------------------------------------------------------------------------

}
