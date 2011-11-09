package com.rais.manager.interfaz;

import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;
import nextapp.echo.app.Panel;
import nextapp.echo.app.Row;

import com.rais.manager.RaisManagerApp;
import com.rais.manager.database.User;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class ProfilePane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private User user;

	public ProfilePane() {

		user = app.getUser();
		initGui();

	}

	// --------------------------------------------------------------------------------

	private void initGui() {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);

		Row row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);

		Column col = new Column();
		col.setCellSpacing(new Extent(10));

		col.add(Constructor.initTopRow("Perfil", 14));

		Label lblName = new Label("Nombre: " + user.getName());
		GUIStyles.setFont(lblName, GUIStyles.NORMAL, 14);
		col.add(lblName);

		Label lblCedula = new Label("Cedula: " + user.getCedula());
		GUIStyles.setFont(lblCedula, GUIStyles.NORMAL, 14);
		col.add(lblCedula);

//		Label lblMail = new Label("Correo: " + student.getMail());
//		GUIStyles.setFont(lblMail, GUIStyles.NORMAL, 14);
//		col.add(lblMail);

		row.add(col);
		add(row);

	}

	// --------------------------------------------------------------------------------

}
