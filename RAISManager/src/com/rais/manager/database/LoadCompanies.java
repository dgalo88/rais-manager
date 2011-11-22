package com.rais.manager.database;

import org.hibernate.Session;

public class LoadCompanies {

	public static final int COMPANIES_NUM = 3;

	// --------------------------------------------------------------------------------

	public static void main(String[] args) {

		Group[] group = new Group[COMPANIES_NUM];

		for (int i = 0; i < group.length; i++) {

			group[i] = new Group();
			group[i].setName("Compañía " + Integer.toString(i + 1));

//			FillImage image = new FillImage( //
//					ImageReferenceCache.getInstance().getImageReference( //
//							"/com/rais/manager/images/companies/company" + (i + 1) + ".png"));
//
//			group[i].setLogo(image.getImage().getRenderId().getBytes());

//			Company company = new Company( //
//					"/com/rais/manager/images/companies/company" //
//					+ Integer.toString((i + 1)) + ".png");
//
//			byte[] logo = company.getLogo();
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

}

//--------------------------------------------------------------------------------

//class Company {
//
//	private byte[] logo;
//	private String pathName;
//
//	// --------------------------------------------------------------------------------
//
//	public Company(String name) {
//
//		pathName = getClass().getResource(name).getPath();
//		File file = new File(pathName);
//
//		byte[] data = new byte[(int) file.length()];
//		InputStream is = null;
//		try {
//			is = new FileInputStream(file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		try {
//			is.read(data);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		this.logo = data;
//
//	}
//
//	// --------------------------------------------------------------------------------
//
//	public byte[] getLogo() {
//		return logo;
//	}
//
//	public void setLogo(byte[] logo) {
//		this.logo = logo;
//	}
//
//	// --------------------------------------------------------------------------------
//
//	public String getPathName() {
//		return pathName;
//	}
//
//	public void setPathName(String pathName) {
//		this.pathName = pathName;
//	}
//
//	// --------------------------------------------------------------------------------
//
//}

//--------------------------------------------------------------------------------