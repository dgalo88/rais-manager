package com.rais.manager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nextapp.echo.app.Label;
import nextapp.echo.app.RadioButton;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.rais.manager.database.Group;
import com.rais.manager.database.GroupStudent;
import com.rais.manager.database.Poll;
import com.rais.manager.database.SessionHibernate;
import com.rais.manager.database.Student;
import com.rais.manager.database.User;
import com.rais.manager.interfaz.AutoCoEvaluationPane;

public class Polls {

	public static final String SENT = new String("SENT");
	public static final String PENDING = new String("PENDING");
	public static final String ANSWERED = new String("ANSWERED");
	public static final String NO_ANSWERED = new String("NO_ANSWERED");

	// --------------------------------------------------------------------------------

	private Polls() {
		/* empty */
	}

	// --------------------------------------------------------------------------------

	public static List<String> loadPartnersData(User user) {

		List<String> partnersList = new ArrayList<String>();

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			List<GroupStudent> groupStudentList = //
					user.getStudentRef().getGroupStudentList();

			for (int i = 0; i < groupStudentList.size(); i++) {

				partnersList.add(groupStudentList.get(i) //
						.getStudentRef().getUserRef().getName());

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

	public static void exportAutoEvaluation(AutoCoEvaluationPane panel, User user) //
			throws IOException {

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			Student student = user.getStudentRef();

			Poll poll = null;
			List<Poll> pollList;

			if (student.getPollList() == null) {
				pollList = new ArrayList<Poll>();
			} else {
				pollList = student.getPollList();
			}

			for (int i = 0; i < pollList.size(); i++) {
				if (pollList.get(i).getStatus() == Polls.PENDING) {
					poll = pollList.get(i);
				}
			}

			Label[] elements = panel.getLblElement();
			RadioButton[][] autoRadioButtons = panel.getAutoRadioBtn();

			String[] answers = new String[elements.length];

			for (int i = 0; i < elements.length; i++) {

				answers[i] = new String();

				for (int j = 0; j < 4; j++) {

					if (autoRadioButtons[i][j].isSelected()) {
						answers[i] = getOptionSelected(j);
					}

				}

			}

			poll.setAnswer1(answers[0]);
			poll.setAnswer2(answers[1]);
			poll.setAnswer3(answers[2]);
			poll.setAnswer4(answers[3]);
			poll.setAnswer5(answers[4]);
			poll.setSentDate(Calendar.getInstance().getTime());
			poll.setStatus(Polls.ANSWERED);
			session.update(poll);


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

	}

	// --------------------------------------------------------------------------------

	public static void exportCoEvaluation(AutoCoEvaluationPane panel) //
			throws IOException {

		List<String> partnersList = panel.getPartnersList();
		RadioButton[][] coRadioButtons = panel.getCoRadioBtn();

		for (int i = 0; i < partnersList.size(); i++) {

			for (int j = 0; j < 4; j++) {
				if (coRadioButtons[i][j].isSelected()) {
					getOptionSelected(j);
				}
			}

		}

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

	@SuppressWarnings("unchecked")
	public static void closeNoAnsweredPolls(Calendar currentDate) {

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			List<Poll> pollList = (List<Poll>) session.createCriteria( //
					Poll.class).add(Restrictions.eq( //
							"status", Polls.PENDING)).list();

			if (pollList.isEmpty()) {
				return;
			}

			for (int i = 0; i < pollList.size(); i++) {

				Poll poll = pollList.get(i);
				poll.setStatus(Polls.NO_ANSWERED);
				poll.setSentDate(currentDate.getTime());
				poll.setAnswer1(Polls.NO_ANSWERED);
				poll.setAnswer2(Polls.NO_ANSWERED);
				poll.setAnswer3(Polls.NO_ANSWERED);
				poll.setAnswer4(Polls.NO_ANSWERED);
				poll.setAnswer5(Polls.NO_ANSWERED);
				session.update(poll);

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

	}

	// --------------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public static void createPolls(Calendar currentDate) {

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			List<Student> studentList = (List<Student>) session.createCriteria( //
					Student.class).add(Restrictions.conjunction()).list();

			if (studentList.isEmpty()) {
				return;
			}

			List<Poll> pollList;

			for (int i = 0; i < studentList.size(); i++) {

				Student student = studentList.get(i);

				Group group = student.getGroupStudentList().get(0).getGroupRef();

				Poll poll = new Poll();
				poll.setReceivedDate(currentDate.getTime());
				poll.setStatus(Polls.PENDING);
				poll.setStudentRef(student);
				poll.setGroupRef(group);
				session.save(poll);

				if (student.getPollList() == null) {
					pollList = new ArrayList<Poll>();
				} else {
					pollList = student.getPollList();
				}
				pollList.add(poll);

				student.setPollList(pollList);
				session.update(student);

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

	}

	// --------------------------------------------------------------------------------

}
