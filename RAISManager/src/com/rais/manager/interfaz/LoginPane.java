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
import nextapp.echo.app.TextField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.rais.manager.Desktop.DesktopType;
import com.rais.manager.RaisManagerApp;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class LoginPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private TextField txtUser;
	private PasswordField fldPassword;

	public LoginPane() {
		initGui();
	}

	private void initGui() {

		setInsets(new Insets(5, 5, 5, 5));
		setAlignment(Alignment.ALIGN_CENTER);

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_CENTER);
		GUIStyles.setFont(row, GUIStyles.NORMAL, 12);

		Column col = new Column();
		col.setCellSpacing(new Extent(5));

		col.add(Constructor.initTopRow("Ingresar al Sistema", 14));

		Grid grid = new Grid();
		grid.setInsets(new Insets(5, 5, 5, 5));
		grid.setBorder(new Border( //
				new Extent(1), Color.BLACK, Border.STYLE_INSET));

		Label lblUser = new Label("Usuario:");
		GUIStyles.setFont(lblUser, GUIStyles.NORMAL);
		grid.add(lblUser);

		txtUser = new TextField();
		GUIStyles.setFont(txtUser, GUIStyles.NORMAL);
		grid.add(txtUser);

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
		btnEnter.setStyle(GUIStyles.DEFAULT_STYLE);
		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEnterClicked();
			}
		});
		rowButtons.add(btnEnter);

		Button btnRegister = new Button("Registrarse");
		btnRegister.setStyle(GUIStyles.DEFAULT_STYLE);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnRegisterClicked();
			}
		});
		rowButtons.add(btnRegister);

		col.add(rowButtons);
		row.add(col);
		add(row);

	}

	protected void btnRegisterClicked() {

		RegisterPane pane = new RegisterPane();
		app.getDesktop().setCentralPanel(pane);

	}

	protected void btnEnterClicked() {

//		if (txtUser.getText().isEmpty()) {
//			app.getDesktop().setWindowPaneEmergente( //
//					"Ingrese el nombre de usuario");
//			return;
//		}
//		if (fldPassword.getText().isEmpty()) {
//			app.getDesktop().setWindowPaneEmergente( //
//					"Ingrese la contraseña");
//			return;
//		}

		app.setNewDesktop(DesktopType.MAIN);

	}

}
