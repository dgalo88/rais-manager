package com.rais.manager.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.csvreader.CsvReader;
import com.rais.manager.database.Group;
import com.rais.manager.database.SessionHibernate;
import com.rais.manager.database.User;
import com.rais.manager.interfaz.ImportStudentsPane;

public class Import {

	// --------------------------------------------------------------------------------

	private Import() {
		/* empty */
	}

	// --------------------------------------------------------------------------------

	public static void loadData(String pathFile, //
			List<String> names, List<String> cedulas, //
			List<String> companies) throws IOException {

		CsvReader csv = new CsvReader(pathFile, ',', Charset.forName("UTF-8"));

		while (csv.readRecord()) {

			String name = csv.get(0);
			names.add(name);

			String cedula = csv.get(1);
			cedulas.add(cedula);

			String company = csv.get(2);
			companies.add(company);

		}
		csv.close();

		System.out.println("names size = " + names.size() + "\n" //
				+ "cedulas size = " + cedulas.size() + "\n" //
				+ "companies size = " + companies.size());
		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i) + " " //
					+ cedulas.get(i) + " " + companies.get(i));
		}

	}

	// --------------------------------------------------------------------------------

	public static void loadStudents( //
			ImportStudentsPane panel, List<User> userList) throws IOException {

		for (int i = 0; i < panel.getNames().size(); i++) {

			if (!createUser(panel, userList, panel.getNames().get(i), //
					panel.getCedulas().get(i), panel.getCompanies().get(i))) {
				return;
			}

		}

		if (userList.isEmpty()) {
			panel.setMessage("Ha ocurrido un error. " //
					+ "Los estudiantes no han sido registrados");
			return;
		}

		panel.setMessage("Estudiantes registrados con éxito");

	}

	// --------------------------------------------------------------------------------

	private static boolean createUser( //
			ImportStudentsPane panel, List<User> userList,//
			String name, String cedula, String company) {

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			if (!Register.checkCedulaExists(session, cedula)) {
				panel.setMessage("El estudiante ya ha sido registrado");
				return false;
			}

			Criteria criteria = session.createCriteria( //
					Group.class).add(Restrictions.eq( //
							"name", company));

			Group group = (Group) criteria.uniqueResult();

			User user = Register.registerUser(session, name, //
					Data.checkCedulaFormat(cedula), group);
			userList.add(user);

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
