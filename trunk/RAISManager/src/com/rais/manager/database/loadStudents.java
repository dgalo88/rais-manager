package com.rais.manager.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.rais.manager.controller.Data;

public class loadStudents {

	public static final int STUDENTS_NUM = 10;
	public static final int TEACHERS_NUM = 1;

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

		User[] user = new User[STUDENTS_NUM + TEACHERS_NUM];

		//Cargar jefe ejecutivo

		Teacher[] teacher = new Teacher[TEACHERS_NUM];

		for (int i = 0; i < teacher.length; i++) {

			user[i] = new User();
			user[i].setCedula(Integer.toString(1234 + i));
			user[i].setName("Jefe Ejecutivo " + Integer.toString(i + 1));

			try {
				user[i].setPassword(Data.encrypt("123456"));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.save(user[i]);

			teacher[i] = new Teacher();
			teacher[i].setUserRef(user[i]);
			session.save(teacher[i]);

			List<GroupTeacher> groupTeacherList;

			if (teacher[i].getGroupTeacherList() == null) {
				groupTeacherList = new ArrayList<GroupTeacher>();
			} else {
				groupTeacherList = teacher[i].getGroupTeacherList();
			}

			for (int j = 0; j < group.length; j++) {

				GroupTeacher groupTeacher = new GroupTeacher();
				groupTeacher.setGroupRef(group[j]);
				groupTeacher.setTeacherRef(teacher[i]);
				session.save(groupTeacher);

				groupTeacherList.add(groupTeacher);

			}

			for (int k = 0; k < group.length; k++) {
				group[k].setGroupTeacherList(groupTeacherList);
				session.update(group[k]);
			}

			teacher[i].setGroupTeacherList(groupTeacherList);
			session.update(teacher[i]);

			//Me aseguro que si el usuario es profesor
			//la referencia a estudiante es null
			user[i].setTeacherRef(teacher[i]);
			user[i].setStudentRef(null);
			session.update(user[i]);

		}

		//Cargar estudiantes

		Student[] student = new Student[STUDENTS_NUM];
		List<GroupStudent> groupStudentList;

		for (int i = 0; i < student.length; i++) {

			user[i] = new User();
			user[i].setName("Estudiante Numero " + (i + 1));
			user[i].setCedula(Integer.toString(i + 1));
			try {
				user[i].setPassword(Data.encrypt("123456"));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.save(user[i]);

			student[i] = new Student();
			student[i].setUserRef(user[i]);
			session.save(student[i]);

			int index = random.nextInt(group.length);

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

			//Me aseguro que si el usuario es estudiante
			//la referencia a profesor es null
			user[i].setStudentRef(student[i]);
			user[i].setTeacherRef(null);
			session.update(user[i]);

		}

		session.getTransaction().commit();
		session.close();

	}

	// --------------------------------------------------------------------------------

}
