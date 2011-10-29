package com.rais.manager.interfaz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import nextapp.echo.app.SelectField;
import nextapp.echo.app.TextField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.rais.manager.Desktop;
import com.rais.manager.RaisManagerApp;
import com.rais.manager.controller.Register;
import com.rais.manager.database.User;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class RegisterPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();
	private Desktop desktop;

	private User user;

	private String message = "";

	private TextField txtAlias;
	private TextField txtName;
	private TextField txtCedula;
	private TextField txtMail;
	private PasswordField fldPassword;
	private PasswordField fldConfirmPassword;

	private String userType;
	private SelectField selUserType;
	private String[] userTypeMenu;

	private Grid grid;
	private Column col;
	private Row errorRow;

	public RegisterPane(User user) {

		this.user = user;
		desktop = app.getDesktop();
		initGui();

	}

	private void initGui() {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);

		Row row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);

		col = new Column();
		col.setCellSpacing(new Extent(5));

		grid = new Grid();
		grid.setBackground(Color.WHITE);
		grid.setColumnWidth(0, new Extent(180));
		grid.setColumnWidth(1, new Extent(300));
		grid.setInsets(new Insets(5, 5, 5, 5));
		grid.setBorder(new Border( //
				new Extent(1), Color.BLACK, Border.STYLE_INSET));

		col.add(Constructor.initTopRow("Registro de Usuario", 14));
		col.add(Constructor.initTopRow("Datos Personales:", 13));

		Label lblAlias = new Label("Nombre de Usuario:");
		GUIStyles.setFont(lblAlias, GUIStyles.NORMAL);
		grid.add(lblAlias);

		txtAlias = new TextField();
		GUIStyles.setFont(txtAlias, GUIStyles.NORMAL);
		txtAlias.setWidth(new Extent(300));
		txtAlias.setMaximumLength(10);
		txtAlias.setToolTipText( //
				"Digite el nombre de usuario que utilizará para " +
				"acceder al sistema. Este debe contener máximo 10 dígitos");
		txtAlias.setText(user.getAlias());
		grid.add(txtAlias);

		Label lblName = new Label("Nombre y Apellido:");
		GUIStyles.setFont(lblName, GUIStyles.NORMAL);
		grid.add(lblName);

		txtName = new TextField();
		GUIStyles.setFont(txtName, GUIStyles.NORMAL);
		txtName.setWidth(new Extent(300));
		txtName.setText(user.getName());
		grid.add(txtName);

		Label lblCedula = new Label("Cédula:");
		GUIStyles.setFont(lblCedula, GUIStyles.NORMAL);
		grid.add(lblCedula);

		txtCedula = new TextField();
		GUIStyles.setFont(txtCedula, GUIStyles.NORMAL);
		txtCedula.setWidth(new Extent(300));
		txtCedula.setMaximumLength(10);
		txtCedula.setText(user.getCedula());
		grid.add(txtCedula);

		Label lblMail = new Label("Correo:");
		GUIStyles.setFont(lblMail, GUIStyles.NORMAL);
		grid.add(lblMail);

		txtMail = new TextField();
		GUIStyles.setFont(txtMail, GUIStyles.NORMAL);
		txtMail.setWidth(new Extent(300));
		txtMail.setText(user.getMail());
		grid.add(txtMail);

		Label lblCompany = new Label("Tipo de usuario:");
		GUIStyles.setFont(lblCompany, GUIStyles.NORMAL);
		grid.add(lblCompany);

		userTypeMenu = new String[3];
		userTypeMenu[0] = "Seleccione un tipo";
		userTypeMenu[1] = "Estudiante";
		userTypeMenu[2] = "Profesor";

		selUserType = new SelectField(userTypeMenu);
		selUserType.setHeight(new Extent(25));
		selUserType.setWidth(new Extent(305));
		GUIStyles.setFont(selUserType, GUIStyles.NORMAL);
		selUserType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				userTypeSelected(selUserType.getSelectedIndex());
			}
		});
		grid.add(selUserType);

		Label lblPassword = new Label("Contraseña:");
		GUIStyles.setFont(lblPassword, GUIStyles.NORMAL);
		grid.add(lblPassword);

		fldPassword = new PasswordField();
		GUIStyles.setFont(fldPassword, GUIStyles.NORMAL);
		fldPassword.setWidth(new Extent(300));
		fldPassword.setMaximumLength(12);
		fldPassword.setToolTipText( //
				"Digite la contraseña que utilizará para acceder al sistema. " + //
				"Esta debe contener entre 6 y 12 dígitos");
		grid.add(fldPassword);

		Label lblConfirmPassword = new Label("Confirmar Contraseña:");
		GUIStyles.setFont(lblConfirmPassword, GUIStyles.NORMAL);
		grid.add(lblConfirmPassword);

		fldConfirmPassword = new PasswordField();
		GUIStyles.setFont(fldConfirmPassword, GUIStyles.NORMAL);
		fldConfirmPassword.setWidth(new Extent(300));
		fldConfirmPassword.setMaximumLength(12);
		fldConfirmPassword.setToolTipText( //
				"Confirme la contraseña digitada anteriormente");
		grid.add(fldConfirmPassword);

		col.add(grid);

		Row rowButtons = new Row();
		rowButtons.setCellSpacing(new Extent(5));
		rowButtons.setInsets(new Insets(5, 5, 5, 5));
		rowButtons.setAlignment(Alignment.ALIGN_CENTER);

		Button btnCancel = new Button("Cancelar");
		btnCancel.setStyle(GUIStyles.BUTTON_STYLE);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnCancelClicked();
			}
		});
		rowButtons.add(btnCancel);

		Button btnNext = new Button("Siguiente");
		btnNext.setStyle(GUIStyles.BUTTON_STYLE);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnNextClicked();
			}
		});
		rowButtons.add(btnNext);

		col.add(rowButtons);
		row.add(col);
		add(row);

	}

	// --------------------------------------------------------------------------------

	protected void userTypeSelected(int selectedIndex) {

		if (selectedIndex == 0) {
			return;
		}
		setUserType(userTypeMenu[selectedIndex]);

	}

	// --------------------------------------------------------------------------------

	private void btnCancelClicked() {

		LoginPane pane = new LoginPane();
		desktop.setCentralPanel(pane);

	}

	// --------------------------------------------------------------------------------

	private void btnNextClicked() {

		if (txtAlias.getText() == "") {
			desktop.setWindowPaneEmergente("Escoge un nombre de usuario");
			return;
		}

		if (checkEmptyFields()) {
			return;
		}

		if (!validateMail()) {
			desktop.setWindowPaneEmergente("Formato de correo no válido");
			return;
		}

		if (!validatePassword()) {
			return;
		}

		if (!Register.createUser(this)) {
			desktop.setWindowPaneEmergente(message);
			return;
		}

		if (selUserType.getSelectedIndex() == 1) {

			RegisterStudentPane pane = new RegisterStudentPane(user);
			desktop.setCentralPanel(pane);

		}
		if (selUserType.getSelectedIndex() == 2) {

			RegisterTeacherPane pane = new RegisterTeacherPane();
			desktop.setCentralPanel(pane);

		}

	}

	// --------------------------------------------------------------------------------

	private boolean validateMail() {

		Pattern pattern = Pattern.compile( //
				"[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*@[a-z0-9][\\w\\.-]" +
				"*[a-z0-9]\\.[a-z][a-z\\.]*[a-z]$");
		Matcher matcher = pattern.matcher(txtMail.getText());

		return matcher.matches();

	}

	// --------------------------------------------------------------------------------

	private boolean validatePassword() {

		if (fldPassword.getText().length() < 6) {
			desktop.setWindowPaneEmergente( //
					"La contraseña debe tener un mínimo de 6 dígitos");
			return false;
		}

		if (!fldConfirmPassword.getText().equals(fldPassword.getText())) {

			if (col.getComponentCount() > 4) {
				col.remove(errorRow);
			}

			errorRow = new Row();
			Label lblError = new Label("Por favor confirma tu contraseña");
			GUIStyles.setFont(lblError, GUIStyles.ITALIC);
			errorRow.add(lblError);
			errorRow.setAlignment(Alignment.ALIGN_CENTER);
			col.add(errorRow);

			getFldPassword().set(PROPERTY_BACKGROUND, GUIStyles.ERROR_COLOR);
			fldConfirmPassword.set(PROPERTY_BACKGROUND, GUIStyles.ERROR_COLOR);

			return false;

		}

		return true;

	}

	// --------------------------------------------------------------------------------

	private boolean checkEmptyFields() {

		boolean flg = false;

		if (txtAlias.getText() == "") {
			txtAlias.set(PROPERTY_BACKGROUND, GUIStyles.ERROR_COLOR);
			flg = true;
		}

		if (txtName.getText() == "") {
			txtName.set(PROPERTY_BACKGROUND, GUIStyles.ERROR_COLOR);
			flg = true;
		}

		if (txtCedula.getText() == "") {
			txtCedula.set(PROPERTY_BACKGROUND, GUIStyles.ERROR_COLOR);
			flg = true;
		}

		if (txtMail.getText() == "") {
			txtMail.set(PROPERTY_BACKGROUND, GUIStyles.ERROR_COLOR);
			flg = true;
		}
		if (selUserType.getSelectedIndex() == 0) {
			selUserType.set(PROPERTY_BACKGROUND, GUIStyles.ERROR_COLOR);
			flg = true;
		}
		if (fldPassword.getText() == "") {
			fldPassword.set(PROPERTY_BACKGROUND, GUIStyles.ERROR_COLOR);
			flg = true;
		}
		if (fldConfirmPassword.getText() == "") {
			fldConfirmPassword.set(PROPERTY_BACKGROUND, GUIStyles.ERROR_COLOR);
			flg = true;
		}

		if (flg) {

			if (col.getComponentCount() > 4) {
				col.remove(errorRow);
			}

			errorRow = new Row();
			Label lblError = new Label("Todos los campos son obligatorios");
			GUIStyles.setFont(lblError, GUIStyles.ITALIC);
			errorRow.add(lblError);
			errorRow.setAlignment(Alignment.ALIGN_CENTER);
			col.add(errorRow);

			return true;

		}

		return false;

	}

	// --------------------------------------------------------------------------------

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// --------------------------------------------------------------------------------

	public TextField getTxtAlias() {
		return txtAlias;
	}

	public void setTxtAlias(TextField txtAlias) {
		this.txtAlias = txtAlias;
	}

	public TextField getTxtName() {
		return txtName;
	}

	public void setTxtName(TextField txtName) {
		this.txtName = txtName;
	}

	public TextField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(TextField txtCedula) {
		this.txtCedula = txtCedula;
	}

	public TextField getTxtMail() {
		return txtMail;
	}

	public void setTxtMail(TextField txtMail) {
		this.txtMail = txtMail;
	}

	public PasswordField getFldPassword() {
		return fldPassword;
	}

	public void setFldPassword(PasswordField fldPassword) {
		this.fldPassword = fldPassword;
	}

	// --------------------------------------------------------------------------------

	public String [] getUserTypeMenu() {
		return userTypeMenu;
	}

	public void setUserTypeMenu(String [] userTypeMenu) {
		this.userTypeMenu = userTypeMenu;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	// --------------------------------------------------------------------------------

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// --------------------------------------------------------------------------------

}
