package com.rais.manager.controller;

import javax.servlet.http.HttpSession;

import nextapp.echo.webcontainer.WebContainerServlet;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.rais.manager.database.SessionHibernate;
import com.rais.manager.database.User;
import com.rais.manager.interfaz.LoginPane;

public class Login {

	// --------------------------------------------------------------------------------

	private Login() {
		/* empty */
	}

	// --------------------------------------------------------------------------------

	public static boolean login(LoginPane pane) {

		Session session = null;

		try {
			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			Criteria criteria = null;
			try {

				criteria = session.createCriteria(User.class).add( //
						Restrictions.and( //
								Restrictions.eq("cedula", pane.getTxtCedula().getText()), //
								Restrictions.eq("password", //
										Data.encrypt(pane.getFldPassword().getText()))));

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			User user = (User) criteria.uniqueResult();
			pane.getRaisManagerApp().setUser(user);

			if (user == null) {
				return false;
			} else {

				// ----------------------------------------
				// the http session magic!
				// ----------------------------------------

				HttpSession httpSession = //
						WebContainerServlet.getActiveConnection().getRequest().getSession();

				System.err.println("login for user " + user.getName() + //
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

	// --------------------------------------------------------------------------------

}
