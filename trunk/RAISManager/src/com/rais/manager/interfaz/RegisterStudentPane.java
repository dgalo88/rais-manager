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
import nextapp.echo.app.RadioButton;
import nextapp.echo.app.Row;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.button.ButtonGroup;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.rais.manager.Desktop;
import com.rais.manager.RaisManagerApp;
import com.rais.manager.controller.Register;
import com.rais.manager.database.Estudiante;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class RegisterStudentPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();
	private Desktop desktop;

	private Estudiante student;

	private SelectField companySelectField;
	private String[] companyMenu;
	private ButtonGroup buttonGroup;
	private Row radioBtnRow;

	private Grid grid;
	private Column col;
	private Row errorRow;

	public RegisterStudentPane(Estudiante student) {

		this.student = student;
		desktop = app.getDesktop();
		initGui();

	}

	private void initGui() {

		setInsets(new Insets(5, 5, 5, 5));
		setAlignment(Alignment.ALIGN_CENTER);

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_CENTER);
		GUIStyles.setFont(row, GUIStyles.NORMAL, 12);

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

		Label lblCompany = new Label("Seleccione su Compañía:");
		GUIStyles.setFont(lblCompany, GUIStyles.NORMAL);
		grid.add(lblCompany);

		// Cargar compañías
		companyMenu = Register.loadCompaniesData();

		companySelectField = new SelectField(getCompanyMenu());
		companySelectField.setHeight(new Extent(25));
		companySelectField.setWidth(new Extent(305));
		GUIStyles.setFont(companySelectField, GUIStyles.NORMAL);
		companySelectField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				companySelected(companySelectField.getSelectedIndex());
			}
		});
		grid.add(companySelectField);

		Label lblDepartment = new Label("Seleccione Departamento:");
		GUIStyles.setFont(lblDepartment, GUIStyles.NORMAL);
		grid.add(lblDepartment);

		radioBtnRow = new Row();
		radioBtnRow.setCellSpacing(new Extent(5));
		radioBtnRow.setInsets(new Insets(5, 5, 5, 5));
		radioBtnRow.setAlignment(Alignment.ALIGN_CENTER);

		buttonGroup = new ButtonGroup();

		RadioButton isBtn = new RadioButton("Ingeniería de Software");
		isBtn.setDisabledForeground(Color.LIGHTGRAY);
		isBtn.setGroup(buttonGroup);
		radioBtnRow.add(isBtn);

		RadioButton bdBtn = new RadioButton("Bases de Datos");
		bdBtn.setDisabledForeground(Color.LIGHTGRAY);
		bdBtn.setGroup(buttonGroup);
		radioBtnRow.add(bdBtn);

		RadioButton bothBtn = new RadioButton("Ambas");
		bothBtn.setDisabledForeground(Color.LIGHTGRAY);
		bothBtn.setGroup(buttonGroup);
		radioBtnRow.add(bothBtn);

		setDepartmentsEnable(false);
		grid.add(radioBtnRow);

		col.add(grid);

		Row rowButtons = new Row();
		rowButtons.setCellSpacing(new Extent(5));
		rowButtons.setInsets(new Insets(5, 5, 5, 5));
		rowButtons.setAlignment(Alignment.ALIGN_CENTER);

		Button btnBack = new Button("Atrás");
		btnBack.setStyle(GUIStyles.DEFAULT_STYLE);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					btnBackClicked();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		rowButtons.add(btnBack);

		Button btnRegister = new Button("Registrarse");
		btnRegister.setStyle(GUIStyles.DEFAULT_STYLE);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnRegisterClicked();
			}
		});
		rowButtons.add(btnRegister);

		col.add(rowButtons);
		row.add(col);
		add(row);

	}

	// --------------------------------------------------------------------------------

	protected void companySelected(int selectedIndex) {

		if (selectedIndex == 0) {
			setDepartmentsEnable(false);
			return;
		}
		setDepartmentsEnable(true);

	}

	// --------------------------------------------------------------------------------

	private void btnBackClicked() throws Exception {

		RegisterPane pane = new RegisterPane(student);
		desktop.setCentralPanel(pane);

	}

	// --------------------------------------------------------------------------------

	private void btnRegisterClicked() {

		if (!checkEmptyFields()) {

			LoginPane pane = new LoginPane();
			desktop.setCentralPanel(pane);

		}

	}

	// --------------------------------------------------------------------------------

	private boolean checkEmptyFields() {

		boolean flg = false;

		if (companySelectField.getSelectedIndex() == 0) {
			companySelectField.set(PROPERTY_BACKGROUND, GUIStyles.ERRORCOLOR);
			flg = true;
		}
		if (!isDepartmentSelected()) {
			radioBtnRow.set(PROPERTY_BACKGROUND, GUIStyles.ERRORCOLOR);
			flg = true;
		}

		if (flg) {

			if (col.getComponentCount() > 3) {
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

	private void setDepartmentsEnable(boolean status) {

		RadioButton[] buttons = buttonGroup.getButtons();

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(status);
		}

	}

	// --------------------------------------------------------------------------------

	private boolean isDepartmentSelected() {

		RadioButton[] buttons = buttonGroup.getButtons();

		for (int i = 0; i < buttons.length; i++) {

			if (buttons[i].isSelected()) {
				return true;
			}

		}
		return false;

	}

	// --------------------------------------------------------------------------------

	public String [] getCompanyMenu() {
		return companyMenu;
	}

	public void setCompanyMenu(String [] companyMenu) {
		this.companyMenu = companyMenu;
	}

	// --------------------------------------------------------------------------------

}
