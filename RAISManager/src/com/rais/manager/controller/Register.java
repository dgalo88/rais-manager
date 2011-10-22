package com.rais.manager.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.rais.manager.database.Estudiante;
import com.rais.manager.database.Grupo;
import com.rais.manager.database.SessionHibernate;
import com.rais.manager.interfaz.RegisterPane;

public class Register {

	// --------------------------------------------------------------------------------

	public static boolean createUser(RegisterPane pane) {

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			if (!checkAliasUsed(session, pane)) {
				pane.setMessage("El nombre de usuario no está disponible");
				return false;
			}

			if (!checkCedulaExists(session, pane)) {
				pane.setMessage("El estudiante ya ha sido registrado");
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

	private static boolean checkAliasUsed( //
			Session session, RegisterPane pane) {

		Criteria criteria = session.createCriteria( //
				Estudiante.class).add(Restrictions.eq( //
						"alias", pane.getTxtAlias().getText()));

		return criteria.list().isEmpty();

	}

	// --------------------------------------------------------------------------------

	private static boolean checkCedulaExists( //
			Session session, RegisterPane pane) {

		Criteria criteria = session.createCriteria( //
				Estudiante.class).add(Restrictions.eq( //
						"cedula", pane.getTxtCedula().getText()));

		return criteria.list().isEmpty();

	}

	// --------------------------------------------------------------------------------

	private static void register(Session session, RegisterPane pane) {

		Estudiante bean = pane.getStudent();

		bean.setAlias(pane.getTxtAlias().getText());
		bean.setNombre(pane.getTxtName().getText());
		bean.setCedula(pane.getTxtCedula().getText());
		bean.setMail(pane.getTxtMail().getText());
		bean.setPassword(pane.getFldPassword().getText());

		session.saveOrUpdate(bean);

	}

	// --------------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public static String[] loadCompaniesData() {

		String[] companyMenu;
		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			List<Grupo> groups = session.createCriteria( //
					Grupo.class).addOrder(Order.asc("id")).list();

			companyMenu = new String[groups.size() + 1];
			companyMenu[0] = "Seleccione su Compañía";
			for (int i = 0; i < groups.size(); i++) {
				companyMenu[i + 1] = groups.get(i).getNombre();
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
		return companyMenu;

	}

	// --------------------------------------------------------------------------------

}
