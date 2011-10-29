package com.rais.manager.interfaz;

import com.rais.manager.RaisManagerApp;
import com.rais.manager.database.User;
import com.rais.manager.styles.GUIStyles;

import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Panel;

@SuppressWarnings("serial")
public class MainPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private User user;

	public MainPane() {

		user = app.getUser();
		initGui();

	}

	private void initGui() {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);

		Column col = new Column();
		col.setCellSpacing(new Extent(5));

		col.add(Constructor.initTopRow("Hola " + user.getName() + ", Bienvenido!"));

		add(col);

	}

	// --------------------------------------------------------------------------------

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// --------------------------------------------------------------------------------

}
