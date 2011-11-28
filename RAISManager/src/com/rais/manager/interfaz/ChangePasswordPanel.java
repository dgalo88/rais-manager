package com.rais.manager.interfaz;

import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Grid;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Label;
import nextapp.echo.app.Panel;
import nextapp.echo.app.PasswordField;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.rais.manager.RaisManagerApp;
import com.rais.manager.controller.Data;
import com.rais.manager.database.User;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class ChangePasswordPanel extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private User user;

	private PasswordField fldActualPassword;
	private PasswordField fldNewPassword;
	private PasswordField fldConfirmPassword;

	// --------------------------------------------------------------------------------

	public ChangePasswordPanel() {

		user = app.getUser();
		initGui();

	}

	// --------------------------------------------------------------------------------

	private void initGui() {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);

		Column col = new Column();
		col.setCellSpacing(new Extent(20));

		Row row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);

		Grid grid = new Grid();
		grid.setInsets(new Insets(5, 5, 5, 5));
		grid.setBorder(new Border( //
				new Extent(1), Color.BLACK, Border.STYLE_INSET));

		col.add(Constructor.initTopRow("Cambiar contraseña", 14));

		Label lblActualPassword = new Label("Contraseña actual:");
		GUIStyles.setFont(lblActualPassword, GUIStyles.NORMAL);
		grid.add(lblActualPassword);

		fldActualPassword = new PasswordField();
		GUIStyles.setFont(fldActualPassword, GUIStyles.NORMAL);
		fldActualPassword.setWidth(new Extent(300));
		fldActualPassword.setToolTipText( //
				"Digite la contraseña que utiliza actualmente para acceder al sistema. " + //
				"Esta debe contener mínimo 6 dígitos");
		grid.add(fldActualPassword);

		Label lblNewPassword = new Label("Contraseña nueva:");
		GUIStyles.setFont(lblNewPassword, GUIStyles.NORMAL);
		grid.add(lblNewPassword);

		fldNewPassword = new PasswordField();
		GUIStyles.setFont(fldNewPassword, GUIStyles.NORMAL);
		fldNewPassword.setWidth(new Extent(300));
		fldNewPassword.setToolTipText( //
				"Digite la nueva contraseña que utilizará para acceder al sistema. " + //
				"Esta debe contener mínimo 6 dígitos");
		grid.add(fldNewPassword);

		Label lblConfirmPassword = new Label("Confirmar Contraseña:");
		GUIStyles.setFont(lblConfirmPassword, GUIStyles.NORMAL);
		grid.add(lblConfirmPassword);

		fldConfirmPassword = new PasswordField();
		GUIStyles.setFont(fldConfirmPassword, GUIStyles.NORMAL);
		fldConfirmPassword.setWidth(new Extent(300));
		fldConfirmPassword.setToolTipText( //
				"Confirme la contraseña digitada anteriormente");
		grid.add(fldConfirmPassword);

		row.add(grid);
		col.add(row);

		row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);
		row.setCellSpacing(new Extent(20));

		Button btnCancel = new Button("Cancelar");
		btnCancel.setStyle(GUIStyles.BUTTON_STYLE);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancelClicked();
			}
		});
		row.add(btnCancel);

		Button btnChange = new Button("Cambiar");
		btnChange.setStyle(GUIStyles.BUTTON_STYLE);
		btnChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnChangeClicked();
			}
		});
		row.add(btnChange);

		col.add(row);
		add(col);

	}

	// --------------------------------------------------------------------------------

	private void btnCancelClicked() {

		ProfilePane panel = new ProfilePane();
		app.getDesktop().setCentralPanel(panel);

	}

	// --------------------------------------------------------------------------------

	private void btnChangeClicked() {

		if (!checkFields()) {
			return;
		}

		String message = "";
		try {
			message = Data.changePassword(fldNewPassword.getText(), user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		app.getDesktop().setWindowPaneEmergente(message);

		ProfilePane panel = new ProfilePane();
		app.getDesktop().setCentralPanel(panel);

	}

	// --------------------------------------------------------------------------------

	private boolean checkFields() {

		if (fldActualPassword.getText().isEmpty()) {
			app.getDesktop().setWindowPaneEmergente( //
					"Ingresa la contraseña actual");
			return false;
		}

		if (fldNewPassword.getText().isEmpty()) {
			app.getDesktop().setWindowPaneEmergente( //
					"Ingresa la nueva contraseña");
			return false;
		}

		if (fldConfirmPassword.getText().isEmpty()) {
			app.getDesktop().setWindowPaneEmergente( //
					"Confirma la nueva contraseña");
			return false;
		}

		if (!checkPasswordSize()) {
			app.getDesktop().setWindowPaneEmergente( //
					"La contraseña debe tener un mínimo de 6 dígitos");
			return false;
		}

		if (fldActualPassword.getText().equals(fldNewPassword.getText())) {
			app.getDesktop().setWindowPaneEmergente( //
					"Ingrese una contraseña diferente a la actual");
			return false;
		}

		if (!fldConfirmPassword.getText().equals(fldNewPassword.getText())) {
			app.getDesktop().setWindowPaneEmergente( //
					"Contraseña no coincide. Por favor confirma la contraseña");
			return false;
		}

		if (!Data.checkPassword(fldActualPassword.getText(), user)) {
			app.getDesktop().setWindowPaneEmergente( //
					"Contraseña incorrecta");
			return false;
		}

		return true;

	}

	// --------------------------------------------------------------------------------

	private boolean checkPasswordSize() {

		return !((fldActualPassword.getText().length() < 6) //
				|| (fldNewPassword.getText().length() < 6) //
				|| (fldConfirmPassword.getText().length() < 6));

	}

	// --------------------------------------------------------------------------------

}
