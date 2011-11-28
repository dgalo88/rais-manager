package com.rais.manager.interfaz;

import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Column;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Grid;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Label;
import nextapp.echo.app.Panel;
import nextapp.echo.app.Row;
import nextapp.echo.app.TextField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.informagen.echo.app.IntegerTextField;

import com.rais.manager.RaisManagerApp;
import com.rais.manager.controller.Data;
import com.rais.manager.controller.Register;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class AddTeacherPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private String message = "";

	private TextField txtName;
	private IntegerTextField txtCedula;

	private Grid grid;
	private Column col;
	private Row errorRow;

	private int componentsCount = 0;

	// --------------------------------------------------------------------------------

	public AddTeacherPane() {
		initGui();
	}

	// --------------------------------------------------------------------------------

	private void initGui() {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);

		Row row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);

		col = new Column();
		col.setCellSpacing(new Extent(5));

		Row centerRow = new Row();
		centerRow.setStyle(GUIStyles.CENTER_ROW_STYLE);

		grid = new Grid();
		grid.setBackground(Color.WHITE);
		grid.setColumnWidth(0, new Extent(180));
		grid.setColumnWidth(1, new Extent(300));
		grid.setInsets(new Insets(5, 5, 5, 5));
		grid.setBorder(new Border( //
				new Extent(1), Color.BLACK, Border.STYLE_INSET));

		addComponentInCol(Constructor.initTopRow("Registro de Profesores", 14));

		Label lblName = new Label("Nombre y Apellido:");
		GUIStyles.setFont(lblName, GUIStyles.NORMAL);
		grid.add(lblName);

		txtName = new TextField();
		GUIStyles.setFont(txtName, GUIStyles.NORMAL);
		txtName.setWidth(new Extent(300));
		grid.add(txtName);

		Label lblCedula = new Label("Cédula:");
		GUIStyles.setFont(lblCedula, GUIStyles.NORMAL);
		grid.add(lblCedula);

		txtCedula = new IntegerTextField();
		GUIStyles.setFont(txtCedula, GUIStyles.NORMAL);
		txtCedula.setToolTipText("Ingrese la cédula del profesor " +
				"usando el siguiente formato: Ej: 012345678");
		txtCedula.setWidth(new Extent(280));
		txtCedula.setMaximumLength(9);
		txtCedula.setMinimumValue(0);
		grid.add(txtCedula);

		centerRow.add(grid);
		addComponentInCol(centerRow);

		centerRow = new Row();
		centerRow.setStyle(GUIStyles.CENTER_ROW_STYLE);
		centerRow.setCellSpacing(new Extent(5));
		centerRow.setInsets(new Insets(5, 5, 5, 5));

		Button btnCancel = new Button("Cancelar");
		btnCancel.setStyle(GUIStyles.BUTTON_STYLE);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnCancelClicked();
			}
		});
		centerRow.add(btnCancel);

		Button btnImport = new Button("Importar...");
		btnImport.setStyle(GUIStyles.BUTTON_STYLE);
		btnImport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnImportClicked();
			}
		});
		centerRow.add(btnImport);

		Button btnClean = new Button("Limpiar");
		btnClean.setStyle(GUIStyles.BUTTON_STYLE);
		btnClean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnCleanClicked();
			}
		});
		centerRow.add(btnClean);

		Button btnSend = new Button("Enviar");
		btnSend.setStyle(GUIStyles.BUTTON_STYLE);
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnSendClicked();
			}
		});
		centerRow.add(btnSend);

		addComponentInCol(centerRow);

		row.add(col);
		add(row);

	}

	// --------------------------------------------------------------------------------

	private void btnCancelClicked() {

		MainPane panel = new MainPane();
		app.getDesktop().setCentralPanel(panel);

	}

	// --------------------------------------------------------------------------------

	private void btnImportClicked() {

		ImportTeachersPane panel = new ImportTeachersPane();
		app.getDesktop().setCentralPanel(panel);

	}

	// --------------------------------------------------------------------------------

	private void btnCleanClicked() {

		message = "";

		txtName.setText("");
		txtName.set(PROPERTY_BACKGROUND, Color.WHITE);

		txtCedula.setText("");
		txtCedula.set(PROPERTY_BACKGROUND, Color.WHITE);

		if (col.getComponentCount() > componentsCount) {
			col.remove(errorRow);
		}

	}

	// --------------------------------------------------------------------------------

	private void btnSendClicked() {

		if (checkEmptyFields()) {
			return;
		}

		txtCedula.setText(Data.checkCedulaFormat(txtCedula.getText()));

		Register.createUser(this);
		app.getDesktop().setWindowPaneEmergente(message);

		btnCleanClicked();

	}

	// --------------------------------------------------------------------------------

	private boolean checkEmptyFields() {

		boolean flg = false;

		if (txtName.getText() == "") {
			txtName.set(PROPERTY_BACKGROUND, GUIStyles.ERROR_COLOR);
			flg = true;
		}

		if (txtCedula.getText() == "") {
			txtCedula.set(PROPERTY_BACKGROUND, GUIStyles.ERROR_COLOR);
			flg = true;
		}

		if (flg) {

			if (col.getComponentCount() > componentsCount) {
				col.remove(errorRow);
			}

			errorRow = new Row();
			errorRow.setStyle(GUIStyles.CENTER_ROW_STYLE);

			Label lblError = new Label("Todos los campos son obligatorios");
			GUIStyles.setFont(lblError, GUIStyles.ITALIC);

			errorRow.add(lblError);
			col.add(errorRow);

		}

		return flg;

	}

	// --------------------------------------------------------------------------------

	private void addComponentInCol(Component component) {
		col.add(component);
		componentsCount++;
	}

	// --------------------------------------------------------------------------------

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// --------------------------------------------------------------------------------

	public TextField getTxtName() {
		return txtName;
	}

	public void setTxtName(TextField txtName) {
		this.txtName = txtName;
	}

	public IntegerTextField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(IntegerTextField txtCedula) {
		this.txtCedula = txtCedula;
	}

	// --------------------------------------------------------------------------------

	public int getComponentsCount() {
		return componentsCount;
	}

	public void setComponentsCount(int componentsCount) {
		this.componentsCount = componentsCount;
	}

	// --------------------------------------------------------------------------------

}
