package com.rais.manager.controller;

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
import com.rais.manager.interfaz.AddStudentPane;
import com.rais.manager.interfaz.RegisterPane;

public class Register {

	// --------------------------------------------------------------------------------

	private Register() {
		/* empty */
	}

	// --------------------------------------------------------------------------------

	public static boolean createUser(RegisterPane panel) {

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			if (!checkAliasUsed(session, panel.getTxtAlias().getText())) {
				panel.setMessage("El nombre de usuario no está disponible");
				return false;
			}

			if (!checkCedulaExists(session, panel.getTxtCedula().getText())) {
				panel.setMessage("El estudiante ya ha sido registrado");
				return false;
			}

			registerUser(session, panel.getUser(), panel.getTxtName().getText(), //
					panel.getTxtCedula().getText(), panel.getFldPassword().getText());

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

	public static boolean createUser(AddStudentPane panel) {

		Session session = null;

		try {

			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			if (!checkCedulaExists(session, panel.getTxtCedula().getText())) {
				panel.setMessage("El estudiante ya ha sido registrado");
				return false;
			}

			registerUser(session, //
					panel.getTxtName().getText(), //
					panel.getTxtCedula().getText(), //
					panel.getCompany());

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

	public static boolean checkAliasUsed( //
			Session session, String alias) {

		Criteria criteria = session.createCriteria( //
				User.class).add(Restrictions.eq( //
						"alias", alias));

		return criteria.list().isEmpty();

	}

	// --------------------------------------------------------------------------------

	public static boolean checkCedulaExists( //
			Session session, String cedula) {

		Criteria criteria = session.createCriteria( //
				User.class).add(Restrictions.eq( //
						"cedula", cedula));

		return criteria.list().isEmpty();

	}

	// --------------------------------------------------------------------------------

	public static void registerUser(Session session, User user, //
			String name, String cedula, String password) {

		user.setName(name);
		user.setCedula(cedula);

		try {
			user.setPassword(Data.encrypt(password));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.saveOrUpdate(user);

	}

	// --------------------------------------------------------------------------------

	public static User registerUser(Session session, //
			String name, String cedula, Group group) {

		User user = new User();

		user.setName(name);
		user.setCedula(cedula);

		try {
			user.setPassword(Data.encrypt( //
					cedula.substring(cedula.length() - 6)));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.save(user);

		registerStudent(session, user, group);

		return user;

	}

	// --------------------------------------------------------------------------------

	public static void registerStudent(Session session, User user, Group group) {

		Student student = new Student();
		student.setUserRef(user);
		session.save(student);

		GroupStudent groupStudent = new GroupStudent();
		groupStudent.setStudentRef(student);
		groupStudent.setGroupRef(group);
		session.save(groupStudent);

		user.setStudentRef(student);
		user.setTeacherRef(null);
		session.update(user);

	}

	// --------------------------------------------------------------------------------

	public static void registerStudent(User user, Group group) {

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		registerStudent(session, user, group);

		session.getTransaction().commit();
		session.close();

	}

	// --------------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public static List<Group> loadCompaniesData() {

		List<Group> groupList;

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		groupList = session.createCriteria( //
				Group.class).addOrder(Order.asc("id")).list();

		session.getTransaction().commit();
		session.close();

		return groupList;

	}

	// --------------------------------------------------------------------------------

}
