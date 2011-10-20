package com.rais.manager;

import nextapp.echo.app.ApplicationInstance;
import nextapp.echo.webcontainer.WebContainerServlet;

@SuppressWarnings("serial")
public class RaisManagerServlet extends WebContainerServlet {

	@Override
	public ApplicationInstance newApplicationInstance() {
		return new RaisManagerApp();
	}

}
