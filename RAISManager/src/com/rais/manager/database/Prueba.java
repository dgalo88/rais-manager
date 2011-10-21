package com.rais.manager.database;

import java.util.Date;

import org.hibernate.Session;

public class Prueba {

	public static void main(String[] args) {

		Estudiante estudiante = new Estudiante();
		estudiante.setCedula("19422959");
		estudiante.setNombre("jesus");

		Date fecha = new Date();
		fecha.setTime(56);

		Encuesta encuesta1 = new Encuesta();
		encuesta1.setFechaEnviada(fecha);
		encuesta1.setEstudianteRef(estudiante);

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		session.save(estudiante);
		session.save(encuesta1);
		session.getTransaction().commit();
		session.close();

	}

}
