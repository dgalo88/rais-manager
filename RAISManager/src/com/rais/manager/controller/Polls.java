package com.rais.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.rais.manager.database.Group;
import com.rais.manager.database.SessionHibernate;
import com.rais.manager.database.User;

public class Polls {

	// --------------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public static List<String> loadPartnersData(User user) {

		List<String> partnersList = new ArrayList<String>();

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			Group group = user.getStudentRef().getGroupRef();

			List<User> userList = session.createCriteria( //
					User.class).addOrder(Order.asc("name")).list();

			for (int i = 0; i < userList.size(); i++) {

				Group curr = userList.get(i).getStudentRef().getGroupRef();

				if ((curr.getId() == group.getId()) //
						&& (userList.get(i).getId() != user.getId())) {
					partnersList.add(userList.get(i).getName());
				}

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
