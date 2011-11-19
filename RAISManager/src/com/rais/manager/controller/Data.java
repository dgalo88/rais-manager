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
import com.rais.manager.database.SessionHibernate;
import com.rais.manager.database.User;

public class Data {

	// --------------------------------------------------------------------------------

	private Data() {
		/* empty */
	}

	// --------------------------------------------------------------------------------

	@SuppressWarnings("rawtypes")
	public static Image getCompanyLogo(User user) throws IOException {

		Session session = null;

		try {
			session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();

			//			List<GroupStudent> groupStudentList = //
			//					user.getStudentRef().getGroupStudentList();

			//			Group group = user.getStudentRef().getGroupStudentList() //
			//					.get(0).getGroupRef();

			Criteria criteria = session.createCriteria( //
					Group.class).add(Restrictions.eq( //
							"name", "ATIS"));

			Group group = (Group) criteria.uniqueResult();

			byte[] bytes = group.getLogo();

			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			Iterator readers = ImageIO.getImageReadersByFormatName("png");
			ImageReader reader = (ImageReader) readers.next();
			Object source = bis; // File or InputStream
			ImageInputStream iis = ImageIO.createImageInputStream(source);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();

			return reader.read(0, param);

		} finally {

			// ----------------------------------------
			// whatever happens, always close
			// ----------------------------------------

			if (session != null) {
				if (session.getTransaction() != null) {
					session.getTransaction().commit();
				}

				session.close();
			}

		}

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

}
