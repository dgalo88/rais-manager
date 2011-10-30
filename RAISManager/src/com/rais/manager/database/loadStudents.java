package com.rais.manager.database;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class loadStudents {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		Random random = new Random();

		User[] user = new User[10];
		Student[] student = new Student[10];
		List<Student> studentList;

		List<Group> groupList = session.createCriteria( //
				Group.class).addOrder(Order.asc("id")).list();

		Group[] group = new Group[groupList.size()];
		for (int i = 0; i < group.length; i++) {
			group[i] = groupList.get(i);
		}

		for (int i = 0; i < user.length; i++) {

			user[i] = new User();
			user[i].setAlias(Integer.toString(i + 1));
			user[i].setName("Estudiante Numero " + (i + 1));
			user[i].setCedula(Integer.toString((i+ 1) * random.nextInt(999999)));
			user[i].setMail("mail" + (i + 1) + "@mail.com");

			try {
				user[i].setPassword(encrypt("123456"));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			session.saveOrUpdate(user[i]);

			student[i] = new Student();
			student[i].setUserRef(user[i]);

			int index = random.nextInt(3);
			student[i].setGroupRef(group[index]);

			session.saveOrUpdate(student[i]);

			if (group[index].getStudentList() == null) {
				studentList = new ArrayList<Student>();
			} else {
				studentList = group[index].getStudentList();
			}
			studentList.add(student[i]);
			group[index].setStudentList(studentList);

			user[i].setStudentRef(student[i]);

			session.saveOrUpdate(group[index]);
			session.saveOrUpdate(user[i]);

		}

		for (int i = 0; i < user.length; i++) {
			session.save(user[i]);
		}

		session.getTransaction().commit();
		session.close();

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
