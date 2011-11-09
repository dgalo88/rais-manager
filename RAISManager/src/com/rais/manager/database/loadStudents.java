package com.rais.manager.database;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class loadStudents {

	// --------------------------------------------------------------------------------

	private loadStudents() {
		/* empty */
	}

	// --------------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		Random random = new Random();

		//Cargar compañías

		List<Group> groupList = session.createCriteria( //
				Group.class).addOrder(Order.asc("id")).list();

		Group[] group = new Group[groupList.size()];
		for (int i = 0; i < group.length; i++) {
			group[i] = groupList.get(i);
		}

		User[] user = new User[10];
		Student[] student = new Student[10];
		List<GroupStudent> groupStudentList;

		//Cargar jefe ejecutivo

		user[0] = new User();
		user[0].setCedula("12345678");
		user[0].setName("Jefe Ejecutivo");
		try {
			user[0].setPassword(encrypt("123456"));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.save(user[0]);

		Teacher teacher = new Teacher();
		teacher.setUserRef(user[0]);
		session.save(teacher);

		List<GroupTeacher> groupTeacherList;

		if (teacher.getGroupTeacherList() == null) {
			groupTeacherList = new ArrayList<GroupTeacher>();
		} else {
			groupTeacherList = teacher.getGroupTeacherList();
		}

		for (int i = 0; i < group.length; i++) {

			GroupTeacher groupTeacher = new GroupTeacher();
			groupTeacher.setGroupRef(group[i]);
			groupTeacher.setTeacherRef(teacher);
			session.save(groupTeacher);

			groupTeacherList.add(groupTeacher);

		}

		for (int i = 0; i < group.length; i++) {
			group[i].setGroupTeacherList(groupTeacherList);
			session.update(group[i]);
		}

		teacher.setGroupTeacherList(groupTeacherList);
		session.update(teacher);

		user[0].setTeacherRef(teacher);
		user[0].setStudentRef(null);
		session.update(user[0]);

		//Cargar estudiantes

		for (int i = 1; i < student.length; i++) {

			user[i] = new User();
			user[i].setName("Estudiante Numero " + (i));
			user[i].setCedula(Integer.toString(i));
			try {
				user[i].setPassword(encrypt("123456"));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.save(user[i]);

			student[i] = new Student();
			student[i].setUserRef(user[i]);
			session.save(student[i]);

			int index = random.nextInt(3);

			GroupStudent groupStudent = new GroupStudent();
			groupStudent.setGroupRef(group[index]);
			groupStudent.setStudentRef(student[i]);
			session.save(groupStudent);

			if (student[i].getGroupStudentList() == null) {
				groupStudentList = new ArrayList<GroupStudent>();
			} else {
				groupStudentList = student[i].getGroupStudentList();
			}
			groupStudentList.add(groupStudent);

			student[i].setGroupStudentList(groupStudentList);
			group[index].setGroupStudentList(groupStudentList);

			session.update(group[index]);
			session.update(student[i]);

			user[i].setStudentRef(student[i]);
			user[i].setTeacherRef(null);
			session.update(user[i]);

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
