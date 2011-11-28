package com.rais.manager.interfaz;

import nextapp.echo.app.Button;
import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Label;
import nextapp.echo.app.Panel;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.rais.manager.RaisManagerApp;
import com.rais.manager.controller.Data;
import com.rais.manager.database.Group;
import com.rais.manager.database.User;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class ProfilePane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private User user;
	private Group group; 

	private Row row;
	private Column col;

	// --------------------------------------------------------------------------------

	public ProfilePane() {

		user = app.getUser();
		initGui();

	}

	// --------------------------------------------------------------------------------

	private void initGui() {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);

		row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);

		col = new Column();
		col.setCellSpacing(new Extent(10));

		col.add(Constructor.initTopRow("Perfil", 14));

		Label lblName = new Label("Nombre: " + user.getName());
		GUIStyles.setFont(lblName, GUIStyles.NORMAL, 14);
		col.add(lblName);

		Label lblCedula = new Label("Cedula: " + user.getCedula());
		GUIStyles.setFont(lblCedula, GUIStyles.NORMAL, 14);
		col.add(lblCedula);

		if (user.getStudentRef() != null) {
			group = Data.getCompany(user);
			addStudentGui();
		}

		Row buttonRow = new Row();
		buttonRow.setStyle(GUIStyles.CENTER_ROW_STYLE);
		buttonRow.setInsets(new Insets(0, 10, 0, 10));

		Button btnNewPassword = new Button("Cambiar contraseña");
		btnNewPassword.setStyle(GUIStyles.BUTTON_STYLE);
		btnNewPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNewPasswordClicked();
			}
		});
		buttonRow.add(btnNewPassword);

		col.add(buttonRow);
		row.add(col);
		add(row);

	}

	// --------------------------------------------------------------------------------

	private void addStudentGui() {

		Label lblCompany = new Label("Compañía: " + group.getName());
		GUIStyles.setFont(lblCompany, GUIStyles.NORMAL, 14);
		col.add(lblCompany);

		//Cargar logo de la Compañía
//		app.setImageReference(new HttpImageReference( //
//				"imagesdata?image_id=" + Integer.toString(group.getId())));
//
//		Label lblLogo = new Label(app.getImageReference());
//		GUIStyles.setFont(lblLogo, GUIStyles.NORMAL, 14);
//		col.add(lblLogo);

	}

	// --------------------------------------------------------------------------------

	private void btnNewPasswordClicked() {

		ChangePasswordPanel panel = new ChangePasswordPanel();
		app.getDesktop().setCentralPanel(panel);

	}

	// --------------------------------------------------------------------------------

}
