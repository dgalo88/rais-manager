package com.rais.manager.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.hibernate.Session;

public class LoadCompanies {

	public static final int COMPANIES_NUM = 3;

	// --------------------------------------------------------------------------------

	private LoadCompanies() {

		System.out.println("Cargar datos de compañías");

		Group[] group = new Group[COMPANIES_NUM];

		for (int i = 0; i < group.length; i++) {

			group[i] = new Group();
			group[i].setName("Compañia " + Integer.toString(i + 1));

//			FillImage image = new FillImage( //
//					ImageReferenceCache.getInstance().getImageReference( //
//							"/com/rais/manager/images/companies/company" + (i + 1) + ".png"));
//
//			group[i].setLogo(image.getImage().getRenderId().getBytes());

//			byte[] logo = loadLogo("/com/rais/manager/images/companies/company" //
//					+ Integer.toString((i + 1)) + ".png");
//
//			group[i].setLogo(logo);

		}

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		for (int i = 0; i < group.length; i++) {
			session.save(group[i]);
		}

		session.getTransaction().commit();
		session.close();

	}

	// --------------------------------------------------------------------------------

	@SuppressWarnings("unused")
	private byte[] loadLogo(String name) {

		File file = new File(getClass().getResource(name).getPath());

		byte[] data = new byte[(int) file.length()];
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			is.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}

	// --------------------------------------------------------------------------------

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		LoadCompanies loadCompanies = new LoadCompanies();
	}

	// --------------------------------------------------------------------------------

}
