package com.rais.manager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nextapp.echo.app.Label;
import nextapp.echo.app.RadioButton;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.csvreader.CsvWriter;
import com.rais.manager.database.Group;
import com.rais.manager.database.SessionHibernate;
import com.rais.manager.database.User;
import com.rais.manager.interfaz.AutoCoEvaluationPane;

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

	public static void exportAutoEvaluation(AutoCoEvaluationPane panel) //
			throws IOException {

		CsvWriter csv = panel.getCsv();

		csv.write("1. Auto Evaluación");
		csv.endRecord();

		csv.write("No.");
		csv.write("Elemento");
		csv.write("Opción");
		csv.endRecord();

		Label[] elements = panel.getLblElement();
		RadioButton[][] autoRadioButtons = panel.getAutoRadioBtn();

		for (int i = 0; i < elements.length; i++) {

			csv.write("1." + (i + 1));
			csv.write(elements[i].getText());

			for (int j = 0; j < 4; j++) {
				if (autoRadioButtons[i][j].isSelected()) {
					csv.write(getOptionSelected(j));
				}
			}

			csv.endRecord();

		}

	}

	// --------------------------------------------------------------------------------

	public static void exportCoEvaluation(AutoCoEvaluationPane panel) //
			throws IOException {

		CsvWriter csv = panel.getCsv();

		csv.endRecord();

		csv.write("2. Co Evaluación");
		csv.endRecord();

		csv.write("Nombre");
		csv.write("Opción");
		csv.endRecord();

		List<String> partnersList = panel.getPartnersList();
		RadioButton[][] coRadioButtons = panel.getCoRadioBtn();

		for (int i = 0; i < partnersList.size(); i++) {

			csv.write(partnersList.get(i));

			for (int j = 0; j < 4; j++) {
				if (coRadioButtons[i][j].isSelected()) {
					csv.write(getOptionSelected(j));
				}
			}

			csv.endRecord();

		}

		csv.close();

	}

	// --------------------------------------------------------------------------------

	private static String getOptionSelected(int index) {

		String option = "";

		switch (index) {
		case 0:
			option = "A";
			break;
		case 1:
			option = "B";
			break;
		case 2:
			option = "C";
			break;
		case 3:
			option = "D";
			break;
		default:
			break;
		}
		return option;

	}

	// --------------------------------------------------------------------------------

}
