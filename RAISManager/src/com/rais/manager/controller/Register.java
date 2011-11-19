package com.rais.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.rais.manager.database.Group;
import com.rais.manager.database.GroupStudent;
import com.rais.manager.database.SessionHibernate;
import com.rais.manager.database.Student;
import com.rais.manager.database.User;
import com.rais.manager.interfaz.RegisterPane;
import com.rais.manager.interfaz.RegisterStudentPane;

public class Register {

	// --------------------------------------------------------------------------------

	private Register() {
		/* empty */
	}

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

			registerUser(session, pane);

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
				User.class).add(Restrictions.eq( //
						"alias", pane.getTxtAlias().getText()));

		return criteria.list().isEmpty();

	}

	// --------------------------------------------------------------------------------

	private static boolean checkCedulaExists( //
			Session session, RegisterPane pane) {

		Criteria criteria = session.createCriteria( //
				User.class).add(Restrictions.eq( //
						"cedula", pane.getTxtCedula().getText()));

		return criteria.list().isEmpty();

	}

	// --------------------------------------------------------------------------------

	private static void registerUser(Session session, RegisterPane pane) {

		User bean = pane.getUser();

		bean.setName(pane.getTxtName().getText());
		bean.setCedula(pane.getTxtCedula().getText());
		try {
			bean.setPassword(Data.encrypt( //
					pane.getFldPassword().getText()));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.saveOrUpdate(bean);

	}

	// --------------------------------------------------------------------------------

	public static void registerStudent(RegisterStudentPane panel) {

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			User user = panel.getUser();

			Student student = new Student();
			student.setUserRef(user);
			session.saveOrUpdate(student);

			Group group = panel.getGroup();

			GroupStudent groupStudent = new GroupStudent();
			groupStudent.setStudentRef(student);
			groupStudent.setGroupRef(group);
			session.saveOrUpdate(groupStudent);

			List<GroupStudent> groupStudentList;
			if (student.getGroupStudentList().isEmpty()) {
				groupStudentList = new ArrayList<GroupStudent>();
			} else {
				groupStudentList = student.getGroupStudentList();
			}
			groupStudentList.add(groupStudent);
			group.setGroupStudentList(groupStudentList);
			student.setGroupStudentList(groupStudentList);

			session.saveOrUpdate(group);
			session.saveOrUpdate(student);

			user.setStudentRef(student);
			user.setTeacherRef(null);
			session.saveOrUpdate(user);

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
	public static List<Group> loadCompaniesData() {

		List<Group> groupList;
		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			groupList = session.createCriteria( //
					Group.class).addOrder(Order.asc("id")).list();

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
		return groupList;

	}

	// --------------------------------------------------------------------------------

}
