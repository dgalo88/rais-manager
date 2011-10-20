package com.rais.manager.interfaz;

import nextapp.echo.app.Panel;

@SuppressWarnings("serial")
public class MainPane extends Panel {

	public MainPane() {
		initGui();
	}

	private void initGui() {

		add(Constructor.initTopRow("Main Page", 14));

	}

}
