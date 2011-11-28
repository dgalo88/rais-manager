/**
 * 
 */
package com.rais.manager.database;

import org.hibernate.Session;

import com.rais.manager.controller.Data;

/**
 * @author Donato Galo
 *
 */
public class CreateSuperUser {

	// --------------------------------------------------------------------------------

	public static void main(String[] args) {

		System.out.println("Cargar datos de estudiantes");

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		User user = new User();

		user.setCedula(Data.checkCedulaFormat(Integer.toString(0)));
		user.setName("Super Usuario");
		try {
			user.setPassword(Data.encrypt("super123"));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.save(user);

		Teacher teacher = new Teacher();
		teacher.setUserRef(user);
		session.save(teacher);

		//Me aseguro que si el usuario es profesor
		//la referencia a estudiante es null
		user.setTeacherRef(teacher);
		user.setStudentRef(null);
		session.update(user);

		session.getTransaction().commit();
		session.close();


	}

	// --------------------------------------------------------------------------------

}
