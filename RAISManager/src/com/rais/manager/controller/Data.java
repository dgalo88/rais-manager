package com.rais.manager.controller;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.rais.manager.database.Group;
import com.rais.manager.database.GroupStudent;
import com.rais.manager.database.SessionHibernate;
import com.rais.manager.database.Student;
import com.rais.manager.database.User;

public class Data {

	// --------------------------------------------------------------------------------

	private Data() {
		/* empty */
	}

	// --------------------------------------------------------------------------------

	public static Group getCompany(User user) {

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria( //
				User.class).add(Restrictions.eq( //
						"id", user.getId()));

		user = (User) criteria.uniqueResult();
		Student student = user.getStudentRef();

		GroupStudent groupStudent = //
				student.getGroupStudentList().get(0);

		Group group = groupStudent.getGroupRef();

		session.getTransaction().commit();
		session.close();

		return group;

	}

	// --------------------------------------------------------------------------------

	@SuppressWarnings("rawtypes")
	public static Image getCompanyLogo(Group group) throws IOException {

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria( //
				Group.class).add(Restrictions.eq( //
						"id", group.getId()));

		group = (Group) criteria.uniqueResult();

		byte[] bytes = group.getLogo();

		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		Iterator readers = ImageIO.getImageReadersByFormatName("png");
		ImageReader reader = (ImageReader) readers.next();
		Object source = bis; // File or InputStream
		ImageInputStream iis = ImageIO.createImageInputStream(source);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();

		session.getTransaction().commit();
		session.close();

		return reader.read(0, param);

	}

	// --------------------------------------------------------------------------------

	public static String encrypt(String password) //
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

	public static boolean checkPassword(String password, User user) {

		boolean result = false;

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria( //
				User.class).add(Restrictions.eq( //
						"id", user.getId()));

		user = (User) criteria.uniqueResult();

		try {

			result = user.getPassword().equals(encrypt(password)) //
					? true : false;

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.getTransaction().commit();
		session.close();

		return result;

	}

	// --------------------------------------------------------------------------------

	public static void changePassword( //
			String newPassword, User user) throws Exception {

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria( //
				User.class).add(Restrictions.eq( //
						"id", user.getId()));

		user = (User) criteria.uniqueResult();

		try {
			user.setPassword(encrypt(newPassword));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.getTransaction().commit();
		session.close();

	}

	// --------------------------------------------------------------------------------

}
