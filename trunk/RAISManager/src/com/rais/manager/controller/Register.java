package com.rais.manager.controller;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.rais.manager.database.Estudiante;
import com.rais.manager.database.SessionHibernate;
import com.rais.manager.interfaz.RegisterPane;

public class Register {

	public static boolean createUser(RegisterPane pane) {

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			if (!checkNickAlreadyUsed(session, pane)) {
				return false;
			}

			register(session, pane);

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
		return true;

	}

	// --------------------------------------------------------------------------------

	private static boolean checkNickAlreadyUsed( //
			Session session, RegisterPane pane) {

		Criteria criteria = session.createCriteria( //
				Estudiante.class).add(Restrictions.eq( //
						"alias", pane.getTxtAlias().getText()));

		if (criteria.list().size() != 0) {
			return false;
		} else {
			return true;
		}

	}

	// --------------------------------------------------------------------------------

	private static void register(Session session, RegisterPane pane) {

		Estudiante bean = new Estudiante();

		bean.setAlias(pane.getTxtAlias().getText());
		bean.setNombre(pane.getTxtName().getText());
		bean.setCedula(pane.getTxtCedula().getText());
		bean.setMail(pane.getTxtMail().getText());
		bean.setPassword(pane.getFldPassword().getText());

		session.save(bean);

	}

	// --------------------------------------------------------------------------------


}
