package com.rais.manager.database;

import java.util.Date;

import org.hibernate.Session;

public class Prueba {

	public static void main(String[] args) {

		User user = new User();
		user.setCedula("19422959");
		user.setName("jesus");

		Date date = new Date();
		date.setTime(56);

		Poll poll1 = new Poll();
		poll1.setSentDate(date);
		poll1.setStudentRef(user.getStudentRef());

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		session.save(user);
		session.save(poll1);
		session.getTransaction().commit();
		session.close();

	}

}
