package com.rais.manager.database;

import java.util.List;
import java.util.Random;

import org.hibernate.Session;

import com.rais.manager.controller.Data;
import com.rais.manager.controller.Register;

public class LoadStudents {

	public static final int STUDENTS_NUM = 10;
	public static final int TEACHERS_NUM = 1;

	// --------------------------------------------------------------------------------

	private LoadStudents() {
		/* empty */
	}

	// --------------------------------------------------------------------------------

	public static void main(String[] args) {

		System.out.println("Cargar datos de estudiantes");

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		Random random = new Random();

		//Cargar compañías

		List<Group> groupList = Register.loadCompaniesData();

		Group[] group = new Group[groupList.size()];
		for (int i = 0; i < group.length; i++) {
			group[i] = groupList.get(i);
		}

		User[] user = new User[STUDENTS_NUM + TEACHERS_NUM];

		//Cargar jefe ejecutivo

		Teacher[] teacher = new Teacher[TEACHERS_NUM];

		for (int i = 0; i < teacher.length; i++) {

			user[i] = new User();
			user[i].setCedula(Data.checkCedulaFormat(Integer.toString(1234 + i)));
			user[i].setName("Jefe Ejecutivo 0" + Integer.toString(i + 1));

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

			for (int j = 0; j < group.length; j++) {

				GroupTeacher groupTeacher = new GroupTeacher();
				groupTeacher.setGroupRef(group[j]);
				groupTeacher.setTeacherRef(teacher[i]);
				session.save(groupTeacher);

			}
			session.update(teacher[i]);

			//Me aseguro que si el usuario es profesor
			//la referencia a estudiante es null
			user[i].setTeacherRef(teacher[i]);
			user[i].setStudentRef(null);
			session.update(user[i]);

		}

		//Cargar estudiantes

		Student[] student = new Student[STUDENTS_NUM];

		for (int i = 0; i < student.length; i++) {

			user[i] = new User();
			user[i].setName("Estudiante Numero 0" + (i + 1));
			user[i].setCedula(Data.checkCedulaFormat(Integer.toString(i + 1)));
			try {
//				user[i].setPassword(Data.encrypt( //
//						user[i].getCedula().substring( //
//								user[i].getCedula().length() - 6)));
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
