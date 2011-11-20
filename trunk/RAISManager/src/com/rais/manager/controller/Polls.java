package com.rais.manager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nextapp.echo.app.Label;
import nextapp.echo.app.RadioButton;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.rais.manager.TestTableModel;
import com.rais.manager.database.Group;
import com.rais.manager.database.GroupStudent;
import com.rais.manager.database.Poll;
import com.rais.manager.database.PollStudent;
import com.rais.manager.database.SessionHibernate;
import com.rais.manager.database.Student;
import com.rais.manager.database.User;
import com.rais.manager.interfaz.AutoCoEvaluationPane;
import com.rais.manager.interfaz.MainPane;

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

	/**
	 * Load a list of partners of company.
	 * 
	 * @param user
	 * @return A list of user, which are partners of company
	 */
	public static List<User> loadPartnersData(User user) {

		List<User> partnersList = new ArrayList<User>();

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria( //
				User.class).add(Restrictions.eq( //
						"id", user.getId()));

		user = (User) criteria.uniqueResult();
		Student student = user.getStudentRef();

		GroupStudent groupStudent = //
				student.getGroupStudentList().get(0);

		Group group = groupStudent.getGroupRef();

		List<GroupStudent> groupStudentList = group.getGroupStudentList();

		groupStudentList.remove(groupStudent);

		for (int i = 0; i < groupStudentList.size(); i++) {

			partnersList.add(groupStudentList.get(i) //
					.getStudentRef().getUserRef());

		}

		session.getTransaction().commit();
		session.close();

		return partnersList;

	}

	// --------------------------------------------------------------------------------

	public static void answerAutoEvaluation(AutoCoEvaluationPane panel, User user) //
			throws IOException {

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria( //
				User.class).add(Restrictions.eq( //
						"id", user.getId()));

		user = (User) criteria.uniqueResult();
		Student student = user.getStudentRef();

		Poll poll = null;
		List<Poll> pollList = student.getPollList();

		for (int i = 0; i < pollList.size(); i++) {
			if (pollList.get(i).getStatus().equals(Polls.PENDING)) {
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

		panel.setPoll(poll);

		//Crear Encuestas de Co-Evaluación: 
		//1) Cargar lista de compañeros
		//2) Crear encuesta para cada compañero
		panel.setPartnersList(loadPartnersData(user));
		createCoEvaluations(panel, session, student);

		session.getTransaction().commit();
		session.close();

	}

	// --------------------------------------------------------------------------------

	private static void createCoEvaluations(AutoCoEvaluationPane panel, //
			Session session, Student student) {

		Criteria criteria = session.createCriteria( //
				Poll.class).add(Restrictions.eq( //
						"id", panel.getPoll().getId()));

		Poll poll = (Poll) criteria.uniqueResult();

		List<PollStudent> pollStudentList;
		if (student.getPollStudentList() != null) {
			pollStudentList = student.getPollStudentList();
		} else {
			pollStudentList = new ArrayList<PollStudent>();
		}

		List<User> partnersList = panel.getPartnersList();

		for (int i = 0; i < partnersList.size(); i++) {

			criteria = session.createCriteria( //
					User.class).add(Restrictions.eq( //
							"id", partnersList.get(i).getId()));

			User userPartner = (User) criteria.uniqueResult();
			Student studentPartner = (Student) userPartner.getStudentRef();

			PollStudent pollStudent = new PollStudent();
			pollStudent.setPollRef(poll);
			pollStudent.setStudentRef(studentPartner);
			pollStudent.setStatus(Polls.NO_ANSWERED);
			pollStudent.setValuation(Polls.NO_ANSWERED);

			session.save(pollStudent);

			pollStudentList.add(pollStudent);

		}

		session.update(student);
		session.update(poll);

	}

	// --------------------------------------------------------------------------------

	public static void answerCoEvaluation(AutoCoEvaluationPane panel, User user) //
			throws IOException {

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria( //
				Poll.class).add(Restrictions.eq( //
						"id", panel.getPoll().getId()));

		Poll poll = (Poll) criteria.uniqueResult();

		List<PollStudent> pollStudentList = poll.getPollStudentList();

		List<User> partnersList = panel.getPartnersList();
		RadioButton[][] coRadioButtons = panel.getCoRadioBtn();

		for (int i = 0; i < partnersList.size(); i++) {

			criteria = session.createCriteria( //
					User.class).add(Restrictions.eq( //
							"id", partnersList.get(i).getId()));

			User userPartner = (User) criteria.uniqueResult();
			Student studentPartner = (Student) userPartner.getStudentRef();

			PollStudent pollStudent = getPollStudent(studentPartner, pollStudentList);

			for (int j = 0; j < 4; j++) {
				if (coRadioButtons[i][j].isSelected()) {
					pollStudent.setValuation(getOptionSelected(j));
				}
			}
			pollStudent.setStatus(Polls.ANSWERED);

			session.update(pollStudent);
			session.update(studentPartner);

		}
		session.update(poll);

		session.getTransaction().commit();
		session.close();

	}

	// --------------------------------------------------------------------------------

	private static PollStudent getPollStudent( //
			Student studentPartner, List<PollStudent> pollStudentList) {

		for (PollStudent pollStudent : pollStudentList) {
			if (pollStudent.getStudentRef().equals(studentPartner)) {
				return pollStudent;
			}
		}
		return null;

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

	public static int checkPendingPolls(User user) {

		Session session = null;
		int pollNum = 0;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria( //
					User.class).add(Restrictions.eq( //
							"id", user.getId()));

			user = (User) criteria.uniqueResult();
			Student student = user.getStudentRef();

			List<Poll> pollList = student.getPollList();

			if (pollList.isEmpty()) {
				return 0;
			}

			for (int i = 0; i < pollList.size(); i++) {

				if (pollList.get(i).getStatus().equals(Polls.PENDING)) {
					pollNum++;
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

		return pollNum;

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

	@SuppressWarnings("unchecked")
	public static void getNoAnsweredPolls(MainPane panel, //
			TestTableModel tableDtaModel, List<Poll> pollList) {

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		pollList = (List<Poll>) session.createCriteria( //
				Poll.class).add(Restrictions.eq( //
						"status", Polls.NO_ANSWERED)).list();

		panel.setPollList(pollList);

		for (Poll poll : pollList) {
			tableDtaModel.add(poll);
		}

		session.getTransaction().commit();
		session.close();

	}

	// --------------------------------------------------------------------------------

}
