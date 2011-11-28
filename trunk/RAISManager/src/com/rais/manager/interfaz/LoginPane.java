package com.rais.manager.interfaz;

import nextapp.echo.app.Alignment;
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

import org.informagen.echo.app.IntegerTextField;

import com.rais.manager.Desktop.DesktopType;
import com.rais.manager.RaisManagerApp;
import com.rais.manager.controller.Data;
import com.rais.manager.controller.Login;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class LoginPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private IntegerTextField txtCedula;
	private PasswordField fldPassword;

	public LoginPane() {
		initGui();
	}

	private void initGui() {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);

		Row row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);

		Column col = new Column();
		col.setCellSpacing(new Extent(5));

		col.add(Constructor.initTopRow("Ingresar al Sistema", 14));

		Grid grid = new Grid();
		grid.setInsets(new Insets(5, 5, 5, 5));
		grid.setBorder(new Border( //
				new Extent(1), Color.BLACK, Border.STYLE_INSET));

		Label lblCedula = new Label("Cédula:");
		GUIStyles.setFont(lblCedula, GUIStyles.NORMAL);
		grid.add(lblCedula);

		txtCedula = new IntegerTextField();
		GUIStyles.setFont(txtCedula, GUIStyles.NORMAL);
		txtCedula.setToolTipText("Ingrese la cédula del estudiante " +
				"usando el siguiente formato: Ej: 012345678");
		txtCedula.setMaximumLength(9);
		txtCedula.setMinimumValue(0);
		grid.add(txtCedula);

		Label lblPassword = new Label("Contraseña:");
		GUIStyles.setFont(lblPassword, GUIStyles.NORMAL);
		grid.add(lblPassword);

		fldPassword = new PasswordField();
		GUIStyles.setFont(fldPassword, GUIStyles.NORMAL);
		grid.add(fldPassword);

		Row centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);
		centerRow.add(grid);
		col.add(centerRow);

		Row rowButtons = new Row();
		rowButtons.setCellSpacing(new Extent(5));
		rowButtons.setInsets(new Insets(5, 5, 5, 5));
		rowButtons.setAlignment(Alignment.ALIGN_CENTER);

		Button btnEnter = new Button("Entrar");
		btnEnter.setStyle(GUIStyles.BUTTON_STYLE);
		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnEnterClicked();
			}
		});
		rowButtons.add(btnEnter);

//		Button btnRegister = new Button("Registrarse");
//		btnRegister.setStyle(GUIStyles.BUTTON_STYLE);
//		btnRegister.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent evt) {
//				btnRegisterClicked();
//			}
//		});
//		rowButtons.add(btnRegister);

		col.add(rowButtons);
		row.add(col);
		add(row);

	}

	// --------------------------------------------------------------------------------

//	protected void btnRegisterClicked() {
//
//		RegisterPane pane = new RegisterPane(new User());
//		app.getDesktop().setCentralPanel(pane);
//
//	}

	// --------------------------------------------------------------------------------

	protected void btnEnterClicked() {

		if (txtCedula.getText().isEmpty()) {
			app.getDesktop().setWindowPaneEmergente( //
					"Ingrese número de cédula");
			return;
		}

		if (!validatePassword()) {
			return;
		}

		txtCedula.setText(Data.checkCedulaFormat(txtCedula.getText()));

		if (!Login.login(this)) {
			app.getDesktop().setWindowPaneEmergente( //
					"Combinación usuario-contraseña no válida");
			return;
		}

		app.setNewDesktop(DesktopType.MAIN);

	}

	// --------------------------------------------------------------------------------

	private boolean validatePassword() {

		if (fldPassword.getText().isEmpty()) {
			app.getDesktop().setWindowPaneEmergente( //
					"Ingrese la contraseña");
			return false;
		}

		if (fldPassword.getText().length() < 6) {
			app.getDesktop().setWindowPaneEmergente( //
					"La contraseña debe tener un mínimo de 6 dígitos");
			return false;
		}

		return true;

	}

	// --------------------------------------------------------------------------------

	public IntegerTextField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(IntegerTextField txtCedula) {
		this.txtCedula = txtCedula;
	}

	public PasswordField getFldPassword() {
		return fldPassword;
	}

	public void setFldPassword(PasswordField fldPassword) {
		this.fldPassword = fldPassword;
	}

	// --------------------------------------------------------------------------------

	public RaisManagerApp getRaisManagerApp() {
		return app;
	}

	// --------------------------------------------------------------------------------

}
