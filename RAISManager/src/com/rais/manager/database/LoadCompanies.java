package com.rais.manager.database;

import nextapp.echo.app.FillImage;

import org.hibernate.Session;

import com.rais.manager.ImageReferenceCache;

public class LoadCompanies {

	public static void main(String[] args) {

		Group[] group = new Group [3];

		for (int i = 0; i < group.length; i++) {

			group[i] = new Group();
			group[i].setName("Compañía " + Integer.toString(i + 1));

			FillImage image = new FillImage( //
					ImageReferenceCache.getInstance().getImageReference( //
							"/com/rais/manager/images/companies/company" + (i + 1) + ".png"));

			group[i].setLogo(image.getImage().getRenderId().getBytes());

		}

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		for (int i = 0; i < group.length; i++) {
			session.save(group[i]);
		}

		session.getTransaction().commit();
		session.close();

	}

}
