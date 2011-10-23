package com.rais.manager.controller;

import java.security.MessageDigest;

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

			Criteria criteria = null;
			try {

				criteria = session.createCriteria(Estudiante.class).add( //
						Restrictions.and( //
								Restrictions.eq("alias", pane.getTxtUser().getText()), //
								Restrictions.eq("password", //
										encrypt(pane.getFldPassword().getText()))));

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

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

	// --------------------------------------------------------------------------------

	private static String encrypt(String password) //
			throws IllegalStateException, Exception {

		MessageDigest md = MessageDigest.getInstance("SHA");
		byte[] b = md.digest(password.getBytes());

		int size = b.length;
		StringBuffer h = new StringBuffer(size);

		for (int i = 0; i < size; i++) {

			int u = b[i] & 255;

			if (u < 16) {
				h.append("0" + Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}

		}
		return h.toString();

	}

	// --------------------------------------------------------------------------------

}
