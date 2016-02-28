package com.rais.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rais.manager.controller.Data;

/**
 * @author Donato Galo
 *
 */
@SuppressWarnings("serial")
public class ImagesDataServlet extends HttpServlet {

	// --------------------------------------------------------------------------------

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) //
			throws ServletException, IOException {

		String imageIdStr = req.getParameter("image_id");

		int imageIdInt = -1;

		try {
			imageIdInt = Integer.parseInt(imageIdStr);
		} catch (NumberFormatException e) {
			// ----------------------------------------
			// Do nothing, imageIdInt will remain in -1
			// ----------------------------------------
		}

		byte[] data;

		if (imageIdInt != -1) {
			data = Data.getCompanyLogo(imageIdInt);
		} else {
			data = loadImage("/com/rais/manager/images/companies/company1.png");
		}

		res.setContentType("image/png");
		res.getOutputStream().write(data);

	}

	// --------------------------------------------------------------------------------

	private byte[] loadImage(String name) throws IOException {

		File file = new File(getClass().getResource(name).getPath());

		byte[] data = new byte[(int) file.length()];
		InputStream is = new FileInputStream(file);
		is.read(data);

		is.close();

		return data;

	}

	// --------------------------------------------------------------------------------

}
