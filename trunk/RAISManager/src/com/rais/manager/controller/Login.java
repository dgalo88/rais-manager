package com.rais.manager.controller;

import javax.servlet.http.HttpSession;

import nextapp.echo.webcontainer.WebContainerServlet;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.rais.manager.database.Estudiante;
import com.rais.manager.database.SessionHibernate;
import com.rais.manager.interfaz.LoginPane;

public class Login {

	public static boolean login(LoginPane pane) {

		Session session = null;

		try {
			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Estudiante.class).add( //
					Restrictions.and( //
							Restrictions.eq("nick", pane.getTxtUser().getText()), //
							Restrictions.eq("pass", pane.getFldPassword().getText())));

			Estudiante user = (Estudiante) criteria.uniqueResult();

			if (user == null) {
				return false;
			} else {

				// ----------------------------------------
				// the http session magic!
				// ----------------------------------------

				HttpSession httpSession = //
						WebContainerServlet.getActiveConnection().getRequest().getSession();

				System.err.println("login for user " + user.getNombre() + //
						" in http session " + httpSession.getId());

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
		return true;

	}

}
